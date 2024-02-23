package com.github.fajaragungpramana.igit.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.github.fajaragungpramana.igit.widget.R
import com.google.android.material.textview.MaterialTextView

class IgitTextPopularity(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var contentTextView: MaterialTextView
    private var titleTextView: MaterialTextView

    private var content: String?
        set(value) {
            contentTextView.text = value.orEmpty()
        }
        get() = contentTextView.text.toString()

    private var title: String?
        set(value) {
            titleTextView.text = value.orEmpty()
        }
        get() = titleTextView.text.toString()

    init {
        View.inflate(context, R.layout.igit_text_popularity, this)

        contentTextView = findViewById(R.id.mtv_content)
        titleTextView = findViewById(R.id.mtv_title)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.IgitTextPopularity)
        try {
            content = ta.getString(R.styleable.IgitTextPopularity_contentPopularity)
            title = ta.getString(R.styleable.IgitTextPopularity_titlePopularity)
        } finally {
            ta.recycle()
        }
    }

}