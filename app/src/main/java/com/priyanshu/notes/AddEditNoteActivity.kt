package com.priyanshu.notes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleEdt : EditText
    lateinit var noteDescriptionEdt : EditText
    lateinit var addUpdateBtn : Button
    lateinit var viewModal : NoteViewModal
    var noteId = -1
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEdt = findViewById(R.id.EditNoteTitle)
        noteDescriptionEdt = findViewById(R.id.EditNoteDescription)
        addUpdateBtn = findViewById(R.id.BtnaddUpdate)

        viewModal = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModal::class.java)

        val noteType = intent.getStringExtra("noteType")

        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteId = intent.getIntExtra("noteId",-1)
            addUpdateBtn.text = "Update Note"
            noteTitleEdt.setText(noteTitle)
            noteDescriptionEdt.setText(noteDesc)

        }
        else{
            addUpdateBtn.text = "Save Note"
        }

        addUpdateBtn.setOnClickListener {
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteDescriptionEdt.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDescription.isEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH :mm")
                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(noteTitle,noteDescription,currentDate)
                    updateNote.id =noteId
                    viewModal.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                }
            }else{
                if(noteTitle.isNotEmpty()&&noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH :mm")
                    val currentDate:String = sdf.format(Date())
                    viewModal.addNote(Note(noteTitle,noteDescription,currentDate))
                    Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()

                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }

    }
}