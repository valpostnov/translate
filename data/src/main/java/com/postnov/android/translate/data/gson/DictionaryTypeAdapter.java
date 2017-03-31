package com.postnov.android.translate.data.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.postnov.android.translate.data.entity.AlternateTranslateEntity;
import com.postnov.android.translate.data.entity.DictionaryEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Valentin Postnov
 */
public class DictionaryTypeAdapter implements JsonDeserializer<DictionaryEntity> {

    private static final String KEY_DEF = "def";
    private static final String KEY_DEF_TEXT = "text";
    private static final String KEY_DEF_TR = "tr";

    @Override
    public DictionaryEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray dictionaryArray = json.getAsJsonObject().get(KEY_DEF).getAsJsonArray();
        if (dictionaryArray.size() != 0) {
            JsonObject dictionaryObject = dictionaryArray.get(0).getAsJsonObject();
            String text = dictionaryObject.get(KEY_DEF_TEXT).getAsString();
            JsonArray trArray = dictionaryObject.get(KEY_DEF_TR).getAsJsonArray();

            List<AlternateTranslateEntity> alternateTranslateEntities = context.deserialize(trArray, new TypeToken<List<AlternateTranslateEntity>>() {}.getType());

            return new DictionaryEntity(text, alternateTranslateEntities);
        }

        return null;
    }
}
