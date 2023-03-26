package xlet.android.interview.taipeizoo.ui.screen.locations

import androidx.core.net.toUri
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import xlet.android.interview.taipeizoo.repository.ZooRepository
import xlet.android.interview.taipeizoo.ui.widget.SmallItem
import xlet.android.interview.taipeizoo.util.Event
import xlet.android.interview.taipeizoo.util.toEvent
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: ZooRepository,
) : ViewModel() {
    private val _intent by lazy { MutableLiveData(Intent.None.toEvent<Intent>()) }
    val intent: LiveData<Event<Intent>>
        get() = _intent
    val locations by lazy {
        repository.getAllLocations().map { list ->
            list.map {
                SmallItem(
                    id = it.id,
                    name = it.name,
                    info = it.info,
                    picUri = it.picUrl.toUri(),
                )
            }
        }.asLiveData()
    }


    init {
        viewModelScope.syncData()
    }

    private fun CoroutineScope.syncData() {
        launch {
            kotlin.runCatching {
                repository.syncLocations()
            }.onFailure { updateSnackbar(it.message.orEmpty()) }
        }
        launch {
            kotlin.runCatching {
                repository.syncPlants()
            }.onFailure { updateSnackbar(it.message.orEmpty()) }
        }
    }

    private fun updateSnackbar(message: String) {
        _intent.value = Event(Intent.ShowSnackbar(message))
    }

    sealed class Intent {
        object None : Intent()
        data class ShowSnackbar(val message: String) : Intent()
    }
}