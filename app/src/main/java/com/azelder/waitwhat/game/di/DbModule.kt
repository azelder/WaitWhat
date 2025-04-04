package com.azelder.waitwhat.game.di

import android.app.Application
import androidx.room.Room
import com.azelder.waitwhat.game.data.LeaderboardRepository
import com.azelder.waitwhat.game.data.db.LeaderboardRepositoryImpl
import com.azelder.waitwhat.game.data.db.ScoresDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideScoresDb(app: Application): ScoresDB {
        return Room.databaseBuilder(
            app,
            ScoresDB::class.java,
            "scores.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLeaderboardRepository(repository: LeaderboardRepositoryImpl): LeaderboardRepository {
        return repository
    }
}