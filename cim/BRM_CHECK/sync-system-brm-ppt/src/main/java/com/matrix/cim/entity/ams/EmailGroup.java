package com.matrix.cim.entity.ams;

import lombok.Data;

@Data
public class EmailGroup {
    private String group_name;
    private String recipient_type;
    private String recipient_name;
    private String recipient;
    private String error_type;
}
