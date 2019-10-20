package ru.bslab.test.features.flow.home

import ru.bslab.test.data.DataManager
import ru.bslab.test.data.local.PreferencesHelper
import ru.bslab.test.features.base.BasePresenter
import ru.bslab.test.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class HomePresenter @Inject
constructor(val preferencesHelper: PreferencesHelper,
            val dataManager: DataManager
) : BasePresenter<HomeMvpView>() {

    override fun attachView(mvpView: HomeMvpView) {
        super.attachView(mvpView)


    }

}