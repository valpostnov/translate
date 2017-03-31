package com.postnov.android.translate.data.entity.mapper;

import com.postnov.android.translate.data.entity.AlternateTranslateEntity;
import com.postnov.android.translate.data.entity.DictionaryEntity;
import com.postnov.android.translate.data.entity.TranslateInnerObject;
import com.postnov.android.translate.domain.Synonym;
import com.postnov.android.translate.domain.Translate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;

import static com.postnov.android.translate.data.util.QueryMapFactoryImpl.LANG;
import static com.postnov.android.translate.data.util.QueryMapFactoryImpl.TEXT;

/**
 * @author Valentin Postnov
 */
public class DictionaryEntityMapper implements Func1<DictionaryEntity, Translate> {

    private static final String SPLITTER = ", ";

    private final Map<String, String> lastRequest;

    public DictionaryEntityMapper(Map<String, String> lastRequest) {
        this.lastRequest = lastRequest;
    }

    @Override
    public Translate call(DictionaryEntity dictionaryEntity) {
        return new Translate.Builder()
                .setOriginal(lastRequest.get(TEXT))
                .setTranslate(dictionaryEntity.getTranslate())
                .setLang(lastRequest.get(LANG))
                .setSynonyms(createSynonyms(dictionaryEntity.getAlternate()))
                .build();
    }

    private List<Synonym> createSynonyms(List<AlternateTranslateEntity> translates) {
        List<Synonym> synonymList = new ArrayList<>();
        for (AlternateTranslateEntity entity : translates) {
            String key = concatSynonyms(entity.getText(), entity.getSynonyms());
            String value = concatMeans(entity.getMeans());
            Synonym synonym = new Synonym(key, value);
            synonymList.add(synonym);
        }

        return synonymList;
    }

    private String concatSynonyms(String text, TranslateInnerObject[] synonyms) {
        if (synonyms == null) {
            return text;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(text);
        builder.append(SPLITTER);

        if (synonyms.length == 1) {
            return builder.append(synonyms[synonyms.length - 1].getText()).toString();
        }

        for (int i = 0; i < synonyms.length - 1; i++) {
            builder.append(synonyms[i].getText());
            builder.append(SPLITTER);
        }

        builder.append(synonyms[synonyms.length - 1].getText());

        return builder.toString();
    }

    private String concatMeans(TranslateInnerObject[] means) {
        if (means == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < means.length - 1; i++) {
            builder.append(means[i].getText());
            builder.append(SPLITTER);
        }

        builder.append(means[means.length - 1].getText());

        return builder.toString();
    }
}
