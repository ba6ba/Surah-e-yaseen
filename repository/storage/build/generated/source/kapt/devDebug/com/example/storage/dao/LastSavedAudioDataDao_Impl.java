package com.example.storage.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.data.lastsaved.LastSavedAudio;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LastSavedAudioDataDao_Impl implements LastSavedAudioDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LastSavedAudio> __insertionAdapterOfLastSavedAudio;

  public LastSavedAudioDataDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLastSavedAudio = new EntityInsertionAdapter<LastSavedAudio>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `LastSavedAudio` (`id`,`audioIndex`,`audioId`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LastSavedAudio value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getAudioIndex());
        if (value.getAudioId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAudioId());
        }
      }
    };
  }

  @Override
  public Object insert(final LastSavedAudio lastSavedAudio, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLastSavedAudio.insert(lastSavedAudio);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object get(final Continuation<? super LastSavedAudio> p0) {
    final String _sql = "SELECT * FROM LastSavedAudio";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<LastSavedAudio>() {
      @Override
      public LastSavedAudio call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAudioIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "audioIndex");
          final int _cursorIndexOfAudioId = CursorUtil.getColumnIndexOrThrow(_cursor, "audioId");
          final LastSavedAudio _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final int _tmpAudioIndex;
            _tmpAudioIndex = _cursor.getInt(_cursorIndexOfAudioIndex);
            final String _tmpAudioId;
            _tmpAudioId = _cursor.getString(_cursorIndexOfAudioId);
            _result = new LastSavedAudio(_tmpId,_tmpAudioIndex,_tmpAudioId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }
}
