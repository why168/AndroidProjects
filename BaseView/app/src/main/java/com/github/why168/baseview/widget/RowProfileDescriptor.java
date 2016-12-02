package com.github.why168.baseview.widget;

/**
 * RowProfileDescriptor
 *
 * @author Edwin.Wu
 * @version 2016/5/28 21:12
 * @since JDK1.8
 */
public class RowProfileDescriptor extends BaseRowDescriptor {
    public String imgUrl;
    public String label;
    public String detailLabel;

    public RowProfileDescriptor(String imgUrl, String detailLabel, String label, RowActionEnum action) {
        this.detailLabel = detailLabel;
        this.imgUrl = imgUrl;
        this.label = label;
        this.action = action;
    }
}
