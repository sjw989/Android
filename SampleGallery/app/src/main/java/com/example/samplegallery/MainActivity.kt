package com.example.samplegallery

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val img : ImageView by lazy{
        findViewById(R.id.iv_photo)
    }
    private val btn:Button by lazy{
        findViewById(R.id.btn_gallery)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn.setOnClickListener{
            checkPermission()
        }


    }

    private fun checkPermission(){
        when {
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED -> {
                // 권한이 승인되어있는 경우
                Toast.makeText(this, "권한이 있습니다", Toast.LENGTH_SHORT).show()
                goGallery()
            }
            else -> {
                Toast.makeText(this, "권한이 없습니다", Toast.LENGTH_SHORT).show()
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    1000
                )
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000 -> {
                if( grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // 권한이 부여가 되었을 때 사진을 가져와야함
                    goGallery() // 갤러리로 이동
                }
                else{
                    Toast.makeText(this, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun goGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent,"gallery"), 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != RESULT_OK){ // 사진 선택 안하고 그냥 취소한 경우
            return
        }
        when (requestCode){
            2000 -> {
                val photo : Uri? = data?.data
                img.setImageURI(photo)
            }
        }

    }
}