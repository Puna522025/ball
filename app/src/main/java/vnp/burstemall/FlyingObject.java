package vnp.burstemall;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import vnp.burstemall.utilities.ChangePixels;

/**
 * Created by pkapo8 on 9/28/2016.
 */


public class FlyingObject extends ImageView
        implements View.OnTouchListener,
        Animator.AnimatorListener,
        ValueAnimator.AnimatorUpdateListener {

    private FlyingObjectListener mListener;
    private ValueAnimator mAnimator;
    private boolean mPopped;

    public FlyingObject(Context context) {
        super(context);
    }

    public FlyingObject(Context context, int color, int rawHeight, int level, int defaultObject) {
        super(context);

        this.mListener = (FlyingObjectListener) context;

        this.setImageResource(defaultObject);
        this.setColorFilter(color);

        int rawWidth = rawHeight / 2;

        int dpHeight = ChangePixels.pixelsToDp(rawHeight, context);
        int dpWidth = ChangePixels.pixelsToDp(rawWidth, context);
        ViewGroup.LayoutParams params =
                new ViewGroup.LayoutParams(dpWidth, dpHeight);
        setLayoutParams(params);

        setOnTouchListener(this);
    }

    public void releaseBalloon(int screenHeight, int duration) {
        mAnimator = new ValueAnimator();
        mAnimator.setDuration(duration);
        mAnimator.setFloatValues(screenHeight, 0f);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addListener(this);
        mAnimator.addUpdateListener(this);
        mAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (!mPopped) {
            setY((Float) animation.getAnimatedValue());
        }
    }

    public interface FlyingObjectListener {
        void popBalloon(FlyingObject balloon, boolean touched);
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
//      This means the balloon got to the top of the screen
        if (!mPopped) {
            mListener.popBalloon(this, false);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!mPopped && event.getAction() == MotionEvent.ACTION_DOWN) {
            mListener.popBalloon(this, true);
            mPopped = true;
            mAnimator.cancel();
        }
        return true;
    }

    public void setPopped(boolean popped) {
        this.mPopped = popped;
    }

}
