package com.neonscan.ui.options;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a$\u0010\b\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a*\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007\u001a@\u0010\u0015\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a \u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\tH\u0002\u00a8\u0006 "}, d2 = {"FormatChip", "", "label", "", "selected", "", "onClick", "Lkotlin/Function0;", "FormatToggle", "Lcom/neonscan/data/model/DocumentFormat;", "onSelect", "Lkotlin/Function1;", "OptionsScreen", "navController", "Landroidx/navigation/NavController;", "repo", "Lcom/neonscan/data/repo/DocumentRepository;", "settingsRepository", "Lcom/neonscan/data/settings/SettingsRepository;", "vm", "Lcom/neonscan/ui/options/OptionsViewModel;", "SaveDialog", "onDismiss", "onSaveApp", "onSavePhone", "onSaveBoth", "saveToDevice", "context", "Landroid/content/Context;", "bitmap", "Landroid/graphics/Bitmap;", "format", "app_debug"})
public final class OptionsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void OptionsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.neonscan.data.repo.DocumentRepository repo, @org.jetbrains.annotations.NotNull()
    com.neonscan.data.settings.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.neonscan.ui.options.OptionsViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FormatToggle(com.neonscan.data.model.DocumentFormat selected, kotlin.jvm.functions.Function1<? super com.neonscan.data.model.DocumentFormat, kotlin.Unit> onSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FormatChip(java.lang.String label, boolean selected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SaveDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function0<kotlin.Unit> onSaveApp, kotlin.jvm.functions.Function0<kotlin.Unit> onSavePhone, kotlin.jvm.functions.Function0<kotlin.Unit> onSaveBoth) {
    }
    
    private static final boolean saveToDevice(android.content.Context context, android.graphics.Bitmap bitmap, com.neonscan.data.model.DocumentFormat format) {
        return false;
    }
}