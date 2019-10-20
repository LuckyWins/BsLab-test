package ru.bslab.test.injection.component

import dagger.Subcomponent
import ru.bslab.test.features.flow.card.CardFragment
import ru.bslab.test.features.flow.home.HomeFragment
import ru.bslab.test.injection.PerFragment
import ru.bslab.test.injection.module.FragmentModule

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(homeFragment: HomeFragment)

    fun inject(cardFragment: CardFragment)

}