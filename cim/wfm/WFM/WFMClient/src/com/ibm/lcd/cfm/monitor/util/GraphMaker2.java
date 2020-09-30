package com.ibm.lcd.cfm.monitor.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GraphMaker2 extends Canvas {

	private static final long serialVersionUID = -612978320732769962L;

	final String CLASS_NAME = "GraphMaker2";

    final boolean DEBUG    = false;

    private int[] data_set;
    private String[] data_label;


    private int Graph_offset;
    private int Graph_Height;
    private int maxValue;

    private String Header_Name;
    private int Header_Width;
    private int Header_Height;

    private String Sub_Status_Name;
    private int Sub_Status_Width;
    private int Sub_Status_Height;

    private String X_Unit_Name;
    private int X_Unit_Width;
    private int X_Unit_Height;

    private String Y_Unit_Name;
    private int Y_Unit_Width;
    private int Y_Unit_Height;

    private int Graph_Devide;
    //private int Graph_Devide_Height;
    private int Y_Tick_Height;
    private int Graph_Height_Detail;

    private int Graph_Width;
    private int X_Tick_Width;
    private int Graph_Devide_Width;

    private int Graph_Bar_Detail;//-4
    private int Graph_Bar_Thick;//7

    private Font fontH;
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


    public GraphMaker2(int Graph_offset, int Graph_Height, int maxValue, int Graph_Devide, int Graph_Width, int Graph_Height_Detail,
                        int Graph_Bar_Detail, int Graph_Bar_Thick,
                       String Header_Name,     int Header_Width,     int Header_Height,
                       String Sub_Status_Name, int Sub_Status_Width, int Sub_Status_Height,
                       String X_Unit_Name,     int X_Unit_Width,     int X_Unit_Height,
                       String Y_Unit_Name,     int Y_Unit_Width,     int Y_Unit_Height,
                       Font fontH,   Color fontColorH,
                       Font fontS,   Color fontColorS,
                       Font fontU_Y, Color fontColorU_Y,
                       Font fontU_X, Color fontColorU_X,
                       Font fontY,   Color fontColorY,
                       Font fontX,   Color fontColorX,
                       Font fontB,   Color fontColorB,
                       Font fontB_S, Color fontColorB_S,
                       boolean isScaleY, int scaleDetailY, int maxScaleLengthY, boolean isHeadOrTail ,boolean isGraph_Bar_Status,
                       int[] data_set, String[] data_label){

        setDefault();

        this.Graph_offset        = Graph_offset;
        this.Graph_Height        = Graph_Height;
        this.maxValue            = maxValue;
        this.Graph_Devide        = Graph_Devide ;
        this.Graph_Width         = Graph_Width;
        this.Graph_Height_Detail = Graph_Height_Detail;
        // �O??�t�o�[�̈ʒu�����l
        this.Graph_Bar_Detail    = Graph_Bar_Detail;
        // �O??�t�o�[�̑���
        this.Graph_Bar_Thick     = Graph_Bar_Thick;
        // �w�b�_�[����
        this.Header_Name         = StringUtil.getCheckedValue(Header_Name, "");
        // �w�b�_�[�ʒu(����)
        this.Header_Width        = Header_Width;
        // �w�b�_�[�ʒu(�c��)
        this.Header_Height       = Header_Height;
        // �T�u�X�e�[�^�X
        this.Sub_Status_Name     = StringUtil.getCheckedValue(Sub_Status_Name, "");
        // �T�u�X�e�[�^�X�\���ʒu(����)
        this.Sub_Status_Width    = Sub_Status_Width;
        // �T�u�X�e�[�^�X�\���ʒu(�c��)
        this.Sub_Status_Height   = Sub_Status_Height;
        // X���P�ʖ���
        this.X_Unit_Name         = StringUtil.getCheckedValue(X_Unit_Name, "");
        // X���P�ʈʒu(����)
        this.X_Unit_Width        = X_Unit_Width;
        // X���P�ʈʒu(�c��)
        this.X_Unit_Height       = X_Unit_Height;
        // Y���P�ʖ���
        this.Y_Unit_Name         = StringUtil.getCheckedValue(Y_Unit_Name, "");
        // Y���P�ʈʒu(����)
        this.Y_Unit_Width        = Y_Unit_Width;
        // Y���P�ʈʒu(�c��)
        this.Y_Unit_Height       = Y_Unit_Height;
        // �w�b�_�[�t�H??�g
        this.fontH               = fontH;
        // �T�u�X�e�[�^�X�t�H??�g
        this.fontS               = fontS;
        // Y���P�ʃt�H??�g
        this.fontU_Y             = fontU_Y;
        // X���P�ʃt�H??�g
        this.fontU_X             = fontU_X;
        // �c���t�H??�g
        this.fontY               = fontY;
        // �����t�H??�g
        this.fontX               = fontX;
        // �o�[�t�H??�g
        this.fontB               = fontB;
        // �o�[�̒l�t�H??�g
        this.fontB_S             = fontB_S;
        // �w�b�_�[�t�H??�g�J??�[
        this.fontColorH          = fontColorH;
        // �T�u�X�e�[�^�X�t�H??�g�J??�[
        this.fontColorS          = fontColorS;
        // Y���P�ʃt�H??�g�J??�[
        this.fontColorU_Y        = fontColorU_Y;
        // X���P�ʃt�H??�g�J??�[
        this.fontColorU_X        = fontColorU_X;
        // �c���t�H??�g�J??�[
        this.fontColorY          = fontColorY;
        // �����t�H??�g�J??�[
        this.fontColorX          = fontColorX;
        // �o�[�t�H??�g�J??�[
        this.fontColorB          = fontColorB;
        // �o�[�̒l�t�H??�g�J??�[
        this.fontColorB_S        = fontColorB_S;
        // �����ڐ���L���t??�O
        this.isScaleY            = isScaleY;
        // �c���ڐ��蒲���l(??���ō��E�ɑJ��)
        this.scaleDetailY        = scaleDetailY;
        // Y��??�ڍő�\����????
        this.maxScaleLengthY     = maxScaleLengthY;
        // ���l�E�l�߃t??�O(true:���Afalse:�E)
        this.isHeadOrTail        = isHeadOrTail;
        // �O??�t�o�[�̒l�\��/��\���t??�O(true:�\���Afalse:��\��)
        this.isGraph_Bar_Status  = isGraph_Bar_Status;

        // �p????�[�^�̒l(index 0�̓_�~�[)
        this.data_set            = data_set;
        // �����ڂ���l(index 0�̓_�~�[)
        this.data_label          = data_label;

        // �c���̖ڐ���Ԃ̒l
        //this.Graph_Devide_Height = maxValue     / Graph_Devide;
        // �����̖ڐ���Ԃ̒l
        this.Graph_Devide_Width  = maxValue     / Graph_Devide;
        // �c����1��??����??��
        this.Y_Tick_Height       = Graph_Height / data_label.length;
        // �����̕�????
        this.X_Tick_Width       = (Graph_Width  / Graph_Devide) - Graph_offset;
    }

    private void setDefault(){
        this.data_set            = null;
        this.data_label          = null;
        this.Graph_offset        = 1;
        this.Graph_Height        = 1;
        this.maxValue            = 1;

        this.Header_Name         = "";
        this.Header_Width        = 1;
        this.Header_Height       = 1;

        this.Sub_Status_Name     = "";
        this.Sub_Status_Width    = 1;
        this.Sub_Status_Height   = 1;

        this.X_Unit_Name         = "";
        /** X���P�ʈʒu(����)                    */
        this.X_Unit_Width        = 1;
        /** X���P�ʈʒu(�c��)                    */
        this.X_Unit_Height       = 1;

        /** Y���P�ʖ���                          */
        this.Y_Unit_Name         = "";
        /** Y���P�ʈʒu(����)                    */
        this.Y_Unit_Width        = 1;
        /** Y���P�ʈʒu(�c��)                    */
        this.Y_Unit_Height       = 1;

        /** �c���̕�????                         */
        this.Graph_Devide        = 1;
        /** �c���̖ڐ���Ԃ̒l                   */
        //this.Graph_Devide_Height = 1;
        /** �c����1��??����??��                  */
        this.Y_Tick_Height       = 1;

        /** �o�[�̉�??                           */
        this.Graph_Width         = 1;
        /** �����̕�????                         */
        this.X_Tick_Width        = 1;
        /** �����̖ڐ���(??�l)�̈ʒu�̒����l     */
        this.Graph_Height_Detail = 1;
        /** �O??�t�o�[�̈ʒu�����l               */
        this.Graph_Bar_Detail    = 1;
        /** �O??�t�o�[�̑���                     */
        this.Graph_Bar_Detail    = 1;

        /** �w�b�_�[�t�H??�g                     */
        this.fontH               = null;
        /** �T�u�X�e�[�^�X�t�H??�g               */
        this.fontS               = null;
        /** Y���P�ʃt�H??�g                      */
        this.fontU_Y             = null;
        /** X���P�ʃt�H??�g                      */
        this.fontU_X             = null;
        /** �c���t�H??�g                         */
        this.fontY               = null;
        /** �����t�H??�g                         */
        this.fontX               = null;
        /** �o�[�t�H??�g                         */
        this.fontB               = null;
        /** �o�[�̒l�t�H??�g                     */
        this.fontB_S             = null;

        /** �w�b�_�[�t�H??�g�J??�[               */
        this.fontColorH          = null;
        /** �T�u�X�e�[�^�X�t�H??�g�J??�[         */
        this.fontColorS          = null;
        /** Y���P�ʃt�H??�g�J??�[                */
        this.fontColorU_Y        = null;
        /** X���P�ʃt�H??�g�J??�[                */
        this.fontColorU_X        = null;
        /** �c���t�H??�g�J??�[                   */
        this.fontColorY          = null;
        /** �����t�H??�g�J??�[                   */
        this.fontColorX          = null;
        /** �o�[�t�H??�g�J??�[                   */
        this.fontColorB          = null;
        /** �o�[�̒l�t�H??�g�J??�[               */
        this.fontColorB_S        = null;

        /** �����ڐ���L���t??�O                 */
        this.isScaleY            = false;
        /** �c���ڐ��蒲���l(??���ō��E�ɑJ��)   */
        this.scaleDetailY        = 1;
        /** Y��??�ڍő�\����????                */
        this.maxScaleLengthY     = 1;
        /** ���l�E�l�߃t??�O(true:���Afalse:�E)  */
        this.isHeadOrTail        = false;
        /** �O??�t�o�[�̒l�\��/��\���t??�O      */
        this.isGraph_Bar_Status  = false;
    }


    /**********************************************************
     * �O??�t�`�ʂ����s���܂�(??�C????????�\�b�h)
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    public void paint(Graphics g) {
        defaultCheck(fontH, fontS, fontU_Y, fontU_X, fontY, fontX, fontB,
                     fontColorH, fontColorS, fontColorU_Y, fontColorU_X, fontColorY, fontColorX, fontColorB,
                     data_set, data_label);

        makeHeader(g);

        // �T�u�X�e�[�^�X�̐ݒ�
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

        // �ڐ���̒l��`��
        makeBarStatus(g);

    }

    /**********************************************************
     * �O??�t�w�b�_�[??��(�O??�t�^�C�g??)��`�ʂ��܂�
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    private void makeHeader(Graphics g){

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
    private void makeSubStatus(Graphics g){

        setStyle(g, fontS, fontColorS);

        // �w�b�_�̕����ƕ`�ʈʒu�̐ݒ�
        g.drawString(this.Sub_Status_Name, this.Sub_Status_Width, this.Sub_Status_Height);
    }

    /**********************************************************
     * X���P�ʖ��̂�`�ʂ��܂�
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    private void makeUnitX(Graphics g){

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
    private void makeUnitY(Graphics g){

        // �t�H??�g�̃X�^�C??�ݒ�
        setStyle(g, fontU_Y, fontColorU_Y);

        g.drawString(this.Y_Unit_Name, this.Y_Unit_Width, this.Y_Unit_Height);

    }

    private void makeHeight(Graphics g){

        int State_Width     = 0;

        setStyle(g, fontY, fontColorY);

        g.drawLine(
            Graph_offset - 1,
            Graph_offset - 1,
            Graph_offset - 1,
            Graph_offset + Graph_Height);

        // �c����`�ʂ��܂�(�E���r��)
        g.drawLine(
            Graph_offset + Graph_Width,
            Graph_offset,
            Graph_offset + Graph_Width,
            Graph_offset + Graph_Height);

        // �c����??�x??�����܂�
        for (int i = 0; i < data_label.length; i++) {
            String checkedtext = "";

            // ��{State_Width(�c��??�ڂ̈ʒu(X�������ɍ��E�ړ������܂�))
            State_Width = scaleDetailY/*114*/;

            // ���p�p??��??�A���p�J�^�J�i��S�p��??�ɕϊ�
			String chkT = data_label[i];


            if(isHeadOrTail){
                // Y��??�ڂ����l�߂ɂ���
                checkedtext = StringUtil.addtoN(chkT, maxScaleLengthY);
            } else {
                // Y��??�ڂ��E�l�߂ɂ���
                checkedtext = StringUtil.addtoN2(chkT, maxScaleLengthY);
            }

            // �c���ڐ����`��
            g.drawString(checkedtext,
                         Graph_offset - State_Width - 25,                        // �c���̖ڐ���(??�l)�̈ʒu(��)
                         Graph_offset + Graph_Height - (i * Y_Tick_Height) + 5);

            setStyle(g, fontY, fontColorY);
            if(isScaleY){
                // �O??�b�h����`�悵�܂�
                if (i > 0){
                    // �O??�b�h����`�悵�܂�
                    g.drawLine(
                        Graph_offset,
                        Graph_offset + Graph_Height - (i * Y_Tick_Height),
                        Graph_offset - 5,
                        Graph_offset + Graph_Height - (i * Y_Tick_Height));
                }
            }
        }

    }

    private void makeWidth(Graphics g){

        setStyle(g, fontX, fontColorX);

        for (int i = 1; i <= Graph_Devide; i++) {
            if(i != Graph_Devide){
                setStyle(g, fontY, new Color(220, 220, 220));
            } else {
                setStyle(g, fontX, fontColorX);
            }

            // �O??�b�h����`�悵�܂�
            g.drawLine(
                Graph_offset + (X_Tick_Width * i) + (Graph_offset * i),
                Graph_offset,
                Graph_offset + (X_Tick_Width * i) + (Graph_offset * i),
                Graph_offset + Graph_Height);
        }

        // ������`�ʂ��܂�(�㑤�r��) // ������??���K�v�ɂȂ邩������Ȃ��̂�??�ӁI
        g.drawLine(
            Graph_offset,
            (Graph_offset + Graph_Height - (data_set.length * Y_Tick_Height)) - 1,
            Graph_offset + Graph_Width ,
            (Graph_offset + Graph_Height - (data_set.length * Y_Tick_Height)) - 1);

        // ������`�ʂ��܂�(�����r��)
        g.drawLine(
            Graph_offset,
            Graph_offset + Graph_Height,
            Graph_offset + Graph_Width,
            Graph_offset + Graph_Height);

        // ������??�x??�����܂�
        for (int i = 0; i <= Graph_Devide; i++) {
            g.drawString(String.valueOf(i * Graph_Devide_Width),
            Graph_offset + (X_Tick_Width * i) + (Graph_offset * i) + Graph_Height_Detail,  // �����̖ڐ���(??�l)�̈ʒu(��)
            Graph_offset + Graph_Height + 18);                                             // �����̖ڐ���(??�l)�̈ʒu(�c)
        }

    }

    /**********************************************************
     * �O??�t�o�[??����`�ʂ��܂�
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    private void makeBar(Graphics g){

        // �t�H??�g�̃X�^�C??�ݒ�
        setStyle(g, fontB, fontColorB);

        // �p????�[�^�̌�??������??�[�v
        for (int i = 0; i < data_set.length; i++) {
            // 100%�ƍl����l��??��
            int bar_width = data_set[i] * Graph_Width / maxValue;

            if (i > 0) {
                // �O??�t�o�[��`��
                g.fillRect(Graph_offset,                                                         // X���̎n�_
                           Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail, // Y���̎n�_
                           bar_width,                                                            // X���̏I�_
                           Graph_Bar_Thick);                                                     // Y���̑���(�o�[�̑���)
            }
        }

    }

    /**********************************************************
     * �O??�t�o�[�̒l��`�ʂ��܂�
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    private void makeBarStatus(Graphics g){

        // �t�H??�g�X�^�C??�̐ݒ�
        setStyle(g, fontB_S, fontColorB_S);

        // �O??�t�o�[�̒l��\�������??
        if(isGraph_Bar_Status){

            // �p????�[�^�̌�??������??�[�v
            for (int i = 0; i < data_set.length; i++) {
                // X���̈ʒu�v�Z
                int status = data_set[i] * Graph_Width / maxValue;

                if (i > 0) {
                    g.drawString(Integer.toString(data_set[i]),                          // �`�ʂ���镶??(�l)
                                 Graph_offset + status ,                                 // X���̈ʒu
                                 Graph_offset + Graph_Height - (i * Y_Tick_Height) + 5); // Y���̈ʒu
                }
            }
        }

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
     * @param data_set      �p????�[�^�̒l(�o�[�ɕ\�������l)
     * @param data_label    �����ڐ���l
     **********************************************************/
    private void defaultCheck(Font fontH, Font fontS, Font fontU_Y, Font fontU_X, Font fontY, Font fontX, Font fontB,
                                Color fontColorH, Color fontColorS, Color fontColorU_Y, Color fontColorU_X,
                                Color fontColorY, Color fontColorX, Color fontColorB,
                                int[] data_set, String[] data_label){

        // �t�H??�gnull�΍�
        if(fontH == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontH   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontS == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontS   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontU_Y == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontU_Y = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontU_X == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontU_X = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontY == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontY   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontX == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontX   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontB == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontB   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontB_S == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontB_S = new Font("Courier", Font.PLAIN, 10);
        }

        // �t�H??�g�J??�[null�΍�
        if(fontColorH == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorH   = Color.BLACK;
        }
        // �t�H??�g�J??�[null�΍�
        if(fontColorS == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorS   = Color.BLACK;
        }
        // �t�H??�g�J??�[null�΍�
        if(fontColorU_Y == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorU_Y = Color.BLACK;
        }
        // �t�H??�g�J??�[null�΍�
        if(fontColorU_X == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorU_X = Color.BLACK;
        }
        // �t�H??�g�J??�[null�΍�
        if(fontColorY == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorY   = Color.BLACK;
        }
        if(fontColorX == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorX   = Color.BLACK;
        }
        if(fontColorB == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorB   = Color.BLACK;
        }
        if(fontColorB_S == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorB_S = Color.BLACK;
        }

        if(data_set.length <= 0 || data_set == null){
            data_set[0] = 0;
        }

        if(data_label.length <= 0 || data_label == null){
            data_label[0] = "";
        }

    }

    private void setStyle(Graphics g, Font font, Color fontColor){
        g.setColor(fontColor);
        g.setFont(font);
    }
}
