package com.example.healthcare;

public class RecyclerCommunityItem {
    private long idx;
    private String nickname;
    private String message;
    private String title;
    private long useridx;
    private String date;

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUseridx() {
        return useridx;
    }

    public void setUseridx(long useridx) {
        this.useridx = useridx;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
