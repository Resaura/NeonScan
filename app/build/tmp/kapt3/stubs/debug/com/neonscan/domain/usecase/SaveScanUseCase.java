package com.neonscan.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J2\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0086B\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/neonscan/domain/usecase/SaveScanUseCase;", "", "repo", "Lcom/neonscan/data/repo/DocumentRepository;", "fileManager", "Lcom/neonscan/util/FileManager;", "(Lcom/neonscan/data/repo/DocumentRepository;Lcom/neonscan/util/FileManager;)V", "defaultName", "", "invoke", "Lcom/neonscan/data/local/DocumentEntity;", "bitmap", "Landroid/graphics/Bitmap;", "exportPdf", "", "exportJpg", "title", "(Landroid/graphics/Bitmap;ZZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SaveScanUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.neonscan.data.repo.DocumentRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.neonscan.util.FileManager fileManager = null;
    
    public SaveScanUseCase(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.repo.DocumentRepository repo, @org.jetbrains.annotations.NotNull()
    com.neonscan.util.FileManager fileManager) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, boolean exportPdf, boolean exportJpg, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.neonscan.data.local.DocumentEntity> $completion) {
        return null;
    }
    
    private final java.lang.String defaultName() {
        return null;
    }
}