package ru.bslab.test.features.flow.main

import activitystarter.MakeActivityStarter
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import ru.bslab.test.R
import ru.bslab.test.data.models.BsLabCard
import ru.bslab.test.features.base.BaseActivity
import ru.bslab.test.features.base.DialogType
import ru.bslab.test.features.base.MainActivityRouter
import javax.inject.Inject

@MakeActivityStarter
class MainActivity : BaseActivity(),
    MainMvpView, MainActivityRouter {

    override fun layoutId() = R.layout.activity_main

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        presenter.attachView(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun configureViews() {
        navController = findNavController(R.id.fragmentContainer)

        setupActionBarWithNavController(navController)

    }

    override fun openHome() {
        presenter.navigateToHome()
    }

    override fun openCard(card: BsLabCard) {
        val bundle = bundleOf("card" to card)
        navController.navigate(R.id.nav_card, bundle)
    }

    override fun backToHome() {
        navController.navigate(R.id.nav_home)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun navigateToHome() {
        presenter.navigateToHome()
    }

    override fun navigateToCard(card: BsLabCard) {
        presenter.navigateToCard(card)
    }

    override fun presentDialog(message: String, type: DialogType) {

        val builder = MaterialAlertDialogBuilder(this@MainActivity)
        builder
            .setIcon(when(type) {
                DialogType.Error -> R.drawable.ic_error
                DialogType.Success -> R.drawable.icon_good
            })
            .setTitle(when(type) {
                DialogType.Error -> "Ошибка"
                DialogType.Success -> "Успешно"
            })
            .setMessage(message)
            .setPositiveButton("ОК", null)
            .show()
    }

}