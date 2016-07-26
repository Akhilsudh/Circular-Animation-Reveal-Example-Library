package com.yellowgeeks.akhilsudhakaran.circularreavealexample;

import android.animation.Animator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

public class DestinationActivity extends AppCompatActivity {

    private FrameLayout destinationFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        destinationFrameLayout = (FrameLayout) findViewById(R.id.destination_layout);

        ////////////////////////////////////////////////////////////////////
        //We call the Transparent layer inorder to show a smooth animation//
        ////////////////////////////////////////////////////////////////////
        if (savedInstanceState == null) {
            destinationFrameLayout.setVisibility(View.INVISIBLE);
            ViewTreeObserver viewTreeObserver = destinationFrameLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealTransition(); //
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            destinationFrameLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            destinationFrameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    private void circularRevealTransition() {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Set the 'X' and 'Y' values for your requirement, Here it is set for the fab being as the source of the circle reveal //
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int X = 9 * destinationFrameLayout.getWidth()/10;
        int Y = 9 * destinationFrameLayout.getHeight()/10;
        int Duration = 500;

        float finalRadius = Math.max(destinationFrameLayout.getWidth(), destinationFrameLayout.getHeight()); //The final radius must be the end points of the current activity

        // create the animator for this view, with the start radius as zero
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(destinationFrameLayout, X, Y, 0, finalRadius);
        circularReveal.setDuration(Duration);

        // set the view visible and start the animation
        destinationFrameLayout.setVisibility(View.VISIBLE);
        // start the animation
        circularReveal.start();
    }
}
