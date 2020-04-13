package wang.icopy.mess_app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.CPushMessage;

/** @author lizhengguang */
public class NotificationService extends Service {
  public static final String TAG = "NotificationService";

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    String action = intent.getAction();
    if (action.equals("your notification click action")) {
      // 添加您的通知点击处理逻辑
      CPushMessage message = intent.getParcelableExtra("message key"); // 获取message
      PushServiceFactory.getCloudPushService()
          .clickMessage(message); // 上报通知点击事件，点击事件相关信息可以在推送控制台查看到
    } else if (action.equals("your notification delete action")) {
      // 添加您的通知删除处理逻辑
      CPushMessage message = intent.getParcelableExtra("message key"); // 获取message
      PushServiceFactory.getCloudPushService()
          .dismissMessage(message); // 上报通知删除事件，点击事件相关信息可以在推送控制台查看到
    }
    return Integer.valueOf(0);
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
