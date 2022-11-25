package com.cardsapp.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cardsapp.R
import com.cardsapp.addDivider
import com.cardsapp.databinding.AddFolderDialogBinding
import com.cardsapp.databinding.FragmentFoldersBinding
import com.cardsapp.launchWhenStarted
import com.cardsapp.replaced
import kotlinx.coroutines.flow.onEach

class FoldersFragment : Fragment(R.layout.fragment_folders) {

    private val binding: FragmentFoldersBinding by viewBinding(FragmentFoldersBinding::bind)
    private val toolbar by lazy { SearchAndSelectionToolbar(binding.foldersFragmentToolBar) }
    private val adapter: FoldersAdapter by lazy {
        FoldersAdapter(requireContext())
    }
    private val viewModel: FoldersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupAdapter()
        setupToolBar()
        setupRecyclerView()

        binding.floatingActionButton.setOnClickListener {
            showAddFolderDialog()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showProgressBar() {
        //TODO
    }

    private fun setupAdapter() {

    }

    private fun setupToolBar() = with(toolbar) {
        defaultTitle = getString(R.string.folders)

        selectionMenuRes = R.menu.delete_menu
        onMenuItemClickListener = Toolbar.OnMenuItemClickListener {
//            viewModel.deleteFolders()
            return@OnMenuItemClickListener true
        }

        onBackPressedListener = View.OnClickListener { findNavController().navigateUp() }
//        onCancelSelectionButtonClick = { adapter.selectionModeEnabled = false }

        onQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String) = true

                override fun onQueryTextChange(newText: String): Boolean {
//                    viewModel.searchFolders(newText)
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

    private fun showAddFolderDialog() {
        val dialogBinding = AddFolderDialogBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.add_folder)
            .setPositiveButton(android.R.string.ok, null)
            .setView(dialogBinding.root)
            .create()

//        dialog.setOnShowListener {
//            adapter.selectionModeEnabled = false
//            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
//                val text = dialogBinding.editText.text.toString()
//                if (text.isBlank()) {
//                    dialogBinding.inputLayout.error = "Пустая строка"
//                    return@setOnClickListener
//                } else if (adapter.currentList.any { it.name == text }) {
//                    dialogBinding.inputLayout.error = "Уже существует"
//                    return@setOnClickListener
//                } else {
//                    viewModel.addFolder(text)
//                    dialog.dismiss()
//                }
//            }
//        }
        dialog.show()
    }
}