package ru.bslab.test.features.flow.main

import ru.bslab.test.features.base.MvpView

interface MainMvpView : MvpView {

    fun configureViews()

    fun openHome()

    fun popBackStack()

    fun backToHome()

    fun changeTitle(title: String?)

}