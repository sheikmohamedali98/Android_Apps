package com.example.miniyoutube

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class WebviewFragmentDirections private constructor() {
  public companion object {
    public fun actionWebviewFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_webviewFragment_to_homeFragment)

    public fun actionWebviewFragmentToHomeFragment2(): NavDirections =
        ActionOnlyNavDirections(R.id.action_webviewFragment_to_homeFragment2)
  }
}
