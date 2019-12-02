package com.example.cardsagainststupidity.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsagainststupidity.MainActivity;
import com.example.cardsagainststupidity.Model.Quiz;
import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.database.DatabaseHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuizCardAdapter extends RecyclerView.Adapter<QuizCardAdapter.ViewHolder> implements Filterable{

    private static final String TAG = "QuizCardAdapter";

    private Context context;
    private ArrayList<Quiz> quizzes;
    private ArrayList<Quiz> quizzes1;

    public QuizCardAdapter(Context context, ArrayList<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
        this.quizzes1 = quizzes;
        sortByDate();

    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
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




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTxtView, subjectTxtView, nFlashcardsTxtView;
        CardView quizCard;
        Button takeQuizBtn, moreBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            titleTxtView = itemView.findViewById(R.id.titleTxtView);
            subjectTxtView = itemView.findViewById(R.id.subjectTxtView);
            nFlashcardsTxtView = itemView.findViewById(R.id.nFlashcardsTxtView);
            quizCard = itemView.findViewById(R.id.quizCard);
            takeQuizBtn = itemView.findViewById(R.id.takeQuizBtn);
            moreBtn = itemView.findViewById(R.id.moreBtn);

            moreBtn.setOnClickListener(this);
        }

        @Override
        public void onClick (View view) {

            final int position;
            position = getAdapterPosition();

            switch (view.getId()) {
                case R.id.moreBtn: //creating a popup menu


                    PopupMenu popup = new PopupMenu(context, this.moreBtn, 0);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.more_menu);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            MainActivity activity = (MainActivity) context;
                            switch (item.getItemId()) {
                                case R.id.edit:
                                    activity.editQuiz(quizzes.get(position).getQuizID());


                                    //handle menu1 click
                                    break;
                                case R.id.delete:

                                    activity.deleteQuiz(quizzes.get(position).getQuizID());
                                    break;

                            }
                            return false;
                        }
                    });
                    //displaying the popup
                    popup.show();
                    break;
            }

        }


        public void setHolderQuizID(int id){
            itemView.setTag(id);
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

    public void refresh(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
        this.quizzes1 = quizzes;
        sortByDate();
    }

    public void sortByDate() {
        Collections.sort(quizzes, new Comparator<Quiz>() {
            public int compare(Quiz o1, Quiz o2) {
                if (o1.getDate_created() == null || o2.getDate_created() == null)
                    return 0;
                return o2.getDate_created().compareTo(o1.getDate_created());
            }
        });

        notifyDataSetChanged();
    }

    public void sortByTitle() {
        Collections.sort(quizzes, new Comparator<Quiz>() {
            public int compare(Quiz o1, Quiz o2) {
                if (o1.getTitle() == null || o2.getTitle() == null)
                    return 0;
                return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
            }
        });

        notifyDataSetChanged();
    }

    public void sortBySubject() {
        Collections.sort(quizzes, new Comparator<Quiz>() {
            public int compare(Quiz o1, Quiz o2) {
                if (o1.getSubject() == null || o2.getSubject() == null)
                    return 0;
                return o1.getSubject().toLowerCase().compareTo(o2.getSubject().toLowerCase());
            }
        });

        notifyDataSetChanged();
    }



}
