package com.alfinapp.data.network.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bid {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("contest")
    @Expose
    private Integer contest;
    @SerializedName("bid_amount")
    @Expose
    private String bidAmount;
    @SerializedName("is_removed")
    @Expose
    private Boolean isRemoved;
    @SerializedName("user")
    @Expose
    private Integer user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContest() {
        return contest;
    }

    public void setContest(Integer contest) {
        this.contest = contest;
    }

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
