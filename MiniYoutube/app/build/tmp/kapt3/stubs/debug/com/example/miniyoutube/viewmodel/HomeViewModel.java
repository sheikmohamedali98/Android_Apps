package com.example.miniyoutube.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/miniyoutube/viewmodel/HomeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_latitude", "Landroidx/lifecycle/MutableLiveData;", "", "_longtitude", "database", "Lcom/example/miniyoutube/database/VideoDatabase;", "latitude", "Landroidx/lifecycle/LiveData;", "getLatitude", "()Landroidx/lifecycle/LiveData;", "longtitude", "getLongtitude", "playList", "", "Lcom/example/miniyoutube/domain/VideoModel;", "getPlayList", "videoRepository", "Lcom/example/miniyoutube/repository/VideoRepository;", "displayPhoto", "", "it", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.AndroidViewModel {
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _latitude = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _longtitude = null;
    private final com.example.miniyoutube.database.VideoDatabase database = null;
    private final com.example.miniyoutube.repository.VideoRepository videoRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.miniyoutube.domain.VideoModel>> playList = null;
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getLatitude() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getLongtitude() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.miniyoutube.domain.VideoModel>> getPlayList() {
        return null;
    }
    
    public final void displayPhoto(@org.jetbrains.annotations.NotNull()
    com.example.miniyoutube.domain.VideoModel it) {
    }
}