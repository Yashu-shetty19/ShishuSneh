package com.shishusneh.app.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.shishusneh.app.db.entity.VaccineRecord;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VaccineDao_Impl implements VaccineDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VaccineRecord> __insertionAdapterOfVaccineRecord;

  public VaccineDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVaccineRecord = new EntityInsertionAdapter<VaccineRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `vaccine_record` (`vaccineId`,`completed`,`completedDateMillis`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VaccineRecord entity) {
        statement.bindString(1, entity.getVaccineId());
        final int _tmp = entity.getCompleted() ? 1 : 0;
        statement.bindLong(2, _tmp);
        if (entity.getCompletedDateMillis() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getCompletedDateMillis());
        }
      }
    };
  }

  @Override
  public Object upsert(final VaccineRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVaccineRecord.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<VaccineRecord>> observeAll() {
    final String _sql = "SELECT * FROM vaccine_record";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"vaccine_record"}, false, new Callable<List<VaccineRecord>>() {
      @Override
      @Nullable
      public List<VaccineRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVaccineId = CursorUtil.getColumnIndexOrThrow(_cursor, "vaccineId");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfCompletedDateMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDateMillis");
          final List<VaccineRecord> _result = new ArrayList<VaccineRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VaccineRecord _item;
            final String _tmpVaccineId;
            _tmpVaccineId = _cursor.getString(_cursorIndexOfVaccineId);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            final Long _tmpCompletedDateMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedDateMillis)) {
              _tmpCompletedDateMillis = null;
            } else {
              _tmpCompletedDateMillis = _cursor.getLong(_cursorIndexOfCompletedDateMillis);
            }
            _item = new VaccineRecord(_tmpVaccineId,_tmpCompleted,_tmpCompletedDateMillis);
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
  public Object getAll(final Continuation<? super List<VaccineRecord>> $completion) {
    final String _sql = "SELECT * FROM vaccine_record";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<VaccineRecord>>() {
      @Override
      @NonNull
      public List<VaccineRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVaccineId = CursorUtil.getColumnIndexOrThrow(_cursor, "vaccineId");
          final int _cursorIndexOfCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "completed");
          final int _cursorIndexOfCompletedDateMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "completedDateMillis");
          final List<VaccineRecord> _result = new ArrayList<VaccineRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VaccineRecord _item;
            final String _tmpVaccineId;
            _tmpVaccineId = _cursor.getString(_cursorIndexOfVaccineId);
            final boolean _tmpCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCompleted);
            _tmpCompleted = _tmp != 0;
            final Long _tmpCompletedDateMillis;
            if (_cursor.isNull(_cursorIndexOfCompletedDateMillis)) {
              _tmpCompletedDateMillis = null;
            } else {
              _tmpCompletedDateMillis = _cursor.getLong(_cursorIndexOfCompletedDateMillis);
            }
            _item = new VaccineRecord(_tmpVaccineId,_tmpCompleted,_tmpCompletedDateMillis);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
