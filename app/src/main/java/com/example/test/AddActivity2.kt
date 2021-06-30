package com.example.test

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.test.databinding.ActivityAdd2Binding
import com.example.test.room.Diary

class AddActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityAdd2Binding
    private lateinit var diaryViewModel: DiaryViewModel

    private var id : Long?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdd2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        diaryViewModel = ViewModelProvider(this, defaultViewModelProviderFactory)
            .get(DiaryViewModel::class.java)

        if(intent != null && intent.hasExtra(EXTAR_CONTETN) && intent.hasExtra(EXTRA_ID)
            && intent.hasExtra(EXTRA_ID))
        {
            binding.contentInput.setText(intent.getStringExtra(EXTAR_CONTETN))
            binding.titleInput.setText(intent.getStringExtra((EXTRA_TITLE)))
        }


        binding.inputBtn.setOnClickListener {

            val title = binding.titleInput.text.toString().trim()
            val content = binding.contentInput.text.toString()

            if(title.isEmpty() || content.isEmpty()){
                Toast.makeText(this,"둘다입력하라",Toast.LENGTH_SHORT).show()
            }

            else{
                val diary = Diary(id,title,content)
                diaryViewModel.insert(diary)
                finish()
            }
        }




    }


    companion object{
        const val EXTAR_CONTETN ="EXTRA_CONTENT"
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_ID = "EXTRA_ID"
    }
}


