package com.example.test.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryDao {
    //sql 라이브러리로 데이터 관리할수있게끔하는 그런곳
    annotation class Dao

    //음....날짜 최신순...정렬은....나중에 생각해보자....
    @Query("SELECT * FROM Diary")
    fun getAll() : LiveData<List<Diary>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(diary: Diary)

    @Delete
    fun delete(diary: Diary)
}