package com.example.activities.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.activities.*

class ForecastDetailsActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)
        tempDisplaySettingManager = TempDisplaySettingManager(this)
        setTitle(R.string.forecast_details)

        val tempText: TextView = findViewById(R.id.tempText)
        val tempDescription: TextView = findViewById(R.id.descriptionText)

        val temp = intent.getFloatExtra("key_temp",0f)
        val description = intent.getStringExtra("key_description")

        tempText.text = formatTempForDisplay(temp, tempDisplaySettingManager.getTempDisplaySetting())
        tempDescription.text = description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.tempDisplaySetttings -> {
                showTempDisplaySettingDialog(this,tempDisplaySettingManager)
                return true
            } else -> return super.onOptionsItemSelected(item)

        }
    }
}
