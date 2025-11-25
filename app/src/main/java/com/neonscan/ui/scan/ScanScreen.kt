package com.neonscan.ui.scan

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PointF
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.PhotoLibrary
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.tasks.TaskExecutors
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.documentscanner.DocumentScanner
import com.google.mlkit.vision.documentscanner.DocumentScannerOptions
import com.neonscan.data.settings.ScanQuality
import com.neonscan.ui.components.NeonPrimaryButton
import com.neonscan.ui.theme.NeonApple
import com.neonscan.ui.theme.NeonBlack
import com.neonscan.util.ScanCache
import com.neonscan.util.toBitmap
import kotlinx.coroutines.delay
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanScreen(navController: NavController, settingsRepository: com.neonscan.data.settings.SettingsRepository, vm: ScanViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
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
    var stableFrames by remember { mutableStateOf(0) }
    val progress by animateFloatAsState(targetValue = stableFrames / 30f, label = "autoProgress")

    // auto capture when stable
    LaunchedEffect(stableFrames) {
        if (stableFrames >= 30) {
            capture?.let { cap ->
                cap.takePicture(
                    TaskExecutors.MAIN_THREAD,
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
                    }
                )
            }
            stableFrames = 0
        }
    }

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
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(onClick = { /* TODO: galerie */ }) {
                    Icon(Icons.Filled.PhotoLibrary, contentDescription = "Galerie", tint = NeonApple)
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    capture?.let { imageCapture ->
                        imageCapture.flashMode = if (flashEnabled) ImageCapture.FLASH_MODE_OFF else ImageCapture.FLASH_MODE_ON
                        flashEnabled = !flashEnabled
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
                onPolygon = { points ->
                    vm.onDocumentDetected(points)
                    if (points.isNotEmpty()) stableFrames++ else stableFrames = 0
                },
                onCaptureCreated = { capture = it }
            )
            DocumentOverlay(points = vm.detectedPolygon, progress = progress)
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
    onPolygon: (List<PointF>) -> Unit,
    onCaptureCreated: (ImageCapture) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
    val options = remember {
        DocumentScannerOptions.Builder()
            .setResultFormats(DocumentScannerOptions.RESULT_FORMAT_JPEG)
            .build()
    }
    val scanner = remember { DocumentScanner.getClient(options) }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val previewView = PreviewView(context)
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
                val analyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also { analysis ->
                        analysis.setAnalyzer(cameraExecutor) { proxy ->
                            val mediaImage = proxy.image
                            if (mediaImage != null) {
                                val image = InputImage.fromMediaImage(mediaImage, proxy.imageInfo.rotationDegrees)
                                scanner.process(image)
                                    .addOnSuccessListener { result ->
                                        val corners = result.pages.firstOrNull()?.documentCorners
                                        if (corners != null) onPolygon(corners.map { PointF(it.x, it.y) }) else onPolygon(emptyList())
                                    }
                                    .addOnCompleteListener { proxy.close() }
                            } else {
                                proxy.close()
                            }
                        }
                    }
                val selector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        selector,
                        preview,
                        imageCapture,
                        analyzer
                    )
                    onCaptureCreated(imageCapture)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(context))
            previewView
        }
    )
}

@Composable
private fun DocumentOverlay(points: List<PointF>, progress: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        if (points.size >= 4) {
            val path = Path().apply {
                moveTo(points[0].x, points[0].y)
                points.drop(1).forEach { lineTo(it.x, it.y) }
                close()
            }
            drawPath(path, color = NeonApple, style = Stroke(width = 4.dp.toPx()))
        } else {
            val paint = Paint().apply {
                color = android.graphics.Color.DKGRAY
                textSize = 38f
            }
            drawContext.canvas.nativeCanvas.drawText(
                "En attente de détection",
                size.width / 3,
                size.height - 120f,
                paint
            )
        }
        if (progress > 0f) {
            drawContext.canvas.nativeCanvas.drawText(
                "Stabilisation...",
                size.width / 2.5f,
                size.height - 160f,
                Paint().apply { color = android.graphics.Color.WHITE; textSize = 32f }
            )
            drawCircle(color = NeonApple, radius = 18.dp.toPx() * progress, center = androidx.compose.ui.geometry.Offset(size.width / 2, size.height - 210f), style = Stroke(width = 3.dp.toPx()))
        }
    }
}
