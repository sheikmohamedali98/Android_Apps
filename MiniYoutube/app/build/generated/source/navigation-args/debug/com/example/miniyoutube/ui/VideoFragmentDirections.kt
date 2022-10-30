package com.example.miniyoutube.ui

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.miniyoutube.R

public class VideoFragmentDirections private constructor() {
  public companion object {
    public fun actionVideoFragmentToWebviewFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_videoFragment_to_webviewFragment)
  }
}
