package com.mohitmamoria.edittextkeyboardissue;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by mohit on 12/14/15.
 */
public class ReplyLayout extends LinearLayout {


    public ReplyLayout(Context context) {
        super(context);
    }

    public ReplyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReplyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ReplyLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d("EditText_Focus", "in the layout file " + event.getAction());
        LocalBroadcastManager
                .getInstance(this.getContext())
                .sendBroadcast(new Intent("REMOVE_NOTIFICATION_VIEW"));
        return super.dispatchKeyEvent(event);
    }
}
