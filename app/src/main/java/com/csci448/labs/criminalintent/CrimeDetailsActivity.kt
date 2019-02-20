package com.csci448.labs.criminalintent

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import java.util.*

// TODO implement SingleFragmentActivity
class CrimeDetailsActivity : SingleFragmentActivity() {

    companion object{
        val LOG_TAG = "448.CrimeDetailsAct"
        var pos = 0

        fun createIntent(fragment: Fragment, uuid: UUID, position: Int): Intent {
            val intent = Intent(fragment.context, CrimeDetailsActivity::class.java)
            intent.putExtra("UUID", uuid)
            intent.putExtra("POSITION", position)
            pos = position
            return intent
        }
    }

    override fun getLogTag() = LOG_TAG

    override fun createFragment(): Fragment {
        val uuid = intent.getSerializableExtra("UUID") as UUID
        return CrimeDetailsFragment.createFragment(uuid, pos)
    }
}