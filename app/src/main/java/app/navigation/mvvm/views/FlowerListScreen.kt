package app.navigation.mvvm.views

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.navigation.mvvm.common.Constants.getPhotoUrl
import app.navigation.mvvm.model.Flower
import app.navigation.mvvm.ui.theme.PurpleGrey80
import app.navigation.mvvm.viewmodel.FlowerViewModel
import app.navigation.mvvm.views.destinations.FlowerDetailScreenDestination
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun FlowerListScreen(navigator: DestinationsNavigator) {
    var viewModel = hiltViewModel<FlowerViewModel>()

    val flowers by viewModel.flowers.observeAsState(emptyList())

    Scaffold(
        topBar = { TopAppBarProvider(navigator, title = "Flowers", showNavIcon = false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            if (flowers.isEmpty()) {
                // Show loading indicator or placeholder
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // Display the list of credit cards
                LazyColumn {
                    items(flowers) { flower ->
                        FlowerCardItem(flower = flower, onClick = {
                            navigator.navigate(FlowerDetailScreenDestination(flower = flower))
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider(
    navigator: DestinationsNavigator,
    title: String = "",
    showNavIcon: Boolean = true
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showNavIcon) {
                IconButton(onClick = {
                    navigator.navigateUp()
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)

                }
            }

        },

        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),

        )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlowerCardItem(flower: Flower, modifier: Modifier = Modifier, onClick: () -> Unit) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        onClick = onClick, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = getPhotoUrl(flower.photo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .width(100.dp)
                    .height(100.dp)
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

            }
        }
    }

}