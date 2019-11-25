package com.example.cardsagainststupidity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.R;

import java.util.ArrayList;

public class QuizCardAdapter extends RecyclerView.Adapter<QuizCardAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "QuizCardAdapter";

    private Context context;
    private ArrayList<Quiz> quizzes;
    private ArrayList<Quiz> quizzes1;

    public QuizCardAdapter(Context context, ArrayList<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
        this.quizzes1 = quizzes;
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
        holder.titleTxtView.setText(quizzes.get(position).getTitle());
        holder.subjectTxtView.setText(quizzes.get(position).getSubject());
        holder.nFlashcardsTxtView.setText(quizzes.get(position).getDeck().size() + " Flashcards");
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTxtView, subjectTxtView, nFlashcardsTxtView;
        CardView quizCard;
        Button takeQuizBtn;
        int holderQuizID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxtView = itemView.findViewById(R.id.titleTxtView);
            subjectTxtView = itemView.findViewById(R.id.subjectTxtView);
            nFlashcardsTxtView = itemView.findViewById(R.id.nFlashcardsTxtView);
            quizCard = itemView.findViewById(R.id.quizCard);
            takeQuizBtn = itemView.findViewById(R.id.takeQuizBtn);


        }

        public void setHolderQuizID(int id){
            this.holderQuizID = id;
        }


    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()){
                    quizzes=quizzes1;
                }else{

                    ArrayList<Quiz> filterList = new ArrayList<>();

                    for (Quiz data : quizzes1){

                        if (data.getTitle().toLowerCase().contains(charString) || data.getSubject().toLowerCase().contains(charString)){
                            filterList.add(data);
                        }
                    }

                    quizzes = filterList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = quizzes;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                quizzes = (ArrayList<Quiz>) results.values;
                notifyDataSetChanged();
            }
        };

    }
}
