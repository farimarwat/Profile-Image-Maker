package com.farimarwat.profileimagemakerexample

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.farimarwat.profileimagemakerexample.databinding.ActivityMainBinding
import pk.farimarwat.profileimagemaker.ProfileImageMaker
import java.io.File
import java.io.FileOutputStream

const val TAG = "ProfileImageMaker"
class MainActivity : AppCompatActivity() {
    lateinit var mContext: Context
    val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var mPim: ProfileImageMaker
    var mBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mContext = this
        mPim = ProfileImageMaker.Builder(mContext)
            .build()

        //preparing for save

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
            mBitmap = MediaStore.Images.Media.getBitmap(contentResolver,it)
            mBitmap?.let { src ->
                mPim.applyRemoveBackground(src){ result ->
                    binding.progressBar.visibility = View.GONE
                    binding.pim.setImage(result)
                }
            }
        }
        binding.button.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            getContent.launch("image/*")
        }
        binding.button2.setOnClickListener {
            saveBitmap(mContext,binding.pim.getBitmap())
        }
    }
    fun saveBitmap(context: Context, bitmap:Bitmap){
        var outStream: FileOutputStream? = null
        val dir = File(context.getExternalFilesDir(null).toString())
        if(!dir.exists()){
            dir.mkdirs()
        }
        val fileName = String.format("%d.png", System.currentTimeMillis())
        val outFile = File(dir, fileName)
        Log.e("TEST",outFile.toString())
        outStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        outStream.flush()
        outStream.close()
    }
}