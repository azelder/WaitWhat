package com.azelder.waitwhat.game.dnd.di

import com.azelder.waitwhat.game.dnd.data.DndRepository
import com.azelder.waitwhat.game.dnd.data.DndRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DndModule {

    @Binds
    fun bindDndRepository(repository: DndRepositoryImpl): DndRepository
}