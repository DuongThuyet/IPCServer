package com.example.duongnguyen.ipcserver

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.example.duongnguyen.service.MusicService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var mService: IMusicServiceServer
    private var mIsServiceConnected: Boolean = false

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            mService = IMusicServiceServer.Stub.asInterface(iBinder)
            mIsServiceConnected = true
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            mIsServiceConnected = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initService()
        tv.setOnClickListener {
            startMusic()
        }
        tv1.setOnClickListener {
            stopMusic()
        }
    }

    private fun initService() {
        val intent = Intent(this, MusicService::class.java)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        startService(intent)
    }

    private fun startMusic() {
        mService.play()
    }

    private fun stopMusic() {
        mService.pause()
    }
}
