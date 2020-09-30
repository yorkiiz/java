package com.matrix.cim.tx.bpishtem;

import lombok.Data;

import java.util.List;

@Data
public class BPISHTEMo {
    //Transaction Output Variables
    public static final int APISHTEQ_OARY1_SIZE = 3;
    //MDL0153    public static final int APISHTEQ_OARY1 = 300;
    public static final int APISHTEQ_OARY1 = 999;//MDL0153

    public String trx_id = "";				//       Transaction ID
    public String type_id = "";				//       input / output
    //           char         system_byte       [ SYSBYTE_LEN                      ];  //       system byte
    public String rtn_code = "";				//       return code
    public String rtn_mesg = "";				//       return message
    public String eqpt_id = "";				//       Equipment ID
    public String eqpt_dsc = "";				//       Equipment Description (Equipment Name)
    public String eqpt_cate = "";				//       Equipment Category
    //       C:Loader/UnLoader Common
    //       I:Independent
    //       D:Loader/UnLoader Divided
    public String recipe_id = "";				//       Last used recipe id
    public String eqpt_stat = "";				//       Equipment Main Status
    public String eqpt_stat_dsc = "";				//       Equipment Status Description
    public String eqpt_mode = "";				//       Equipment Operation Mode
    public String sgr_cnt = "";				//       Carrier Count
    public String sht_cnt = ""; //sht count
    public String test_flg="";




    //Sub Array References
    public List<BPISHTEMo_a1> oary1;
}
