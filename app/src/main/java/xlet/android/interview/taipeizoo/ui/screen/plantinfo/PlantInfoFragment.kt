package xlet.android.interview.taipeizoo.ui.screen.plantinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import xlet.android.interview.taipeizoo.databinding.FragmentPlantInfoBinding

@AndroidEntryPoint
class PlantInfoFragment : Fragment() {
    private var _binding: FragmentPlantInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PlantInfoViewModel>()
    private val args by navArgs<PlantInfoFragmentArgs>()

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
        binding.apply {
            ivPhoto.contentDescription = args.title
            viewModel.plantDetail.observe(viewLifecycleOwner) {
                ivPhoto.load(it.pic01.url.toUri())
                ivPhoto.contentDescription = it.pic01.alt
                tvDescription.text = viewModel.getDetailDescription(it)
            }
        }
        binding.tvDescription.text =
            "為落葉小喬木或大型灌木，枝條具有黃灰色的皮孔用於呼吸。葉片長10~15公分，寬6~9公分，呈廣卵形，邊緣呈尖銳鋸齒狀。花朵為單一性別，單一植株只會開特定性別的花。果實俗稱桑椹，長度2~2.5公分，呈長橢圓形，為多顆瘦果組成，成熟呈暗紅色或深紫色。"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}