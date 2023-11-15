package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.ppb.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNotesDao: NoteDao
    private lateinit var executorService: ExecutorService
    private var updateId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDB.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        with(binding) {
            btnAdd.setOnClickListener {
                val title = editTitle.text.toString()
                val desc = editDesc.text.toString()
                insertNote(Note(title = title, description = desc))
                setEmptyField()
            }
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val item = adapterView.adapter.getItem(i) as Note
                updateId = item.id
                editTitle.setText(item.title)
                editDesc.setText(item.description)
            }
            btnUpdate.setOnClickListener {
                val title = editTitle.text.toString()
                val desc = editDesc.text.toString()
                updateNote(Note(id = updateId, title = title, description = desc))
                setEmptyField()
            }
            listView.setOnItemLongClickListener { adapterView, view, i, l ->
                val item = adapterView.adapter.getItem(i) as Note
                deleteNote(item)
                true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

    private fun getAllNotes() {
        mNotesDao.allNotes.observe(this) {
            notes ->
            val adapter = ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, notes)
            binding.listView.adapter = adapter
        }
    }

    private fun insertNote(note: Note) {
        executorService.execute {
            mNotesDao.insert(note)
        }
    }
    private fun updateNote(note: Note) {
        executorService.execute {
            mNotesDao.update(note)
        }
    }
    private fun deleteNote(note:Note) {
        executorService.execute {
            mNotesDao.delete(note)
        }
    }
    private fun setEmptyField() {
        with(binding) {
            editTitle.setText("")
            editDesc.setText("")
        }
    }
}