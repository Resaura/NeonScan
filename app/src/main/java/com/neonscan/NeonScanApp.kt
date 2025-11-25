package com.neonscan

import android.app.Application
import com.google.android.gms.ads.MobileAds

class NeonScanApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}
