package com.example.fgmobileapp;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

public class JoyStickComponent extends RelativeLayout implements View.OnTouchListener {

    public interface PositionChangedEvent {
        void positionChanged(float aileron, float elevator);
    }
    private PositionChangedEvent onPositionChanged;

    private int _xDelta;
    private int _yDelta;
    float dAileron = 0;
    float dElevator = 0;
    ImageView imageView;

    public PositionChangedEvent getOnPositionChangedEvent() {
        return onPositionChanged;
    }

    public void setOnPositionChangedEvent(PositionChangedEvent onPositionChangedEvent) {
        this.onPositionChanged = onPositionChangedEvent;
    }

    public JoyStickComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Inflate the layout for this fragment
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.joy_stick_view, this, true);

//        imageView = new ImageView(context);
//        imageView.setImageResource(R.drawable.dot);
//
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
//        layoutParams.leftMargin = 0;
//        layoutParams.topMargin = 0;
//        layoutParams.bottomMargin = 0;
//        layoutParams.rightMargin = 0;
//        imageView.setLayoutParams(layoutParams);
        imageView = findViewById(R.id.imageView);
        imageView.setOnTouchListener(this);

        //this.addView(imageView);
    }

    @BindingAdapter("onPositionChange")
    public static void setPositionChangeListener(JoyStickComponent view, PositionChangedEvent oldListener, PositionChangedEvent newListener)
    {
        view.setOnPositionChangedEvent(newListener);
    }

    private float dx; // postTranslate X distance
    private float dy; // postTranslate Y distance
    private float[] matrixValues = new float[9];
    float matrixX = 0; // X coordinate of matrix inside the ImageView
    float matrixY = 0; // Y coordinate of matrix inside the ImageView
    float width = 0; // width of drawable
    float height = 0; // height of drawable

    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = 0;
                layoutParams.bottomMargin = 0;
                view.setLayoutParams(layoutParams);

                if (layoutParams.leftMargin < 0)
                    layoutParams.leftMargin = 0;
                else if (layoutParams.leftMargin > this.getWidth() - imageView.getWidth())
                    layoutParams.leftMargin = this.getWidth() - imageView.getWidth();

                if (layoutParams.topMargin < 0)
                    layoutParams.topMargin = 0;
                else if (layoutParams.topMargin > this.getHeight() - imageView.getHeight())
                    layoutParams.topMargin = this.getHeight() - imageView.getHeight();

                dAileron = layoutParams.leftMargin;
                dElevator = layoutParams.topMargin;

                int y = (this.getWidth() - imageView.getWidth()) / 2;
                if (dAileron < y)
                    dAileron = (y - dAileron) * -1;
                else if (dAileron > y)
                    dAileron = dAileron - y;
                else dAileron = 0;

                int x = (this.getHeight() - imageView.getHeight()) / 2;
                if (dElevator < x)
                    dElevator = (x - dElevator) * -1;
                else if (dElevator > x)
                    dElevator = dElevator - x;
                else dElevator = 0;

                dAileron = dAileron / (float)y;
                dElevator = dElevator / (float)x;
                if (getOnPositionChangedEvent() != null) {
                    getOnPositionChangedEvent().positionChanged(dAileron, dElevator);
                }
                break;
        }
        this.invalidate();
        return true;
    }

}
