package com.example.miniyoutube.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0001X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/example/miniyoutube/database/DatabaseInstance;", "Lcom/example/miniyoutube/database/RoomDao;", "()V", "roomDao", "getRoomDao", "()Lcom/example/miniyoutube/database/RoomDao;", "app_debug"})
public abstract class DatabaseInstance implements com.example.miniyoutube.database.RoomDao {
    
    public DatabaseInstance() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.miniyoutube.database.RoomDao getRoomDao();
}