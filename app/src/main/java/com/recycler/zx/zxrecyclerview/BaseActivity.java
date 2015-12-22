
  
package com.recycler.zx.zxrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.recycler.zx.zxrecyclerview.utils.ActivityController;


public class BaseActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		ActivityController.addActivity(this);

		//去掉标题栏和通知栏
		//设置全屏模式
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//去除标题栏
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	/*public void setContentView(View view) {
		//代码设置横竖屏，也可以在menifest中设置
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
		super.setContentView(view);
	}*/




	public void sendActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
	public void sendActivityFlags(Context context, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy(); 
		ActivityController.removeActivity(this);
	}
}
  