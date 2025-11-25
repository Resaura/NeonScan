package com.neonscan.data.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001c"}, d2 = {"Lcom/neonscan/data/settings/SettingsRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "KEY_FORMAT", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "KEY_FREQ", "", "KEY_QUALITY", "settings", "Lkotlinx/coroutines/flow/Flow;", "Lcom/neonscan/data/settings/UserSettings;", "getSettings", "()Lkotlinx/coroutines/flow/Flow;", "updateFormat", "", "format", "Lcom/neonscan/data/model/DocumentFormat;", "(Lcom/neonscan/data/model/DocumentFormat;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateInterstitialFrequency", "freq", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateQuality", "quality", "Lcom/neonscan/data/settings/ScanQuality;", "(Lcom/neonscan/data/settings/ScanQuality;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SettingsRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> KEY_QUALITY = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> KEY_FORMAT = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> KEY_FREQ = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.neonscan.data.settings.UserSettings> settings = null;
    
    public SettingsRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.neonscan.data.settings.UserSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateQuality(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.settings.ScanQuality quality, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFormat(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.model.DocumentFormat format, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateInterstitialFrequency(int freq, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}