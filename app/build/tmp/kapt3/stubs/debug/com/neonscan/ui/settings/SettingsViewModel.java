package com.neonscan.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0014"}, d2 = {"Lcom/neonscan/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/neonscan/data/settings/SettingsRepository;", "(Lcom/neonscan/data/settings/SettingsRepository;)V", "settings", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/neonscan/data/settings/UserSettings;", "getSettings", "()Lkotlinx/coroutines/flow/StateFlow;", "setFormat", "Lkotlinx/coroutines/Job;", "f", "Lcom/neonscan/data/model/DocumentFormat;", "setFrequency", "freq", "", "setQuality", "q", "Lcom/neonscan/data/settings/ScanQuality;", "app_debug"})
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.neonscan.data.settings.SettingsRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.neonscan.data.settings.UserSettings> settings = null;
    
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.settings.SettingsRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.neonscan.data.settings.UserSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setQuality(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.settings.ScanQuality q) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setFormat(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.model.DocumentFormat f) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setFrequency(int freq) {
        return null;
    }
}