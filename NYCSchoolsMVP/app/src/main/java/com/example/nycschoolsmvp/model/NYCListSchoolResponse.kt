package com.example.nycschoolsmvp.model



//variables must match whats in the data files
data class NYCListSchoolResponse(

    val dbn: String,
    val school_name: String,
    val location: String,
    val phone_number: String
)

data class NYCListSatResponse(
    val dbn: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String
)