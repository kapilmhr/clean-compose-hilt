package app.navigation.mvvm.repository

import app.navigation.mvvm.model.Flower

interface FlowerRepository {
    suspend fun getFlowers(): List<Flower>
}