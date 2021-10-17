package com.example.healthcare.data;

public class PostGroupUserData {
    private String userNickName;
    private String userName;
    private String birth;
    private int photoIdx;

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getPhotoIdx() {
        return photoIdx;
    }

    public void setPhotoIdx(int photoIdx) {
        this.photoIdx = photoIdx;
    }
}
