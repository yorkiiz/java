/********************************************************************************/
/*  System :             WCFM 3.0                                               */
/*  File Name :          GraphMakerCommonDataSet.java                           */
/*  File Description :   �O���t���[�J�[�ėp�f�[�^�Z�b�g�N���X                   */
/*                                                                              */
/*  Classes :            GraphMakerCommonDataSet                                */
/*   DataColumn                                                                 */
/*                                                                              */
/*  5100-0317 (C) Copyright IBMJapan Industrial Solutions, Co., LTD. 2004       */
/*            (C) Copyright IBM Corp. 2004                                      */
/*                                                                              */
/*  Modification History :                                                      */
/*    Date        Level  Modified By       Description                          */
/*    ----------  -----  ----------------  -----------------------------        */
/*    11/01/2004  S000   T.Miyazaki        First Version                        */
/*                                                                              */
/********************************************************************************/

package com.ibm.lcd.cfm.monitor.util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**********************************************************
 * �O���t���[�J�[�ėp�f�[�^�Z�b�g�N���X
 **********************************************************/
public class GraphMakerCommonDataSet {

    /** �O���t�`��ʒu�̌��_�����X���W�I�t�Z�b�g */
    private int Graph_offset_X;
	/** �O���t�`��ʒu�̌��_�����Y���W�I�t�Z�b�g */
	private int Graph_offset_Y;
    /** �O���t�̑傫��(����)                 */
    private int Graph_Height;
    /** �o�[��100%(MAX�l)�ƍl����l          */
    private int maxValue;

    /** �w�b�_�[�^�C�g������                 */
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

    /** �c���̕�����                         */
    private int Graph_Devide;
    /** �c���̖ڐ���Ԃ̒l                   */
    private int Graph_Devide_Height;
    /** �c����1�������̍���                  */
    private int Y_Tick_Height;
    
    /** �c���̖ڐ���(���l)�̈ʒu�̒����l     */
    private int Graph_Width_Detail;

    /** �O���t�̉���                         */
    private int Graph_Width;
    /** �����̕�����                         */
    private int X_Tick_Width;
    /** �����̖ڐ���Ԃ̒l                   */
    private int Graph_Devide_Width;
	/** �����̖ڐ���(�T�u)�̏㉺�ʒu�����l   */
	private int XLabelSubDetail;

    /** �O���t�o�[�̈ʒu�����l               */
    private int Graph_Bar_Detail;
    /** �O���t�o�[�̑���                     */
    private int Graph_Bar_Thick;

    /** �w�b�_�[�t�H���g                     */
    private Font fontH;
    /** �T�u�X�e�[�^�X�t�H���g               */
    private Font fontS;
    /** Y���P�ʃt�H���g                      */
    private Font fontU_Y;
    /** X���P�ʃt�H���g                      */
    private Font fontU_X;
    /** �c���t�H���g                         */
    private Font fontY;
    /** �����t�H���g                         */
    private Font fontX;
    /** �o�[�t�H���g                         */
    private Font fontB;
    /** �o�[�̒l�t�H���g                     */
    private Font fontB_S;

    /** �w�b�_�[�t�H���g�J���[               */
    private Color fontColorH;
    /** �T�u�X�e�[�^�X�t�H���g�J���[         */
    private Color fontColorS;
    /** Y���P�ʃt�H���g�J���[                */
    private Color fontColorU_Y;
    /** X���P�ʃt�H���g�J���[                */
    private Color fontColorU_X;
    /** �c���t�H���g�J���[                   */
    private Color fontColorY;
    /** �����t�H���g�J���[                   */
    private Color fontColorX;
    /** �o�[�t�H���g�J���[                   */
    private Color fontColorB;
    /** �o�[�̒l�t�H���g�J���[               */
    private Color fontColorB_S;

    /** �c���ڐ���L���t���O                 */
    private boolean isScaleY;
    /** �c���ڐ��蒲���l(�����ō��E�ɑJ��)   */
    private int scaleDetailY;
    /** Y�����ڍő�\��������                */
    private int maxScaleLengthY;
    /** ���l�E�l�߃t���O(true:���Afalse:�E)  */
    private boolean isHeadOrTail;

    /** �O���t�o�[�̒l�\��/��\���t���O      */
    private boolean isGraph_Bar_Status;

    /** �c���ڂ���l(index 0�̓_�~�[)        */
    private String[] data_label_Y = {};
    /** �����ڂ���l(index 0�̓_�~�[)        */
    private String[] data_label_X = {};
	/** �����ڂ���l�T�u(index 0�̓_�~�[)    */
	private String[] data_label_X_Sub = {};
    /** �o�[�̋�؂萔                       */
//    private int[] data_Div = {};
    /** X����i���C���ʒu�㉺�������l        */
    private int lineDetailXTop;

    /** ��؂���Ԋu�̔������l               */
    private int detailDecade;
	/** ��؂���ʒu�̔������l               */
    private int detailState;

    /** �O���t�o�[List                       */
    private List list       = null;
    /** �O���t�o�[�ڍ�List                   */
    private List detailList = null;

    /** �R���X�g���N�^ */
    public GraphMakerCommonDataSet() {
        // List�̏�����
        list       = new ArrayList(0);
        // �O���t�o�[�ڍ�List�̏�����(�s��Ȃ�)
        detailList = new ArrayList(0);
    }


    /**********************************************************
     * �O���t�`��ʒu�̌��_�����X���W�I�t�Z�b�g�̃Z�b�g
     * @param  int �O���t�`��ʒu�̌��_����̃I�t�Z�b�g
     * @return �Ȃ�
     **********************************************************/
    public void setGraphOffset_X(int Graph_offset_X) {
        this.Graph_offset_X = Graph_offset_X;
    }

    /**********************************************************
     * �O���t�`��ʒu�̌��_�����X���W�I�t�Z�b�g�̎擾
     * @return �O���t�`��ʒu�̌��_����̃I�t�Z�b�g
     **********************************************************/
    public int getGraphOffset_X() {
        return this.Graph_offset_X;
    }

	/**********************************************************
	 * �O���t�`��ʒu�̌��_�����Y���W�I�t�Z�b�g�̃Z�b�g
	 * @param  int �O���t�`��ʒu�̌��_����̃I�t�Z�b�g
	 * @return �Ȃ�
	 **********************************************************/
	public void setGraphOffset_Y(int Graph_offset_Y) {
		this.Graph_offset_Y = Graph_offset_Y;
	}

	/**********************************************************
	 * �O���t�`��ʒu�̌��_�����Y���W�I�t�Z�b�g�̎擾
	 * @return �O���t�`��ʒu�̌��_����̃I�t�Z�b�g
	 **********************************************************/
	public int getGraphOffset_Y() {
		return this.Graph_offset_Y;
	}
	
    /**********************************************************
     * �O���t�̑傫��(����)�̃Z�b�g
     *
     * @param  int �O���t�̑傫��(����)
     * @return �Ȃ�
     **********************************************************/
    public void setGraphHeight(int Graph_Height) {
        this.Graph_Height = Graph_Height;
    }

    /**********************************************************
     * �O���t�̑傫��(����)�̎擾
     *
     * @return �O���t�̑傫��(����)
     **********************************************************/
    public int getGraphHeight() {
        return this.Graph_Height;
    }

    /**********************************************************
     * �o�[��100%(MAX�l)�ƍl����l�̃Z�b�g
     *
     * @param  int �o�[��100%(MAX�l)�ƍl����l
     * @return �Ȃ�
     **********************************************************/
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**********************************************************
     * �o�[��100%(MAX�l)�ƍl����l�̎擾
     *
     * @return �o�[��100%(MAX�l)�ƍl����l
     **********************************************************/
    public int getMaxValue() {
        return this.maxValue;
    }

    /**********************************************************
     * �w�b�_�[�^�C�g�����̂̃Z�b�g
     *
     * @param  String �w�b�_�[�^�C�g������
     * @return �Ȃ�
     **********************************************************/
    public void setHeaderName(String Header_Name) {
        this.Header_Name = Header_Name;
    }

    /**********************************************************
     * �w�b�_�[�^�C�g�����̂̎擾
     *
     * @return �w�b�_�[�^�C�g������
     **********************************************************/
    public String getHeaderName() {
        return this.Header_Name;
    }

    /**********************************************************
     * �w�b�_�[�ʒu(����)�̃Z�b�g
     *
     * @param  int �w�b�_�[�ʒu(����)
     * @return �Ȃ�
     **********************************************************/
    public void setHeaderWidth(int Header_Width) {
        this.Header_Width = Header_Width;
    }

    /**********************************************************
     * �w�b�_�[�ʒu(����)�̎擾
     *
     * @return �w�b�_�[�ʒu(����)
     **********************************************************/
    public int getHeaderWidth() {
        return this.Header_Width;
    }

    /**********************************************************
     * �w�b�_�[�ʒu(�c��)�̃Z�b�g
     *
     * @param  int �w�b�_�[�ʒu(�c��)
     * @return �Ȃ�
     **********************************************************/
    public void setHeaderHeight(int Header_Height) {
        this.Header_Height = Header_Height;
    }

    /**********************************************************
     * �w�b�_�[�ʒu(�c��)�̎擾
     *
     * @return �w�b�_�[�ʒu(�c��)
     **********************************************************/
    public int getHeaderHeight() {
        return this.Header_Height;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X���̂̃Z�b�g
     *
     * @param  String �T�u�X�e�[�^�X����
     * @return �Ȃ�
     **********************************************************/
    public void setSubStatusName(String Sub_Status_Name) {
        this.Sub_Status_Name = Sub_Status_Name;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X���̂̎擾
     *
     * @return �T�u�X�e�[�^�X����
     **********************************************************/
    public String getSubStatusName() {
        return this.Sub_Status_Name;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�ʒu(����)�̃Z�b�g
     *
     * @param  int �T�u�X�e�[�^�X�ʒu(����)
     * @return �Ȃ�
     **********************************************************/
    public void setSubStatusWidth(int Sub_Status_Width) {
        this.Sub_Status_Width = Sub_Status_Width;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�ʒu(����)�̎擾
     *
     * @return �T�u�X�e�[�^�X�ʒu(����)
     **********************************************************/
    public int getSubStatusWidth() {
        return this.Sub_Status_Width;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�ʒu(�c��)�̃Z�b�g
     *
     * @param  int �T�u�X�e�[�^�X�ʒu(�c��)
     * @return �Ȃ�
     **********************************************************/
    public void setSubStatusHeight(int Sub_Status_Height) {
        this.Sub_Status_Height = Sub_Status_Height;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�ʒu(�c��)�̎擾
     *
     * @return �T�u�X�e�[�^�X�ʒu(�c��)
     **********************************************************/
    public int getSubStatusHeight() {
        return this.Sub_Status_Height;
    }

    /**********************************************************
     * X���P�ʖ��̂̃Z�b�g
     *
     * @param  String X���P�ʖ���
     * @return �Ȃ�
     **********************************************************/
    public void setXUnitName(String X_Unit_Name) {
        this.X_Unit_Name = X_Unit_Name;
    }

    /**********************************************************
     * X���P�ʖ��̂̎擾
     *
     * @return X���P�ʖ���
     **********************************************************/
    public String getXUnitName() {
        return this.X_Unit_Name;
    }

    /**********************************************************
     * X���P�ʈʒu(����)�̃Z�b�g
     *
     * @param  int X���P�ʈʒu(����)
     * @return �Ȃ�
     **********************************************************/
    public void setXUnitWidth(int X_Unit_Width) {
        this.X_Unit_Width = X_Unit_Width;
    }

    /**********************************************************
     * X���P�ʈʒu(����)�̎擾
     *
     * @return X���P�ʈʒu(����)
     **********************************************************/
    public int getXUnitWidth() {
        return this.X_Unit_Width;
    }

    /**********************************************************
     * X���P�ʈʒu(�c��)�̃Z�b�g
     *
     * @param  int X���P�ʈʒu(�c��)
     * @return �Ȃ�
     **********************************************************/
    public void setXUnitHeight(int X_Unit_Height) {
        this.X_Unit_Height = X_Unit_Height;
    }

    /**********************************************************
     * X���P�ʈʒu(�c��)�̎擾
     *
     * @return X���P�ʈʒu(�c��)
     **********************************************************/
    public int getXUnitHeight() {
        return this.X_Unit_Height;
    }

    /**********************************************************
     * Y���P�ʖ��̂̃Z�b�g
     *
     * @param  String Y���P�ʖ���
     * @return �Ȃ�
     **********************************************************/
    public void setYUnitName(String Y_Unit_Name) {
        this.Y_Unit_Name = Y_Unit_Name;
    }

    /**********************************************************
     * Y���P�ʖ��̂̎擾
     *
     * @return Y���P�ʖ���
     **********************************************************/
    public String getYUnitName() {
        return this.Y_Unit_Name;
    }

    /**********************************************************
     * Y���P�ʈʒu(����)�̃Z�b�g
     *
     * @param  int Y���P�ʈʒu(����)
     * @return �Ȃ�
     **********************************************************/
    public void setYUnitWidth(int Y_Unit_Width) {
        this.Y_Unit_Width = Y_Unit_Width;
    }

    /**********************************************************
     * Y���P�ʈʒu(����)�̎擾
     *
     * @return Y���P�ʈʒu(����)
     **********************************************************/
    public int getYUnitWidth() {
        return this.Y_Unit_Width;
    }

    /**********************************************************
     * Y���P�ʈʒu(�c��)�̃Z�b�g
     *
     * @param  int Y���P�ʈʒu(�c��)
     * @return �Ȃ�
     **********************************************************/
    public void setYUnitHeight(int Y_Unit_Height) {
        this.Y_Unit_Height = Y_Unit_Height;
    }

    /**********************************************************
     * Y���P�ʈʒu(�c��)�̎擾
     *
     * @return Y���P�ʈʒu(�c��)
     **********************************************************/
    public int getYUnitHeight() {
        return this.Y_Unit_Height;
    }

    /**********************************************************
     * �c���̕������̃Z�b�g
     *
     * @param  int �c���̕�����
     * @return �Ȃ�
     **********************************************************/
    public void setGraphDevide(int Graph_Devide) {
        this.Graph_Devide = Graph_Devide;
    }

    /**********************************************************
     * �c���̕������̎擾
     *
     * @return �c���̕�����
     **********************************************************/
    public int getGraphDevide() {
        return this.Graph_Devide;
    }

    /**********************************************************
     * �c���̖ڐ���Ԃ̒l�̃Z�b�g
     *
     * @param  int �c���̖ڐ���Ԃ̒l
     * @return �Ȃ�
     **********************************************************/
    public void setGraphDevideHeight(int Graph_Devide_Height) {
        this.Graph_Devide_Height = Graph_Devide_Height;
    }

    /**********************************************************
     * �c���̖ڐ���Ԃ̒l�̎擾
     *
     * @return �c���̖ڐ���Ԃ̒l
     **********************************************************/
    public int getGraphDevideHeight() {
        return this.Graph_Devide_Height;
    }

    /**********************************************************
     * �c����1�������̍����̃Z�b�g
     *
     * @param  int �c����1�������̍���
     * @return �Ȃ�
     **********************************************************/
    public void setYTickHeight(int Y_Tick_Height) {
        this.Y_Tick_Height = Y_Tick_Height;
    }

    /**********************************************************
     * �c����1�������̍����̎擾
     *
     * @return �c����1�������̍���
     **********************************************************/
    public int getYTickHeight() {
        return this.Y_Tick_Height;
    }

    /**********************************************************
     * �����̖ڐ���(���l)�̈ʒu�̒����l�̃Z�b�g
     *
     * @param  int �����̖ڐ���(���l)�̈ʒu�̒����l
     * @return �Ȃ�
     **********************************************************/
    public void setGraphWidthDetail(int Graph_Width_Detail) {
        this.Graph_Width_Detail = Graph_Width_Detail;
    }

    /**********************************************************
     * �����̖ڐ���(���l)�̈ʒu�̒����l�̎擾
     *
     * @return �����̖ڐ���(���l)�̈ʒu�̒����l
     **********************************************************/
    public int getGraphWidthDetail() {
        return this.Graph_Width_Detail;
    }

    /**********************************************************
     * �O���t�̉����̃Z�b�g
     *
     * @param  int �O���t�̉���
     * @return �Ȃ�
     **********************************************************/
    public void setGraphWidth(int Graph_Width) {
        this.Graph_Width = Graph_Width;
    }

    /**********************************************************
     * �O���t�̉����̎擾
     *
     * @return �O���t�̉���
     **********************************************************/
    public int getGraphWidth() {
        return this.Graph_Width;
    }

    /**********************************************************
     * �����̕������̃Z�b�g
     *
     * @param  int �����̕�����
     * @return �Ȃ�
     **********************************************************/
    public void setXTickWidth(int X_Tick_Width) {
        this.X_Tick_Width= X_Tick_Width;
    }

    /**********************************************************
     * �����̕������̎擾
     *
     * @return �����̕�����
     **********************************************************/
    public int getXTickWidth() {
        return this.X_Tick_Width;
    }

	/**********************************************************
	 * �����̖ڐ���Ԃ̒l�̃Z�b�g
	 *
	 * @param  int �����̖ڐ���Ԃ̒l
	 * @return �Ȃ�
	 **********************************************************/
	public void setGraphDevideWidth(int Graph_Devide_Width) {
		this.Graph_Devide_Width = Graph_Devide_Width;
	}

	/**********************************************************
	 * �����̖ڐ���Ԃ̒l�̎擾
	 *
	 * @return �����̖ڐ���Ԃ̒l
	 **********************************************************/
	public int getGraphDevideWidth() {
		return this.Graph_Devide_Width;
	}

	/**********************************************************
	 * �����̖ڐ���(�T�u)�̏㉺�ʒu�����l�̃Z�b�g
	 *
	 * @return �����̖ڐ���Ԃ̒l
	 **********************************************************/
	public void setXLabelSubDetail(int XLabelSubDetail) {
		this.XLabelSubDetail = XLabelSubDetail;
	}

	/**********************************************************
	 * �����̖ڐ���(�T�u)�̏㉺�ʒu�����l�̎擾
	 *
	 * @return �����̖ڐ���Ԃ̒l
	 **********************************************************/
	public int getXLabelSubDetail() {
		return this.XLabelSubDetail;
	}

    /**********************************************************
     * �O���t�o�[�̈ʒu�����l�̃Z�b�g
     *
     * @param  int �O���t�o�[�̈ʒu�����l
     * @return �Ȃ�
     **********************************************************/
    public void setGraphBarDetail(int Graph_Bar_Detail) {
        this.Graph_Bar_Detail = Graph_Bar_Detail;
    }

    /**********************************************************
     * �O���t�o�[�̈ʒu�����l�̎擾
     *
     * @return �O���t�o�[�̈ʒu�����l
     **********************************************************/
    public int getGraphBarDetail() {
        return this.Graph_Bar_Detail;
    }

    /**********************************************************
     * �O���t�o�[�̑����̃Z�b�g
     *
     * @param  int �O���t�o�[�̑���
     * @return �Ȃ�
     **********************************************************/
    public void setGraphBarThick(int Graph_Bar_Thick) {
        this.Graph_Bar_Thick = Graph_Bar_Thick;
    }

    /**********************************************************
     * �O���t�o�[�̑����̎擾
     *
     * @return �O���t�o�[�̑���
     **********************************************************/
    public int getGraphBarThick() {
        return this.Graph_Bar_Thick;
    }

    /**********************************************************
     * �w�b�_�[�t�H���g�̃Z�b�g
     *
     * @param  Font �w�b�_�[�t�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontH(Font fontH) {
        this.fontH = fontH;
    }

    /**********************************************************
     * �w�b�_�[�t�H���g�̎擾
     *
     * @return �w�b�_�[�t�H���g
     **********************************************************/
    public Font getFontH() {
        return this.fontH;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�t�H���g�̃Z�b�g
     *
     * @param  fontS �T�u�X�e�[�^�X�t�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontS(Font fontS) {
        this.fontS = fontS;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�t�H���g�̎擾
     *
     * @return �T�u�X�e�[�^�X�t�H���g
     **********************************************************/
    public Font getFontS() {
        return this.fontS;
    }

    /**********************************************************
     * Y���P�ʃt�H���g�̃Z�b�g
     *
     * @param  Font Y���P�ʃt�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontU_Y(Font fontU_Y) {
        this.fontU_Y = fontU_Y;
    }

    /**********************************************************
     * Y���P�ʃt�H���g�̎擾
     *
     * @return Y���P�ʃt�H���g
     **********************************************************/
    public Font getFontU_Y() {
        return this.fontU_Y;
    }

    /**********************************************************
     * X���P�ʃt�H���g�̃Z�b�g
     *
     * @param  Font X���P�ʃt�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontU_X(Font fontU_X) {
        this.fontU_X = fontU_X;
    }

    /**********************************************************
     * X���P�ʃt�H���g�̎擾
     *
     * @return X���P�ʃt�H���g
     **********************************************************/
    public Font getFontU_X() {
        return this.fontU_X;
    }

    /**********************************************************
     * �c���t�H���g�̃Z�b�g
     *
     * @param  Font �c���t�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontY(Font fontY) {
        this.fontY = fontY;
    }

    /**********************************************************
     * �c���t�H���g�̎擾
     *
     * @return �c���t�H���g
     **********************************************************/
    public Font getFontY() {
        return this.fontY;
    }

    /**********************************************************
     * �����t�H���g�̃Z�b�g
     *
     * @param  Font �����t�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontX(Font fontX) {
        this.fontX = fontX;
    }

    /**********************************************************
     * �����t�H���g�̎擾
     *
     * @return �����t�H���g
     **********************************************************/
    public Font getFontX() {
        return this.fontX;
    }

    /**********************************************************
     * �o�[�t�H���g�̃Z�b�g
     *
     * @param  Font �o�[�t�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontB(Font fontB) {
        this.fontB = fontB;
    }

    /**********************************************************
     * �o�[�t�H���g�̎擾
     *
     * @return �o�[�t�H���g
     **********************************************************/
    public Font getFontB() {
        return this.fontB;
    }

    /**********************************************************
     * �o�[�̒l�t�H���g�̃Z�b�g
     *
     * @param  Font �o�[�̒l�t�H���g
     * @return �Ȃ�
     **********************************************************/
    public void setFontB_S(Font fontB_S) {
        this.fontB_S = fontB_S;
    }

    /**********************************************************
     * �o�[�̒l�t�H���g�̎擾
     *
     * @return �o�[�̒l�t�H���g
     **********************************************************/
    public Font getFontB_S() {
        return this.fontB_S;
    }

    /**********************************************************
     * �w�b�_�[�t�H���g�J���[�̃Z�b�g
     *
     * @param  Color �w�b�_�[�t�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorH(Color fontColorH) {
        this.fontColorH = fontColorH;
    }

    /**********************************************************
     * �w�b�_�[�t�H���g�J���[�̎擾
     *
     * @return �w�b�_�[�t�H���g�J���[
     **********************************************************/
    public Color getFontColorH() {
        return this.fontColorH;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�t�H���g�J���[�̃Z�b�g
     *
     * @param  Color �T�u�X�e�[�^�X�t�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorS(Color fontColorS) {
        this.fontColorS = fontColorS;
    }

    /**********************************************************
     * �T�u�X�e�[�^�X�t�H���g�J���[�̎擾
     *
     * @return �T�u�X�e�[�^�X�t�H���g�J���[
     **********************************************************/
    public Color getFontColorS() {
        return this.fontColorS;
    }

    /**********************************************************
     * Y���P�ʃt�H���g�J���[�̃Z�b�g
     *
     * @param  Color Y���P�ʃt�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorU_Y(Color fontColorU_Y) {
        this.fontColorU_Y = fontColorU_Y;
    }

    /**********************************************************
     * Y���P�ʃt�H���g�J���[�̎擾
     *
     * @return Y���P�ʃt�H���g�J���[
     **********************************************************/
    public Color getFontColorU_Y() {
        return this.fontColorU_Y;
    }

    /**********************************************************
     * X���P�ʃt�H���g�J���[�̃Z�b�g
     *
     * @param  Color X���P�ʃt�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorU_X(Color fontColorU_X) {
        this.fontColorU_X = fontColorU_X;
    }

    /**********************************************************
     * X���P�ʃt�H���g�J���[�̎擾
     *
     * @return X���P�ʃt�H���g�J���[
     **********************************************************/
    public Color getFontColorU_X() {
        return this.fontColorU_X;
    }

    /**********************************************************
     * �c���t�H���g�J���[�̃Z�b�g
     *
     * @param  Color �c���t�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorY(Color fontColorY) {
        this.fontColorY = fontColorY;
    }

    /**********************************************************
     * �c���t�H���g�J���[�̎擾
     *
     * @return �c���t�H���g�J���[
     **********************************************************/
    public Color getFontColorY() {
        return this.fontColorY;
    }

    /**********************************************************
     * �����t�H���g�J���[�̃Z�b�g
     *
     * @param  Color �����t�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorX(Color fontColorX) {
        this.fontColorX = fontColorX;
    }

    /**********************************************************
     * �����t�H���g�J���[�̎擾
     *
     * @return �����t�H���g�J���[
     **********************************************************/
    public Color getFontColorX() {
        return this.fontColorX;
    }

    /**********************************************************
     * �o�[�t�H���g�J���[�̃Z�b�g
     *
     * @param  Color �o�[�t�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorB(Color fontColorB) {
        this.fontColorB = fontColorB;
    }

    /**********************************************************
     * �o�[�t�H���g�J���[�̎擾
     *
     * @return �o�[�t�H���g�J���[
     **********************************************************/
    public Color getFontColorB() {
        return this.fontColorB;
    }

    /**********************************************************
     * �o�[�̒l�t�H���g�J���[�̃Z�b�g
     *
     * @param  Color �o�[�̒l�t�H���g�J���[
     * @return �Ȃ�
     **********************************************************/
    public void setFontColorB_S(Color fontColorB_S) {
        this.fontColorB_S = fontColorB_S;
    }

    /**********************************************************
     * �o�[�̒l�t�H���g�J���[�̎擾
     *
     * @return �o�[�̒l�t�H���g�J���[
     **********************************************************/
    public Color getFontColorB_S() {
        return this.fontColorB_S;
    }

    /**********************************************************
     * �c���ڐ���L���t���O�̃Z�b�g
     *
     * @param  boolean �c���ڐ���L���t���O
     * @return �Ȃ�
     **********************************************************/
    public void setIsScaleY(boolean isScaleY) {
        this.isScaleY = isScaleY;
    }

    /**********************************************************
     * �c���ڐ���L���t���O�̎擾
     *
     * @return �o�[�̒l�t�H���g�J���[
     **********************************************************/
    public boolean getIsScaleY() {
        return this.isScaleY;
    }

    /**********************************************************
     * �c���ڐ��蒲���l(�����ō��E�ɑJ��)�̃Z�b�g
     *
     * @param  int �c���ڐ��蒲���l(�����ō��E�ɑJ��)
     * @return �Ȃ�
     **********************************************************/
    public void setScaleDetailY(int scaleDetailY) {
        this.scaleDetailY = scaleDetailY;
    }

    /**********************************************************
     * �c���ڐ��蒲���l(�����ō��E�ɑJ��)�̎擾
     *
     * @return �c���ڐ��蒲���l(�����ō��E�ɑJ��)
     **********************************************************/
    public int getScaleDetailY() {
        return this.scaleDetailY;
    }

    /**********************************************************
     * Y�����ڍő�\���������̃Z�b�g
     *
     * @param  int Y�����ڍő�\��������
     * @return �Ȃ�
     **********************************************************/
    public void setMaxScaleLengthY(int maxScaleLengthY) {
        this.maxScaleLengthY = maxScaleLengthY;
    }

    /**********************************************************
     * Y�����ڍő�\���������̎擾
     *
     * @return Y�����ڍő�\��������
     **********************************************************/
    public int getMaxScaleLengthY() {
        return this.maxScaleLengthY;
    }

    /**********************************************************
     * ���l�E�l�߃t���O(true:���Afalse:�E)�̃Z�b�g
     *
     * @param  boolean ���l�E�l�߃t���O(true:���Afalse:�E)
     * @return �Ȃ�
     **********************************************************/
    public void setIsHeadOrTail(boolean isHeadOrTail) {
        this.isHeadOrTail = isHeadOrTail;
    }

    /**********************************************************
     * ���l�E�l�߃t���O(true:���Afalse:�E)�̎擾
     *
     * @return ���l�E�l�߃t���O(true:���Afalse:�E)
     **********************************************************/
    public boolean getIsHeadOrTail() {
        return this.isHeadOrTail;
    }

    /**********************************************************
     * �O���t�o�[�̒l�\��/��\���t���O�̃Z�b�g
     *
     * @param  boolean �O���t�o�[�̒l�\��/��\���t���O
     * @return �Ȃ�
     **********************************************************/
    public void setIsGraphBarStatus(boolean isGraph_Bar_Status) {
        this.isGraph_Bar_Status = isGraph_Bar_Status;
    }

    /**********************************************************
     * �O���t�o�[�̒l�\��/��\���t���O�̎擾
     *
     * @return �O���t�o�[�̒l�\��/��\���t���O
     **********************************************************/
    public boolean getIsGraphBarStatus() {
        return this.isGraph_Bar_Status;
    }

    /**********************************************************
     * �c���ڂ���l�̃Z�b�g<br>
     * index0�̓_�~�[�l�Ȃ̂ŋ󕶎���K���Z�b�g���Ă�������
     *
     * @param  String[] �c���ڂ���l
     * @return �Ȃ�
     **********************************************************/
    public void setDataLabelY(String[] data_label_Y) {
        this.data_label_Y = data_label_Y;
    }

    /**********************************************************
     * �c���ڂ���l�̎擾
     *
     * @return �c���ڂ���l
     **********************************************************/
    public String[] getDataLabelY() {
        return this.data_label_Y;
    }

    /**********************************************************
     * �����ڂ���l�̃Z�b�g<br>
     * ��)�����ŃZ�b�g�����z��̗v�f���� setDataDiv()�AsetDataLabelXSub()<br>
     * �̗v�f���Ɠ����łȂ��Ă͂Ȃ�܂���B
     *
     * @param  String[] �����ڂ���l
     * @return �Ȃ�
     **********************************************************/
    public void setDataLabelX(String[] data_label_X) {
        this.data_label_X = data_label_X;
    }

    /**********************************************************
     * �����ڂ���l�̎擾
     *
     * @return �����ڂ���l
     **********************************************************/
    public String[] getDataLabelX() {
        return this.data_label_X;
    }
    
	/**********************************************************
	 * �����ڂ���l(�T�u)�̃Z�b�g<br>
     * ��)�����ŃZ�b�g�����z��̗v�f���� setDataLabelX()�AsetDataDiv()<br>
     * �̗v�f���Ɠ����łȂ��Ă͂Ȃ�܂���B
	 *
	 * @param  String[] �����ڂ���l
	 * @return �Ȃ�
	 **********************************************************/
	public void setDataLabelXSub(String[] data_label_X_Sub) {
		this.data_label_X_Sub = data_label_X_Sub;
	}

	/**********************************************************
	 * �����ڂ���l(�T�u)�̎擾
	 *
	 * @return �����ڂ���l
	 **********************************************************/
	public String[] getDataLabelXSub() {
		return this.data_label_X_Sub;
	}

    /**********************************************************
     * X�������̏�i�̐������l
     *
     * @param int X�������̏�i�̐������l
     **********************************************************/
    public void setLineDetailXTop(int lineDetailXTop) {
        this.lineDetailXTop = lineDetailXTop;
    }

    /**********************************************************
     * X�������̏�i�̐������l�̎擾
     *
     * @return X�������̏�i�̐������l
     **********************************************************/
    public int getLineDetailXTop() {
        return lineDetailXTop;
    }

	/**********************************************************
	 * ��؂���Ԋu�̔������l�̃Z�b�g<br>
	 * �g�p�̐����͂��܂���B
	 *
	 * @param int ��؂���Ԋu�̔������l
	 **********************************************************/
	public void setDetailDecade(int detailDecade) {
		this.detailDecade = detailDecade;
	}

	/**********************************************************
	 * ��؂���Ԋu�̔������l�̎擾<br>
	 * �g�p�̐����͂��܂���B
	 * 
	 * @return ��؂���Ԋu�̔������l
	 **********************************************************/
	public int getDetailDecade() {
		return detailDecade;
	}
	
	/**********************************************************
	 * ��؂���ʒu�̔������l�̃Z�b�g<br>
	 * �g�p�̐����͂��܂���B
	 *
	 * @param int ��؂���Ԋu�̔������l
	 **********************************************************/
	public void setDetailState(int detailState) {
		this.detailState = detailState;
	}

	/**********************************************************
	 * ��؂���ʒu�̔������l�̎擾<br>
	 * �g�p�̐����͂��܂���B
	 * 
	 * @return ��؂���Ԋu�̔������l
	 **********************************************************/
	public int getDetailState() {
		return detailState;
	}

    /**********************************************************
     *
     *
     * @return �Ȃ�
     **********************************************************/
    public void setList(Object obj) {
        if (obj != null) {
            this.list.add(obj);
        }
    }

    /**********************************************************
     *
     *
     * @return LinkedList
     **********************************************************/
    public List getList() {
        return this.list;
    }

    /**********************************************************
     *
     *
     * @return �Ȃ�
     **********************************************************/
    public void setDetailList(Object obj) {
        if (obj != null) {
            this.detailList.add(obj);
        }
    }

    /**********************************************************
     *
     *
     * @return LinkedList
     **********************************************************/
    public List getDetailList() {
        return this.detailList;
    }


    /**********************************************************
     * ���X�g�̃T�C�Y��Ԃ��܂�
     *
     * @return int
     **********************************************************/
    public int getListSize() {
        return this.list.size();
    }

    /**********************************************************
     * ���X�g�̃T�C�Y��Ԃ��܂�
     *
     * @return int
     **********************************************************/
    public int getDetailListSize() {
        return this.detailList.size();
    }

    /***********************************************************
     * �f�[�^�N���A
     *
     ***********************************************************/
    public void clearList() {
        if (null != list) {
            this.list.clear();
        }
    }

    /***********************************************************
     * �f�[�^�N���A
     *
     ***********************************************************/
    public void clearDetailList() {
        if (null != list) {
//			this.detailList.clear();
			this.detailList = new ArrayList(0);
        }
    }


    /********************************************************************************/
    /*  System :             WCFM 3.0                                               */
    /*  File Name :          GraphMakerCommonDetailDataSet.java                     */
    /*  File Description :   �O���t���[�J�[�ėp�f�[�^�Z�b�g(�o�[�ڍ�)�C���i�[�N���X */
    /*                                                                              */
    /*  Classes :            GraphMakerCommonDetailDataSet                          */
    /*   DataColumn                                                                 */
    /*                                                                              */
    /*  5100-0317 (C) Copyright IBMJapan Industrial Solutions, Co., LTD. 2004       */
    /*            (C) Copyright IBM Corp. 2004                                      */
    /*                                                                              */
    /*  Modification History :                                                      */
    /*    Date        Level  Modified By       Description                          */
    /*    ----------  -----  ----------------  -----------------------------        */
    /*    11/01/2004  S000   T.Miyazaki        First Version                        */
    /*                                                                              */
    /********************************************************************************/

    /**********************************************************
     * �O���t���[�J�[�ėp�f�[�^�Z�b�g(�o�[�ڍ�)�C���i�[�N���X
     **********************************************************/
    public static class GraphMakerCommonDetailDataSet {

        /** �O���t�o�[��ɕ\������e�L�X�g������        */
        private String text     = null;

        /** �O���t�o�[��ɕ\������e�L�X�g�̐F          */
        private Color textColor = null;

        /** �O���t�o�[�̐F                              */
        private Color barColor  = null;

        /** �O���t�o�[��100���ɑ΂���l�̊���(�o�[�̒l) */
        private int rate       = 0;
		
        /**********************************************************
         * �O���t�o�[��ɕ\������e�L�X�g������̃Z�b�g
         *
         * @return String �O���t�o�[��ɕ\������e�L�X�g������
         **********************************************************/
        public void setText(String text) {
            this.text = text;
        }

        /**********************************************************
         * �O���t�o�[��ɕ\������e�L�X�g������̎擾
         *
         * @return �O���t�o�[��ɕ\������e�L�X�g������
         **********************************************************/
        public String getText() {
            return this.text;
        }

        /**********************************************************
         * �O���t�o�[��ɕ\������e�L�X�g�̐F�̃Z�b�g
         *
         * @param Color �O���t�o�[��ɕ\������e�L�X�g�̐F
         **********************************************************/
        public void setTextColor(Color textColor) {
            this.textColor = textColor;
        }

        /**********************************************************
         * �O���t�o�[��ɕ\������e�L�X�g�̐F�̎擾
         *
         * @return �O���t�o�[��ɕ\������e�L�X�g������
         **********************************************************/
        public Color getTextColor() {
            return this.textColor;
        }

        /**********************************************************
         * �O���t�o�[�̐F�̃Z�b�g
         *
         * @param Color �O���t�o�[�̐F
         **********************************************************/
        public void setBarColor(Color barColor) {
            this.barColor = barColor;
        }

        /**********************************************************
         * �O���t�o�[�̐F�̎擾
         *
         * @return �O���t�o�[�̐F
         **********************************************************/
        public Color getBarColor() {
            return this.barColor;
        }

        /**********************************************************
         * �O���t�o�[��100���ɑ΂���l�̊���(�o�[�̒l)�̃Z�b�g
         *
         * @param int �O���t�o�[��100���ɑ΂���l(�o�[�̒l)�̊���
         **********************************************************/
        public void setRate(int rate) {
            this.rate = rate;
        }

        /**********************************************************
         * �O���t�o�[��100���ɑ΂���l�̊���(�o�[�̒l)�̎擾
         *
         * @return �O���t�o�[��100���ɑ΂���l�̊���(�o�[�̒l)
         **********************************************************/
        public int getRate() {
            return rate;
        }
    }
}
