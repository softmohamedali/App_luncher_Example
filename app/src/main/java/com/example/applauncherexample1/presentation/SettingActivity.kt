package com.example.applauncherexample1.presentation

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.edit
import com.example.applauncherexample1.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private var _binding: ActivitySettingBinding?=null
    val binding: ActivitySettingBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        setUpAction()
        val img=getImage()
        if (img!=null){
            binding.imageView.setImageURI(Uri.parse(img))
        }
    }



    private fun setUpAction() {
        binding.button.setOnClickListener {
            val intentPickImage=Intent(Intent.ACTION_PICK)
            intentPickImage.type="image/*"
            startForResultPickImage.launch(intentPickImage)
        }
    }




    private val startForResultPickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->

        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data!=null){
                var uri=result.data!!
                binding.imageView.setImageURI(uri.data)
                saveUri(uri.data.toString())
            }
        }
    }

    fun saveUri(v:String){
        getSharedPreferences("sh", MODE_PRIVATE).edit {
            putString("img",v)
        }.apply{}
    }

    fun getImage():String?{
        return getSharedPreferences("sh", MODE_PRIVATE).getString("img","")
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}











//
//val hasPermission = remember {
//    mutableStateOf(
//        ContextCompat.checkSelfPermission(
//            context,
//            Manifest.permission.CAMERA
//        )== PackageManager.PERMISSION_GRANTED
//    )
//}
//
//val launcher= rememberLauncherForActivityResult(
//    contract = ActivityResultContracts.RequestPermission(),
//    onResult ={ isGranted ->
//        hasPermission.value=isGranted
//    }
//)