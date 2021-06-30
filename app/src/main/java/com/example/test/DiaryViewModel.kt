package com.example.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.test.room.Diary
import com.example.test.room.DiaryRepository

class DiaryViewModel(application: Application) : AndroidViewModel(application){


    //Repository : 뷰모델과 상호작용하기 위해 정리된 데이터 API를 들고 있는 클래스
// 뷰모델은 Repository와 상호작용해야함!!!!!
//뷰모델은 DB나 서버에 직접접근하지 않고 Repository에 접근하며 데이터 관리
//그래서 Reopositroy 의 DB 제어 함수 사용


    private val repository = DiaryRepository(application)
    private val diarys = repository.getAll()

   fun getAll() : androidx.lifecycle.LiveData<List<Diary>> {

       return  this.diarys
   }

    fun insert(diary: Diary){
        repository.insert(diary)
    }

    fun delete(diary: Diary){
        repository.delete(diary)
    }

}