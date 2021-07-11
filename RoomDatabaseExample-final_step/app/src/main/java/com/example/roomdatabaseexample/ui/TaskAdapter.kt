package com.example.roomdatabaseexample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.data.PriorityLevel
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.data.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*


class TaskAdapter(private val listener: (Long) -> Unit):
    ListAdapter<Task, TaskAdapter.ViewHolder>(
        DiffCallback()
    ){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init{
            itemView.setOnClickListener{
                listener.invoke(getItem(adapterPosition).id)
            }
        }

        fun bind(task: Task){
            with(task){
                when(task.priority){
                    PriorityLevel.High.ordinal ->{
                        task_priority.setBackgroundColor(
                            ContextCompat.getColor(containerView.context,
                                R.color.colorPriorityHigh
                            ))
                    }
                    PriorityLevel.Medium.ordinal ->{
                        task_priority.setBackgroundColor(
                            ContextCompat.getColor(containerView.context,
                                R.color.colorPriorityMedium
                            ))
                    }
                    else ->  task_priority.setBackgroundColor(
                        ContextCompat.getColor(containerView.context,
                            R.color.colorPriorityLow
                        ))
                }
                task_title.text = task.title
                task_detail.text = task.detail
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}