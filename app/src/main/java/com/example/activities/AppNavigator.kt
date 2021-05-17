package com.example.activities


/// Navigate from one activity to other
interface AppNavigator {
    fun navigateToCurrentForecast(zipcode: String)
    fun navigateToLocationEntry()
}