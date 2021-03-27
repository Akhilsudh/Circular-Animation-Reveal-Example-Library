# Circular-Animation-Reveal-Example-Library

This is an Example app that demonstrates how simple Circular animation transitions can be accomplished from one activity to another on clicking a button. This Example uses an FAB as the button that causes the transition to the second activity.

## Screenshots
<!--
![Source Activity](http://i.imgur.com/9AqqpfY.png)
![Transition Animation 1](http://i.imgur.com/Vy3Z0bt.png)
![Transition Animation 2](http://i.imgur.com/OfeNQZE.png)
![Transition Animation 3](http://i.imgur.com/YIwDoHJ.png)
![Transition Animation 4](http://i.imgur.com/RJndOqT.png)
![Destination Activity](http://i.imgur.com/V97E4rU.png)
-->
<img src="http://i.imgur.com/9AqqpfY.png" alt="Source Activity" width="300px"> <img src="http://i.imgur.com/Vy3Z0bt.png" alt="Transition Animation 1" width="300px"> <img src="http://i.imgur.com/OfeNQZE.png" alt="Transition Animation 2" width="300px"> <img src="http://i.imgur.com/YIwDoHJ.png" alt="Transition Animation 3" width="300px"> <img src="http://i.imgur.com/RJndOqT.png" alt="Transition Animation 4" width="300px"> <img src="http://i.imgur.com/V97E4rU.png" alt="Destination Activity" width="300px">

## Implementation
We first define the layout xml file for the destination activity as follows:

    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/destination_layout"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:background="#FFC107">
          <!-- The actual layout for the destination Activity Goes here -->
          <TextView
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="This is the activity that is revealed!!!" />
    </FrameLayout>

We employ a frame layout here, but this can be implimented as any other type of layout depending on your need.
In the Activity we are going to transition to we will override onCreate() as follows:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        destinationFrameLayout = (FrameLayout) findViewById(R.id.destination_layout);
        if (savedInstanceState == null) {
            destinationFrameLayout.setVisibility(View.INVISIBLE);
            ViewTreeObserver viewTreeObserver = destinationFrameLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealTransition(); //Function To start the animation
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

In the above snippet, we see that we are placing the circularRevealTransition() into a OnGlobalLayoutListener, since we need the view to be drawn for the animation.
Finally we impliment the circularRevealTrandition() function as follows:

    private void circularRevealTransition() {
        int X = 9 * destinationFrameLayout.getWidth()/10; //The X coordinate for the initial position
        int Y = 9 * destinationFrameLayout.getHeight()/10; //The Y coordinate for the initial position
        int Duration = 500;  //Duration for the animation

        float finalRadius = Math.max(destinationFrameLayout.getWidth(), destinationFrameLayout.getHeight()); //The final radius must be the end points of the current activity

        // create the animator for this view, with the start radius as zero
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(destinationFrameLayout, X, Y, 0, finalRadius);
        circularReveal.setDuration(Duration);

        // set the view visible and start the animation
        destinationFrameLayout.setVisibility(View.VISIBLE);
        // start the animation
        circularReveal.start();
    }



## License
    The MIT License (MIT)

    Copyright (c) 2014-2016 Akhil Sudhakaran
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
