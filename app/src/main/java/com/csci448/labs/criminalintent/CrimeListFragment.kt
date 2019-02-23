package com.csci448.labs.criminalintent


import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListFragment : Fragment() {

    // define the Callback interface
    interface Callbacks {
        fun onCrimeSelected(crime: Crime, position: Int)
    }

    companion object {
        private const val LOG_TAG = "448.CrimeListFrag"
        private val REQUEST_CODE_DETAILS_FRAGMENT = 0
    }

    private lateinit var adapter: CrimeListAdapter

    // define the callback object
    private var callbacks: Callbacks? = null

    private class CrimeHolder(val fragment: CrimeListFragment, val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(crime: Crime, position: Int) {
            view.list_item_crime_title_text_view.text = crime.title
            view.list_item_crime_date_text_view.text = crime.date.toString()
            view.list_item_crime_solved_check_box.text = crime.isSolved.toString()

            view.setOnClickListener {
                fragment.callbacks?.onCrimeSelected(crime, position)
            }
        }
    }

    private class CrimeListAdapter(val fragment: CrimeListFragment, val crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {

        override fun getItemCount(): Int {
            return crimes.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater = LayoutInflater.from(fragment.context)
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(fragment, view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            holder.bind(crimes[position], position)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if(resultCode != RESULT_OK) { return }
        if(data == null) { return }
        if(requestCode == REQUEST_CODE_DETAILS_FRAGMENT) {
            val position = CrimeDetailsFragment.getExtra()
            adapter.notifyItemChanged(position)
        }
    }

     fun updateUI() {
        if( ::adapter.isInitialized ) {
            adapter.notifyDataSetChanged()
        } else {
            adapter = CrimeListAdapter(this, CrimeLab.getCrimes())
            crime_list_recycler_view.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when(item?.itemId) {
            R.id.new_crime_menu_item -> {
                val crime = Crime()
                CrimeLab.addCrime(crime)
                updateUI()
                callbacks?.onCrimeSelected(crime, CrimeLab.getNumberOfCrimes()-1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.d(LOG_TAG, "onCreate() called")
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(LOG_TAG, "onActivityCreated() called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume() called")
        updateUI()
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Log.d(LOG_TAG, "onCreateView() called")
        return inflater.inflate( R.layout.fragment_list, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "onViewCreated() called")
        super.onViewCreated(view, savedInstanceState)
        crime_list_recycler_view.layoutManager = LinearLayoutManager( activity )
    }
}