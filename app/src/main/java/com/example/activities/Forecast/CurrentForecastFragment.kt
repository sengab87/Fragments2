package com.example.activities.Forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activities.*

import com.example.activities.details.ForecastDetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 */
class CurrentForecastFragment : Fragment() {
    private lateinit var appNavigator: AppNavigator
    override fun onAttach(context: Context) {
        super.onAttach(context)

        /// app nav reference to activity
        appNavigator = context as AppNavigator
    }
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRespository = ForecastRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val zipcode = arguments!!.getString(KEY_ZIPCODE) ?: ""
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)
        val forecastList: RecyclerView = view.findViewById(R.id.forecastList)

        val locationEntryButton:FloatingActionButton = view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener{
            appNavigator.navigateToLocationEntry()
        }
        forecastList.layoutManager = LinearLayoutManager(requireContext())
        val dailyForeCastAdapter = DailyForeCastAdapter(tempDisplaySettingManager){
            /// clicked click handler
            showForecastDetails(it)

        }
        forecastList.adapter = dailyForeCastAdapter
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->

            /// UPDATE OUT LIST ADAPTER
            dailyForeCastAdapter.submitList(forecastItems)

        }

        forecastRespository.weeklyForecast.observe(this, weeklyForecastObserver)
        forecastRespository.loadForecast(zipcode)
        return view
    }
    private fun showForecastDetails(forecast: DailyForecast){
        val forecastDetailsIntent = Intent(requireContext(), ForecastDetailsActivity::class.java)
        forecastDetailsIntent.putExtra("key_temp",forecast.temp)
        forecastDetailsIntent.putExtra("key_description",forecast.description)
        startActivity(forecastDetailsIntent)
    }

    companion object{
        const val KEY_ZIPCODE = "key_zipcode"

        fun newInstance(zipcode: String) : CurrentForecastFragment {
            val fragment = CurrentForecastFragment()

            val args = Bundle()
            args.putString(KEY_ZIPCODE,zipcode)

            fragment.arguments = args

            return fragment
        }
    }


}
