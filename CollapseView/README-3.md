## 3.CollapseView

### 介绍
* CollapseView 折叠/展开
* FlowLayout 流式布局


### 参考资料
* <a target="_blank" href="http://blog.csdn.net/lfdfhl/article/details/51671038">http://blog.csdn.net/lfdfhl/article/details/51671038</a>

* <a target="_blank" href="http://www.stay4it.com/course/24">http://www.stay4it.com/course/24</a>

### 效果图
![DataBinding-art](https://raw.githubusercontent.com/why168/AndroidProjects/master/Art/CollapseView-art.gif)


### 示例代码

```java
/**
 * 折叠/展开ViewGroup
 *
 * @author Edwin.Wu
 * @version 2016/06/05 上午1:57
 * @since JDK1.8
 */
public class CollapseView extends LinearLayout {
    private final Context mContext;
    private long duration = 555;//展开/折叠的时间(s)
    private TextView mNumberTextView;
    private TextView mTitleTextView;
    private RelativeLayout mTitleRelativeLayout;
    private RelativeLayout mContentRelativeLayout;
    private ImageView mArrowImageView;
    private int parentWidthMeasureSpec;
    private int parentHeightMeasureSpec;

    public CollapseView(Context context) {
        super(context);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.collapse_layout, this);
        init();
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.collapse_layout, this);
        init();
    }

    public CollapseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.collapse_layout, this);
        init();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec = widthMeasureSpec;
        parentHeightMeasureSpec = heightMeasureSpec;
    }

    private void init() {
        mNumberTextView = (TextView) findViewById(R.id.numberTextView);
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mTitleRelativeLayout = (RelativeLayout) findViewById(R.id.titleRelativeLayout);
        mContentRelativeLayout = (RelativeLayout) findViewById(R.id.contentRelativeLayout);
        mArrowImageView = (ImageView) findViewById(R.id.arrowImageView);
        mTitleRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateArrow();
            }
        });

        collapse(mContentRelativeLayout);
    }

    public void setNumber(String number) {
        if (!TextUtils.isEmpty(number)) {
            mNumberTextView.setText(number);
        }
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitleTextView.setText(title);
        }
    }

    public void setContent(int resID) {
        View view = LayoutInflater.from(mContext).inflate(resID, null);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);


        mContentRelativeLayout.addView(view);
    }

    private void rotateArrow() {
        int angle = 0;
        if (mArrowImageView.getTag() == null || mArrowImageView.getTag().equals(true)) {
            mArrowImageView.setTag(false);
            angle = -180;
            //TODO 展开
            expand(mContentRelativeLayout);
        } else {
            angle = 0;
            mArrowImageView.setTag(true);
            //TODO 折叠
            collapse(mContentRelativeLayout);
        }
        mArrowImageView.animate().setDuration(duration).rotation(angle);
    }

    /**
     * 折叠
     *
     * @param view 视图
     */
    private void collapse(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.e("TAG", "interpolatedTime = " + interpolatedTime);
                if (interpolatedTime == 1) {
                    mContentRelativeLayout.setVisibility(GONE);
                } else {
                    view.getLayoutParams().height = measuredHeight - (int) (measuredHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * 展开
     *
     * @param view 视图
     */
    private void expand(final View view) {
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredHeight = view.getMeasuredHeight();
        view.setVisibility(View.VISIBLE);

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.e("TAG", "interpolatedTime = " + interpolatedTime);
                if (interpolatedTime == 1) {
                    view.getLayoutParams().height = measuredHeight;
                } else {
                    view.getLayoutParams().height = (int) (measuredHeight * interpolatedTime);
                }
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }

        };
        animation.setDuration(duration);
        view.startAnimation(animation);
    }
}
```