package com.example.miniyoutube.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.miniyoutube.domain.VideoModel
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class VideoFragmentArgs(
  public val selectPhotos: VideoModel
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(VideoModel::class.java)) {
      result.set("selectPhotos", this.selectPhotos as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(VideoModel::class.java)) {
      result.set("selectPhotos", this.selectPhotos as Serializable)
    } else {
      throw UnsupportedOperationException(VideoModel::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): VideoFragmentArgs {
      bundle.setClassLoader(VideoFragmentArgs::class.java.classLoader)
      val __selectPhotos : VideoModel?
      if (bundle.containsKey("selectPhotos")) {
        if (Parcelable::class.java.isAssignableFrom(VideoModel::class.java) ||
            Serializable::class.java.isAssignableFrom(VideoModel::class.java)) {
          __selectPhotos = bundle.get("selectPhotos") as VideoModel?
        } else {
          throw UnsupportedOperationException(VideoModel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__selectPhotos == null) {
          throw IllegalArgumentException("Argument \"selectPhotos\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"selectPhotos\" is missing and does not have an android:defaultValue")
      }
      return VideoFragmentArgs(__selectPhotos)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): VideoFragmentArgs {
      val __selectPhotos : VideoModel?
      if (savedStateHandle.contains("selectPhotos")) {
        if (Parcelable::class.java.isAssignableFrom(VideoModel::class.java) ||
            Serializable::class.java.isAssignableFrom(VideoModel::class.java)) {
          __selectPhotos = savedStateHandle.get<VideoModel?>("selectPhotos")
        } else {
          throw UnsupportedOperationException(VideoModel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__selectPhotos == null) {
          throw IllegalArgumentException("Argument \"selectPhotos\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"selectPhotos\" is missing and does not have an android:defaultValue")
      }
      return VideoFragmentArgs(__selectPhotos)
    }
  }
}
