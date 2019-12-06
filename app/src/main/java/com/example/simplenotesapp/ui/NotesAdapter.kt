package com.example.simplenotesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenotesapp.databinding.NoteCardBinding
import com.example.simplenotesapp.domain.NoteEntity
import com.google.android.material.textfield.TextInputEditText

class NotesAdapter : ListAdapter<NoteEntity, NotesAdapter.NotesViewHolder>(NoteDiff()) {

    class NotesViewHolder private constructor(binding: NoteCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val noteID: TextView = binding.noteId
        val noteText: TextView = binding.noteText
        val noteEditText: TextInputEditText = binding.noteEditText
        val noteEditButton: ImageButton = binding.noteEdit
        val noteConfirmEditButton: ImageButton = binding.noteConfirm
        val deleteButton: ImageButton = binding.noteDelete

        fun bind(note: NoteEntity) {
            noteID.text = note.id.toString()
            noteText.text = note.note
        }

        companion object {
            fun generate(parent: ViewGroup): NotesViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NoteCardBinding.inflate(inflater, parent, false)
                return NotesViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.generate(parent)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NoteDiff : DiffUtil.ItemCallback<NoteEntity>() {
    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.note == newItem.note
    }
}