package adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cricapp.R;

import java.util.ArrayList;

import firebase_models.Details;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Details> matchDetailsList;

    public MatchAdapter(Context context, ArrayList<Details> matchDetailsList) {
        this.context = context;
        this.matchDetailsList = matchDetailsList;
    }

    @NonNull
    @Override
    public MatchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match,parent,false);
        return new MatchAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.MyViewHolder holder, int position) {

        final Details match = matchDetailsList.get(position);

        String match_no = "Match # " + (position + 1);
        String teams = match.getTeamOne() + " vs " + match.getTeamTwo();

        holder.matchNoTextView.setText(match_no);
        holder.teamsTextView.setText(teams);

        if(!match.isStatus()){
            holder.cardView.setBackgroundColor(Color.parseColor("#000000"));
            holder.matchNoTextView.setTextColor(Color.parseColor("#eeeeee"));
            holder.teamsTextView.setTextColor(Color.parseColor("#eeeeee"));
        }


    }

    @Override
    public int getItemCount() {
        return matchDetailsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView matchNoTextView;
        TextView teamsTextView;


        private MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            matchNoTextView = itemView.findViewById(R.id.matchNo);
            teamsTextView = itemView.findViewById(R.id.matchTeams);

        }
    }

}
