package com.yorkiiz.yysdata.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Data
public class YysData {

    private Timestamp date;
    private int number;
    private String gold;
    private String tili;
    private String blue_ticket;
    private String gouyu;
    private String shepi;
    private String goldenshepi;
    private String comment;


}
