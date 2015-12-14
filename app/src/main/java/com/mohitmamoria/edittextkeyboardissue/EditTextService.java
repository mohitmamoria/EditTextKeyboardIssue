package com.mohitmamoria.edittextkeyboardissue;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

//        EditText replyBox = (EditText) cardView.findViewById(R.id.notification_reply_box);
//        replyBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("EditText_Focus", "clicked");
//                cardView.setLayoutParams(EditTextService.this.getParamsThatWorksForKeyboard());
//            }
//        });
//        replyBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//
//                if (hasFocus) {
//                    Log.d("EditText_Focus", "has focus");
//                    cardView.setLayoutParams(EditTextService.this.getParamsThatWorksForKeyboard());
//                    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
//                } else {
//                    Log.d("EditText_Focus", "no focus");
//                    imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
//                }
//            }
//        });

        WindowManager.LayoutParams params = this.getParamsThatWorksForKeyboard();
//        WindowManager.LayoutParams params = this.getParamsThatWorksForTouchEvents();

        LocalBroadcastManager
                .getInstance(this.getApplicationContext())
                .registerReceiver(
                        new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {
                                Log.d("EditText_Focus", "received intent");

                                cardView.clearFocus();
//                                cardView.setLayoutParams(EditTextService.this.getParamsThatWorksForTouchEvents());

                                if(cardView.isAttachedToWindow()) {
                                    cardView.setVisibility(View.GONE);
//                                    windowManager.removeView(cardView);
                                }
//                                ((RelativeLayout) cardView).removeView(replyBox);
                                EditTextService.this.onDestroy();
                            }
                        },
                        new IntentFilter("REMOVE_NOTIFICATION_VIEW")
                );
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
        params.y = 400;

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
        params.y = 400;

        return params;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getApplicationContext(), EditTextService.class));
    }
}
