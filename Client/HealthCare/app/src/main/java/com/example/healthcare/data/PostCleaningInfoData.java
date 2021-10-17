package com.example.healthcare.data;

public class PostCleaningInfoData {
    private int liquidAmount;
    private long recentlyCleaningTime;
    private int isCleaned;
    private int autoCleaningCycle;

    public int getLiquidAmount() {
        return liquidAmount;
    }

    public void setLiquidAmount(int liquidAmount) {
        this.liquidAmount = liquidAmount;
    }

    public long getRecentlyCleaningTime() {
        return recentlyCleaningTime;
    }

    public void setRecentlyCleaningTime(long recentlyCleaningTime) {
        this.recentlyCleaningTime = recentlyCleaningTime;
    }

    public int getIsCleaned() {
        return isCleaned;
    }

    public void setIsCleaned(int isCleaned) {
        this.isCleaned = isCleaned;
    }

    public int getAutoCleaningCycle() {
        return autoCleaningCycle;
    }

    public void setAutoCleaningCycle(int autoCleaningCycle) {
        this.autoCleaningCycle = autoCleaningCycle;
    }
}
