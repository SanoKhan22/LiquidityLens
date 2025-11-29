package com.liquiditylens.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CandleDatabase_Impl extends CandleDatabase {
  private volatile CandleDao _candleDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `candles` (`id` TEXT NOT NULL, `symbol` TEXT NOT NULL, `interval` TEXT NOT NULL, `openTime` INTEGER NOT NULL, `open` REAL NOT NULL, `high` REAL NOT NULL, `low` REAL NOT NULL, `close` REAL NOT NULL, `volume` REAL NOT NULL, `closeTime` INTEGER NOT NULL, `isFinal` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7af44104790834aaefbb99ecbae6c6bc')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `candles`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCandles = new HashMap<String, TableInfo.Column>(11);
        _columnsCandles.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("symbol", new TableInfo.Column("symbol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("interval", new TableInfo.Column("interval", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("openTime", new TableInfo.Column("openTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("open", new TableInfo.Column("open", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("high", new TableInfo.Column("high", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("low", new TableInfo.Column("low", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("close", new TableInfo.Column("close", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("volume", new TableInfo.Column("volume", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("closeTime", new TableInfo.Column("closeTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCandles.put("isFinal", new TableInfo.Column("isFinal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCandles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCandles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCandles = new TableInfo("candles", _columnsCandles, _foreignKeysCandles, _indicesCandles);
        final TableInfo _existingCandles = TableInfo.read(db, "candles");
        if (!_infoCandles.equals(_existingCandles)) {
          return new RoomOpenHelper.ValidationResult(false, "candles(com.liquiditylens.data.model.Candle).\n"
                  + " Expected:\n" + _infoCandles + "\n"
                  + " Found:\n" + _existingCandles);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7af44104790834aaefbb99ecbae6c6bc", "154d8d5eba91d3bdacb1189d33eb7dfe");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "candles");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `candles`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CandleDao.class, CandleDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CandleDao candleDao() {
    if (_candleDao != null) {
      return _candleDao;
    } else {
      synchronized(this) {
        if(_candleDao == null) {
          _candleDao = new CandleDao_Impl(this);
        }
        return _candleDao;
      }
    }
  }
}
