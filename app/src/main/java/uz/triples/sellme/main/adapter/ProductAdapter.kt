package uz.triples.sellme.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_product.view.*
import uz.triples.sellme.R
import uz.triples.sellme.data.ProductData

class ProductAdapter : ListAdapter<ProductData, ProductAdapter.ProductViewHolder>(DiffItem) {

    object DiffItem : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.image == newItem.image && oldItem.isfavorite == newItem.isfavorite
        }
    }

    private var listenerfavorite: ((Int) -> Unit)? = null

    fun setOnClickFavoriteListener(fm: (Int) -> Unit) {
        listenerfavorite = fm
    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.favorite.setOnClickListener {
                listenerfavorite?.invoke(adapterPosition)
            }
        }

        fun onBind() {
            val d = getItem(adapterPosition)
            itemView.apply {
                products_img.setImageResource(d.image)

                if (d.isfavorite) {
                    favorite.setImageResource(R.drawable.favorite_empty)
                } else {
                    favorite.setImageResource(R.drawable.favorite_full)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product, parent, false
            )
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.onBind()
}
