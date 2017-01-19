package com.github.why168.multichannelbuild.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author Edwin.Wu
 * @version 2017/1/18 14:51
 * @since JDK1.8
 */
public class SwipeView extends FrameLayout {

    private View contentView;
    private View menuView;
    private int contentWidth, menuWidth, viewHeight;
    private ViewDragHelper dragHelper;
    private int firstX;
    private int firstY;
    private boolean canOpen = true;

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        dragHelper = ViewDragHelper.create(this, callBack);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        contentView = this.getChildAt(0);
        menuView = this.getChildAt(1);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentWidth = contentView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
        viewHeight = contentView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuView.layout(contentWidth, 0, contentWidth + menuWidth, viewHeight);
    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == contentView || child == menuView;
        }

        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentView) {
                if (left > 0) {
                    left = 0;
                } else if (left < -menuWidth) {
                    left = -menuWidth;
                }
            } else {
                if (left > contentWidth) {
                    left = contentWidth;
                } else if (left < contentWidth - menuWidth) {
                    left = contentWidth - menuWidth;
                }
            }
            return left;
        }

        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (changedView == contentView) {
                menuView.layout(menuView.getLeft() + dx, 0, menuView.getRight() + dx, menuView.getBottom());
            } else {
                contentView.layout(contentView.getLeft() + dx, 0, contentView.getRight() + dx, contentView.getBottom());
            }


        }

        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (contentView.getLeft() < -menuWidth / 2) {
                open();
            } else {
                close();
            }
        }

        public int getViewHorizontalDragRange(View child) {
            return menuWidth;
        }
    };

    public void close() {
        dragHelper.smoothSlideViewTo(contentView, 0, 0);
        ViewCompat.postInvalidateOnAnimation(this);

        if (onStatuChangeListener != null) {
            onStatuChangeListener.onClose(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void open() {
        dragHelper.smoothSlideViewTo(contentView, -menuWidth, 0);
        ViewCompat.postInvalidateOnAnimation(this);

        if (onStatuChangeListener != null) {
            onStatuChangeListener.onOpen(this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = (int) event.getRawX();
                firstY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getRawX();
                int moveY = (int) event.getRawY();
                int distanceX = Math.abs(moveX - firstX);
                int distanceY = Math.abs(moveY - firstY);
                if (distanceX > distanceY) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                firstX = moveX;
                firstY = moveY;
                break;
            default:
                break;
        }
        if (canOpen) {
            dragHelper.processTouchEvent(event);
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (onStatuChangeListener != null) {
                canOpen = onStatuChangeListener.onDown(this);
            }
        }
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    public interface OnStatuChangeListener {
        public void onOpen(SwipeView GradleView);

        public void onClose(SwipeView GradleView);

        public boolean onDown(SwipeView GradleView);
    }

    private OnStatuChangeListener onStatuChangeListener;

    public void setOnStatuChangeListener(OnStatuChangeListener onStatuChangeListener) {
        this.onStatuChangeListener = onStatuChangeListener;
    }

}
