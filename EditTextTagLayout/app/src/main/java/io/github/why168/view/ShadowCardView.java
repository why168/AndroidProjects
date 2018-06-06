package io.github.why168.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

import io.github.why168.R;


/**
 * 阴影
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/6/2 下午7:13 v0.9
 * @since JDK1.8
 */
public class ShadowCardView extends CardView {

    private boolean shadowEnabled;
    private String text;
    private int cornerRadius;

    public ShadowCardView(@NonNull Context context) {
        super(context);
        init();
    }

    public ShadowCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        parseAttributeSet(context, attrs);
        init();
    }

    private void parseAttributeSet(final Context context, final @Nullable AttributeSet attrs) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShadowCardView, 0, 0);
        this.shadowEnabled = a.getBoolean(R.styleable.ShadowCardView_shadow, true);
        this.text = a.getString(R.styleable.ShadowCardView_text);
        this.cornerRadius = a.getDimensionPixelSize(R.styleable.ShadowCardView_cornerRadius, 0);
        a.recycle();
    }

    private void init() {
        inflate(getContext(), R.layout.view_shadow_text_view, this);
        initView();
    }

    private void initView() {
        setText(this.text);
        setMaxCardElevation(this.cornerRadius);
        setRadius(this.cornerRadius);
        setShadowEnabled(this.shadowEnabled);
    }

    public void setShadowEnabled(final boolean shadowEnabled) {
        this.shadowEnabled = shadowEnabled;
        if (this.shadowEnabled) enableShadow();
        else disableShadow();
    }

    public ShadowCardView setCornerRadius(final float radius) {
        setRadius(radius);
        return this;
    }

    public void enableShadow() {
        this.setCardElevation(4f);
    }

    public void disableShadow() {
        this.setCardElevation(0f);
    }

    public void setText(final String s) {
        final TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(s);
    }

    public String getText() {
        final TextView textView = (TextView) findViewById(R.id.text_view);
        return textView.getText().toString();
    }

    public void hideText() {
        final TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public void showText() {
        final TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setTransformationMethod(null);
    }
}
