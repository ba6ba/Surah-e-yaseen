package com.example.storage.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.data.audio.AudioMediaData;
import com.example.storage.converters.AuthorDataConverter;
import com.example.storage.converters.DataConverter;
import com.example.storage.converters.ImageMetaDataConverter;
import com.example.storage.converters.MediaMetaDataConverter;
import com.example.storage.converters.MetaDataConverter;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AudioMediaDataDao_Impl implements AudioMediaDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AudioMediaData> __insertionAdapterOfAudioMediaData;

  private final DataConverter __dataConverter = new DataConverter();

  private final MetaDataConverter __metaDataConverter = new MetaDataConverter();

  private final AuthorDataConverter __authorDataConverter = new AuthorDataConverter();

  private final ImageMetaDataConverter __imageMetaDataConverter = new ImageMetaDataConverter();

  private final MediaMetaDataConverter __mediaMetaDataConverter = new MediaMetaDataConverter();

  private final EntityDeletionOrUpdateAdapter<AudioMediaData> __updateAdapterOfAudioMediaData;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  public AudioMediaDataDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAudioMediaData = new EntityInsertionAdapter<AudioMediaData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `AudioMediaData` (`id`,`data`,`genre`,`title`,`album`,`subTitle`,`metaData`,`authorData`,`imageMetaData`,`mediaMetaData`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AudioMediaData value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        final String _tmp;
        _tmp = __dataConverter.fromList(value.getData());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        if (value.getGenre() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGenre());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        if (value.getAlbum() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAlbum());
        }
        if (value.getSubTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSubTitle());
        }
        final String _tmp_1;
        _tmp_1 = __metaDataConverter.fromList(value.getMetaData());
        if (_tmp_1 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = __authorDataConverter.fromList(value.getAuthorData());
        if (_tmp_2 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = __imageMetaDataConverter.fromList(value.getImageMetaData());
        if (_tmp_3 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, _tmp_3);
        }
        final String _tmp_4;
        _tmp_4 = __mediaMetaDataConverter.fromList(value.getMediaMetaData());
        if (_tmp_4 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp_4);
        }
      }
    };
    this.__updateAdapterOfAudioMediaData = new EntityDeletionOrUpdateAdapter<AudioMediaData>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `AudioMediaData` SET `id` = ?,`data` = ?,`genre` = ?,`title` = ?,`album` = ?,`subTitle` = ?,`metaData` = ?,`authorData` = ?,`imageMetaData` = ?,`mediaMetaData` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AudioMediaData value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        final String _tmp;
        _tmp = __dataConverter.fromList(value.getData());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        if (value.getGenre() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGenre());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        if (value.getAlbum() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAlbum());
        }
        if (value.getSubTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSubTitle());
        }
        final String _tmp_1;
        _tmp_1 = __metaDataConverter.fromList(value.getMetaData());
        if (_tmp_1 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = __authorDataConverter.fromList(value.getAuthorData());
        if (_tmp_2 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = __imageMetaDataConverter.fromList(value.getImageMetaData());
        if (_tmp_3 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, _tmp_3);
        }
        final String _tmp_4;
        _tmp_4 = __mediaMetaDataConverter.fromList(value.getMediaMetaData());
        if (_tmp_4 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp_4);
        }
        if (value.getId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getId());
        }
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE AudioMediaData SET authorData = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object add(final AudioMediaData audioMediaData, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAudioMediaData.insert(audioMediaData);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object addAll(final List<AudioMediaData> listAudioMediaData,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAudioMediaData.insert(listAudioMediaData);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object updateAll(final List<AudioMediaData> listAudioMediaData,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAudioMediaData.handleMultiple(listAudioMediaData);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object update(final AudioMediaData.AuthorData authorData, final String audioId,
      final Continuation<? super Unit> p2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdate.acquire();
        int _argIndex = 1;
        final String _tmp;
        _tmp = __authorDataConverter.fromList(authorData);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (audioId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, audioId);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdate.release(_stmt);
        }
      }
    }, p2);
  }

  @Override
  public Object get(final String id, final Continuation<? super AudioMediaData> p1) {
    final String _sql = "SELECT * FROM AudioMediaData WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return CoroutinesRoom.execute(__db, false, new Callable<AudioMediaData>() {
      @Override
      public AudioMediaData call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "album");
          final int _cursorIndexOfSubTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "subTitle");
          final int _cursorIndexOfMetaData = CursorUtil.getColumnIndexOrThrow(_cursor, "metaData");
          final int _cursorIndexOfAuthorData = CursorUtil.getColumnIndexOrThrow(_cursor, "authorData");
          final int _cursorIndexOfImageMetaData = CursorUtil.getColumnIndexOrThrow(_cursor, "imageMetaData");
          final int _cursorIndexOfMediaMetaData = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaMetaData");
          final AudioMediaData _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final AudioMediaData.Data _tmpData;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfData);
            _tmpData = __dataConverter.fromString(_tmp);
            final String _tmpGenre;
            _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpAlbum;
            _tmpAlbum = _cursor.getString(_cursorIndexOfAlbum);
            final String _tmpSubTitle;
            _tmpSubTitle = _cursor.getString(_cursorIndexOfSubTitle);
            final AudioMediaData.MetaData _tmpMetaData;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfMetaData);
            _tmpMetaData = __metaDataConverter.fromString(_tmp_1);
            final AudioMediaData.AuthorData _tmpAuthorData;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfAuthorData);
            _tmpAuthorData = __authorDataConverter.fromString(_tmp_2);
            final AudioMediaData.ImageMetaData _tmpImageMetaData;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfImageMetaData);
            _tmpImageMetaData = __imageMetaDataConverter.fromString(_tmp_3);
            final AudioMediaData.MediaMetaData _tmpMediaMetaData;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfMediaMetaData);
            _tmpMediaMetaData = __mediaMetaDataConverter.fromString(_tmp_4);
            _result = new AudioMediaData(_tmpId,_tmpData,_tmpGenre,_tmpTitle,_tmpAlbum,_tmpSubTitle,_tmpMetaData,_tmpAuthorData,_tmpImageMetaData,_tmpMediaMetaData);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }

  @Override
  public Object getAll(final Continuation<? super List<AudioMediaData>> p0) {
    final String _sql = "SELECT * FROM AudioMediaData";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.execute(__db, false, new Callable<List<AudioMediaData>>() {
      @Override
      public List<AudioMediaData> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "album");
          final int _cursorIndexOfSubTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "subTitle");
          final int _cursorIndexOfMetaData = CursorUtil.getColumnIndexOrThrow(_cursor, "metaData");
          final int _cursorIndexOfAuthorData = CursorUtil.getColumnIndexOrThrow(_cursor, "authorData");
          final int _cursorIndexOfImageMetaData = CursorUtil.getColumnIndexOrThrow(_cursor, "imageMetaData");
          final int _cursorIndexOfMediaMetaData = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaMetaData");
          final List<AudioMediaData> _result = new ArrayList<AudioMediaData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final AudioMediaData _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final AudioMediaData.Data _tmpData;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfData);
            _tmpData = __dataConverter.fromString(_tmp);
            final String _tmpGenre;
            _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpAlbum;
            _tmpAlbum = _cursor.getString(_cursorIndexOfAlbum);
            final String _tmpSubTitle;
            _tmpSubTitle = _cursor.getString(_cursorIndexOfSubTitle);
            final AudioMediaData.MetaData _tmpMetaData;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfMetaData);
            _tmpMetaData = __metaDataConverter.fromString(_tmp_1);
            final AudioMediaData.AuthorData _tmpAuthorData;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfAuthorData);
            _tmpAuthorData = __authorDataConverter.fromString(_tmp_2);
            final AudioMediaData.ImageMetaData _tmpImageMetaData;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfImageMetaData);
            _tmpImageMetaData = __imageMetaDataConverter.fromString(_tmp_3);
            final AudioMediaData.MediaMetaData _tmpMediaMetaData;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfMediaMetaData);
            _tmpMediaMetaData = __mediaMetaDataConverter.fromString(_tmp_4);
            _item = new AudioMediaData(_tmpId,_tmpData,_tmpGenre,_tmpTitle,_tmpAlbum,_tmpSubTitle,_tmpMetaData,_tmpAuthorData,_tmpImageMetaData,_tmpMediaMetaData);
            _result.add(_item);
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
