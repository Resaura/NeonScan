package com.neonscan.ui.scan;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\"\u0010\b\u001a\u00020\u00012\u0018\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0003\u001a\"\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007\u001a\u001a\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0002\u001a\u0010\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0017H\u0002\u00a8\u0006\u0018"}, d2 = {"CameraPreview", "", "quality", "Lcom/neonscan/data/settings/ScanQuality;", "onCaptureCreated", "Lkotlin/Function2;", "Landroidx/camera/core/ImageCapture;", "Landroidx/camera/core/Camera;", "DocumentOverlay", "corners", "", "Lkotlin/Pair;", "", "ScanScreen", "navController", "Landroidx/navigation/NavController;", "settingsRepository", "Lcom/neonscan/data/settings/SettingsRepository;", "vm", "Lcom/neonscan/ui/scan/ScanViewModel;", "defaultFrame", "openGallery", "context", "Landroid/content/Context;", "app_debug"})
public final class ScanScreenKt {
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ScanScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.neonscan.data.settings.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.neonscan.ui.scan.ScanViewModel vm) {
    }
    
    private static final java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> defaultFrame() {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CameraPreview(com.neonscan.data.settings.ScanQuality quality, kotlin.jvm.functions.Function2<? super androidx.camera.core.ImageCapture, ? super androidx.camera.core.Camera, kotlin.Unit> onCaptureCreated) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DocumentOverlay(java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> corners) {
    }
    
    private static final void openGallery(android.content.Context context) {
    }
}