package com.superforce.reed.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superforce.reed.RSS
import com.superforce.reed.RssClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel(
    private val rssClient: RssClient
): ViewModel() {

    fun fetchFeeds(): LiveData<List<RSS>> {
        val result = MutableLiveData<List<RSS>>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(rssClient.fetchFeeds(listOf("https://feeds.arstechnica.com/arstechnica/index")))
        }

        return result
    }
}
