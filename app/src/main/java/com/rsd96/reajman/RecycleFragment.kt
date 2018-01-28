package com.rsd96.reajman

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.florent37.viewanimator.ViewAnimator
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_recycle.*
import java.util.*

/**
 * Created by Ramshad on 1/26/18.
 */
class RecycleFragment: Fragment() {

    var dbref = FirebaseDatabase.getInstance().reference

    private val TAG = "MainActivity"

    var yValues: ArrayList<Entry> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_recycle, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null ) {
            dbref.child("users").child(FirebaseAuth.getInstance().currentUser?.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snap: DataSnapshot?) {
                    if (snap?.hasChild("point") == true) {
                        var points: String = snap?.child("point").getValue().toString()
                        tv_points.text = points
                    }
                }

                override fun onCancelled(p0: DatabaseError?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })


            dbref.child("bags").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snap: DataSnapshot?) {
                    for (x in snap?.children!!) {
                        if(x.child("bagId").getValue() == "28304")
                            cv_green.visibility = View.VISIBLE
                    }
                }

                override fun onCancelled(p0: DatabaseError?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }

        cv_points.setOnClickListener(View.OnClickListener { view ->
            startActivity(Intent(activity.applicationContext, LeaderActivity::class.java))
        })

        cv_ai.setOnClickListener(View.OnClickListener { view ->
            startActivity(Intent(activity.applicationContext, AIActivity::class.java))
        })


        view_full.setOnClickListener(View.OnClickListener { view ->
            ViewAnimator
                    .animate(cv_green)
                    .dp().translationX(0f, -1000f)
                    .duration(900)
                    .onStop { cv_green.visibility = View.GONE }
                    .start()

            Toast.makeText(activity.applicationContext, "Thank you for making Ajman a greener place !", Toast.LENGTH_SHORT).show()

            var lat = "25.418290"
            var long = "55.439038"
            var db = FirebaseDatabase.getInstance()
            val dbData = HashMap<String, Any>()
            dbData.put("bagId", "28304")
            dbData.put("bagOwner", "KluRSgWD41ZSv1TpXtfc3seLLX03")
            dbData.put("lat", lat)
            dbData.put("long", long)

            db.reference.child("bagsFull").push().updateChildren(dbData).addOnCompleteListener {  }
        })

        lineChart.setDragEnabled(true)
        lineChart.setScaleEnabled(false)
        lineChart.getDescription()?.isEnabled = false

        lineChart.getXAxis()?.setDrawGridLines(false)
        lineChart.getAxisLeft()?.setDrawGridLines(false)
        lineChart.getAxisRight()?.setDrawGridLines(false)
        lineChart.getAxisRight()?.isEnabled = false

        yValues.add(Entry(0f, 0f))
        yValues.add(Entry(10f, 30f))
        yValues.add(Entry(20f, 30f))
        yValues.add(Entry(30f, 40f))
        yValues.add(Entry(40f, 60f))
        yValues.add(Entry(50f, 80f))

        val lineDataSet = LineDataSet(yValues, "Current Points")

        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.setDrawFilled(true)
        lineDataSet.setCircleColor(Color.WHITE)
        lineDataSet.fillColor = Color.rgb(156, 204, 100) //gradient
        lineDataSet.color = Color.rgb(146, 193, 91) //outline
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.fillAlpha = 110
        lineDataSet.lineWidth = 3f
        lineDataSet.valueTextSize = 1f

        val dataSets = arrayListOf<ILineDataSet>()
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets)

        lineChart.setData(lineData)
    }

}