package api

import retrofit2.http.GET
import api.RandomMealResponse

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal(): RandomMealResponse
}