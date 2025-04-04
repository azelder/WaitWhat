package com.azelder.waitwhat.game.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.azelder.waitwhat.game.data.db.dao.QuizScoreDao
import com.azelder.waitwhat.game.data.db.entity.QuizScoreEntity

@Database(
    entities = [QuizScoreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ScoresDB : RoomDatabase() {
    abstract val dao: QuizScoreDao

}

@androidx.room.TypeConverters
class Converters {
    @androidx.room.TypeConverter
    fun fromTimestamp(value: Long?): java.time.Instant? {
        return value?.let { java.time.Instant.ofEpochMilli(it) }
    }

    @androidx.room.TypeConverter
    fun instantToTimestamp(instant: java.time.Instant?): Long? {
        return instant?.toEpochMilli()
    }
} 