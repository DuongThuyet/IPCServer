package com.example.duongnguyen.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.DeadObjectException
import android.os.IBinder
import android.support.annotation.RequiresApi
import com.example.duongnguyen.ipcserver.IMusicServiceServer
import com.example.duongnguyen.ipcserver.R
import com.example.duongnguyen.model.ModelDem


class MusicService : Service() {

    private val NOTIFICATION_ID = 1

    private var mNotification = Notification()

    private lateinit var mPlayerManager: MediaPlayer

    private var model = ModelDem()

    private val mBinder = object : IMusicServiceServer.Stub() {
        override fun getSongName(): String {
            return model.name
        }

        override fun changeMediaStatus() {
            //TODO changeMediaStatus
        }

        override fun playSong() {
            //TODO playSong
            try {
                mPlayerManager.start()
            } catch (e: DeadObjectException) {
                e.printStackTrace()
            }

        }

        override fun play() {
            try {
                mPlayerManager.start()
            } catch (e: DeadObjectException) {
                e.printStackTrace()
            }
        }

        override fun pause() {
            mPlayerManager.pause()
        }

        override fun getCurrentDuration(): Int {
            return model.subModel.subModelId
        }

        override fun getTotalDuration(): Int {
            return mPlayerManager.duration
        }
    }

    override fun onCreate() {
        super.onCreate()
        mPlayerManager = MediaPlayer.create(applicationContext, R.raw.tymn)
        startForegroundService()

    }

    private fun startForegroundService() {
        startForeground(NOTIFICATION_ID, getNotification())
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private fun getNotification(): Notification {
        val title = "IPC Testing"
        mNotification = Notification.Builder(this).setContentTitle(title)
            .setContentText(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()

        return mNotification
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayerManager.pause()
    }
}
