package uz.triples.sellme.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import uz.triples.sellme.R
import uz.triples.sellme.data.CategoryData

class CategoryAdapter : ListAdapter<CategoryData, CategoryAdapter.ViewHolder>(DiffItem) {


    object DiffItem : DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.name == newItem.name && oldItem.image == newItem.image
        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind() {
            val d = getItem(adapterPosition)
            itemView.apply {
                //TODO
                categoryimg.setImageResource(d.image)
                categoryname.text = d.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}
