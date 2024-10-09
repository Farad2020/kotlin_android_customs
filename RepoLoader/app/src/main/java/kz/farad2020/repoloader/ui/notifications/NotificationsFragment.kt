package kz.farad2020.repoloader.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kz.farad2020.repoloader.databinding.FragmentNotificationsBinding
import kz.farad2020.repoloader.ui.base.BindingFragment

class NotificationsFragment : BindingFragment<FragmentNotificationsBinding>(
    FragmentNotificationsBinding::inflate
) {

    private val notificationsViewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return binding.root
    }
}