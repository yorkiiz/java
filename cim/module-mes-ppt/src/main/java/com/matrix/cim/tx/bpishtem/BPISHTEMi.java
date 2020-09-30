package com.matrix.cim.tx.bpishtem;

import lombok.Data;

import java.util.List;

@Data
public class BPISHTEMi {
    public String trx_id = "BPISHTEM";				//       Transaction ID
    public String type_id = "I";				//       input / output
    //           char         system_byte       [ SYSBYTE_LEN                      ];  //       system byte
    public String eqpt_id = "";				//       Equipment ID
    public String sgr_id = "";				//       Sheet Group ID
    public String product_id = "";		    //       Product ID
    public String worder_id = "";           //       work order ID
    public String batch_flg = "";           //      Batch Flg
    public String cut_id = "";              //      CUT ID
    public String grade = "";               //     grade MDL0132
    public String sht_id = "";              //     panel id   MDL0155
    public String asc_flg = "";//MDL0183
    public String test_flg="";
    public String atmi_flg="";
    public String act_flg="";

    List<BPISHTEMi_a> iary;
}
