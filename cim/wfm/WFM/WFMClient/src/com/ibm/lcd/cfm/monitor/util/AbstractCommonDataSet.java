
package com.ibm.lcd.cfm.monitor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public abstract class AbstractCommonDataSet {

    private List list = null;
    private Set set   = null;
    private Map map   = null;


    private LinkedList lList = null;
    

    public AbstractCommonDataSet(){
		lList = new LinkedList();
        this.list = lList;
    }

    public AbstractCommonDataSet(Stack list){
        list = new Stack();
        this.list = list;
    }

    public AbstractCommonDataSet(Vector list){
        list = new Vector();
        this.list = list;
    }

    public AbstractCommonDataSet(ArrayList list){
        list = new ArrayList();
        this.list = list;
    }

    public AbstractCommonDataSet(HashSet set){
        set = new HashSet();
        this.set = set;
    }

    public AbstractCommonDataSet(TreeSet set){
        set = new TreeSet();
        this.set = set;
    }

    public AbstractCommonDataSet(Hashtable map){
        map = new Hashtable();
        this.map = map;
    }

    public AbstractCommonDataSet(HashMap map){
        map = new HashMap();
        this.map = map;
    }

    public AbstractCommonDataSet(TreeMap map){
        map = new TreeMap();
        this.map = map;
    }

    public void setList(Object obj) {
        if (obj != null) {
            this.list.add(obj);
        }
    }

    public void setSet(Object obj) {
        if (obj != null) {
            this.set.add(obj);
        }
    }

    public void setMap(Object obj) {
        if (obj != null) {
        }
    }

    public List getList() {
        return this.list;
    }


    public int getListSize() {
        return this.list.size();
    }

    public int getSetSize() {
        return this.set.size();
    }

    public int getMapSize() {
        return this.map.size();
    }

//    public Iterator getListIterator(){
//        return this.iterator = this.list.iterator();
//    }
//
//    public Iterator getSetIterator(){
//        return this.iterator = set.iterator();
//    }
//
//    public Iterator getMapIterator(String key){
//        return this.iterator = (Iterator)map.get(key);
//    }

    public void clearList() {
        if (null != list) {
            this.list.clear();
        }
    }

    public void clearSet() {
        if (null != set) {
            this.set.clear();
        }
    }

    public void clearMap() {
        if (null != map) {
            this.map.clear();
        }
    }

    public void clearAll() {
        if (null != list) {
            list.clear();
        }
        if (null != set) {
            set.clear();
        }
        if (null != map) {
            map.clear();
        }
    }
}
