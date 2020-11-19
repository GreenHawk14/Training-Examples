package com.example.nycschoolsmvp.presenter

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.example.nycschoolsmvp.model.ListDataType
import com.example.nycschoolsmvp.model.NYCListSchoolResponse
import com.example.nycschoolsmvp.model.SchoolNetwork
import com.example.nycschoolsmvp.model.SchoolsLocale
import com.example.nycschoolsmvp.view.ImainActivity

class MainActivityPresenter {

    private var view: ImainActivity? = null
    private lateinit var schoolNetwork: SchoolNetwork
    private lateinit var schoolsLocale: SchoolsLocale

    fun bindView(view: ImainActivity)
    {
        this.view
    }

        //focus on the information with cache data, not often times current data.
    fun requestData() {
        //todo check if local is empty
        //todo update if local is not empty
        //todo send data from local
            if (view?.readLastNetworkCall().equals("N/A"))
                    {
                        //todo update from network
                        schoolNetwork.initVolley ({
                            //todo inform error on network call
                            Log.d(TAG, "request network error $it")
                        }, {
                            schoolsLocale.insertLstSchools(it)
                        })
                    }
            else{
                    schoolsLocale.readListSchools {
                        updateViewData(it)
                    }
                }
        
    }
    private fun updateViewData(dataSet: List<NYCListSchoolResponse>)
    {
        val schoolType: ListDataType.SCHOOLTYPE =
                ListDataType.SCHOOLTYPE(dataSet)

    }
    fun unBind()
    {
        view = null
    }
}