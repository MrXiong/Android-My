package com.recycler.zx.zxrecyclerview.FileAndJsonPaser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 2015/12/20.
 */
public class XmlPull {
    public static List<Person> pullxml(InputStream in)throws Exception{
        List<Person> list=null;
        Person person = null;
        // 由android.util.Xml创建一个XmlPullParser实例  
        XmlPullParser parser = Xml.newPullParser();
        // 设置输入流 并指明编码方式  
        parser.setInput(in, "UTF-8");
        // 产生第一个事件  
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                // 判断当前事件是否为文档开始事件  
                case XmlPullParser.START_DOCUMENT:
                    list=new ArrayList<Person>();// 初始化list集合  
                    break;
                // 判断当前事件是否为标签元素开始事件  
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("Person")) { // 判断开始标签元素是否是Person  
                        person = new Person();
                    } else if (parser.getName().equals("personId")) {
                        eventType = parser.next();
                        // 得到Person标签的属性值，并设置Person的id  
                        person.setPersonId(parser.getText());
                    } else if (parser.getName().equals("name")) { // 判断开始标签元素是否是Person  
                        eventType = parser.next();
                        person.setName(parser.getText());
                    } else if (parser.getName().equals("sex")) { // 判断开始标签元素是否是price
                        eventType = parser.next();
                        person.setSex(parser.getText());
                    }
                    break;
                // 判断当前事件是否为标签元素结束事件  
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("Person")) { // 判断结束标签元素是否是Person  
                        list.add(person); // 将Person添加到Persons集合
                        person = null;
                    }
                    break;
            }
            // 进入下一个元素并触发相应事件  
            eventType = parser.next();
        }
        return list;
    }
}
