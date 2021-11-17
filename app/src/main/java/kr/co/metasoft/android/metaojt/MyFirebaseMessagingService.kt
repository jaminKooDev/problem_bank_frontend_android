package kr.co.metasoft.android.metaojt

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // [START receive_message]
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // [START_EXCLUDE]
        // 메시지 데이터 메시지와 알림 메시지에는 두 가지 유형이 있습니다.
        // 데이터 메시지는 앱이 포그라운드에 있든 백그라운드에 있든 onMessageReceived 에서 처리됩니다.
        // 데이터 메시지는 GCM 에서 전통적으로 사용되는 유형입니다.
        // 알림 메시지는 앱이 포그라운드에 있을 때 onMessageReceived 에서만 여기에서 수신됩니다.
        // 앱이 백그라운드에 있을 때 자동으로 생성된 알림이 표시됩니다.
        // 사용자가 알림을 탭하면 앱으로 돌아갑니다.
        // 알림 및 데이터 페이로드를 모두 포함하는 메시지는 알림 메시지로 처리됩니다. Firebase 콘솔은 항상 알림 메시지를 보냅니다.
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage.from}")

        // 메시지에 데이터 페이로드가 포함되어 있는지 확인
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
        }

        // 메시지에 알림 페이로드가 포함되어 있는지 확인
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}