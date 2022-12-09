package com.example.contact.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contact.BuildConfig
import com.example.contact.R
import com.example.contact.databinding.FragmentContactDetailBinding
import com.example.contact.viewmodel.DetailContactViewmodel
import com.example.contact.viewmodel.DetailContactViewmodelFactory
import com.google.android.material.snackbar.Snackbar


class ContactDetailFragment : Fragment() {

    private  val args by navArgs<ContactDetailFragmentArgs>()

    private lateinit var binding: FragmentContactDetailBinding
    private lateinit var viewmodel:DetailContactViewmodel
    private var contactNumber:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentContactDetailBinding.inflate(inflater,container,false)
        val factory = DetailContactViewmodelFactory(requireActivity().application)
        viewmodel = ViewModelProvider(this,factory)[DetailContactViewmodel::class.java]


        val list = viewmodel.getContact(args.currentContact)
        list.observe(viewLifecycleOwner, Observer {
          binding.apply {
              if(it.imageUrl.isEmpty()){
                  profileImage.setImageResource(R.drawable.ic_baseline_person_24)
              }else {
                  profileImage.setImageURI(it.imageUrl.toUri())
              }
              personName.text = it.firstname
              phoneNumber.text = it.phonenumber
              companyName.text = it.company
              emailId.text = it.email
              favorite.isChecked = it.isFavorite
              contactNumber = it.phonenumber
          }
        })


        binding.editFloatingBtn.setOnClickListener {
            findNavController().navigate(ContactDetailFragmentDirections.actionContactDetailFragmentToUpdateFragment(args.currentContact))
        }
        binding.favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewmodel.updateFavorite(true,args.currentContact)
            }else{
                viewmodel.updateFavorite(false,args.currentContact)
            }
        }

        binding.message.setOnClickListener {
            if(checkPermisssionForMessage()) {
                contactNumber?.let { sendMessage(it) }
            }else{
                Snackbar.make(binding.root,"permission Dined for Send Message", Snackbar.LENGTH_LONG).setAction("Setting"){gotoAppInfo()}.show()

            }
        }

        binding.phoneCall.setOnClickListener {
            if(checkPermissionForPhoneCall()) {
                contactNumber?.let { makeCall(it) }
            }else{
                Snackbar.make(binding.root,"permission Dined for Make call", Snackbar.LENGTH_LONG).setAction("Setting"){gotoAppInfo()}.show()

            }
        }


        return binding.root
    }

  private  fun sendMessage(contactNumber: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$contactNumber"))
//        intent.putExtra("sms_body", "Enter your messaage")
        startActivity(intent)
    }

    private  fun makeCall(contactNumber: String){

        val intent = Intent(Intent.ACTION_CALL)
        intent.setData(Uri.parse("tel:"+contactNumber))
        startActivity(intent)
    }

  private  fun checkPermissionForPhoneCall():Boolean =ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED

    private fun checkPermisssionForMessage():Boolean = ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED

   private fun gotoAppInfo(){
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID,null)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}


