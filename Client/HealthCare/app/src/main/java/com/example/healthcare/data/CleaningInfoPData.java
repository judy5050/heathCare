package com.example.healthcare.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CleaningInfoPData {

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

        @SerializedName("cleaningIdx")
        @Expose
        private Integer cleaningIdx;
        @SerializedName("groupIdx")
        @Expose
        private Integer groupIdx;

        public Integer getCleaningIdx() {
            return cleaningIdx;
        }

        public void setCleaningIdx(Integer cleaningIdx) {
            this.cleaningIdx = cleaningIdx;
        }

        public Integer getGroupIdx() {
            return groupIdx;
        }

        public void setGroupIdx(Integer groupIdx) {
            this.groupIdx = groupIdx;
        }

    }
}