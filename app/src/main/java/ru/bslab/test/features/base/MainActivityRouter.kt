package ru.bslab.test.features.base

import ru.bslab.test.data.models.BsLabCard

interface MainActivityRouter {

    fun navigateToHome()

    fun navigateToCard(card: BsLabCard)

}