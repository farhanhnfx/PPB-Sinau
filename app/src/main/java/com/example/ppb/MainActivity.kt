package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ppb.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val firestore = FirebaseFirestore.getInstance()
    private val noteCollectionRef = firestore.collection("financial_notes")
    private val notesLiveData: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeNotes()
        getAllNotes()

        with(binding) {
            btnAdd.setOnClickListener{
                showPopupDialog()
            }
            rvContent.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 || dy < 0) {
                        btnAdd.visibility = View.GONE
                    }
                }
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        btnAdd.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun observeNotes() {
        notesLiveData.observe(this) {
            notes ->
            val listAdapter = NoteAdapter(notes) {
                    selectedNote ->
                showPopupDialog(selectedNote)
            }
            binding.rvContent.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            val total = calculateTotal(notes)
            binding.txtTotal.text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(total)
        }
    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

    private fun getAllNotes() {
        observeNoteChanges()
    }

    private fun observeNoteChanges() {
        noteCollectionRef.addSnapshotListener {value, error ->
            if (error != null) {
                Log.d("MainActivityLOG", "Error listening for budget changes: $error")
            }
            val notes = value?.toObjects(Note::class.java)
            if (notes != null) {
                notesLiveData.postValue(notes)
            }
        }
    }

    private fun showPopupDialog(note: Note? = null) {
        val notePopup = NotePopupDialog(this, note)
        notePopup.show(supportFragmentManager, "NotePopup")
    }

    private fun calculateTotal(notes: List<Note>): Int {
        val plus = ArrayList<Int>()
        val minus = ArrayList<Int>()
        for (note in notes) {
            if (note.category == "Pemasukan") {
                plus.add(note.amount)
            }
            else {
                minus.add(note.amount)
            }
        }
        val sumA = plus.sum()
        val sumB = minus.sum()
        return sumA - sumB
    }

    fun addNote(note: Note) {
        noteCollectionRef.add(note).addOnSuccessListener {
                documentReference ->
            val createdNoteId = documentReference.id
            note.id = createdNoteId
            documentReference.set(note).addOnFailureListener {
                Log.d("MainActivityLOG", "Error updating note id: $it")
            }
        }.addOnFailureListener {
            Log.d("MainActivityLOG", "Error adding note id: $it")
        }
    }
    fun updateNote(note: Note) {
        noteCollectionRef.document(note.id).set(note).addOnFailureListener {
            Log.d("MainActivityLOG", "Error updating note: $it")
        }
    }
    fun deleteNote(note: Note) {
        if (note.id.isEmpty()) {
            Log.d("MainActivityLOG", "Error deleting note: empty id")
        }
        noteCollectionRef.document(note.id).delete().addOnFailureListener {
            Log.d("MainActivityLOG", "Error deleting note: $it")
        }
    }
}