package com.neonscan.di;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/neonscan/di/AppModule;", "", "()V", "provideDb", "Lcom/neonscan/data/local/AppDatabase;", "context", "Landroid/content/Context;", "provideRepo", "Lcom/neonscan/data/repo/DocumentRepository;", "provideSettings", "Lcom/neonscan/data/settings/SettingsRepository;", "app_debug"})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.neonscan.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.neonscan.data.local.AppDatabase provideDb(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.neonscan.data.repo.DocumentRepository provideRepo(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.neonscan.data.settings.SettingsRepository provideSettings(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}