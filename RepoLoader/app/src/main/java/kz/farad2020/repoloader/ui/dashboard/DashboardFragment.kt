package kz.farad2020.repoloader.ui.dashboard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kz.farad2020.repoloader.databinding.FragmentDashboardBinding
import kz.farad2020.repoloader.ui.base.BindingFragment
import java.io.File

class DashboardFragment : BindingFragment<FragmentDashboardBinding>(
    FragmentDashboardBinding::inflate
) {
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

        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        checkFilesPermission()

        return binding.root
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
            files?.let {
                for (file in it) {
                    // Do something with each file (e.g., log file names)
                    println("File: ${file.name}")
                }
            }
        } else {
            println("Download directory does not exist or is not a directory.")
        }
    }
}