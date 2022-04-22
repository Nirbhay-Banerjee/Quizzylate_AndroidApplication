package com.example.myquizzy

data class QuestionBlueprint(
    val id : Int ,
    val question : String ,
    val opt1 : String ,
    val opt2 : String ,
    val opt3 : String ,
    val opt4 : String ,
    val correctAns : Int ,
    val accompanyImg : Int




)
//This is basically the dataclass for our questions. Each question must have the above mentioned
//parameters

//These questions will be available to the app through constants object