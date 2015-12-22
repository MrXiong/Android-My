package com.recycler.zx.zxrecyclerview.Service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zx on 2015/12/16.
 */
public class Person implements Parcelable {
     public String name;
     public String job;

    public Person(){}
    protected Person(Parcel in) {
        name = in.readString();
        job = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(job);
    }
}
