package ru.bslab.test.features.base

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
interface MvpView {

    /**
     * Shows error message
     * [message] - text message to show
     * [type] -  type of dialog icon
     * [timeout] - time before dismiss (in seconds)
     */
    fun presentDialog(message: String, type: DialogType = DialogType.Error)

    fun showProgressView()

    fun hideProgressView()

}

enum class DialogType {
    Error, Success
}