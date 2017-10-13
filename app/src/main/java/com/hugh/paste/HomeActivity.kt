package com.hugh.paste

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hugh.paste.app.BaseActivity
import com.hugh.paste.utils.HPrefs
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_title.*

/**
 * Created by hugh on 2017/9/29.
 */
class HomeActivity: BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //start service
        val intent = Intent(this, HPasteService::class.java)
        startService(intent)

        initView()
    }

    override fun onResume() {
        super.onResume()
        showContent()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.title_iv_right) {
            HPrefs.clearContent()
            home_tv.text = ""
            home_tv_time.text = ""
        }
    }

    private fun initView() {
        title_tv.text = "HPaste"
        title_iv_right.setOnClickListener(this)
//        home_tv.setOnCreateContextMenuListener { menu, v, menuInfo ->
//            System.out.println("OnCreateContextMenu hugh")
//        }
//        home_tv.setOnEditorActionListener { v, actionId, event ->
//            System.out.println("onAction action:${actionId}")
//            false
//        }
//        home_tv.setOnKeyListener { v, keyCode, event ->
//            System.out.println("onKey code:${keyCode}")
//            false
//        }
//        home_tv.setOnTouchListener{ v, event ->
//            System.out.println("onTouch action:${event.action}")
//            false
//        }
//        home_tv.setOnFocusChangeListener { v, hasFocus ->
//            System.out.println("onFocusChange code:${hasFocus}")
//        }
    }

    private fun showContent() {
        home_tv.text = HPrefs.getContent()
        home_tv_time.text = HPrefs.getTime()
    }
}