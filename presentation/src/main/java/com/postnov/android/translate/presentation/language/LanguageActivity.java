package com.postnov.android.translate.presentation.language;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.postnov.android.translate.presentation.R;
import com.postnov.android.translate.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.postnov.android.translate.presentation.main.MainFragment.CHOOSE_ORIGINAL_LANG_ACTION;

/**
 * @author Valentin Postnov
 *         Чистый хардкод
 */
public class LanguageActivity extends BaseActivity implements LanguageView {

    @Inject
    LanguagePresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void start(Fragment fragment, String action) {
        fragment.startActivity(new Intent(fragment.getActivity(), LanguageActivity.class).setAction(action));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    @Override
    protected void initInjection() {
        getComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_language;
    }

    @OnClick(R.id.russia)
    void onClickRussia() {
        presenter.updateOriginal(getIntent().getAction());
        finish();
    }

    @OnClick(R.id.english)
    void onClickEnglish() {
        presenter.updateTranslate(getIntent().getAction());
        finish();
    }

    private void initToolbar() {
        String action = getIntent().getAction();
        toolbar.setTitle(CHOOSE_ORIGINAL_LANG_ACTION.equals(action)
                ? getString(R.string.choose_original_lang)
                : getString(R.string.choose_translate_lang));

        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp);
    }
}
