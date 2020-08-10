package com.ricknotes.proquiz.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("response_code")
    private int responseCode;
    @SerializedName("results")
    private List<Questions> results;

    public Result(int responseCode, List<Questions> results) {
        this.responseCode = responseCode;
        this.results = results;
    }

    public Result() {
    }

    public int getResponseCode() {
        return responseCode;
    }

    public List<Questions> getResults() {
        return results;
    }
}
