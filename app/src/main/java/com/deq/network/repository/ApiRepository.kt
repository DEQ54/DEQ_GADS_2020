package com.deq.network.repository

import android.util.Log
import com.deq.model.Hours
import com.deq.model.Skills
import com.deq.network.ApiService
import com.deq.network.Result
import com.deq.network.SubmissionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService,
    private val submissionService: SubmissionService
) {

    suspend fun getHours(): Result<List<Hours>> {
        return try {
            Result.Success(withContext(Dispatchers.IO) {
                service.getHoursAsync().await()
            })
        } catch (ex: Exception) {
            return Result.Error(ex.message!!)
        }
    }

    suspend fun getSkills(): Result<List<Skills>> {
        return try {
            Result.Success(withContext(Dispatchers.IO) {
                service.getSkillsAsync().await()
            })
        } catch (ex: Exception) {
            return Result.Error(ex.message!!)
        }
    }

    suspend fun makeSubmission(
        emailAddress: String,
        name: String,
        lastName: String,
        linkToProject: String
    ): Result<Unit> {
        return try {
            Result.Success(withContext(Dispatchers.IO) {
                submissionService.makeSubmissionAsync(emailAddress, name, lastName, linkToProject)
                    .await()
            })
        } catch (ex: Exception) {
            Log.e("mistake", ex.message!!)
            return Result.Error(ex.message!!)
        }
    }
}