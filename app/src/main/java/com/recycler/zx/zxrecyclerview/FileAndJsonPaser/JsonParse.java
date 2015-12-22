package com.recycler.zx.zxrecyclerview.FileAndJsonPaser;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 2015/12/20.
 */
public class JsonParse {
    private Context mContext;
    public JsonParse(Context context){
        this.mContext = context;
    }

    public String createJson(List<City> list){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            int size = list.size();
            for (int i = 0; i <size ; i++) {
                City city = list.get(i);
                JSONObject obj = new JSONObject();
                obj.put("cityId",city.getCityId());
                obj.put("cityName",city.getCityName());
                jsonArray.put(obj);
            }
            jsonObject.put("citys",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    public List<City> parseJsonFromJsonReader(){

        String storeTypeList = readAssertFile("cache/HotCityList.json");

        List<City> list = new ArrayList<>();

        JsonReader jr = new JsonReader(new StringReader(storeTypeList));

        try {
            //开始解析数组
            jr.beginArray();
            while (jr.hasNext()) {
                City storeType = new City();
                //开始解析对象
                jr.beginObject();
                while (jr.hasNext()) {
                    String name = jr.nextName();
                    if("cityId".equals(name)) {
                        storeType.setCityId(jr.nextString());
                    }
                    else if("cityName".equals(name)) {
                        storeType.setCityName(jr.nextString());
                    }
                }
                jr.endObject();//结束对象的解析
                list.add(storeType);
            }
            jr.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    //Gson解析对象
    public City parseJsonObjFromGson(){
        Gson gson = new Gson();
        City city = gson.fromJson("json数据", City.class);
        return city;
    }
    //Gson解析数组
    public List<City> parseJsonArrayFromGson(){
        String citylist = readAssertFile("cache/HotCityList.json");
        Type type = new TypeToken<ArrayList<City>>(){}.getType();
        Gson gson = new Gson();
        List<City> list = gson.fromJson(citylist, type);
        return list;
    }
    //Gson解析数组多层嵌套
    public List<StoreType> parseJsonArrayFromGsons(){
        String citylist = readAssertFile("cache/StoreTypeList.json");
        Type type = new TypeToken<ArrayList<StoreType>>(){}.getType();
        Gson gson = new Gson();
        List<StoreType> list = gson.fromJson(citylist, type);
        return list;
    }
    public MyWeather parseJsonArrayFromGsons2(){
        String weatherList = readAssertFile("cache/Weather.json");
        Gson gson = new Gson();
        return gson.fromJson(weatherList, MyWeather.class);
    }
    //数组转成json数据
    public String toJsonArrayFromGson(List<City> list){
        Type type = new TypeToken<ArrayList<City>>(){}.getType();
        Gson gson = new Gson();
        return gson.toJson(list,type);
    }

    private  String readAssertFile(String fileName) {
        InputStream in = null;
        try {
            StringBuilder sb = new StringBuilder();
            in = mContext.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while( (line = br.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}
