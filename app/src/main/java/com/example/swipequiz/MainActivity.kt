package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import androidx.recyclerview.widget.*


class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //Left = incorrect, right = correct

                val position = viewHolder.adapterPosition
                if(direction == ItemTouchHelper.LEFT) {
                    if(questions[position].validation == false) {
                        questions.removeAt(position)
                        questionAdapter.notifyDataSetChanged()
                    } else {
                        questionAdapter.notifyDataSetChanged()
                        Toast.makeText(applicationContext, getString(R.string.message_incorrect), Toast.LENGTH_LONG).show()
                    }
                } else {
                    if(questions[position].validation == true) {
                        questions.removeAt(position)
                        questionAdapter.notifyDataSetChanged()
                    } else {
                        questionAdapter.notifyDataSetChanged()
                        Toast.makeText(applicationContext, getString(R.string.message_incorrect), Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        return ItemTouchHelper(callback)
    }

    private fun initViews() {
        rvQuestions.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvQuestions.adapter = questionAdapter

        createItemTouchHelper().attachToRecyclerView(rvQuestions)

        rvQuestions.addItemDecoration(
            DividerItemDecoration(
                rvQuestions.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        for (i in Question.QUESTION_SENTENCES.indices) {
            questions.add(Question(Question.QUESTION_SENTENCES[i], Question.QUESTION_ANSWERS[i], Question.QUESTION_VALIDATIONS[i]))
        }
        questionAdapter.notifyDataSetChanged()
    }
}
