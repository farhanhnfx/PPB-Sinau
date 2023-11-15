package com.example.ppb

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.ppb.databinding.NoteDialogBinding

class NotePopupDialog(private val activity: MainActivity, private val note: Note? = null): DialogFragment() {
    val binding by lazy {
        NoteDialogBinding.inflate(layoutInflater)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        binding.spinnerCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, resources.getStringArray(R.array.category))
        builder.apply {
            if (note == null) {
                setPositiveButton("Tambah") {
                        dialog, which ->
                    val txtTitle = binding.editTitle.text.toString()
                    val txtDesc = binding.editDesc.text.toString()
                    val txtAmount = binding.editAmount.text.toString().toInt()
                    val spnCategory = binding.spinnerCategory.selectedItem.toString()
                    addNote(Note(title = txtTitle, description = txtDesc, category = spnCategory, amount = txtAmount))
                    dismiss()
                }
            }
            else {
                with(binding) {
                    editTitle.setText(note.title)
                    editDesc.setText(note.description)
                    editAmount.setText(note.amount.toString())
                    var pos = 0
                    if (note.category == "Pengeluaran") {
                        pos = 1
                    }
                    spinnerCategory.setSelection(pos)
                }

                setPositiveButton("Edit") {
                        dialog, which ->
                    val txtTitle = binding.editTitle.text.toString()
                    val txtDesc = binding.editDesc.text.toString()
                    val txtAmount = binding.editAmount.text.toString().toInt()
                    val spnCategory = binding.spinnerCategory.selectedItem.toString()
                    updateNote(Note(id = note.id,title = txtTitle, description = txtDesc, category = spnCategory, amount = txtAmount))
                    dismiss()
                }
                setNeutralButton("Hapus Catatan") {
                        dialog, which ->
                    deleteNote(note)
                    dismiss()
                }
            }
        }
        builder.setView(binding.root).setTitle("Catatan Keuangan")
        return builder.create()
    }

    private fun addNote(note: Note) {
        activity.executorService.execute {
            activity.mNotesDao.insert(note)
        }
    }
    private fun updateNote(note: Note) {
        activity.executorService.execute {
            activity.mNotesDao.update(note)
        }
    }
    private fun deleteNote(note: Note) {
        activity.executorService.execute {
            activity.mNotesDao.delete(note)
        }
    }
}