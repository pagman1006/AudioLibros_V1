package com.inad.audiolibros.v1.app

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.inad.audiolibros.v1.app.fragments.DetailFragment
import com.inad.audiolibros.v1.app.utils.Constants

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var adapter: AdapterBooksFilter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app : App = this.application as App
        adapter = app.adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { v ->
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_preferencias -> {}
            R.id.menu_acerca -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setMessage(Constants.MESSAGE_ABOUT)
                builder.setPositiveButton(android.R.string.ok, null)
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun goToLastVisited() {
        val pref: SharedPreferences = getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE)
        val id: Int = pref.getInt(Constants.PREF_LAST, -1)
        if (id >= 0) {
            showDetail(id)
        } else {
            Toast.makeText(this, Constants.MESSAGE_LAST_SEEN, Toast.LENGTH_LONG).show()
        }
    }

    fun showDetail(id: Int) {
        if (supportFragmentManager.findFragmentById(R.id.detalle_fragment) != null) {
            val detailFragment: DetailFragment =
                supportFragmentManager.findFragmentById(R.id.detalle_fragment) as DetailFragment
            detailFragment.ponInfoLibro(id)
        } else {
            val nuevoFragment = DetailFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_ID_LIBRO, id)
            nuevoFragment.arguments = args
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.selector_fragment, nuevoFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        val pref: SharedPreferences = getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putInt(Constants.PREF_LAST, id)
        editor.apply()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_todos -> {
                adapter!!.setGender(Constants.G_TODOS)
                adapter!!.notifyDataSetChanged()
            }
            R.id.nav_epico -> {
                adapter!!.setGender(Constants.G_EPICO)
                adapter!!.notifyDataSetChanged()
            }
            R.id.nav_XIX -> {
                adapter!!.setGender(Constants.G_S_XIX)
                adapter!!.notifyDataSetChanged()
            }
            R.id.nav_suspense -> {
                adapter!!.setGender(Constants.G_SUSPENSE)
                adapter!!.notifyDataSetChanged()
            }
        }
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}