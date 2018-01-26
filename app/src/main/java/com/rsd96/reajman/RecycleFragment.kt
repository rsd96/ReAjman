package com.rsd96.reajman

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_recycle.*


/**
 * Created by Ramshad on 1/26/18.
 */
class RecycleFragment: Fragment() {

    var dbref = FirebaseDatabase.getInstance().reference

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_recycle, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null ) {
            dbref.child("users").child(FirebaseAuth.getInstance().currentUser?.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snap: DataSnapshot?) {
                    if (snap?.hasChild("points") == true) {
                        var points: String = snap?.child("points").getValue().toString()
                        tv_points.text = points
                    }
                }

                override fun onCancelled(p0: DatabaseError?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }

    }

}