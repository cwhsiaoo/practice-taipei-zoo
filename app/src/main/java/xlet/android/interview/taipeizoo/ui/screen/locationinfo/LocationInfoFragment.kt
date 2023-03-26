package xlet.android.interview.taipeizoo.ui.screen.locationinfo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import xlet.android.interview.taipeizoo.databinding.FragmentLocationInfoBinding


@AndroidEntryPoint
class LocationInfoFragment : Fragment() {
    private var _binding: FragmentLocationInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LocationInfoViewModel>()
    private val locationDetailAdapter by lazy {
        LocationDetailAdapter(onClickLink = {
            val browserIntent = Intent(Intent.ACTION_VIEW, it)
            startActivity(browserIntent)
        })
    }
    private val plantAdapter by lazy {
        PlantAdapter(onClick = { id, title ->
            findNavController().navigate(
                LocationInfoFragmentDirections.actionLocationInfoFragmentToPlantInfoFragment(
                    title = title,
                    plantId = id,
                )
            )
        })
    }
    private val concatAdapter by lazy {
        ConcatAdapter().apply {
            addAdapter(locationDetailAdapter)
            addAdapter(plantAdapter)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.plants.observe(viewLifecycleOwner) {
            plantAdapter.submitList(it)
        }
        viewModel.locationDetail.observe(viewLifecycleOwner) {
            locationDetailAdapter.submitList(listOf(it))
        }
        binding.recyclerView.adapter = concatAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}