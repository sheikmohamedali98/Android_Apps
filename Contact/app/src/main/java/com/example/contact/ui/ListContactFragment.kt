package com.example.contact.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.BuildConfig
import com.example.contact.R
import com.example.contact.adapter.ContactAdapter
import com.example.contact.database.Contact
import com.example.contact.databinding.FragmentListContactBinding
import com.example.contact.util.SwipeFeature
import com.example.contact.viewmodel.ContactViewModel
import com.example.contact.viewmodel.ContactViewmodelFactory
import com.google.android.material.snackbar.Snackbar


class ListContactFragment : Fragment(), ContactAdapter.ClickListener {


    private lateinit var binding:FragmentListContactBinding
    private lateinit var viewModel: ContactViewModel
    private var clicked = false
    private lateinit var adapter: ContactAdapter



//    private val rotateOpen:Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_open_anim) }
//    private val rotateClose:Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_close_anim) }
//    private val fromBottom :Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.from_bottom_anim) }
//    private val toBottom:Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.to_bottom_anim) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListContactBinding.inflate(inflater, container, false)
        val application = requireActivity().application
        val factory = ContactViewmodelFactory(application)
        viewModel = ViewModelProvider(this, factory)[ContactViewModel::class.java]
        adapter = ContactAdapter(this@ListContactFragment)
        binding.recyclerView.adapter = adapter



        ItemTouchHelper(object : SwipeFeature(application){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val contact = adapter.currentList[position]
                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        findNavController().navigate(ListContactFragmentDirections.actionListContactFragmentToMessageFragment(contact.phonenumber))
                        Toast.makeText(activity, "Edited", Toast.LENGTH_SHORT).show()
                    }
                    ItemTouchHelper.LEFT -> {
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setMessage("Are you sure you want to Delete?")
                            .setCancelable(false)
                            .setTitle("Contact")
                            .setPositiveButton("Yes") { dialog, id ->
                                // Delete selected note from database
                              viewModel.deletteSingleContact(contact)
                            }
                            .setNegativeButton("No") { dialog, id ->
                                dialog.dismiss()
                            }
                        val alert = builder.create()
                        alert.show()
                    }
                }
            }

        }).attachToRecyclerView(binding.recyclerView)

        viewModel.readAllContact.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate( R.id.action_listContactFragment_to_deatilFragment)
        }

        requestPermission.launch(
            arrayOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
            )
        )
        return binding.root
    }

    override fun onCardClickListener(contact: Contact) {
        findNavController().navigate(ListContactFragmentDirections.actionListContactFragmentToContactDetailFragment(contact.id))
    }

//    private fun addButtonClick() {
//        setVisibility(clicked)
//        setAnimation(clicked)
//        clicked = !clicked
//    }
//
//    private fun setAnimation(clicked:Boolean) {
//        if(!clicked){
//            binding.deleteAll.visibility = View.VISIBLE
//            binding.adding.visibility = View.VISIBLE
//        }else{
//            binding.deleteAll.visibility = View.GONE
//            binding.adding.visibility = View.GONE
//        }
//    }
//
//    private fun setVisibility(clicked: Boolean) {
//        if(!clicked){
//            binding.deleteAll.startAnimation(fromBottom)
//            binding.adding.startAnimation(fromBottom)
//            binding.floatingActionButton.startAnimation(rotateOpen)
//        }else{
//            binding.deleteAll.startAnimation(toBottom)
//            binding.adding.startAnimation(toBottom)
//            binding.floatingActionButton.startAnimation(rotateClose)
//        }
//    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.forEach { actionMap ->
                when (actionMap.key) {
                    Manifest.permission.READ_CONTACTS -> {
                        if (actionMap.value) {
                            // permission granted continue the normal
                            Log.i("DEBUG", "permission granted")
                        } else {
                            Snackbar.make(binding.root,"permission Dined Read Contact", Snackbar.LENGTH_LONG).setAction("Setting"){gotoAppInfo()}.show()
                            Log.i("DEBUG", "permission denied")
                        }
                    }
                    Manifest.permission.CALL_PHONE->{
                        if (actionMap.value) {
                            // permission granted continue the normal
                            Log.i("DEBUG", "permission granted")
                        } else {
                             Snackbar.make(binding.root,"permission Dined for Make Call", Snackbar.LENGTH_LONG).setAction("Setting"){gotoAppInfo()}.show()
                            Log.i("DEBUG", "permission denied")
                        }
                    }
                }
            }
        }

   fun  gotoAppInfo(){
       val intent = Intent()
       intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
       val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID,null)
       intent.data = uri
       intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
       startActivity(intent)
    }
}