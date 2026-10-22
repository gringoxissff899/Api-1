package com.vitorioso.external;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import java.util.*;

/** Independent training arena: no reading or modification of other apps. */
public class TrainingActivity extends Activity {
  Arena arena;
  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL);
    TextView heading = new TextView(this); heading.setText("TREINO DE MIRA • DEMONSTRAÇÃO\nNão altera o Free Fire MAX"); heading.setTextSize(17); heading.setPadding(18,16,10,10); root.addView(heading);
    arena = new Arena(); root.addView(arena,new LinearLayout.LayoutParams(-1,0,1));
    LinearLayout switches=new LinearLayout(this); switches.setOrientation(LinearLayout.VERTICAL);
    addSwitch(switches,"Aim Assist",true,0); addSwitch(switches,"ESP Box",true,1);
    addSwitch(switches,"ESP Line",true,2); addSwitch(switches,"ESP Antena",true,3);
    addSwitch(switches,"ESP Distância",true,4);
    root.addView(switches);
    TextView fovLabel=new TextView(this);fovLabel.setText("FOV: 180");root.addView(fovLabel);
    SeekBar fov=new SeekBar(this);fov.setMax(350);fov.setProgress(180);root.addView(fov);
    fov.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){public void onProgressChanged(SeekBar s,int v,boolean u){arena.fov=Math.max(30,v);fovLabel.setText("FOV: "+arena.fov);}public void onStartTrackingTouch(SeekBar s){}public void onStopTrackingTouch(SeekBar s){}});
    TextView smoothLabel=new TextView(this);smoothLabel.setText("Suavidade: 20%");root.addView(smoothLabel);
    SeekBar smooth=new SeekBar(this);smooth.setMax(100);smooth.setProgress(20);root.addView(smooth);
    smooth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){public void onProgressChanged(SeekBar s,int v,boolean u){arena.smooth=v/100f;smoothLabel.setText("Suavidade: "+v+"%");}public void onStartTrackingTouch(SeekBar s){}public void onStopTrackingTouch(SeekBar s){}});
    setContentView(root);
  }
  void addSwitch(LinearLayout parent,String label,boolean checked,int which){Switch s=new Switch(this);s.setText(label);s.setChecked(checked);s.setPadding(18,0,18,0);parent.addView(s);s.setOnCheckedChangeListener((b,v)->{switch(which){case 0:arena.aim=v;break;case 1:arena.box=v;break;case 2:arena.line=v;break;case 3:arena.antenna=v;break;case 4:arena.distance=v;break;}});}
  class Arena extends View {
    final Paint paint=new Paint(3); final float[] x={.22f,.72f,.43f,.83f}, y={.23f,.32f,.62f,.78f};
    boolean aim=true,box=true,line=true,antenna=true,distance=true; int fov=180;float smooth=.2f;float aimX=-1,aimY=-1;long last=System.nanoTime();
    Arena(){super(TrainingActivity.this);setBackgroundColor(Color.rgb(16,20,31));}
    @Override protected void onDraw(Canvas c){super.onDraw(c);float w=getWidth(),h=getHeight(),cx=w/2,cy=h/2;
      long now=System.nanoTime();float dt=Math.min(.05f,(now-last)/1e9f);last=now;
      paint.setStyle(Paint.Style.STROKE);paint.setStrokeWidth(2);paint.setColor(0x66FFD54F);c.drawCircle(cx,cy,fov,paint);
      float tx=cx,ty=cy,best=fov;
      for(int i=0;i<x.length;i++){
        float px=(x[i]+.035f*(float)Math.sin(now/1e9f*(.6+i*.17)+i))*w;
        float py=(y[i]+.025f*(float)Math.cos(now/1e9f*(.8+i*.13)+i))*h;
        float d=(float)Math.hypot(px-cx,py-cy);if(d<best){best=d;tx=px;ty=py;}
        paint.setColor(0xFFFFD54F);paint.setStrokeWidth(3);paint.setStyle(Paint.Style.STROKE);
        if(box)c.drawRect(px-20,py-36,px+20,py+36,paint);
        if(line)c.drawLine(cx,h,px,py,paint);
        if(antenna){c.drawLine(px,py-36,px,py-68,paint);c.drawCircle(px,py-72,4,paint);}
        if(distance){paint.setStyle(Paint.Style.FILL);paint.setTextSize(24);c.drawText(((int)d)+" px",px-28,py-80,paint);}
      }
      if(aim){if(aimX<0){aimX=cx;aimY=cy;}float t=Math.min(1,smooth*dt*60);aimX+=(tx-aimX)*t;aimY+=(ty-aimY)*t;}
      else{aimX=cx;aimY=cy;}
      paint.setColor(0xFF4DD0E1);paint.setStrokeWidth(3);paint.setStyle(Paint.Style.STROKE);
      c.drawCircle(aimX,aimY,11,paint);c.drawLine(aimX-17,aimY,aimX+17,aimY,paint);c.drawLine(aimX,aimY-17,aimX,aimY+17,paint);
      postInvalidateDelayed(33);
    }
  }
}
