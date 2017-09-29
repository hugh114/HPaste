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
        }
    }

    private fun initView() {
        title_tv.text = "HPaste"
        title_iv_right.setOnClickListener(this)
    }

    private fun showContent() {
        home_tv.text = HPrefs.getContent()
    }
}