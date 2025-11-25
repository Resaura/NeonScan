package com.neonscan.ui.scan;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\rR7\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR/\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\r8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0019"}, d2 = {"Lcom/neonscan/ui/scan/ScanViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "<set-?>", "", "Landroid/graphics/PointF;", "detectedPolygon", "getDetectedPolygon", "()Ljava/util/List;", "setDetectedPolygon", "(Ljava/util/List;)V", "detectedPolygon$delegate", "Landroidx/compose/runtime/MutableState;", "Landroid/graphics/Bitmap;", "lastBitmap", "getLastBitmap", "()Landroid/graphics/Bitmap;", "setLastBitmap", "(Landroid/graphics/Bitmap;)V", "lastBitmap$delegate", "onDocumentDetected", "", "corners", "onShot", "bitmap", "app_debug"})
public final class ScanViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState detectedPolygon$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState lastBitmap$delegate = null;
    
    public ScanViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<android.graphics.PointF> getDetectedPolygon() {
        return null;
    }
    
    private final void setDetectedPolygon(java.util.List<? extends android.graphics.PointF> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap getLastBitmap() {
        return null;
    }
    
    private final void setLastBitmap(android.graphics.Bitmap p0) {
    }
    
    public final void onDocumentDetected(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.graphics.PointF> corners) {
    }
    
    public final void onShot(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
    }
}