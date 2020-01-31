package com.alfinapp.data.network.model.user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubscribedContest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("alfin_identifier")
    @Expose
    private String alfinIdentifier;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("rewards")
    @Expose
    private List<Reward> rewards = null;
    @SerializedName("bids")
    @Expose
    private List<Bid> bids = null;
    @SerializedName("min_bid")
    @Expose
    private String minBid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlfinIdentifier() {
        return alfinIdentifier;
    }

    public void setAlfinIdentifier(String alfinIdentifier) {
        this.alfinIdentifier = alfinIdentifier;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public String getMinBid() {
        return minBid;
    }

    public void setMinBid(String minBid) {
        this.minBid = minBid;
    }

}
