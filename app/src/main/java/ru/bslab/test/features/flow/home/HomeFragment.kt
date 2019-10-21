package ru.bslab.test.features.flow.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.fragmentargs.FragmentArgs
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*
import ru.bslab.test.R
import ru.bslab.test.data.models.BsLabCard
import ru.bslab.test.data.models.BsLabProvider
import ru.bslab.test.features.base.BaseFragment
import ru.bslab.test.features.base.MainActivityRouter
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMvpView {

    override fun layoutId() = R.layout.fragment_home

    @Inject
    lateinit var presenter: HomePresenter

    private val homeAdapter = HomeAdapter()

    override fun onCardClick() = homeAdapter.subscribeToClickCards()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
        fragmentComponent().inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachButtons(this)
    }

    override fun configureRecyclerView() {
        rvProviders.layoutManager = LinearLayoutManager(context)
        //rvProviders.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvProviders.adapter = homeAdapter
    }

    override fun showData(providers: List<BsLabProvider>) {
        homeAdapter.setData(providers)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun openCard(card: BsLabCard) {
        (activity as? MainActivityRouter)?.navigateToCard(card)
    }

}