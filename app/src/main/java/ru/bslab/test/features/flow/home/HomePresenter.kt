package ru.bslab.test.features.flow.home

import io.reactivex.rxkotlin.addTo
import ru.bslab.test.data.DataManager
import ru.bslab.test.data.local.PreferencesHelper
import ru.bslab.test.data.models.BsLabProvider
import ru.bslab.test.features.base.BasePresenter
import ru.bslab.test.injection.ConfigPersistent
import timber.log.Timber
import javax.inject.Inject

@ConfigPersistent
class HomePresenter @Inject
constructor(val preferencesHelper: PreferencesHelper,
            val dataManager: DataManager
) : BasePresenter<HomeMvpView>() {

    var providers: List<BsLabProvider>? = null

    override fun attachView(mvpView: HomeMvpView) {
        super.attachView(mvpView)

        mvpView.configureRecyclerView()

        restoreViewState()

        loadProviders()
    }

    private fun loadProviders() {
        dataManager.testRequest()
            .doOnSubscribe { mvpView?.showProgressView() }
            .doFinally { mvpView?.hideProgressView() }
            .subscribe({
                providers = it.providers
                mvpView?.showData(it.providers ?: listOf())
            }, { throwable ->
                Timber.e(throwable)
                mvpView?.presentDialog("Сетевая ошибка")
            })
            .addTo(compositeDisposable)
    }

    private fun restoreViewState() {
        mvpView?.showData(providers ?: listOf())
    }

}