package com.example.jebineinstein.travel;

/**
 * Created by jebineinstein on 31/1/17.
 */

public class BusDataModel {

    String from, to, bpoint, dpoint, apoint, busname, busid, seatavail, cost, date;

    public BusDataModel(String busid, String busname, String dpoint, String apoint, String bpoint, String from, String to,  String cost, String date, String seatavail) {
        this.from = from;
        this.to = to;
        this.bpoint = bpoint;
        this.dpoint = dpoint;
        this.apoint = apoint;
        this.busname = busname;
        this.busid = busid;
        this.seatavail = seatavail;
        this.cost = cost;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBpoint() {
        return bpoint;
    }

    public String getDpoint() {
        return dpoint;
    }

    public String getApoint() {
        return apoint;
    }

    public String getBusname() {
        return busname;
    }

    public String getBusid() {
        return busid;
    }

    public String getSeatavail() {
        return seatavail;
    }

    public String getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }
}
