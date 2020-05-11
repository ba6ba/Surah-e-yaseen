package com.example.recitation.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.data.verse.Verse
import com.example.data.verse.translation
import com.example.extensions.toHtml
import com.example.recitation.ChapterProvider.Companion.ABUL_ALA_MAUDUDI
import com.example.recitation.ChapterProvider.Companion.SAHIH_INTERNATION_EN
import kotlinx.android.synthetic.main.recitation_item_layout.view.*

class RecitationItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    lateinit var onItemClickListener : (Verse) -> Unit

    fun onBind(verse: Verse) {
        itemView.apply {
            arabicTextView.text = verse.indoPakText?.toHtml()
            urduTextView.text = verse.translation(ABUL_ALA_MAUDUDI)?.toHtml()
            englishTextView.text = verse.translation(SAHIH_INTERNATION_EN)?.toHtml()
            ayahNumber.text = verse.verseNumber?.toString()
            setOnClickListener {
                onItemClickListener(verse)
            }
        }
    }
}