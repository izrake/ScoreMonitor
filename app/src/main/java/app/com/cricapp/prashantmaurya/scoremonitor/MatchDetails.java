package app.com.cricapp.prashantmaurya.scoremonitor;

/**
 * Created by prashant.maurya on 1/2/2017.
 */

public class MatchDetails {

    public String getTeamAName() {
        return teamAName;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    private String teamAName;
    private int matchId;

    public MatchDetails(String teamAName,String teamBName,String runScoredTeamA,  String runScoredTeamB, int winnerImage,int matchId) {
        this.teamAName = teamAName;
        this.winnerImage = winnerImage;
        this.runScoredTeamB = runScoredTeamB;
        this.runScoredTeamA = runScoredTeamA;
        this.teamBName = teamBName;
    }

    private String teamBName;
    private String runScoredTeamA;
    private String runScoredTeamB;
    private int winnerImage;

    public int getWinnerImage() {
        return winnerImage;
    }

    public void setWinnerImage(int winnerImage) {
        this.winnerImage = winnerImage;
    }

    public MatchDetails() {

    }






    public String getRunScoredTeamA() {
        return runScoredTeamA;
    }

    public void setRunScoredTeamA(String runScoredTeamA) {
        this.runScoredTeamA = runScoredTeamA;
    }

    public String getRunScoredTeamB() {
        return runScoredTeamB;
    }

    public void setRunScoredTeamB(String runScoredTeamB) {
        this.runScoredTeamB = runScoredTeamB;
    }

}
