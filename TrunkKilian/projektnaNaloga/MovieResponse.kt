package com.example.projektnanaloga.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)

data class Movie(
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Double,
    val poster_path: String?
)

/* Primer response-a (skrajsan results array):

{
  "page": 1,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/942A899wL89zwh5UlSWUCZCmMk8.jpg",
      "genre_ids": [
        28,
        16,
        878
      ],
      "id": 269246,
      "title": "Batman Beyond",
      "original_language": "en",
      "original_title": "Batman Beyond",
      "overview": "",
      "popularity": 0.3736,
      "poster_path": "/dDD8EwK7LHhfq2gmJznS94QSFaD.jpg",
      "release_date": "2014-04-19",
      "softcore": false,
      "video": false,
      "vote_average": 7.0,
      "vote_count": 71
    },
    {
      "adult": false,
      "backdrop_path": "/jFEy7DClkMm8nGXAwmpe577Vlq0.jpg",
      "genre_ids": [
        9648,
        80,
        16
      ],
      "id": 40662,
      "title": "Batman: Rdeča maska",
      "original_language": "en",
      "original_title": "Batman: Under the Red Hood",
      "overview": "",
      "popularity": 2.9956,
      "poster_path": "/7lmHqHg1rG9b4U8MjuyQjmJ7Qm0.jpg",
      "release_date": "2010-07-27",
      "softcore": false,
      "video": false,
      "vote_average": 7.757,
      "vote_count": 1729
    }
  ],
  "total_pages": 10,
  "total_results": 196
}

 */