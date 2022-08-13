package com.cardsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cardsapp.databinding.FragmentHomeBinding
import com.cardsapp.databinding.ModuleHomeCardBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val adapter by lazy { HomeFragmentFoldersAdapter() }
    private val navController by lazy { findNavController() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        binding.allModules.setOnClickListener { navigateToModulesFragment() }
        binding.allFolders.setOnClickListener { navigateToFoldersFragment() }

        observeFoldersInfo()
        viewModel.getFoldersInfoList()

        observeLastModulesInfo()
        viewModel.getLastModulesInfoList(MAX_MODULES_COUNT)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeFoldersInfo() =
        viewModel.foldersLiveData.observe(viewLifecycleOwner) { foldersInfoList ->
            if (foldersInfoList.isEmpty()) {
                showNoFoldersTextView()
            } else {
                hideNoFoldersTextView()
                adapter.list = foldersInfoList
            }
        }


    private fun observeLastModulesInfo() =
        viewModel.lastModules.observe(viewLifecycleOwner) { modulesInfoList ->
            if (modulesInfoList.isEmpty()) {
                showNoModulesTextView()
            } else {
                hideNoModulesTextView()
                modulesInfoList.forEach { moduleInfo ->
                    binding.modulesGridLayout.addView(createModuleCardView(moduleInfo))
                }
            }
        }

    private fun showNoModulesTextView() {
        with(binding.noModulesTextView) {
            visibility = View.VISIBLE
            setOnClickListener { navigateToCreateModuleFragment() }
        }
    }

    private fun hideNoModulesTextView() {
        binding.noModulesTextView.visibility = View.GONE
    }

    private fun showNoFoldersTextView() {
        binding.recyclerView.visibility = View.INVISIBLE
        with(binding.noFoldersTextView) {
            visibility = View.VISIBLE
            setOnClickListener { navigateToCreateFolderFragment() }
        }
    }

    private fun hideNoFoldersTextView() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.noFoldersTextView.visibility = View.GONE
    }

    private fun navigateToCreateFolderFragment() {
        // TODO: navigation
        Log.d("navigation", "CreateFolderFragment")
    }

    private fun navigateToFoldersFragment() {
        navController.navigate(R.id.action_homeFragment_to_foldersFragment)
    }

    private fun navigateToModulesFragment(folderInfo: FolderInfo? = null) {
        // TODO: navigation
        if (folderInfo != null) {
            Log.d("navigation", "ModulesFragment $folderInfo")
        } else {
            Log.d("navigation", "ModulesFragment")
        }
    }

    private fun navigateToModuleFragment(moduleInfo: ModuleInfo) {
        // TODO: navigation
        Log.d("navigation", "ModuleFragment $moduleInfo")
    }

    private fun navigateToCreateModuleFragment() {
        // TODO: navigation
        Log.d("navigation", "CreateModuleFragment")
    }

    private fun createModuleCardView(moduleInfo: ModuleInfo): CardView {
        return ModuleHomeCardBinding.inflate(layoutInflater)
            .apply {
                moduleNameTextView.text = moduleInfo.name
                termsCountTextView.text =
                    root.context.getString(R.string.terms_count, moduleInfo.size)
            }
            .root
            .apply {
                setOnClickListener { navigateToModuleFragment(moduleInfo) }
            }
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            adapter = this@HomeFragment.adapter
            addDivider(R.drawable.home_recycler_divider, DividerItemDecoration.HORIZONTAL)
            LinearSnapHelper().attachToRecyclerView(this)
        }
        adapter.onItemClickListener = ItemClickListener {
            navigateToModulesFragment(it)
        }
    }

    companion object {
        private const val MAX_MODULES_COUNT = 4
    }


}
