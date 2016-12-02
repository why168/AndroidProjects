package com.github.why168.baseview.widget;

import java.util.ArrayList;

/**
 * GroupDescriptor
 *
 * @author Edwin.Wu
 * @version 2016/5/27 23:54
 * @since JDK1.8
 */
public class GroupDescriptor {
    public String title;
    public ArrayList<BaseRowDescriptor> descriptors;

    public GroupDescriptor(ArrayList<BaseRowDescriptor> descriptors) {
        this.descriptors = descriptors;
    }

    public GroupDescriptor(String title, ArrayList<BaseRowDescriptor> descriptors) {
        this.title = title;
        this.descriptors = descriptors;
    }
}
