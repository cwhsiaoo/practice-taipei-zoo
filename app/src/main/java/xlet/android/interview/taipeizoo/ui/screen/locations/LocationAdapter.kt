package xlet.android.interview.taipeizoo.ui.screen.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import xlet.android.interview.taipeizoo.databinding.ItemSmallBinding
import xlet.android.interview.taipeizoo.ui.widget.SmallItem
import xlet.android.interview.taipeizoo.ui.widget.SmallItemViewHolder

class LocationAdapter(
    private val onClick: (id: Long, title: String) -> Unit,
) : ListAdapter<SmallItem, SmallItemViewHolder>(SmallItem.DIFF_ITEM_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallItemViewHolder {
        return SmallItemViewHolder(
            binding = ItemSmallBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick,
        )
    }

    override fun onBindViewHolder(holder: SmallItemViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}
