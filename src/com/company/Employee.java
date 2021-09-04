package com.company;

/**
 * Sharif Uni Employees Program ( Maktab59.Mr.Falahati )
 *
 * Group 3: Alireza HasanPour & Mahdi Leilaz Mehrabadi
 */

public class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private boolean gender;
    private boolean isMarried;
    private int spouseID;
    private double[] location = new double[2];
    private Distance distance = new Distance();
    private boolean isInvited;
    private String spouseName;

    public void setInvited(boolean invited) {
        isInvited = invited;
    }

    public double getDistance() {
        return distance.calLocation();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setIsInvited() {
        if (isMarried() && getDistance() <= 30.0)
            this.isInvited = true;
    }

    public boolean isInvited() {
        return isInvited;
    }

    public int getSpouse() {
        return spouseID;
    }

    public void setSpouse(int spouseID, int index) {
        this.spouseID = spouseID;
        this.isMarried = true;
        this.spouseName = Main.employeesArray.getInputArray()[index].firstName + " "
                + Main.employeesArray.getInputArray()[index].lastName;

    }

    public double[] getLocation() {
        return location;
    }

    public boolean setLocation(double location, boolean isLatitude) {

        boolean latitudeIsValid;

        if (isLatitude) {
            latitudeIsValid = distance.setEmployeeLatitoude(location);
            if (!latitudeIsValid) {
                System.out.println("The entered latitude is out of range (-90.0000,90.0000)");
                return latitudeIsValid;
            }
            this.location[0] = location;
        }

        boolean longtitudeIsValid;

        if (!isLatitude) {
            longtitudeIsValid = distance.setEmployeeLongtitude(location);
            if (!longtitudeIsValid) {
                System.out.println("The entered longtitude is out of range (-180.0000,180.0000)");
                return longtitudeIsValid;
            }
            this.location[1] = location;
        }

        return true;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        String output;
        output = '\n' + "Employee's ID : " + id + '\n' + "Name = " + firstName + " " + lastName + '\n' + "Gender = "
                + (gender ? "male" : "Female") + '\n' + "Married = " + (isMarried ? "Yes" : "No") + '\n';

        if (isMarried)
            output += "   : SpouseID = " + spouseID + '\n' + "   : Spouse Name = " + spouseName + '\n';

        output += "Location = " + String.format("[ %.4f", location[0]) + " , " + String.format("%.4f ]", location[1])
                + '\n' + "   : Distance from Uni = " + String.format("%.2f km", getDistance()) + '\n';
        return output;
    }
}
