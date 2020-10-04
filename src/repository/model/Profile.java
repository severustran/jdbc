package repository.model;

import java.util.Date;

public class Profile {
    protected int id;
    protected String display_name;
    protected String birthday;
    protected String gender;
    protected String phone;
    protected boolean isLecture;

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(final String display_name) {
        this.display_name = display_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public boolean isLecture() {
        return isLecture;
    }

    public void setLecture(final boolean lecture) {
        isLecture = lecture;
    }
}
