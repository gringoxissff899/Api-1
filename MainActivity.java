package com.vitorioso.external;
import android.app.*;import android.os.*;import android.content.*;import android.net.Uri;import android.provider.Settings;import android.view.*;import android.webkit.*;import android.widget.*;
public class MainActivity extends Activity {
  WebView web;
  @Override public void onCreate(Bundle b){super.onCreate(b);
    LinearLayout layout=new LinearLayout(this);layout.setOrientation(LinearLayout.VERTICAL);
    LinearLayout tools=new LinearLayout(this);
    Button overlay=new Button(this);overlay.setText("Menu flutuante");tools.addView(overlay,new LinearLayout.LayoutParams(0,-2,1));
    Button game=new Button(this);game.setText("Abrir FF MAX");tools.addView(game,new LinearLayout.LayoutParams(0,-2,1));
    Button training=new Button(this);training.setText("Treinar Aim + ESP");layout.addView(training);training.setOnClickListener(v->startActivity(new Intent(this,TrainingActivity.class)));
    layout.addView(tools);web=new WebView(this);web.getSettings().setJavaScriptEnabled(true);web.getSettings().setDomStorageEnabled(true);
    web.setWebViewClient(new WebViewClient());layout.addView(web,new LinearLayout.LayoutParams(-1,0,1));setContentView(layout);
    web.loadUrl("file:///android_asset/index.html");
    overlay.setOnClickListener(v->{if(!Settings.canDrawOverlays(this)){startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:"+getPackageName())));Toast.makeText(this,"Autorize e toque novamente",Toast.LENGTH_LONG).show();}else{startForegroundService(new Intent(this,FloatService.class));}});
    game.setOnClickListener(v->openGame());
  }
  void openGame(){Intent i=getPackageManager().getLaunchIntentForPackage("com.dts.freefiremax");if(i!=null)startActivity(i);else Toast.makeText(this,"Free Fire MAX não encontrado",Toast.LENGTH_LONG).show();}
  @Override public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}
