package com.example.custom_dialog_speed_up

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.example.custom_dialog_speed_up.databinding.DialogBinding
import kotlin.math.round

@SuppressLint("InflateParams", "SetTextI18n")
fun Activity.showDialog(){
    val view: View = LayoutInflater.from(this).inflate(R.layout.dialog, null)
    val dialog = Dialog(this)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(view)
    dialog.window?.setLayout(
        this.window.decorView.width * 9 / 10,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    if (!dialog.isShowing) {
        dialog.show()
    }
    val binding = DialogBinding.bind(view)

    binding.progressBarController.onUpdateSpeedUp = {
        binding.tvSpeedUp.text = "${String.format("%.2f",it)}x"
    }

    binding.btnMinus.setOnClickListener {
        binding.progressBarController.onMinus()
    }

    binding.btnPlus.setOnClickListener {
        binding.progressBarController.onPlus()
    }
}