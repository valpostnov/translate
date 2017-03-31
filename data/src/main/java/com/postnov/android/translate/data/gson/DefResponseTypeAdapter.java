package com.postnov.android.translate.data.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.postnov.android.translate.data.entity.dictionary.Def;
import com.postnov.android.translate.data.entity.dictionary.DefTr;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Valentin Postnov
 */
public class DefResponseTypeAdapter implements JsonDeserializer<Def> {

    private static final String KEY_DEF = "def";
    private static final String KEY_DEF_TEXT = "text";
    private static final String KEY_DEF_TR = "tr";

    @Override
    public Def deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray defArray = json.getAsJsonObject().get(KEY_DEF).getAsJsonArray();
        if (defArray.size() != 0) {
            JsonObject defObject = defArray.get(0).getAsJsonObject();
            String text = defObject.get(KEY_DEF_TEXT).getAsString();
            JsonArray trArray = defObject.get(KEY_DEF_TR).getAsJsonArray();

            List<DefTr> defTrList = context.deserialize(trArray, new TypeToken<List<DefTr>>() {
            }.getType());

            return new Def(text, defTrList);
        }

        return null;
    }
}
