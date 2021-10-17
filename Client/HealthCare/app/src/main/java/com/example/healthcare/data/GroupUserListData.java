package com.example.healthcare.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupUserListData {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Result result;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {

        @SerializedName("userInfoList")
        @Expose
        private List<UserInfo> userInfoList = null;
        @SerializedName("count")
        @Expose
        private Integer count;

        public List<UserInfo> getUserInfoList() {
            return userInfoList;
        }

        public void setUserInfoList(List<UserInfo> userInfoList) {
            this.userInfoList = userInfoList;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public static class UserInfo {

            @SerializedName("userIdx")
            @Expose
            private Integer userIdx;
            @SerializedName("userName")
            @Expose
            private String userName;
            @SerializedName("userNickName")
            @Expose
            private String userNickName;
            @SerializedName("birth")
            @Expose
            private String birth;
            @SerializedName("photoIdx")
            @Expose
            private Integer photoIdx;

            public Integer getUserIdx() {
                return userIdx;
            }

            public void setUserIdx(Integer userIdx) {
                this.userIdx = userIdx;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(String userNickName) {
                this.userNickName = userNickName;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public Integer getPhotoIdx() {
                return photoIdx;
            }

            public void setPhotoIdx(Integer photoIdx) {
                this.photoIdx = photoIdx;
            }

        }
    }
}