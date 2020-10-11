
package com.ibm.lcd.cfm.util;


//import com.ibm.lcd.cfm.monitor.util.AbstractCommonDataSet;

public class CommonDataSet 
//extends AbstractCommonDataSet
{

    private String EQPT_ID    = "";
    //private String STOCKER_ID = "";


    public CommonDataSet(String EQPT_ID){
        super();
		this.EQPT_ID = EQPT_ID;
    }

    public void setEqptId(String EQPT_ID){
    	this.EQPT_ID = EQPT_ID;
    }

    public String getEqptId(){
		return this.EQPT_ID;
    }
}
