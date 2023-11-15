package com.example.ppb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppb.databinding.ItemCardBinding
import java.text.NumberFormat
import java.util.Locale

class NoteAdapter(private val listNote: List<Note>,
                  private val onItemLongClickListener: (Note) -> Unit)
    : RecyclerView.Adapter<NoteAdapter.ItemNoteViewHolder>() {
    inner class ItemNoteViewHolder(private val binding: ItemCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                txtTitle.text = note.title
                txtDesc.text = note.description
                txtAmount.text = "Rp" + NumberFormat.getInstance(Locale("id", "ID")).format(note.amount)
                txtNum.text = (adapterPosition+1).toString()
                if (note.category == "Pengeluaran") {
                    itemView.setBackgroundResource(R.drawable.item_red)
                    icAction.setBackgroundResource(R.drawable.baseline_remove_circle_24)
                    txtAmount.setTextColor(itemView.context.resources.getColor(R.color.dark_red))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNoteViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemNoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: ItemNoteViewHolder, position: Int) {
        holder.bind(listNote[position])
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener.invoke(listNote[position])
            true
        }
    }
}