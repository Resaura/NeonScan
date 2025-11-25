package com.neonscan.ui.scan;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a\u0016\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\u0016\u0010\n\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0003\u001a\u001a\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u00a8\u0006\u0013"}, d2 = {"CameraPreview", "", "onShot", "Lkotlin/Function1;", "Landroid/graphics/Bitmap;", "onCaptureCreated", "Landroidx/camera/core/ImageCapture;", "CaptureButton", "onClick", "Lkotlin/Function0;", "DocumentOverlay", "points", "", "Landroid/graphics/PointF;", "ScanScreen", "navController", "Landroidx/navigation/NavController;", "vm", "Lcom/neonscan/ui/scan/ScanViewModel;", "app_debug"})
public final class ScanScreenKt {
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ScanScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.neonscan.ui.scan.ScanViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CameraPreview(kotlin.jvm.functions.Function1<? super android.graphics.Bitmap, kotlin.Unit> onShot, kotlin.jvm.functions.Function1<? super androidx.camera.core.ImageCapture, kotlin.Unit> onCaptureCreated) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DocumentOverlay(java.util.List<? extends android.graphics.PointF> points) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CaptureButton(kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}