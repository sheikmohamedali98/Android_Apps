package com.example.alarammanager

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.alarammanager.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.S)
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: AlarmManagerViewModel
    private lateinit var calendar: Calendar
    private  var alarmManager: AlarmManager? = null
    private lateinit  var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[AlarmManagerViewModel::class.java]
        calendar = Calendar.getInstance()
        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        createNotificationManager()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectTime.setOnClickListener {
            val isSystem24Hour = is24HourFormat(requireContext())
            viewModel.timePicker(isSystem24Hour).show(childFragmentManager,"Alarm Manager")
            setTime()
        }
        binding.setAlarm.setOnClickListener {
            setAlarm()
        }
        binding.cancelAlarm.setOnClickListener {
            cancelAlarm()
        }
    }

    private fun cancelAlarm() {
        val intent = Intent(requireActivity(),AlarmReciver::class.java)
        pendingIntent = PendingIntent.getBroadcast(requireContext(),0,intent,PendingIntent.FLAG_MUTABLE)

        if(alarmManager == null){
            alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        }
        alarmManager?.cancel(pendingIntent)
        Toast.makeText(activity, "Alarm Canceled", Toast.LENGTH_SHORT).show()

    }


    private fun setAlarm(){
        val intent = Intent(requireActivity(),AlarmReciver::class.java)
        pendingIntent = PendingIntent.getBroadcast(requireContext(),0,intent,PendingIntent.FLAG_MUTABLE)
        alarmManager?.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
        Toast.makeText(activity, "Alarm set", Toast.LENGTH_SHORT).show()

    }

    private fun setTime(){

        lifecycleScope.launchWhenStarted {
            viewModel.time.collectLatest {time->
                binding.time.text = time
            }
            calendar = viewModel.calendar
        }
    }

    private  fun createNotificationManager(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name = "Alarm Manager"
            val important = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("Alarm Manager",name,important)
            channel.description = "Notification For Alarm"

            val notificationManager = requireActivity().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}