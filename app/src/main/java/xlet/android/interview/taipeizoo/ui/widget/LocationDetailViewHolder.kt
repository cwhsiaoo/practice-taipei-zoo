package xlet.android.interview.taipeizoo.ui.widget

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import xlet.android.interview.taipeizoo.databinding.ItemLocationDetailBinding

class LocationDetailViewHolder(
    private val binding: ItemLocationDetailBinding,
    private val onClickLink: (link: Uri) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: LocationDetail) {
        binding.btnOpenWeb.setOnClickListener {
            onClickLink.invoke(data.link)
        }
        binding.tvDescription.text = data.info
    }
}

data class LocationDetail(
    val id: Long,
    val name: String,
    val info: String,
    val link: Uri,
    val picUri: Uri,
) {
    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<LocationDetail>() {
            override fun areItemsTheSame(
                oldItem: LocationDetail,
                newItem: LocationDetail
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LocationDetail,
                newItem: LocationDetail
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
