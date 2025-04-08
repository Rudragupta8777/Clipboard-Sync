package com.example.clipboardsync

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var database: FirebaseDatabase
    private lateinit var clipboardRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        database = FirebaseDatabase.getInstance()
        clipboardRef = database.getReference("text")

        val textFromCloud = findViewById<TextView>(R.id.textFromPC)
        val buttonCopy = findViewById<Button>(R.id.buttonCopy)
        val inputToCloud = findViewById<EditText>(R.id.editTextToPC)
        val buttonSend = findViewById<Button>(R.id.buttonSend)

        // 🔄 Listen to any change in "clipboard/text"
        clipboardRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val text = snapshot.getValue(String::class.java)
                textFromCloud.text = text ?: "Nothing yet"
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        // 📋 Copy current Firebase text to Android clipboard
        buttonCopy.setOnClickListener {
            val text = textFromCloud.text.toString()
            if (text.isNotEmpty()) {
                val clip = ClipData.newPlainText("Clipboard Text", text)
                clipboardManager.setPrimaryClip(clip)
            }
        }

        // 🚀 Send new text to Firebase (for PC or another phone to pick up)
        buttonSend.setOnClickListener {
            val text = inputToCloud.text.toString()
            if (text.isNotEmpty()) {
                clipboardRef.setValue(text)
                inputToCloud.text.clear()
            }
        }
    }
}
