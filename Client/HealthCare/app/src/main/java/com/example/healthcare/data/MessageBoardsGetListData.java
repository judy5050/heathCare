package com.example.healthcare.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageBoardsGetListData {

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

        @SerializedName("messageBoardList")
        @Expose
        private List<MessageBoard> messageBoardList = null;

        public List<MessageBoard> getMessageBoardList() {
            return messageBoardList;
        }

        public void setMessageBoardList(List<MessageBoard> messageBoardList) {
            this.messageBoardList = messageBoardList;
        }

        public static class MessageBoard {

            @SerializedName("messageBoardIdx")
            @Expose
            private Integer messageBoardIdx;
            @SerializedName("userNickName")
            @Expose
            private String userNickName;
            @SerializedName("message")
            @Expose
            private String message;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("userIdx")
            @Expose
            private Integer userIdx;
            @SerializedName("createdDate")
            @Expose
            private String createdDate;

            public Integer getMessageBoardIdx() {
                return messageBoardIdx;
            }

            public void setMessageBoardIdx(Integer messageBoardIdx) {
                this.messageBoardIdx = messageBoardIdx;
            }

            public String getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(String userNickName) {
                this.userNickName = userNickName;
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

            public Integer getUserIdx() {
                return userIdx;
            }

            public void setUserIdx(Integer userIdx) {
                this.userIdx = userIdx;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

        }
    }
}