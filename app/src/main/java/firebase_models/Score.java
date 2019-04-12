package firebase_models;

public class Score {

    private int balls;
    private int runs;
    private int wkts;
    private int wides;
    private int noballs;

    public Score(int balls, int runs, int wkts, int wides, int noballs) {
        this.balls = balls;
        this.runs = runs;
        this.wkts = wkts;
        this.wides = wides;
        this.noballs = noballs;
    }

    public Score(){

    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWkts() {
        return wkts;
    }

    public void setWkts(int wkts) {
        this.wkts = wkts;
    }

    public int getWides() {
        return wides;
    }

    public void setWides(int wides) {
        this.wides = wides;
    }

    public int getNoballs() {
        return noballs;
    }

    public void setNoballs(int noballs) {
        this.noballs = noballs;
    }
}
