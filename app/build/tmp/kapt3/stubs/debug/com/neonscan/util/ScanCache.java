package com.neonscan.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR.\u0010\u000f\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/neonscan/util/ScanCache;", "", "()V", "currentDocument", "Lcom/neonscan/data/local/DocumentEntity;", "getCurrentDocument", "()Lcom/neonscan/data/local/DocumentEntity;", "setCurrentDocument", "(Lcom/neonscan/data/local/DocumentEntity;)V", "lastBitmap", "Landroid/graphics/Bitmap;", "getLastBitmap", "()Landroid/graphics/Bitmap;", "setLastBitmap", "(Landroid/graphics/Bitmap;)V", "lastCorners", "", "Lkotlin/Pair;", "", "getLastCorners", "()Ljava/util/List;", "setLastCorners", "(Ljava/util/List;)V", "app_debug"})
public final class ScanCache {
    @org.jetbrains.annotations.Nullable()
    private static android.graphics.Bitmap lastBitmap;
    @org.jetbrains.annotations.Nullable()
    private static com.neonscan.data.local.DocumentEntity currentDocument;
    @org.jetbrains.annotations.Nullable()
    private static java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> lastCorners;
    @org.jetbrains.annotations.NotNull()
    public static final com.neonscan.util.ScanCache INSTANCE = null;
    
    private ScanCache() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap getLastBitmap() {
        return null;
    }
    
    public final void setLastBitmap(@org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.neonscan.data.local.DocumentEntity getCurrentDocument() {
        return null;
    }
    
    public final void setCurrentDocument(@org.jetbrains.annotations.Nullable()
    com.neonscan.data.local.DocumentEntity p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> getLastCorners() {
        return null;
    }
    
    public final void setLastCorners(@org.jetbrains.annotations.Nullable()
    java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> p0) {
    }
}