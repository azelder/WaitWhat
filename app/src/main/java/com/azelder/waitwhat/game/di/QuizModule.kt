package com.azelder.waitwhat.game.di

import com.azelder.waitwhat.game.data.QuizRepository
import com.azelder.waitwhat.game.data.QuizRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface QuizModule {

    @Binds
    fun bindQuizRepository(repository: QuizRepositoryImpl): QuizRepository
}