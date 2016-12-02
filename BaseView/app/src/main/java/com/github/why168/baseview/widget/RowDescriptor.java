package com.github.why168.baseview.widget;

/**
 * RowDescriptor
 *
 * @author Edwin.Wu
 * @version 2016/5/27 22:46
 * @since JDK1.8
 */
public class RowDescriptor extends BaseRowDescriptor {
    public int iconResId;
    public String label;

    public RowDescriptor(int iconResId, String label, RowActionEnum action) {
        this.iconResId = iconResId;
        this.label = label;
        this.action = action;
    }
}
