package com.rsd96.reajman

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.tweetui.SearchTimeline
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter
import kotlinx.android.synthetic.main.fragment_feed.*


/**
 * Created by Ramshad on 1/26/18.
 */
class FeedFragment: Fragment() {

    private val TAG = "TWITTER_PRUEBA"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Twitter.initialize(activity.applicationContext)
        val adapter = retrieveTimeLineByHashtag(activity.applicationContext, "#Programmers")
        lv_feed.setAdapter(adapter)

    }

    fun retrieveTimeLineByHashtag(context: Context, hashtag: String): TweetTimelineListAdapter {
        Log.d(TAG, "Loading tweets with hashtag " + hashtag)
        val searchTimeline = SearchTimeline.Builder().query(hashtag).build()
        return TweetTimelineListAdapter.Builder(context).setTimeline(searchTimeline).build()
    }

}