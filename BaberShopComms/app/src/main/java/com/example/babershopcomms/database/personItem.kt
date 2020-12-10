package com.example.babershopcomms.database

data class personItem(

    val name: String? = " ",
    val adInfo: String? = " ",
    val setdate_time: String? = " ",
    val specBarb: String? = " ",
    val haircut: Boolean = false,
    val styles: Boolean = false,
    val braid_locs: Boolean = false,
    val trimming: Boolean = false,
    val color_dying: Boolean = false

)

data class CustomerResponse(
    val resultCount: Int,
    val result: List<personItem>
)