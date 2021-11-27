package com.inad.audiolibros.v1.app

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.inad.audiolibros.v1.app.fragments.DetailFragment
import com.inad.audiolibros.v1.app.utils.Constants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_preferencias -> {  }
            R.id.menu_ultimo -> {
                goToLastVisited()
            }
            R.id.menu_buscar -> {  }
            R.id.menu_acerca -> {
                val builder : AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setMessage(Constants.MESSAGE_ABOUT)
                builder.setPositiveButton(android.R.string.ok, null)
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLastVisited() {
        val pref : SharedPreferences = getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE)
        val id : Int = pref.getInt(Constants.PREF_LAST, -1)
        if( id >= 0) {
            showDetail(id)
        } else {
            Toast.makeText(this, Constants.MESSAGE_LAST_SEEN, Toast.LENGTH_LONG).show()
        }
    }

    fun showDetail(id: Int) {
        if(supportFragmentManager.findFragmentById(R.id.detalle_fragment) != null) {
            val detailFragment : DetailFragment = supportFragmentManager.findFragmentById(R.id.detalle_fragment) as DetailFragment
            detailFragment.ponInfoLibro(id)
        } else {
            val nuevoFragment = DetailFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_ID_LIBRO, id)
            nuevoFragment.arguments = args
            val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.selector_fragment, nuevoFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        val pref : SharedPreferences = getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE)
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putInt(Constants.PREF_LAST, id)
        editor.apply()
    }
}