package com.example.story.view.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.story.data.Response.ListStoryItem
import com.example.story.data.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    private val _data = MutableLiveData<List<ListStoryItem>?>()
    val data : LiveData<List<ListStoryItem>?> = _data


    fun fetchStory(token: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getAllstories("Bearer $token")
                _data.value = response.listStory
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}