package com.postnov.android.translate.data.datasource.local.resolver;

import android.support.annotation.NonNull;

import com.postnov.android.translate.domain.HistoryItem;
import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;

import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.TABLE;

/**
 * Generated resolver for Delete Operation.
 */
public class HistoryItemStorIOSQLiteDeleteResolver extends DefaultDeleteResolver<HistoryItem> {
    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public DeleteQuery mapToDeleteQuery(@NonNull HistoryItem object) {
        return DeleteQuery.builder()
            .table(TABLE)
            .where("timestamp = ?")
            .whereArgs(object.getTimestamp())
            .build();
    }
}
