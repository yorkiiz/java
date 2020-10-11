
package com.ibm.lcd.cfm.monitor.layout;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentState extends IdentifyObject {
    final String CLASS_NAME = "EquipmentState";

    public String state = null;
    public String subState = null;
    public String mode = null;
    public String alertFlag = null;
    public int alertLevel = 0;
    public String assign = null;
    public String assignLoad = null;
    public String assignUnload = null;
    public int wipCount = 0;
    public int abnormalCount = 0;

    public boolean alertD = false;
	public boolean alertP = false;
	public boolean alertU = false;
	public boolean alertE = false;

	public boolean alertO = false;
    public boolean alertH = false;
    public boolean alertM = false;
    public boolean alertL = false;
    public boolean alertC = false;
    
    public boolean alertN = false; //alrt_flag and alrt_type = "0" means OK
    
    public boolean statDown = false;

    public int currentAlarm = 0;
    public int currentStatus = 0;
  
    public int wipLabelCount    = 0;
    public String inprCount    = "";
    public int shelfLabelCnt    = 0;
    public int chamberCnt       = 0;
    public int chamberMaxCnt    = 0;
    public int bufferStockCnt   = 0;

    public boolean e84Flag = false;

    private ArrayList portsList = new ArrayList();
    private HashMap portsMap = new HashMap();

    public EquipmentState(String id) {
        super(id);
    }

    public final void addPort(PortState port) {
        portsList.add(port);
        portsMap.put(port.getId(), port);
        port.equipmentState = this;
    }

    public final int getPortCount() {
        return portsList.size();
    }

    public final PortState getPort(String id) {
        return (PortState)portsMap.get(id);
    }

    public final PortState getPort(int index) {
    	if(index < 0 || index >= portsList.size())
    		return null;
    	
    	return (PortState)portsList.get(index);
    }
}
