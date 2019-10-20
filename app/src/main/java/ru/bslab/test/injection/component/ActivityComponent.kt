package ru.bslab.test.injection.component

import dagger.Subcomponent
import ru.bslab.test.features.base.BaseActivity
import ru.bslab.test.features.flow.main.MainActivity
import ru.bslab.test.injection.PerActivity
import ru.bslab.test.injection.module.ActivityModule

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)

}