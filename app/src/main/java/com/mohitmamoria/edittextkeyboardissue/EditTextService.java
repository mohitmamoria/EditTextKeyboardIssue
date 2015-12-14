package com.mohitmamoria.edittextkeyboardissue;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mohit on 12/14/15.
 */
public class EditTextService extends Service {
    private WindowManager windowManager;
    private View cardView;

    public EditTextService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // get Window Manager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // get Layout Inflater
        LayoutInflater layoutInflater = (LayoutInflater) this.getApplicationContext().getSystemService(
                LAYOUT_INFLATER_SERVICE
        );

        // the floating view
        cardView = layoutInflater.inflate(R.layout.card, null);

        EditText replyBox = (EditText) cardView.findViewById(R.id.notification_reply_box);
        replyBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.d("EditText_Focus", "has focus");
                } else {
                    Log.d("EditText_Focus", "no focus");
                }
            }
        });

        WindowManager.LayoutParams params = this.getParamsThatWorksForKeyboard();
//        WindowManager.LayoutParams params = this.getParamsThatWorksForTouchEvents();

        windowManager.addView(cardView, params);
    }

    private WindowManager.LayoutParams getParamsThatWorksForTouchEvents() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 100;
        params.y = 100;

        return params;
    }

    private WindowManager.LayoutParams getParamsThatWorksForKeyboard() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 100;
        params.y = 100;

        return params;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
