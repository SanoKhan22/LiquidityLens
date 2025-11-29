package com.liquiditylens.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.liquiditylens.data.model.Candle;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CandleDao_Impl implements CandleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Candle> __insertionAdapterOfCandle;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldCandles;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public CandleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCandle = new EntityInsertionAdapter<Candle>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `candles` (`id`,`symbol`,`interval`,`openTime`,`open`,`high`,`low`,`close`,`volume`,`closeTime`,`isFinal`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Candle entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getSymbol() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSymbol());
        }
        if (entity.getInterval() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getInterval());
        }
        statement.bindLong(4, entity.getOpenTime());
        statement.bindDouble(5, entity.getOpen());
        statement.bindDouble(6, entity.getHigh());
        statement.bindDouble(7, entity.getLow());
        statement.bindDouble(8, entity.getClose());
        statement.bindDouble(9, entity.getVolume());
        statement.bindLong(10, entity.getCloseTime());
        final int _tmp = entity.isFinal() ? 1 : 0;
        statement.bindLong(11, _tmp);
      }
    };
    this.__preparedStmtOfDeleteOldCandles = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "\n"
                + "        DELETE FROM candles \n"
                + "        WHERE symbol = ? \n"
                + "        AND interval = ? \n"
                + "        AND openTime < ?\n"
                + "    ";
        return _query;
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM candles";
        return _query;
      }
    };
  }

  @Override
  public Object insertCandles(final List<Candle> candles,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCandle.insert(candles);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldCandles(final String symbol, final String interval, final long beforeTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldCandles.acquire();
        int _argIndex = 1;
        if (symbol == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, symbol);
        }
        _argIndex = 2;
        if (interval == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, interval);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, beforeTime);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldCandles.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getCandles(final String symbol, final String interval, final int limit,
      final Continuation<? super List<Candle>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM candles \n"
            + "        WHERE symbol = ? AND interval = ? \n"
            + "        ORDER BY openTime ASC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (symbol == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, symbol);
    }
    _argIndex = 2;
    if (interval == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, interval);
    }
    _argIndex = 3;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Candle>>() {
      @Override
      @NonNull
      public List<Candle> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfOpenTime = CursorUtil.getColumnIndexOrThrow(_cursor, "openTime");
          final int _cursorIndexOfOpen = CursorUtil.getColumnIndexOrThrow(_cursor, "open");
          final int _cursorIndexOfHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "high");
          final int _cursorIndexOfLow = CursorUtil.getColumnIndexOrThrow(_cursor, "low");
          final int _cursorIndexOfClose = CursorUtil.getColumnIndexOrThrow(_cursor, "close");
          final int _cursorIndexOfVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "volume");
          final int _cursorIndexOfCloseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "closeTime");
          final int _cursorIndexOfIsFinal = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinal");
          final List<Candle> _result = new ArrayList<Candle>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Candle _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final String _tmpInterval;
            if (_cursor.isNull(_cursorIndexOfInterval)) {
              _tmpInterval = null;
            } else {
              _tmpInterval = _cursor.getString(_cursorIndexOfInterval);
            }
            final long _tmpOpenTime;
            _tmpOpenTime = _cursor.getLong(_cursorIndexOfOpenTime);
            final double _tmpOpen;
            _tmpOpen = _cursor.getDouble(_cursorIndexOfOpen);
            final double _tmpHigh;
            _tmpHigh = _cursor.getDouble(_cursorIndexOfHigh);
            final double _tmpLow;
            _tmpLow = _cursor.getDouble(_cursorIndexOfLow);
            final double _tmpClose;
            _tmpClose = _cursor.getDouble(_cursorIndexOfClose);
            final double _tmpVolume;
            _tmpVolume = _cursor.getDouble(_cursorIndexOfVolume);
            final long _tmpCloseTime;
            _tmpCloseTime = _cursor.getLong(_cursorIndexOfCloseTime);
            final boolean _tmpIsFinal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFinal);
            _tmpIsFinal = _tmp != 0;
            _item = new Candle(_tmpId,_tmpSymbol,_tmpInterval,_tmpOpenTime,_tmpOpen,_tmpHigh,_tmpLow,_tmpClose,_tmpVolume,_tmpCloseTime,_tmpIsFinal);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Candle>> getCandlesFlow(final String symbol, final String interval,
      final int limit) {
    final String _sql = "\n"
            + "        SELECT * FROM candles \n"
            + "        WHERE symbol = ? AND interval = ? \n"
            + "        ORDER BY openTime ASC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (symbol == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, symbol);
    }
    _argIndex = 2;
    if (interval == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, interval);
    }
    _argIndex = 3;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"candles"}, new Callable<List<Candle>>() {
      @Override
      @NonNull
      public List<Candle> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfOpenTime = CursorUtil.getColumnIndexOrThrow(_cursor, "openTime");
          final int _cursorIndexOfOpen = CursorUtil.getColumnIndexOrThrow(_cursor, "open");
          final int _cursorIndexOfHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "high");
          final int _cursorIndexOfLow = CursorUtil.getColumnIndexOrThrow(_cursor, "low");
          final int _cursorIndexOfClose = CursorUtil.getColumnIndexOrThrow(_cursor, "close");
          final int _cursorIndexOfVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "volume");
          final int _cursorIndexOfCloseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "closeTime");
          final int _cursorIndexOfIsFinal = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinal");
          final List<Candle> _result = new ArrayList<Candle>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Candle _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final String _tmpInterval;
            if (_cursor.isNull(_cursorIndexOfInterval)) {
              _tmpInterval = null;
            } else {
              _tmpInterval = _cursor.getString(_cursorIndexOfInterval);
            }
            final long _tmpOpenTime;
            _tmpOpenTime = _cursor.getLong(_cursorIndexOfOpenTime);
            final double _tmpOpen;
            _tmpOpen = _cursor.getDouble(_cursorIndexOfOpen);
            final double _tmpHigh;
            _tmpHigh = _cursor.getDouble(_cursorIndexOfHigh);
            final double _tmpLow;
            _tmpLow = _cursor.getDouble(_cursorIndexOfLow);
            final double _tmpClose;
            _tmpClose = _cursor.getDouble(_cursorIndexOfClose);
            final double _tmpVolume;
            _tmpVolume = _cursor.getDouble(_cursorIndexOfVolume);
            final long _tmpCloseTime;
            _tmpCloseTime = _cursor.getLong(_cursorIndexOfCloseTime);
            final boolean _tmpIsFinal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFinal);
            _tmpIsFinal = _tmp != 0;
            _item = new Candle(_tmpId,_tmpSymbol,_tmpInterval,_tmpOpenTime,_tmpOpen,_tmpHigh,_tmpLow,_tmpClose,_tmpVolume,_tmpCloseTime,_tmpIsFinal);
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

  @Override
  public Object getCandlesInRange(final String symbol, final String interval, final long startTime,
      final long endTime, final Continuation<? super List<Candle>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM candles \n"
            + "        WHERE symbol = ? \n"
            + "        AND interval = ? \n"
            + "        AND openTime >= ? \n"
            + "        AND openTime <= ?\n"
            + "        ORDER BY openTime ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (symbol == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, symbol);
    }
    _argIndex = 2;
    if (interval == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, interval);
    }
    _argIndex = 3;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 4;
    _statement.bindLong(_argIndex, endTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Candle>>() {
      @Override
      @NonNull
      public List<Candle> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfOpenTime = CursorUtil.getColumnIndexOrThrow(_cursor, "openTime");
          final int _cursorIndexOfOpen = CursorUtil.getColumnIndexOrThrow(_cursor, "open");
          final int _cursorIndexOfHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "high");
          final int _cursorIndexOfLow = CursorUtil.getColumnIndexOrThrow(_cursor, "low");
          final int _cursorIndexOfClose = CursorUtil.getColumnIndexOrThrow(_cursor, "close");
          final int _cursorIndexOfVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "volume");
          final int _cursorIndexOfCloseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "closeTime");
          final int _cursorIndexOfIsFinal = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinal");
          final List<Candle> _result = new ArrayList<Candle>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Candle _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final String _tmpInterval;
            if (_cursor.isNull(_cursorIndexOfInterval)) {
              _tmpInterval = null;
            } else {
              _tmpInterval = _cursor.getString(_cursorIndexOfInterval);
            }
            final long _tmpOpenTime;
            _tmpOpenTime = _cursor.getLong(_cursorIndexOfOpenTime);
            final double _tmpOpen;
            _tmpOpen = _cursor.getDouble(_cursorIndexOfOpen);
            final double _tmpHigh;
            _tmpHigh = _cursor.getDouble(_cursorIndexOfHigh);
            final double _tmpLow;
            _tmpLow = _cursor.getDouble(_cursorIndexOfLow);
            final double _tmpClose;
            _tmpClose = _cursor.getDouble(_cursorIndexOfClose);
            final double _tmpVolume;
            _tmpVolume = _cursor.getDouble(_cursorIndexOfVolume);
            final long _tmpCloseTime;
            _tmpCloseTime = _cursor.getLong(_cursorIndexOfCloseTime);
            final boolean _tmpIsFinal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFinal);
            _tmpIsFinal = _tmp != 0;
            _item = new Candle(_tmpId,_tmpSymbol,_tmpInterval,_tmpOpenTime,_tmpOpen,_tmpHigh,_tmpLow,_tmpClose,_tmpVolume,_tmpCloseTime,_tmpIsFinal);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestCandle(final String symbol, final String interval,
      final Continuation<? super Candle> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM candles \n"
            + "        WHERE symbol = ? AND interval = ? \n"
            + "        ORDER BY openTime DESC \n"
            + "        LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (symbol == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, symbol);
    }
    _argIndex = 2;
    if (interval == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, interval);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Candle>() {
      @Override
      @Nullable
      public Candle call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfOpenTime = CursorUtil.getColumnIndexOrThrow(_cursor, "openTime");
          final int _cursorIndexOfOpen = CursorUtil.getColumnIndexOrThrow(_cursor, "open");
          final int _cursorIndexOfHigh = CursorUtil.getColumnIndexOrThrow(_cursor, "high");
          final int _cursorIndexOfLow = CursorUtil.getColumnIndexOrThrow(_cursor, "low");
          final int _cursorIndexOfClose = CursorUtil.getColumnIndexOrThrow(_cursor, "close");
          final int _cursorIndexOfVolume = CursorUtil.getColumnIndexOrThrow(_cursor, "volume");
          final int _cursorIndexOfCloseTime = CursorUtil.getColumnIndexOrThrow(_cursor, "closeTime");
          final int _cursorIndexOfIsFinal = CursorUtil.getColumnIndexOrThrow(_cursor, "isFinal");
          final Candle _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final String _tmpInterval;
            if (_cursor.isNull(_cursorIndexOfInterval)) {
              _tmpInterval = null;
            } else {
              _tmpInterval = _cursor.getString(_cursorIndexOfInterval);
            }
            final long _tmpOpenTime;
            _tmpOpenTime = _cursor.getLong(_cursorIndexOfOpenTime);
            final double _tmpOpen;
            _tmpOpen = _cursor.getDouble(_cursorIndexOfOpen);
            final double _tmpHigh;
            _tmpHigh = _cursor.getDouble(_cursorIndexOfHigh);
            final double _tmpLow;
            _tmpLow = _cursor.getDouble(_cursorIndexOfLow);
            final double _tmpClose;
            _tmpClose = _cursor.getDouble(_cursorIndexOfClose);
            final double _tmpVolume;
            _tmpVolume = _cursor.getDouble(_cursorIndexOfVolume);
            final long _tmpCloseTime;
            _tmpCloseTime = _cursor.getLong(_cursorIndexOfCloseTime);
            final boolean _tmpIsFinal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFinal);
            _tmpIsFinal = _tmp != 0;
            _result = new Candle(_tmpId,_tmpSymbol,_tmpInterval,_tmpOpenTime,_tmpOpen,_tmpHigh,_tmpLow,_tmpClose,_tmpVolume,_tmpCloseTime,_tmpIsFinal);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
