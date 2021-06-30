package com.example.test.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class Diary(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "content")
    var content: String,

    //날짜는 음.... 나중에 해보자
    /*@ColumnInfo(name = "date")
    var date: Date,
*/
    )
{
    constructor() : this(null,"","")
}