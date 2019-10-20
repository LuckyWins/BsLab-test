package ru.bslab.test.features.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.collection.LongSparseArray
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import ru.bslab.test.TestApplication
import ru.bslab.test.injection.component.ConfigPersistentComponent
import ru.bslab.test.injection.component.DaggerConfigPersistentComponent
import ru.bslab.test.injection.component.FragmentComponent
import ru.bslab.test.injection.module.FragmentModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * Abstract Fragment that every other Fragment in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent are kept
 * across configuration changes.
 */
abstract class BaseFragment : Fragment(), MvpView {

    private var fragmentComponent: FragmentComponent? = null
    private var fragmentId = 0L

    private var glide: RequestManager? = null
    private var glidePrefs: RequestOptions? = null

    companion object {
        private const val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val componentsArray = LongSparseArray<ConfigPersistentComponent>()
        private val NEXT_ID = AtomicLong(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        fragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent?
        if (componentsArray.get(fragmentId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", fragmentId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                .appComponent(TestApplication[activity as Context].component)
                .build()
            componentsArray.put(fragmentId, configPersistentComponent)
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", fragmentId)
            configPersistentComponent = componentsArray.get(fragmentId)
        }
        fragmentComponent = configPersistentComponent?.fragmentComponent(FragmentModule(this))

        glide = configPersistentComponent?.glide()
        glidePrefs = configPersistentComponent?.glidePrefs()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(layoutId(), container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @LayoutRes
    abstract fun layoutId(): Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_FRAGMENT_ID, fragmentId)
    }

    override fun onDestroy() {
        if (!activity!!.isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", fragmentId)
            componentsArray.remove(fragmentId)
        }
        super.onDestroy()
    }

    override fun showProgressView() {
        (activity as? BaseActivity)?.showProgressView()
    }

    override fun hideProgressView() {
        (activity as? BaseActivity)?.hideProgressView()
    }

    override fun presentDialog(message: String, type: DialogType) {
        (activity as? BaseActivity)?.presentDialog(message, type)
    }

    fun loadImageGlide(url: String, img: ImageView) {
        glide!!.load(url)
            .apply(glidePrefs!!.signature(ObjectKey(url)))
            //.centerCrop()
            .into(img)
    }

    fun fragmentComponent() = fragmentComponent as FragmentComponent
}