package ru.bslab.test.features.flow.main

import ru.bslab.test.features.base.MvpView

interface MainMvpView : MvpView {

    fun configureViews()

    fun openHome()

    fun openCard()

    fun popBackStack()

    fun backToHome()

}