package firebase_models;

public class Player {

    private String playerName;
    private Batting batting;
    private Bowling bowling;

    public Player(String playerName, int position) {

        this.playerName = playerName;

        //batting
        this.batting = new Batting();
        this.batting.position = position;
        this.batting.runs = 0;
        this.batting.balls = 0;
        this.batting.out = false;
        this.batting.played = false;

        //bowling
        this.bowling = new Bowling();
        this.bowling.balls = 0;
        this.bowling.runs = 0;
        this.bowling.wkts = 0;
        this.bowling.bowled = false;

    }

    public Player(){

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Batting getBatting() {
        return batting;
    }

    public void setBatting(Batting batting) {
        this.batting = batting;
    }

    public Bowling getBowling() {
        return bowling;
    }

    public void setBowling(Bowling bowling) {
        this.bowling = bowling;
    }

    public class Batting{

        public int position;
        public int runs;
        public int balls;
        public boolean out;
        public boolean played;

        public Batting(int position, int runs, int balls, boolean out, boolean played) {
            this.position = position;
            this.runs = runs;
            this.balls = balls;
            this.out = out;
            this.played = played;
        }

        public Batting(){

        }
    }

    public class Bowling{

        public int balls;
        public int runs;
        public int wkts;
        public boolean bowled;

        public Bowling(int balls, int runs, int wkts, boolean bowled) {
            this.balls = balls;
            this.runs = runs;
            this.wkts = wkts;
            this.bowled = bowled;
        }

        public Bowling() {

        }

    }

}