package com.recycler.zx.zxrecyclerview.Service;

import android.os.RemoteException;

/**业务接口的具体实现类
 * Created by zx on 2015/12/16.
 */
public class CatImpl extends ICat.Stub {
    private String name;

    @Override
    public Person getPerson() throws RemoteException {
        Person person = new Person();
        person.name = "小猫";
        person.job = "稍低";

        return person;
    }

    @Override
    public void setName(String name) throws RemoteException {
    this.name = name;
    }

    @Override
    public String desc() throws RemoteException {
        return "我的名字是"+name;
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
