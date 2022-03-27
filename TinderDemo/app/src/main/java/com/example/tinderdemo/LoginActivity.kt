package com.example.tinderdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var callbackManager : CallbackManager
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()
        val etv_email = findViewById<EditText>(R.id.etv_email)
        val etv_password = findViewById<EditText>(R.id.etv_password)

        initLoginButton()
        initSignupButton()
        initEmailAndPasswordText()
        initFacebookLoginButton()

    }

    private fun initFacebookLoginButton() {
        val facebookLoginButton = findViewById<LoginButton>(R.id.btn_facebookLogin)
        facebookLoginButton.setPermissions("email", "public_profile")
        facebookLoginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult) {
                val credential = FacebookAuthProvider.getCredential(result.accessToken.token)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this@LoginActivity) {task->
                        if(task.isSuccessful){
                            handleSuccessLogin()
                        }
                        else{
                            Toast.makeText(this@LoginActivity, "Facebook 로그인에 실패했습니다.", Toast.LENGTH_SHORT)
                        }
                    }
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(this@LoginActivity, "Facebook 로그인에 실패했습니다.", Toast.LENGTH_SHORT)
            }

            override fun onCancel() {
                Toast.makeText(this@LoginActivity, "Facebook 로그인에 실패했습니다.", Toast.LENGTH_SHORT)
            }

        })
    }

    private fun initEmailAndPasswordText() {
        val etv_email = findViewById<EditText>(R.id.etv_email)
        val etv_password = findViewById<EditText>(R.id.etv_password)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_signup = findViewById<Button>(R.id.btn_signup)
        etv_email.addTextChangedListener{
            val enable = etv_email.text.isNotEmpty() && etv_password.text.isNotEmpty()
            btn_login.isEnabled = enable
            btn_signup.isEnabled= enable
        }
        etv_password.addTextChangedListener{
            val enable = etv_email.text.isNotEmpty() && etv_password.text.isNotEmpty()
            btn_login.isEnabled = enable
            btn_signup.isEnabled= enable
        }
    }

    private fun initLoginButton() {
        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        handleSuccessLogin()
                    } else {
                        Toast.makeText(this, "로그인에 실패했습니다. 이메일 비번을 확인해주세요", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    private fun initSignupButton() {
        val btn_signup = findViewById<Button>(R.id.btn_signup)
        btn_signup.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입에 성공했습니다. 로그인 버튼을 눌러주세요", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "이미 있는 이메일이거나, 회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }
    }

    private fun getInputEmail(): String {
        return findViewById<EditText>(R.id.etv_email).text.toString()
    }

    private fun getInputPassword(): String {
        return findViewById<EditText>(R.id.etv_password).text.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
    private fun handleSuccessLogin(){
        if(auth.currentUser == null){
            Toast.makeText(this, "로그인에 실패헀습니다.", Toast.LENGTH_SHORT).show()
            return
        }
        val userId = auth.currentUser?.uid.orEmpty()
        val currentUserDB = Firebase.database.reference.child("Users").child(userId)
        val user = mutableMapOf<String,Any>()
        user["userId"] = userId
        currentUserDB.updateChildren(user)
        finish()

    }

}