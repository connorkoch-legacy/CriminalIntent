package com.csci448.labs.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_crime_pager.*


class CrimePagerActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "448.CrimePagerActivity"

        fun createIntent(context: Context?, pos: Int): Intent{
            val intent = Intent(context, CrimePagerActivity::class.java)
            intent.putExtra("POSITION", pos)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")
        setContentView(R.layout.activity_crime_pager)
        var crimes : MutableList<Crime> = CrimeLab.getCrimes()

        crime_view_pager.adapter = object: FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return crimes.size
            }

            override fun getItem(position: Int): Fragment {
                return CrimeDetailsFragment.createFragment(crimes[position].id, position)
            }
        }
        var pos = intent.getIntExtra("POSITION", -1)
        if(pos < crimes.size){
            crime_view_pager.currentItem = pos
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume() called")
    }

    override fun onPause() {
        Log.d(LOG_TAG, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LOG_TAG, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy() called")
        super.onDestroy()
    }
}