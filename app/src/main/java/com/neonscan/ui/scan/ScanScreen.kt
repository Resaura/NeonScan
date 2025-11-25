package com.neonscan.ui.scan

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import android.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.neonscan.data.settings.ScanQuality
import com.neonscan.ui.components.NeonPrimaryButton
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.util.ScanCache
import com.neonscan.util.toBitmap

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(
    navController: NavController,
    settingsRepository: com.neonscan.data.settings.SettingsRepository,
    vm: ScanViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val permission = rememberPermissionState(Manifest.permission.CAMERA)
    LaunchedEffect(Unit) { permission.launchPermissionRequest() }
    val context = LocalContext.current
    val mainExecutor = remember { ContextCompat.getMainExecutor(context) }
    var quality by remember { mutableStateOf(ScanQuality.STANDARD) }
    LaunchedEffect(Unit) {
        settingsRepository.settings.collect { settings ->
            quality = settings.quality
            com.neonscan.util.AdsManager.frequency = settings.interstitialFrequency
        }
    }

    if (!permission.status.isGranted) {
        Box(modifier = Modifier.fillMaxSize().background(NeonBlack), contentAlignment = Alignment.Center) {
            Text("Autorisez la caméra pour continuer", color = Color.White)
        }
        return
    }

    var flashEnabled by remember { mutableStateOf(false) }
    var capture: ImageCapture? by remember { mutableStateOf(null) }
    var camera by remember { mutableStateOf<Camera?>(null) }

    Scaffold(
        containerColor = NeonBlack,
        topBar = {
            TopAppBar(
                title = { Text("Scanner", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Fermer", tint = NeonApple)
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .fillMaxWidth()
                    .height(72.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                val galleryContext = context
                IconButton(onClick = { openGallery(galleryContext) }) {
                    Icon(Icons.Filled.PhotoLibrary, contentDescription = "Galerie", tint = NeonApple)
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    capture?.let { imageCapture ->
                        flashEnabled = !flashEnabled
                        imageCapture.flashMode = if (flashEnabled) ImageCapture.FLASH_MODE_ON else ImageCapture.FLASH_MODE_OFF
                        camera?.cameraControl?.enableTorch(flashEnabled)
                    }
                }) {
                    Icon(if (flashEnabled) Icons.Filled.FlashOn else Icons.Filled.FlashOff, contentDescription = "Flash", tint = NeonApple)
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            CameraPreview(
                quality = quality,
                onCaptureCreated = { cap, cam ->
                    capture = cap
                    camera = cam
                }
            )
            DocumentOverlay()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(bottom = 72.dp)) {
                    Text("Alignez le document dans le cadre", color = Color.White)
                    Spacer(Modifier.height(8.dp))
                    NeonPrimaryButton(text = "CAPTURE", onClick = {
                        capture?.takePicture(
                            mainExecutor,
                            object : ImageCapture.OnImageCapturedCallback() {
                                override fun onCaptureSuccess(image: ImageProxy) {
                                    val bmp = image.toBitmap()
                                    if (bmp != null) {
                                        vm.onShot(bmp)
                                        ScanCache.lastBitmap = bmp
                                        navController.navigate("edit")
                                    }
                                    image.close()
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    super.onError(exception)
                                }
                            }
                        )
                    })
                }
            }
        }
    }
}

@Composable
private fun CameraPreview(
    quality: ScanQuality,
    onCaptureCreated: (ImageCapture, Camera) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val previewView = PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val captureQuality = when (quality) {
                    ScanQuality.LOW -> 70
                    ScanQuality.STANDARD -> 85
                    ScanQuality.HIGH -> 100
                }
                val imageCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .setJpegQuality(captureQuality)
                    .build()
                val selector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    val camera = cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        selector,
                        preview,
                        imageCapture
                    )
                    onCaptureCreated(imageCapture, camera)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(context))
            previewView
        }
    )
}

@Composable
private fun DocumentOverlay() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val paint = Paint().apply {
            color = android.graphics.Color.DKGRAY
            textSize = 38f
        }
        drawContext.canvas.nativeCanvas.drawText(
            "Cadrez le document",
            size.width / 3,
            size.height - 120f,
            paint
        )
    }
}

private fun openGallery(context: android.content.Context) {
    val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }
    val chooser = Intent.createChooser(intent, "Choisir une image")
    if (context is Activity) context.startActivity(chooser) else {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(chooser)
    }
}
