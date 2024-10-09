package kz.farad2020.repoloader.ui.search.adapters

import androidx.recyclerview.widget.DiffUtil
import kz.farad2020.domain.model.GitHubRepository

class RepositoriesDiffUtil(
    private val oldList: List<GitHubRepository>,
    private val newList: List<GitHubRepository>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].fullName == newList[newItemPosition].fullName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
