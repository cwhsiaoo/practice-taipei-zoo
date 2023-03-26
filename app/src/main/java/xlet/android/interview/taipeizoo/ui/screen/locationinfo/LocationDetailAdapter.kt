package xlet.android.interview.taipeizoo.ui.screen.locationinfo

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import xlet.android.interview.taipeizoo.databinding.ItemLocationDetailBinding
import xlet.android.interview.taipeizoo.ui.widget.LocationDetail
import xlet.android.interview.taipeizoo.ui.widget.LocationDetail.Companion.DIFF_ITEM_CALLBACK
import xlet.android.interview.taipeizoo.ui.widget.LocationDetailViewHolder

class LocationDetailAdapter(
    private val onClickLink: (link: Uri) -> Unit = {},
) :
    ListAdapter<LocationDetail, LocationDetailViewHolder>(DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationDetailViewHolder {
        return LocationDetailViewHolder(
            binding = ItemLocationDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickLink = onClickLink,
        )
    }

    override fun onBindViewHolder(holder: LocationDetailViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}
