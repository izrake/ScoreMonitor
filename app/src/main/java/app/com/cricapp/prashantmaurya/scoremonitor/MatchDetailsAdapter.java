package app.com.cricapp.prashantmaurya.scoremonitor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by prashant.maurya on 1/2/2017.
 */

public class MatchDetailsAdapter extends RecyclerView.Adapter<MatchDetailsAdapter.MyViewHolder> {

    private Context mContext;
    private List<MatchDetails> matchDetailsList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_card_view,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MatchDetails matchDetails=matchDetailsList.get(position);
        holder.matchBetween.setText(matchDetails.getTeamAName()+"("+matchDetails.getRunScoredTeamA()+")/"+matchDetails.getTeamBName()+"("+matchDetails.getRunScoredTeamB()+")");
        holder.runScoredTeamA.setText(matchDetails.getTeamAName()+" Scored:"+matchDetails.getRunScoredTeamA());
        holder.runScoredTeamB.setText(matchDetails.getTeamBName()+" Scored:"+matchDetails.getRunScoredTeamB());

        Glide.with(mContext).load(matchDetails.getWinnerImage()).into(holder.winnerImage);


    }

    @Override
    public int getItemCount() {
        return matchDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView matchBetween, runScoredTeamA, runScoredTeamB,matchId;

        public ImageView winnerImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            matchBetween = (TextView) itemView.findViewById(R.id.match_between);
            runScoredTeamA = (TextView) itemView.findViewById(R.id.score_team_a);
            runScoredTeamB = (TextView) itemView.findViewById(R.id.score_team_b);
            winnerImage=(ImageView)itemView.findViewById(R.id.winner_image);
            matchId=(TextView)itemView.findViewById(R.id.match_id);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            TextView matchBetween=(TextView) itemView.findViewById(R.id.match_between);
            Intent intent=new Intent(mContext,ScoreDetailActivity.class);
            intent.putExtra("MatchBetween",matchBetween.getText().toString());

            mContext.startActivity(intent);
        }
    }

    public MatchDetailsAdapter(Context mContext, List<MatchDetails> matchDetailsList) {
        this.mContext = mContext;
        this.matchDetailsList = matchDetailsList;
    }


}
