package com.gavin.pushnotification.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gavin.pollnotification.R;
import com.gavin.pushnotification.service.WakeService;
import com.gavin.pushnotification.utils.SystemTools;

public class MainActivity extends Activity {
	
	
	private static final String TAG = "MainActivity";
	
	private ServiceConnection sc;
	
	private Thread mThread = null;

	
	private Button bindBtn;
	
	private Button unBindBtn;
	
	private Button startBtn;
	
	
	private Button stopBtn;
	
	private Button threadBtn;
	
	private Boolean isBind = false;
	
	private boolean runFlag = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		
		startBtn = (Button)findViewById(R.id.startBtn);
		
		stopBtn = (Button)findViewById(R.id.stopBtn);
		

		
		
		startBtn.setOnClickListener(clickListener);
		
		stopBtn.setOnClickListener(clickListener);
		

		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	View.OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int clickId = v.getId();
			Intent intentService = new Intent(MainActivity.this, WakeService.class);  
			intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
			intentService.setAction("gavinwen");  
			switch (clickId) {
//				case R.id.bindBtn:
//					isBind =  bindService(intentService, sc, Context.BIND_AUTO_CREATE);
//					break;
					
//				case R.id.unBindBtn:
//					if(isBind == true){
//						Log.i(TAG, "call unbind btn");
//						unbindService(sc);
//						isBind=false;
//					}
//					break;
					
				case R.id.startBtn:
					
					startService(intentService);
					SystemTools.showTosat(MainActivity.this, "服务开启");
					break;
					
				case R.id.stopBtn:
					
					stopService(intentService);
					SystemTools.showTosat(MainActivity.this, "服务停止");
					break;
					
//				case R.id.threadBtn:
//					Log.i(TAG, "acvivity thread");
//					Thread thread = getThread();
//					thread.start();
//					break;
				default:
					break;
			}
			
		}
	};
	
	
	private Thread getThread(){
		if(mThread==null){
			mThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					int i=0;
					while(runFlag){
						
						try {
							Thread.sleep(2000);
							Log.i(String.valueOf(Thread.currentThread().getId()), String.valueOf(i++));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			});
		}
		return mThread;
	} 
	
	

}
