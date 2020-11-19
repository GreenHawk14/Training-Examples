package com.example.nycschoolsmvp.view

import com.example.nycschoolsmvp.model.ListDataType

interface ImainActivity {
    fun requestData()
    fun displayData(dataSet: ListDataType.SCHOOLTYPE)
    fun bindPresenter()
    fun storeLastNetworkCall(data: String)
    fun readLastNetworkCall(): String

}