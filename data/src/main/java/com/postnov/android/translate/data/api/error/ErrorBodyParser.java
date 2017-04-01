package com.postnov.android.translate.data.api.error;

import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * @author Valentin Postnov
 */
public class ErrorBodyParser {

    private SparseArray<String> codes = new SparseArray<>(7);
    private final Gson gson;

    @Inject
    public ErrorBodyParser(Gson gson) {
        this.gson = gson;
        codes.put(401, "Неправильный API-ключ");
        codes.put(402, "API-ключ заблокирован");
        codes.put(403, "Превышено суточное ограничение на количество запросов.");
        codes.put(404, "Превышено суточное ограничение на объем переведенного текста");
        codes.put(413, "Превышен максимально допустимый размер текста");
        codes.put(422, "Текст не может быть переведен");
        codes.put(501, "Заданное направление перевода не поддерживается");
    }

    public ApiErrorEntity parseResponse(Response<?> response) {
        try {
            JsonElement jsonElement = new JsonParser().parse(response.errorBody().string());
            return gson.fromJson(jsonElement, ApiErrorEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String errorMessage(int code) {
        return codes.get(code);
    }
}
