package com.neonscan.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/neonscan/util/AdsManager;", "", "()V", "TEST_BANNER", "", "TEST_INTERSTITIAL", "counter", "", "frequency", "getFrequency", "()I", "setFrequency", "(I)V", "interstitial", "Lcom/google/android/gms/ads/interstitial/InterstitialAd;", "loadAndShow", "", "activity", "Landroid/app/Activity;", "maybeShowInterstitial", "force", "", "app_debug"})
public final class AdsManager {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TEST_BANNER = "ca-app-pub-3940256099942544/6300978111";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TEST_INTERSTITIAL = "ca-app-pub-3940256099942544/1033173712";
    @org.jetbrains.annotations.Nullable()
    private static com.google.android.gms.ads.interstitial.InterstitialAd interstitial;
    private static int counter = 0;
    private static int frequency = 3;
    @org.jetbrains.annotations.NotNull()
    public static final com.neonscan.util.AdsManager INSTANCE = null;
    
    private AdsManager() {
        super();
    }
    
    public final int getFrequency() {
        return 0;
    }
    
    public final void setFrequency(int p0) {
    }
    
    public final void maybeShowInterstitial(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, boolean force) {
    }
    
    private final void loadAndShow(android.app.Activity activity) {
    }
}