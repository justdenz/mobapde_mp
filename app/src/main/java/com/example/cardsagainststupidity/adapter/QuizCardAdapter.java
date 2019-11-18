package com.example.cardsagainststupidity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsagainststupidity.R;

public class QuizCardAdapter extends RecyclerView.Adapter<QuizCardAdapter.ViewHolder> {

    private static final String TAG = "QuizCardAdapter";

    private Context context;

    public QuizCardAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

//        TextView quizTitle, quizSubject, quizDescription, quizFlashCardQuantity, quizAuthor;
//        CardView quizCard;
//        Button takeQuizBtn, pinQuizBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            quizTitle = itemView.findViewById(R.id.quizTitle);
//            quizSubject = itemView.findViewById(R.id.quizSubject);
//            quizDescription = itemView.findViewById(R.id.quizDescription);
//            quizFlashCardQuantity = itemView.findViewById(R.id.quizFlashCardQuantity);
//            quizAuthor = itemView.findViewById(R.id.quizAuthor);
//            quizCard = itemView.findViewById(R.id.quizCard);
//            takeQuizBtn = itemView.findViewById(R.id.takeQuizBtn);
//            pinQuizBtn = itemView.findViewById(R.id.pinQuizBtn);
        }
    }
}
