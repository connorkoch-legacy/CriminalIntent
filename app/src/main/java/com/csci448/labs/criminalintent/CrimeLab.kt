package com.csci448.labs.criminalintent

import java.util.UUID

object CrimeLab {

    private val crimes : MutableList<Crime> = mutableListOf()

    fun getCrime(id: UUID): Crime? {
        for(crime in crimes) {
            if(crime.id == id) {
                return crime
            }
        }
        return null
    }

    fun getCrimes() = crimes

    fun getNumberOfCrimes() = crimes.size

    fun addCrime(c: Crime) {
        crimes.add(c)
    }

}