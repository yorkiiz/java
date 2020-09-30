
package com.ibm.lcd.cfm.monitor.layout;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;

public class PortLayer extends BaseLayer {
    final String CLASS_NAME = "PortLayer";

    public PortState state = null;

    public PortLayer(PortState state) {
        super(state);
        this.state = state;
        layoutType = LayoutType.PORT;
    }

    public String getReferredValue(String referredName) {

        if (MonitorConfig.PORT_ID.equals(referredName)) {
            return getId();
        }
        else if (MonitorConfig.EQPT_ID.equals(referredName)) {
            if (state != null && state.equipmentState != null)
                return state.equipmentState.getId();
        }
        return super.getReferredValue(referredName);
    }
}
