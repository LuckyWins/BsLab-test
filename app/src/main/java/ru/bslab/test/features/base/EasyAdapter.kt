package ru.bslab.test.features.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class EasyAdapter(private vararg val binders: Binder<*, Holder<*>>) : RecyclerView.Adapter<EasyAdapter.EasyAdapterViewHolder<*, Holder<*>>>() {

    var data = mutableListOf<Any>()

    override fun onBindViewHolder(holder: EasyAdapterViewHolder<*, Holder<*>>, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EasyAdapterViewHolder<*, Holder<*>> = EasyAdapterViewHolder(parent, binders[viewType] as Binder<Any, Holder<Any>>)

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        val obj = data[position]

        for (index in 0 until binders.size) {
            if (!binders[index].isMyType(obj)) continue
            return index
        }

        throw BinderException(position, obj::class.java)
    }

    open inner class EasyAdapterViewHolder<T, out H : Holder<T>>(parent: ViewGroup, binder: Binder<T, H>) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(binder.getLayoutResId(), parent, false)) {

        private val holder: H = binder.createHolder()

        init {
            holder.initViews(itemView)
        }

        fun bind(position: Int) {
            val item = getItem(position)
            holder.bind(position, item)
        }

        open fun getItem(position: Int): T {
            return data[position] as T
        }
    }
}

/**
 * Binder is used for providing layout and creating holder for loaded view
 */
interface Binder<in T, out H : Holder<T>> {
    fun getLayoutResId(): Int
    fun createHolder(): H
    fun isMyType(obj: Any): Boolean
}

/**
 * Holder is used for finding, storing view and binding its specific type of item to this views
 */
interface Holder<in T> {
    fun initViews(itemView: View)
    fun bind(position: Int, item: T)
}

/**
 * Holder in which initialization of views is wanted
 */
abstract class EasyBinderHolder<T>(private val layoutResId: Int, private val clazz: Class<T>) : Holder<T>, Binder<T, EasyBinderHolder<T>>, Cloneable {

    lateinit var itemView: View

    override fun initViews(itemView: View) {
        this.itemView = itemView
        init()
    }

    override fun getLayoutResId(): Int {
        return layoutResId
    }

    override fun isMyType(obj: Any): Boolean {
        return obj.javaClass.isClassInstance(clazz)
    }

    abstract fun init()

    abstract override fun bind(position: Int, item: T)

    override fun createHolder(): EasyBinderHolder<T> {
        return clone() as EasyBinderHolder<T>
    }

    private fun Class<*>.isClassInstance(clazz: Class<*>): Boolean {
        return when {
            this == clazz -> true
            this == Object::class.java -> false
            else -> superclass!!.isClassInstance(clazz)
        }
    }

}

/**
 * Empty holder in which initialization of views is not wanted
 */
open class EmptyBinderHolder<T>(layoutResId: Int, clazz: Class<T>) : EasyBinderHolder<T>(layoutResId, clazz) {

    override fun init() {}

    override fun bind(position: Int, item: T) {}

}

/**
 * Exception that is threw when no Binder is found for specific type of item on currently binded position
 */
class BinderException(position: Int, clazz: Class<*>) : RuntimeException("No binder found for position: $position, type: $clazz")