package com.example.cardsagainststupidity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.R;

import java.util.ArrayList;

public class QuizCardAdapter extends RecyclerView.Adapter<QuizCardAdapter.ViewHolder> {

    private static final String TAG = "QuizCardAdapter";

    private Context context;
    private ArrayList<Quiz> quizzes;

    public QuizCardAdapter(Context context, ArrayList<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
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
        holder.setHolderQuizID(quizzes.get(position).getQuizID());
        holder.quizTitle.setText(quizzes.get(position).getTitle());
        holder.quizSubject.setText(quizzes.get(position).getSubject());
        holder.quizDescription.setText(quizzes.get(position).getDescription());
        holder.quizFlashCardQuantity.setText(quizzes.get(position).getDeck().size());
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView quizTitle, quizSubject, quizDescription, quizFlashCardQuantity, quizAuthor;
        CardView quizCard;
        Button takeQuizBtn;
        int holderQuizID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quizTitle = itemView.findViewById(R.id.quizTitle);
            quizSubject = itemView.findViewById(R.id.quizSubject);
            quizDescription = itemView.findViewById(R.id.quizDescription);
            quizFlashCardQuantity = itemView.findViewById(R.id.quizFlashCardQuantity);
            quizCard = itemView.findViewById(R.id.quizCard);
            takeQuizBtn = itemView.findViewById(R.id.takeQuizBtn);
            holderQuizID = -1;
        }

        public void setHolderQuizID(int id){
            this.holderQuizID = id;
        }


    }
}
