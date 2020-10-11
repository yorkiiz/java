package com.ibm.lcd.cfm.monitor.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class GraphMaker4 extends Canvas {

	private static final long serialVersionUID = 6541674569018065956L;

	final String CLASS_NAME = "GraphMaker4";

	final boolean DEBUG    = false;

	private String[] data_label_Y;
	/** �����ڂ���l(index 0�̓_�~�[)        */
	private String[] data_label_X;
	/** �����ڂ���l�T�u(index 0�̓_�~�[)    */
	private String[] data_label_X_Sub;

	/** �O??�t�`��ʒu�̌��_�����X���W�I�t�Z�b�g */
	private int Graph_offset_X;
	/** �O??�t�`��ʒu�̌��_�����Y���W�I�t�Z�b�g */
	private int Graph_offset_Y;
	/** �O??�t�̑傫��(??��)                 */
	private int Graph_Height;
	/** �o�[��100%(MAX�l)�ƍl����l          */
	private int maxValue;

	/** �w�b�_�[�^�C�g??����                 */
	private String Header_Name;
	/** �w�b�_�[�ʒu(����)                   */
	private int Header_Width;
	/** �w�b�_�[�ʒu(�c��)                   */
	private int Header_Height;

	/** �T�u�X�e�[�^�X����                   */
	private String Sub_Status_Name;
	/** �T�u�X�e�[�^�X�ʒu(����)             */
	private int Sub_Status_Width;
	/** �T�u�X�e�[�^�X�ʒu(�c��)             */
	private int Sub_Status_Height;

	/** X���P�ʖ���                          */
	private String X_Unit_Name;
	/** X���P�ʈʒu(����)                    */
	private int X_Unit_Width;
	/** X���P�ʈʒu(�c��)                    */
	private int X_Unit_Height;

	/** Y���P�ʖ���                          */
	private String Y_Unit_Name;
	/** Y���P�ʈʒu(����)                    */
	private int Y_Unit_Width;
	/** Y���P�ʈʒu(�c��)                    */
	private int Y_Unit_Height;

	/** �c���̕�????                         */
	private int Graph_Devide;
	/** �c���̖ڐ���Ԃ̒l                   */
	//private int Graph_Devide_Height;
	/** �c����1��??����??��                  */
	private int Y_Tick_Height;
	
	/** �O??�t�̉�??                         */
	private int Graph_Width;
	/** �����̕�????                         */
	//private int X_Tick_Width;
	/** �����̖ڐ���Ԃ̒l                   */
	//private int Graph_Devide_Width;
	/** �����̖ڐ���(�T�u)�̏㉺�ʒu�����l   */
	private int XLabelSubDetail;
	/** �����̖ڐ���(??�l)�̈ʒu�̒����l     */
	private int Graph_Width_Detail; //-7

	/** �O??�t�o�[�̈ʒu�����l               */
	private int Graph_Bar_Detail;
	/** �O??�t�o�[�̑���                     */
	private int Graph_Bar_Thick;

	/** �w�b�_�[�t�H??�g                     */
	private Font fontH;
	/** �T�u�X�e�[�^�X�t�H??�g               */
	private Font fontS;
	/** Y���P�ʃt�H??�g                      */
	private Font fontU_Y;
	/** X���P�ʃt�H??�g                      */
	private Font fontU_X;
	/** �c���t�H??�g                         */
	private Font fontY;
	/** �����t�H??�g                         */
	private Font fontX;
	/** �o�[�t�H??�g                         */
	private Font fontB;
	/** �o�[�̒l�t�H??�g                     */
	private Font fontB_S;

	/** �w�b�_�[�t�H??�g�J??�[               */
	private Color fontColorH;
	/** �T�u�X�e�[�^�X�t�H??�g�J??�[         */
	private Color fontColorS;
	/** Y���P�ʃt�H??�g�J??�[                */
	private Color fontColorU_Y;
	/** X���P�ʃt�H??�g�J??�[                */
	private Color fontColorU_X;
	/** �c���t�H??�g�J??�[                   */
	private Color fontColorY;
	/** �����t�H??�g�J??�[                   */
	private Color fontColorX;
	/** �o�[�t�H??�g�J??�[                   */
	private Color fontColorB;
	/** �o�[�̒l�t�H??�g�J??�[               */
	private Color fontColorB_S;

	/** �c���ڐ���L���t??�O                 */
	private boolean isScaleY;
	/** �c���ڐ��蒲���l(??���ō��E�ɑJ��)   */
	private int scaleDetailY;
	/** Y��??�ڍő�\����????                */
	private int maxScaleLengthY;
	/** ���l�E�l�߃t??�O(true:���Afalse:�E)  */
	private boolean isHeadOrTail;

	/** �O??�t�o�[�̒l�\��/��\���t??�O      */
	private boolean isGraph_Bar_Status;
	/** X����i??�C??�ʒu�㉺�������l        */
	private int lineDetailXTop;

	/** �c��؂���̊Ԋu(??)�̔������l       */
	private int detailDecade;
	/** �c��؂���̊Ԋu(�ʒu)�̔������l       */
	private int detailState;

	/** �O??�t??�[�J�[�ėp�f�[�^�Z�b�g       */
	private GraphMakerCommonDataSet data;

	/**************************************************************************
	 * �R??�X�g??�N�^<br>
	 * �O??�t�̊e�p�[�c��??���ݒ���s���܂��B<br>
	 *
	 * @param dataSet �O??�t??�[�J�[�ėp�f�[�^�Z�b�g
	 *************************************************************************/
	public GraphMaker4(GraphMakerCommonDataSet dataSet) {

		// �f�t�H??�g�l�̃Z�b�g
		setDefault();

		this.data_label_Y        = dataSet.getDataLabelY();	// �c���ڂ���l(index 0�̓_�~�[)
		this.data_label_X        = dataSet.getDataLabelX();	// �����ڂ���l(index 0�̓_�~�[)
		this.data_label_X_Sub    = dataSet.getDataLabelXSub();	// �����ڂ���l�T�u(index 0�̓_�~�[)
		
		this.Graph_offset_X      = dataSet.getGraphOffset_X();			// �O??�t�`��ʒu�̌��_�����X���W�I�t�Z�b�g
		this.Graph_offset_Y      = dataSet.getGraphOffset_Y();			// �O??�t�`��ʒu�̌��_�����Y���W�I�t�Z�b�g
		this.Graph_Height        = dataSet.getGraphHeight();			// �O??�t�̑傫��(??��)
		this.maxValue            = dataSet.getMaxValue();				// �o�[��100%(MAX�l)�ƍl����l
		this.Graph_Devide        = dataSet.getDataLabelX().length - 1;	// �c���̕�????
		this.Graph_Width         = dataSet.getGraphWidth();			// �O??�t�̉�??
		this.Graph_Width_Detail  = dataSet.getGraphWidthDetail();		// �����̖ڐ���(??�l)�̈ʒu�̒����l
		this.Graph_Bar_Detail    = dataSet.getGraphBarDetail();		// �O??�t�o�[�̈ʒu�����l
		this.Graph_Bar_Thick     = dataSet.getGraphBarThick();			// �O??�t�o�[�̑���
		this.Header_Name         = dataSet.getHeaderName();			// �w�b�_�[����
		this.Header_Width        = dataSet.getHeaderWidth();			// �w�b�_�[�ʒu(����)
		this.Header_Height       = dataSet.getHeaderHeight();			// �w�b�_�[�ʒu(�c��)
		this.Sub_Status_Name     = dataSet.getSubStatusName();			// �T�u�X�e�[�^�X
		this.Sub_Status_Width    = dataSet.getSubStatusWidth();		// �T�u�X�e�[�^�X�\���ʒu(����)
		this.Sub_Status_Height   = dataSet.getSubStatusHeight();		// �T�u�X�e�[�^�X�\���ʒu(�c��)
		this.X_Unit_Name         = dataSet.getXUnitName();				// X���P�ʖ���
		this.X_Unit_Width        = dataSet.getXUnitWidth();			// X���P�ʈʒu(����)
		this.X_Unit_Height       = dataSet.getXUnitHeight();			// X���P�ʈʒu(�c��)
		this.Y_Unit_Name         = dataSet.getYUnitName();				// Y���P�ʖ���
		this.Y_Unit_Width        = dataSet.getYUnitWidth();			// Y���P�ʈʒu(����)
		this.Y_Unit_Height       = dataSet.getYUnitHeight();			// Y���P�ʈʒu(�c��)
		this.fontH               = dataSet.getFontH();					// �w�b�_�[�t�H??�g
		this.fontS               = dataSet.getFontS();					// �T�u�X�e�[�^�X�t�H??�g
		this.fontU_Y             = dataSet.getFontU_Y();				// Y���P�ʃt�H??�g
		this.fontU_X             = dataSet.getFontU_X();				// X���P�ʃt�H??�g
		this.fontY               = dataSet.getFontY();					// �c���t�H??�g
		this.fontX               = dataSet.getFontX();					// �����t�H??�g
		this.fontB               = dataSet.getFontB();					// �o�[�t�H??�g
		this.fontB_S             = dataSet.getFontB_S();				// �o�[�̒l�t�H??�g
		this.fontColorH          = dataSet.getFontColorH();			// �w�b�_�[�t�H??�g�J??�[
		this.fontColorS          = dataSet.getFontColorS();			// �T�u�X�e�[�^�X�t�H??�g�J??�[
		this.fontColorU_Y        = dataSet.getFontColorU_Y();			// Y���P�ʃt�H??�g�J??�[
		this.fontColorU_X        = dataSet.getFontColorU_X();			// X���P�ʃt�H??�g�J??�[
		this.fontColorY          = dataSet.getFontColorY();			// �c���t�H??�g�J??�[
		this.fontColorX          = dataSet.getFontColorX();			// �����t�H??�g�J??�[
		this.fontColorB          = dataSet.getFontColorB();			// �o�[�t�H??�g�J??�[
		this.fontColorB_S        = dataSet.getFontColorB_S();			// �o�[�̒l�t�H??�g�J??�[
		this.isScaleY            = dataSet.getIsScaleY();				// �����ڐ���L���t??�O
		this.scaleDetailY        = dataSet.getScaleDetailY();			// �c���ڐ��蒲���l(??���ō��E�ɑJ��)
		this.maxScaleLengthY     = dataSet.getMaxScaleLengthY();		// Y��??�ڍő�\����????
		this.isHeadOrTail        = dataSet.getIsHeadOrTail();			// ���l�E�l�߃t??�O(true:���Afalse:�E)
		this.isGraph_Bar_Status  = dataSet.getIsGraphBarStatus();		// �O??�t�o�[�̒l�\��/��\���t??�O(true:�\���Afalse:��\��)
		this.lineDetailXTop      = dataSet.getLineDetailXTop();		// X����i??�C??�ʒu�㉺�������l
		this.XLabelSubDetail     = dataSet.getXLabelSubDetail();		// �����̖ڐ���(�T�u)�̏㉺�ʒu�����l
		
		this.detailDecade        = dataSet.getDetailDecade();		// ��؂���Ԋu�̔������l
		this.detailState         = dataSet.getDetailState();		// ��؂���ʒu�̔������l
		
		//this.Graph_Devide_Height = maxValue / Graph_Devide;		// �c���̖ڐ���Ԃ̒l(���g�p)
		//this.Graph_Devide_Width  = maxValue / Graph_Devide;		// �����̖ڐ���Ԃ̒l(���g�p)
		
		this.Y_Tick_Height       = Graph_Height / data_label_Y.length;				// �c����1��??����??��
		//this.X_Tick_Width        = (Graph_Width / Graph_Devide) - Graph_offset_X;	// �����̕�????
		
		this.data = dataSet;

	}

	private void setDefault() {
		
		this.data_label_Y        = null;
		this.data_label_X        = null;	/* �����ڂ���l(index 0�̓_�~�[)        */
		this.data_label_X_Sub    = null;	/* �����ڂ���l�T�u(index 0�̓_�~�[)    */
		
		this.Graph_offset_X      = 1;		/* �O??�t�`��ʒu�̌��_�����X�I�t�Z�b�g*/
		this.Graph_offset_Y      = 1;		/* �O??�t�`��ʒu�̌��_�����Y�I�t�Z�b�g*/
		this.Graph_Height        = 1;		/* �O??�t�̑傫��(??��)                 */
		this.maxValue            = 10000;	/* �o�[��100%(MAX�l)�ƍl����l          */
		
		this.Header_Name         = "";		/* �w�b�_�[�^�C�g??����                 */
		this.Header_Width        = 1;		/* �w�b�_�[�ʒu(����)                   */
		this.Header_Height       = 1;		/* �w�b�_�[�ʒu(�c��)                   */
		
		this.Sub_Status_Name     = "";		/* �T�u�X�e�[�^�X����                   */
		this.Sub_Status_Width    = 1;		/* �T�u�X�e�[�^�X�ʒu(����)             */
		this.Sub_Status_Height   = 1;		/* �T�u�X�e�[�^�X�ʒu(�c��)             */
		
		this.X_Unit_Name         = "";		/* X���P�ʖ���                          */
		this.X_Unit_Width        = 1;		/* X���P�ʈʒu(����)                    */
		this.X_Unit_Height       = 1;		/* X���P�ʈʒu(�c��)                    */
		
		this.Y_Unit_Name         = "";		/* Y���P�ʖ���                          */
		this.Y_Unit_Width        = 1;		/* Y���P�ʈʒu(����)                    */
		this.Y_Unit_Height       = 1;		/* Y���P�ʈʒu(�c��)                    */
		
		this.Graph_Devide        = 1;		/* �c���̕�????                         */
		//this.Graph_Devide_Height = 1;		/* �c���̖ڐ���Ԃ̒l                   */
		this.Y_Tick_Height       = 1;		/* �c����1��??����??��                  */
		
		this.Graph_Width         = 1;		/* �o�[�̉�??                           */
		//this.X_Tick_Width        = 1;		/* �����̕�????                         */
		this.Graph_Width_Detail  = 1;		/* �����̖ڐ���(??�l)�̈ʒu�̒����l     */
		this.Graph_Bar_Detail    = 1;		/* �O??�t�o�[�̈ʒu�����l               */
		this.Graph_Bar_Thick     = 1;		/* �O??�t�o�[�̑���                     */
		
		this.fontH               = null;	/* �w�b�_�[�t�H??�g                     */
		this.fontS               = null;	/* �T�u�X�e�[�^�X�t�H??�g               */
		this.fontU_Y             = null;	/* Y���P�ʃt�H??�g                      */
		this.fontU_X             = null;	/* X���P�ʃt�H??�g                      */
		this.fontY               = null;	/* �c���t�H??�g                         */
		this.fontX               = null;	/* �����t�H??�g                         */
		this.fontB               = null;	/* �o�[�t�H??�g                         */
		this.fontB_S             = null;	/* �o�[�̒l�t�H??�g                     */
		
		this.fontColorH          = null;	/* �w�b�_�[�t�H??�g�J??�[               */
		this.fontColorS          = null;	/* �T�u�X�e�[�^�X�t�H??�g�J??�[         */
		this.fontColorU_Y        = null;	/* Y���P�ʃt�H??�g�J??�[                */
		this.fontColorU_X        = null;	/* X���P�ʃt�H??�g�J??�[                */
		this.fontColorY          = null;	/* �c���t�H??�g�J??�[                   */
		this.fontColorX          = null;	/* �����t�H??�g�J??�[                   */
		this.fontColorB          = null;	/* �o�[�t�H??�g�J??�[                   */
		this.fontColorB_S        = null;	/* �o�[�̒l�t�H??�g�J??�[               */
		
		this.isScaleY            = false;	/* �����ڐ���L���t??�O                 */
		this.scaleDetailY        = 1;		/* �c���ڐ��蒲���l(??���ō��E�ɑJ��)   */
		this.maxScaleLengthY     = 1;		/* Y��??�ڍő�\����????                */
		this.isHeadOrTail        = false;	/* ���l�E�l�߃t??�O(true:���Afalse:�E)  */
		this.isGraph_Bar_Status  = false;	/* �O??�t�o�[�̒l�\��/��\���t??�O      */
		
		this.lineDetailXTop      = 1;		/* X����i??�C??�ʒu�㉺�������l        */
		this.XLabelSubDetail     = 1;		/* �����̖ڐ���(�T�u)�̏㉺�ʒu�����l   */
		
		this.detailDecade        = 0;		/* ��؂���Ԋu�̔������l               */
		this.detailState         = 0;		/* ��؂���ʒu�̔������l               */
	}

	public void paint(Graphics g) {
		defaultCheck(fontH, fontS, fontU_Y, fontU_X, fontY, fontX, fontB,
					fontColorH, fontColorS, fontColorU_Y, fontColorU_X, fontColorY, fontColorX, fontColorB,
					data_label_Y, data_label_X, data_label_X_Sub);

		makeHeader(g);

		makeSubStatus(g);

		// X���P�ʖ��̂̐ݒ�
		makeUnitX(g);

		// Y���P�ʖ��̂̐ݒ�
		makeUnitY(g);

		// �c���̕`��
		makeHeight(g);

		// �����̕`��
		makeWidth(g);

		// �ڐ���̕`��
		makeBar(g);

	}

	/**********************************************************
	 * �O??�t�w�b�_�[??��(�O??�t�^�C�g??)��`�ʂ��܂�
	 *
	 * @param g �O??�t�B�N�X
	 **********************************************************/
	private void makeHeader(Graphics g) {

		// �t�H??�g�̃X�^�C??�ݒ�
		setStyle(g, fontH, fontColorH);

		// �w�b�_�̕����ƕ`�ʈʒu�̐ݒ�
		g.drawString(this.Header_Name, this.Header_Width, this.Header_Height);

	}

	/**********************************************************
	 * �O??�t�T�u�X�e�[�^�X��`�ʂ��܂�
	 *
	 * @param g �O??�t�B�N�X
	 **********************************************************/
	private void makeSubStatus(Graphics g) {

		// �t�H??�g�̃X�^�C??�ݒ�
		setStyle(g, fontS, fontColorS);

		// �w�b�_�̕����ƕ`�ʈʒu�̐ݒ�
		g.drawString(this.Sub_Status_Name, this.Sub_Status_Width, this.Sub_Status_Height);
	}

	/**********************************************************
	 * X���P�ʖ��̂�`�ʂ��܂�
	 *
	 * @param g �O??�t�B�N�X
	 **********************************************************/
	private void makeUnitX(Graphics g) {

		// �t�H??�g�̃X�^�C??�ݒ�
		setStyle(g, fontU_X, fontColorU_X);

		// �w�b�_�̕����ƕ`�ʈʒu�̐ݒ�
		g.drawString(this.X_Unit_Name, this.X_Unit_Width, this.X_Unit_Height);

	}

	/**********************************************************
	 * Y���P�ʖ��̂�`�ʂ��܂�
	 *
	 * @param g �O??�t�B�N�X
	 **********************************************************/
	private void makeUnitY(Graphics g) {

		// �t�H??�g�̃X�^�C??�ݒ�
		setStyle(g, fontU_Y, fontColorU_Y);

		// �w�b�_�̕����ƕ`�ʈʒu�̐ݒ�
		g.drawString(this.Y_Unit_Name, this.Y_Unit_Width, this.Y_Unit_Height);

	}

	/**********************************************************
	 * �O??�t�c��??����`�ʂ��܂�
	 *
	 * @param g �O??�t�B�N�X
	 **********************************************************/
	private void makeHeight(Graphics g) {
		/** �c���ڐ���̒l�̒����`�F�b�N�l */
		//int chkWidth_Length        = 0;
		/** �c���ڐ���̕`�ʈʒu */
		int State_Width            = 0;

		// �t�H??�g�̃X�^�C??�ݒ�
		setStyle(g, fontY, fontColorY);

		// �c����`�ʂ��܂�(�����r��)
		g.drawLine(	Graph_offset_X - 1,
					Graph_offset_Y - 1,
					Graph_offset_X - 1,
					Graph_offset_Y + Graph_Height);

		// �c����`�ʂ��܂�(�E���r��)
		g.drawLine(	Graph_offset_X + Graph_Width -1,
					Graph_offset_Y,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + Graph_Height);

		// �c����??�x??�����܂�
		for (int i = 0; i < data_label_Y.length; i++) {
			String checkedtext = "";

			// ��{State_Width(�c��??�ڂ̈ʒu(X�������ɍ��E�ړ������܂�))
			State_Width = scaleDetailY /*114*/;

			// ���p�p??��??�A���p�J�^�J�i��S�p��??�ɕϊ�
			// String chkT = ConvertUtil.hanKkana2ZenKkana(ConvertUtil.toFullANS(data_label_Y[i]));

			// Y���\��??�ڕ�??�l�߈ʒu����
			if (isHeadOrTail) {
				// checkedtext = StringUtil.addtoN(chkT, maxScaleLengthY);
				checkedtext = StringUtil.addtoN(data_label_Y[i], maxScaleLengthY);	// Y��??�ڂ����l�߂ɂ���
			} else {
				// checkedtext = StringUtil.addtoN2(chkT, maxScaleLengthY);
				checkedtext = StringUtil.addtoN2(data_label_Y[i], maxScaleLengthY);	// Y��??�ڂ��E�l�߂ɂ���
			}

			// �c���ڐ����`��
			g.drawString(	checkedtext, 
							Graph_offset_X - State_Width - 25,
							Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + 5);	// �c���̖ڐ���(??�l)�̈ʒu(��)

			setStyle(g, fontY, fontColorY);
			if (isScaleY) {
				// �O??�b�h����`�悵�܂�
				if (i > 0) {
					// Y���ڐ���O??�b�h����`�悵�܂�
					g.drawLine( Graph_offset_X,
								Graph_offset_Y + Graph_Height - (i * Y_Tick_Height),
								Graph_offset_X - 5,
								Graph_offset_Y + Graph_Height - (i * Y_Tick_Height));
				}
			}
		}
		
	}

	/**********************************************************
	 * �O??�t����??����`�ʂ��܂�
	 *
	 * @param g �O??�t�B�N�X
	 **********************************************************/
	private void makeWidth(Graphics g) {

		// �t�H??�g�̃X�^�C??�ݒ�
		setStyle(g, fontX, fontColorX);

		// ��؂���Ԋu�̔������l
		final int moreDetailDecade = (Graph_Width / Graph_Devide) + detailDecade;

		// �ڐ���̋�؂�ƂȂ����`�悵�܂�
		for (int i = 1; i < Graph_Devide ; i++) {
			//int bar_width = Graph_Height;

			// ��؂���̔z�F
			if (i != Graph_Devide) {
				// ??�x??�_�[�F
				setStyle(g, fontX, new Color(220, 220, 220));
			} else {
				setStyle(g, fontX, fontColorX);
			}

			// �O??�b�h����`�悵�܂�(X���ڐ��育�Ƃɕ`��)
			g.drawLine( Graph_offset_X + (moreDetailDecade * i) + detailState /*(X_Tick_Width * i) + (Graph_offset * i)*/,
						Graph_offset_Y,
						Graph_offset_X + (moreDetailDecade * i) + detailState /*(X_Tick_Width * i) + (Graph_offset * i)*/,
						Graph_offset_Y + Graph_Height);

		}

		// �t�H??�g�����ɖ߂�
		setStyle(g, fontX, fontColorX);

		// ������`�ʂ��܂�(�㑤�r��) // ������??���K�v�Ȃ̂�??�ӁI
		g.drawLine( Graph_offset_X,
					Graph_offset_Y + lineDetailXTop,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + lineDetailXTop);
//		g.drawLine( Graph_offset,
//				   (Graph_offset + Graph_Height - (data_label_Y.length * Y_Tick_Height)) -6 + lineDetailXTop,
//					Graph_offset + Graph_Width -1,
//				   (Graph_offset + Graph_Height - (data_label_Y.length * Y_Tick_Height)) -6 + lineDetailXTop);
				   
System.out.println("Graph_Height:"+ Graph_Height +" Y_length:"+ data_label_Y.length +" Y_Tick_Height:"+ Y_Tick_Height +" lineDetailXTop:"+ lineDetailXTop);

		// ������`�ʂ��܂�(�����r��)
		g.drawLine( Graph_offset_X,
					Graph_offset_Y + Graph_Height,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + Graph_Height);

		// ������??�x??�����܂�
		for (int i = 0; i <= Graph_Devide; i++) {
			g.drawString(data_label_X[i],
						Graph_offset_X + /*(X_Tick_Width * i) + (Graph_offset * i)*/(moreDetailDecade * i) + Graph_Width_Detail,    // �����̖ڐ���(??�l)�̈ʒu(��)
						Graph_offset_Y + Graph_Height + 18);                                               // �����̖ڐ���(??�l)�̈ʒu(�c)

			// �����X�e�[�^�X(�T�u)
			if(data_label_X.length == data_label_X_Sub.length && data_label_X_Sub.length > 0 && data_label_X_Sub != null){
				g.drawString(data_label_X_Sub[i],
							Graph_offset_X + /*(X_Tick_Width * i) + (Graph_offset * i)*/(moreDetailDecade * i) + Graph_Width_Detail,// �����̖ڐ���T�u(??�l)�̈ʒu(��)
							Graph_offset_Y + Graph_Height + 18 + XLabelSubDetail);                         // �����̖ڐ���T�u(??�l)�̈ʒu(�c)
			}

		}
	}

//	/**********************************************************
//	 * �O??�t�o�[??����`�ʂ��܂�
//	 *
//	 * @param g �O??�t�B�N�X
//	 **********************************************************/
//	private void makeBar_org(Graphics g) {
//		/** ??�\�b�h���� */
//		final String METHOD_NAME = "makeBar";
//
//		// �f�o�b�O??�O�o��
//		LogUtil.out(true, CLASS_NAME, METHOD_NAME, "START");
//
//		// �t�H??�g�̃X�^�C??�ݒ�
//		setStyle(g, fontB, fontColorB);
//
//		// �ޔ�pint
//		int next = 0;
//
//		// �ڍ׃f�[�^�S�Ă��i�[����??�X�g�̎擾
//		ArrayList array1 = (ArrayList) data.getList();
//
//		// �p????�[�^�̌�??������??�[�v
//		for (int i = 0; i < array1.size(); i++) {
//			// i == 0�̏�??�̓o�[�͐�΂�??���Ȃ�����
//			if (i > 0) {
//				// �O??�t�f�[�^�̎擾
//				GraphMakerCommonDataSet data1 =	(GraphMakerCommonDataSet) array1.get(i);
//				
//				// 1�{�Â̃o�[�̏ڍ׏����i�[����ArrayList
//				ArrayList array2 = (ArrayList) data1.getDetailList();
//
//				
//				int bar_width_bk = 0;	// 1�̃o�[�̑S�̂̒���
//				int bar_width    = 0;	// 1�̃o�[��??�̈�??�ڂ̃o�[�̒���
//				int checkFrom;			// �f�[�^��??���Ă���z��̎擾�J�n�C??�f�b�N�X
//				int checkTo;			// �f�[�^��??���Ă���z��̎擾�I���C??�f�b�N�X
//				
//				
//				if (i == 1)	{checkFrom = 1 + next;}	// ??���X�^�[�g�J�n�C??�f�b�N�X��1
//				else			{checkFrom = next;}		// ??���ȊO�̊J�n�C??�f�b�N�X
//				
//				
//				// �J�n�C??�f�b�N�X���番????(�I���C??�f�b�N�X)�܂ł̒���
//				checkTo = checkFrom + data_div[i] + 1;
//				next = checkTo;
//				
//				// �O??�t�̃o�[��??��??��
//				for (int k = checkFrom; k < checkTo; k++) {
//
//					// �O??�t�ڍ׃f�[�^�̎擾
//					GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet data2 =
//						(GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet) array2.get(k);
//
//					// 1��??�̃o�[�̒���
//					bar_width = data2.getRate() * Graph_Width / maxValue;
//					// �O??�t�o�[�̐F�̐ݒ�
//					g.setColor(data2.getBarColor());
//					// �O??�t�o�[��`��
//					g.fillRect(Graph_offset + bar_width_bk,                                         // X���̎n�_
//							   Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail,// Y���̎n�_
//							   bar_width,                                                           // X���̏I�_
//							   Graph_Bar_Thick);                                                    // Y���̑���(�o�[�̑���)
//
//					// 1�{�̃o�[����??��ނ̃o�[��??�����߂̒l�ݒ�
//					bar_width_bk = bar_width_bk + bar_width;
//
//					/****************************
//					 * �O??�t�o�[�̒l��`�ʂ��܂�
//					 ****************************/
//					if(isGraph_Bar_Status){
//						// �t�H??�g�̃X�^�C??�ݒ�
//						setStyle(g, fontB_S, fontColorB_S);
//
//						// �o�[��????����������??�A�o�[�O��??�ڃf�[�^��`�ʂ��܂�
//						if(data2.getRate() <= 5 && data2.getRate() > 0){
//
//							g.setColor(Color.DARK_GRAY);
//							// �w��
//							g.drawLine((Graph_offset + bar_width_bk - (bar_width / 2)) ,
//										Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail,
//									   (Graph_offset + bar_width_bk - (bar_width / 2)),
//										Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 3);
//
//							// �O??�t�o�[�̒l��\�����镶??��̐F
//							g.setColor(data2.getTextColor());
//							// �O??�t�o�[�̒l��`�ʂ��܂�
//							g.drawString(data2.getText(),                                                            // ??�x??(�ڐ���)��i * Graph_Devide_Height���Ƃɕ\��
//										 (Graph_offset + bar_width_bk - (bar_width / 2)) - 5,                        // �c���̖ڐ���(??�l)�̈ʒu(��)�B??�S���10����蕶????���o��
//										  Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 5); // �c���̖ڐ���(??�l)�̈ʒu(�c)
//						} else {
//
//							// �O??�t�o�[�̒l��\�����镶??��̐F
//							g.setColor(data2.getTextColor());
//							// �O??�t�o�[�̒l��`�ʂ��܂�
//							g.drawString(data2.getText(),                                   // ??�x??(�ڐ���)��i * Graph_Devide_Height���Ƃɕ\��
//									(Graph_offset + bar_width_bk - (bar_width / 2)) - 10,   // �c���̖ڐ���(??�l)�̈ʒu(��)�B??�S���10����蕶????���o��
//									Graph_offset + Graph_Height - (i * Y_Tick_Height) + 5); // �c���̖ڐ���(??�l)�̈ʒu(�c)
//						}
//
//						// �t�H??�g�̃X�^�C??�ݒ�(���ɖ߂�)
//						setStyle(g, fontB, fontColorB);
//					}
//				}
//			}
//		}
//
//		// �d�Ȃ����Ƃ��̗D��x��??���̂�??������
//		// �t�H??�g�̃X�^�C??�ݒ�
//		setStyle(g, fontY, fontColorY);
//
//		// �c����`�ʂ��܂�(�E���r��)
//		g.drawLine(
//			Graph_offset + Graph_Width -1,
//			Graph_offset,
//			Graph_offset + Graph_Width -1,
//			Graph_offset + Graph_Height);
//
//		// �f�o�b�O??�O�o��
//		LogUtil.out(true, CLASS_NAME, METHOD_NAME, "END");
//	}

	/**********************************************************
	 * �O??�t�o�[??����`�ʂ��܂�
	 *
	 * @param g �O??�t�B�N�X
	 **********************************************************/
	private void makeBar(Graphics g) {
		setStyle(g, fontB, fontColorB);

		ArrayList array1 = (ArrayList) data.getList();
		
		for (int i = 0; i < array1.size(); i++) {
			if (i > 0) {				
				ArrayList detaillist = (ArrayList) array1.get(i);
				
				int bar_width_bk = 0;	// 1�̃o�[�̑S�̂̒���
				int bar_width    = 0;	// 1�̃o�[��??�̈�??�ڂ̃o�[�̒���
				
				int finger_flg = 0;	// �w���t??�O(0:�w���Ȃ� 1:�w���� 2:�w����)
				int fingerdetail = 0;	// �w�����̏�??�̒����l
				int fingertext = 0;	// �w�����̏�??�̒����l
				
				for (int k = 0; k < detaillist.size(); k++) {
					
					// �O??�t�ڍ׃f�[�^�̎擾
					GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet data2 =
						(GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet) detaillist.get(k);
					
					// 1��??�̃o�[�̒���
					if(k == detaillist.size() - 1){
						bar_width = Graph_Width - bar_width_bk;
					}else{
						bar_width = data2.getRate() * Graph_Width / maxValue;
					}
					
					// bar_width���[??�̏�??�́A�R????�g������
					if(bar_width == 0){ data2.setText(""); }
					
					// �O??�t�o�[�̐F�̐ݒ�
					g.setColor(data2.getBarColor());
					// �O??�t�o�[��`��
					g.fillRect(Graph_offset_X + bar_width_bk,                                         // X���̎n�_
							   Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail,// Y���̎n�_
							   bar_width,                                                             // X���̏I�_
							   Graph_Bar_Thick);                                                      // Y���̑���(�o�[�̑���)

					// 1�{�̃o�[����??��ނ̃o�[��??�����߂̒l�ݒ�
					bar_width_bk = bar_width_bk + bar_width;

					/****************************
					 * �O??�t�o�[�̒l��`�ʂ��܂�
					 ****************************/
					if(isGraph_Bar_Status){
						// �t�H??�g�̃X�^�C??�ݒ�
						setStyle(g, fontB_S, fontColorB_S);

						// �o�[��????����������??�A�o�[�O��??�ڃf�[�^��`�ʂ��܂�
						if(data2.getRate() <= (maxValue*0.05) && data2.getRate() > 0){
							
							// �w���̒���
							if(finger_flg == 0 || finger_flg == 2){
								finger_flg = 1;
								fingerdetail = 0;
								fingertext = 0;
							}else{
								finger_flg = 2;
								fingerdetail = Graph_Bar_Thick + 3;
								fingertext = Graph_Bar_Thick + 20;
							}
							
							g.setColor(Color.DARK_GRAY);
							// �w��
							g.drawLine((Graph_offset_X + bar_width_bk - (bar_width / 2)) ,
										Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail + fingerdetail,
									   (Graph_offset_X + bar_width_bk - (bar_width / 2)),
										Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 3 + fingerdetail);

							// �O??�t�o�[�̒l��\�����镶??��̐F
							g.setColor(data2.getTextColor());
							// �O??�t�o�[�̒l��`�ʂ��܂�
							g.drawString(data2.getText(),                                                              // ??�x??(�ڐ���)��i * Graph_Devide_Height���Ƃɕ\��
										 (Graph_offset_X + bar_width_bk - (bar_width / 2)) - 5,                        // �c���̖ڐ���(??�l)�̈ʒu(��)�B??�S���10����蕶????���o��
										  Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 5 + fingertext); // �c���̖ڐ���(??�l)�̈ʒu(�c)
						} else {
							// �w���t??�O??����
							finger_flg = 0;
							fingerdetail = 0;
							
							// �O??�t�o�[�̒l��\�����镶??��̐F
							g.setColor(data2.getTextColor());
							// �O??�t�o�[�̒l��`�ʂ��܂�
							g.drawString(data2.getText(),                                      // ??�x??(�ڐ���)��i * Graph_Devide_Height���Ƃɕ\��
									(Graph_offset_X + bar_width_bk - (bar_width / 2)) - 10,    // �c���̖ڐ���(??�l)�̈ʒu(��)�B??�S���10����蕶????���o��
									 Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + 5); // �c���̖ڐ���(??�l)�̈ʒu(�c)
						}

						// �t�H??�g�̃X�^�C??�ݒ�(���ɖ߂�)
						setStyle(g, fontB, fontColorB);
					}
				}
			}
		}

		// �d�Ȃ����Ƃ��̗D��x��??���̂�??������
		// �t�H??�g�̃X�^�C??�ݒ�
		setStyle(g, fontY, fontColorY);

		// �c����`�ʂ��܂�(�E���r��)
		g.drawLine( Graph_offset_X + Graph_Width -1,
					Graph_offset_Y,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + Graph_Height);

	}

	/**********************************************************
	 * GraphMaker�N??�X���Ă΂ꂽ�ہA�s���l��INPUT����Ă��Ȃ����`�F�b�N���܂�<br>
	 * �s���l�������n���ꂽ��??�A�����I�Ƀf�t�H??�g�ݒ���s���܂��B
	 *
	 * @param fontH         �w�b�_�[�t�H??�g
	 * @param fontS         �T�u�X�e�[�^�X�t�H??�g
	 * @param fontU_Y       Y���P�ʃt�H??�g
	 * @param fontU_X       X���P�ʃt�H??�g
	 * @param fontY         �O??�t�c���t�H??�g
	 * @param fontX         �����t�H??�g
	 * @param fontB         �O??�t�o�[�t�H??�g
	 * @param fontColorH    �w�b�_�[�t�H??�g�J??�[
	 * @param fontColorS    �T�u�X�e�[�^�X�t�H??�g�J??�[
	 * @param fontColorU_Y  Y���P�ʃt�H??�g�J??�[
	 * @param fontColorU_X  X���P�ʃt�H??�g�J??�[
	 * @param fontColorY    �O??�t�c���t�H??�g�J??�[
	 * @param fontColorX    �O??�t�����t�H??�g�J??�[
	 * @param fontColorB    �O??�t�o�[�t�H??�g�J??�[
	 * @param data_label_Y  �c���ڂ���l
	 * @param data_label_X  �����ڐ���l
	 * @param data_div      �o�[�̋�؂�??�Q�z��
	 **********************************************************/
	private void defaultCheck(Font fontH, Font fontS, Font fontU_Y, Font fontU_X, Font fontY, Font fontX, Font fontB,
								Color fontColorH, Color fontColorS, Color fontColorU_Y, Color fontColorU_X, Color fontColorY, Color fontColorX, Color fontColorB,
								String[] data_label_Y, String[] data_label_X, String[] data_label_X_Sub) {

		// �t�H??�gnull�΍�  �f�t�H??�g�t�H??�g���Z�b�g
		if (fontH == null)		{ this.fontH   = new Font("Courier", Font.PLAIN, 10); }
		if (fontS == null)		{ this.fontS   = new Font("Courier", Font.PLAIN, 10); }
		if (fontU_Y == null)	{ this.fontU_Y = new Font("Courier", Font.PLAIN, 10); }
		if (fontU_X == null)	{ this.fontU_X = new Font("Courier", Font.PLAIN, 10); }
		if (fontY == null)		{ this.fontY   = new Font("Courier", Font.PLAIN, 10); }
		if (fontX == null)		{ this.fontX   = new Font("Courier", Font.PLAIN, 10); }
		if (fontB == null)		{ this.fontB   = new Font("Courier", Font.PLAIN, 10); }
		if (fontB_S == null)	{ this.fontB_S = new Font("Courier", Font.PLAIN, 10); }

		// �t�H??�g�J??�[null�΍�  �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
		if (fontColorH == null)	{this.fontColorH = Color.BLACK;}
		if (fontColorS == null)	{this.fontColorS = Color.BLACK;}
		if (fontColorU_Y == null)	{this.fontColorU_Y = Color.BLACK;}
		if (fontColorU_X == null)	{this.fontColorU_X = Color.BLACK;}
		if (fontColorY == null)	{this.fontColorY = Color.BLACK;}
		if (fontColorX == null)	{this.fontColorX = Color.BLACK;}
		if (fontColorB == null)	{this.fontColorB = Color.BLACK;}
		if (fontColorB_S == null)	{this.fontColorB_S = Color.BLACK;}

		// �c���ڂ���l�`�F�b�N
		if (data_label_Y.length <= 0 || data_label_Y == null) {
			data_label_Y = new String[1];
			data_label_Y[0] = "";
		}
		// �����ڂ���l�`�F�b�N
		if (data_label_X.length <= 0 || data_label_X == null) {
			data_label_X = new String[1];
			data_label_X[0] = "";
		}
		// �o�[�̋�؂�??�l�`�F�b�N
		if (data_label_X_Sub.length <= 0 || data_label_X_Sub == null) {
			data_label_X_Sub = new String[1];
			data_label_X_Sub[0] = "";
		}

	}

	/**********************************************************
	 * �O??�t�o�[�̃t�H??�g�ݒ���s���܂�
	 *
	 * @param g         �O??�t�B�N�X
	 * @param font      �t�H??�g
	 * @param fontColor �t�H??�g�J??�[
	 **********************************************************/
	private void setStyle(Graphics g, Font font, Color fontColor) {
		
		g.setColor(fontColor);	// �t�H??�g�̐F�ݒ�
		g.setFont(font);		// �t�H??�g�̐ݒ�
	}
}
