package com.example.miniyoutube.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.example.miniyoutube.R
import com.example.miniyoutube.domain.VideoModel
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.String
import kotlin.Suppress

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToVideoFragment(
    public val selectPhotos: VideoModel
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_videoFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(VideoModel::class.java)) {
          result.putParcelable("selectPhotos", this.selectPhotos as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(VideoModel::class.java)) {
          result.putSerializable("selectPhotos", this.selectPhotos as Serializable)
        } else {
          throw UnsupportedOperationException(VideoModel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  private data class ActionHomeFragmentToWeatherFragment(
    public val latitude: String,
    public val longtitude: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_weatherFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("latitude", this.latitude)
        result.putString("longtitude", this.longtitude)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToVideoFragment(selectPhotos: VideoModel): NavDirections =
        ActionHomeFragmentToVideoFragment(selectPhotos)

    public fun actionHomeFragmentToWeatherFragment(latitude: String, longtitude: String):
        NavDirections = ActionHomeFragmentToWeatherFragment(latitude, longtitude)
  }
}
