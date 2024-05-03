package app.navigation.mvvm

import app.navigation.mvvm.network.MyApi
import app.navigation.mvvm.repository.FlowerRepository
import app.navigation.mvvm.repository.FlowerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASEURL = "https://services.hanselandpetal.com/feeds/"

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(myApi: MyApi): FlowerRepository {
        return FlowerRepositoryImpl(myApi)
    }
}