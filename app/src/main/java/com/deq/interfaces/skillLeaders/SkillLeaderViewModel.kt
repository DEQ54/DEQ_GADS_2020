package com.deq.interfaces.skillLeaders

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deq.model.Skills
import com.deq.network.LoadingStatus
import com.deq.network.Result
import com.deq.network.repository.ApiRepository
import kotlinx.coroutines.launch

class SkillLeaderViewModel @ViewModelInject constructor(private val repository: ApiRepository) :
    ViewModel() {

    private val _skillLeaders = MutableLiveData<List<Skills>>()
    val skillLeaders: LiveData<List<Skills>>
        get() = _skillLeaders

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    init {
        getSkillLeaders()
    }

    private fun getSkillLeaders() {
        _loadingStatus.value = LoadingStatus.Loading("Fetching Skill Leaders ...")
        viewModelScope.launch {
            when (val result = repository.getSkills()) {
                is Result.Success -> {
                    _loadingStatus.value = LoadingStatus.Success
                    _skillLeaders.value = result.data
                }
                is Result.Error -> {
                    _loadingStatus.value = LoadingStatus.Error(result.message)
                }
            }
        }
    }

}