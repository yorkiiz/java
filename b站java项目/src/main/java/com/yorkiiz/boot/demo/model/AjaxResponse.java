package com.yorkiiz.boot.demo.model;

import lombok.Data;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Data
public class AjaxResponse {

    private boolean isok;
    private int code; //200,400,600
    private String message;

    private Object data;

    public AjaxResponse(){

    }

    public static AjaxResponse success(Object obj){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("find succeuss");
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }


}
