package xlet.android.interview.taipeizoo.ui.screen.plantinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import xlet.android.interview.taipeizoo.databinding.FragmentPlantInfoBinding

class PlantInfoFragment : Fragment() {
    private var _binding: FragmentPlantInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlantInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivPhoto.contentDescription = "小葉桑"
        binding.ivPhoto.load("http://www.zoo.gov.tw/iTAP/04_Plant/Moraceae/australis/australis_2.jpg")
        binding.tvDescription.text =
            "為落葉小喬木或大型灌木，枝條具有黃灰色的皮孔用於呼吸。葉片長10~15公分，寬6~9公分，呈廣卵形，邊緣呈尖銳鋸齒狀。花朵為單一性別，單一植株只會開特定性別的花。果實俗稱桑椹，長度2~2.5公分，呈長橢圓形，為多顆瘦果組成，成熟呈暗紅色或深紫色。"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}