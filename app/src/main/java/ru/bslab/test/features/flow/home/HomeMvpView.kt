package ru.bslab.test.features.flow.home

import io.reactivex.Observable
import ru.bslab.test.data.models.BsLabCard
import ru.bslab.test.data.models.BsLabProvider
import ru.bslab.test.features.base.MvpView

interface HomeMvpView : MvpView {

    fun onCardClick(): Observable<BsLabCard>

    fun configureRecyclerView()

    fun showData(providers: List<BsLabProvider>)

    fun openCard(card: BsLabCard)

}