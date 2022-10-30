package com.example.miniyoutube.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.miniyoutube.DatabaseVideo;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RoomDao_Impl implements RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DatabaseVideo> __insertionAdapterOfDatabaseVideo;

  public RoomDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDatabaseVideo = new EntityInsertionAdapter<DatabaseVideo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `DatabaseVideo` (`id`,`name`,`tn_img`,`video_url`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DatabaseVideo value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getTn_img() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTn_img());
        }
        if (value.getVideo_url() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getVideo_url());
        }
      }
    };
  }

  @Override
  public void insertAll(final DatabaseVideo... videoModel) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDatabaseVideo.insert(videoModel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<DatabaseVideo>> getVideo() {
    final String _sql = "SELECT * FROM databasevideo";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"databasevideo"}, false, new Callable<List<DatabaseVideo>>() {
      @Override
      public List<DatabaseVideo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfTnImg = CursorUtil.getColumnIndexOrThrow(_cursor, "tn_img");
          final int _cursorIndexOfVideoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "video_url");
          final List<DatabaseVideo> _result = new ArrayList<DatabaseVideo>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseVideo _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpTn_img;
            if (_cursor.isNull(_cursorIndexOfTnImg)) {
              _tmpTn_img = null;
            } else {
              _tmpTn_img = _cursor.getString(_cursorIndexOfTnImg);
            }
            final String _tmpVideo_url;
            if (_cursor.isNull(_cursorIndexOfVideoUrl)) {
              _tmpVideo_url = null;
            } else {
              _tmpVideo_url = _cursor.getString(_cursorIndexOfVideoUrl);
            }
            _item = new DatabaseVideo(_tmpId,_tmpName,_tmpTn_img,_tmpVideo_url);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
