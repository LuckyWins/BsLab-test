package ru.bslab.test.features.flow.home

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.fragmentargs.FragmentArgs
import kotlinx.android.synthetic.main.fragment_home.*
import ru.bslab.test.R
import ru.bslab.test.features.base.BaseFragment
import ru.bslab.test.features.base.MainActivityRouter
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMvpView {

    override fun layoutId() = R.layout.fragment_home

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
        fragmentComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        btnOpenCard.setOnClickListener { openCard() }
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun openCard() {
        (activity as? MainActivityRouter)?.navigateToCard()
    }

}