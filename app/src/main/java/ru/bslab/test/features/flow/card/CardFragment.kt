package ru.bslab.test.features.flow.card

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hannesdorfmann.fragmentargs.FragmentArgs
import kotlinx.android.synthetic.main.fragment_card.*
import ru.bslab.test.R
import ru.bslab.test.data.models.BsLabCard
import ru.bslab.test.features.base.BaseFragment
import javax.inject.Inject

class CardFragment : BaseFragment(), CardMvpView {

    override val args: CardFragmentArgs by navArgs()

    override fun layoutId() = R.layout.fragment_card

    @Inject
    lateinit var presenter: CardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
        fragmentComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun configureViews(card: BsLabCard) {
        card.imageUrl?.let {
            Glide.with(cardImage.context)
                .load(it)
                .centerCrop()
                .into(cardImage)
        }
        cardTitle.text = card.title
        cardCredits.text = card.credits.toString()
        cardDescription.text = card.description
    }

}