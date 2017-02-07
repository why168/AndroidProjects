package github.why168.swipeback.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.github.why168.LoopViewPagerLayout;
import com.github.why168.listener.OnBannerItemClickListener;
import com.github.why168.loader.OnDefaultImageViewLoader;
import com.github.why168.modle.BannerInfo;
import com.github.why168.modle.IndicatorLocation;
import com.github.why168.modle.LoopStyle;

import java.util.ArrayList;

import github.why168.swipeback.R;
import github.why168.swipeback.base.BaseActivity;

/**
 * 带ViewPager界面返回
 *
 * @author Edwin.Wu
 * @version 2017/2/7 18:57
 * @since JDK1.8
 */
public class ViewPagerActivity extends BaseActivity implements OnBannerItemClickListener {
    private LoopViewPagerLayout mLoopViewPagerLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void setUpView() {
        mLoopViewPagerLayout = (LoopViewPagerLayout) findViewById(R.id.mLoopViewPagerLayout);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        mLoopViewPagerLayout = (LoopViewPagerLayout) findViewById(R.id.mLoopViewPagerLayout);
        mLoopViewPagerLayout.setLoop_ms(2000);//轮播的速度(毫秒)
        mLoopViewPagerLayout.setLoop_duration(1000);//滑动的速率(毫秒)
        mLoopViewPagerLayout.setLoop_style(LoopStyle.Empty);//轮播的样式-默认empty
        mLoopViewPagerLayout.setIndicatorLocation(IndicatorLocation.Center);//指示器位置-中Center
        mLoopViewPagerLayout.initializeData(mContext);//初始化数据
        final ArrayList<BannerInfo> bannerInfos = new ArrayList<>();
        bannerInfos.add(new BannerInfo<Integer>(R.color.black, "第一张图片"));
        bannerInfos.add(new BannerInfo<Integer>(R.color.read, "第二张图片"));
        bannerInfos.add(new BannerInfo<Integer>(R.color.colorAccent, "第三张图片"));
        bannerInfos.add(new BannerInfo<Integer>(R.color.colorPrimaryDark, "第四张图片"));
        bannerInfos.add(new BannerInfo<Integer>(R.color.colorPrimary, "第五张图片"));
        mLoopViewPagerLayout.setOnLoadImageViewListener(new OnDefaultImageViewLoader() {
            @Override
            public void onLoadImageView(ImageView imageView, Object o) {
                imageView.setBackgroundResource((Integer) o);
            }
        });//设置图片加载&自定义图片监听
        mLoopViewPagerLayout.setOnBannerItemClickListener(this);//设置监听
        mLoopViewPagerLayout.setLoopData(bannerInfos);//设置数据
    }

    @Override
    public void onBannerClick(int i, ArrayList<BannerInfo> arrayList) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mLoopViewPagerLayout.startLoop();
        Log.e("Edwin", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLoopViewPagerLayout.stopLoop();
        Log.e("Edwin", "onStop");
    }
}
