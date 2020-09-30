package com.matrix.cim.tx.bpihldsh;

import com.matrix.cim.tx.bpinqsht.BPINQSHTi_a;
import lombok.Data;

import java.util.List;

@Data
public class BPIHLDSHi {
    public String trx_id = "BPIHLDSH";				//       Transaction ID
    public String type_id = "I";				//       input / output
    public String bay_id = "";				//       Bay (Module/Sub-Area) Area ID for stat_flg ='2'
    public String eqpt_id = "";				//       Equipment ID
    public String worder_id = "";			//	A0.02 Work Order ID
    public String stat_flg = "";			// noused for TPM//		 1:PROC_INPROCESS_HOLD "ISHD"
    public List<BPINQSHTi_a> iary;
}
