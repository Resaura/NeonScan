package com.neonscan.ui.options;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\"\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001d0!R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR+\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00148F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u000f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u0006#"}, d2 = {"Lcom/neonscan/ui/options/OptionsViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/neonscan/data/repo/DocumentRepository;", "fileManager", "Lcom/neonscan/util/FileManager;", "(Lcom/neonscan/data/repo/DocumentRepository;Lcom/neonscan/util/FileManager;)V", "<set-?>", "", "exportJpg", "getExportJpg", "()Z", "setExportJpg", "(Z)V", "exportJpg$delegate", "Landroidx/compose/runtime/MutableState;", "exportPdf", "getExportPdf", "setExportPdf", "exportPdf$delegate", "", "title", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "title$delegate", "defaultName", "save", "", "bitmap", "Landroid/graphics/Bitmap;", "onSaved", "Lkotlin/Function1;", "Lcom/neonscan/data/local/DocumentEntity;", "app_debug"})
public final class OptionsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.neonscan.data.repo.DocumentRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.neonscan.util.FileManager fileManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState title$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState exportPdf$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState exportJpg$delegate = null;
    
    public OptionsViewModel(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.repo.DocumentRepository repo, @org.jetbrains.annotations.NotNull()
    com.neonscan.util.FileManager fileManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean getExportPdf() {
        return false;
    }
    
    public final void setExportPdf(boolean p0) {
    }
    
    public final boolean getExportJpg() {
        return false;
    }
    
    public final void setExportJpg(boolean p0) {
    }
    
    public final void save(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.neonscan.data.local.DocumentEntity, kotlin.Unit> onSaved) {
    }
    
    private final java.lang.String defaultName() {
        return null;
    }
}