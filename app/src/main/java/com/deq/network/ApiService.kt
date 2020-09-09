package com.deq.network

import com.deq.model.Hours
import com.deq.model.Skills
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("api/hours")
    fun getHoursAsync(): Deferred<List<Hours>>

    @GET("api/skilliq")
    fun getSkillsAsync(): Deferred<List<Skills>>
}