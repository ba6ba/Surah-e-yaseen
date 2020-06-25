package com.example.storage;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.storage.dao.AudioMediaDataDao;
import com.example.storage.dao.AudioMediaDataDao_Impl;
import com.example.storage.dao.SurahDao;
import com.example.storage.dao.SurahDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AudioMediaDataDao _audioMediaDataDao;

  private volatile SurahDao _surahDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AudioMediaData` (`id` TEXT NOT NULL, `data` TEXT, `genre` TEXT NOT NULL, `title` TEXT NOT NULL, `album` TEXT NOT NULL, `subTitle` TEXT NOT NULL, `metaData` TEXT, `authorData` TEXT, `imageMetaData` TEXT, `mediaMetaData` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LastSavedData` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `lastSavedAudio` TEXT NOT NULL, `lastSavedReadingContent` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e8daadf7e8a044955c84f87230d5927f')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `AudioMediaData`");
        _db.execSQL("DROP TABLE IF EXISTS `LastSavedData`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAudioMediaData = new HashMap<String, TableInfo.Column>(10);
        _columnsAudioMediaData.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("data", new TableInfo.Column("data", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("genre", new TableInfo.Column("genre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("album", new TableInfo.Column("album", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("subTitle", new TableInfo.Column("subTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("metaData", new TableInfo.Column("metaData", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("authorData", new TableInfo.Column("authorData", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("imageMetaData", new TableInfo.Column("imageMetaData", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioMediaData.put("mediaMetaData", new TableInfo.Column("mediaMetaData", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAudioMediaData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAudioMediaData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAudioMediaData = new TableInfo("AudioMediaData", _columnsAudioMediaData, _foreignKeysAudioMediaData, _indicesAudioMediaData);
        final TableInfo _existingAudioMediaData = TableInfo.read(_db, "AudioMediaData");
        if (! _infoAudioMediaData.equals(_existingAudioMediaData)) {
          return new RoomOpenHelper.ValidationResult(false, "AudioMediaData(com.example.data.audio.AudioMediaData).\n"
                  + " Expected:\n" + _infoAudioMediaData + "\n"
                  + " Found:\n" + _existingAudioMediaData);
        }
        final HashMap<String, TableInfo.Column> _columnsLastSavedData = new HashMap<String, TableInfo.Column>(3);
        _columnsLastSavedData.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLastSavedData.put("lastSavedAudio", new TableInfo.Column("lastSavedAudio", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLastSavedData.put("lastSavedReadingContent", new TableInfo.Column("lastSavedReadingContent", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLastSavedData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLastSavedData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLastSavedData = new TableInfo("LastSavedData", _columnsLastSavedData, _foreignKeysLastSavedData, _indicesLastSavedData);
        final TableInfo _existingLastSavedData = TableInfo.read(_db, "LastSavedData");
        if (! _infoLastSavedData.equals(_existingLastSavedData)) {
          return new RoomOpenHelper.ValidationResult(false, "LastSavedData(com.example.data.lastsaved.LastSavedData).\n"
                  + " Expected:\n" + _infoLastSavedData + "\n"
                  + " Found:\n" + _existingLastSavedData);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e8daadf7e8a044955c84f87230d5927f", "f3513eb4e480832a1833a8f43094a5c3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "AudioMediaData","LastSavedData");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `AudioMediaData`");
      _db.execSQL("DELETE FROM `LastSavedData`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public AudioMediaDataDao audioMediaDataDao() {
    if (_audioMediaDataDao != null) {
      return _audioMediaDataDao;
    } else {
      synchronized(this) {
        if(_audioMediaDataDao == null) {
          _audioMediaDataDao = new AudioMediaDataDao_Impl(this);
        }
        return _audioMediaDataDao;
      }
    }
  }

  @Override
  public SurahDao surahDao() {
    if (_surahDao != null) {
      return _surahDao;
    } else {
      synchronized(this) {
        if(_surahDao == null) {
          _surahDao = new SurahDao_Impl(this);
        }
        return _surahDao;
      }
    }
  }
}
