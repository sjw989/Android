package com.example.hw01_apptive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.host_activity.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_activity)
        initViewFinal()
    }



    //모듈화를 위해 분리 , 클래스안에 함수 매소드.
    fun initViewFinal() {

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        //NavHostFragment 는 클래스
        val navController = host.navController // 바로 윗줄 포함 두줄 필수. 네비게이션.xml에 접근위해.
        //NavigationUI.setupWithNavController(main_navigationView,navController)

        // 선언해서 객체 집어넣는것
        //appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment), main_drawer_layout)
        //우리가 만든것을 appBarConfiguration 와 연결결
        //setupActionBarWithNavController(navController, appBarConfiguration)


        // _ 이 부분은 매개변수를 안줄것이고 얘들이 주는 리턴값을 안쓰겠다.
        // -> 리턴할때..
        navController.addOnDestinationChangedListener{_, destination, _ ->
            // 화면이 바뀔때 키보드 무조건 숨김
            val dest: String = try{
                resources.getResourceName(destination.id)
            } catch (e: Exception){
                return@addOnDestinationChangedListener
            }
        } // 원랜 ({})

    }
}
