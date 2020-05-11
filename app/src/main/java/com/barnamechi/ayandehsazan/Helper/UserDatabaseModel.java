package com.barnamechi.ayandehsazan.Helper;

public class UserDatabaseModel {
    private int id;
    private String username;
    private String displayname;
    private String useremail;
    private String userAvatarURL;


    public UserDatabaseModel() {
    }


    public UserDatabaseModel(int id, String username, String displayname, String useremail, String userAvatarURL) {
        this.id = id;
        this.username = username;
        this.displayname = displayname;
        this.useremail = useremail;
        this.userAvatarURL = userAvatarURL;
    }

    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
