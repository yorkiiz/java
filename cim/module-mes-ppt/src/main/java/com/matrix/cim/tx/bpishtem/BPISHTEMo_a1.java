package com.matrix.cim.tx.bpishtem;

import lombok.Data;

@Data
public class BPISHTEMo_a1 {

    public String sht_id = "";				//       Sheet ID
    //       In other words, we can call it as following name.
    //       Board ID, Glass ID, Representative Chip ID, Work ID..
    public String sht_stat = "";				//       Sheet   Status
    public String group_id = "";				//A0.02  Grade Label ID
    public String sgr_id = "";				//A0.01  Batch ID
    public String lot_id = "";				//       Lot ID
    public String splt_id = "";				//       Lot Split ID
    public String nx_route_cate = "";				//       Next Route Category
    public String nx_route_id = "";				//       Next Route ID
    public String nx_route_ver = "";				//       Next Route Version
    public String nx_ope_no = "";				//       Next Operation No.
    public String nx_ope_id = "";				//       Next Operation ID
    public String nx_ope_dsc = "";				//       Operation Description
    public String nx_pep_lvl = "";				//       Next PEPlevel
    public String nx_recipe_id = "";				//       Next Recipe ID
    public String nx_proc_id = "";				//       Next Process ID
    public String product_id = "";				//       Product ID
    public String ec_code = "";				    //       Engineering Change
    public String unit = "";				    //A0.03  Unit.
    //       "LOT "
    //       "WAFE"
    public String sht_cnt = "";				    //A0.06  Sheet Count

    public String move_in_flg ="";              //A0.07  Move In Flag
    //       Y:Move In
    //       N:Cannot Move In

    //    public String pack_flg="";               //pack flag:Y->YES N->NO
//    public String mtrl_grade="";              //Material Grade    for Futher hold :glass special mark
//    public String hot_flag="";               //hot flag:Y->YES N->NO
    public String nx_ope_ver = "";             //C0.01    Next Operation Version
    public String worder_id  = "";             //C0.01    Work Order ID
    public String grade = "";				   //C0.02    Grade
    public String ole_pnl_grade="";
    public String pnl_grade="";
    public String main_defect_code = "";	   //CB.10    main_defect_code
    public String sv_crr_id="";
    public String old_grade="";
    public String cf_qrs_date="";
    public String cf_qrs_time="";
    public String cf_stay_days="";
    public String logon_date="";               //MDL2139.8
    public String logon_time="";               //MDL2139.8
    public String c_sht_id= "";
}
