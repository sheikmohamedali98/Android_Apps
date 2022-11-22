package com.example.movieapp.networkconnectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class NetworkConnectionObserver(context: Context):ConnectionObserver {
  private  val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    override fun observer(): Flow<ConnectionObserver.Status> {
        return  callbackFlow {
            val callback = object :NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                launch { send(ConnectionObserver.Status.Available) }
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                launch { send(ConnectionObserver.Status.Losing) }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                launch { send(ConnectionObserver.Status.Lost) }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                launch { send(ConnectionObserver.Status.Unavailable) }
            }
        }
           connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }

        }
    }


}