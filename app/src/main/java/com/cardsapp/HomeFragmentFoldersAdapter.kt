package com.cardsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cardsapp.databinding.FolderHomeCardBinding

class HomeFragmentFoldersAdapter :
    RecyclerView.Adapter<HomeFragmentFoldersAdapter.FolderInfoHolder>() {

    private lateinit var context: Context

    var list = emptyList<FolderInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener = ItemClickListener<FolderInfo> { }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        context = recyclerView.context
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderInfoHolder {
        val inflater = LayoutInflater.from(context)
        val binding = FolderHomeCardBinding.inflate(inflater, parent, false)
        return FolderInfoHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderInfoHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount() = list.size


    inner class FolderInfoHolder(private val binding: FolderHomeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentFolderInfo: FolderInfo? = null

        init {
            binding.root.setOnClickListener {
                if (currentFolderInfo != null) {
                    onItemClickListener.onItemClick(currentFolderInfo!!)
                }
            }
        }

        fun bind(folderInfo: FolderInfo) {
            currentFolderInfo = folderInfo
            with(binding) {
                nameTextView.text = folderInfo.name
                sizeTextView.text = context.getString(R.string.modules_count, folderInfo.size)
            }
        }

    }
}
