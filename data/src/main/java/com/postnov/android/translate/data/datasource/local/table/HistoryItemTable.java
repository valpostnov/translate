package com.postnov.android.translate.data.datasource.local.table;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio.sqlite.queries.Query;

/**
 * @author Valentin Postnov
 */
public class HistoryItemTable {

    public static final String TABLE = "history";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_ORIGINAL = "original";
    public static final String COLUMN_TRANSLATE = "translate";
    public static final String COLUMN_LANG = "lang";
    public static final String COLUMN_IS_FAVE = "is_fave";
    public static final String COLUMN_IS_HISTORY = "is_history";

    public static final Query QUERY_HISTORY = Query.builder().table(TABLE).where("is_history = 1").build();
    public static final Query QUERY_FAVE = Query.builder().table(TABLE).where("is_fave = 1").build();
    public static final DeleteQuery QUERY_DELETE_HISTORY = DeleteQuery.builder()
            .table(TABLE)
            .where("is_fave = 0 AND is_history = 0")
            .build();

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_TIMESTAMP + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_ORIGINAL + " TEXT NOT NULL, "
                + COLUMN_TRANSLATE + " TEXT NOT NULL, "
                + COLUMN_LANG + " TEXT NOT NULL, "
                + COLUMN_IS_FAVE + " INTEGER NOT NUll, "
                + COLUMN_IS_HISTORY + " INTEGER NOT NUll"
                + ");";
    }

    public static String getDeleteTableQuery() {
        return "DROP TABLE IF EXIST " + TABLE;
    }
}
