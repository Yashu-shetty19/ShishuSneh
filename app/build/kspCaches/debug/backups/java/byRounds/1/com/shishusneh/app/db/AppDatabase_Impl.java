package com.shishusneh.app.db;

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
import com.shishusneh.app.db.dao.BabyProfileDao;
import com.shishusneh.app.db.dao.BabyProfileDao_Impl;
import com.shishusneh.app.db.dao.GrowthDao;
import com.shishusneh.app.db.dao.GrowthDao_Impl;
import com.shishusneh.app.db.dao.MilestoneDao;
import com.shishusneh.app.db.dao.MilestoneDao_Impl;
import com.shishusneh.app.db.dao.VaccineDao;
import com.shishusneh.app.db.dao.VaccineDao_Impl;
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
public final class AppDatabase_Impl extends AppDatabase {
  private volatile BabyProfileDao _babyProfileDao;

  private volatile GrowthDao _growthDao;

  private volatile VaccineDao _vaccineDao;

  private volatile MilestoneDao _milestoneDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `baby_profile` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `dobMillis` INTEGER NOT NULL, `gender` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `growth_entry` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateMillis` INTEGER NOT NULL, `weightKg` REAL NOT NULL, `heightCm` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `vaccine_record` (`vaccineId` TEXT NOT NULL, `completed` INTEGER NOT NULL, `completedDateMillis` INTEGER, PRIMARY KEY(`vaccineId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `milestone_record` (`milestoneId` TEXT NOT NULL, `reached` INTEGER NOT NULL, PRIMARY KEY(`milestoneId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7e8f588ef31d956d9cef8e7d6cc0ebdf')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `baby_profile`");
        db.execSQL("DROP TABLE IF EXISTS `growth_entry`");
        db.execSQL("DROP TABLE IF EXISTS `vaccine_record`");
        db.execSQL("DROP TABLE IF EXISTS `milestone_record`");
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
        final HashMap<String, TableInfo.Column> _columnsBabyProfile = new HashMap<String, TableInfo.Column>(4);
        _columnsBabyProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBabyProfile.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBabyProfile.put("dobMillis", new TableInfo.Column("dobMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBabyProfile.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBabyProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBabyProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBabyProfile = new TableInfo("baby_profile", _columnsBabyProfile, _foreignKeysBabyProfile, _indicesBabyProfile);
        final TableInfo _existingBabyProfile = TableInfo.read(db, "baby_profile");
        if (!_infoBabyProfile.equals(_existingBabyProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "baby_profile(com.shishusneh.app.db.entity.BabyProfile).\n"
                  + " Expected:\n" + _infoBabyProfile + "\n"
                  + " Found:\n" + _existingBabyProfile);
        }
        final HashMap<String, TableInfo.Column> _columnsGrowthEntry = new HashMap<String, TableInfo.Column>(4);
        _columnsGrowthEntry.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthEntry.put("dateMillis", new TableInfo.Column("dateMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthEntry.put("weightKg", new TableInfo.Column("weightKg", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGrowthEntry.put("heightCm", new TableInfo.Column("heightCm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGrowthEntry = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGrowthEntry = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGrowthEntry = new TableInfo("growth_entry", _columnsGrowthEntry, _foreignKeysGrowthEntry, _indicesGrowthEntry);
        final TableInfo _existingGrowthEntry = TableInfo.read(db, "growth_entry");
        if (!_infoGrowthEntry.equals(_existingGrowthEntry)) {
          return new RoomOpenHelper.ValidationResult(false, "growth_entry(com.shishusneh.app.db.entity.GrowthEntry).\n"
                  + " Expected:\n" + _infoGrowthEntry + "\n"
                  + " Found:\n" + _existingGrowthEntry);
        }
        final HashMap<String, TableInfo.Column> _columnsVaccineRecord = new HashMap<String, TableInfo.Column>(3);
        _columnsVaccineRecord.put("vaccineId", new TableInfo.Column("vaccineId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccineRecord.put("completed", new TableInfo.Column("completed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVaccineRecord.put("completedDateMillis", new TableInfo.Column("completedDateMillis", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVaccineRecord = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVaccineRecord = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVaccineRecord = new TableInfo("vaccine_record", _columnsVaccineRecord, _foreignKeysVaccineRecord, _indicesVaccineRecord);
        final TableInfo _existingVaccineRecord = TableInfo.read(db, "vaccine_record");
        if (!_infoVaccineRecord.equals(_existingVaccineRecord)) {
          return new RoomOpenHelper.ValidationResult(false, "vaccine_record(com.shishusneh.app.db.entity.VaccineRecord).\n"
                  + " Expected:\n" + _infoVaccineRecord + "\n"
                  + " Found:\n" + _existingVaccineRecord);
        }
        final HashMap<String, TableInfo.Column> _columnsMilestoneRecord = new HashMap<String, TableInfo.Column>(2);
        _columnsMilestoneRecord.put("milestoneId", new TableInfo.Column("milestoneId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMilestoneRecord.put("reached", new TableInfo.Column("reached", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMilestoneRecord = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMilestoneRecord = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMilestoneRecord = new TableInfo("milestone_record", _columnsMilestoneRecord, _foreignKeysMilestoneRecord, _indicesMilestoneRecord);
        final TableInfo _existingMilestoneRecord = TableInfo.read(db, "milestone_record");
        if (!_infoMilestoneRecord.equals(_existingMilestoneRecord)) {
          return new RoomOpenHelper.ValidationResult(false, "milestone_record(com.shishusneh.app.db.entity.MilestoneRecord).\n"
                  + " Expected:\n" + _infoMilestoneRecord + "\n"
                  + " Found:\n" + _existingMilestoneRecord);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7e8f588ef31d956d9cef8e7d6cc0ebdf", "26664b6297186b46c5983fdde9e05c52");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "baby_profile","growth_entry","vaccine_record","milestone_record");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `baby_profile`");
      _db.execSQL("DELETE FROM `growth_entry`");
      _db.execSQL("DELETE FROM `vaccine_record`");
      _db.execSQL("DELETE FROM `milestone_record`");
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
    _typeConvertersMap.put(BabyProfileDao.class, BabyProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GrowthDao.class, GrowthDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VaccineDao.class, VaccineDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MilestoneDao.class, MilestoneDao_Impl.getRequiredConverters());
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
  public BabyProfileDao babyProfileDao() {
    if (_babyProfileDao != null) {
      return _babyProfileDao;
    } else {
      synchronized(this) {
        if(_babyProfileDao == null) {
          _babyProfileDao = new BabyProfileDao_Impl(this);
        }
        return _babyProfileDao;
      }
    }
  }

  @Override
  public GrowthDao growthDao() {
    if (_growthDao != null) {
      return _growthDao;
    } else {
      synchronized(this) {
        if(_growthDao == null) {
          _growthDao = new GrowthDao_Impl(this);
        }
        return _growthDao;
      }
    }
  }

  @Override
  public VaccineDao vaccineDao() {
    if (_vaccineDao != null) {
      return _vaccineDao;
    } else {
      synchronized(this) {
        if(_vaccineDao == null) {
          _vaccineDao = new VaccineDao_Impl(this);
        }
        return _vaccineDao;
      }
    }
  }

  @Override
  public MilestoneDao milestoneDao() {
    if (_milestoneDao != null) {
      return _milestoneDao;
    } else {
      synchronized(this) {
        if(_milestoneDao == null) {
          _milestoneDao = new MilestoneDao_Impl(this);
        }
        return _milestoneDao;
      }
    }
  }
}
