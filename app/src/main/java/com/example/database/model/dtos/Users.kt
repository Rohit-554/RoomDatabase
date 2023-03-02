package com.example.database.model.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "UserDatabase")
data class Users(
    val name:String,
    val email:String,
    val phoneNumber:String,
    val isOptedForWorkshop:Boolean = false,
    val password:String,
//    val enrolledCourses:List<Workshop>
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}
data class EnrolledCourses(val courseList: String)


//class ListConverter(){
//    @TypeConverter
//    fun fromListToJson(value:List<Workshop>) = Gson().toJson(value)
//    @TypeConverter
//    fun frmJsonToLst(value: String) = Gson().fromJson(value,Array<EnrolledCourses>::class.java).toList()
//
//}

