package api

data class RandomMealResponse(
    val meals: List<Meal>
)

data class Meal(
    val strMeal: String,
    val strCategory: String,
    val strInstructions: String,
    val strMealThumb: String,

    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?
)