package com.postnov.android.translate.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.Translate;
import com.postnov.android.translate.presentation.MainActivity;
import com.postnov.android.translate.presentation.R;
import com.postnov.android.translate.presentation.base.BaseFragment;
import com.postnov.android.translate.presentation.language.LanguageActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @author Valentin Postnov
 */
public class MainFragment extends BaseFragment<MainPresenter> implements MainFragmentView, TranslateAdapter.OnBookmarkClickListener {

    public static final String CHOOSE_ORIGINAL_LANG_ACTION = "CHOOSE_ORIGINAL_LANG_ACTION";
    public static final String CHOOSE_TRANSLATE_LANG_ACTION = "CHOOSE_TRANSLATE_LANG_ACTION";
    private final TranslateAdapter translateAdapter = new TranslateAdapter();
    @BindView(R.id.fragment_main_input_view)
    EditText inputView;
    @BindView(R.id.fragment_main_recycler)
    RecyclerView rv;
    @BindView(R.id.fragment_main_error_view)
    TextView errorView;
    @BindView(R.id.fragment_main_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_main_toolbar_lng1)
    TextView chosenOriginalLang;
    @BindView(R.id.fragment_main_toolbar_lng2)
    TextView chosenTranslateLang;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
        presenter.fetchLanguages();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showTranslate(Translate translate) {
        translateAdapter.swap(translate);
    }

    @Override
    public void showProgress() {
        translateAdapter.clear();
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        translateAdapter.clear();
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(error);
    }

    @Override
    public void updateHistoryItem(HistoryItem item) {
        translateAdapter.swap(item);
    }

    @Override
    public void setDefLanguages(String original, String translate) {
        chosenOriginalLang.setText(original);
        chosenTranslateLang.setText(translate);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    @Override
    protected void initInjection() {
        ((MainActivity) getActivity()).getComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onBookmarkClick(HistoryItem item) {
        presenter.addOrDeleteBookmarkForLastItem(item);
    }

    @OnClick(R.id.fragment_main_toolbar_lng1)
    void onFirstLngClick() {
        LanguageActivity.start(this, CHOOSE_ORIGINAL_LANG_ACTION);
    }

    @OnClick(R.id.fragment_main_toolbar_lng2)
    void onSecondLngClick() {
        LanguageActivity.start(this, CHOOSE_TRANSLATE_LANG_ACTION);
    }

    @OnClick(R.id.fragment_main_toolbar_swap)
    void onLngSwapClick() {
        presenter.swapLanguages();
        Translate item = translateAdapter.getItem();
        inputView.setText(item != null ? item.getTranslate() : null);
    }

    @OnClick(R.id.fragment_main_clear)
    void onClickClear() {
        inputView.setText(null);
        translateAdapter.clear();
    }

    @OnTextChanged(R.id.fragment_main_input_view)
    void onInputViewTextChanged(CharSequence text) {
        presenter.fetchTranslate(text.toString());
    }

    private void initRecyclerView() {
        translateAdapter.setOnBookmarkClickListener(this);
        rv.setAdapter(translateAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
