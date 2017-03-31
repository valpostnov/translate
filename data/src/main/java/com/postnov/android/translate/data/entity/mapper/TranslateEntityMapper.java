package com.postnov.android.translate.data.entity.mapper;

import com.postnov.android.translate.data.entity.TranslateEntity;
import com.postnov.android.translate.domain.Translate;

import java.util.Collections;
import java.util.Map;

import rx.functions.Func1;

import static com.postnov.android.translate.data.util.QueryMapFactoryImpl.TEXT;

/**
 * @author Valentin Postnov
 */
public class TranslateEntityMapper implements Func1<TranslateEntity, Translate> {

    private final Map<String, String> request;

    public TranslateEntityMapper(Map<String, String> request) {
        this.request = request;
    }

    @Override
    public Translate call(TranslateEntity response) {
        return new Translate.Builder()
                .setOriginal(request.get(TEXT))
                .setTranslate(response.getText())
                .setLang(response.getLang())
                .setSynonyms(Collections.emptyList())
                .build();
    }
}
