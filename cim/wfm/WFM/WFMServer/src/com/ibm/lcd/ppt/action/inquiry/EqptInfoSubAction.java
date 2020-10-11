
package com.ibm.lcd.ppt.action.inquiry;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibm.cxl.struct.StructElement;
import com.ibm.cxl.struct.StructUtilities;
import com.ibm.lcd.cfm.monitor.util.StringUtil;
import com.ibm.lcd.ppt.action.PPTAction;
//import com.ibm.lcd.ppt.action.sql.EqptInfoSQL;
//import com.ibm.lcd.ppt.action.sql.LotStayLimitSQL;

public class EqptInfoSubAction extends PPTAction {

	
    static String eqpt_id;

    static	boolean eqpt_common = true;

    static	String	ret_date_time = "";
	static  String	ret_rpt_date = "";
	static  String	ret_rpt_time = "";
    static	String	ret_flg = "";
    static	String	ret_alrt_id = "";

	static HashMap		eqptMap;
	
    static	ArrayList	chList;
    static	ArrayList	sList;
    static	ArrayList	eList;
    static	ArrayList	portList;

    static StructElement	structApieqald;
    static StructElement	structApleqalm;
    static StructElement	structDpiqptpd;
    static StructElement	structApinqeqp;
    static StructElement	structLotInfo;
	static StructElement	structApiwipeq;

	static	HashMap	lotStayMap = new HashMap();
	
    public EqptInfoSubAction(String id){
        eqpt_id = id;
        chList = new ArrayList();
        sList = new ArrayList();
        eList = new ArrayList();
        portList = new ArrayList();
    }

    public void setApinqeqpInfo() throws Exception{

        boolean applyoff = false;
		String recipe_id = "";
		String setupUseLimit = "";

        if(structApinqeqp == null)	{
        	eqptMap = null;
        	return;
        }
		eqptMap = (HashMap)StructUtilities.toMap(structApinqeqp);

		if(structDpiqptpd != null)	{
			StructElement[] oaryDpiqptpd = structDpiqptpd.select("oary");
			if (oaryDpiqptpd.length > 0) {
				for (int j = 0; j < oaryDpiqptpd.length; j++){
					HashMap hashDpiqptpd = (HashMap)StructUtilities.toMap(oaryDpiqptpd[j]);
					String active_flg = (String)hashDpiqptpd.get("active_flg");
					if("Y".equals(active_flg)){
						recipe_id = (String)hashDpiqptpd.get("recipe_id");
						if(recipe_id != null && recipe_id.length() == 0)	{ recipe_id = null; }
						setupUseLimit = (String)hashDpiqptpd.get("product_set_cnt");
						break;
					}
				}
			}
			eqptMap.put("recipe_id", recipe_id);
			eqptMap.put("product_set_cnt", setupUseLimit);
		}
		
		
        String eqpt_cate = (String)eqptMap.get("eqpt_cate");
        if(!eqpt_cate.equals("C"))	{eqpt_common = false;}

        StructElement[] elementApplyoff;
        if(eqpt_common && structApleqalm != null)	{
            elementApplyoff = structApleqalm.select("oary");
            if (elementApplyoff != null && elementApplyoff.length > 0) {
                int alrt_code_cnt = 0;
                for (int k = 0; k < elementApplyoff.length; k++){

                    HashMap hashApplyoff = (HashMap)StructUtilities.toMap(elementApplyoff[k]);
                    String alrt_code = (String)hashApplyoff.get("alrt_code");
                    String alert_on_off_flg = (String)hashApplyoff.get("alert_on_off_flg");
                    String aply_eqpt_id = (String)hashApplyoff.get("eqpt_id");
                    
					alrt_code        = StringUtil.getCheckedValue(alrt_code, "");
					alert_on_off_flg = StringUtil.getCheckedValue(alert_on_off_flg, "");
					aply_eqpt_id     = StringUtil.getCheckedValue(aply_eqpt_id, "");
					
                    if((eqpt_id.equals(aply_eqpt_id) && "Y".equals(alert_on_off_flg))&& alrt_code.equals("193"))	{alrt_code_cnt++;}
                }
                if(alrt_code_cnt <= 1)	{applyoff = true;}
            }
        }
		
        int m;
        StructElement[] oary3 = structApinqeqp.select("oary3");
        if (oary3.length > 0) {

            for (m = 0; m < oary3.length; m++){
                HashMap hashSubEqpt = (HashMap)StructUtilities.toMap(oary3[m]);
                String sub_eqpt_id = (String)hashSubEqpt.get("sub_eqpt_id");
				if(sub_eqpt_id != null){
					String chamberNameRule = config.getProperty("namerule.chamber."+eqpt_id.substring(0,3));//S003
					if(chamberNameRule != null && sub_eqpt_id.startsWith(chamberNameRule))	{
	                    chList.add(hashSubEqpt);
	                }
	                else if(sub_eqpt_id.startsWith("S0"))	{
	                    sList.add(hashSubEqpt);
	                }
	                else if(sub_eqpt_id.startsWith("E"))	{
	                    if(applyoff)	{hashSubEqpt.put("applyoff", "ON");}
	                    else			{hashSubEqpt.put("applyoff", "OFF");}
	                    eList.add(hashSubEqpt);
	                }
				}
            }
        }
		
		
        int n,p;
        StructElement[] oaryApinqeqp = structApinqeqp.select("oary");
        n = structApinqeqp.getInteger("port_cnt", 0);
        if (n > 0 && oaryApinqeqp.length > 0) {
            for (p = 0; p < n && p < oaryApinqeqp.length; p++){
                HashMap hashPort = (HashMap)StructUtilities.toMap(oaryApinqeqp[p]);
                portList.add(hashPort);
            }
        }
		
        return;
    }
    
//	public void getLotAlarmList() throws Exception{
//		
//		ArrayList lotStayList = new ArrayList();
//		LotStayLimitSQL lotstaysql = new LotStayLimitSQL();
//		
//		lotStayList = lotstaysql.getLotLimitList();
//		for(int i = 0; i < lotStayList.size(); i++){
//			HashMap localmap = (HashMap)lotStayList.get(i);
//			String local_eqpt_id = StringUtil.getCheckedValue((String)localmap.get("code_ext"), "");
//			String lotlimit = StringUtil.getCheckedValue((String)localmap.get("sub_item"), "0");
//			lotStayMap.put(local_eqpt_id, lotlimit);
//		}
//		lotstaysql.close();
//		
//	}
	
    public ArrayList getLotInfoList() throws Exception{

        int i,j;
        ArrayList retlist = new ArrayList(0);
        String keyElement;
        
		if(structApiwipeq != null){
			StructElement[] wipoary = structApiwipeq.select("oary");
			
			if (wipoary.length > 0) {
				ArrayList prelotid = new ArrayList(0);
				boolean added_flg;
				
				for (j = 0; j < wipoary.length; j++){
					added_flg = false;											
					HashMap hashmap = (HashMap)StructUtilities.toMap(wipoary[j]);
					String lot_id = StringUtil.getCheckedValue((String)hashmap.get("lot_id"), "");
					String position_id = StringUtil.getCheckedValue((String)hashmap.get("position"), "");

					for(int k = 0; k < prelotid.size(); k++){
						String pre_lot_id = (String)prelotid.get(k);
						if(pre_lot_id != null && pre_lot_id.equals(lot_id)){
							added_flg = true;
							break;
						}
					}
					
					if(!added_flg && eqpt_id.equals(position_id)){
						prelotid.add(lot_id);
						String crr_id = (String)hashmap.get("crr_id");
						
						String logof_date = StringUtil.getCheckedValue((String)hashmap.get("logof_date"), "");
						String logof_time = StringUtil.getCheckedValue((String)hashmap.get("logof_time"), "");
						String rep_logof_date = logof_date.replace('-','/');
						String rep_logof_time = logof_time.replace('.',':');
						String logof_date_time = rep_logof_date + " " + rep_logof_time;
						hashmap.put("logon_date_time", logof_date_time);
						
						String ope_no = StringUtil.getCheckedValue((String)hashmap.get("nx_ope_no"), "");
						hashmap.put("cr_ope_no", ope_no);
						
						try{
							Date lst_date = Date.valueOf(rep_logof_date);
							Time lst_time = Time.valueOf(rep_logof_time);
							
							long staytime = new java.util.Date().getTime() - (lst_date.getTime() + lst_time.getTime());// ‘Ø—¯??ŠÔ(ƒ~??•b)
							
							String lotlimit = (String)lotStayMap.get(eqpt_id);
							long long_lotlimit = Long.parseLong(lotlimit);
							long_lotlimit = long_lotlimit * 60 * 1000;
							if(staytime > long_lotlimit){
								hashmap.put("alrt_flg", "Y");
							}else{
								hashmap.put("alrt_flg", "");
							}
						}catch(IllegalArgumentException iae){
							hashmap.put("alrt_flg", "");
						}
						StructElement structDpiqcrrx = structFactory.getStruct(config.getProperty("dpiqcrrx.struct.in"));
						structDpiqcrrx.setString("crr_id", crr_id);
						structDpiqcrrx = exchange(config.getProperty("dpiqcrrx.q.in"), structDpiqcrrx, config.getProperty("dpiqcrrx.q.out"), config.getProperty("dpiqcrrx.struct.out"));
						
						HashMap hashDpiqcrrx = (HashMap)StructUtilities.toMap(structDpiqcrrx);
						String position = StringUtil.getCheckedValue((String)hashDpiqcrrx.get("position"), "");
						hashmap.put("position", position);
		                
						retlist.add(hashmap);
					}
				}
			}
		}
        
        if(structLotInfo != null){
			StructElement[] oary;
			
	        if(eqpt_common)	{
	            oary = structLotInfo.select("oary1");
	            i = structLotInfo.getInteger("crr_cnt", 0);
	            keyElement = "crr_id";
	        }else				{
	            oary = structLotInfo.select("oary");
	            i = structLotInfo.getInteger("sht_cnt", 0);
	            keyElement = "logon_crr_id";
	        }
	
	        if (i > 0 && oary.length > 0) {
				ArrayList prelotid = new ArrayList(0);
				boolean added_flg;
				
	            for (j = 0; j < i && j < oary.length; j++){
					added_flg = false;
	                HashMap hashmap = (HashMap)StructUtilities.toMap(oary[j]);
					String lot_id = (String)hashmap.get("lot_id");
					
					for(int k = 0; k < prelotid.size(); k++){
						String pre_lot_id = (String)prelotid.get(k);
						if(pre_lot_id != null && pre_lot_id.equals(lot_id)){
							added_flg = true;
							break;
						}
					}
					
					if(!added_flg){
						prelotid.add(lot_id);
						String crr_id = (String)hashmap.get(keyElement);
						
		                String logon_date = StringUtil.getCheckedValue((String)hashmap.get("logon_date"), "");
		                String logon_time = StringUtil.getCheckedValue((String)hashmap.get("logon_time"), "");
		                String rep_logon_date = logon_date.replace('-','/');
		                String rep_logon_time = logon_time.replace('.',':');
		                String logon_date_time = rep_logon_date + " " + rep_logon_time;
		                hashmap.put("logon_date_time", logon_date_time);
		                
						hashmap.put("crr_stat", "INPR");
						
						hashmap.put("alrt_flg", "");
						
		                StructElement structDpiqcrrx = structFactory.getStruct(config.getProperty("dpiqcrrx.struct.in"));
		                structDpiqcrrx.setString("crr_id", crr_id);
		                structDpiqcrrx = exchange(config.getProperty("dpiqcrrx.q.in"), structDpiqcrrx, config.getProperty("dpiqcrrx.q.out"), config.getProperty("dpiqcrrx.struct.out"));
						
		                HashMap hashDpiqcrrx = (HashMap)StructUtilities.toMap(structDpiqcrrx);
		                String position = StringUtil.getCheckedValue((String)hashDpiqcrrx.get("position"), "");
		                hashmap.put("position", position);
		                
		                retlist.add(hashmap);
					}
	            }
	        }
        }
        return retlist;
    }

    public boolean isEqpt_common() {
        return eqpt_common;
    }

    public void setStructApieqald(StructElement element) {
        structApieqald = element;
    }

    public void setStructApleqalm(StructElement element) {
        structApleqalm = element;
    }

    public void setStructLotInfo(StructElement element) {
        structLotInfo = element;
    }

    public void setStructApinqeqp(StructElement element) {
        structApinqeqp = element;
    }

    public void setStructDpiqptpd(StructElement element) {
        structDpiqptpd = element;
    }

	public void setStructApiwipeq(StructElement element) {
		structApiwipeq = element;
	}
	
    public ArrayList getChList() {
        if(chList.size() <= 0)	{return null;}
        return chList;
    }

    public ArrayList getEList() {
        if(eList.size() <= 0)	{return null;}
        return eList;
    }

    public ArrayList getSList() {
        if(sList.size() <= 0)	{return null;}
        return sList;
    }

    public ArrayList getPortList() {
        if(portList.size() <= 0)	{return null;}
        return portList;
    }

    public String getRet_alrt_id() {
        return ret_alrt_id;
    }

	public String getRet_rpt_date() {
		return ret_rpt_date;
	}

	public String getRet_rpt_time() {
		return ret_rpt_time;
	}

	public HashMap getEqptMap() {
		return eqptMap;
	}

}
