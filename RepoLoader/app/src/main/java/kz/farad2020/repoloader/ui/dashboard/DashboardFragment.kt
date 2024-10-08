package kz.farad2020.repoloader.ui.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kz.farad2020.repoloader.databinding.FragmentDashboardBinding
import kz.farad2020.repoloader.ui.base.BindingFragment
import kz.farad2020.repoloader.ui.base.gone
import kz.farad2020.repoloader.ui.base.visible
import kz.farad2020.repoloader.ui.dashboard.adapters.DownloadsAdapter
import kz.farad2020.repoloader.ui.home.HomeViewModel
import kz.farad2020.repoloader.ui.home.adapters.RepositoriesAdapter
import java.io.File

@AndroidEntryPoint
class DashboardFragment : BindingFragment<FragmentDashboardBinding>(
    FragmentDashboardBinding::inflate
), DownloadsAdapter.OnClickDownloadedRepo {

    private val viewModel: DashboardViewModel by viewModels()


    private val readExternalStoragePermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted, proceed with accessing files
                listFilesInDownloadDirectory()
            } else {
                // Permission denied
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        setMessageListener()

        checkFilesPermission()

        return binding.root
    }

    private fun setMessageListener(){
        viewModel.text.observe(viewLifecycleOwner) {
            if(it.trim().isEmpty()){
                binding.textDashboard.gone()
            }else{
                binding.textDashboard.text = it
                binding.textDashboard.visible()
            }
        }
    }

    private fun checkFilesPermission(){
        // Check for permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted
            listFilesInDownloadDirectory()
        } else {
            // Request permission
            readExternalStoragePermissionRequest.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun listFilesInDownloadDirectory() {
        val downloadDirectory = File(requireContext().getExternalFilesDir(null), "Download")

        if (downloadDirectory.exists() && downloadDirectory.isDirectory) {
            val files = downloadDirectory.listFiles()

            if (files != null) {
                showDownloadedRepositories(files.toList())
                viewModel.setMessage("")
            }

            files?.let {
                for (file in it) {
                    // Do something with each file (e.g., log file names)
                    println("File: ${file.name}")
                }
            }
        } else {
            viewModel.setMessage("Download directory does not exist or is not a directory.")
        }
    }

    private fun showDownloadedRepositories(files: List<File>){
        if(binding.rcDownloads.adapter != null && binding.rcDownloads.adapter is DownloadsAdapter){
            (binding.rcDownloads.adapter as DownloadsAdapter).replaceDownloads(files)
        }else{
            val adapter = DownloadsAdapter(files.toMutableList(), this)
            binding.rcDownloads.adapter = adapter
        }
    }

    override fun onClickRepository(data: File) {
        Toast.makeText(requireContext(), "click repo", Toast.LENGTH_SHORT).show()
    }
}