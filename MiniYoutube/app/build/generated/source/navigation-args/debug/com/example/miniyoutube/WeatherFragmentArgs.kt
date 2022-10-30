package com.example.miniyoutube

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class WeatherFragmentArgs(
  public val latitude: String,
  public val longtitude: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("latitude", this.latitude)
    result.putString("longtitude", this.longtitude)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("latitude", this.latitude)
    result.set("longtitude", this.longtitude)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): WeatherFragmentArgs {
      bundle.setClassLoader(WeatherFragmentArgs::class.java.classLoader)
      val __latitude : String?
      if (bundle.containsKey("latitude")) {
        __latitude = bundle.getString("latitude")
        if (__latitude == null) {
          throw IllegalArgumentException("Argument \"latitude\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"latitude\" is missing and does not have an android:defaultValue")
      }
      val __longtitude : String?
      if (bundle.containsKey("longtitude")) {
        __longtitude = bundle.getString("longtitude")
        if (__longtitude == null) {
          throw IllegalArgumentException("Argument \"longtitude\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"longtitude\" is missing and does not have an android:defaultValue")
      }
      return WeatherFragmentArgs(__latitude, __longtitude)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): WeatherFragmentArgs {
      val __latitude : String?
      if (savedStateHandle.contains("latitude")) {
        __latitude = savedStateHandle["latitude"]
        if (__latitude == null) {
          throw IllegalArgumentException("Argument \"latitude\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"latitude\" is missing and does not have an android:defaultValue")
      }
      val __longtitude : String?
      if (savedStateHandle.contains("longtitude")) {
        __longtitude = savedStateHandle["longtitude"]
        if (__longtitude == null) {
          throw IllegalArgumentException("Argument \"longtitude\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"longtitude\" is missing and does not have an android:defaultValue")
      }
      return WeatherFragmentArgs(__latitude, __longtitude)
    }
  }
}
