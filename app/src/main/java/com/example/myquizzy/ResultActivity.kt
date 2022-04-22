package com.example.myquizzy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        val userName = intent.getStringExtra(Constants.USER_NAME)
        //in case of getting an int extra there must be a default value
        val score = intent.getIntExtra(Constants.TOTAL_SCORE,0)

        tv_score.text = "Your score is $score out of 5"
        tv_username.text = "$userName"
        btn_over.setOnClickListener()
        {
            startActivity(Intent(this,MainActivity::class.java))

            finish()
        }

    }


/*
    What we want is to make the activities interconnected.
    1)So basically from the main activity we click on launch act1 and from it if click on a button we should go back
    to the main activity along with an indication that the activity which we wanted had launched

    ACT_MAIN-------> actReq
    <--------
    {Activity launched}

    2)WE actually fetch some data which the user enters in the second activity and then we go back to the first activity(callingAct) and display that data there!!


    The process is to create companion objects in the main activity
    Companion objects  contain kind of a flag as to which activity will send a request
    private const val FIRST_ACTIVITY_REQUEST_CODE = 1 (basically activity name and its request code) so that we can get to the request later on

    Then since we require to retrieve a result from the called activity,thus we use startActivity for result method and not the normal startActivity one..Here we pass
    the 1st act req code as a parameter

    After this another method called onActivityResult needs to be overriden.this method is what actually retrives the data from another activity and makes it available in the
    calling activity!

    Here first of all we need to check if the resultcode is Activity_ok (basically if its fine)
    and then if the request code is of the first activity only...then do something specific
    if of second activity,then do something else...so on

    We can also check for cancelled condition just like ok condition


    In the called activity,we need to set the result to activity ok (here we need no intents..just do the method)

    Whatever information we need to send from another activity is done through intent in form of companion objects thus,a comapnion object for the data must also be
    created,just like request codes

    To send data,in the called activity there must be created another intent and thenwe can use putExtra method which stores values as  key value pairs
    Here the key must be the companion object created for data in the calling activity

    And then in the setResult method,make the result activityOk and also pass the intent

    Now just like previous case in the calling activity we need to do entries in the onActivityResult method...where first we check if the requestcode is of the 2nd activity

    Then do what we need to.To access the extras which are sent through intent the data parameter of this method can be used.Check if the data is not null..then store the
    data.getStringExtra(key) in a variable and use it. Here the key is again the name of the companion object created for data
*/

//If we press back button from activity..then REsult_Cancelled is executed
//The process starts from main activity to quizquestion activity to this results activity .Another way is to use shared
// preferences which will be touched later

}