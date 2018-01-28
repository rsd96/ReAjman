package com.rsd96.reajman

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
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

    private var isFragmentLoaded = false

    private val TAG = "TWITTER_PRUEBA"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Twitter.initialize(activity)
        lv_feed.adapter = retrieveTimeLineByHashtag(activity.applicationContext, "#ReAjman")

    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if (isVisibleToUser && !isFragmentLoaded ) {
//            // Load your data here or do network operations here
//            isFragmentLoaded = true
//
//        }
//    }


    fun retrieveTimeLineByHashtag(context: Context, hashtag: String): TweetTimelineListAdapter {
        val searchTimeline = SearchTimeline.Builder().query(hashtag).build()
        return TweetTimelineListAdapter.Builder(context).setTimeline(searchTimeline).build()
    }



}