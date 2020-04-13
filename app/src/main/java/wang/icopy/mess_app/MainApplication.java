package wang.icopy.mess_app;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.alibaba.ha.adapter.AliHaAdapter;
import com.alibaba.ha.adapter.AliHaConfig;
import com.alibaba.ha.adapter.Plugin;
import com.alibaba.ha.adapter.service.tlog.TLogLevel;
import com.alibaba.ha.adapter.service.tlog.TLogService;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

/** @author lizhengguang */
public class MainApplication extends Application {

  private static final String TAG = "MainApplication";

  @Override
  public void onCreate() {
    super.onCreate();
    initHa();
    initCloudChannel(this);
  }

  private void initHa() {
    Log.e("ha", "init");
    AliHaConfig config = new AliHaConfig();
    config.appKey = "29094943"; // appkey
    config.appVersion = "1.01"; // 应用的版本号信箱
    config.appSecret = "6f0abd7c86acc8135eba4156965533f7"; // appsecret
    config.channel = "mqc_test"; // 应用的渠道号标记，自定义
    config.userNick = "mess_app";
    config.application = this;
    config.context = getApplicationContext();
    config.isAliyunos = false; // 是否为yunos
    config.rsaPublicKey =
        "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3DY03dYBA4tFI16pUwvPbFnBbzxdE7senNDG7ItxBzOSShpEncAJYtn9aEi9qKIfDhnK1hfAPMag+y7CcwmuF/6f6VhLvY/S0WFAE5KWPw3Ka/OXf0fPYQoKsbfw+gAmPztjRGO/CB/A+2wjGm1Apt7EkxGfkbhnu/NTLb4sIsQIDAQAB"; // tlog公钥， 在控制台下载
    // aliyun-emas-services.json文件，文件内的appmonitor.tlog.rsaSecret字段即为公钥信息（文件下载方式：在
    // EMAS控制台-> 应用管理  找到对应的应用，点击应用所在区块右上角菜单内的“配置下载”），必填
    AliHaAdapter.getInstance().addPlugin(Plugin.tlog);
    AliHaAdapter.getInstance().openDebug(true);
    AliHaAdapter.getInstance().start(config);
  }

  /**
   * 初始化云推送通道
   *
   * @param applicationContext
   */
  private void initCloudChannel(final Context applicationContext) {
    PushServiceFactory.init(applicationContext);
    CloudPushService pushService = PushServiceFactory.getCloudPushService();
    pushService.register(
        applicationContext,
        new CommonCallback() {
          @Override
          public void onSuccess(String response) {
            Log.d(TAG, "init cloudchannel success");
          }

          @Override
          public void onFailed(String errorCode, String errorMessage) {
            // 设置可上传日志级别，默认 e 级别
            TLogService.updateLogLevel(TLogLevel.INFO);
            String TAG = "initCloudChannel";
            String MODEL = "初始化云推送通道";
            TLogService.logv(
                MODEL,
                TAG,
                "init cloudchannel failed -- errorcode:"
                    + errorCode
                    + " -- errorMessage:"
                    + errorMessage);
          }
        });
  }
}
