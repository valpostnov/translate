package com.postnov.android.translate.presentation.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.postnov.android.translate.data.datasource.local.TranslateOpenHelper;
import com.postnov.android.translate.data.datasource.local.resolver.HistoryItemSQLiteTypeMapping;
import com.postnov.android.translate.data.datasource.local.table.HistoryItemTable;
import com.postnov.android.translate.data.entity.DictionaryEntity;
import com.postnov.android.translate.data.gson.DictionaryJsonDeserializer;
import com.postnov.android.translate.data.util.LanguageHelper;
import com.postnov.android.translate.data.util.LanguageHelperImpl;
import com.postnov.android.translate.data.util.PreferencesManager;
import com.postnov.android.translate.data.util.QueryMapFactoryImpl;
import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.QueryMapFactory;
import com.postnov.android.translate.presentation.App;
import com.postnov.android.translate.presentation.bus.RxBus;
import com.postnov.android.translate.presentation.utils.BaseRxTransformer;
import com.postnov.android.translate.presentation.utils.RxTransformer;
import com.pushtorefresh.storio.sqlite.Changes;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @author Valentin Postnov
 */
@Module
public class ApplicationModule {

    private static final String GLOBAL_PREFS_NAME = "global_prefs";

    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    SharedPreferences provideGlobalSharedPrefs(Context context) {
        return context.getSharedPreferences(GLOBAL_PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    LanguageHelper provideLanguageHelper(SharedPreferences preferences) {
        return new LanguageHelperImpl(preferences);
    }

    @Provides
    @Singleton
    Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    RxTransformer provideBaseRxTransformer() {
        return new BaseRxTransformer();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(DictionaryEntity.class, new DictionaryJsonDeserializer())
                .create();
    }

    @Provides
    @Singleton
    QueryMapFactory provideQueryMapFactory(PreferencesManager manager) {
        return new QueryMapFactoryImpl(manager);
    }

    @Provides
    @Singleton
    StorIOSQLite provideStoreIoSqlite(SQLiteOpenHelper openHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(openHelper)
                .addTypeMapping(HistoryItem.class, new HistoryItemSQLiteTypeMapping())
                .build();
    }

    @Provides
    @Singleton
    Observable<Changes> provideObserver(StorIOSQLite storIOSQLite) {
        return storIOSQLite.observeChangesInTable(HistoryItemTable.TABLE);
    }

    @Provides
    @Singleton
    SQLiteOpenHelper provideTranslateOpenHelper(Context context) {
        return new TranslateOpenHelper(context);
    }

    @Provides
    @Singleton
    RxBus provideBus() {
        return new RxBus();
    }
}
