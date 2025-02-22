package com.bugrahanumaysafak.realtime_weather_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugrahanumaysafak.realtime_weather_app.api.Constant
import com.bugrahanumaysafak.realtime_weather_app.api.NetworkResponse
import com.bugrahanumaysafak.realtime_weather_app.api.RetrofitInstance
import com.bugrahanumaysafak.realtime_weather_app.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi

    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {

        viewModelScope.launch {

            _weatherResult.value = NetworkResponse.Loading

            try {
                val response = weatherApi.getWeather(Constant.apiKey, city)

                if (response.isSuccessful) {

                    response.body()?.let {
                        _weatherResult.postValue(NetworkResponse.Success(it))
                    }
                }else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch (e : Exception) {
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }
        }
    }
}