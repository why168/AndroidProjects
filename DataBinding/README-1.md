## 1.Data Binding框架MVVM

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
