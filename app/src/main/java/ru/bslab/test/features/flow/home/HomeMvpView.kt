package ru.bslab.test.features.flow.home

import ru.bslab.test.data.models.BsLabProvider
import ru.bslab.test.features.base.MvpView

interface HomeMvpView : MvpView {

    fun configureRecyclerView()

    fun showData(providers: List<BsLabProvider>)

    fun openCard()

}