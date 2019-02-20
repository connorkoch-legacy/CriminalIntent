package com.csci448.labs.criminalintent

import java.util.Date
import java.util.UUID

class Crime {
    var id : UUID
        private set

    var title : String = ""
    var date : Date = Date()
    var isSolved : Boolean = false

    init {
        id = UUID.randomUUID()
    }

}