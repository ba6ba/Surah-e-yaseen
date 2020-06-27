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
import com.example.storage.dao.LastSavedAudioDataDao;
import com.example.storage.dao.LastSavedAudioDataDao_Impl;
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

  private volatile LastSavedAudioDataDao _lastSavedAudioDataDao;

  private volatile SurahDao _surahDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AudioMediaData` (`id` TEXT NOT NULL, `data` TEXT, `genre` TEXT NOT NULL, `title` TEXT NOT NULL, `album` TEXT NOT NULL, `subTitle` TEXT NOT NULL, `metaData` TEXT, `authorData` TEXT, `imageMetaData` TEXT, `mediaMetaData` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LastSavedAudio` (`id` TEXT NOT NULL, `audioIndex` INTEGER NOT NULL, `audioId` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LastSavedReadingContent` (`id` INTEGER NOT NULL, `pageNumber` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '45c9cfe68ffc810a56a91bee28633273')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `AudioMediaData`");
        _db.execSQL("DROP TABLE IF EXISTS `LastSavedAudio`");
        _db.execSQL("DROP TABLE IF EXISTS `LastSavedReadingContent`");
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
        final HashMap<String, TableInfo.Column> _columnsLastSavedAudio = new HashMap<String, TableInfo.Column>(3);
        _columnsLastSavedAudio.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLastSavedAudio.put("audioIndex", new TableInfo.Column("audioIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLastSavedAudio.put("audioId", new TableInfo.Column("audioId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLastSavedAudio = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLastSavedAudio = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLastSavedAudio = new TableInfo("LastSavedAudio", _columnsLastSavedAudio, _foreignKeysLastSavedAudio, _indicesLastSavedAudio);
        final TableInfo _existingLastSavedAudio = TableInfo.read(_db, "LastSavedAudio");
        if (! _infoLastSavedAudio.equals(_existingLastSavedAudio)) {
          return new RoomOpenHelper.ValidationResult(false, "LastSavedAudio(com.example.data.lastsaved.LastSavedAudio).\n"
                  + " Expected:\n" + _infoLastSavedAudio + "\n"
                  + " Found:\n" + _existingLastSavedAudio);
        }
        final HashMap<String, TableInfo.Column> _columnsLastSavedReadingContent = new HashMap<String, TableInfo.Column>(2);
        _columnsLastSavedReadingContent.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLastSavedReadingContent.put("pageNumber", new TableInfo.Column("pageNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLastSavedReadingContent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLastSavedReadingContent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLastSavedReadingContent = new TableInfo("LastSavedReadingContent", _columnsLastSavedReadingContent, _foreignKeysLastSavedReadingContent, _indicesLastSavedReadingContent);
        final TableInfo _existingLastSavedReadingContent = TableInfo.read(_db, "LastSavedReadingContent");
        if (! _infoLastSavedReadingContent.equals(_existingLastSavedReadingContent)) {
          return new RoomOpenHelper.ValidationResult(false, "LastSavedReadingContent(com.example.data.lastsaved.LastSavedReadingContent).\n"
                  + " Expected:\n" + _infoLastSavedReadingContent + "\n"
                  + " Found:\n" + _existingLastSavedReadingContent);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "45c9cfe68ffc810a56a91bee28633273", "0223a7d4ef292b517d6440691b10da60");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "AudioMediaData","LastSavedAudio","LastSavedReadingContent");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `AudioMediaData`");
      _db.execSQL("DELETE FROM `LastSavedAudio`");
      _db.execSQL("DELETE FROM `LastSavedReadingContent`");
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
  public LastSavedAudioDataDao lastSavedAudioDataDao() {
    if (_lastSavedAudioDataDao != null) {
      return _lastSavedAudioDataDao;
    } else {
      synchronized(this) {
        if(_lastSavedAudioDataDao == null) {
          _lastSavedAudioDataDao = new LastSavedAudioDataDao_Impl(this);
        }
        return _lastSavedAudioDataDao;
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
