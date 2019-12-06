package com.example.simplenotesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenotesapp.databinding.NoteCardBinding
import com.example.simplenotesapp.domain.NoteEntity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class NotesAdapter : ListAdapter<NoteEntity, NotesAdapter.NotesViewHolder>(NoteDiff()) {

    val deleteRequest = MutableLiveData<Long>()
    val updateRequest = MutableLiveData<NoteEntity>()

    class NotesViewHolder private constructor(
        private val binding: NoteCardBinding,
        private val deleteRequest: MutableLiveData<Long>,
        private val updateRequest: MutableLiveData<NoteEntity>
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val noteID: TextView = binding.noteId
        private val noteText: TextView = binding.noteText
        private val noteEditTextWrap: TextInputLayout = binding.noteEditTextWrap
        private val noteEditText: TextInputEditText = binding.noteEditText
        private val noteEditButton: ImageButton = binding.noteEdit
        private val noteConfirmEditButton: ImageButton = binding.noteConfirm
        private val deleteButton: ImageButton = binding.noteDelete

        fun bind(note: NoteEntity) {
            noteID.text = note.id.toString()
            noteText.text = note.note
            binding.executePendingBindings()
            initListeners()
        }

        private fun initListeners() {
            deleteButton.setOnClickListener {
                deleteRequest.value = noteID.text.toString().toLong()
            }
            noteEditButton.setOnClickListener {
                noteText.visibility = View.GONE
                noteEditTextWrap.visibility = View.VISIBLE
                noteEditButton.visibility = View.GONE
                noteConfirmEditButton.visibility = View.VISIBLE
                noteEditText.setText(noteText.text, TextView.BufferType.EDITABLE)
            }
            noteConfirmEditButton.setOnClickListener {
                updateRequest.value = NoteEntity(
                    noteID.text.toString().toLong(),
                    noteEditText.text.toString()
                )
                noteText.visibility = View.VISIBLE
                noteEditTextWrap.visibility = View.GONE
                noteEditButton.visibility = View.VISIBLE
                noteConfirmEditButton.visibility = View.GONE
            }
        }

        companion object {
            fun generate(
                parent: ViewGroup,
                deleteRequest: MutableLiveData<Long>,
                updateRequest: MutableLiveData<NoteEntity>
            ): NotesViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NoteCardBinding.inflate(inflater, parent, false)
                return NotesViewHolder(binding, deleteRequest, updateRequest)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.generate(parent, deleteRequest, updateRequest)
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