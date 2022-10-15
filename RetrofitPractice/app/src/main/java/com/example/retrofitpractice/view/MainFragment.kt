package com.example.retrofitpractice.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpractice.R
import com.example.retrofitpractice.databinding.FragmentMainBinding
import com.example.retrofitpractice.repository.Repository
import com.example.retrofitpractice.viewmodel.MainViewModel
import com.example.retrofitpractice.viewmodel.MainViewModelFactory


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMainBinding.inflate(inflater,container,false)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        val option:HashMap<String,String> = HashMap()
        option["_sort"] = "id"
        option["_order"] = "desc"

//        viewModel.getPost()
//        viewModel.myResponce.observe(viewLifecycleOwner, Observer {response->
//          if(response.isSuccessful){
////              Log.d("Response",response.body()?.number.toString())
////              Log.d("Response",response.body()?.id.toString())
//              binding.textView.text = response.body()?.line1.toString()
////              Log.d("Response",response.body()?.body.toString())
//          }else{
//              binding.textView.text= "hai"
//
//          }
//        })



//
//        binding.button.setOnClickListener {
//
//            val myNumber = binding.editTextNumber.text.toString()
//            viewModel.getPost2(myNumber.toInt())
////            viewModel.getPost2(myNumber.toInt())
//            viewModel.myResponce2.observe(viewLifecycleOwner, Observer {response->
//                if(response.isSuccessful){
//                    binding.textView.text = response.body().toString()
//                }else{
//
//                    binding.textView.text = response.code().toString()
//                }
//            })
//            Toast.makeText(activity, "${myNumber.toInt()}", Toast.LENGTH_SHORT).show()
//
//        }
//        binding.button.setOnClickListener {
//            val myNumber = binding.editTextNumber.text.toString()
//            viewModel.myResponce
//        }


//        binding.button.setOnClickListener {
//            val myNuber = binding.editTextNumber.text.toString()
//            viewModel.getPost2(myNuber.toInt())
//            Toast.makeText(activity, "${myNuber.toInt()}", Toast.LENGTH_SHORT).show()
//            viewModel.myResponce2.observe(viewLifecycleOwner, Observer { response ->
//                if(response.isSuccessful){
//                    binding.textView.text = response.body().toString()
//                }else{
//                    binding.textView.text = response.code().toString()
//                }
//
//            })
//        }

        binding.button.setOnClickListener {
            val myNumber =  binding.editTextNumber.text.toString()
            viewModel.getCustomPost3(myNumber.toInt(),option)
            viewModel.myCustomePost.observe(viewLifecycleOwner, Observer { response->
                if(response.isSuccessful){
                    binding.textView.text  = response.body().toString()
//                    response.body().forEach {
//
//                    }

                }else{
                    binding.textView.text = response.code().toString()
                }

            })
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root

    }


}