# AndroidProjects
* **个人总结归纳-目录大纲**
	1. Data Binding框架MVVM
	2. BaseView
	3. CollapseView
	4. 更新中...
		



## 1.Data Binding框架MVVM

<font>项目源码位置：AndroidProjects/DataBinding目录</font> 

### 介绍

* Data binding 在2015年7月发布的Android Studio v1.3.0 版本上引入，在2016年4月Android Studio v2.0.0 上正式支持。目前为止，Data Binding 已经支持双向绑定了。

* Data Binding 解决了 Android UI 编程中的一个痛点，官方原生支持 MVVM 模型可以让我们在不改变既有代码框架的前提下，非常容易地使用这些新特性；

* Databinding 是一个实现数据和UI绑定的框架，是一个实现 MVVM 模式的工具，有了 Data Binding，在Android中也可以很方便的实现MVVM开发模式。

* Data Binding 是一个support库，最低支持到Android 2.1（API Level 7+）。

* Data Binding 之前，我们不可避免地要编写大量的毫无营养的代码，如 findViewById()、setText()，setVisibility()，setEnabled() 或 setOnClickListener() 等，通过 Data Binding , 我们可以通过声明式布局以精简的代码来绑定应用程序逻辑和布局，这样就不用编写大量的毫无营养的代码了。




### 参考资料

* <a target="_blank" href="https://developer.android.com/topic/libraries/data-binding/index.html">https://developer.android.com/topic/libraries/data-binding/index.html</a>

* <a target="_blank" href="http://tech.vg.no/2015/07/17/android-databinding-goodbye-presenter-hello-viewmodel/">http://tech.vg.no/2015/07/17/android-databinding-goodbye-presenter-hello-viewmodel/</a>

* <a  target="_blank" href="https://segmentfault.com/a/1190000002876984">https://segmentfault.com/a/1190000002876984</a>

* <a target="_blank" href="http://www.imooc.com/learn/719">http://www.imooc.com/learn/719</a>

* <a target="_blank" href="http://www.imooc.com/learn/720">http://www.imooc.com/learn/720</a>

### Gradle配置：

```groovy
android {  
    dataBinding {
        enabled = true
    }
}
```

### 效果图
![DataBinding-art](https://raw.githubusercontent.com/why168/AndroidProjects/master/Art/DataBinding-art.gif)


### 示例代码

```java
/**
 * BaseActivity
 *
 * @author Edwin.Wu
 * @version 2016/11/25 16:21
 * @since JDK1.8
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    protected Context mContext;
    protected T b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        bindView();
        setUpData();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected void bindView() {
        b = DataBindingUtil.setContentView(this, getLayoutResId());
    }

    protected abstract void setUpData();

}
```

```java
/**
 * BaseFragment
 *
 * @author Edwin.Wu
 * @version 2016/11/25 16:20
 * @since JDK1.8
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    protected Context mContext;
    protected T b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bindView(inflater, container);
        setUpData();
        return b.getRoot();
    }


    @LayoutRes
    protected abstract int getLayoutResId();

    private void bindView(LayoutInflater inflater, ViewGroup container) {
        b = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
    }

    protected abstract void setUpData();
}
```

```java
/**
 * BindingViewHolder
 *
 * @author Edwin.Wu
 * @version 2016/11/28 11:01
 * @since JDK1.8
 */
class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T binding;

    BindingViewHolder(View itemView) {
        super(itemView);
    }

    public T getBinding() {
        return binding;
    }

    public void setBinding(T binding) {
        this.binding = binding;
    }
}
``` 

```java
/**
 * ListView
 *
 * @author Edwin.Wu
 * @version 2016/11/28 00:25
 * @since JDK1.8
 */
public class ListViewAdapter extends BaseAdapter {
    private List<Bean.DataBean> data;
    private LayoutInflater mLayoutInflater;
    private ItemListBinding binding;

    public ListViewAdapter(Context context, List<Bean.DataBean> allData) {
        this.data = allData;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Bean.DataBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_list, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemListBinding) convertView.getTag();
        }
        binding.setData(data.get(position));
        return convertView;
    }
}
```


```java
/**
 * RecyclerView
 *
 * @author Edwin.Wu
 * @version 2016/11/28 11:02
 * @since JDK1.8
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemRecyclerViewBinding>> {
    private List<Bean.DataBean> data;
    private LayoutInflater mLayoutInflater;

    private onItemClickLister mLister;

    public interface onItemClickLister {
        void onItemClick(View itemView, Bean.DataBean employee, int position);

    }

    public RecyclerViewAdapter(Context context, List<Bean.DataBean> data) {
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public BindingViewHolder<ItemRecyclerViewBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecyclerViewBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_recycler_view, parent, false);

        BindingViewHolder<ItemRecyclerViewBinding> holder = new BindingViewHolder<ItemRecyclerViewBinding>(binding.getRoot());

        holder.setBinding(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder<ItemRecyclerViewBinding> holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLister != null) {
                    mLister.onItemClick(holder.itemView, data.get(position), position);
                }
            }
        });
        holder.getBinding().setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setOnItemClickLister(onItemClickLister mLister) {
        if (mLister != null) {
            this.mLister = mLister;
        } else {
            throw new NullPointerException("onItemClickLister is null");
        }
    }


    public void addAll(List<Bean.DataBean> beanList) {
        if (beanList != null) {
            data.addAll(beanList);
        } else {
            throw new NullPointerException("List<Bean.DataBean> is null");
        }
    }


    public void add(int position, Bean.DataBean dataBean) {
        if (dataBean != null) {
            data.add(position, dataBean);
            notifyItemInserted(position);
        } else {
            throw new NullPointerException("dataBean is null");
        }
    }

    public void remove(int position) {
        if (data.size() == 0) {
            return;
        }
        data.remove(position);
        notifyItemRemoved(position);
    }
}
```

```java
/**
 * ViewModel
 * <p>
 * dataBinding绑定
 *
 * @author Edwin.Wu
 * @version 2016/11/25 20:38
 * @since JDK1.8
 */
public class ViewBindingAdapter {

    /**
     * dataBinding
     * 图片加载
     *
     * @param view
     * @param url
     * @param drawable
     */
    @BindingAdapter({"app:imageUrl", "app:placeHolder"})
    public static void loadImageFromUrl(ImageView view, String url, Drawable drawable) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(drawable)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .error(drawable)
                .into(view);
    }
```

```xml
item_recycler_view.xml

<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

        <variable
            name="data"
            type="com.github.why168.databinding.model.Bean.DataBean" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_margin="10dp"
        android:orientation="horizontal">

        <ImageView
            android:id="@+id/image_recycler_view"
            android:layout_width="100dp"
            android:layout_height="100dp"
            app:imageUrl="@{data.img}"
            app:placeHolder="@{@drawable/ic_launcher}" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginLeft="10dp"
            android:orientation="vertical">

            <TextView
                android:id="@+id/tv_recycler_title"
                android:layout_width="wrap_content"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:text="@{data.title}"
                android:textSize="15sp" />

            <TextView
                android:id="@+id/tv_recycler_subtitle"
                android:layout_width="wrap_content"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:text="@{data.subtitle}"
                android:textSize="18sp" />
        </LinearLayout>

    </LinearLayout>
</layout>
```


## 2. BaseView

<font>项目源码位置：AndroidProjects/BaseView目录</font> 

### 介绍

* 手写代码通过参数配置布局,提高APP性能,提高复用。


### 参考资料

* <a target="_blank" href="http://www.stay4it.com/course/12">http://www.stay4it.com/course/12</a>

### 效果图
![DataBinding-art](https://raw.githubusercontent.com/why168/AndroidProjects/master/Art/BaseView-art.png)

### 示例代码

```java
/**
 * MainActivity
 *
 * @author Edwin.Wu
 * @version 2016/5/28 21:15
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity implements OnRowClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContainerView mWidgetRowContainerView = (ContainerView) findViewById(R.id.mWidgetRowContainerView);
        ArrayList<GroupDescriptor> groupDescriptors = new ArrayList<GroupDescriptor>();

        ArrayList<BaseRowDescriptor> descriptors1 = new ArrayList<BaseRowDescriptor>();
        descriptors1.add(new RowProfileDescriptor("imagurl", "Edwin", "WeChat ID:wuhaoyou_949", RowActionEnum.MY_POSTS));
        GroupDescriptor groupDescriptor1 = new GroupDescriptor(descriptors1);
        groupDescriptors.add(groupDescriptor1);

        ArrayList<BaseRowDescriptor> descriptors2 = new ArrayList<BaseRowDescriptor>();
        descriptors2.add(new RowDescriptor(R.mipmap.more_my_album, "My Posts", RowActionEnum.ALBUM));
        descriptors2.add(new RowDescriptor(R.mipmap.more_my_favorite, "Favorites", RowActionEnum.FAVORITE));
        descriptors2.add(new RowDescriptor(R.mipmap.more_my_bank_card, "Wallet", RowActionEnum.BANK_CARD));
        descriptors2.add(new RowDescriptor(R.mipmap.emotionstore_custom_icon, "Cards & Passes", RowActionEnum.CUSTOM_ICON));
        GroupDescriptor groupDescriptor2 = new GroupDescriptor(descriptors2);
        groupDescriptors.add(groupDescriptor2);

        ArrayList<BaseRowDescriptor> descriptors3 = new ArrayList<BaseRowDescriptor>();
        descriptors3.add(new RowDescriptor(R.mipmap.emotionstore_emoji_icon, "Sticker Gallery", RowActionEnum.EMOJI_ICON));
        GroupDescriptor groupDescriptor3 = new GroupDescriptor(descriptors3);
        groupDescriptors.add(groupDescriptor3);

        ArrayList<BaseRowDescriptor> descriptors4 = new ArrayList<BaseRowDescriptor>();
        descriptors4.add(new RowDescriptor(R.mipmap.more_setting, "Settings", RowActionEnum.SETTING));
        GroupDescriptor groupDescriptor4 = new GroupDescriptor(descriptors4);
        groupDescriptors.add(groupDescriptor4);


        mWidgetRowContainerView.initializeData(groupDescriptors, this);
        mWidgetRowContainerView.notifyDataChanged();
    }

    @Override
    public void onRowClick(RowActionEnum actionEnum) {
        Toast.makeText(this, "RowActionEnum--->" + actionEnum.name(), Toast.LENGTH_SHORT).show();

        //TODO 根据不同的action实现逻辑
        switch (actionEnum) {
            case ALBUM:
                break;
            case FAVORITE:
                break;
            case BANK_CARD:
                break;
            case CUSTOM_ICON:
                break;
            case EMOJI_ICON:
                break;
            case SETTING:
                break;
        }
    }
}
```
## 3.CollapseView

<font>项目源码位置：AndroidProjects/CollapseView目录</font> 

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





<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
#### 技术交流大本营
>欢迎加入Android技术交流大群，群号码：554610222
> > Android技术交流，进群后请改名片.<br>例如：北京-李四.<br>群内交流以技术为主，乱发黄图乱发广告乱开车者一律踢.
> >
> ><a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=3fe01fcf10b71c29729a7b016477ceb899a6eb057e8c89cf1ea7b6773a477393"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="Android技术交流大群" title="Android技术交流大群"></a>
<br>

##MIT License

```
Copyright (c) 2016 Edwin

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
```