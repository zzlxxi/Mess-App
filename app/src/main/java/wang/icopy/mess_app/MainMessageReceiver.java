package wang.icopy.mess_app;

import android.content.Context;
import com.alibaba.ha.adapter.service.tlog.TLogLevel;
import com.alibaba.ha.adapter.service.tlog.TLogService;
import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

/** @author lizhengguang */
public class MainMessageReceiver extends MessageReceiver {
  // 消息接收部分的LOG_TAG
  public static final String REC_TAG = "receiver";

  @Override
  public void onNotification(
      Context context, String title, String summary, Map<String, String> extraMap) {
    // TODO 处理推送通知
    // 设置可上传日志级别，默认 e 级别
    TLogService.updateLogLevel(TLogLevel.INFO);
    String MODEL = "MainMessageReceiver";
    TLogService.logv(
        MODEL,
        "MyMessageReceiver",
        "Receive notification, title: "
            + title
            + ", summary: "
            + summary
            + ", extraMap: "
            + extraMap);
  }

  @Override
  public void onMessage(Context context, CPushMessage cPushMessage) {
    String MODEL = "MainMessageReceiver";
    TLogService.logv(
        MODEL,
        "MyMessageReceiver",
        "onMessage, messageId: "
            + cPushMessage.getMessageId()
            + ", title: "
            + cPushMessage.getTitle()
            + ", content:"
            + cPushMessage.getContent());
  }

  @Override
  public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
    String MODEL = "MainMessageReceiver";
    TLogService.logv(
        MODEL,
        "MyMessageReceiver",
        "onNotificationOpened, title: "
            + title
            + ", summary: "
            + summary
            + ", extraMap:"
            + extraMap);
  }

  @Override
  protected void onNotificationClickedWithNoAction(
      Context context, String title, String summary, String extraMap) {
    String MODEL = "MainMessageReceiver";
    TLogService.logv(
        MODEL,
        "MyMessageReceiver",
        "onNotificationClickedWithNoAction, title: "
            + title
            + ", summary: "
            + summary
            + ", extraMap:"
            + extraMap);
  }

  @Override
  protected void onNotificationReceivedInApp(
      Context context,
      String title,
      String summary,
      Map<String, String> extraMap,
      int openType,
      String openActivity,
      String openUrl) {
    String MODEL = "MainMessageReceiver";
    TLogService.logv(
        MODEL,
        "MyMessageReceiver",
        "onNotificationReceivedInApp, title: "
            + title
            + ", summary: "
            + summary
            + ", extraMap:"
            + extraMap
            + ", openType:"
            + openType
            + ", openActivity:"
            + openActivity
            + ", openUrl:"
            + openUrl);
  }

  @Override
  protected void onNotificationRemoved(Context context, String messageId) {
    TLogService.updateLogLevel(TLogLevel.INFO);
    String MODEL = "MainMessageReceiver";
    TLogService.logv(MODEL, "MyMessageReceiver", "onNotificationRemoved");
  }
}
