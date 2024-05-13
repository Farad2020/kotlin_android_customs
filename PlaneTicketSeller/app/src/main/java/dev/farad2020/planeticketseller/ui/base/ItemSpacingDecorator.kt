package dev.farad2020.planeticketseller.ui.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemSpacingDecoration(private val spacing: Int,
                            private val lasItemSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = spacing

        val itemCount = parent.adapter!!.itemCount

        // Handle edge cases (first and last item) if needed
        val position = parent.getChildAdapterPosition(view)
        if (position == itemCount - 1) {
            outRect.right = lasItemSpacing
        }
    }
}