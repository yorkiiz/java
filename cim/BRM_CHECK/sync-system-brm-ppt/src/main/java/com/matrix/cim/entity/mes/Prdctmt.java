package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;

@Data
@TableField(brmTableName = "RBPRDCTMT",pptTableName = "APRDCTMT")
public class Prdctmt extends BaseEntity {
    @PKField
    private String product_id;
    @PKField
    private String mtrl_product_id;
    @PKField
    private String ope_id;
    private String limit_flg;

}
