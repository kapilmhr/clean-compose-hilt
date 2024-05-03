package app.navigation.mvvm.repository

import app.navigation.mvvm.model.Flower
import app.navigation.mvvm.network.MyApi
import javax.inject.Inject

class FlowerRepositoryImpl @Inject constructor(private val myApi: MyApi) : FlowerRepository {
    override suspend fun getFlowers(): List<Flower> {
        return myApi.getFlowers()
    }

}