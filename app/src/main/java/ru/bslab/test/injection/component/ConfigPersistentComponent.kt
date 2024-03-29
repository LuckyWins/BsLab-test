package ru.bslab.test.injection.component

import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Component
import ru.bslab.test.injection.ConfigPersistent
import ru.bslab.test.injection.module.ActivityModule
import ru.bslab.test.injection.module.FragmentModule

/**
 * A dagger component that will live during the lifecycle of an Activity or Fragment but it won't
 * be destroy during configuration changes. Check [BaseActivity] and [BaseFragment] to
 * see how this components survives configuration changes.
 * Use the [ConfigPersistent] scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = [AppComponent::class])
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

    fun glide(): RequestManager

    fun glidePrefs(): RequestOptions

}