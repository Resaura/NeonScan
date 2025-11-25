package com.neonscan.ui.scan;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\b\u0010\b\u001a\u00020\u0001H\u0003\u001a\"\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u00a8\u0006\u0013"}, d2 = {"CameraPreview", "", "quality", "Lcom/neonscan/data/settings/ScanQuality;", "onCaptureCreated", "Lkotlin/Function2;", "Landroidx/camera/core/ImageCapture;", "Landroidx/camera/core/Camera;", "DocumentOverlay", "ScanScreen", "navController", "Landroidx/navigation/NavController;", "settingsRepository", "Lcom/neonscan/data/settings/SettingsRepository;", "vm", "Lcom/neonscan/ui/scan/ScanViewModel;", "openGallery", "context", "Landroid/content/Context;", "app_debug"})
public final class ScanScreenKt {
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ScanScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.neonscan.data.settings.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.neonscan.ui.scan.ScanViewModel vm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CameraPreview(com.neonscan.data.settings.ScanQuality quality, kotlin.jvm.functions.Function2<? super androidx.camera.core.ImageCapture, ? super androidx.camera.core.Camera, kotlin.Unit> onCaptureCreated) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DocumentOverlay() {
    }
    
    private static final void openGallery(android.content.Context context) {
    }
}