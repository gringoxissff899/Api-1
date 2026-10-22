package com.vitorioso.external;
import android.app.*;import android.os.*;import android.content.*;import android.graphics.PixelFormat;import android.view.*;import android.widget.*;
public class FloatService extends Service {
 WindowManager wm;View floating;
 @Override public void onCreate(){super.onCreate();
  NotificationManager nm=getSystemService(NotificationManager.class);NotificationChannel channel=new NotificationChannel("float","Menu flutuante",NotificationManager.IMPORTANCE_LOW);nm.createNotificationChannel(channel);
  Notification notification=new Notification.Builder(this,"float").setContentTitle("Vitorioso Auxílios").setContentText("Menu flutuante ativo").setSmallIcon(android.R.drawable.ic_menu_manage).build();startForeground(1,notification);
  wm=(WindowManager)getSystemService(WINDOW_SERVICE);LinearLayout panel=new LinearLayout(this);panel.setOrientation(LinearLayout.VERTICAL);panel.setPadding(14,10,14,10);panel.setBackgroundColor(0xEE171717);
  Button open=new Button(this);open.setText("Abrir painel");panel.addView(open);
  Button game=new Button(this);game.setText("Abrir FF MAX");panel.addView(game);
  Button training=new Button(this);training.setText("Treinar Aim + ESP");panel.addView(training);training.setOnClickListener(v->{Intent i=new Intent(this,TrainingActivity.class);i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);startActivity(i);});
  Button close=new Button(this);close.setText("Fechar menu");panel.addView(close);
  WindowManager.LayoutParams params=new WindowManager.LayoutParams(-2,-2,WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,PixelFormat.TRANSLUCENT);params.gravity=Gravity.TOP|Gravity.START;params.x=20;params.y=180;
  wm.addView(panel,params);floating=panel;
  open.setOnClickListener(v->{Intent i=new Intent(this,MainActivity.class);i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);startActivity(i);});
  game.setOnClickListener(v->{Intent i=getPackageManager().getLaunchIntentForPackage("com.dts.freefiremax");if(i!=null)startActivity(i);else Toast.makeText(this,"FF MAX não instalado",Toast.LENGTH_SHORT).show();});
  close.setOnClickListener(v->stopSelf());
 }
 @Override public int onStartCommand(Intent i,int f,int id){return START_NOT_STICKY;}
 @Override public void onDestroy(){if(wm!=null&&floating!=null)wm.removeView(floating);super.onDestroy();}
 @Override public IBinder onBind(Intent i){return null;}
}
