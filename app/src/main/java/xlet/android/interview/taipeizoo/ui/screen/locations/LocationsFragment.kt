package xlet.android.interview.taipeizoo.ui.screen.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import xlet.android.interview.taipeizoo.databinding.FragmentLocationsBinding
import xlet.android.interview.taipeizoo.util.observeEvent

@AndroidEntryPoint
class LocationsFragment : Fragment() {
    private var _binding: FragmentLocationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LocationsViewModel>()
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
            viewModel.locations.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            recyclerView.adapter = adapter
        }
        viewModel.intent.observeEvent(viewLifecycleOwner) {
            when (it) {
                LocationsViewModel.Intent.None -> Unit
                is LocationsViewModel.Intent.ShowSnackbar -> {
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}