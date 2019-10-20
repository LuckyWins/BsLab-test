package ru.bslab.test.features.flow.main

import ru.bslab.test.data.DataManager
import ru.bslab.test.data.local.PreferencesHelper
import ru.bslab.test.features.base.BasePresenter
import ru.bslab.test.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val preferencesHelper: PreferencesHelper,
            private val dataManager: DataManager
): BasePresenter<MainMvpView>() {

    override fun attachView(mvpView: MainMvpView) {
        super.attachView(mvpView)

        mvpView.configureViews()
        //mvpView.openHome()
    }

    fun navigateToBack() {
        mvpView?.popBackStack()
    }

    fun navigateToHome() {
        mvpView?.backToHome()
    }

}