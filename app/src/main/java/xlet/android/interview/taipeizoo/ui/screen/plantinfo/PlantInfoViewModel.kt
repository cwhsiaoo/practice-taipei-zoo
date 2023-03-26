package xlet.android.interview.taipeizoo.ui.screen.plantinfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import xlet.android.interview.taipeizoo.datasource.database.table.Plant
import xlet.android.interview.taipeizoo.repository.ZooRepository
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlantInfoViewModel @Inject constructor(
    private val zooRepository: ZooRepository,
    private val state: SavedStateHandle,
) : ViewModel() {
    val plantDetail by lazy {
        state.getStateFlow(KEY_PLANT_ID, -1L)
            .flatMapLatest { id ->
                if (id == -1L) {
                    emptyFlow()
                } else {
                    zooRepository.getPlant(id)
                }
            }.asLiveData()
    }

    fun getDetailDescription(data: Plant): String = data.run {
        """
${name.zh}
${name.latin}

別名
$alsoKnown

簡介
$brief

辨認方式
$feature

功能性
$functionApplication

最後更新
$update
        """
    }

    companion object {
        const val KEY_PLANT_ID = "plantId"
    }
}