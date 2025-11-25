package com.neonscan.util

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

object AdsManager {
    const val TEST_BANNER = "ca-app-pub-3940256099942544/6300978111"
    const val TEST_INTERSTITIAL = "ca-app-pub-3940256099942544/1033173712"
    private var interstitial: InterstitialAd? = null
    private var counter = 0
    var frequency: Int = 3

    fun maybeShowInterstitial(activity: Activity, force: Boolean = false) {
        counter++
        val limit = if (frequency <= 0) 3 else frequency
        if (!force && counter % limit != 0) return
        loadAndShow(activity)
    }

    private fun loadAndShow(activity: Activity) {
        InterstitialAd.load(
            activity,
            TEST_INTERSTITIAL,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitial = ad
                    ad.show(activity)
                }
            }
        )
    }
}

@Composable
fun AdMobBanner(adUnitId: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth().height(52.dp),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}
