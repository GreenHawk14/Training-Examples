package com.example.nycschoolsmvp.model

sealed class ListDataType{
    data class SCHOOLTYPE(val data: List<NYCListSchoolResponse>)
        : ListDataType()

    data class SATTYPE(val data: NYCListSatResponse)
        : ListDataType()

}