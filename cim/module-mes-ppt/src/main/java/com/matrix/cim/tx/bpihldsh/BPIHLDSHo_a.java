package com.matrix.cim.tx.bpihldsh;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BPIHLDSHo_a {

    public String sht_id = "";				//       Sheet ID
    //       In other words, we can call it as following name.
    //       Board ID, Glass ID, Representative Chip ID, Work ID..
    public String crr_id = "";				//       Carrier ID
    public String slot_no = "";				//       Slot No               //A0.00
    public String sht_stat = "";				//       Sheet Status
    public String product_id = "";				//       Product ID
    public String ec_code = "";				//       Engineering Change
    public String route_id = "";				//       Current Route ID
    public String route_ver = "";				//       Current Route Version
    public String ope_no = "";				//       Current Operation No.
    public String ope_id = "";  			//       Current Operation ID
    public String ope_ver = "";  			//       Current Operation Version
    public String ope_dsc="";
    public String eqpt_id = "";				//       Equipment ID
    public String eqpt_dsc = "";				//       Equipment Description (Equipment Name)
    public String sht_cnt="";				//       Sheet Count           //A0.00
    public String pnl_cnt = "";  			//       Panel Count           //A0.00
    public String pnl_sht_cnt = "";  			//       Panel Count per Sheet //A0.00
    public String pep_lvl="";				//       PEP level
    public String lot_id="";    			//       Lot ID
    public String pln_rel_date="";          //       Plan Hold Release Date
    public String hld_cnt="";				//       Hold Count

    //Sub Array References
    public List<BPIHLDSHo_a1> oary1;
}
