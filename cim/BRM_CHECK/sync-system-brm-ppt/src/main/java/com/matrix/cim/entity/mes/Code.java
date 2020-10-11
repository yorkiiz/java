package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Data
@Slf4j
@TableField(brmTableName="RBCODE",pptTableName = "ACODE")
public class Code extends BaseEntity {
    @PKField
    private String code_cate;
    @PKField
    private String code;
    @PKField
    private String code_ext;

    private String subitem;
    private String code_dsc;
    private String ext_1;
    private String ext_2;
    private String ext_3;
    private String ext_4;
    private String ext_5;


}
