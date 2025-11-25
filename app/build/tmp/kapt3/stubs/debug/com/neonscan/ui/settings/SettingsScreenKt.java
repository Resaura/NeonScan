package com.neonscan.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a$\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0018\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002\u00a8\u0006\u0010"}, d2 = {"FormatRow", "", "selected", "Lcom/neonscan/data/model/DocumentFormat;", "onSelect", "Lkotlin/Function1;", "QualityRow", "Lcom/neonscan/data/settings/ScanQuality;", "SettingsScreen", "navController", "Landroidx/navigation/NavController;", "settingsRepository", "Lcom/neonscan/data/settings/SettingsRepository;", "qLabel", "", "q", "app_debug"})
public final class SettingsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.neonscan.data.settings.SettingsRepository settingsRepository) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QualityRow(com.neonscan.data.settings.ScanQuality selected, kotlin.jvm.functions.Function1<? super com.neonscan.data.settings.ScanQuality, kotlin.Unit> onSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FormatRow(com.neonscan.data.model.DocumentFormat selected, kotlin.jvm.functions.Function1<? super com.neonscan.data.model.DocumentFormat, kotlin.Unit> onSelect) {
    }
    
    private static final java.lang.String qLabel(com.neonscan.data.settings.ScanQuality q) {
        return null;
    }
}