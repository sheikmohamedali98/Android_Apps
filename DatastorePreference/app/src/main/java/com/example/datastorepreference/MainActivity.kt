package com.example.datastorepreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.datastorepreference.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first

private const val  PREFERENCE  = "data"

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "save")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val save = binding.editTextTextPersonName.text.toString()
            if(save.isNotEmpty()){
                lifecycleScope.launchWhenStarted {
                    save(PREFERENCE,save)
                }
            }else{
                Toast.makeText(this, "Please Enter The Value", Toast.LENGTH_SHORT).show()
            }

        }

        lifecycleScope.launchWhenStarted {
            val getDataFromDataStore = getData(PREFERENCE)
            binding.textView.text = getDataFromDataStore

        }

    }

    private suspend fun save(key:String,value:String){
        val datastoreKey = stringPreferencesKey(key)
        dataStore.edit {saveData->
            saveData[datastoreKey] = value
        }
    }

    private suspend fun getData(key:String):String{
        val datastoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return  preferences[datastoreKey].toString()
    }

}