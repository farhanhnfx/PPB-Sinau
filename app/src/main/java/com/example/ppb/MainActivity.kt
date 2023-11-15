package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ppb.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var mNotesDao: NoteDao
    lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDB.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

    private fun getAllNotes() {
        mNotesDao.allNotes.observe(this) {
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
}