package com.example.myquizzy



//This file contains the major data that needs to  be present in our app in an organized fashion


object Constants {

    //since i have created object constants i can create all companion objects here itself beacuse constants are
    //accessible everywhere
   const  val USER_NAME :String="username"
   const val TOTAL_SCORE : String = "score"

    fun getQuestions() : ArrayList<QuestionBlueprint>
    {
        val questionsOfQuiz = ArrayList<QuestionBlueprint>()

        val q1 =  QuestionBlueprint  (1,
        "Which chocolate brand has the slogan:\"You are not you when you are hungry\" ?",
        "Cadbury" ,
        "Snickers",
        "M&M",
        "Bournville",
        2,
        R.drawable.q1_img
        )



        val q2 =  QuestionBlueprint  (2,
            "Where is around 70% of the world's total chocolate grown ?",
            "West Africa" ,
            "India",
            "Brazil",
            "USA",
            1,
            R.drawable.q2_img
        )


        val q3 =  QuestionBlueprint  (3,
            "How many cocoa beans are there in one pound of chocolate",
            "300" ,
            "700",
            "1200",
            "500",
            4,
            R.drawable.q3_img
        )



        val q4 =  QuestionBlueprint  (4,
            "Which country imports the most chocolate ?",
            "Germany" ,
            "Canada",
            "India",
            "Belgium",
            1,
            R.drawable.q4_img
        )


        val q5 =  QuestionBlueprint  (5,
            "Which company came up with the heart shaped chocolate box ?",
            "Hersheys" ,
            "Ferrero Rotcher",
            "Cadbury",
            "Bournville",
            2,
            R.drawable.q5_img
        )

        questionsOfQuiz.add (q1)
        questionsOfQuiz.add (q2)
        questionsOfQuiz.add (q3)
        questionsOfQuiz.add (q4)
        questionsOfQuiz.add (q5)




        return questionsOfQuiz
    }

}