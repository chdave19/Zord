package com.ytmusicclone.di.hilt.modules

import com.ytmusicclone.data.database.PersonalisationLocalDataSource
import com.ytmusicclone.data.fake.FakeLocalDataSource
import com.ytmusicclone.data.network.RemotePersonalisationData
import com.ytmusicclone.data.network.ktorclient.KtorClientProvider
import com.ytmusicclone.data.network.ktorclient.client.KtorRemotePersonalisationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {
    @Provides
    @Singleton
    fun providePersonalisationRemoteDataSource(httpClientProvider: KtorClientProvider): RemotePersonalisationData =
        KtorRemotePersonalisationDataSource(httpClientProvider)

    @Provides
    @Singleton
    fun providePersonalisationLocalDataSource(): PersonalisationLocalDataSource =
        FakeLocalDataSource()
}
