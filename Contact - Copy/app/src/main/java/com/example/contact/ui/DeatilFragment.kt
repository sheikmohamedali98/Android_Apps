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

//    val textWatcher = object :TextWatcher{
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        }
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            val personName = binding.personEdit.text.toString()
//            val number = binding.phoneEdit.text.toString()
//            val company = binding.companyName.text.toString()
//
//            binding.button.isEnabled = (personName.isNotEmpty()&&!number.isNotEmpty()&&!company.isNotEmpty())
//        }
//
//        override fun afterTextChanged(s: Editable?) {
//        }
//
//    }

    //
    private fun addContact() {
        val personName = binding.personEdit.text.toString()
        val number = binding.phoneEdit.text.toString()
        val companyName = binding.companyName.text.toString()
        if (personName.isEmpty() && number.isEmpty() && companyName.isEmpty()) {

            Toast.makeText(activity, "failed to save", Toast.LENGTH_SHORT).show()


        } else {
            val contact = Contact(0, firstname = personName,
                phonenumber = number,
                company = companyName, imageUrl = imageUrl)

            viewmodel.addContact(contact)
            Toast.makeText(activity, "Saved Contact ", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_deatilFragment_to_listFragment)

        }
    }
}