package SatProjekt;

import java.util.ArrayList;
import java.util.Date;

public class Satellite {
    private ArrayList<String> names;
    private int norad;
    private float orbitalPosition;
    private char orbitalPositionDirection;
    private float satellitePosition;
    private char satellitePositionDirection;
    private Date launchDate;
    private int launchMass;
    private String band;
    private Date updated;

    public Satellite(String name) {
        this.names = new ArrayList<>();
        this.names.add(name);
    }


    public ArrayList<String> getNames() {
        return names;
    }

    public void addName(String name) {
        this.names.add(name);
    }

    public void addNames(ArrayList<String> names) {
        this.names.addAll(names);
    }

    public int getNorad() {
        return norad;
    }

    public void setNorad(int norad) {
        this.norad = norad;
    }

    public float getOrbitalPosition() {
        return orbitalPosition;
    }

    public void setOrbitalPosition(float orbitalPosition) {
        this.orbitalPosition = orbitalPosition;
    }

    public char getOrbitalPositionDirection() {
        return orbitalPositionDirection;
    }

    public void setOrbitalPositionDirection(char orbitalPositionDirection) {
        this.orbitalPositionDirection = orbitalPositionDirection;
    }

    public float getSatellitePosition() {
        return satellitePosition;
    }

    public void setSatellitePosition(float satellitePosition) {
        this.satellitePosition = satellitePosition;
    }

    public char getSatellitePositionDirection() {
        return satellitePositionDirection;
    }

    public void setSatellitePositionDirection(char satellitePositionDirection) {
        this.satellitePositionDirection = satellitePositionDirection;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public int getLaunchMass() {
        return launchMass;
    }

    public void setLaunchMass(int launchMass) {
        this.launchMass = launchMass;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Satellites{" +
                "names: " + names +
                ", norad: " + norad +
                ", orbital_position: " + orbitalPosition + "° " + orbitalPositionDirection +
                ", satellite_position: " + satellitePosition + "° " + satellitePositionDirection +
                ", launch_date: " + launchDate +
                ", launch_mass: " + launchMass +
                ", band: '" + band + '\'' +
                ", Updated: " + updated +
                '}';
    }
}
