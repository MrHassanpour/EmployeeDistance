package com.company;


/**
 * Sharif Uni Employees Program ( Maktab59.Mr.Falahati )
 *
 * Group 3: Alireza HasanPour & Mahdi Leilaz Mehrabadi
 */

import java.lang.Math;

public class Distance {
    private final double[] latitudeLimit = { -90.0, 90.0 };
    private final double[] longtitudeLimit = { -180.0, 180.0 };
    private static final double latitude = 35.7036;
    private static final double longtitude = 51.3515;
    private static final double radios = 6371.0;
    private static final double radianToDegreeConstant = Math.PI / 180;
    private double employeeLatitoude;
    private double employeeLongtitude;

    public boolean setEmployeeLatitoude(double employeeLatitoude) {
        boolean isValid = true;
        if (employeeLatitoude > latitudeLimit[1] || employeeLatitoude < latitudeLimit[0]) {
            isValid = false;
        }
        if (isValid)
            this.employeeLatitoude = employeeLatitoude;

        return isValid;
    }

    public boolean setEmployeeLongtitude(double employeeLongtitude) {
        boolean isValid = true;
        if (employeeLongtitude > longtitudeLimit[1] || employeeLongtitude < longtitudeLimit[0]) {
            isValid = false;
        }
        if (isValid)
            this.employeeLongtitude = employeeLongtitude;

        return isValid;
    }

    public double getEmployeeLatitoude() {
        return employeeLatitoude;
    }

    public double getEmployeeLongtitude() {
        return employeeLongtitude;
    }

    public double calLocation() {
        double latitudeDegree = latitude * radianToDegreeConstant;
        double employeeLatitoudeDegree = employeeLatitoude * radianToDegreeConstant;
        double longtitudesDiffrenceDegree = (longtitude - employeeLongtitude) * radianToDegreeConstant;
        double latitudesDiffrenceDegree = (latitude - employeeLatitoude) * radianToDegreeConstant;

        double value = Math.pow(Math.sin(latitudesDiffrenceDegree / 2), 2) + Math.cos(latitudeDegree)
                * Math.cos(employeeLatitoudeDegree) * Math.pow(Math.sin(longtitudesDiffrenceDegree / 2), 2);
        double centralAngle = 2 * Math.asin(Math.sqrt(value));

        double distance = centralAngle * radios;
        return distance;

    }
}
