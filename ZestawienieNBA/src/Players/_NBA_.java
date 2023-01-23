package Players;

import java.util.ArrayList;
import java.util.Collections;

public class _NBA_ {
    private ArrayList<String> names;

    private String name;
    private String team;

    private int rebounds;
    private int gamesPlayed;
    private float minutesPlayed;
    private int points;
    private int assists;
    private int threePointers;
    private String percentageOf3P;

    public _NBA_(String name){
        this.names = new ArrayList<>(Collections.singleton(name));
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getNames() {
        return names;
    }

    public String getTeam() {
        return team;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public float getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(float minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getThreePointers() {
        return threePointers;
    }

    public void setThreePointers(int threePointers) {
        this.threePointers = threePointers;
    }

    public String getPercentageOf3P() {
        return percentageOf3P;
    }

    public void setPercentageOf3P(String percentageOf3P) {
        this.percentageOf3P = percentageOf3P;
    }
    public String toString() {
        return "namee: " + names +
                ", team: " +  team+
                ", points: " + points +
                ", assists: " + assists +
                ", Three Pointers: " + threePointers +
                ", Percentage of Three Pointers: 0" + percentageOf3P+
                ", Games Played: " + gamesPlayed +
                ", Minutes Played: " +minutesPlayed+
                '}';
    }

}
