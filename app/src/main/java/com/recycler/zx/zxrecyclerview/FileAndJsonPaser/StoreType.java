package com.recycler.zx.zxrecyclerview.FileAndJsonPaser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zx on 2015/12/20.
 */
public class StoreType {
    private String storeTypeId;
    private String storeTypeName;
    private String pid;
    private List<StoreType> children;
    private int position;

    public String getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(String storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<StoreType> getChildren() {
        return children;
    }

    public void setChildren(List<StoreType> children) {
        this.children = children;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "StoreType{" +
                "storeTypeId='" + storeTypeId + '\'' +
                ", storeTypeName='" + storeTypeName + '\'' +
                ", pid='" + pid + '\'' +
                ", children=" + children +
                ", position=" + position +
                '}';
    }
}
