package com.gavin.pushnotification.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.gavin.pollnotification.R;
import com.gavin.pushnotification.activity.MainActivity;


/**
 * 唤醒服务
 * @author gavinwen
 *
 */
public class WakeService extends Service{
	
	private static Binder mBinder;
	
	private static String TAG = "WakeService";
	
	private Thread mThread = null;
	
	private boolean runFlag = true;
	
	
	private static Socket socket=null;
	
	
	private Context context;
	
	
	class MyBinder extends Binder{
		
		public Service getLocalService(){
			return WakeService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		if(mBinder == null){
			mBinder = new MyBinder();
		}
		return mBinder;
	}
	
	@Override
	public void onCreate(){
		Log.i(TAG, "LocalService onCreate");
		super.onCreate();
		

		
	}
	
	@Override
	public void onStart(Intent intent,int startId){
		Log.i(TAG, "LocalService onStart");
		super.onStart(intent, startId);
		
		Thread thread = getThread();
		if(thread.isAlive()){
			Log.i(TAG, "thread is aleady started");
		}else{
			thread.start();
		}
		
		
		
		
	}
	
	
	@Override
	public void onDestroy(){
		Log.i(TAG, "LocalService onDestroy");
		super.onDestroy();
		runFlag = false;
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		Log.i(TAG, "LocalService onStartCommand");
		Log.i(TAG, "action is "+intent.getAction());
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	private Thread getThread(){
		
		if(mThread==null){
			mThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					//请求服务器
					doRequest();
					
				}
			});
		}
		return mThread;
	}
	
	//生成通知
	private void doNotification(String title,String msg){
		
		
		Notification notification = new Notification(R.drawable.ic_launcher,"通知",System.currentTimeMillis());
		
		Intent intent = new Intent(this,MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		notification.setLatestEventInfo(this, title, msg, pendingIntent);
		notification.defaults=Notification.DEFAULT_ALL;
		
		startForeground(1, notification);
		


		
	}
	
	//网络请求
	private void doRequest(){
			Socket socket = getSocket();
			
			if (socket.isConnected()){
				Log.i(TAG, "soceket connected");
				InputStream is = null;
				//持续请求服务器
				while (runFlag) {
					try {
						
						is = socket.getInputStream();
						byte[] resp = new byte[100];
						is.read(resp);
						String res = new String(resp);
						Log.i(TAG, res);
						String[]  str = res.split("&");
						doNotification(str[0], str[1]);
					} catch (Exception e) {
						e.printStackTrace();
						if(is != null)
							try {
								is.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
					} finally {
						Log.i(TAG, "over");
					}
			}
					
				}
			

		
		
	}
	
	
	
	
	
	private Context getContext(){
		if(context == null){
			context = this;
		}
		return context;
	}
	
	private Socket getSocket(){
		if(socket==null){
			String ip = "192.168.173.1";
			int port = 10000;
			try {
				socket=new Socket(ip,port);
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return socket;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
