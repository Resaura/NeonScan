package com.neonscan.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/neonscan/ui/home/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/neonscan/data/repo/DocumentRepository;", "(Lcom/neonscan/data/repo/DocumentRepository;)V", "docs", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/neonscan/data/local/DocumentEntity;", "getDocs", "()Lkotlinx/coroutines/flow/StateFlow;", "delete", "Lkotlinx/coroutines/Job;", "doc", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.neonscan.data.repo.DocumentRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.neonscan.data.local.DocumentEntity>> docs = null;
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.repo.DocumentRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.neonscan.data.local.DocumentEntity>> getDocs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(@org.jetbrains.annotations.NotNull()
    com.neonscan.data.local.DocumentEntity doc) {
        return null;
    }
}