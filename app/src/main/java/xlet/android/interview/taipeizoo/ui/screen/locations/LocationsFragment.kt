package xlet.android.interview.taipeizoo.ui.screen.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import xlet.android.interview.taipeizoo.databinding.FragmentLocationsBinding
import xlet.android.interview.taipeizoo.ui.screen.locationinfo.LocationInfoFragmentDirections
import xlet.android.interview.taipeizoo.ui.widget.SmallItem

@AndroidEntryPoint
class LocationsFragment : Fragment() {
    private var _binding: FragmentLocationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter by lazy {
        LocationAdapter(onClick = { id, title ->
            findNavController().navigate(
                LocationsFragmentDirections.actionLocationsFragmentToLocationInfoFragment(
                    title = title,
                    locationId = id,
                )
            )
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            adapter.submitList(
                listOf(
                    SmallItem(
                        id = -1L,
                        name = "熱帶雨林區",
                        info = "熱帶雨林是地球的珍寶，除了孕育多樣性的動、植物之外，同時也提供全球生物極大比例的氧氣；此外，熱帶雨林還藉著水蒸氣的循環，維持地球穩定的降雨。因此，熱帶雨林的消失，將威脅地球所有生物的生存與平衡。目前，以婆羅洲為主的亞洲熱帶雨林，是地球上僅次於亞馬遜河流域的熱帶雨林集中地。n熱帶雨林區的展示空間，以南美洲亞馬遜河流域環境以及亞洲東南亞熱帶雨林為主軸，展示熱帶雨林生物多樣性特色與氛圍，完整呈現分布在赤道附近，高溫潮溼且植物種類繁多的熱帶雨林意象，進一步強化動物園推動生物多樣性保育研究與教育的功能。n本園熱帶雨林區主要模擬東南亞熱帶雨林的自然生態景觀，依展示動線規劃成河口生態、密林生態及林緣生態三大展示區，區內可見板根、氣生根、支柱根、附生植物、林間霧氣等熱帶雨林特有的生態現象，是國內首座兼具雨林景觀與活體動物的生態展示，也是環境教育的最佳場所。",
                        picUri = "http://www.zoo.gov.tw/iTAP/05_Exhibit/03_TropicalRainforest.jpg".toUri(),
                    )
                )
            )
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}