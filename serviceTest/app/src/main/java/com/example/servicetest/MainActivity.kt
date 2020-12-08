package com.example.servicetest

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.example.servicetest.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        registerBR()

        binding.btnStart.setOnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity,
            MusicService::class.java)
            startService(intent)
        }
        binding.stopBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this@MainActivity,
            MusicService::class.java)
            stopService(intent)
        }
        binding.btnRandomButton.setOnClickListener {
            startService(Intent(this,BoundService::class.java))
            binding.btnRandomButton.text = myAIDLService?.getNumber(10)
            startService(Intent(this, DownloadIntentService::class.java))
        }
    }

    private fun registerBR() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_COMPLETE_DOWNLOARD)
        
        registerReceiver(broadcastR, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent()
        intent.setClass(this@MainActivity, BoundService::class.java)
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)
        Log.d(TAG, "onResume: ${myBoundService?.number}")
    }

    private var myBoundService: BoundService? = null
    private var myAIDLService: ICommonPlayMusic? = null

    private val serviceConnection: ServiceConnection =
            object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                    val Binder : BoundService.MyBinder = service as BoundService.MyBinder
//                    myBoundService = Binder.getBoundService()
                   myAIDLService = ICommonPlayMusic.Stub.asInterface(service)
                    Log.d(TAG, "onServiceConnected: ")
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    myBoundService = null
                    Log.d(TAG, "onServiceDisconnected: ")
                }

            }
    val broadcastR: BroadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive: ")
            if(intent?.action == ACTION_COMPLETE_DOWNLOARD) {
                binding.btnRandomButton.text = intent?.getStringExtra(EXTRA_DATA)
            }
        }

    }
}