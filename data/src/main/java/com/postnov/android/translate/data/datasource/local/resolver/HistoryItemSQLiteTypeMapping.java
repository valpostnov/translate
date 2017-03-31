package com.postnov.android.translate.data.datasource.local.resolver;

import com.postnov.android.translate.domain.HistoryItem;
import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;

/**
 * Generated mapping with collection of resolvers.
 */
public class HistoryItemSQLiteTypeMapping extends SQLiteTypeMapping<HistoryItem> {
    public HistoryItemSQLiteTypeMapping() {
        super(new HistoryItemStorIOSQLitePutResolver(),
                new HistoryItemStorIOSQLiteGetResolver(),
                new HistoryItemStorIOSQLiteDeleteResolver());
    }
}
