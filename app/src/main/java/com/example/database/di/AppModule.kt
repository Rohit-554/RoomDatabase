package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.model.database.local.Dao
import com.example.database.model.database.local.UserDatabase
import com.example.database.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDbInstance(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "contact_database")
            .build()

    @Provides
    fun provideDaoInstance(userDatabase: UserDatabase): Dao =
        userDatabase.userDao()

    @Provides
    fun provideUserRepository(userDao: Dao):UserRepository = UserRepository(userDao)

}