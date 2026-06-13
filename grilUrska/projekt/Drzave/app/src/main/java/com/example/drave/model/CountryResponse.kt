package com.example.drave.model

data class ApiResponse(
    val data: DataContainer
)

data class DataContainer(
    val objects: List<CountryResponse>
)

data class CountryResponse(

    val names: CountryName,

    val capitals: List<Capital>?,

    val region: String,

    val population: Int,

    val languages: List<Language>?

)

data class CountryName(

    val common: String,

    val official: String

)

data class Capital(
    val name: String
)

data class Language(
    val name: String
)

data class SavedCountry(

    val name: String,

    val capital: String,

    val region: String,

    val population: Int,

    val languages: String,

    var visited: Boolean,

    val priority: String

)