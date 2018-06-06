package io.github.why168.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 * 输入建议
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/6/2 下午7:13 v0.9
 * @since JDK1.8
 */
public class SuggestionInputLayout extends FrameLayout {

    private String word;

    public SuggestionInputLayout(@NonNull Context context) {
        this(context, null);
    }

    public SuggestionInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuggestionInputLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_suggestion_input, this);
    }

    public void setWordSuggestion(final String suggestion) {
        final EditText suggestionView = findViewById(R.id.suggestion);
        suggestionView.setText(suggestion);
    }

    public void clear() {
        final EditText wordView = findViewById(R.id.word);
        final EditText suggestionView = findViewById(R.id.suggestion);
        wordView.setText("");
        suggestionView.setText("");
    }

    public void clearSuggestion() {
        final EditText suggestionView = findViewById(R.id.suggestion);
        suggestionView.setText("");
        this.word = null;
    }

    // 获取输入的单词
    public EditText getWordView() {
        return findViewById(R.id.word);
    }

    // 获取建议的View
    public EditText getSuggestionView() {
        return findViewById(R.id.suggestion);
    }

    // 获取单词
    public ShadowCardView getTagView() {
        return findViewById(R.id.tag);
    }

    public void setSuggestionAsWord() {
        final String suggestion = getSuggestionView().getText().toString();
        if (suggestion.length() == 0) return;
        getWordView().setText(suggestion);
        this.word = suggestion;
    }

    public void showTagView(final String word) {
        getWordView().setVisibility(GONE);
        getSuggestionView().setVisibility(GONE);
        getTagView().setVisibility(VISIBLE);
        getTagView().setText(word);
        this.word = word;
    }

    public void showInputView(final String word) {
        getWordView().setVisibility(VISIBLE);
        getSuggestionView().setVisibility(VISIBLE);
        getTagView().setVisibility(GONE);
        getWordView().setText(word);
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }
}
