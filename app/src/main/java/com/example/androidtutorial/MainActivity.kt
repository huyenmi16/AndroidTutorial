package com.example.androidtutorial


// MainActivity.kt
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: WordAdapter

    private val wordList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fabAdd = findViewById(R.id.fabAdd)

        // Initialize RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WordAdapter(wordList)
        recyclerView.adapter = adapter

        // Set click listener for FAB to add a new word
        fabAdd.setOnClickListener {
            addWord()
        }

        // Populate initial dataset
        wordList.addAll(generateInitialWords())
        adapter.notifyDataSetChanged()
    }

    private fun generateInitialWords(): List<String> {
        return listOf("Hello", "World", "RecyclerView", "Example", "Android", "Kotlin")
    }

    private fun addWord() {
        val newWord = "New Word ${wordList.size + 1}"
        wordList.add(newWord)
        adapter.notifyItemInserted(wordList.size - 1)
        recyclerView.smoothScrollToPosition(wordList.size - 1)
    }

    // RecyclerView Adapter
    private inner class WordAdapter(private val items: List<String>) :
        RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_word, parent, false)
            return WordViewHolder(view)
        }

        @SuppressLint("ResourceAsColor")
        override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
            val word = items[position]
            holder.textViewWord.text = word
            holder.itemView.setOnClickListener {
                // Handle click on item
                // For now, just change background color to indicate selection
                holder.itemView.setBackgroundColor(
                    if (holder.itemView.isSelected) android.R.color.transparent else android.R.color.darker_gray
                )
                holder.itemView.isSelected = !holder.itemView.isSelected
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }

        // ViewHolder
        inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewWord: TextView = itemView.findViewById(R.id.textViewWord)
        }
    }
}
