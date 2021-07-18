package com.usama.roomkotlin.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.usama.roomkotlin.R
import com.usama.roomkotlin.data.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]

        val textViewId = holder.textView
        textViewId.text = currentItem.id.toString()

        val textViewFirstName = holder.textView2
        textViewFirstName.text = currentItem.firstName

        val textViewLastName = holder.textView3
        textViewLastName.text = currentItem.lastName

        val textViewAge = holder.textView4
        textViewAge.text = currentItem.age.toString()

        holder.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById(R.id.textView) as TextView
        val textView2 = itemView.findViewById(R.id.textView2) as TextView
        val textView3 = itemView.findViewById(R.id.textView3) as TextView
        val textView4 = itemView.findViewById(R.id.textView4) as TextView
        val rowLayout = itemView.findViewById(R.id.rowLayout) as ConstraintLayout
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}