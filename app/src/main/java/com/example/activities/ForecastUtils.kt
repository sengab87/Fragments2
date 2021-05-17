package com.example.activities

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String {
    return when(tempDisplaySetting) {
        TempDisplaySetting.Farenheit -> String.format("%.2f F°",temp)
        TempDisplaySetting.Celsius ->  {
            val temp = (temp - 32f) * (5f/9f)
            String.format("%.2f C°",temp)
        }
    }
}
fun showTempDisplaySettingDialog(context: Context, tempDisplaySettingManager: TempDisplaySettingManager){
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Choose Display Units")
        .setMessage("Choose which temperature unit to use for temperature display")
        .setPositiveButton("F°"){_, _ ->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Farenheit)
        }
        .setNeutralButton("C°") {_, _ ->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
        }
        .setOnDismissListener(){
            Toast.makeText(context,"Dismiised", Toast.LENGTH_SHORT).show()
        }

    dialogBuilder.show()

}