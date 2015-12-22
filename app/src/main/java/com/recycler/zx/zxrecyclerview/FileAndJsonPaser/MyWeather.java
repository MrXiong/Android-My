package com.recycler.zx.zxrecyclerview.FileAndJsonPaser;

import java.util.List;

/**
 * Created by zx on 2015/12/20.
 */
public class MyWeather {
    private String error;
    private String status;
    private String date;
    private List<Results> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MyWeather{" +
                "error='" + error + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", results=" + results +
                '}';
    }
}
