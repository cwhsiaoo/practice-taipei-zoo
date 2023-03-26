package xlet.android.interview.taipeizoo.ui.screen.locationinfo

import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import xlet.android.interview.taipeizoo.repository.ZooRepository
import xlet.android.interview.taipeizoo.ui.widget.LocationDetail
import xlet.android.interview.taipeizoo.ui.widget.SmallItem
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LocationInfoViewModel @Inject constructor(
    private val zooRepository: ZooRepository,
    private val state: SavedStateHandle,
) : ViewModel() {
    val locationDetail by lazy {
        state.getStateFlow(KEY_LOCATION_ID, -1L)
            .flatMapLatest { id ->
                if (id == -1L) {
                    emptyFlow()
                } else {
                    zooRepository.getLocation(id).map {
                        LocationDetail(
                            id = it.id,
                            name = it.name,
                            info = it.info,
                            picUri = it.picUrl.toUri(),
                            link = it.url.toUri(),
                        )
                    }
                }
            }.asLiveData()
    }
    val plants by lazy {
        state.getStateFlow(KEY_TITLE, "")
            .flatMapLatest { title ->
                if (title.isEmpty()) {
                    emptyFlow()
                } else {
                    zooRepository.getPlantsByLocationName(title).map { list ->
                        list.map {
                            SmallItem(
                                id = it.id,
                                name = it.name.zh,
                                info = it.alsoKnown,
                                picUri = it.pic01.url.toUri(),
                            )
                        }
                    }
                }
            }.asLiveData()
    }

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_LOCATION_ID = "locationId"
    }
}