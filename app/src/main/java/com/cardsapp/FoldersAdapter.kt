package com.cardsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cardsapp.databinding.FolderCardBinding
import kotlinx.coroutines.flow.*

class FoldersAdapter(val context: Context) :
    ListAdapter<FolderInfoRvDto, FoldersAdapter.FolderInfoHolder>(DIFF_ITEM_CALLBACK) {

    var onItemClickListener = ItemClickListener<FolderInfoRvDto> { }

    var selectionModeListener: OnSelectionModeChangedListener? = null

    var selectionModeEnabled = false
        set(value) {
            field = value
            if (!value) clearSelection()
            selectionModeListener?.onSelectionModeChanged(value)
        }

    private val selectedItems = MutableStateFlow<List<FolderInfoRvDto>>(emptyList())

    val selectionFlow = selectedItems.filter { selectionModeEnabled }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderInfoHolder {
        val inflater = LayoutInflater.from(context)
        val binding = FolderCardBinding.inflate(inflater, parent, false)
        return FolderInfoHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderInfoHolder, position: Int) {
        val folderInfo = currentList[position]
        holder.bind(folderInfo)
    }

    override fun submitList(list: List<FolderInfoRvDto>?) {
        super.submitList(list)
        if (selectionModeEnabled) {
            selectedItems.value = list?.filter { it.selected } ?: emptyList()
        }
    }

    fun finishSelection(): List<FolderInfo> {
        val result = selectedItems.value
        selectionModeEnabled = false
        return result.map { FolderInfo(name = it.name, size = it.size) }
    }

    private fun clearSelection() {
        submitList(currentList.map { it.copy(selected = false) })
    }


    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<FolderInfoRvDto>() {
            override fun areItemsTheSame(oldItem: FolderInfoRvDto, newItem: FolderInfoRvDto) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: FolderInfoRvDto, newItem: FolderInfoRvDto) =
                oldItem == newItem
        }
    }

    inner class FolderInfoHolder(private val binding: FolderCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentFolderInfo: FolderInfoRvDto? = null

        init {
            binding.root.setOnClickListener {
                if (selectionModeEnabled) {
                    toggleItem()
                } else {
                    onItemClickListener.onItemClick(currentList[absoluteAdapterPosition])
                }
            }
            binding.root.setOnLongClickListener {
                if (selectionModeEnabled) {
                    selectionModeEnabled = false
                } else {
                    selectionModeEnabled = true
                    toggleItem()
                }

                return@setOnLongClickListener true
            }
        }

        fun bind(folderInfo: FolderInfoRvDto) {
            currentFolderInfo = folderInfo
            with(binding) {
                nameTextView.text = folderInfo.name
                sizeTextView.text = context.getString(R.string.modules_count, folderInfo.size)
                if (folderInfo.selected) {
                    checkMark.visibility = View.VISIBLE
                } else {
                    checkMark.visibility = View.INVISIBLE
                }
            }
        }

        private fun toggleItem() {
            currentFolderInfo?.let { current ->
                submitList(
                    currentList.replaced(current, current.copy(selected = !current.selected))
                )
            }
        }
    }


}
