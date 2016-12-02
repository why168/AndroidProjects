package com.github.why168.baseview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.why168.baseview.widget.BaseRowDescriptor;
import com.github.why168.baseview.widget.ContainerView;
import com.github.why168.baseview.widget.GroupDescriptor;
import com.github.why168.baseview.widget.OnRowClickListener;
import com.github.why168.baseview.widget.RowActionEnum;
import com.github.why168.baseview.widget.RowDescriptor;
import com.github.why168.baseview.widget.RowProfileDescriptor;

import java.util.ArrayList;

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
