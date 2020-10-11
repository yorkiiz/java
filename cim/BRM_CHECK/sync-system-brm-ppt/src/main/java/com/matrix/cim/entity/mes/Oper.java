package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;

@Data
@TableField(brmTableName = "RBOPER",pptTableName = "AOPER")
public class Oper extends BaseEntity {
    @PKField
    private String ope_id;
    @PKField
    private String ope_ver;

    private String ope_dsc;
    private String proc_id;
    private String eqptg_id;
    private String pep_lvl;
    private String dept_code;
    private Integer std_ld_time;
    private Integer man_ope_time;
    private String up_load_id;
    private String down_load_id;
    private String crr_cln_flg;
    private String recipe_rule_FLG;
    private String rep_unit;
    private String pfc_bank_id;
    private String ld_pfc_bank_ID;
    private String ptt_chk_flg;
    private String test_rep_typ;
    private String pfc_typ;
    private String use_pfc_flg;
    private String qrs_rst_flg;
    private String metal_flg_tyP;
    private String cor_upldid;
    private String cor_dnldid;
    private String del_upldid;
    private String del_dnldid;
    private String addt_info_1;
    private String addt_info_2;
    private String addt_info_3;
}
