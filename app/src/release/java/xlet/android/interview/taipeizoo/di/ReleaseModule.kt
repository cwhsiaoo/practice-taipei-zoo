package xlet.android.interview.taipeizoo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object ReleaseModule {
    @Provides
    fun createReleaseOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}