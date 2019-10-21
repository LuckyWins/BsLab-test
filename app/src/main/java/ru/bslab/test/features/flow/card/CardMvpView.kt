package ru.bslab.test.features.flow.card

import ru.bslab.test.data.models.BsLabCard
import ru.bslab.test.features.base.MvpView

interface CardMvpView : MvpView {

    val args: CardFragmentArgs

    fun configureViews(card: BsLabCard)

}