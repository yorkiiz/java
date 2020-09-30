package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;

@Data
@TableField(brmTableName = "RBROUTE",pptTableName = "AROUTE")
public class Route extends BaseEntity {
    @PKField
    private String route_id;
    @PKField
    private String route_ver;
    @PKField
    private String cr_ope_no;
    private String pv_ope_no;
    private String nx_ope_no;
    private String module_id;
    private String module_ver;
    private String cr_ope_id;
    private String cr_ope_ver;
    private String rwk_rst_flg;
    private String rwk_avl_flg;
    private String mproc_flg;
    private String mproc_id;
    private String wip_bank_flg;
    private String def_wip_bank_id;
    private String shp_bank_flg;
    private String stage_id;
    private String read_glass_flg;
}
