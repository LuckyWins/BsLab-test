package ru.bslab.test.util.rx.scheduler

/**
 * Created by lam on 2/6/17.
 */

object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}