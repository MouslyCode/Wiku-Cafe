package com.example.wikucafe.Data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class dataMenu(
    var id : String? = null,
    var menuName: String? = null,
    var jnsmenu: String? = null,
    var hargaMenu: Int? = null,
    var desc: String? = null,
    var img: String? = null
)
