package dev.farad2020.planeticketseller.ui.btm_sheet

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import dev.farad2020.planeticketseller.R
import dev.farad2020.planeticketseller.databinding.BtmSheetSearchBinding

class SearchBottomSheet:
    BaseBtmSheetFragment<BtmSheetSearchBinding>(
        BtmSheetSearchBinding::inflate
    ){

//    private lateinit var dialog: BottomSheetDialog
//    override fun getTheme(): Int = R.style.BottomSheetDialogTheme


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }



}