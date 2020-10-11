package com.ibm.lcd.cfm.monitor.layout;

import java.util.ArrayList;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;

public class EquipmentLayer extends BaseLayer {
    final String CLASS_NAME = "EquipmentLayer";

    public EquipmentState state = null;

    private ArrayList units = new ArrayList();
    private ArrayList ports = new ArrayList();

    public EquipmentLayer(EquipmentState state) {
        super(state);

        this.state = state;
        layoutType = LayoutType.EQUIPMENT;
   }

    public EquipmentLayer(EquipmentState state, String type) {
        super(state);

        this.state = state;
        layoutType = LayoutType.EQUIPMENT;
//        if("AT".equals(type))				/* L1.1 */
//          subLayoutType = LayoutType.AT;		/* L1.1 */
//        else if("PEP".equals(type))			/* L1.1 */
//          subLayoutType = LayoutType.PEP;		/* L1.1 */
//        else if("CLN".equals(type))			/* L1.2 */
//          subLayoutType = LayoutType.CLN;		/* L1.2 */
    }


    public String getReferredValue(String referredName) {
        if (MonitorConfig.EQPT_ID.equals(referredName))
            return getId();
        return super.getReferredValue(referredName);
    }

    public final void addPort(PortLayer port) {
        ports.add(port);
    }

    public final int getPortCount() {
        return ports.size();
    }

    public final PortLayer getPort(int index) {
        try {
            return (PortLayer)ports.get(index);
        }
        catch (Exception ex) {}
        return null;
    }

    public final void addUnit(UnitLayer unit) {
        units.add(unit);
    }

    public final int getUnitCount() {
        return units.size();
    }

    public final UnitLayer getUnit(int index) {
        try {
            return (UnitLayer)units.get(index);
        }
        catch (Exception ex) {}
        return null;
    }

}
