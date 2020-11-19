package com.example.nycschoolsmvp.model

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class SchoolsLocale {
    private val readDatabase: SQLiteDatabase
    private val writeDatabase: SQLiteDatabase
    private val schoolsDBhelper: SchoolsDatabase

    init {
        schoolsDBhelper = SchoolsDatabase()
        readDatabase = schoolsDBhelper.readableDatabase
        writeDatabase = schoolsDBhelper.writableDatabase
    }

    fun insertLstSchools(stringResponse: JSONArray) {
        val gson = Gson()
        val dataSet: List<NYCListSchoolResponse> =
            gson.fromJson(stringResponse.toString(),
            object: TypeToken<List<NYCListSchoolResponse>>() {}.type)


        for (school in dataSet) {
            val contentValues = ContentValues()
            contentValues.put(TABLE_SCHOOLS.COLUMN_DBN, school.dbn)
            contentValues.put(TABLE_SCHOOLS.COLUMN_LOCATION, school.location)
            contentValues.put(TABLE_SCHOOLS.COLUMN_PHONE_NUMBER, school.phone_number)
            contentValues.put(TABLE_SCHOOLS.COLUM_SCHOOL_NAME, school.school_name)

            writeDatabase.insert(TABLE_SCHOOLS::class.simpleName,
                    " ", contentValues)
        }

    }

    fun insertListSAT(dataSet: List<NYCListSatResponse>) {
        val contentWrapperValues = ContentValues()
        for (satSchools in dataSet) {
            val contentValues = ContentValues()
            contentValues.put(TABLE_SAT.COLUMN_DBN, satSchools.dbn)
            contentValues.put(TABLE_SAT.COLUMN_SAT_TEST_NUM, satSchools.num_of_sat_test_takers)
            contentValues.put(TABLE_SAT.COLUMN_SAT_CRIT_READ, satSchools.sat_critical_reading_avg_score)
            contentValues.put(TABLE_SAT.COLUMN_MATH_AVG, satSchools.sat_math_avg_score)
            contentValues.put(TABLE_SAT.COLUMN_SAT_WRITING_AVG, satSchools.sat_writing_avg_score)
        }
        writeDatabase.insert(TABLE_SAT::class.simpleName,
                null, contentWrapperValues)
    }

    fun readListSchools(callbackSchoolList: (MutableList<NYCListSchoolResponse>) -> Unit) {
        val projection = arrayOf(TABLE_SCHOOLS.COLUMN_DBN,
                TABLE_SCHOOLS.COLUM_SCHOOL_NAME,
                TABLE_SCHOOLS.COLUMN_LOCATION,
                TABLE_SCHOOLS.COLUMN_PHONE_NUMBER)
        val orderby = "${TABLE_SCHOOLS.COLUMN_DBN} DESC"
        val schoolCursor =
                readDatabase.query(TABLE_SCHOOLS::class.simpleName,
                        projection, null,
                        null,
                        null,
                        null,
                        orderby)

        schoolCursor.use {

            val listOfSchools = mutableListOf<NYCListSchoolResponse>()
            while (schoolCursor.moveToNext()) {
                val itemSchool = NYCListSchoolResponse(
                        schoolCursor
                                .getString(schoolCursor
                                        .getColumnIndex(TABLE_SCHOOLS.COLUMN_DBN)),
                        schoolCursor
                                .getString(schoolCursor
                                        .getColumnIndex(TABLE_SCHOOLS.COLUM_SCHOOL_NAME)),
                        schoolCursor
                                .getString(schoolCursor
                                        .getColumnIndex(TABLE_SCHOOLS.COLUMN_LOCATION)),
                        schoolCursor
                                .getString(schoolCursor
                                        .getColumnIndex(TABLE_SCHOOLS.COLUMN_PHONE_NUMBER))
                )
                listOfSchools.add(itemSchool)
            }
            //schoolCursor.close()
            callbackSchoolList.invoke(listOfSchools)
        }
    }

    fun readSatBySchool(dbn: String, callbackSchoolSat: (NYCListSatResponse)->Unit)
    {
//        SELECT Name, DBN,
//        from SAT
//        join SCHOOL on SAT
//        where SAT, DBN == dbn

        val selectQuery = "SELECT ${TABLE_SAT::class.simpleName}.${TABLE_SAT.COLUMN_SAT_TEST_NUM}, " +
                "${TABLE_SAT::class.simpleName}.${TABLE_SAT.COLUMN_SAT_CRIT_READ}, " +
                "${TABLE_SAT::class.simpleName}.${TABLE_SAT.COLUMN_MATH_AVG}, " +
                "${TABLE_SAT::class.simpleName}.${TABLE_SAT.COLUMN_SAT_WRITING_AVG}, " +
                "${TABLE_SCHOOLS::class.simpleName}, ${TABLE_SCHOOLS.COLUM_SCHOOL_NAME} " +
                "FROM ${TABLE_SCHOOLS::class.simpleName} JOIN ${TABLE_SAT::class.simpleName}" +
                "ON ${TABLE_SCHOOLS::class.simpleName}, ${TABLE_SCHOOLS.COLUMN_DBN} " +
                "= ${TABLE_SAT::class.simpleName}, ${TABLE_SAT.COLUMN_DBN}" +
                "WHERE ${TABLE_SAT.COLUMN_DBN} = $dbn"

        val listSAt = mutableListOf<NYCListSatResponse>()
        readDatabase.rawQuery(selectQuery, null).use {cursor ->
            while (cursor.moveToNext())
            {
                val item = NYCListSatResponse(
                        cursor.getString(cursor.getColumnIndex(TABLE_SAT.COLUMN_DBN)),
                        cursor.getString(cursor.getColumnIndex(TABLE_SAT.COLUMN_SAT_TEST_NUM)),
                        cursor.getString(cursor.getColumnIndex(TABLE_SAT.COLUMN_MATH_AVG)),
                        cursor.getString(cursor.getColumnIndex(TABLE_SAT.COLUMN_SAT_CRIT_READ)),
                        cursor.getString(cursor.getColumnIndex(TABLE_SAT.COLUMN_SAT_WRITING_AVG)),
                        cursor.getString(cursor.getColumnIndex(TABLE_SCHOOLS.COLUM_SCHOOL_NAME))
                )
                listSAt.add(item)
            }
        }
        callbackSchoolSat.invoke(listSAt[0])
    }
}
