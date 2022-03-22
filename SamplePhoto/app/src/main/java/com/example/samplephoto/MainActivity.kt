package com.example.samplephoto

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val addPhotoButton: Button by lazy {
        findViewById<Button>(R.id.btn_addphoto)
    }
    private val startPhotoFrameMode: Button by lazy {
        findViewById<Button>(R.id.btn_FrameMode)
    }
    private val imageViewList: List<ImageView> by lazy {
        mutableListOf<ImageView>().apply {
            add(findViewById(R.id.iv_1))
            add(findViewById(R.id.iv_2))
            add(findViewById(R.id.iv_3))
            add(findViewById(R.id.iv_4))
            add(findViewById(R.id.iv_5))
            add(findViewById(R.id.iv_6))
        }
    }

    private val imageUriList : MutableList<Uri> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAddPhotoButton()
        initStartPhotoFrameModeButton()
    }


    private fun initAddPhotoButton() {
        addPhotoButton.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    goGallery()
                    // 권한이 잘 부여됐을 때 갤러리에서 사진을 선택하는 기능
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    // 권한이 부여가 되어있지않으면 교육용 팝업?을 출력
                    showPermissionContextPopup()
                }
                else -> {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1000
                    )
                }
            }
        }
    }

    private fun initStartPhotoFrameModeButton() {
        startPhotoFrameMode.setOnClickListener{
            val intent = Intent(this, PhotoFrameActivity::class.java)
            imageUriList.forEachIndexed { index, imageview ->
                intent.putExtra("photo$index", imageview.toString())
            }
            intent.putExtra("photoListSize", imageUriList.size)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000 -> {
                if( grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
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
        if( resultCode != Activity.RESULT_OK){ // 캔슬한 경우
            return
        }
        when(requestCode){
            2000 -> {
                val selectedImageUri : Uri? = data?.data

                if (selectedImageUri != null){

                    if(imageUriList.size == 6) {
                        Toast.makeText(this, "사진이 꽉 찼습니다 !", LENGTH_SHORT).show()
                        return
                    }

                    imageUriList.add(selectedImageUri)
                    imageViewList[imageUriList.size - 1].setImageURI(selectedImageUri)
                }
                else{
                    Toast.makeText(this, "사진을 가져오지 못했습니다", LENGTH_SHORT).show()
                }

            }
            else -> {
                Toast.makeText(this, "사진을 가져오지 못했습니다", LENGTH_SHORT).show()
            }

        }
    }

    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this).setTitle("권한이 필요합니다")
            .setMessage("전자액자에서 사진을 불러오기 위해 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }

}