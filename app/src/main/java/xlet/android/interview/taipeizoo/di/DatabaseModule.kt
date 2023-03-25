package xlet.android.interview.taipeizoo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xlet.android.interview.taipeizoo.datasource.database.TaipeiZooDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        application: Application
    ): TaipeiZooDatabase {
        return Room.databaseBuilder(application, TaipeiZooDatabase::class.java, "taipei-zoo-db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
