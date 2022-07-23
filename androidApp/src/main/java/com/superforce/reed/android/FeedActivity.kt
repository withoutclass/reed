package com.superforce.reed.android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.superforce.reed.RssClientImpl
import com.superforce.reed.android.ui.theme.ReedTheme

class FeedActivity : ComponentActivity() {
    val rssClient = RssClientImpl()
    val feedViewModel = FeedViewModel(RssClientImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ReedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Loading Feeds")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ItemTitle(title: String) {
    Text(title)
}

@Composable
fun FeedCard(
    cardImageUrl: String,
    cardTitle: String,
    cardContent: String,
    cardLink: String
) {
    val context = LocalContext.current
    Row(modifier = Modifier.clickable {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))

        context.startActivity(intent)
    }) {
        ItemImage(url = cardImageUrl)

        Column {
            Text(cardTitle)
            Spacer(modifier = Modifier.height(4.dp))
            Text(cardContent)
        }
    }
}

@Composable
fun ItemImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = "Item Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(RoundedCornerShape(size = Dp(5F)))
            .size(40.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReedTheme {
        Greeting("Android")
    }
}