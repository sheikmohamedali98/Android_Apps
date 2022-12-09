package com.example.contact.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.contact.R
import com.example.contact.database.Contact
import  com.example.contact.databinding.FragmentDeatilBinding
import com.example.contact.viewmodel.ContactViewModel
import com.example.contact.viewmodel.ContactViewmodelFactory
import com.github.dhaval2404.imagepicker.ImagePicker


class DeatilFragment : Fragment() {

    private lateinit var binding: FragmentDeatilBinding
    private lateinit var viewmodel: ContactViewModel
    private var imageUrl: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeatilBinding.inflate(inflater, container, false)

        val factory = ContactViewmodelFactory(requireActivity().application)
        viewmodel = ViewModelProvider(this, factory)[ContactViewModel::class.java]


//        binding.apply {
//            personEdit.addTextChangedListener(textWatcher)
//            companyName.addTextChangedListener(textWatcher)
//            phoneEdit.addTextChangedListener(textWatcher)
//        }

        binding.button.setOnClickListener {
            addContact()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.profileImage.setOnClickListener {
            binding.profileImage.setOnClickListener {
                Toast.makeText(activity, "profile clicked ", Toast.LENGTH_SHORT).show()
                ImagePicker.with(this)
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080,
                        1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }
            }
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data!!
                imageUrl = fileUri.path.toString()
                binding.profileImage.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(activity,
                    ImagePicker.getError(data).toString(),
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }


    private fun addContact() {
        val personName = binding.personEdit.text.toString()
        val number = binding.phoneEdit.text.toString()
        val companyName = binding.companyName.text.toString()
        val emailId = binding.emailId.text.toString()
        if (personName.isEmpty() && number.isEmpty() && companyName.isEmpty()) {

            Toast.makeText(activity, "Enter Valid Input", Toast.LENGTH_SHORT).show()


        } else {

            val contact = Contact(0, firstname = personName,
                phonenumber = number,
                company = companyName, imageUrl = imageUrl, email = emailId)

            viewmodel.addContact(contact)
            Toast.makeText(activity, "Saved Contact ", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
//            val controller: NavController = this.findNavController()
//            controller.popBackStack(R.id.listContactFragment, true);

        }
    }
}