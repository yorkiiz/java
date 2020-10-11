package com.matrix.cim.entity;


public class Bsheet {

  private String shtId;
  private String sgrId;
  private String lotId;
  private String crrId;
  private String slotNo;
  private String mGroupId;
  private String groupId;
  private String ppboxId;
  private String shtStat;
  private long shtCnt;
  private long pnlCnt;
  private long xAxisCnt;
  private long yAxisCnt;
  private long pnlShtCnt;
  private String reprocFlg;
  private String eqptId;
  private String eqptPortId;
  private String crRouteId;
  private String crRouteVer;
  private String crOpeNo;
  private String crOpeId;
  private String crOpeVer;
  private String crProcId;
  private String crEqptgId;
  private String crEqptId;
  private String crPepLvl;
  private String crRecipeId;
  private String nxRouteId;
  private String nxRouteVer;
  private String nxOpeNo;
  private String nxOpeId;
  private String nxOpeVer;
  private String nxProcId;
  private String nxEqptgId;
  private String nxEqptId;
  private String nxPepLvl;
  private String nxDeptCode;
  private String nxRecipeId;
  private String pvLogofUser;
  private java.sql.Date pvLogofDate;
  private java.sql.Time pvLogofTime;
  private java.sql.Date pvLogofMfdt;
  private String pvLogofMfwk;
  private String pvLogofMfmn;
  private long pvLogofMfsh;
  private String logonUser;
  private String logonCrrId;
  private String logonSlotNo;
  private java.sql.Date logonDate;
  private java.sql.Time logonTime;
  private java.sql.Date logonMfdt;
  private String logonMfwk;
  private String logonMfmn;
  private long logonMfsh;
  private String logofUser;
  private java.sql.Date logofDate;
  private java.sql.Time logofTime;
  private java.sql.Date logofMfdt;
  private String logofMfwk;
  private String logofMfmn;
  private long logofMfsh;
  private java.sql.Date pvPlnCmpDate;
  private java.sql.Time pvPlnCmpTime;
  private java.sql.Date pvPlnCmpMfdt;
  private String pvPlnCmpMfwk;
  private String pvPlnCmpMfmn;
  private long pvPlnCmpMfsh;
  private java.sql.Date plnCmpDate;
  private java.sql.Time plnCmpTime;
  private java.sql.Date plnCmpMfdt;
  private String plnCmpMfwk;
  private String plnCmpMfmn;
  private long plnCmpMfsh;
  private java.sql.Date pcmpDate;
  private java.sql.Time pcmpTime;
  private java.sql.Date pcmpMfdt;
  private String pcmpMfwk;
  private String pcmpMfmn;
  private long pcmpMfsh;
  private java.sql.Date acmpDate;
  private java.sql.Time acmpTime;
  private java.sql.Date acmpMfdt;
  private String acmpMfwk;
  private String acmpMfmn;
  private long acmpMfsh;
  private String evtCate;
  private String evtUser;
  private java.sql.Date evtDate;
  private java.sql.Time evtTime;
  private java.sql.Date evtMfdt;
  private String evtMfwk;
  private String evtMfmn;
  private long evtMfsh;
  private String crMprocId;
  private String crMprocFlg;
  private String nxMprocId;
  private String nxMprocFlg;
  private long mprocHld;
  private java.sql.Date plnMprocDate;
  private java.sql.Time plnMprocTime;
  private java.sql.Date plnMprocMfdt;
  private String plnMprocMfwk;
  private String plnMprocMfmn;
  private long plnMprocMfsh;
  private java.sql.Date actMprocDate;
  private java.sql.Time actMprocTime;
  private java.sql.Date actMprocMfdt;
  private String actMprocMfwk;
  private String actMprocMfmn;
  private long actMprocMfsh;
  private String svCrrId;
  private String svSlotNo;
  private String svRouteId;
  private String svRouteVer;
  private String svOpeNo;
  private String svEqptgId;
  private String svEqptId;
  private String svRecipeId;
  private String svShtStat;
  private String pvCrrId;
  private String pvSlotNo;
  private String clmCrrId;
  private String clmSlotNo;
  private String abndFlg;
  private String deldays;
  private String shtNoteFlg;
  private String fabId;
  private String lineId;
  private String productId;
  private String ecCode;
  private String chargeCode;
  private String ownerId;
  private String prty;
  private String mtrlProductId;
  private String orderId;
  private String salesOrder;
  private String projectCd;
  private String engName;
  private String expNo;
  private String stbLotId;
  private String dsRecipeId;
  private String acRecipeId;
  private String productCate;
  private java.sql.Date actStbDate;
  private java.sql.Time actStbTime;
  private String prtSpltId;
  private String spltId;
  private String ceId;
  private String eenId;
  private String stageId;
  private String svReprocFlg;
  private String shtRecipeId;
  private String svShtRecipeId;
  private String destShop;
  private String mtrlGrade;
  private String shtJudge;
  private String stbShop;
  private String abnormalFlg1;
  private String orgLotId;
  private String orgPrtSpltId;
  private String orgSpltId;
  private String pvProcId;
  private String pvEqptId;
  private String pvPepLvl;
  private java.sql.Date cmpDueDate;
  private String lgScrpFlg;
  private String stbSamplingFlg;
  private String saleFlg;
  private String mixOwner;
  private String svPrty;
  private String worderId;
  private String unit;
  private String shtIdBc;
  private long fqcFlg;
  private String rcvFlg;
  private String svProductId;
  private String svMtrlProductId;
  private String worderType;
  private String grade;
  private String boxId;
  private String palletId;
  private String shipNo;
  private String serialNo;
  private String serialNoCust;
  private String addtInfo1;
  private String addtInfo2;
  private String addtInfo3;
  private String addtInfo4;
  private String addtInfo5;
  private String addtInfo6;
  private String addtInfo7;
  private String addtInfo8;
  private String addtInfo9;
  private String addtInfo10;


  public String getShtId() {
    return shtId;
  }

  public void setShtId(String shtId) {
    this.shtId = shtId;
  }


  public String getSgrId() {
    return sgrId;
  }

  public void setSgrId(String sgrId) {
    this.sgrId = sgrId;
  }


  public String getLotId() {
    return lotId;
  }

  public void setLotId(String lotId) {
    this.lotId = lotId;
  }


  public String getCrrId() {
    return crrId;
  }

  public void setCrrId(String crrId) {
    this.crrId = crrId;
  }


  public String getSlotNo() {
    return slotNo;
  }

  public void setSlotNo(String slotNo) {
    this.slotNo = slotNo;
  }


  public String getMGroupId() {
    return mGroupId;
  }

  public void setMGroupId(String mGroupId) {
    this.mGroupId = mGroupId;
  }


  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }


  public String getPpboxId() {
    return ppboxId;
  }

  public void setPpboxId(String ppboxId) {
    this.ppboxId = ppboxId;
  }


  public String getShtStat() {
    return shtStat;
  }

  public void setShtStat(String shtStat) {
    this.shtStat = shtStat;
  }


  public long getShtCnt() {
    return shtCnt;
  }

  public void setShtCnt(long shtCnt) {
    this.shtCnt = shtCnt;
  }


  public long getPnlCnt() {
    return pnlCnt;
  }

  public void setPnlCnt(long pnlCnt) {
    this.pnlCnt = pnlCnt;
  }


  public long getXAxisCnt() {
    return xAxisCnt;
  }

  public void setXAxisCnt(long xAxisCnt) {
    this.xAxisCnt = xAxisCnt;
  }


  public long getYAxisCnt() {
    return yAxisCnt;
  }

  public void setYAxisCnt(long yAxisCnt) {
    this.yAxisCnt = yAxisCnt;
  }


  public long getPnlShtCnt() {
    return pnlShtCnt;
  }

  public void setPnlShtCnt(long pnlShtCnt) {
    this.pnlShtCnt = pnlShtCnt;
  }


  public String getReprocFlg() {
    return reprocFlg;
  }

  public void setReprocFlg(String reprocFlg) {
    this.reprocFlg = reprocFlg;
  }


  public String getEqptId() {
    return eqptId;
  }

  public void setEqptId(String eqptId) {
    this.eqptId = eqptId;
  }


  public String getEqptPortId() {
    return eqptPortId;
  }

  public void setEqptPortId(String eqptPortId) {
    this.eqptPortId = eqptPortId;
  }


  public String getCrRouteId() {
    return crRouteId;
  }

  public void setCrRouteId(String crRouteId) {
    this.crRouteId = crRouteId;
  }


  public String getCrRouteVer() {
    return crRouteVer;
  }

  public void setCrRouteVer(String crRouteVer) {
    this.crRouteVer = crRouteVer;
  }


  public String getCrOpeNo() {
    return crOpeNo;
  }

  public void setCrOpeNo(String crOpeNo) {
    this.crOpeNo = crOpeNo;
  }


  public String getCrOpeId() {
    return crOpeId;
  }

  public void setCrOpeId(String crOpeId) {
    this.crOpeId = crOpeId;
  }


  public String getCrOpeVer() {
    return crOpeVer;
  }

  public void setCrOpeVer(String crOpeVer) {
    this.crOpeVer = crOpeVer;
  }


  public String getCrProcId() {
    return crProcId;
  }

  public void setCrProcId(String crProcId) {
    this.crProcId = crProcId;
  }


  public String getCrEqptgId() {
    return crEqptgId;
  }

  public void setCrEqptgId(String crEqptgId) {
    this.crEqptgId = crEqptgId;
  }


  public String getCrEqptId() {
    return crEqptId;
  }

  public void setCrEqptId(String crEqptId) {
    this.crEqptId = crEqptId;
  }


  public String getCrPepLvl() {
    return crPepLvl;
  }

  public void setCrPepLvl(String crPepLvl) {
    this.crPepLvl = crPepLvl;
  }


  public String getCrRecipeId() {
    return crRecipeId;
  }

  public void setCrRecipeId(String crRecipeId) {
    this.crRecipeId = crRecipeId;
  }


  public String getNxRouteId() {
    return nxRouteId;
  }

  public void setNxRouteId(String nxRouteId) {
    this.nxRouteId = nxRouteId;
  }


  public String getNxRouteVer() {
    return nxRouteVer;
  }

  public void setNxRouteVer(String nxRouteVer) {
    this.nxRouteVer = nxRouteVer;
  }


  public String getNxOpeNo() {
    return nxOpeNo;
  }

  public void setNxOpeNo(String nxOpeNo) {
    this.nxOpeNo = nxOpeNo;
  }


  public String getNxOpeId() {
    return nxOpeId;
  }

  public void setNxOpeId(String nxOpeId) {
    this.nxOpeId = nxOpeId;
  }


  public String getNxOpeVer() {
    return nxOpeVer;
  }

  public void setNxOpeVer(String nxOpeVer) {
    this.nxOpeVer = nxOpeVer;
  }


  public String getNxProcId() {
    return nxProcId;
  }

  public void setNxProcId(String nxProcId) {
    this.nxProcId = nxProcId;
  }


  public String getNxEqptgId() {
    return nxEqptgId;
  }

  public void setNxEqptgId(String nxEqptgId) {
    this.nxEqptgId = nxEqptgId;
  }


  public String getNxEqptId() {
    return nxEqptId;
  }

  public void setNxEqptId(String nxEqptId) {
    this.nxEqptId = nxEqptId;
  }


  public String getNxPepLvl() {
    return nxPepLvl;
  }

  public void setNxPepLvl(String nxPepLvl) {
    this.nxPepLvl = nxPepLvl;
  }


  public String getNxDeptCode() {
    return nxDeptCode;
  }

  public void setNxDeptCode(String nxDeptCode) {
    this.nxDeptCode = nxDeptCode;
  }


  public String getNxRecipeId() {
    return nxRecipeId;
  }

  public void setNxRecipeId(String nxRecipeId) {
    this.nxRecipeId = nxRecipeId;
  }


  public String getPvLogofUser() {
    return pvLogofUser;
  }

  public void setPvLogofUser(String pvLogofUser) {
    this.pvLogofUser = pvLogofUser;
  }


  public java.sql.Date getPvLogofDate() {
    return pvLogofDate;
  }

  public void setPvLogofDate(java.sql.Date pvLogofDate) {
    this.pvLogofDate = pvLogofDate;
  }


  public java.sql.Time getPvLogofTime() {
    return pvLogofTime;
  }

  public void setPvLogofTime(java.sql.Time pvLogofTime) {
    this.pvLogofTime = pvLogofTime;
  }


  public java.sql.Date getPvLogofMfdt() {
    return pvLogofMfdt;
  }

  public void setPvLogofMfdt(java.sql.Date pvLogofMfdt) {
    this.pvLogofMfdt = pvLogofMfdt;
  }


  public String getPvLogofMfwk() {
    return pvLogofMfwk;
  }

  public void setPvLogofMfwk(String pvLogofMfwk) {
    this.pvLogofMfwk = pvLogofMfwk;
  }


  public String getPvLogofMfmn() {
    return pvLogofMfmn;
  }

  public void setPvLogofMfmn(String pvLogofMfmn) {
    this.pvLogofMfmn = pvLogofMfmn;
  }


  public long getPvLogofMfsh() {
    return pvLogofMfsh;
  }

  public void setPvLogofMfsh(long pvLogofMfsh) {
    this.pvLogofMfsh = pvLogofMfsh;
  }


  public String getLogonUser() {
    return logonUser;
  }

  public void setLogonUser(String logonUser) {
    this.logonUser = logonUser;
  }


  public String getLogonCrrId() {
    return logonCrrId;
  }

  public void setLogonCrrId(String logonCrrId) {
    this.logonCrrId = logonCrrId;
  }


  public String getLogonSlotNo() {
    return logonSlotNo;
  }

  public void setLogonSlotNo(String logonSlotNo) {
    this.logonSlotNo = logonSlotNo;
  }


  public java.sql.Date getLogonDate() {
    return logonDate;
  }

  public void setLogonDate(java.sql.Date logonDate) {
    this.logonDate = logonDate;
  }


  public java.sql.Time getLogonTime() {
    return logonTime;
  }

  public void setLogonTime(java.sql.Time logonTime) {
    this.logonTime = logonTime;
  }


  public java.sql.Date getLogonMfdt() {
    return logonMfdt;
  }

  public void setLogonMfdt(java.sql.Date logonMfdt) {
    this.logonMfdt = logonMfdt;
  }


  public String getLogonMfwk() {
    return logonMfwk;
  }

  public void setLogonMfwk(String logonMfwk) {
    this.logonMfwk = logonMfwk;
  }


  public String getLogonMfmn() {
    return logonMfmn;
  }

  public void setLogonMfmn(String logonMfmn) {
    this.logonMfmn = logonMfmn;
  }


  public long getLogonMfsh() {
    return logonMfsh;
  }

  public void setLogonMfsh(long logonMfsh) {
    this.logonMfsh = logonMfsh;
  }


  public String getLogofUser() {
    return logofUser;
  }

  public void setLogofUser(String logofUser) {
    this.logofUser = logofUser;
  }


  public java.sql.Date getLogofDate() {
    return logofDate;
  }

  public void setLogofDate(java.sql.Date logofDate) {
    this.logofDate = logofDate;
  }


  public java.sql.Time getLogofTime() {
    return logofTime;
  }

  public void setLogofTime(java.sql.Time logofTime) {
    this.logofTime = logofTime;
  }


  public java.sql.Date getLogofMfdt() {
    return logofMfdt;
  }

  public void setLogofMfdt(java.sql.Date logofMfdt) {
    this.logofMfdt = logofMfdt;
  }


  public String getLogofMfwk() {
    return logofMfwk;
  }

  public void setLogofMfwk(String logofMfwk) {
    this.logofMfwk = logofMfwk;
  }


  public String getLogofMfmn() {
    return logofMfmn;
  }

  public void setLogofMfmn(String logofMfmn) {
    this.logofMfmn = logofMfmn;
  }


  public long getLogofMfsh() {
    return logofMfsh;
  }

  public void setLogofMfsh(long logofMfsh) {
    this.logofMfsh = logofMfsh;
  }


  public java.sql.Date getPvPlnCmpDate() {
    return pvPlnCmpDate;
  }

  public void setPvPlnCmpDate(java.sql.Date pvPlnCmpDate) {
    this.pvPlnCmpDate = pvPlnCmpDate;
  }


  public java.sql.Time getPvPlnCmpTime() {
    return pvPlnCmpTime;
  }

  public void setPvPlnCmpTime(java.sql.Time pvPlnCmpTime) {
    this.pvPlnCmpTime = pvPlnCmpTime;
  }


  public java.sql.Date getPvPlnCmpMfdt() {
    return pvPlnCmpMfdt;
  }

  public void setPvPlnCmpMfdt(java.sql.Date pvPlnCmpMfdt) {
    this.pvPlnCmpMfdt = pvPlnCmpMfdt;
  }


  public String getPvPlnCmpMfwk() {
    return pvPlnCmpMfwk;
  }

  public void setPvPlnCmpMfwk(String pvPlnCmpMfwk) {
    this.pvPlnCmpMfwk = pvPlnCmpMfwk;
  }


  public String getPvPlnCmpMfmn() {
    return pvPlnCmpMfmn;
  }

  public void setPvPlnCmpMfmn(String pvPlnCmpMfmn) {
    this.pvPlnCmpMfmn = pvPlnCmpMfmn;
  }


  public long getPvPlnCmpMfsh() {
    return pvPlnCmpMfsh;
  }

  public void setPvPlnCmpMfsh(long pvPlnCmpMfsh) {
    this.pvPlnCmpMfsh = pvPlnCmpMfsh;
  }


  public java.sql.Date getPlnCmpDate() {
    return plnCmpDate;
  }

  public void setPlnCmpDate(java.sql.Date plnCmpDate) {
    this.plnCmpDate = plnCmpDate;
  }


  public java.sql.Time getPlnCmpTime() {
    return plnCmpTime;
  }

  public void setPlnCmpTime(java.sql.Time plnCmpTime) {
    this.plnCmpTime = plnCmpTime;
  }


  public java.sql.Date getPlnCmpMfdt() {
    return plnCmpMfdt;
  }

  public void setPlnCmpMfdt(java.sql.Date plnCmpMfdt) {
    this.plnCmpMfdt = plnCmpMfdt;
  }


  public String getPlnCmpMfwk() {
    return plnCmpMfwk;
  }

  public void setPlnCmpMfwk(String plnCmpMfwk) {
    this.plnCmpMfwk = plnCmpMfwk;
  }


  public String getPlnCmpMfmn() {
    return plnCmpMfmn;
  }

  public void setPlnCmpMfmn(String plnCmpMfmn) {
    this.plnCmpMfmn = plnCmpMfmn;
  }


  public long getPlnCmpMfsh() {
    return plnCmpMfsh;
  }

  public void setPlnCmpMfsh(long plnCmpMfsh) {
    this.plnCmpMfsh = plnCmpMfsh;
  }


  public java.sql.Date getPcmpDate() {
    return pcmpDate;
  }

  public void setPcmpDate(java.sql.Date pcmpDate) {
    this.pcmpDate = pcmpDate;
  }


  public java.sql.Time getPcmpTime() {
    return pcmpTime;
  }

  public void setPcmpTime(java.sql.Time pcmpTime) {
    this.pcmpTime = pcmpTime;
  }


  public java.sql.Date getPcmpMfdt() {
    return pcmpMfdt;
  }

  public void setPcmpMfdt(java.sql.Date pcmpMfdt) {
    this.pcmpMfdt = pcmpMfdt;
  }


  public String getPcmpMfwk() {
    return pcmpMfwk;
  }

  public void setPcmpMfwk(String pcmpMfwk) {
    this.pcmpMfwk = pcmpMfwk;
  }


  public String getPcmpMfmn() {
    return pcmpMfmn;
  }

  public void setPcmpMfmn(String pcmpMfmn) {
    this.pcmpMfmn = pcmpMfmn;
  }


  public long getPcmpMfsh() {
    return pcmpMfsh;
  }

  public void setPcmpMfsh(long pcmpMfsh) {
    this.pcmpMfsh = pcmpMfsh;
  }


  public java.sql.Date getAcmpDate() {
    return acmpDate;
  }

  public void setAcmpDate(java.sql.Date acmpDate) {
    this.acmpDate = acmpDate;
  }


  public java.sql.Time getAcmpTime() {
    return acmpTime;
  }

  public void setAcmpTime(java.sql.Time acmpTime) {
    this.acmpTime = acmpTime;
  }


  public java.sql.Date getAcmpMfdt() {
    return acmpMfdt;
  }

  public void setAcmpMfdt(java.sql.Date acmpMfdt) {
    this.acmpMfdt = acmpMfdt;
  }


  public String getAcmpMfwk() {
    return acmpMfwk;
  }

  public void setAcmpMfwk(String acmpMfwk) {
    this.acmpMfwk = acmpMfwk;
  }


  public String getAcmpMfmn() {
    return acmpMfmn;
  }

  public void setAcmpMfmn(String acmpMfmn) {
    this.acmpMfmn = acmpMfmn;
  }


  public long getAcmpMfsh() {
    return acmpMfsh;
  }

  public void setAcmpMfsh(long acmpMfsh) {
    this.acmpMfsh = acmpMfsh;
  }


  public String getEvtCate() {
    return evtCate;
  }

  public void setEvtCate(String evtCate) {
    this.evtCate = evtCate;
  }


  public String getEvtUser() {
    return evtUser;
  }

  public void setEvtUser(String evtUser) {
    this.evtUser = evtUser;
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


  public java.sql.Date getEvtMfdt() {
    return evtMfdt;
  }

  public void setEvtMfdt(java.sql.Date evtMfdt) {
    this.evtMfdt = evtMfdt;
  }


  public String getEvtMfwk() {
    return evtMfwk;
  }

  public void setEvtMfwk(String evtMfwk) {
    this.evtMfwk = evtMfwk;
  }


  public String getEvtMfmn() {
    return evtMfmn;
  }

  public void setEvtMfmn(String evtMfmn) {
    this.evtMfmn = evtMfmn;
  }


  public long getEvtMfsh() {
    return evtMfsh;
  }

  public void setEvtMfsh(long evtMfsh) {
    this.evtMfsh = evtMfsh;
  }


  public String getCrMprocId() {
    return crMprocId;
  }

  public void setCrMprocId(String crMprocId) {
    this.crMprocId = crMprocId;
  }


  public String getCrMprocFlg() {
    return crMprocFlg;
  }

  public void setCrMprocFlg(String crMprocFlg) {
    this.crMprocFlg = crMprocFlg;
  }


  public String getNxMprocId() {
    return nxMprocId;
  }

  public void setNxMprocId(String nxMprocId) {
    this.nxMprocId = nxMprocId;
  }


  public String getNxMprocFlg() {
    return nxMprocFlg;
  }

  public void setNxMprocFlg(String nxMprocFlg) {
    this.nxMprocFlg = nxMprocFlg;
  }


  public long getMprocHld() {
    return mprocHld;
  }

  public void setMprocHld(long mprocHld) {
    this.mprocHld = mprocHld;
  }


  public java.sql.Date getPlnMprocDate() {
    return plnMprocDate;
  }

  public void setPlnMprocDate(java.sql.Date plnMprocDate) {
    this.plnMprocDate = plnMprocDate;
  }


  public java.sql.Time getPlnMprocTime() {
    return plnMprocTime;
  }

  public void setPlnMprocTime(java.sql.Time plnMprocTime) {
    this.plnMprocTime = plnMprocTime;
  }


  public java.sql.Date getPlnMprocMfdt() {
    return plnMprocMfdt;
  }

  public void setPlnMprocMfdt(java.sql.Date plnMprocMfdt) {
    this.plnMprocMfdt = plnMprocMfdt;
  }


  public String getPlnMprocMfwk() {
    return plnMprocMfwk;
  }

  public void setPlnMprocMfwk(String plnMprocMfwk) {
    this.plnMprocMfwk = plnMprocMfwk;
  }


  public String getPlnMprocMfmn() {
    return plnMprocMfmn;
  }

  public void setPlnMprocMfmn(String plnMprocMfmn) {
    this.plnMprocMfmn = plnMprocMfmn;
  }


  public long getPlnMprocMfsh() {
    return plnMprocMfsh;
  }

  public void setPlnMprocMfsh(long plnMprocMfsh) {
    this.plnMprocMfsh = plnMprocMfsh;
  }


  public java.sql.Date getActMprocDate() {
    return actMprocDate;
  }

  public void setActMprocDate(java.sql.Date actMprocDate) {
    this.actMprocDate = actMprocDate;
  }


  public java.sql.Time getActMprocTime() {
    return actMprocTime;
  }

  public void setActMprocTime(java.sql.Time actMprocTime) {
    this.actMprocTime = actMprocTime;
  }


  public java.sql.Date getActMprocMfdt() {
    return actMprocMfdt;
  }

  public void setActMprocMfdt(java.sql.Date actMprocMfdt) {
    this.actMprocMfdt = actMprocMfdt;
  }


  public String getActMprocMfwk() {
    return actMprocMfwk;
  }

  public void setActMprocMfwk(String actMprocMfwk) {
    this.actMprocMfwk = actMprocMfwk;
  }


  public String getActMprocMfmn() {
    return actMprocMfmn;
  }

  public void setActMprocMfmn(String actMprocMfmn) {
    this.actMprocMfmn = actMprocMfmn;
  }


  public long getActMprocMfsh() {
    return actMprocMfsh;
  }

  public void setActMprocMfsh(long actMprocMfsh) {
    this.actMprocMfsh = actMprocMfsh;
  }


  public String getSvCrrId() {
    return svCrrId;
  }

  public void setSvCrrId(String svCrrId) {
    this.svCrrId = svCrrId;
  }


  public String getSvSlotNo() {
    return svSlotNo;
  }

  public void setSvSlotNo(String svSlotNo) {
    this.svSlotNo = svSlotNo;
  }


  public String getSvRouteId() {
    return svRouteId;
  }

  public void setSvRouteId(String svRouteId) {
    this.svRouteId = svRouteId;
  }


  public String getSvRouteVer() {
    return svRouteVer;
  }

  public void setSvRouteVer(String svRouteVer) {
    this.svRouteVer = svRouteVer;
  }


  public String getSvOpeNo() {
    return svOpeNo;
  }

  public void setSvOpeNo(String svOpeNo) {
    this.svOpeNo = svOpeNo;
  }


  public String getSvEqptgId() {
    return svEqptgId;
  }

  public void setSvEqptgId(String svEqptgId) {
    this.svEqptgId = svEqptgId;
  }


  public String getSvEqptId() {
    return svEqptId;
  }

  public void setSvEqptId(String svEqptId) {
    this.svEqptId = svEqptId;
  }


  public String getSvRecipeId() {
    return svRecipeId;
  }

  public void setSvRecipeId(String svRecipeId) {
    this.svRecipeId = svRecipeId;
  }


  public String getSvShtStat() {
    return svShtStat;
  }

  public void setSvShtStat(String svShtStat) {
    this.svShtStat = svShtStat;
  }


  public String getPvCrrId() {
    return pvCrrId;
  }

  public void setPvCrrId(String pvCrrId) {
    this.pvCrrId = pvCrrId;
  }


  public String getPvSlotNo() {
    return pvSlotNo;
  }

  public void setPvSlotNo(String pvSlotNo) {
    this.pvSlotNo = pvSlotNo;
  }


  public String getClmCrrId() {
    return clmCrrId;
  }

  public void setClmCrrId(String clmCrrId) {
    this.clmCrrId = clmCrrId;
  }


  public String getClmSlotNo() {
    return clmSlotNo;
  }

  public void setClmSlotNo(String clmSlotNo) {
    this.clmSlotNo = clmSlotNo;
  }


  public String getAbndFlg() {
    return abndFlg;
  }

  public void setAbndFlg(String abndFlg) {
    this.abndFlg = abndFlg;
  }


  public String getDeldays() {
    return deldays;
  }

  public void setDeldays(String deldays) {
    this.deldays = deldays;
  }


  public String getShtNoteFlg() {
    return shtNoteFlg;
  }

  public void setShtNoteFlg(String shtNoteFlg) {
    this.shtNoteFlg = shtNoteFlg;
  }


  public String getFabId() {
    return fabId;
  }

  public void setFabId(String fabId) {
    this.fabId = fabId;
  }


  public String getLineId() {
    return lineId;
  }

  public void setLineId(String lineId) {
    this.lineId = lineId;
  }


  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }


  public String getEcCode() {
    return ecCode;
  }

  public void setEcCode(String ecCode) {
    this.ecCode = ecCode;
  }


  public String getChargeCode() {
    return chargeCode;
  }

  public void setChargeCode(String chargeCode) {
    this.chargeCode = chargeCode;
  }


  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }


  public String getPrty() {
    return prty;
  }

  public void setPrty(String prty) {
    this.prty = prty;
  }


  public String getMtrlProductId() {
    return mtrlProductId;
  }

  public void setMtrlProductId(String mtrlProductId) {
    this.mtrlProductId = mtrlProductId;
  }


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getSalesOrder() {
    return salesOrder;
  }

  public void setSalesOrder(String salesOrder) {
    this.salesOrder = salesOrder;
  }


  public String getProjectCd() {
    return projectCd;
  }

  public void setProjectCd(String projectCd) {
    this.projectCd = projectCd;
  }


  public String getEngName() {
    return engName;
  }

  public void setEngName(String engName) {
    this.engName = engName;
  }


  public String getExpNo() {
    return expNo;
  }

  public void setExpNo(String expNo) {
    this.expNo = expNo;
  }


  public String getStbLotId() {
    return stbLotId;
  }

  public void setStbLotId(String stbLotId) {
    this.stbLotId = stbLotId;
  }


  public String getDsRecipeId() {
    return dsRecipeId;
  }

  public void setDsRecipeId(String dsRecipeId) {
    this.dsRecipeId = dsRecipeId;
  }


  public String getAcRecipeId() {
    return acRecipeId;
  }

  public void setAcRecipeId(String acRecipeId) {
    this.acRecipeId = acRecipeId;
  }


  public String getProductCate() {
    return productCate;
  }

  public void setProductCate(String productCate) {
    this.productCate = productCate;
  }


  public java.sql.Date getActStbDate() {
    return actStbDate;
  }

  public void setActStbDate(java.sql.Date actStbDate) {
    this.actStbDate = actStbDate;
  }


  public java.sql.Time getActStbTime() {
    return actStbTime;
  }

  public void setActStbTime(java.sql.Time actStbTime) {
    this.actStbTime = actStbTime;
  }


  public String getPrtSpltId() {
    return prtSpltId;
  }

  public void setPrtSpltId(String prtSpltId) {
    this.prtSpltId = prtSpltId;
  }


  public String getSpltId() {
    return spltId;
  }

  public void setSpltId(String spltId) {
    this.spltId = spltId;
  }


  public String getCeId() {
    return ceId;
  }

  public void setCeId(String ceId) {
    this.ceId = ceId;
  }


  public String getEenId() {
    return eenId;
  }

  public void setEenId(String eenId) {
    this.eenId = eenId;
  }


  public String getStageId() {
    return stageId;
  }

  public void setStageId(String stageId) {
    this.stageId = stageId;
  }


  public String getSvReprocFlg() {
    return svReprocFlg;
  }

  public void setSvReprocFlg(String svReprocFlg) {
    this.svReprocFlg = svReprocFlg;
  }


  public String getShtRecipeId() {
    return shtRecipeId;
  }

  public void setShtRecipeId(String shtRecipeId) {
    this.shtRecipeId = shtRecipeId;
  }


  public String getSvShtRecipeId() {
    return svShtRecipeId;
  }

  public void setSvShtRecipeId(String svShtRecipeId) {
    this.svShtRecipeId = svShtRecipeId;
  }


  public String getDestShop() {
    return destShop;
  }

  public void setDestShop(String destShop) {
    this.destShop = destShop;
  }


  public String getMtrlGrade() {
    return mtrlGrade;
  }

  public void setMtrlGrade(String mtrlGrade) {
    this.mtrlGrade = mtrlGrade;
  }


  public String getShtJudge() {
    return shtJudge;
  }

  public void setShtJudge(String shtJudge) {
    this.shtJudge = shtJudge;
  }


  public String getStbShop() {
    return stbShop;
  }

  public void setStbShop(String stbShop) {
    this.stbShop = stbShop;
  }


  public String getAbnormalFlg1() {
    return abnormalFlg1;
  }

  public void setAbnormalFlg1(String abnormalFlg1) {
    this.abnormalFlg1 = abnormalFlg1;
  }


  public String getOrgLotId() {
    return orgLotId;
  }

  public void setOrgLotId(String orgLotId) {
    this.orgLotId = orgLotId;
  }


  public String getOrgPrtSpltId() {
    return orgPrtSpltId;
  }

  public void setOrgPrtSpltId(String orgPrtSpltId) {
    this.orgPrtSpltId = orgPrtSpltId;
  }


  public String getOrgSpltId() {
    return orgSpltId;
  }

  public void setOrgSpltId(String orgSpltId) {
    this.orgSpltId = orgSpltId;
  }


  public String getPvProcId() {
    return pvProcId;
  }

  public void setPvProcId(String pvProcId) {
    this.pvProcId = pvProcId;
  }


  public String getPvEqptId() {
    return pvEqptId;
  }

  public void setPvEqptId(String pvEqptId) {
    this.pvEqptId = pvEqptId;
  }


  public String getPvPepLvl() {
    return pvPepLvl;
  }

  public void setPvPepLvl(String pvPepLvl) {
    this.pvPepLvl = pvPepLvl;
  }


  public java.sql.Date getCmpDueDate() {
    return cmpDueDate;
  }

  public void setCmpDueDate(java.sql.Date cmpDueDate) {
    this.cmpDueDate = cmpDueDate;
  }


  public String getLgScrpFlg() {
    return lgScrpFlg;
  }

  public void setLgScrpFlg(String lgScrpFlg) {
    this.lgScrpFlg = lgScrpFlg;
  }


  public String getStbSamplingFlg() {
    return stbSamplingFlg;
  }

  public void setStbSamplingFlg(String stbSamplingFlg) {
    this.stbSamplingFlg = stbSamplingFlg;
  }


  public String getSaleFlg() {
    return saleFlg;
  }

  public void setSaleFlg(String saleFlg) {
    this.saleFlg = saleFlg;
  }


  public String getMixOwner() {
    return mixOwner;
  }

  public void setMixOwner(String mixOwner) {
    this.mixOwner = mixOwner;
  }


  public String getSvPrty() {
    return svPrty;
  }

  public void setSvPrty(String svPrty) {
    this.svPrty = svPrty;
  }


  public String getWorderId() {
    return worderId;
  }

  public void setWorderId(String worderId) {
    this.worderId = worderId;
  }


  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }


  public String getShtIdBc() {
    return shtIdBc;
  }

  public void setShtIdBc(String shtIdBc) {
    this.shtIdBc = shtIdBc;
  }


  public long getFqcFlg() {
    return fqcFlg;
  }

  public void setFqcFlg(long fqcFlg) {
    this.fqcFlg = fqcFlg;
  }


  public String getRcvFlg() {
    return rcvFlg;
  }

  public void setRcvFlg(String rcvFlg) {
    this.rcvFlg = rcvFlg;
  }


  public String getSvProductId() {
    return svProductId;
  }

  public void setSvProductId(String svProductId) {
    this.svProductId = svProductId;
  }


  public String getSvMtrlProductId() {
    return svMtrlProductId;
  }

  public void setSvMtrlProductId(String svMtrlProductId) {
    this.svMtrlProductId = svMtrlProductId;
  }


  public String getWorderType() {
    return worderType;
  }

  public void setWorderType(String worderType) {
    this.worderType = worderType;
  }


  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }


  public String getBoxId() {
    return boxId;
  }

  public void setBoxId(String boxId) {
    this.boxId = boxId;
  }


  public String getPalletId() {
    return palletId;
  }

  public void setPalletId(String palletId) {
    this.palletId = palletId;
  }


  public String getShipNo() {
    return shipNo;
  }

  public void setShipNo(String shipNo) {
    this.shipNo = shipNo;
  }


  public String getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }


  public String getSerialNoCust() {
    return serialNoCust;
  }

  public void setSerialNoCust(String serialNoCust) {
    this.serialNoCust = serialNoCust;
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


  public String getAddtInfo3() {
    return addtInfo3;
  }

  public void setAddtInfo3(String addtInfo3) {
    this.addtInfo3 = addtInfo3;
  }


  public String getAddtInfo4() {
    return addtInfo4;
  }

  public void setAddtInfo4(String addtInfo4) {
    this.addtInfo4 = addtInfo4;
  }


  public String getAddtInfo5() {
    return addtInfo5;
  }

  public void setAddtInfo5(String addtInfo5) {
    this.addtInfo5 = addtInfo5;
  }


  public String getAddtInfo6() {
    return addtInfo6;
  }

  public void setAddtInfo6(String addtInfo6) {
    this.addtInfo6 = addtInfo6;
  }


  public String getAddtInfo7() {
    return addtInfo7;
  }

  public void setAddtInfo7(String addtInfo7) {
    this.addtInfo7 = addtInfo7;
  }


  public String getAddtInfo8() {
    return addtInfo8;
  }

  public void setAddtInfo8(String addtInfo8) {
    this.addtInfo8 = addtInfo8;
  }


  public String getAddtInfo9() {
    return addtInfo9;
  }

  public void setAddtInfo9(String addtInfo9) {
    this.addtInfo9 = addtInfo9;
  }


  public String getAddtInfo10() {
    return addtInfo10;
  }

  public void setAddtInfo10(String addtInfo10) {
    this.addtInfo10 = addtInfo10;
  }

}
