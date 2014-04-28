package com.sonymobile.smartconnect.extension.controlsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewDebug.FlagToString;

/**
 * The extension receiver receives the extension intents and starts the
 * extension service when it arrives.
 */
public class ExtensionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        Log.d(SampleExtensionService.LOG_TAG, "onReceive: " + intent.getAction());
        
        //checks if the user clicks on an image on the smart watch
        if(intent.getAction() == "com.sonyericsson.extras.aef.control.OBJECT_CLICK_EVENT")
        {
    	 intent.setClass(context, LocationRetriever.class);
    	 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(intent);
         
         //launches the ControlExtension to provide feedback by changing the layout
         intent.setClass(context, SampleExtensionService.class);
         context.startService(intent);
         }
        else
        {	
        	  intent.setClass(context, SampleExtensionService.class);
              context.startService(intent);
       }
      
    }
}
