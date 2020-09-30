package com.matrix.cim.tx.bpihldsh;

import lombok.Data;

import java.util.List;

@Data
public class BPIHLDSHo {

    public String trx_id = "";				//       Transaction ID
    public String type_id = "";				//       input / output
    public String rtn_code = "";				//       return code
    public String rtn_mesg = "";				//       return message
    public String sht_cnt = "";				//       Carrier Count

    //Sub Array References
    public List<BPIHLDSHo_a> oary;
}
