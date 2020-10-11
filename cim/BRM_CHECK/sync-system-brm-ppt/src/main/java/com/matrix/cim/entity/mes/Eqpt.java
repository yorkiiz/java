package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;

@Data
@TableField(brmTableName = "RBEQPT",pptTableName = "AEQPT")
public class Eqpt extends BaseEntity{
    @PKField
    private String eqpt_id;

    private String eqpt_dsc;
    private String sys_suffix;
    private String unit_typ;
    private String root_eqpt_id;
    private String prt_eqpt_id;
    private String eqpt_typ;
    private String eqpt_subtyp;
    private String eqpt_cate;
    private String kanban;
    private String bay_id;
    private String bcs_node;
    private String tcs_node;
    private Integer max_sht_cnt;
    private Integer max_sht_time;
    private String floor_code;
    private String crr_cln_route_id;
    private String crr_cln_route_ver;
    private String eqpt_trns_cate;
    private String batch_flg;
    private String inl_stc_eqp_flg;
    private String cnct_eqpt_id;
    private String area_code;
    private String line_id;
    private String addt_info_1;
    private String addt_info_2;
    private String addt_info_3;
}
