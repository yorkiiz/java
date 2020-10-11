package com.matrix.cim.tx.bpihldsh;

import lombok.Data;

@Data
public class BPIHLDSHo_a1 {
    public String hld_seq_no = "";				//       Hold Sequence Number
    public String hld_cate = "";				//       Hold Category
    public String hld_code = "";				//       Hold Reason Code
    public String hld_user = "";				//       Hold Report User
    public String hld_date = "";				//       Hold reported Date
    public String hld_time = "";				//       Hold reported time
    //    public String comment = "";				// L0.01 Hold comment
    public String sht_ope_msg = "";				//       Sheet Note message   A0.00
}
