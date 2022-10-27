package com.example.newsapp.domain.weather


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "current")
    val current: Current,
    @Json(name = "forecast")
    val forecast: Forecast,
    @Json(name = "location")
    val location: Location
) {
    @JsonClass(generateAdapter = true)
    data class Current(
        @Json(name = "cloud")
        val cloud: Int, // 75
        @Json(name = "condition")
        val condition: Condition,
        @Json(name = "feelslike_c")
        val feelslikeC: Double, // 31.7
        @Json(name = "feelslike_f")
        val feelslikeF: Double, // 89.0
        @Json(name = "gust_kph")
        val gustKph: Double, // 25.2
        @Json(name = "gust_mph")
        val gustMph: Double, // 15.7
        @Json(name = "humidity")
        val humidity: Int, // 79
        @Json(name = "is_day")
        val isDay: Int, // 1
        @Json(name = "last_updated")
        val lastUpdated: String, // 2022-10-27 15:15
        @Json(name = "last_updated_epoch")
        val lastUpdatedEpoch: Int, // 1666863900
        @Json(name = "precip_in")
        val precipIn: Double, // 0.03
        @Json(name = "precip_mm")
        val precipMm: Double, // 0.8
        @Json(name = "pressure_in")
        val pressureIn: Double, // 29.8
        @Json(name = "pressure_mb")
        val pressureMb: Double, // 1009.0
        @Json(name = "temp_c")
        val tempC: Double, // 29.0
        @Json(name = "temp_f")
        val tempF: Double, // 84.2
        @Json(name = "uv")
        val uv: Double, // 6.0
        @Json(name = "vis_km")
        val visKm: Double, // 4.0
        @Json(name = "vis_miles")
        val visMiles: Double, // 2.0
        @Json(name = "wind_degree")
        val windDegree: Int, // 40
        @Json(name = "wind_dir")
        val windDir: String, // NE
        @Json(name = "wind_kph")
        val windKph: Double, // 3.6
        @Json(name = "wind_mph")
        val windMph: Double // 2.2
    ) {
        @JsonClass(generateAdapter = true)
        data class Condition(
            @Json(name = "code")
            val code: Int, // 1030
            @Json(name = "icon")
            val icon: String, // //cdn.weatherapi.com/weather/64x64/day/143.png
            @Json(name = "text")
            val text: String // Mist
        )
    }

    @JsonClass(generateAdapter = true)
    data class Forecast(
        @Json(name = "forecastday")
        val forecastday: List<Forecastday>
    ) {
        @JsonClass(generateAdapter = true)
        data class Forecastday(
            @Json(name = "astro")
            val astro: Astro,
            @Json(name = "date")
            val date: String, // 2022-10-27
            @Json(name = "date_epoch")
            val dateEpoch: Int, // 1666828800
            @Json(name = "day")
            val day: Day,
            @Json(name = "hour")
            val hour: List<Hour>
        ) {
            @JsonClass(generateAdapter = true)
            data class Astro(
                @Json(name = "moon_illumination")
                val moonIllumination: String, // 11
                @Json(name = "moon_phase")
                val moonPhase: String, // Waxing Crescent
                @Json(name = "moonrise")
                val moonrise: String, // 07:36 AM
                @Json(name = "moonset")
                val moonset: String, // 07:20 PM
                @Json(name = "sunrise")
                val sunrise: String, // 06:01 AM
                @Json(name = "sunset")
                val sunset: String // 05:44 PM
            )

            @JsonClass(generateAdapter = true)
            data class Day(
                @Json(name = "avghumidity")
                val avghumidity: Double, // 69.0
                @Json(name = "avgtemp_c")
                val avgtempC: Double, // 27.2
                @Json(name = "avgtemp_f")
                val avgtempF: Double, // 80.9
                @Json(name = "avgvis_km")
                val avgvisKm: Double, // 9.5
                @Json(name = "avgvis_miles")
                val avgvisMiles: Double, // 5.0
                @Json(name = "condition")
                val condition: Condition,
                @Json(name = "daily_chance_of_rain")
                val dailyChanceOfRain: Int, // 82
                @Json(name = "daily_chance_of_snow")
                val dailyChanceOfSnow: Int, // 0
                @Json(name = "daily_will_it_rain")
                val dailyWillItRain: Int, // 1
                @Json(name = "daily_will_it_snow")
                val dailyWillItSnow: Int, // 0
                @Json(name = "maxtemp_c")
                val maxtempC: Double, // 29.8
                @Json(name = "maxtemp_f")
                val maxtempF: Double, // 85.6
                @Json(name = "maxwind_kph")
                val maxwindKph: Double, // 21.2
                @Json(name = "maxwind_mph")
                val maxwindMph: Double, // 13.2
                @Json(name = "mintemp_c")
                val mintempC: Double, // 24.5
                @Json(name = "mintemp_f")
                val mintempF: Double, // 76.1
                @Json(name = "totalprecip_in")
                val totalprecipIn: Double, // 0.31
                @Json(name = "totalprecip_mm")
                val totalprecipMm: Double, // 7.8
                @Json(name = "totalsnow_cm")
                val totalsnowCm: Double, // 0.0
                @Json(name = "uv")
                val uv: Double // 6.0
            ) {
                @JsonClass(generateAdapter = true)
                data class Condition(
                    @Json(name = "code")
                    val code: Int, // 1189
                    @Json(name = "icon")
                    val icon: String, // //cdn.weatherapi.com/weather/64x64/day/302.png
                    @Json(name = "text")
                    val text: String // Moderate rain
                )
            }

            @JsonClass(generateAdapter = true)
            data class Hour(
                @Json(name = "chance_of_rain")
                val chanceOfRain: Int, // 0
                @Json(name = "chance_of_snow")
                val chanceOfSnow: Int, // 0
                @Json(name = "cloud")
                val cloud: Int, // 11
                @Json(name = "condition")
                val condition: Condition,
                @Json(name = "dewpoint_c")
                val dewpointC: Double, // 18.9
                @Json(name = "dewpoint_f")
                val dewpointF: Double, // 66.0
                @Json(name = "feelslike_c")
                val feelslikeC: Double, // 27.6
                @Json(name = "feelslike_f")
                val feelslikeF: Double, // 81.7
                @Json(name = "gust_kph")
                val gustKph: Double, // 25.6
                @Json(name = "gust_mph")
                val gustMph: Double, // 15.9
                @Json(name = "heatindex_c")
                val heatindexC: Double, // 27.6
                @Json(name = "heatindex_f")
                val heatindexF: Double, // 81.7
                @Json(name = "humidity")
                val humidity: Int, // 64
                @Json(name = "is_day")
                val isDay: Int, // 0
                @Json(name = "precip_in")
                val precipIn: Double, // 0.0
                @Json(name = "precip_mm")
                val precipMm: Double, // 0.0
                @Json(name = "pressure_in")
                val pressureIn: Double, // 29.88
                @Json(name = "pressure_mb")
                val pressureMb: Double, // 1012.0
                @Json(name = "temp_c")
                val tempC: Double, // 26.1
                @Json(name = "temp_f")
                val tempF: Double, // 79.0
                @Json(name = "time")
                val time: String, // 2022-10-27 00:00
                @Json(name = "time_epoch")
                val timeEpoch: Int, // 1666809000
                @Json(name = "uv")
                val uv: Double, // 1.0
                @Json(name = "vis_km")
                val visKm: Double, // 10.0
                @Json(name = "vis_miles")
                val visMiles: Double, // 6.0
                @Json(name = "will_it_rain")
                val willItRain: Int, // 0
                @Json(name = "will_it_snow")
                val willItSnow: Int, // 0
                @Json(name = "wind_degree")
                val windDegree: Int, // 30
                @Json(name = "wind_dir")
                val windDir: String, // NNE
                @Json(name = "wind_kph")
                val windKph: Double, // 15.1
                @Json(name = "wind_mph")
                val windMph: Double, // 9.4
                @Json(name = "windchill_c")
                val windchillC: Double, // 26.1
                @Json(name = "windchill_f")
                val windchillF: Double // 79.0
            ) {
                @JsonClass(generateAdapter = true)
                data class Condition(
                    @Json(name = "code")
                    val code: Int, // 1000
                    @Json(name = "icon")
                    val icon: String, // //cdn.weatherapi.com/weather/64x64/night/113.png
                    @Json(name = "text")
                    val text: String // Clear
                )
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Location(
        @Json(name = "country")
        val country: String, // India
        @Json(name = "lat")
        val lat: Double, // 13.08
        @Json(name = "localtime")
        val localtime: String, // 2022-10-27 15:28
        @Json(name = "localtime_epoch")
        val localtimeEpoch: Int, // 1666864736
        @Json(name = "lon")
        val lon: Double, // 80.28
        @Json(name = "name")
        val name: String, // Chennai
        @Json(name = "region")
        val region: String, // Tamil Nadu
        @Json(name = "tz_id")
        val tzId: String // Asia/Kolkata
    )
}