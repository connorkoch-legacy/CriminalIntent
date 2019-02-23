package com.csci448.labs.criminalintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListActivity : SingleFragmentActivity(), CrimeListFragment.Callbacks, CrimeDetailsFragment.Callbacks {

    override fun onCrimeUpdated() {
        //TODO: How to check if a details was updated and we are in two pane view
        if() {
            val listFragment: CrimeListFragment? =
                supportFragmentManager.findFragmentById(R.id.fragment_container) as CrimeListFragment
            listFragment?.updateUI()
        }
    }

    override fun onCrimeSelected(crime: Crime, position: Int) {
        if(findViewById<FrameLayout>(R.id.detail_fragment_container)==null) {
            val intent = CrimePagerActivity.createIntent(this, position)
            startActivity(intent)
        } else {
            val newDetail = CrimeDetailsFragment.createFragment(crime.id,
                position)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_fragment_container, newDetail)
                .commit()
        }
    }

    companion object {
        private var LOG_TAG = "448.CrimeListAct"
    }
    override fun getLogTag() = LOG_TAG
    override fun createFragment() = CrimeListFragment()
    override fun getLayoutResId() = R.layout.activity_masterdetail

}
