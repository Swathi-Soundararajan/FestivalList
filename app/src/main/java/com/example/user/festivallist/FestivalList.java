package com.example.user.festivallist;

import android.view.inputmethod.EditorInfo;

/**
 * Created by user on 08-04-2018.
 */

public class FestivalList {
    //for activity 1
    private String Eventname;
    private String Location;
    private String Fees;
    private String EventDate;
    private String Email;
    private String thumb;
    //for activity 2
    private String address,phone,odate,ddate;

    public FestivalList(String name, String location,String fees,String date,String email,String thumb){
        this.Eventname = name;
        this.Location = location;
        this.Fees = fees;
        this.EventDate = date;
        this.Email= email;
        this.thumb = thumb;
    }
    public FestivalList(String name,String address,String email,String phone,String openingd,String deadline,String fees){
        this.Eventname = name;
        this.address=address;
        this.Email=email;
        this.phone=phone;
        this.odate=openingd;
        this.ddate= deadline;
        this.Fees=fees;
    }

    public String getEventname() {
        return Eventname;
    }

    public String getLocation() {
        return Location;
    }

    public String getFees() {
        return Fees;
    }

    public String getEventDate() {
        return EventDate;
    }

    public String getEmail() {
        return Email;
    }

    public String getThumb() {
        return thumb;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getOdate() {
        return odate;
    }

    public String getDdate() {
        return ddate;
    }
}
