/*
 * (C) Copyright IBM Corp. 2002  All rights reserved.
 *
 * US Government Users Restricted Rights Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 *
 * The program is provided "as is" without any warranty express or
 * implied, including the warranty of non-infringement and the implied
 * warranties of merchantibility and fitness for a particular purpose.
 * IBM will not be liable for any damages suffered by you as a result
 * of using the Program. In no event will IBM be liable for any
 * special, indirect or consequential damages or lost profits even if
 * IBM has been advised of the possibility of their occurrence. IBM
 * will not be liable for any third party claims against you.
 */
//*********************************************************************************
//
// SYSTEM        : IBM MES system
//
// PROGRAM NAME  : BulletinBoardQueryAction.java
//
// Outline       :
//
// (c) Copyright 2005, International Business Machines Corp
//
// Modification history:
//
// DATE        LEVEL  NAME             COMMENT
// ----------  -----  ---------------  --------------------------------------------
// 2005/08/01  A0.00  IBM              Initial Release
// 2014/03/13  C0.00  Nico Cai         Add loaded carrier info on port
//*********************************************************************************
package com.ibm.lcd.cfm.monitor.layout;

/**
 * This class represents the port state.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2002/01/01 %
 */
public class PortState extends IdentifyObject {

    public EquipmentState equipmentState = null;
    public String state = null;
    
    public boolean portDOWN = false;  //Port Down
    public boolean portFREE = false;  //Port Free
    public boolean portLDCM = false;  //Port Load Complete
    public boolean portLDRQ = false;  //Load Request
    public boolean portUDCM = false;  //Un-Load Complete
    public boolean portUDRQ = false;  //Un-Load Request  
    
    public int currentStatus = 0;

    public String crr_id = null;	//C0.00
    
    

    public PortState(String id) {
        super(id);
    }

}