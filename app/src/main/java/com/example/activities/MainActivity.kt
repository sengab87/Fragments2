package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activities.Forecast.CurrentForecastFragment
import com.example.activities.details.ForecastDetailsActivity
import com.example.activities.location.LocationEntryFragment

// Fragment has it own xml
/// Fragment manager manages what is shown on screen add and remove fragments.
/// Fragment transaction represents the addition and removes fragment from the container


// activities define layout which has layout container and views ///
// Recycle view layoutManager // Adapter connects data to individual views
// viewHolder bind data

/// Repo load or form data either from internet or device /// view Model
/// repo data to activity liveData ///
/// Live Data observable data Holder -----> observe live data from activity which will change when
// Repo update live data.   Repo is Like data source /// Adapter like delegate //

// Live data must be observed /// MutableLiveData
class MainActivity : AppCompatActivity(), AppNavigator {

    /// Observer to know when it updated
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        tempDisplaySettingManager = TempDisplaySettingManager(this)




        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, LocationEntryFragment())
            .commit()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
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

    override fun navigateToCurrentForecast(zipcode: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,CurrentForecastFragment.newInstance(zipcode))
            .commit()
    }

    override fun navigateToLocationEntry() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, LocationEntryFragment())
            .commit()
    }

}
