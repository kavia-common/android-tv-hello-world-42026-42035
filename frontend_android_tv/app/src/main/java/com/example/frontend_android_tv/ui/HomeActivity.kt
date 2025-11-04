package com.example.frontend_android_tv.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend_android_tv.R

// Simple model for placeholder posters
data class Poster(val id: Int)

// Adapter for horizontal rail
private class PosterAdapter(
    private val items: List<Poster>
) : RecyclerView.Adapter<PosterViewHolder>() {
    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): PosterViewHolder {
        val v = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poster, parent, false)
        return PosterViewHolder(v)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        // Nothing dynamic; local placeholder background handles visuals
        holder.itemView.isFocusable = true
        holder.itemView.isFocusableInTouchMode = true
    }

    override fun getItemCount(): Int = items.size
}

private class PosterViewHolder(view: View) : RecyclerView.ViewHolder(view)

// PUBLIC_INTERFACE
/**
 * HomeActivity for Android TV.
 *
 * Displays:
 * - Top menu (Home, Login, Setting, My Plan) with D-pad focus and navigation to Login.
 * - Banner area.
 * - Multiple horizontal rails (Top trending, Continue watching, Action, Drama, Horror, Other genres).
 */
class HomeActivity : FragmentActivity() {

    private lateinit var topMenu: LinearLayout
    private lateinit var railsContainer: LinearLayout

    private val railTitles by lazy {
        listOf(
            getString(R.string.rail_top_trending),
            getString(R.string.rail_continue),
            getString(R.string.rail_action),
            getString(R.string.rail_drama),
            getString(R.string.rail_horror),
            getString(R.string.rail_other),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupTopMenu()
        setupRails()
    }

    private fun setupTopMenu() {
        topMenu = findViewById(R.id.top_menu)

        val home = findViewById<TextView>(R.id.menu_home)
        val login = findViewById<TextView>(R.id.menu_login)
        val settings = findViewById<TextView>(R.id.menu_settings)
        val myPlan = findViewById<TextView>(R.id.menu_my_plan)

        // Home: stay here
        home.setOnClickListener { /* no-op */ }

        // Login: navigate
        login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Placeholder actions
        settings.setOnClickListener { /* no-op */ }
        myPlan.setOnClickListener { /* no-op */ }

        // Ensure initial focus for TV
        home.isFocusable = true
        home.isFocusableInTouchMode = true
        home.requestFocus()
    }

    private fun setupRails() {
        railsContainer = findViewById(R.id.rails_container)
        // rails_container includes 6 view_rail via <include>. Assign titles and adapters.
        // Iterate through children, assume each include inflated as LinearLayout.
        val childCount = railsContainer.childCount
        for (index in 0 until childCount) {
            val railView = railsContainer.getChildAt(index)
            val titleView = railView.findViewById<TextView>(R.id.rail_title)
            val listView = railView.findViewById<RecyclerView>(R.id.rail_list)

            val title = railTitles.getOrNull(index) ?: getString(R.string.rail_other)
            titleView.text = title

            listView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            listView.isNestedScrollingEnabled = false
            // Populate with placeholder items
            val posters = (0 until 20).map { Poster(it) }
            listView.adapter = PosterAdapter(posters)
        }
    }
}
