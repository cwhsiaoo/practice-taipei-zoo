package xlet.android.interview.taipeizoo.ui.widget

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import xlet.android.interview.taipeizoo.databinding.ItemSmallBinding

class SmallItemViewHolder(
    private val binding: ItemSmallBinding,
    private val onClick: (id: Long, title: String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: SmallItem) {
        binding.apply {
            root.setOnClickListener { onClick.invoke(data.id, data.name) }
            tvName.text = data.name
            tvInfo.text = data.info
            ivPhoto.contentDescription = data.name
            ivPhoto.load(data.picUri)
        }
    }
}

data class SmallItem(
    val id: Long,
    val name: String,
    val info: String,
    val picUri: Uri,
) {
    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<SmallItem>() {
            override fun areItemsTheSame(oldItem: SmallItem, newItem: SmallItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SmallItem, newItem: SmallItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
