package com.postnov.android.translate.data.datasource.local.resolver;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.postnov.android.translate.domain.HistoryItem;
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;

import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_IS_BOOKMARK;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_IS_HISTORY;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_LANG;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_ORIGINAL;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_TIMESTAMP;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_TRANSLATE;

/**
 * Generated resolver for Get Operation.
 */
public class HistoryItemStorIOSQLiteGetResolver extends DefaultGetResolver<HistoryItem> {
    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public HistoryItem mapFromCursor(@NonNull Cursor cursor) {
        HistoryItem object = new HistoryItem();

        object.setOriginal(cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL)));
        object.setHistory(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_HISTORY)) == 1);
        object.setBookmark(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_BOOKMARK)) == 1);
        object.setTimestamp(cursor.getLong(cursor.getColumnIndex(COLUMN_TIMESTAMP)));
        object.setLang(cursor.getString(cursor.getColumnIndex(COLUMN_LANG)));
        object.setTranslated(cursor.getString(cursor.getColumnIndex(COLUMN_TRANSLATE)));

        return object;
    }
}
