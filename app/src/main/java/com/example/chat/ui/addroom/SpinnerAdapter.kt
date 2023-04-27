package com.example.chat.ui.addroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.chat.R
import com.example.chat.databinding.ActivityAddRoomBinding
import com.example.chat.databinding.ItemCategoryBinding
import com.example.chat.ui.model.Category
import java.text.FieldPosition

class SpinnerAdapter(var list: List<Category>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var viewHolder: ViewHolder
        var currentView = view
        if (currentView == null) {
            val viewBinding: ItemCategoryBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context), R.layout.item_category, parent, false
            )
            viewHolder = ViewHolder(viewBinding)
            currentView = viewHolder.viewbinding.root
            currentView.tag = viewHolder
        } else {viewHolder = currentView?.tag as ViewHolder}

       var item=list[position]
        viewHolder.viewbinding.item =item
        return currentView
    }

    class ViewHolder(var viewbinding: ItemCategoryBinding)
}