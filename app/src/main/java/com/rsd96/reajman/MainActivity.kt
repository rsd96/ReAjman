package com.rsd96.reajman

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var auth: FirebaseAuth? = FirebaseAuth.getInstance()
    internal var email = "rmshdbasheer@gmail.com"
    internal var pass = "123456"
    var userName = "Ahmed"
    var lat = "25.418290"
    var long = "55.439038"

    var db: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        actionBar?.setDisplayShowTitleEnabled(false)

        if (auth != null) {
            auth!!.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                //user.text = auth!!.currentUser!!.email
                db.getReference("users").child(auth?.currentUser?.uid).child("user_name").setValue(userName)
                //db.getReference("users").child(auth?.currentUser?.uid).child("points").setValue(0)
                db.getReference("users").child(auth?.currentUser?.uid).child("lat").setValue(lat)
                db.getReference("users").child(auth?.currentUser?.uid).child("long").setValue(long)

            }
        }

        setupViewPager()

        tabs.setTabTextColors(
                ContextCompat.getColor(this, android.R.color.black),
                ContextCompat.getColor(this, android.R.color.black)
        )


        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {

                when(tab?.position) {

                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    /*0 -> tab?.setIcon(R.drawable.ic_tab_alert_unselected)
                    1 -> tab?.setIcon(R.drawable.ic_tab_alert_feed_unselected)
                    2 -> tab?.setIcon(R.drawable.ic_tab_chat_unselected)*/
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        fab.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, QRScanActivity::class.java))
        })
    }

    private fun setupViewPager() {
        val adapter: TabsAdapter =  TabsAdapter(supportFragmentManager)
        adapter.addFragment(FeedFragment(), "Feed")
        adapter.addFragment(RecycleFragment(), "Recycle")
        adapter.addFragment(EventsFragment(), "Events")
        viewPager.adapter = adapter
        viewPager.currentItem = 1
        tabs.setupWithViewPager(viewPager)
    }
}
