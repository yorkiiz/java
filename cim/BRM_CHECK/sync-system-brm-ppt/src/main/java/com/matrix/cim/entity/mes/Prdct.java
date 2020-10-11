package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;

@Data
@TableField(brmTableName = "RBPRDCT",pptTableName = "APRDCT")
public class Prdct extends BaseEntity {
    @PKField
    private String product_id;
    private String product_dsc;
    private String product_dsc2;
    private String product_cate;
    private Integer x_axis_cnt;
    private Integer y_axis_cnt;
    private Integer pnl_sht_cnt;
    private String charge_code;
    private String group_pn;
    private String grade_code;
    private String cont_typ;
    private Integer max_recyc_cnt;
    private String layout_id;
    private String ptype;
    private String accept_grade;
    private String customer_id;
    private String veri_code;
    private String internal_code;
    private String addt_info_1;
    private String addt_info_2;
    private String addt_info_3;
    private String mix_layout_flg;
    private Integer yield_threshold1;
    private Integer x2_axis_cnt;
    private Integer y2_axis_cnt;
    private Integer yield_threshold2;
    private Integer crr_empt_no;
}
