package com.csci448.labs.criminalintent

import java.util.UUID

object CrimeLab {
    private val crimes : MutableList<Crime> = mutableListOf()

    init {
        for(i in 0..100) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = (i % 2 == 0)
            crimes.add(crime)
        }
    }

    fun getCrime(id: UUID): Crime? {
        for(crime in crimes) {
            if(crime.id == id) {
                return crime
            }
        }
        return null
    }

    fun getCrimes() = crimes

}