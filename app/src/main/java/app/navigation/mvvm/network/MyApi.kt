package app.navigation.mvvm.network

import app.navigation.mvvm.model.Flower
import retrofit2.http.GET

interface MyApi {

    @GET("flowers.json")
    suspend fun getFlowers():List<Flower>
}