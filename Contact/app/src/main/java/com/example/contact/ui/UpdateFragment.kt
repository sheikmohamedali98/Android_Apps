package com.example.contact.ui

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contact.R
import com.example.contact.database.Contact
import com.example.contact.databinding.FragmentUpdateBinding
import com.example.contact.viewmodel.UpdateViewmodel
import com.example.contact.viewmodel.UpdateViewmodelFactory
import com.github.dhaval2404.imagepicker.ImagePicker


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewmodel:UpdateViewmodel
    private var imageUrl:String = ""

    private  val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUpdateBinding.inflate(inflater,container,false)

        val factroy = UpdateViewmodelFactory(requireActivity().application)
        viewmodel = ViewModelProvider(this,factroy)[UpdateViewmodel::class.java]

        binding.profileImage.setOnClickListener {
          imagePicker()
        }
        val id = args.updateContact

       val contact = viewmodel.getContact(id)
        contact.observe(viewLifecycleOwner, Observer {
            binding.apply {
                if(it.imageUrl.isEmpty()){
                    profileImage.setImageResource(R.drawable.ic_baseline_person_24)
                }else{
                    profileImage.setImageURI(it.imageUrl.toUri())
                }

                personName.editText?.setText(it.firstname)
                phoneEdit.setText(it.phonenumber)
                companyName.setText(it.company)
                emailId.setText(it.email)
                imageUrl = it.imageUrl

            }
        })






        binding.button.setOnClickListener {
            updateContact()
            findNavController().navigateUp()
        }


        return binding.root
    }


    val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
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


    fun updateContact(){
        val personName = binding.personEdit.text.toString()
        val number = binding.phoneEdit.text.toString()
        val companyName = binding.companyName.text.toString()
        val emailId = binding.emailId.text.toString()
        if (personName.isEmpty() && number.isEmpty() && companyName.isEmpty()) {
            Toast.makeText(activity, "failed to save", Toast.LENGTH_SHORT).show()
        } else {

            val contact = Contact(args.updateContact, firstname = personName,
                phonenumber = number,
                company = companyName, imageUrl = imageUrl, email = emailId)
                viewmodel.updateContact(contact)
                Toast.makeText(activity, "Updated Contact ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun imagePicker(){
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