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
 * =====================================================================
 * Modification History
 * ---------------------------------------------------------------------
 * L1.1	     HsiaoYa	     add AT and PEP Layout Type
 * L1.2	     HsiaoYa	     add CLN and NOCLN Layout Type
 */
package com.ibm.lcd.cfm.monitor.layout;

/**
 * This class defines layout types.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2002/01/01 %
 */
public final class LayoutType {

    public final static int EQUIPMENT = 1;
    public final static int STOCKER = 2;
    public final static int UNIT = 3;
    public final static int PORT = 4;
    public final static int CARRIER = 5;
    public final static int AT = 11;		/* L1.1 */
    public final static int PEP = 12;		/* L1.1 */
    public final static int CLN = 13;		/* L1.2	*/
    public final static int NOCLN = 14;		/* L1.2 */
    public final static int EMPTY =0;		/* L1.2 */
}