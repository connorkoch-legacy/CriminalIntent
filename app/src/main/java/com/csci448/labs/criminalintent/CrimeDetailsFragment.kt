package com.csci448.labs.criminalintent

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.list_item_crime.*
import kotlinx.android.synthetic.main.list_item_crime.view.*
import java.util.*

// TODO create the fragment
class CrimeDetailsFragment : Fragment() {

    interface Callbacks {
        fun onCrimeUpdated()
    }

    private var callbacks: Callbacks? = null

    companion object{
        val LOG_TAG = "448.CrimeDetailsFrag"
        var position: Int = 0

        fun createFragment(uuid: UUID, pos: Int): Fragment{
            val arguments = Bundle()
            arguments.putString("UUID", uuid.toString())
            arguments.putInt("POSITION", pos)
            val fragment = CrimeDetailsFragment()
            position = pos
            fragment.arguments = arguments
            return fragment
        }

        fun getExtra(): Int{
            return position
        }
    }

    private lateinit var crime : Crime

    private fun setResult(){
        val intent = Intent()
        intent.putExtra("POSITION", position)
        activity?.setResult(RESULT_OK, intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")
        crime = Crime()
        val randomUUIDString = UUID.randomUUID().toString()
        val uuidString = arguments?.getString("UUID", randomUUIDString ) ?: randomUUIDString
        crime = CrimeLab.getCrime( UUID.fromString(uuidString) ) ?: Crime()
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        Log.d(LOG_TAG, "onCreateView() called")
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOG_TAG, "onViewCreated() called")

        crime_title_edit_text.setText(crime.title)

        crime_title_edit_text.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start:Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
                callbacks?.onCrimeUpdated()
                setResult()
            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        crime_date_button.text = crime.date.toString()
        crime_date_button.isEnabled = false
        crime_solved_checkbox.setOnCheckedChangeListener {
                _, isChecked -> crime.isSolved = isChecked
            callbacks?.onCrimeUpdated()
            setResult()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(LOG_TAG, "onAttach() called")
        callbacks = context as Callbacks
    }

    override fun onDetach() {
        Log.d(LOG_TAG, "onDetach() called")
        callbacks = null
        super.onDetach()
    }

    override fun onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView() called")
        super.onDestroyView()
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