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
    /** 縦軸の1分??分の??さ                  */
    private int Y_Tick_Height;

    /** バーの横??                           */
    private int Graph_Width;
    /** 横軸の分????                         */
    private int X_Tick_Width;
    /** バーの位置の微調整値                 */
    private int Graph_Width_Detail;

    /** ヘッダーフォ??ト                     */
    private Font fontH;
    /** サブステータスフォ??ト               */
    private Font fontS;
    /** 縦軸フォ??ト                         */
    private Font fontY;
    /** 横軸フォ??ト                         */
    private Font fontX;
    /** バーフォ??ト                         */
    private Font fontB;

    /** ヘッダーフォ??トカ??ー               */
    private Color fontColorH;
    /** サブステータスフォ??トカ??ー         */
    private Color fontColorS;
    /** 縦軸フォ??トカ??ー                   */
    private Color fontColorY;
    /** 横軸フォ??トカ??ー                   */
    private Color fontColorX;
    /** バーフォ??トカ??ー                   */
    private Color fontColorB;

    /** 横軸目盛り有無フ??グ                 */
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

        // グ??フ描画位置の原点からのオフセット
        this.Graph_offset      = Graph_offset;
        // グ??フの大きさ(??さ)
        this.Graph_Height      = Graph_Height;
        // バーで100%(MAX値)と考える値
        this.maxValue          = maxValue;
        // 縦軸の分????
        this.Graph_Devide      = Graph_Devide ;
        // バーの横??
        this.Graph_Width       = Graph_Width;
        // バーの位置の微調整値
        this.Graph_Width_Detail= Graph_Width_Detail;
        // ヘッダー名称
        this.Header_Name       = StringUtil.getCheckedValue(Header_Name, "");
        // ヘッダー位置(横軸)
        this.Header_Width      = Header_Width;
        // ヘッダー位置(縦軸)
        this.Header_Height     = Header_Height;
        // ヘッダーフォ??ト
        this.fontH             = fontH;
        // サブステータス
        this.Sub_Status_Name   = StringUtil.getCheckedValue(Sub_Status_Name, "");
        // サブステータス表示位置(横軸)
        this.Sub_Status_Width  = Sub_Status_Width;
        // サブステータス表示位置(縦軸)
        this.Sub_Status_Height = Sub_Status_Height;
        // サブステータスフォ??ト
        this.fontS             = fontS;
        // 縦軸フォ??ト
        this.fontY             = fontY;
        // 横軸フォ??ト
        this.fontX             = fontX;
        // バーフォ??ト
        this.fontB             = fontB;
        // ヘッダーフォ??トカ??ー
        this.fontColorH        = fontColorH;
        // サブステータスフォ??トカ??ー
        this.fontColorS        = fontColorS;
        // 縦軸フォ??トカ??ー
        this.fontColorY        = fontColorY;
        // 横軸フォ??トカ??ー
        this.fontColorX        = fontColorX;
        // バーフォ??トカ??ー
        this.fontColorB        = fontColorB;
        // 横軸目盛り有無フ??グ
        this.isScaleX          = isScaleX;

        // パ????ータの値(index 0はダミー)
        this.data_set      = data_set;
        // 横軸目もり値(index 0はダミー)
        this.data_label    = data_label;

        // 縦軸の目盛り間の値
        this.Graph_Devide_Height = maxValue     / Graph_Devide;
        // 縦軸の1分??分の??さ
        this.Y_Tick_Height       = Graph_Height / Graph_Devide;
        // 横軸の分????
        this.X_Tick_Width        = Graph_Width  / data_set.length;

    }

    private void setDefault(){
        /** パ????ータの値(index 0はダミー)      */
        this.data_set            = null;
        /** 横軸目もり値(index 0はダミー)        */
        this.data_label          = null;

        /** グ??フ描画位置の原点からのオフセット */
        this.Graph_offset        = 1;
        /** グ??フの大きさ(??さ)                 */
        this.Graph_Height        = 1;
        /** バーで100%(MAX値)と考える値          */
        this.maxValue            = 1;

        /** ヘッダータイト??名称                 */
        this.Header_Name       = "";
        /** ヘッダー位置(横軸)                   */
        this.Header_Width        = 1;
        /** ヘッダー位置(縦軸)                   */
        this.Header_Height       = 1;

        /** サブステータス名称                   */
        this.Sub_Status_Name   = "";
        /** サブステータス位置(横軸)             */
        this.Sub_Status_Width    = 1;
        /** サブステータス位置(縦軸)             */
        this.Sub_Status_Height   = 1;

        /** 縦軸の分????                         */
        this.Graph_Devide        = 1;
        /** 縦軸の目盛り間の値                   */
        this.Graph_Devide_Height = 1;
        /** 縦軸の1分??分の??さ                  */
        this.Y_Tick_Height       = 1;

        /** バーの横??                           */
        this.Graph_Width         = 1;
        /** 横軸の分????                         */
        this.X_Tick_Width        = 1;
        /** バーの位置の微調整値                 */
        this.Graph_Width_Detail  = 0;

        /** ヘッダーフォ??ト                     */
        this.fontH               = null;
        /** サブステータスフォ??ト               */
        this.fontS               = null;
        /** 縦軸フォ??ト                         */
        this.fontY               = null;
        /** 横軸フォ??ト                         */
        this.fontX               = null;
        /** バーフォ??ト                         */
        this.fontB               = null;

        /** ヘッダーフォ??トカ??ー               */
        this.fontColorH          = null;
        /** サブステータスフォ??トカ??ー         */
        this.fontColorS          = null;
        /** 縦軸フォ??トカ??ー                   */
        this.fontColorY          = null;
        /** 横軸フォ??トカ??ー                   */
        this.fontColorX          = null;
        /** バーフォ??トカ??ー                   */
        this.fontColorB          = null;

        /** 横軸目盛り有無フ??グ                 */
        this.isScaleX            = false;
    }


    /**********************************************************
     * グ??フ描写を実行します(??イ????????ソッド)
     *
     * @param g グ??フィクス
     **********************************************************/
    public void paint(Graphics g) {

        // デフォ??トチェック
        defaultCheck(fontH, fontS, fontY, fontX, fontB,
                     fontColorH, fontColorS, fontColorY, fontColorX, fontColorB,
                     data_set, data_label);

        // グ??フヘッダの設定
        makeHeader(g);

        // サブステータスの設定
        makeSubStatus(g);

        // 縦軸の描写
        makeHeight(g);

        // 横軸の描写
        makeWidth(g);

        // 目盛りの描写
        makeBar(g);

    }

    /**********************************************************
     * グ??フヘッダー??分(グ??フタイト??)を描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeHeader(Graphics g){

        // フォ??トのスタイ??設定
        setStyle(g, fontH, fontColorH);

        // ヘッダの文言と描写位置の設定
        g.drawString(this.Header_Name, this.Header_Width, this.Header_Height);

    }

    /**********************************************************
     * グ??フサブステータスを描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeSubStatus(Graphics g){

        // フォ??トのスタイ??設定
        setStyle(g, fontS, fontColorS);

        // ヘッダの文言と描写位置の設定
        g.drawString(this.Sub_Status_Name, this.Sub_Status_Width, this.Sub_Status_Height);

    }

    /**********************************************************
     * グ??フ縦軸??分を描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeHeight(Graphics g){
        /** 縦軸目盛りの値の長さチェック値 */
        int chkWidth_Length = 0;
        /** 縦軸目盛りの描写位置           */
        int State_Width     = 0;

        // フォ??トのスタイ??設定
        setStyle(g, fontY, fontColorY);

        // 縦軸を描写します(左側罫線)
        g.drawLine(
            Graph_offset + 3,
            Graph_offset,
            Graph_offset + 3,
            Graph_offset + Graph_Height);

        // 縦軸を描写します(右側罫線)
        g.drawLine(
            Graph_offset + Graph_Width + 3,
            Graph_offset,
            Graph_offset + Graph_Width + 3,
            Graph_offset + Graph_Height);

        // 縦軸に??ベ??を入れます
        for (int i = 0; i <= Graph_Devide; i++) {
            // 目盛りの値の長さ
            chkWidth_Length = Integer.toString( i * Graph_Devide_Height ).toString().length();

            // 縦軸の値位置の調整(5??の??値まで対??)
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

            // 縦軸目盛りを描写
            g.drawString(
                String.valueOf(i * Graph_Devide_Height),            // ??ベ??(目盛り)はi * Graph_Devide_Heightごとに表示
                Graph_offset - State_Width,                         // 縦軸の目盛り(??値)の位置(横)
                Graph_offset + Graph_Height - (i * Y_Tick_Height)); // 縦軸の目盛り(??値)の位置(縦)

            // グ??ッド線を描画します
            if(i != 0 && i != Graph_Devide){
                // ??ベ??ダー色
                setStyle(g, fontY, new Color(220, 220, 220));

                g.drawLine(
                    Graph_offset,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height),
                    Graph_offset + Graph_Width + 3,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height));
            } else {
                // 元の指定された色に戻す
                setStyle(g, fontY, fontColorY);

                g.drawLine(
                    Graph_offset,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height),
                    Graph_offset + Graph_Width + 3,
                    Graph_offset + Graph_Height - (i * Y_Tick_Height));
            }

            // 元の指定された色に戻す
            setStyle(g, fontY, fontColorY);

            // 線の微調整(右下)
            g.drawLine(
                Graph_offset,
                Graph_offset + Graph_Height - (i * Y_Tick_Height),
                Graph_offset + 3,
                Graph_offset + Graph_Height - (i * Y_Tick_Height));
        }

        // 線の微調整(目盛り)
        g.drawLine(
            Graph_offset + Graph_Width + 3,
            Graph_offset,
            Graph_offset + Graph_Width + 3,
            Graph_offset + Graph_Height);

    }

    /**********************************************************
     * グ??フ横軸??分を描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeWidth(Graphics g){

        // フォ??トのスタイ??設定
        setStyle(g, fontX, fontColorX);

        // 横軸
        g.drawLine(
            Graph_offset,
            Graph_offset + Graph_Height,
            Graph_offset + Graph_Width,
            Graph_offset + Graph_Height);

        // 横軸に??ベ??を入れます
        for (int i = 0; i < data_set.length; i++) {
            g.drawString(
                data_label[i],
                Graph_offset + X_Tick_Width * i + X_Tick_Width / 10,  // 横軸目盛り??値の位置
                Graph_offset + Graph_Height + 15);

            // 横軸目盛りを表示
            if(isScaleX){
                // 横軸描写
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
     * グ??フバー??分を描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeBar(Graphics g){


        // フォ??トのスタイ??設定
        setStyle(g, fontB, fontColorB);

        // パ????ータの個??分だけ??ープ
        for (int i = 0; i < data_set.length; i++) {
            int bar_height = data_set[i] * Graph_Height / maxValue;  // 100%と考える値で??る

            g.fillRect(
                (Graph_offset + X_Tick_Width * i + X_Tick_Width / Graph_Devide) + Graph_Width_Detail,
                Graph_offset + Graph_Height - bar_height,
                X_Tick_Width / 2,
                bar_height);
        }

    }


    /**********************************************************
     * GraphMakerク??スが呼ばれた際、不正値がINPUTされていないかチェックします<br>
     * 不正値が引き渡された場??、強制的にデフォ??ト設定を行います。
     *
     * @param fontH         ヘッダーフォ??ト
     * @param fontS         サブステータスフォ??ト
     * @param fontY         グ??フ縦軸フォ??ト
     * @param fontX         横軸フォ??ト
     * @param fontB         グ??フバーフォ??ト
     * @param fontColorH    ヘッダーフォ??トカ??ー
     * @param fontColorS    サブステータスフォ??トカ??ー
     * @param fontColorY    グ??フ縦軸フォ??トカ??ー
     * @param fontColorX    グ??フ横軸フォ??トカ??ー
     * @param fontColorB    グ??フバーフォ??トカ??ー
     * @param data_set      パ????ータの値(バーに表示される値)
     * @param data_label    横軸目盛り値
     **********************************************************/
    private void defaultCheck(Font fontH, Font fontS, Font fontY, Font fontX, Font fontB,
                                Color fontColorH, Color fontColorS, Color fontColorY, Color fontColorX, Color fontColorB,
                                int[] data_set, String[] data_label){


        // フォ??トnull対策
        if(fontH == null){
            // デフォ??トフォ??トをセット
            this.fontH = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontS == null){
            // デフォ??トフォ??トをセット
            this.fontS = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontY == null){
            // デフォ??トフォ??トをセット
            this.fontY = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontX == null){
            // デフォ??トフォ??トをセット
            this.fontX = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontB == null){
            // デフォ??トフォ??トをセット
            this.fontB = new Font("Courier", Font.PLAIN, 10);
        }

        // フォ??トカ??ーnull対策
        if(fontColorS == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorS = Color.BLACK;
        }
        // フォ??トカ??ーnull対策
        if(fontColorH == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorH = Color.BLACK;
        }
        // フォ??トカ??ーnull対策
        if(fontColorY == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorY = Color.BLACK;
        }
        if(fontColorX == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorX = Color.BLACK;
        }
        if(fontColorB == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorB = Color.BLACK;
        }

        // パ????ータの値チェック
        if(data_set.length <= 0 || data_set == null){
            data_set[0] = 0;
        }

        // 横軸目もり値チェック
        if(data_label.length <= 0 || data_label == null){
            data_label[0] = "";
        }
    }

    private void setStyle(Graphics g, Font font, Color fontColor){
        g.setColor(fontColor);
        g.setFont(font);
    }
}
