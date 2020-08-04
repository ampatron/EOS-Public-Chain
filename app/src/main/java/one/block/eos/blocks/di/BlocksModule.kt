package one.block.eos.blocks.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import one.block.eos.blocks.api.ChainService
import one.block.eos.blocks.data.BlocksRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object BlocksModule {

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().setPrettyPrinting().create()

    @Singleton
    @Provides
    fun provideChainService(retrofit: Retrofit): ChainService {
        return retrofit.create(ChainService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://eos.greymass.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideBlockRepository(api: ChainService): BlocksRepository {
        return BlocksRepository(api)
    }
}