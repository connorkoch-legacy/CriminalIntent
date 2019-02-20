package com.csci448.labs.criminalintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListActivity : SingleFragmentActivity() {

    companion object {
        private var LOG_TAG = "448.CrimeListAct"
    }
    override fun getLogTag() = LOG_TAG
    override fun createFragment() = CrimeListFragment()
}
