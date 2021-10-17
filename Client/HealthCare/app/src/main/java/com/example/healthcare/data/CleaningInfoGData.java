package com.example.healthcare.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CleaningInfoGData {

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
        private Long cleaningIdx;
        @SerializedName("liquidAmount")
        @Expose
        private Integer liquidAmount;
        @SerializedName("recentlyCleaning")
        @Expose
        private Long recentlyCleaning;
        @SerializedName("isCleaned")
        @Expose
        private Integer isCleaned;
        @SerializedName("autoCleaningCycle")
        @Expose
        private Integer autoCleaningCycle;

        public Long getCleaningIdx() {
            return cleaningIdx;
        }

        public void setCleaningIdx(Long cleaningIdx) {
            this.cleaningIdx = cleaningIdx;
        }

        public Integer getLiquidAmount() {
            return liquidAmount;
        }

        public void setLiquidAmount(Integer liquidAmount) {
            this.liquidAmount = liquidAmount;
        }

        public Long getRecentlyCleaning() {
            return recentlyCleaning;
        }

        public void setRecentlyCleaning(Long recentlyCleaning) {
            this.recentlyCleaning = recentlyCleaning;
        }

        public Integer getIsCleaned() {
            return isCleaned;
        }

        public void setIsCleaned(Integer isCleaned) {
            this.isCleaned = isCleaned;
        }

        public Integer getAutoCleaningCycle() {
            return autoCleaningCycle;
        }

        public void setAutoCleaningCycle(Integer autoCleaningCycle) {
            this.autoCleaningCycle = autoCleaningCycle;
        }

    }
}