package com.cardsapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cardsapp.R
import com.cardsapp.databinding.FolderCardBinding

class FoldersAdapter(val context: Context) :
    ListAdapter<SelectableFolderWithSize, FoldersAdapter.FolderInfoHolder>(DIFF_ITEM_CALLBACK) {

    var onItemClickListener: (SelectableFolderWithSize) -> Unit =  { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderInfoHolder {
        val inflater = LayoutInflater.from(context)
        val binding = FolderCardBinding.inflate(inflater, parent, false)
        return FolderInfoHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderInfoHolder, position: Int) {
        val folderInfo = currentList[position]
        holder.bind(folderInfo)
    }

    override fun submitList(list: List<SelectableFolderWithSize>?) {
        super.submitList(list)
    }

    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<SelectableFolderWithSize>() {
            override fun areItemsTheSame(
                oldItem: SelectableFolderWithSize,
                newItem: SelectableFolderWithSize
            ) =
                oldItem.folderWithSize.folder == newItem.folderWithSize.folder

            override fun areContentsTheSame(
                oldItem: SelectableFolderWithSize,
                newItem: SelectableFolderWithSize
            ) =
                oldItem == newItem
        }
    }

    inner class FolderInfoHolder(private val binding: FolderCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentFolder: SelectableFolderWithSize

        init {
            binding.root.setOnClickListener {
                onItemClickListener(currentFolder)
            }
            binding.root.setOnLongClickListener {

                return@setOnLongClickListener true
            }
        }

        fun bind(selectableFolderWithSize: SelectableFolderWithSize) {
            currentFolder = selectableFolderWithSize
            with(binding) {
                nameTextView.text = selectableFolderWithSize.folderWithSize.folder.name
                sizeTextView.text = context.getString(
                    R.string.modules_count,
                    selectableFolderWithSize.folderWithSize.size
                )
                checkMark.visibility =
                    if (selectableFolderWithSize.selected) View.VISIBLE else View.INVISIBLE
            }
        }

    }


}
