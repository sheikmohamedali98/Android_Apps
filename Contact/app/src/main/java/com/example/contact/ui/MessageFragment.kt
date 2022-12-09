package com.example.contact.ui

import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.contact.R
import com.example.contact.databinding.FragmentMessageBinding


class MessageFragment : Fragment() {

    private lateinit var binding: FragmentMessageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageBinding.inflate(layoutInflater, container, false)

        val args = MessageFragmentArgs.fromBundle(requireArguments()).phoneNumber

        binding.personName.text = args

        binding.sendButton.setOnClickListener {
            val message = binding.messageArea.text.toString()
            sendMessage(args,message)

        }
        return binding.root
    }

    private fun sendMessage (phoneNumber:String,message:String){
        // on the below line we are creating a try and catch block
        try {

            val smsManager: SmsManager
            if (Build.VERSION.SDK_INT>=23 && false) {

                smsManager = requireContext().getSystemService(SmsManager::class.java)
            }
            else{

                smsManager = SmsManager.getDefault() as SmsManager
            }

            smsManager.sendTextMessage(phoneNumber, null, message, null, null)

            Toast.makeText(activity, "Message Sent", Toast.LENGTH_LONG).show()

        } catch (e: Exception) {

            println("\n\n\n\n${e.message.toString()}\n\n\n")
            Toast.makeText(activity, "Please enter all the data.."+e.message.toString(), Toast.LENGTH_LONG)
                .show()
        }

    }

}