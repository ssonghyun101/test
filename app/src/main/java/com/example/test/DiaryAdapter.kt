package com.example.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.ItemViewBinding
import com.example.test.room.Diary


// 적어도 아이템뷰 클릭시 -> 내용이 남아있는 addview 이동은 정의해줘야함
class DiaryAdapter(val diaryItemClick : (Diary) -> Unit)
: RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {

    private var diarys : List<Diary> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryAdapter.ViewHolder {
        val binding = ItemViewBinding.inflate(

            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaryAdapter.ViewHolder, position: Int) {

        holder.onBind(diarys[position])

    }

    override fun getItemCount(): Int {
        return diarys.size
    }


    inner class ViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(diary: Diary){
            binding.content.text = diary.content
            binding.title.text = diary.title

            itemView.setOnLongClickListener {
                diaryItemClick(diary)
                true
            }


        }
    }

    fun setDiarys(diarys : List<Diary>) {
        this.diarys = diarys
        notifyDataSetChanged()
    }

}