package ru.bslab.test.features.flow.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.list_item_home_provider.view.*
import ru.bslab.test.R
import ru.bslab.test.data.models.BsLabCard
import ru.bslab.test.data.models.BsLabProvider

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder>() {

    private val clickObserver: PublishSubject<BsLabCard> = PublishSubject.create()
    private var items: List<BsLabProvider>? = null

    fun setData(items: List<BsLabProvider>) {
        this.items = items

        notifyDataSetChanged()
    }

    fun subscribeToClick() = clickObserver

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_home_provider, parent, false)
        return HomeAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = items?.count() ?: 0

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        items?.let {
            val provider = it[position]
            holder.bind(provider)
        }
    }

    class HomeAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(value: BsLabProvider) {
            itemView.title.text = value.title

        }
    }
}