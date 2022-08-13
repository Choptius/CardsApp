package com.cardsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cardsapp.databinding.FragmentFoldersBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FoldersFragment : Fragment(R.layout.fragment_folders) {

    private val binding: FragmentFoldersBinding by viewBinding(FragmentFoldersBinding::bind)
    private val navController by lazy { findNavController() }
    private val toolbar by lazy { SearchAndSelectionToolbar(binding.foldersFragmentToolBar) }
    private val adapter: FoldersAdapter by lazy {
        FoldersAdapter(requireContext())
    }
    private val viewModel: FoldersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupToolBar()
        setupAdapter()
        setupRecyclerView()

        viewModel.foldersInfoList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.map { FolderInfoRvDto(name = it.name, size = it.size) })
        }
        viewModel.getFoldersInfoList()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupAdapter() {
        adapter.selectionModeListener =  OnSelectionModeChangedListener { enabled ->
            toolbar.selectionModeEnabled = enabled
        }

        adapter.selectionFlow.onEach { list ->
            toolbar.showSelectedItemsCount(list.size)
        }.launchWhenStarted(lifecycleScope)
    }

    private fun setupToolBar() = with(toolbar) {
        defaultTitle = getString(R.string.folders)

        selectionMenuRes = R.menu.delete_menu
        onMenuItemClickListener = Toolbar.OnMenuItemClickListener {
            viewModel.deleteFolders(adapter.finishSelection())
            return@OnMenuItemClickListener true
        }

        onBackPressedListener = View.OnClickListener { navController.navigateUp() }
        onCancelSelectionButtonClick = { adapter.selectionModeEnabled = false }

        onQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String) = true

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.searchFolders(newText)
                    binding.foldersRecyclerView.scrollToPosition(0)
                    return true
                }
            }
    }

    private fun setupRecyclerView() {
        with(binding.foldersRecyclerView) {
            adapter = this@FoldersFragment.adapter
            addDivider(R.drawable.folders_recycler_didvider, DividerItemDecoration.VERTICAL)
        }
    }

}