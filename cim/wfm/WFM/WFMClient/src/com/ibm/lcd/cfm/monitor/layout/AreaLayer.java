
package com.ibm.lcd.cfm.monitor.layout;

import java.awt.Image;
import java.util.ArrayList;

public class AreaLayer extends BaseLayer {

    public volatile Image drawImage = null;
    private ArrayList equipments = new ArrayList();

    public AreaLayer(String id) {
        super(id);
    }

    public final void addEquipment(EquipmentLayer equipment) {
        equipments.add(equipment);
    }

    public final int getEquipmentCount() {
        return equipments.size();
    }

    public final EquipmentLayer getEquipment(String id) {
        return getEquipment(equipments.indexOf(id));
    }

    public final EquipmentLayer getEquipment(int index) {
        try {
            return (EquipmentLayer)equipments.get(index);
        }
        catch (Exception ex) {}
        return null;
    }

}
