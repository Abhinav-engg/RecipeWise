package com.abhinav.recipewise.data.remote.dto

import com.abhinav.recipewise.data.remote.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RecipeAPIService(private val client: HttpClient) {

    suspend fun getAllRecipes(): RecipeResponse {
        return client.get(urlString = "${KtorClient.BASE_URL}recipes").body()

    }

    suspend fun getRecipeById(id: Int): RecipeDTO {
        return client.get(urlString = "${KtorClient.BASE_URL}recipes/$id").body()
    }

    suspend fun addRecipe(request: AddRecipeRequest){
        client.post(urlString = "${KtorClient.BASE_URL}recipes") {
            contentType(ContentType.Application.Json)
            setBody(request) // convert the request object to JSON and set it as the body of the request
        }
    }



}