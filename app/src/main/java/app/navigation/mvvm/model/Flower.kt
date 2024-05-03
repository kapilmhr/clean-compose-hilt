package app.navigation.mvvm.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flower(
    var name: String,
    var category: String,
    var instructions: String,
    var photo: String,
    var price: Double,
    var productId: Int
) : Parcelable