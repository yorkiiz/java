package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;

@Data
@TableField(brmTableName = "RBROUTEI",pptTableName = "AROUTEI")
public class Routei extends BaseEntity {
    @PKField
    private String route_id;
    @PKField
    private String route_ver;

    private String route_dsc;
    private String route_cate;
    private String first_ope_no;
    private String str_bank_id;
    private String end_bank_id;
    private Integer max_rwk_cnt;
    private Integer recyc_cnt_up;
    private String addt_info_1;
    private String addt_info_2;
    private String addt_info_3;
}
