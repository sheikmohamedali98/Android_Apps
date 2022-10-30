package com.example.miniyoutube.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J!\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\t\"\u00020\u0005H\'\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/miniyoutube/database/RoomDao;", "", "getVideo", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/miniyoutube/DatabaseVideo;", "insertAll", "", "videoModel", "", "([Lcom/example/miniyoutube/DatabaseVideo;)V", "app_debug"})
public abstract interface RoomDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM databasevideo")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.miniyoutube.DatabaseVideo>> getVideo();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAll(@org.jetbrains.annotations.NotNull()
    com.example.miniyoutube.DatabaseVideo... videoModel);
}