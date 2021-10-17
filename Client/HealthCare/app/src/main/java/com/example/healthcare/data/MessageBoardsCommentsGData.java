package com.example.healthcare.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageBoardsCommentsGData {

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

        @SerializedName("commentResList")
        @Expose
        private List<CommentRes> commentResList = null;

        public List<CommentRes> getCommentResList() {
            return commentResList;
        }

        public void setCommentResList(List<CommentRes> commentResList) {
            this.commentResList = commentResList;
        }

        public static class CommentRes {

            @SerializedName("commentIdx")
            @Expose
            private Integer commentIdx;
            @SerializedName("userIdx")
            @Expose
            private Integer userIdx;
            @SerializedName("userNickName")
            @Expose
            private String userNickName;
            @SerializedName("commentMsg")
            @Expose
            private String commentMsg;
            @SerializedName("createdDate")
            @Expose
            private String createdDate;

            public Integer getCommentIdx() {
                return commentIdx;
            }

            public void setCommentIdx(Integer commentIdx) {
                this.commentIdx = commentIdx;
            }

            public Integer getUserIdx() {
                return userIdx;
            }

            public void setUserIdx(Integer userIdx) {
                this.userIdx = userIdx;
            }

            public String getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(String userNickName) {
                this.userNickName = userNickName;
            }

            public String getCommentMsg() {
                return commentMsg;
            }

            public void setCommentMsg(String commentMsg) {
                this.commentMsg = commentMsg;
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