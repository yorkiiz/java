package com.matrix.cim.tx.bpinqsht;

import lombok.Data;

import java.util.List;

@Data
public class BPINQSHTo {
    public String trx_id = "BPINQSHT";
    public String type_id = "O";
    public String rtn_code = "";
    public String rtn_mesg = "";
    public List<BPINQSHTo_a> oary;
    public List<BPINQSHTo_a1> oary1;
    public List<BPINQSHTo_a2> oary2;
}
