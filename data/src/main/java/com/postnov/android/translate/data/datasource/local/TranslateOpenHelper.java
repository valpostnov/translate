package com.postnov.android.translate.data.datasource.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.postnov.android.translate.data.datasource.local.table.HistoryItemTable;

import javax.inject.Inject;

/**
 * @author Valentin Postnov
 */
public class TranslateOpenHelper extends SQLiteOpenHelper {

    @Inject
    public TranslateOpenHelper(Context context) {
        super(context, "translate_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HistoryItemTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(HistoryItemTable.getDeleteTableQuery());
    }
}
