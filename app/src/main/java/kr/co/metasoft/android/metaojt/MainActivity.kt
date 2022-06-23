package kr.co.metasoft.android.metaojt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kr.co.metasoft.android.metaojt.global.NetworkConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val connection = NetworkConnection(applicationContext)
//        connection.observe(this) { isConnected ->
//            if (isConnected) {
//                Log.d("fmtl", "네트워크 연결됨")
//                Log.d("fmtl", FirebaseMessaging.getInstance().token.toString())
//                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        Log.w("fmtl", "Fetching FCM registration token failed", task.exception)
//                        return@OnCompleteListener
//                    }
//
//                    // Get new FCM registration token
//                    val token = task.result
//
//                    // Log and toast
//                    val msg = getString(R.string.msg_token_fmt, token)
//                    Log.d("fmtl", msg)
//                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//                })
//            } else {
//                Log.d("fmtl", "네트워크 연결X")
////                    this.findNavController(R.id.nav_host_fragment)
////                        .navigate(R.id.networkErrorFragment)
//            }
//            }


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("fmtl", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("fmtl", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

    }
}