package com.example.myquizzy
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        //Adding the navigation functionality from this activity to questions activity on click of a
        //button using the intents
        btnStrt.setOnClickListener(){

            //can go on only if name is entered

            if(tvName.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this,QuizQuestions::class.java)
                intent.putExtra(Constants.USER_NAME,tvName.text.toString())
                startActivity(intent)
                finish()
            }

        }

    }
}