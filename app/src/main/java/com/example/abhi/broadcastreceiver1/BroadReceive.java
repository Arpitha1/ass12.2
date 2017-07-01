package com.example.abhi.broadcastreceiver1;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class BroadReceive extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        try{
            if(bundle!=null){
                final Object[] Obj = (Object[])bundle.get("Hello");
                for(int i=0;i<Obj.length;i++){
                    SmsMessage current = SmsMessage.createFromPdu((byte[])Obj[i]);
                    String phno = current.getDisplayOriginatingAddress();
                    String sender=phno;
                    String message = current.getDisplayMessageBody();
                    Log.i("SmsReceiver","senderNum: "+ sender+"; message: "+message);
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,"senderNum: "+sender+", message: "+message,duration);
                    toast.show();
                }
            }
        }catch (Exception e){
            Log.e("SmsReceiver","Exception smsReceiver"+e);
        }
    }
}
