package com.bugrahanumaysafak.realtime_weather_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.bugrahanumaysafak.realtime_weather_app.ui.theme.Realtime_Weather_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        enableEdgeToEdge()
        setContent {
            Realtime_Weather_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WeatherPage(weatherViewModel)
                }
            }
        }
    }
}
