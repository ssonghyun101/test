package com.example.test.room

import android.app.Application
import androidx.lifecycle.LiveData
import java.lang.Exception

// 뷰모델과 상호작용하기 위해 잘 정리된(Clean) 데이터 API를 들고 있는 클래스
// 뷰모델은
// DB나 서버에 직접 접근하지 않고,
// 리포지토리에 접근하는 것으로 앱의 데이터를 관리한다.
class DiaryRepository(application : Application) {

    private val diaryDatabase = DiaryDatabase.getInstance(application)!!
    private val diaryDao : DiaryDao = diaryDatabase.DiaryDao()
    private val diarys : LiveData<List<Diary>> = diaryDao.getAll()


// ViewModel에서 DB에 접근을 요청할 때 수행할 함수

// 메인 스레드에서 Room DB에 접근하면 크래쉬 발생
//별도의 스레드로 Room의 데이터에 접근

    // 함수(매개변수) : 반환형 {}
//fun(a:Int) : Int {}
    fun getAll() : LiveData<List<Diary>> {
        return diarys
    }

    fun insert(diary: Diary){
        try{
            val thread = Thread(Runnable {


                diaryDao.insert(diary)
            })

            thread.start()
        } catch (e: Exception) {}

    }

    fun delete(diary: Diary){
        try{
            val thread = Thread(Runnable {

                diaryDao.delete(diary)
            })
            thread.start()
        } catch (e : Exception) { }
    }




}
