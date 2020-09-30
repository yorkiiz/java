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
    /** Y軸単位フォ??ト                      */
    private Font fontU_Y;
    /** X軸単位フォ??ト                      */
    private Font fontU_X;
    /** 縦軸フォ??ト                         */
    private Font fontY;
    /** 横軸フォ??ト                         */
    private Font fontX;
    /** バーフォ??ト                         */
    private Font fontB;
    /** バーの値フォ??ト                     */
    private Font fontB_S;

    /** ヘッダーフォ??トカ??ー               */
    private Color fontColorH;
    /** サブステータスフォ??トカ??ー         */
    private Color fontColorS;
    /** Y軸単位フォ??トカ??ー                */
    private Color fontColorU_Y;
    /** X軸単位フォ??トカ??ー                */
    private Color fontColorU_X;
    /** 縦軸フォ??トカ??ー                   */
    private Color fontColorY;
    /** 横軸フォ??トカ??ー                   */
    private Color fontColorX;
    /** バーフォ??トカ??ー                   */
    private Color fontColorB;
    /** バーの値フォ??トカ??ー               */
    private Color fontColorB_S;

    /** 縦軸目盛り有無フ??グ                 */
    private boolean isScaleY;
    /** 縦軸目盛り調整値(??減で左右に遷移)   */
    private int scaleDetailY;
    /** Y軸??目最大表示文????                */
    private int maxScaleLengthY;
    /** 左詰右詰めフ??グ(true:左、false:右)  */
    private boolean isHeadOrTail;

    /** グ??フバーの値表示/非表示フ??グ      */
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
        // グ??フバーの位置調整値
        this.Graph_Bar_Detail    = Graph_Bar_Detail;
        // グ??フバーの太さ
        this.Graph_Bar_Thick     = Graph_Bar_Thick;
        // ヘッダー名称
        this.Header_Name         = StringUtil.getCheckedValue(Header_Name, "");
        // ヘッダー位置(横軸)
        this.Header_Width        = Header_Width;
        // ヘッダー位置(縦軸)
        this.Header_Height       = Header_Height;
        // サブステータス
        this.Sub_Status_Name     = StringUtil.getCheckedValue(Sub_Status_Name, "");
        // サブステータス表示位置(横軸)
        this.Sub_Status_Width    = Sub_Status_Width;
        // サブステータス表示位置(縦軸)
        this.Sub_Status_Height   = Sub_Status_Height;
        // X軸単位名称
        this.X_Unit_Name         = StringUtil.getCheckedValue(X_Unit_Name, "");
        // X軸単位位置(横軸)
        this.X_Unit_Width        = X_Unit_Width;
        // X軸単位位置(縦軸)
        this.X_Unit_Height       = X_Unit_Height;
        // Y軸単位名称
        this.Y_Unit_Name         = StringUtil.getCheckedValue(Y_Unit_Name, "");
        // Y軸単位位置(横軸)
        this.Y_Unit_Width        = Y_Unit_Width;
        // Y軸単位位置(縦軸)
        this.Y_Unit_Height       = Y_Unit_Height;
        // ヘッダーフォ??ト
        this.fontH               = fontH;
        // サブステータスフォ??ト
        this.fontS               = fontS;
        // Y軸単位フォ??ト
        this.fontU_Y             = fontU_Y;
        // X軸単位フォ??ト
        this.fontU_X             = fontU_X;
        // 縦軸フォ??ト
        this.fontY               = fontY;
        // 横軸フォ??ト
        this.fontX               = fontX;
        // バーフォ??ト
        this.fontB               = fontB;
        // バーの値フォ??ト
        this.fontB_S             = fontB_S;
        // ヘッダーフォ??トカ??ー
        this.fontColorH          = fontColorH;
        // サブステータスフォ??トカ??ー
        this.fontColorS          = fontColorS;
        // Y軸単位フォ??トカ??ー
        this.fontColorU_Y        = fontColorU_Y;
        // X軸単位フォ??トカ??ー
        this.fontColorU_X        = fontColorU_X;
        // 縦軸フォ??トカ??ー
        this.fontColorY          = fontColorY;
        // 横軸フォ??トカ??ー
        this.fontColorX          = fontColorX;
        // バーフォ??トカ??ー
        this.fontColorB          = fontColorB;
        // バーの値フォ??トカ??ー
        this.fontColorB_S        = fontColorB_S;
        // 横軸目盛り有無フ??グ
        this.isScaleY            = isScaleY;
        // 縦軸目盛り調整値(??減で左右に遷移)
        this.scaleDetailY        = scaleDetailY;
        // Y軸??目最大表示文????
        this.maxScaleLengthY     = maxScaleLengthY;
        // 左詰右詰めフ??グ(true:左、false:右)
        this.isHeadOrTail        = isHeadOrTail;
        // グ??フバーの値表示/非表示フ??グ(true:表示、false:非表示)
        this.isGraph_Bar_Status  = isGraph_Bar_Status;

        // パ????ータの値(index 0はダミー)
        this.data_set            = data_set;
        // 横軸目もり値(index 0はダミー)
        this.data_label          = data_label;

        // 縦軸の目盛り間の値
        //this.Graph_Devide_Height = maxValue     / Graph_Devide;
        // 横軸の目盛り間の値
        this.Graph_Devide_Width  = maxValue     / Graph_Devide;
        // 縦軸の1分??分の??さ
        this.Y_Tick_Height       = Graph_Height / data_label.length;
        // 横軸の分????
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
        /** X軸単位位置(横軸)                    */
        this.X_Unit_Width        = 1;
        /** X軸単位位置(縦軸)                    */
        this.X_Unit_Height       = 1;

        /** Y軸単位名称                          */
        this.Y_Unit_Name         = "";
        /** Y軸単位位置(横軸)                    */
        this.Y_Unit_Width        = 1;
        /** Y軸単位位置(縦軸)                    */
        this.Y_Unit_Height       = 1;

        /** 縦軸の分????                         */
        this.Graph_Devide        = 1;
        /** 縦軸の目盛り間の値                   */
        //this.Graph_Devide_Height = 1;
        /** 縦軸の1分??分の??さ                  */
        this.Y_Tick_Height       = 1;

        /** バーの横??                           */
        this.Graph_Width         = 1;
        /** 横軸の分????                         */
        this.X_Tick_Width        = 1;
        /** 横軸の目盛り(??値)の位置の調整値     */
        this.Graph_Height_Detail = 1;
        /** グ??フバーの位置調整値               */
        this.Graph_Bar_Detail    = 1;
        /** グ??フバーの太さ                     */
        this.Graph_Bar_Detail    = 1;

        /** ヘッダーフォ??ト                     */
        this.fontH               = null;
        /** サブステータスフォ??ト               */
        this.fontS               = null;
        /** Y軸単位フォ??ト                      */
        this.fontU_Y             = null;
        /** X軸単位フォ??ト                      */
        this.fontU_X             = null;
        /** 縦軸フォ??ト                         */
        this.fontY               = null;
        /** 横軸フォ??ト                         */
        this.fontX               = null;
        /** バーフォ??ト                         */
        this.fontB               = null;
        /** バーの値フォ??ト                     */
        this.fontB_S             = null;

        /** ヘッダーフォ??トカ??ー               */
        this.fontColorH          = null;
        /** サブステータスフォ??トカ??ー         */
        this.fontColorS          = null;
        /** Y軸単位フォ??トカ??ー                */
        this.fontColorU_Y        = null;
        /** X軸単位フォ??トカ??ー                */
        this.fontColorU_X        = null;
        /** 縦軸フォ??トカ??ー                   */
        this.fontColorY          = null;
        /** 横軸フォ??トカ??ー                   */
        this.fontColorX          = null;
        /** バーフォ??トカ??ー                   */
        this.fontColorB          = null;
        /** バーの値フォ??トカ??ー               */
        this.fontColorB_S        = null;

        /** 横軸目盛り有無フ??グ                 */
        this.isScaleY            = false;
        /** 縦軸目盛り調整値(??減で左右に遷移)   */
        this.scaleDetailY        = 1;
        /** Y軸??目最大表示文????                */
        this.maxScaleLengthY     = 1;
        /** 左詰右詰めフ??グ(true:左、false:右)  */
        this.isHeadOrTail        = false;
        /** グ??フバーの値表示/非表示フ??グ      */
        this.isGraph_Bar_Status  = false;
    }


    /**********************************************************
     * グ??フ描写を実行します(??イ????????ソッド)
     *
     * @param g グ??フィクス
     **********************************************************/
    public void paint(Graphics g) {
        defaultCheck(fontH, fontS, fontU_Y, fontU_X, fontY, fontX, fontB,
                     fontColorH, fontColorS, fontColorU_Y, fontColorU_X, fontColorY, fontColorX, fontColorB,
                     data_set, data_label);

        makeHeader(g);

        // サブステータスの設定
        makeSubStatus(g);

        // X軸単位名称の設定
        makeUnitX(g);

        // Y軸単位名称の設定
        makeUnitY(g);

        // 縦軸の描写
        makeHeight(g);

        // 横軸の描写
        makeWidth(g);

        // 目盛りの描写
        makeBar(g);

        // 目盛りの値を描写
        makeBarStatus(g);

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

        setStyle(g, fontS, fontColorS);

        // ヘッダの文言と描写位置の設定
        g.drawString(this.Sub_Status_Name, this.Sub_Status_Width, this.Sub_Status_Height);
    }

    /**********************************************************
     * X軸単位名称を描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeUnitX(Graphics g){

        // フォ??トのスタイ??設定
        setStyle(g, fontU_X, fontColorU_X);

        // ヘッダの文言と描写位置の設定
        g.drawString(this.X_Unit_Name, this.X_Unit_Width, this.X_Unit_Height);

    }

    /**********************************************************
     * Y軸単位名称を描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeUnitY(Graphics g){

        // フォ??トのスタイ??設定
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

        // 縦軸を描写します(右側罫線)
        g.drawLine(
            Graph_offset + Graph_Width,
            Graph_offset,
            Graph_offset + Graph_Width,
            Graph_offset + Graph_Height);

        // 縦軸に??ベ??を入れます
        for (int i = 0; i < data_label.length; i++) {
            String checkedtext = "";

            // 基本State_Width(縦軸??目の位置(X軸方向に左右移動させます))
            State_Width = scaleDetailY/*114*/;

            // 半角英??文??、半角カタカナを全角文??に変換
			String chkT = data_label[i];


            if(isHeadOrTail){
                // Y軸??目を左詰めにする
                checkedtext = StringUtil.addtoN(chkT, maxScaleLengthY);
            } else {
                // Y軸??目を右詰めにする
                checkedtext = StringUtil.addtoN2(chkT, maxScaleLengthY);
            }

            // 縦軸目盛りを描写
            g.drawString(checkedtext,
                         Graph_offset - State_Width - 25,                        // 縦軸の目盛り(??値)の位置(横)
                         Graph_offset + Graph_Height - (i * Y_Tick_Height) + 5);

            setStyle(g, fontY, fontColorY);
            if(isScaleY){
                // グ??ッド線を描画します
                if (i > 0){
                    // グ??ッド線を描画します
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

            // グ??ッド線を描画します
            g.drawLine(
                Graph_offset + (X_Tick_Width * i) + (Graph_offset * i),
                Graph_offset,
                Graph_offset + (X_Tick_Width * i) + (Graph_offset * i),
                Graph_offset + Graph_Height);
        }

        // 横軸を描写します(上側罫線) // 微調整??が必要になるかもしれないので??意！
        g.drawLine(
            Graph_offset,
            (Graph_offset + Graph_Height - (data_set.length * Y_Tick_Height)) - 1,
            Graph_offset + Graph_Width ,
            (Graph_offset + Graph_Height - (data_set.length * Y_Tick_Height)) - 1);

        // 横軸を描写します(下側罫線)
        g.drawLine(
            Graph_offset,
            Graph_offset + Graph_Height,
            Graph_offset + Graph_Width,
            Graph_offset + Graph_Height);

        // 横軸に??ベ??を入れます
        for (int i = 0; i <= Graph_Devide; i++) {
            g.drawString(String.valueOf(i * Graph_Devide_Width),
            Graph_offset + (X_Tick_Width * i) + (Graph_offset * i) + Graph_Height_Detail,  // 横軸の目盛り(??値)の位置(横)
            Graph_offset + Graph_Height + 18);                                             // 横軸の目盛り(??値)の位置(縦)
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
            // 100%と考える値で??る
            int bar_width = data_set[i] * Graph_Width / maxValue;

            if (i > 0) {
                // グ??フバーを描画
                g.fillRect(Graph_offset,                                                         // X軸の始点
                           Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail, // Y軸の始点
                           bar_width,                                                            // X軸の終点
                           Graph_Bar_Thick);                                                     // Y軸の太さ(バーの太さ)
            }
        }

    }

    /**********************************************************
     * グ??フバーの値を描写します
     *
     * @param g グ??フィクス
     **********************************************************/
    private void makeBarStatus(Graphics g){

        // フォ??トスタイ??の設定
        setStyle(g, fontB_S, fontColorB_S);

        // グ??フバーの値を表示する場??
        if(isGraph_Bar_Status){

            // パ????ータの個??分だけ??ープ
            for (int i = 0; i < data_set.length; i++) {
                // X軸の位置計算
                int status = data_set[i] * Graph_Width / maxValue;

                if (i > 0) {
                    g.drawString(Integer.toString(data_set[i]),                          // 描写される文??(値)
                                 Graph_offset + status ,                                 // X軸の位置
                                 Graph_offset + Graph_Height - (i * Y_Tick_Height) + 5); // Y軸の位置
                }
            }
        }

    }

    /**********************************************************
     * GraphMakerク??スが呼ばれた際、不正値がINPUTされていないかチェックします<br>
     * 不正値が引き渡された場??、強制的にデフォ??ト設定を行います。
     *
     * @param fontH         ヘッダーフォ??ト
     * @param fontS         サブステータスフォ??ト
     * @param fontU_Y       Y軸単位フォ??ト
     * @param fontU_X       X軸単位フォ??ト
     * @param fontY         グ??フ縦軸フォ??ト
     * @param fontX         横軸フォ??ト
     * @param fontB         グ??フバーフォ??ト
     * @param fontColorH    ヘッダーフォ??トカ??ー
     * @param fontColorS    サブステータスフォ??トカ??ー
     * @param fontColorU_Y  Y軸単位フォ??トカ??ー
     * @param fontColorU_X  X軸単位フォ??トカ??ー
     * @param fontColorY    グ??フ縦軸フォ??トカ??ー
     * @param fontColorX    グ??フ横軸フォ??トカ??ー
     * @param fontColorB    グ??フバーフォ??トカ??ー
     * @param data_set      パ????ータの値(バーに表示される値)
     * @param data_label    横軸目盛り値
     **********************************************************/
    private void defaultCheck(Font fontH, Font fontS, Font fontU_Y, Font fontU_X, Font fontY, Font fontX, Font fontB,
                                Color fontColorH, Color fontColorS, Color fontColorU_Y, Color fontColorU_X,
                                Color fontColorY, Color fontColorX, Color fontColorB,
                                int[] data_set, String[] data_label){

        // フォ??トnull対策
        if(fontH == null){
            // デフォ??トフォ??トをセット
            this.fontH   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontS == null){
            // デフォ??トフォ??トをセット
            this.fontS   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontU_Y == null){
            // デフォ??トフォ??トをセット
            this.fontU_Y = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontU_X == null){
            // デフォ??トフォ??トをセット
            this.fontU_X = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontY == null){
            // デフォ??トフォ??トをセット
            this.fontY   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontX == null){
            // デフォ??トフォ??トをセット
            this.fontX   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontB == null){
            // デフォ??トフォ??トをセット
            this.fontB   = new Font("Courier", Font.PLAIN, 10);
        }
        if(fontB_S == null){
            // デフォ??トフォ??トをセット
            this.fontB_S = new Font("Courier", Font.PLAIN, 10);
        }

        // フォ??トカ??ーnull対策
        if(fontColorH == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorH   = Color.BLACK;
        }
        // フォ??トカ??ーnull対策
        if(fontColorS == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorS   = Color.BLACK;
        }
        // フォ??トカ??ーnull対策
        if(fontColorU_Y == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorU_Y = Color.BLACK;
        }
        // フォ??トカ??ーnull対策
        if(fontColorU_X == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorU_X = Color.BLACK;
        }
        // フォ??トカ??ーnull対策
        if(fontColorY == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorY   = Color.BLACK;
        }
        if(fontColorX == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorX   = Color.BLACK;
        }
        if(fontColorB == null){
            // デフォ??トフォ??トカ??ー(??)をセット
            this.fontColorB   = Color.BLACK;
        }
        if(fontColorB_S == null){
            // デフォ??トフォ??トカ??ー(??)をセット
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
