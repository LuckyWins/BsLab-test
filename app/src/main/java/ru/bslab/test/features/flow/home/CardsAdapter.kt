package ru.bslab.test.features.flow.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.list_item_home_card.view.*
import ru.bslab.test.R
import ru.bslab.test.data.models.BsLabCard

class CardsAdapter(private var items : List<BsLabCard>?): RecyclerView.Adapter<CardsAdapter.CardsAdapterViewHolder>() {

    private val clickObserver: PublishSubject<BsLabCard> = PublishSubject.create()

    fun setData(items: List<BsLabCard>) {
        this.items = items

        notifyDataSetChanged()
    }

    fun subscribeToClickCard() = clickObserver

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_home_card, parent, false)
        return CardsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = items?.count() ?: 0

    override fun onBindViewHolder(holder: CardsAdapterViewHolder, position: Int) {
        items?.let {
            val card = it[position]
            holder.bind(card)
            holder.itemView.cardItem.setOnClickListener {
                clickObserver.onNext(card)
            }
        }
    }

    class CardsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(value: BsLabCard) {
            value.imageUrl?.let {
                Glide.with(itemView.cardImage.context)
                    .load(it)
                    .centerCrop()
                    .into(itemView.cardImage)
            }
            itemView.cardTitle.text = value.title
            itemView.cardCredits.text = value.credits.toString()

        }
    }
}