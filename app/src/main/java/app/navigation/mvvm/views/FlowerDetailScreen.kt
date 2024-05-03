package app.navigation.mvvm.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.navigation.mvvm.common.Constants.getPhotoUrl
import app.navigation.mvvm.model.Flower
import app.navigation.mvvm.views.destinations.FlowerDetailScreenDestination
import app.navigation.mvvm.views.destinations.ImagePreviewDestination
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FlowerDetailScreen(navigator: DestinationsNavigator, flower: Flower) {
    Scaffold(
        topBar = { TopAppBarProvider(navigator, title = flower.name) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = getPhotoUrl(flower.photo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clickable {
                            navigator.navigate(ImagePreviewDestination(flower))
                        }
                )
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = flower.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = flower.category)

                        Text(text = "$" + flower.price.toString())
                    }
                    Text(text = flower.instructions)


                }
            }

        }
    }
}

@Destination
@Composable
fun ImagePreview(navigator: DestinationsNavigator,flower: Flower) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Icon(
            Icons.Filled.Close,
            tint = Color.White,
            contentDescription = "Close",
            modifier = Modifier.padding(10.dp).clickable {
                navigator.popBackStack()
            }
        )
        AsyncImage(
            model = getPhotoUrl(flower.photo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}