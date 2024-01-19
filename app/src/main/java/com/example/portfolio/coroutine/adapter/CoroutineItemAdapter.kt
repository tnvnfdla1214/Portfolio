package com.example.portfolio.coroutine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CoroutineTest
import com.example.portfolio.databinding.ItemCoroutineBinding

class CoroutineItemAdapter :
    RecyclerView.Adapter<CoroutineItemAdapter.CoroutineItemViewHolder>() {

    private val tests: MutableList<CoroutineTest> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoroutineItemViewHolder {
        return CoroutineItemViewHolder(
            ItemCoroutineBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(holder: CoroutineItemViewHolder, position: Int) {
        holder.bind(tests[position])
    }

    override fun getItemCount(): Int = tests.size

    fun addTest(test: CoroutineTest) {
        tests.add(test)
        notifyItemInserted(tests.size - 1)
    }

    fun updateTest(position: Int, test: CoroutineTest) {
        if (position >= 0 && position < tests.size) {
            tests[position] = test
            notifyItemChanged(position)
        }
    }

    fun setTests(newTests: List<CoroutineTest>) {
        tests.clear()
        tests.addAll(newTests)
        notifyDataSetChanged()
    }

    inner class CoroutineItemViewHolder(private val binding: ItemCoroutineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(test: CoroutineTest) {
            binding.run {
                time.text = test.time
                data.text = test.data
            }
        }
    }
}
