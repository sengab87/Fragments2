package com.example.activities.location

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.activities.AppNavigator

import com.example.activities.R

/**
 * A simple [Fragment] subclass.
 */
class LocationEntryFragment : Fragment() {
    private lateinit var appNavigator: AppNavigator
    override fun onAttach(context: Context) {
        super.onAttach(context)

        /// app nav reference to activity
        appNavigator = context as AppNavigator
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_entry, container, false)

        val zipCodeEditText: EditText = view.findViewById(R.id.zipcodeEditText)
        val enterButton: Button = view.findViewById(R.id.enterButton)
        enterButton.setOnClickListener{
            val zipCode : String = zipCodeEditText.text.toString()
            if (zipCode.length != 5){
                Toast.makeText(requireContext(),"error", Toast.LENGTH_SHORT).show()

            }else {
                  Toast.makeText(requireContext(),zipCode,Toast.LENGTH_SHORT).show()
                  appNavigator.navigateToCurrentForecast(zipCode)
//                forecastRespository.loadForecast(zipCode)
            }
        }


        return view
    }

}
