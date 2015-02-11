/*
 * @author: Nagabhushan S B
 */

package com.app.amazingshooter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class MainActivity extends Activity
{
   private GestureDetector gestureDetector; 
   private BulletView cannonView;

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState); 
      setContentView(R.layout.main); 
      

      cannonView = (BulletView) findViewById(R.id.cannonView);

      gestureDetector = new GestureDetector(MainActivity.this, myGestureListener);
      
     
      setVolumeControlStream(AudioManager.STREAM_MUSIC);

     
      
   }
   @Override
   public void onPause()
   {
      super.onPause(); 
      cannonView.stopGame(); 
   }
   @Override
   protected void onDestroy()
   {
      super.onDestroy();
      cannonView.releaseResources();
   } 
   @Override
   public boolean onTouchEvent(MotionEvent event)
   {
     
      int action = event.getAction();

     
      if (action == MotionEvent.ACTION_DOWN ||
         action == MotionEvent.ACTION_MOVE)
      {
         cannonView.alignCannon(event); 
      } 
      return gestureDetector.onTouchEvent(event);
   } 
   SimpleOnGestureListener myGestureListener = new SimpleOnGestureListener()
   {
     
      @Override
      public boolean onDoubleTap(MotionEvent e)
      {
         cannonView.fireCannonball(e); 
         return true;
      } 
   }; 
} 

