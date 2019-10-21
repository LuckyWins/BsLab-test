package ru.bslab.test.features.flow.card

import ru.bslab.test.data.DataManager
import ru.bslab.test.data.local.PreferencesHelper
import ru.bslab.test.features.base.BasePresenter
import ru.bslab.test.injection.ConfigPersistent
import javax.inject.Inject

@ConfigPersistent
class CardPresenter @Inject
constructor(val preferencesHelper: PreferencesHelper,
            val dataManager: DataManager
) : BasePresenter<CardMvpView>() {

    override fun attachView(mvpView: CardMvpView) {
        super.attachView(mvpView)

        mvpView.configureViews(mvpView.args.card)
    }

}