package kz.farad2020.repoloader.ui.download.adapters

import androidx.recyclerview.widget.DiffUtil
import kz.farad2020.domain.model.GitHubRepository
import java.io.File

class DownloadsDiffUtil(
    private val oldList: List<File>,
    private val newList: List<File>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
