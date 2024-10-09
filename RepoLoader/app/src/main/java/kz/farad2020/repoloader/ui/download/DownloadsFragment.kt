package kz.farad2020.repoloader.ui.download

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import kz.farad2020.repoloader.databinding.FragmentDownloadsBinding
import kz.farad2020.repoloader.ui.base.BindingFragment
import kz.farad2020.repoloader.ui.base.gone
import kz.farad2020.repoloader.ui.base.visible
import kz.farad2020.repoloader.ui.download.adapters.DownloadsAdapter
import java.io.File

@AndroidEntryPoint
class DownloadsFragment : BindingFragment<FragmentDownloadsBinding>(
    FragmentDownloadsBinding::inflate
), DownloadsAdapter.OnClickDownloadedRepo {
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

        checkFilesPermission()

        setupViews()

        return binding.root
    }

    private fun setupViews(){
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            checkFilesPermission()
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
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun listFilesInDownloadDirectory() {
        val downloadDirectory = File(requireContext().getExternalFilesDir(null), "Download")

        if (downloadDirectory.exists() && downloadDirectory.isDirectory) {
            val files = downloadDirectory.listFiles()

            if (files != null) {
                showDownloadedRepositories(files.toList())
                binding.tvMessage.gone()
            }
        } else {
            binding.tvMessage.visible()
        }

        binding.swipeRefresh.isRefreshing = false
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