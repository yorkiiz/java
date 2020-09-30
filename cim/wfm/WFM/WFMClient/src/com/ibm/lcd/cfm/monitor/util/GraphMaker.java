package com.ibm.lcd.cfm.monitor.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GraphMaker extends Canvas {
	private static final long serialVersionUID = 5005277038183982304L;

    final String CLASS_NAME = "GraphMaker";

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

    private int Graph_Devide;
    private int Graph_Devide_Height;
    /** �c����1��??����??��                  */
    private int Y_Tick_Height;

    /** �o�[�̉�??                           */
    private int Graph_Width;
    /** �����̕�????                         */
    private int X_Tick_Width;
    /** �o�[�̈ʒu�̔������l                 */
    private int Graph_Width_Detail;

    /** �w�b�_�[�t�H??�g                     */
    private Font fontH;
    /** �T�u�X�e�[�^�X�t�H??�g               */
    private Font fontS;
    /** �c���t�H??�g                         */
    private Font fontY;
    /** �����t�H??�g                         */
    private Font fontX;
    /** �o�[�t�H??�g                         */
    private Font fontB;

    /** �w�b�_�[�t�H??�g�J??�[               */
    private Color fontColorH;
    /** �T�u�X�e�[�^�X�t�H??�g�J??�[         */
    private Color fontColorS;
    /** �c���t�H??�g�J??�[                   */
    private Color fontColorY;
    /** �����t�H??�g�J??�[                   */
    private Color fontColorX;
    /** �o�[�t�H??�g�J??�[                   */
    private Color fontColorB;

    /** �����ڐ���L���t??�O                 */
    private boolean isScaleX;

    public GraphMaker(int Graph_offset, int Graph_Height, int maxValue, int Graph_Devide, int Graph_Width, int Graph_Width_Detail,
                       String Header_Name, int Header_Width, int Header_Height,
                       String Sub_Status_Name,  int Sub_Status_Width, int Sub_Status_Height,
                       Font fontH, Color fontColorH,
                       Font fontS, Color fontColorS,
                       Font fontY, Color fontColorY,
                       Font fontX, Color fontColorX,
                       Font fontB, Color fontColorB,
                       boolean isScaleX,
                       int[] data_set, String[] data_label){

        setDefault();

        // �O??�t�`��ʒu�̌��_����̃I�t�Z�b�g
        this.Graph_offset      = Graph_offset;
        // �O??�t�̑傫��(??��)
        this.Graph_Height      = Graph_Height;
        // �o�[��100%(MAX�l)�ƍl����l
        this.maxValue          = maxValue;
        // �c���̕�????
        this.Graph_Devide      = Graph_Devide ;
        // �o�[�̉�??
        this.Graph_Width       = Graph_Width;
        // �o�[�̈ʒu�̔������l
        this.Graph_Width_Detail= Graph_Width_Detail;
        // �w�b�_�[����
        this.Header_Name       = StringUtil.getCheckedValue(Header_Name, "");
        // �w�b�_�[�ʒu(����)
        this.Header_Width      = Header_Width;
        // �w�b�_�[�ʒu(�c��)
        this.Header_Height     = Header_Height;
        // �w�b�_�[�t�H??�g
        this.fontH             = fontH;
        // �T�u�X�e�[�^�X
        this.Sub_Status_Name   = StringUtil.getCheckedValue(Sub_Status_Name, "");
        // �T�u�X�e�[�^�X�\���ʒu(����)
        this.Sub_Status_Width  = Sub_Status_Width;
        // �T�u�X�e�[�^�X�\���ʒu(�c��)
        this.Sub_Status_Height = Sub_Status_Height;
        // �T�u�X�e�[�^�X�t�H??�g
        this.fontS             = fontS;
        // �c���t�H??�g
        this.fontY             = fontY;
        // �����t�H??�g
        this.fontX             = fontX;
        // �o�[�t�H??�g
        this.fontB             = fontB;
        // �w�b�_�[�t�H??�g�J??�[
        this.fontColorH        = fontColorH;
        // �T�u�X�e�[�^�X�t�H??�g�J??�[
        this.fontColorS        = fontColorS;
        // �c���t�H??�g�J??�[
        this.fontColorY        = fontColorY;
        // �����t�H??�g�J??�[
        this.fontColorX        = fontColorX;
        // �o�[�t�H??�g�J??�[
        this.fontColorB        = fontColorB;
        // �����ڐ���L���t??�O
        this.isScaleX          = isScaleX;

        // �p????�[�^�̒l(index 0�̓_�~�[)
        this.data_set      = data_set;
        // �����ڂ���l(index 0�̓_�~�[)
        this.data_label    = data_label;

        // �c���̖ڐ���Ԃ̒l
        this.Graph_Devide_Height = maxValue     / Graph_Devide;
        // �c����1��??����??��
        this.Y_Tick_Height       = Graph_Height / Graph_Devide;
        // �����̕�????
        this.X_Tick_Width        = Graph_Width  / data_set.length;

    }

    private void setDefault(){
        /** �p????�[�^�̒l(index 0�̓_�~�[)      */
        this.data_set            = null;
        /** �����ڂ���l(index 0�̓_�~�[)        */
        this.data_label          = null;

        /** �O??�t�`��ʒu�̌��_����̃I�t�Z�b�g */
        this.Graph_offset        = 1;
        /** �O??�t�̑傫��(??��)                 */
        this.Graph_Height        = 1;
        /** �o�[��100%(MAX�l)�ƍl����l          */
        this.maxValue            = 1;

        /** �w�b�_�[�^�C�g??����                 */
        this.Header_Name       = "";
        /** �w�b�_�[�ʒu(����)                   */
        this.Header_Width        = 1;
        /** �w�b�_�[�ʒu(�c��)                   */
        this.Header_Height       = 1;

        /** �T�u�X�e�[�^�X����                   */
        this.Sub_Status_Name   = "";
        /** �T�u�X�e�[�^�X�ʒu(����)             */
        this.Sub_Status_Width    = 1;
        /** �T�u�X�e�[�^�X�ʒu(�c��)             */
        this.Sub_Status_Height   = 1;

        /** �c���̕�????                         */
        this.Graph_Devide        = 1;
        /** �c���̖ڐ���Ԃ̒l                   */
        this.Graph_Devide_Height = 1;
        /** �c����1��??����??��                  */
        this.Y_Tick_Height       = 1;

        /** �o�[�̉�??                           */
        this.Graph_Width         = 1;
        /** �����̕�????                         */
        this.X_Tick_Width        = 1;
        /** �o�[�̈ʒu�̔������l                 */
        this.Graph_Width_Detail  = 0;

        /** �w�b�_�[�t�H??�g                     */
        this.fontH               = null;
        /** �T�u�X�e�[�^�X�t�H??�g               */
        this.fontS               = null;
        /** �c���t�H??�g                         */
        this.fontY               = null;
        /** �����t�H??�g                         */
        this.fontX               = null;
        /** �o�[�t�H??�g                         */
        this.fontB               = null;

        /** �w�b�_�[�t�H??�g�J??�[               */
        this.fontColorH          = null;
        /** �T�u�X�e�[�^�X�t�H??�g�J??�[         */
        this.fontColorS          = null;
        /** �c���t�H??�g�J??�[                   */
        this.fontColorY          = null;
        /** �����t�H??�g�J??�[                   */
        this.fontColorX          = null;
        /** �o�[�t�H??�g�J??�[                   */
        this.fontColorB          = null;

        /** �����ڐ���L���t??�O                 */
        this.isScaleX            = false;
    }


    /**********************************************************
     * �O??�t�`�ʂ����s���܂�(??�C????????�\�b�h)
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    public void paint(Graphics g) {

        // �f�t�H??�g�`�F�b�N
        defaultCheck(fontH, fontS, fontY, fontX, fontB,
                     fontColorH, fontColorS, fontColorY, fontColorX, fontColorB,
                     data_set, data_label);

        // �O??�t�w�b�_�̐ݒ�
        makeHeader(g);

        // �T�u�X�e�[�^�X�̐ݒ�
        makeSubStatus(g);

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

        // �t�H??�g�̃X�^�C??�ݒ�
        setStyle(g, fontS, fontColorS);

        // �w�b�_�̕����ƕ`�ʈʒu�̐ݒ�
        g.drawString(this.Sub_Status_Name, this.Sub_Status_Width, this.Sub_Status_Height);

    }

    /**********************************************************
     * �O??�t�c��??����`�ʂ��܂�
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    private void makeHeight(Graphics g){
        /** �c���ڐ���̒l�̒����`�F�b�N�l */
        int chkWidth_Length = 0;
        /** �c���ڐ���̕`�ʈʒu           */
        int State_Width     = 0;

        // �t�H??�g�̃X�^�C??�ݒ�
        setStyle(g, fontY, fontColorY);

        // �c����`�ʂ��܂�(�����r��)
        g.drawLine(
            Graph_offset + 3,
            Graph_offset,
            Graph_offset + 3,
            Graph_offset + Graph_Height);

        // �c����`�ʂ��܂�(�E���r��)
        g.drawLine(
            Graph_offset + Graph_Width + 3,
            Graph_offset,
            Graph_offset + Graph_Width + 3,
            Graph_offset + Graph_Height);

        // �c����??�x??�����܂�
        for (int i = 0; i <= Graph_Devide; i++) {
            // �ڐ���̒l�̒���
            chkWidth_Length = Integer.toString( i * Graph_Devide_Height ).toString().length();

            // �c���̒l�ʒu�̒���(5??��??�l�܂ő�??)
            if (chkWidth_Length == 1){
                State_Width = 10;
            }else if(chkWidth_Length == 2){
                State_Width = 15;
            }else if(chkWidth_Length == 3){
                State_Width = 20;
            }else if(chkWidth_Length == 4){
                State_Width = 26;
            }else if(chkWidth_Length == 5){
                State_Width = 33;
            }else{
                State_Width = 38;
            }

            // �c���ڐ����`��
            g.drawString(
                String.valueOf(i * Graph_Devide_Height),            // ??�x??(�ڐ���)��i * Graph_Devide_Height���Ƃɕ\��
                Graph_offset - State_Width,                         // �c���̖ڐ���(??�l)�̈ʒu(��)
                Graph_offset + Graph_Height - (i * Y_Tick_Height)); // �c���̖ڐ���(??�l)�̈ʒu(�c)

            // �O??�b�h����`�悵�܂�
            if(i != 0 && i != Graph_Devide){
                // ??�x??�_�[�F
                setStyle(g, fontY, new Color(220, 220, 220));

                g.drawLine(
                    Graph_offset,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height),
                    Graph_offset + Graph_Width + 3,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height));
            } else {
                // ���̎w�肳�ꂽ�F�ɖ߂�
                setStyle(g, fontY, fontColorY);

                g.drawLine(
                    Graph_offset,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height),
                    Graph_offset + Graph_Width + 3,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height));
            }

            // ���̎w�肳�ꂽ�F�ɖ߂�
            setStyle(g, fontY, fontColorY);

            // ���̔�����(�E��)
            g.drawLine(
                Graph_offset,
                Graph_offset + Graph_Height - (i * Y_Tick_Height),
                Graph_offset + 3,
                Graph_offset + Graph_Height - (i * Y_Tick_Height));
        }

        // ���̔�����(�ڐ���)
        g.drawLine(
            Graph_offset + Graph_Width + 3,
            Graph_offset,
            Graph_offset + Graph_Width + 3,
            Graph_offset + Graph_Height);

    }

    /**********************************************************
     * �O??�t����??����`�ʂ��܂�
     *
     * @param g �O??�t�B�N�X
     **********************************************************/
    private void makeWidth(Graphics g){

        // �t�H??�g�̃X�^�C??�ݒ�
        setStyle(g, fontX, fontColorX);

        // ����
        g.drawLine(
            Graph_offset,
            Graph_offset + Graph_Height,
            Graph_offset + Graph_Width,
            Graph_offset + Graph_Height);

        // ������??�x??�����܂�
        for (int i = 0; i < data_set.length; i++) {
            g.drawString(
                data_label[i],
                Graph_offset + X_Tick_Width * i + X_Tick_Width / 10,  // �����ڐ���??�l�̈ʒu
                Graph_offset + Graph_Height + 15);

            // �����ڐ����\��
            if(isScaleX){
                // �����`��
                if(i==0){
                    g.drawLine(
                        Graph_offset + X_Tick_Width * i + X_Tick_Width / 2,
                        Graph_offset + Graph_Height,
                        Graph_offset + X_Tick_Width * i + X_Tick_Width / 2,
                        Graph_offset + Graph_Height + 0);
                }else{
                    g.drawLine(
                        Graph_offset + X_Tick_Width * i + X_Tick_Width / 2,
                        Graph_offset + Graph_Height,
                        Graph_offset + X_Tick_Width * i + X_Tick_Width / 2,
                        Graph_offset + Graph_Height + 5);
                }
            }
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
            int bar_height = data_set[i] * Graph_Height / maxValue;  // 100%�ƍl����l��??��

            g.fillRect(
                (Graph_offset + X_Tick_Width * i + X_Tick_Width / Graph_Devide) + Graph_Width_Detail,
                Graph_offset + Graph_Height - bar_height,
                X_Tick_Width / 2,
                bar_height);
        }

    }


    /**********************************************************
     * GraphMaker�N??�X���Ă΂ꂽ�ہA�s���l��INPUT����Ă��Ȃ����`�F�b�N���܂�<br>
     * �s���l�������n���ꂽ��??�A�����I�Ƀf�t�H??�g�ݒ���s���܂��B
     *
     * @param fontH         �w�b�_�[�t�H??�g
     * @param fontS         �T�u�X�e�[�^�X�t�H??�g
     * @param fontY         �O??�t�c���t�H??�g
     * @param fontX         �����t�H??�g
     * @param fontB         �O??�t�o�[�t�H??�g
     * @param fontColorH    �w�b�_�[�t�H??�g�J??�[
     * @param fontColorS    �T�u�X�e�[�^�X�t�H??�g�J??�[
     * @param fontColorY    �O??�t�c���t�H??�g�J??�[
     * @param fontColorX    �O??�t�����t�H??�g�J??�[
     * @param fontColorB    �O??�t�o�[�t�H??�g�J??�[
     * @param data_set      �p????�[�^�̒l(�o�[�ɕ\�������l)
     * @param data_label    �����ڐ���l
     **********************************************************/
    private void defaultCheck(Font fontH, Font fontS, Font fontY, Font fontX, Font fontB,
                                Color fontColorH, Color fontColorS, Color fontColorY, Color fontColorX, Color fontColorB,
                                int[] data_set, String[] data_label){


        // �t�H??�gnull�΍�
        if(fontH == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontH = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontS == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontS = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontY == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontY = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontX == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontX = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontB == null){
            // �f�t�H??�g�t�H??�g���Z�b�g
            this.fontB = new Font("Courier", Font.PLAIN, 10);
        }

        // �t�H??�g�J??�[null�΍�
        if(fontColorS == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorS = Color.BLACK;
        }
        // �t�H??�g�J??�[null�΍�
        if(fontColorH == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorH = Color.BLACK;
        }
        // �t�H??�g�J??�[null�΍�
        if(fontColorY == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorY = Color.BLACK;
        }
        if(fontColorX == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorX = Color.BLACK;
        }
        if(fontColorB == null){
            // �f�t�H??�g�t�H??�g�J??�[(??)���Z�b�g
            this.fontColorB = Color.BLACK;
        }

        // �p????�[�^�̒l�`�F�b�N
        if(data_set.length <= 0 || data_set == null){
            data_set[0] = 0;
        }

        // �����ڂ���l�`�F�b�N
        if(data_label.length <= 0 || data_label == null){
            data_label[0] = "";
        }
    }

    private void setStyle(Graphics g, Font font, Color fontColor){
        g.setColor(fontColor);
        g.setFont(font);
    }
}
