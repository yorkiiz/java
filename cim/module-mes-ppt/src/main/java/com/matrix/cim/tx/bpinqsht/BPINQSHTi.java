package com.matrix.cim.tx.bpinqsht;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class BPINQSHTi {
    public String trx_id = "BPINQSHT";
    public String type_id = "I";
    public String c_pnl_flg = "";
    public List<BPINQSHTi_a> iary;

}
