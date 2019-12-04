package com.example.cardsagainststupidity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardsagainststupidity.MainActivity;
import com.example.cardsagainststupidity.Model.QuizRecord;
import com.example.cardsagainststupidity.Model.Statistics;
import com.example.cardsagainststupidity.R;
import com.example.cardsagainststupidity.StatsActivity;
import com.example.cardsagainststupidity.TakeQuizActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class QuizHistoryAdapter extends RecyclerView.Adapter<QuizHistoryAdapter.ViewHolder> {

	private Context context;
	private SimpleDateFormat formatter;
private ArrayList<QuizRecord> records;

	public QuizHistoryAdapter (Context context, ArrayList<QuizRecord> records) {
		this.context = context;
		this.records = records;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
		holder.setHolderQuizID(records.get(position).getQuiz().getQuizID());
		holder.titleTxtView.setText(records.get(position).getQuiz().getTitle());

		DecimalFormat df = new DecimalFormat("#.##");
		holder.scorePercentageTxtView.setText(df.format(records.get(position).getScorePercentage()) + "%");

		formatter = new SimpleDateFormat("MMMM dd, yyyy hh:mm:ss");

		holder.dateTakenTxtView.setText("" + formatter.format(records.get(position).getDate_taken()));
		holder.durationTxtView.setText("Duration: " + records.get(position).getDuration() + " seconds");

	}

	@Override
	public int getItemCount() {
		return records.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		TextView titleTxtView, scorePercentageTxtView, dateTakenTxtView, durationTxtView;
		Button takeQuizBtn;

		public ViewHolder (@NonNull View itemView) {
			super(itemView);

			titleTxtView = itemView.findViewById(R.id.titleTxtView);
			scorePercentageTxtView = itemView.findViewById(R.id.scorePercentageTxtView);
			dateTakenTxtView  =itemView.findViewById(R.id.dateTakenTxtView);
			durationTxtView  = itemView.findViewById(R.id.durationTxtView);
			takeQuizBtn = itemView.findViewById(R.id.takeQuizBtn);

			takeQuizBtn.setOnClickListener(this);
		}


		@Override
		public void onClick (View view) {

			final int position;
			position = getAdapterPosition();

			switch (view.getId()) {

				case R.id.takeQuizBtn:
					StatsActivity activity = (StatsActivity) context;
					activity.takeQuiz(records.get(position).getQuiz().getQuizID());
					break;

			}


		}

		public void setHolderQuizID (int id) {itemView.setTag(id);}
	}

}
