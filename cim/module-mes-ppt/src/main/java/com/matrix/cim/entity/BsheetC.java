package com.matrix.cim.entity;


public class BsheetC {

  private String shtId;
  private String cShtId;
  private String addtInfo1;
  private String addtInfo2;
  private java.sql.Date evtDate;
  private java.sql.Time evtTime;


  public String getShtId() {
    return shtId;
  }

  public void setShtId(String shtId) {
    this.shtId = shtId;
  }


  public String getCShtId() {
    return cShtId;
  }

  public void setCShtId(String cShtId) {
    this.cShtId = cShtId;
  }


  public String getAddtInfo1() {
    return addtInfo1;
  }

  public void setAddtInfo1(String addtInfo1) {
    this.addtInfo1 = addtInfo1;
  }


  public String getAddtInfo2() {
    return addtInfo2;
  }

  public void setAddtInfo2(String addtInfo2) {
    this.addtInfo2 = addtInfo2;
  }


  public java.sql.Date getEvtDate() {
    return evtDate;
  }

  public void setEvtDate(java.sql.Date evtDate) {
    this.evtDate = evtDate;
  }


  public java.sql.Time getEvtTime() {
    return evtTime;
  }

  public void setEvtTime(java.sql.Time evtTime) {
    this.evtTime = evtTime;
  }

}
