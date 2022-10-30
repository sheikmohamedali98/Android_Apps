package com.example.miniyoutube.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000e2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u000e\u000f\u0010B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0011"}, d2 = {"Lcom/example/miniyoutube/adapter/VideoAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/miniyoutube/domain/VideoModel;", "Lcom/example/miniyoutube/adapter/VideoAdapter$VideoViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallBack", "OnClickListner", "VideoViewHolder", "app_debug"})
public final class VideoAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.miniyoutube.domain.VideoModel, com.example.miniyoutube.adapter.VideoAdapter.VideoViewHolder> {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.miniyoutube.adapter.VideoAdapter.DiffCallBack DiffCallBack = null;
    
    public VideoAdapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.miniyoutube.adapter.VideoAdapter.VideoViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.miniyoutube.adapter.VideoAdapter.VideoViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/miniyoutube/adapter/VideoAdapter$VideoViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/miniyoutube/databinding/LinearLayoutBinding;", "(Lcom/example/miniyoutube/databinding/LinearLayoutBinding;)V", "bind", "", "videoModel", "Lcom/example/miniyoutube/domain/VideoModel;", "app_debug"})
    public static final class VideoViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private com.example.miniyoutube.databinding.LinearLayoutBinding binding;
        
        public VideoViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.miniyoutube.databinding.LinearLayoutBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.miniyoutube.domain.VideoModel videoModel) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B(\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004R,\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/example/miniyoutube/adapter/VideoAdapter$OnClickListner;", "", "clickListner", "Lkotlin/Function1;", "Lcom/example/miniyoutube/domain/VideoModel;", "Lkotlin/ParameterName;", "name", "videoModel", "", "(Lkotlin/jvm/functions/Function1;)V", "getClickListner", "()Lkotlin/jvm/functions/Function1;", "onClick", "app_debug"})
    public static final class OnClickListner {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.example.miniyoutube.domain.VideoModel, kotlin.Unit> clickListner = null;
        
        public OnClickListner(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.example.miniyoutube.domain.VideoModel, kotlin.Unit> clickListner) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlin.jvm.functions.Function1<com.example.miniyoutube.domain.VideoModel, kotlin.Unit> getClickListner() {
            return null;
        }
        
        public final void onClick(@org.jetbrains.annotations.NotNull()
        com.example.miniyoutube.domain.VideoModel videoModel) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/miniyoutube/adapter/VideoAdapter$DiffCallBack;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/miniyoutube/domain/VideoModel;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallBack extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.miniyoutube.domain.VideoModel> {
        
        private DiffCallBack() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.miniyoutube.domain.VideoModel oldItem, @org.jetbrains.annotations.NotNull()
        com.example.miniyoutube.domain.VideoModel newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.example.miniyoutube.domain.VideoModel oldItem, @org.jetbrains.annotations.NotNull()
        com.example.miniyoutube.domain.VideoModel newItem) {
            return false;
        }
    }
}