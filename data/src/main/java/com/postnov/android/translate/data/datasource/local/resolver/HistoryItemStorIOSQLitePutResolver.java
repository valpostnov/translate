package com.postnov.android.translate.data.datasource.local.resolver;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.postnov.android.translate.domain.HistoryItem;
import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;

import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_IS_BOOKMARK;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_IS_HISTORY;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_LANG;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_ORIGINAL;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_TIMESTAMP;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.COLUMN_TRANSLATE;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.TABLE;

/**
 * Generated resolver for Put Operation.
 */
public class HistoryItemStorIOSQLitePutResolver extends DefaultPutResolver<HistoryItem> {

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public InsertQuery mapToInsertQuery(@NonNull HistoryItem object) {
        return InsertQuery.builder()
                .table(TABLE)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public UpdateQuery mapToUpdateQuery(@NonNull HistoryItem object) {
        return UpdateQuery.builder()
                .table(TABLE)
                .where("timestamp = ?")
                .whereArgs(object.getTimestamp())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public ContentValues mapToContentValues(@NonNull HistoryItem object) {
        ContentValues contentValues = new ContentValues(6);

        contentValues.put(COLUMN_ORIGINAL, object.getOriginal());
        contentValues.put(COLUMN_IS_HISTORY, object.isHistory());
        contentValues.put(COLUMN_IS_BOOKMARK, object.isBookmark());
        contentValues.put(COLUMN_TIMESTAMP, object.getTimestamp());
        contentValues.put(COLUMN_LANG, object.getLang());
        contentValues.put(COLUMN_TRANSLATE, object.getTranslated());

        return contentValues;
    }
}
