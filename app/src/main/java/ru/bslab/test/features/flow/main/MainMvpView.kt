package ru.bslab.test.features.flow.main

import ru.bslab.test.data.models.BsLabCard
import ru.bslab.test.features.base.MvpView

interface MainMvpView : MvpView {

    fun configureViews()

    fun openHome()

    fun openCard(card: BsLabCard)

    fun popBackStack()

    fun backToHome()

}