package com.example.myquizzy
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestions : AppCompatActivity(),View.OnClickListener {

    //Global variables which can be accessed throughout class because private
    //Good practice

    private var mListOfQuestions : ArrayList<QuestionBlueprint>? = null
    private var mCurrentItem = 0
    private var mSelectedOptionNo : Int = 0
    private var mCorrectAnswerCount : Int= 0
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        //fetching the extra information from intent

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        //here the questions from Question file will come as Constants object is accessible anywhere

        mListOfQuestions = Constants.getQuestions()

        populateData()

        // Populating the data

    }

    private fun populateData()
    {
    val currQuestion = mListOfQuestions!![mCurrentItem]

        btnSubmit.text = "SUBMIT"
    //everywhere a null check must be done

        tv_opt1.isClickable = true
        tv_opt2.isClickable = true
        tv_opt4.isClickable = true
        tv_opt3.isClickable = true
    img_qimg.setImageResource(currQuestion!!.accompanyImg)
    //Setting the progress bar progress
    pb_progressBar.progress = currQuestion.id
    //making the text as required
    tv_prog.text = "${currQuestion.id}" + "/" + pb_progressBar.max

    //Fetching the questions and options from dataset and making them available on the screen

    //these are needed to be made to be not nullables by elvis operator
    tv_ques.text = currQuestion!!.question
    tv_opt1.text = currQuestion!!.opt1
    tv_opt2.text = currQuestion!!.opt2
    tv_opt3.text = currQuestion!!.opt3
    tv_opt4.text = currQuestion!!.opt4

        //We will add onClick listeners to all textviews
        //in order to keep a track of which one is selected

        tv_opt1.setOnClickListener(this)
        tv_opt2.setOnClickListener(this)
        tv_opt3.setOnClickListener(this)
        tv_opt4.setOnClickListener(this)

        //setting the onclick listener to the submit button

        btnSubmit.setOnClickListener(this)

        setDefaultTvOptBg()

        }
    //we can also create arraylist of ui elements like textviews

    //We need to set the default bg of the textviews accordingly,for which the following fn is used
    //this function also sets the previously selected tv to default if any new tv is selected


    private fun setDefaultTvOptBg()
    {
        //function returns list of type val!!

        val options = ArrayList<TextView>()

        options.add(0,tv_opt1)
        options.add(1,tv_opt2)
        options.add(2,tv_opt3)
        options.add(3,tv_opt4)

    //we edit some properties which make all the textview different from selected one

        for(opt in options)
        {
            opt.setTextColor(Color.parseColor("#a3a3a3"))
            //following is a graphics attribute
            opt.typeface = Typeface.DEFAULT

            //this approach can be used to set a drawable as a bg from code
            opt.background = ContextCompat.getDrawable(this,R.drawable.opt_tv_bg)
        }

    }



    override fun onClick(v : View?) {


        when(v?.id)

        {

            //If the first option is selected then
            R.id.tv_opt1->{
                selectedOptTvBg(
                    tv_opt1,
                    1
                )
            }

            R.id.tv_opt2->{
                selectedOptTvBg(tv_opt2,2)
            }
            R.id.tv_opt3->{
                selectedOptTvBg(tv_opt3,3)
            }
            R.id.tv_opt4->{
                selectedOptTvBg(tv_opt4,4)
            }
            R.id.btnSubmit->{
                //here there are 3 CASES possible : ans not selected,correct ans selected and wrong selected

                if(mSelectedOptionNo == 0 && mCurrentItem != (mListOfQuestions!!.size - 1))   //basically the global variable kinda behaves like a flag for us to keep track
                                            //whether something is selected
                {
                    //if nothing selected and we are not on the last question,move forward
                    mCurrentItem++


                    populateData()
                }

                else
                {
                        //this check is to handle the last activity
                    if(btnSubmit.text == "Finish")
                    {
                      //  Toast.makeText(this,"Quiz Over",Toast.LENGTH_SHORT).show()

                    //setDefaultTvOptBg()
                          //Name can only be intent!!
                        val intent = Intent(this,ResultActivity::class.java)
                        //startActivityForResult(intentRes)
                        intent.putExtra(Constants.USER_NAME,mUserName)
                        intent.putExtra(Constants.TOTAL_SCORE,mCorrectAnswerCount)

                        startActivity(intent)

                        finish()
                    }


                        //here we check the selected answer and the correct answer
                    //if selected answer is incorrect then we pass the incorrect red bg to the answerSelectedbg
                    // function.Otherwise the correct one is anyways passed to the correct option,this way is selected
                    //is correct,then only 1 green tv,if wrong then 1 red and 1 green tv
                            val currentQuestion = mListOfQuestions!![mCurrentItem]

                        if (mSelectedOptionNo != currentQuestion!!.correctAns) {
                            answerSelectedBg(mSelectedOptionNo, R.drawable.ans_wrong)
                        }

                    else{
                        //to keep count of correct answers

                        mCorrectAnswerCount++

                        }
                    //this line shows to the user after every question that what the correct ans is
                        answerSelectedBg(currentQuestion!!.correctAns, R.drawable.ans_correct)

                        btnSubmit.text = "Go to Next Question"
                            //in order to restrict user from selecting multiple times,we set clickable false after
                    //first click and set it true again while populating data
                    tv_opt1.isClickable = false
                    tv_opt2.isClickable = false
                    tv_opt4.isClickable = false
                    tv_opt3.isClickable = false


                        mSelectedOptionNo = 0


                    if( mCurrentItem >= (mListOfQuestions!!.size-1))
                    {
                        btnSubmit.text = "Finish"



                    }

                }




            }


        }

    }
// we need to create a different drawable for the selected option of course


    private fun selectedOptTvBg(tv:TextView , selectedOpt:Int)
    {
        //whenever one option is selected the others must be default

        setDefaultTvOptBg()

        //theGlobalVariable is given a concrete value here

        mSelectedOptionNo=  selectedOpt
//in parse color method it is necessary to enter the hexcode because it asks for specifically a  string!!
        tv.setTextColor(Color.parseColor("#ffff00"))

        //following is a graphics attribute

        tv.setTypeface(tv.typeface,Typeface.BOLD_ITALIC)

        //this approach can be used to set a drawable as a bg from code

        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_opt_tv_bg)




    }

    // this function just has 1 job,to set the background color of the passed textfield to bg

    private fun answerSelectedBg(selectedVal: Int,bg:Int)
    {
        //selectedVal can have either 1,2,3,4 values so we handle all cases here.
        //important to note that the method is called only after checking the answer and thus no checks are required
        //here.This method just sets both right and wrong bg to selected and correct answers as appropriate

        when(selectedVal)

        {
            1 -> {
                tv_opt1.background = ContextCompat.getDrawable(this,bg)
                tv_opt1.setTextColor(Color.parseColor("#ffff00"))
            }

            2 -> {
                tv_opt2.background = ContextCompat.getDrawable(this,bg)
                tv_opt2.setTextColor(Color.parseColor("#ffff00"))
            }

            3 -> {
                tv_opt3.background = ContextCompat.getDrawable(this,bg)
                tv_opt3.setTextColor(Color.parseColor("#ffff00"))
            }

            4 -> {
                tv_opt4.background = ContextCompat.getDrawable(this,bg)
                tv_opt4.setTextColor(Color.parseColor("#ffff00"))
            }
        }

    }
}



    //quizQuestions.xml will contain the individual question layout and later the data will be
    // populated to the layout to be displayed on the screen
