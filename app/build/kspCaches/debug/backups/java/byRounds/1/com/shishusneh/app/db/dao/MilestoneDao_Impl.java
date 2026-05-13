package com.shishusneh.app.db.dao;

import android.database.Cursor;
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
import com.shishusneh.app.db.entity.MilestoneRecord;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MilestoneDao_Impl implements MilestoneDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MilestoneRecord> __insertionAdapterOfMilestoneRecord;

  public MilestoneDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMilestoneRecord = new EntityInsertionAdapter<MilestoneRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `milestone_record` (`milestoneId`,`reached`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MilestoneRecord entity) {
        statement.bindString(1, entity.getMilestoneId());
        final int _tmp = entity.getReached() ? 1 : 0;
        statement.bindLong(2, _tmp);
      }
    };
  }

  @Override
  public Object upsert(final MilestoneRecord record, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMilestoneRecord.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<MilestoneRecord>> observeAll() {
    final String _sql = "SELECT * FROM milestone_record";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"milestone_record"}, false, new Callable<List<MilestoneRecord>>() {
      @Override
      @Nullable
      public List<MilestoneRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMilestoneId = CursorUtil.getColumnIndexOrThrow(_cursor, "milestoneId");
          final int _cursorIndexOfReached = CursorUtil.getColumnIndexOrThrow(_cursor, "reached");
          final List<MilestoneRecord> _result = new ArrayList<MilestoneRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MilestoneRecord _item;
            final String _tmpMilestoneId;
            _tmpMilestoneId = _cursor.getString(_cursorIndexOfMilestoneId);
            final boolean _tmpReached;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfReached);
            _tmpReached = _tmp != 0;
            _item = new MilestoneRecord(_tmpMilestoneId,_tmpReached);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
