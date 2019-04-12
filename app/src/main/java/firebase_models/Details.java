package firebase_models;

public class Details {

    private int totalOvers;
    private int totalPlayers;
    private String teamOne;
    private String teamTwo;
    private boolean status;

    public Details(int totalOvers, int totalPlayers, String teamOne, String teamTwo, boolean status) {
        this.totalOvers = totalOvers;
        this.totalPlayers = totalPlayers;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.status = status;
    }

    public Details(){

    }

    public int getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}