package xlet.android.interview.taipeizoo.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xlet.android.interview.taipeizoo.BuildConfig
import xlet.android.interview.taipeizoo.datasource.network.ZooService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun createMoshi(): Moshi = Moshi.Builder()
        .build()

    @Singleton
    @Provides
    fun createRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_HOST)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun createZooService(retrofit: Retrofit): ZooService {
        return retrofit.create(ZooService::class.java)
    }
}