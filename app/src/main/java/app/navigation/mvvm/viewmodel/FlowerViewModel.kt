package app.navigation.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.navigation.mvvm.model.Flower
import app.navigation.mvvm.repository.FlowerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowerViewModel @Inject constructor(private val repository: FlowerRepository) : ViewModel() {
    private var _flowers = MutableLiveData<List<Flower>>()
    val flowers: LiveData<List<Flower>> get() = _flowers

    init {
        getFlowers()
    }

    fun getFlowers() {
        viewModelScope.launch {
            try {
                val flowers = repository.getFlowers()
                println("FLOWERS SIZE ===> ${flowers.size}")
                Log.d("FLOWERS", flowers.size.toString())
                _flowers.value = flowers
            } catch (e: Exception) {
                Log.e("Error", "Error: ${e.message}")
            }
        }
    }
}