package ru.bslab.test.features.flow.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.fragmentargs.FragmentArgs
import kotlinx.android.synthetic.main.fragment_home.*
import ru.bslab.test.R
import ru.bslab.test.data.models.BsLabProvider
import ru.bslab.test.features.base.BaseFragment
import ru.bslab.test.features.base.MainActivityRouter
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeMvpView {

    override fun layoutId() = R.layout.fragment_home

    @Inject
    lateinit var presenter: HomePresenter

    private val homeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
        fragmentComponent().inject(this)
    }

    override fun configureRecyclerView() {
        providersRecyclerView.layoutManager = LinearLayoutManager(context)
        providersRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        providersRecyclerView.adapter = homeAdapter
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

    override fun openCard() {
        (activity as? MainActivityRouter)?.navigateToCard()
    }

}