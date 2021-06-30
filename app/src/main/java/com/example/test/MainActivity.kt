package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivityMainBinding
import com.example.test.room.Diary
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var diaryViewModel: DiaryViewModel
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //일기뷰 클릭시
        // 수정 가능하게
        // 에드뷰에 그대로 내용 전달해주는 것
        val adapter = DiaryAdapter({ diary ->
            val intent = Intent(this, AddActivity2::class.java)
            intent.putExtra(AddActivity2.EXTRA_TITLE,diary.title)
            intent.putExtra(AddActivity2.EXTAR_CONTETN,diary.content)
            intent.putExtra(AddActivity2.EXTRA_ID,diary.id)
            startActivity(intent)
        })


        val lm = LinearLayoutManager(this)
        binding.recyle.adapter = adapter
        binding.recyle.layoutManager = lm
        binding.recyle.setHasFixedSize(true)

        //뷰모델 좀 가져오자
        diaryViewModel = ViewModelProvider(this,defaultViewModelProviderFactory).
            get(DiaryViewModel::class.java)

        diaryViewModel.getAll().observe(this, Observer<List<Diary>>
        {
            diary -> adapter.setDiarys(diary!!)
        })



        //추가버튼 클릭시
        //addveiw로 이동
        binding.addBtn.setOnClickListener {

            val intent = Intent(this, AddActivity2::class.java)
            startActivity(intent)

        }



    }



}
