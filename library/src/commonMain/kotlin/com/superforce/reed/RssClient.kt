package com.superforce.reed

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*


data class FeedImage(
    val url: String,
    val title: String,
)

data class RSS(val channel: Channel)

data class Channel(
    val title: String,
    val link: String,
    val description: String,
    val image: FeedImage,
    val items: List<FeedItem>

)

data class FeedItem(
    val title: String,
    val link: String,
    val pubDate: String,
    val description: String,
    val content: String
)

interface RssClient {
    suspend fun fetchFeeds(feeds: List<String> = listOf("https://feeds.arstechnica.com/arstechnica/index")): List<RSS>
}

class RssClientImpl(): RssClient {

    private val httpClient = httpClient()

    override suspend fun fetchFeeds(feeds: List<String>): List<RSS> {
        return feeds.map { feedUrl ->
            httpClient.get(feedUrl).body()
        }
    }
}