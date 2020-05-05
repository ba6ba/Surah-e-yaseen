package com.example.recitation.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recitation.Verse
import kotlinx.android.synthetic.main.recitation_item_layout.view.*

class RecitationItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var onItemClickListener : (Verse) -> Unit

    fun onBind(verse: Verse) {
        itemView.apply {
            arabicTextView.text = verse.arabic
            urduTextView.text = verse.urdu
            englishTextView.text = verse.english
            ayahNumber.text = verse.number.toString()
            setOnClickListener {
                onItemClickListener(verse)
            }
        }
    }
}