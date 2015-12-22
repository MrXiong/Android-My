package com.recycler.zx.zxrecyclerview.FileAndJsonPaser;

/**
 * Created by zx on 2015/12/20.
 */
public class City {
    private String cityId;
    private String cityName;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
