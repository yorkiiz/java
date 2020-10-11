
package com.ibm.lcd.cfm.monitor.util;

import java.util.HashMap;
import java.util.LinkedList;

public class DeviceTable{

    final String CLASS_NAME                 = "DeviceTable";

    private String OnlineInstallationNumber = "";

    private String AllDeviceNumber          = "";

    private String WorkingLotNumber         = "";

    private String DeliveringLotNumber      = "";

    private String NowWorkingLotNumber      = "";

    private String NowDeliveringNumber      = "";

    private HashMap map                     = null;

    private LinkedList list                 = null;

    public DeviceTable(){
        map  = new HashMap();
        list = new LinkedList();
    }

    public void setOnlineInstallationNumber(String OnlineInstallationNumber){
        map.put("OnlineInstallationNumber",StringUtil.getCheckedValue(this.OnlineInstallationNumber,""));
    }

    public void setOnlineInstallationNumber(String key, String OnlineInstallationNumber){
        map.put(key, StringUtil.getCheckedValue(this.OnlineInstallationNumber,""));
    }

    public String getOnlineInstallationNumber(){

        return (String)map.get("OnlineInstallationNumber");
    }

    public String getOnlineInstallationNumber(String key){

        return (String)map.get(key);
    }


    public void setAllDeviceNumber(String AllDeviceNumber){
        map.put("AllDeviceNumber",StringUtil.getCheckedValue(this.AllDeviceNumber,""));
    }

    public void setAllDeviceNumber(String key, String AllDeviceNumber){
        map.put(key, StringUtil.getCheckedValue(this.AllDeviceNumber,""));
    }

    public String getAllDeviceNumber(){
        return (String)map.get("AllDeviceNumber");
    }

    public String getAllDeviceNumber(String key){
        return (String)map.get(key);
    }

    public void setWorkingLotNumber(String WorkingLotNumber){
        map.put("WorkingLotNumber",StringUtil.getCheckedValue(this.WorkingLotNumber,""));
    }

    public void setWorkingLotNumber(String key, String WorkingLotNumber){
        map.put(key, StringUtil.getCheckedValue(this.WorkingLotNumber,""));
    }

    public String getWorkingLotNumber(){
        return (String)map.get("WorkingLotNumber");
    }

    public String getWorkingLotNumber(String key){
        return (String)map.get(key);
    }

    public void setDeliveringLotNumber(String DeliveringLotNumber){
        map.put("DeliveringLotNumber",StringUtil.getCheckedValue(this.DeliveringLotNumber,""));
    }

    public void setDeliveringLotNumber(String key, String DeliveringLotNumber){

        map.put(key, StringUtil.getCheckedValue(this.DeliveringLotNumber,""));
    }

    public String getDeliveringLotNumber(){
        return (String)map.get("DeliveringLotNumber");
    }

    public String getDeliveringLotNumber(String key){
        return (String)map.get(key);
    }

    public void setNowWorkingLotNumber(String NowWorkingLotNumber){
        map.put("NowWorkingLotNumber",StringUtil.getCheckedValue(this.NowWorkingLotNumber,""));
    }

    public String getNowWorkingLotNumber(){
        return (String)map.get("NowWorkingLotNumber");
    }

    public void setNowDeliveringNumber(String NowDeliveringNumber){
        map.put("NowDeliveringNumber",StringUtil.getCheckedValue(this.NowDeliveringNumber,""));
    }

    public String getNowDeliveringNumber(){
        return (String)map.get("NowDeliveringNumber");
    }

    public void setList(Object obj) {
        if (obj != null) {
            this.list.add(obj);
        }
    }

    public LinkedList getList(){
        return this.list;
    }

    public int getListSize(){
        return this.list.size();
    }

    public HashMap getDeviceMap(){
        return this.map;
    }

    public int getDeviceMapSize(){
        return this.map.size();
    }
}
