package ru.bslab.test.features.flow.card

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.fragmentargs.FragmentArgs
import ru.bslab.test.R
import ru.bslab.test.features.base.BaseFragment
import javax.inject.Inject

class CardFragment : BaseFragment(), CardMvpView {

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

}