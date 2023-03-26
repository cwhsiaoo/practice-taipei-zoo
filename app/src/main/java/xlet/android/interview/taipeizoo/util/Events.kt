package xlet.android.interview.taipeizoo.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class Event<out T>(private val content: T) {
    private val isHandled = AtomicBoolean()

    fun getContentIfNotHandled(): T? {
        return if (isHandled.get()) {
            null
        } else {
            isHandled.set(true)
            content
        }
    }

    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(value: Event<T>) {
        value.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}

@MainThread
inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> = EventObserver<T> { t -> onChanged.invoke(t) }.also {
    observe(owner, it)
}

fun <T> T.toEvent() = Event(this)
