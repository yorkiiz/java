/********************************************************************************/
/*  System :             WCFM 3.0                                               */
/*  File Name :          GraphMakerCommonDataSet.java                           */
/*  File Description :   グラフメーカー汎用データセットクラス                   */
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
 * グラフメーカー汎用データセットクラス
 **********************************************************/
public class GraphMakerCommonDataSet {

    /** グラフ描画位置の原点からのX座標オフセット */
    private int Graph_offset_X;
	/** グラフ描画位置の原点からのY座標オフセット */
	private int Graph_offset_Y;
    /** グラフの大きさ(高さ)                 */
    private int Graph_Height;
    /** バーで100%(MAX値)と考える値          */
    private int maxValue;

    /** ヘッダータイトル名称                 */
    private String Header_Name;
    /** ヘッダー位置(横軸)                   */
    private int Header_Width;
    /** ヘッダー位置(縦軸)                   */
    private int Header_Height;

    /** サブステータス名称                   */
    private String Sub_Status_Name;
    /** サブステータス位置(横軸)             */
    private int Sub_Status_Width;
    /** サブステータス位置(縦軸)             */
    private int Sub_Status_Height;

    /** X軸単位名称                          */
    private String X_Unit_Name;
    /** X軸単位位置(横軸)                    */
    private int X_Unit_Width;
    /** X軸単位位置(縦軸)                    */
    private int X_Unit_Height;

    /** Y軸単位名称                          */
    private String Y_Unit_Name;
    /** Y軸単位位置(横軸)                    */
    private int Y_Unit_Width;
    /** Y軸単位位置(縦軸)                    */
    private int Y_Unit_Height;

    /** 縦軸の分割数                         */
    private int Graph_Devide;
    /** 縦軸の目盛り間の値                   */
    private int Graph_Devide_Height;
    /** 縦軸の1分割分の高さ                  */
    private int Y_Tick_Height;
    
    /** 縦軸の目盛り(数値)の位置の調整値     */
    private int Graph_Width_Detail;

    /** グラフの横幅                         */
    private int Graph_Width;
    /** 横軸の分割数                         */
    private int X_Tick_Width;
    /** 横軸の目盛り間の値                   */
    private int Graph_Devide_Width;
	/** 横軸の目盛り(サブ)の上下位置調整値   */
	private int XLabelSubDetail;

    /** グラフバーの位置調整値               */
    private int Graph_Bar_Detail;
    /** グラフバーの太さ                     */
    private int Graph_Bar_Thick;

    /** ヘッダーフォント                     */
    private Font fontH;
    /** サブステータスフォント               */
    private Font fontS;
    /** Y軸単位フォント                      */
    private Font fontU_Y;
    /** X軸単位フォント                      */
    private Font fontU_X;
    /** 縦軸フォント                         */
    private Font fontY;
    /** 横軸フォント                         */
    private Font fontX;
    /** バーフォント                         */
    private Font fontB;
    /** バーの値フォント                     */
    private Font fontB_S;

    /** ヘッダーフォントカラー               */
    private Color fontColorH;
    /** サブステータスフォントカラー         */
    private Color fontColorS;
    /** Y軸単位フォントカラー                */
    private Color fontColorU_Y;
    /** X軸単位フォントカラー                */
    private Color fontColorU_X;
    /** 縦軸フォントカラー                   */
    private Color fontColorY;
    /** 横軸フォントカラー                   */
    private Color fontColorX;
    /** バーフォントカラー                   */
    private Color fontColorB;
    /** バーの値フォントカラー               */
    private Color fontColorB_S;

    /** 縦軸目盛り有無フラグ                 */
    private boolean isScaleY;
    /** 縦軸目盛り調整値(増減で左右に遷移)   */
    private int scaleDetailY;
    /** Y軸項目最大表示文字数                */
    private int maxScaleLengthY;
    /** 左詰右詰めフラグ(true:左、false:右)  */
    private boolean isHeadOrTail;

    /** グラフバーの値表示/非表示フラグ      */
    private boolean isGraph_Bar_Status;

    /** 縦軸目もり値(index 0はダミー)        */
    private String[] data_label_Y = {};
    /** 横軸目もり値(index 0はダミー)        */
    private String[] data_label_X = {};
	/** 横軸目もり値サブ(index 0はダミー)    */
	private String[] data_label_X_Sub = {};
    /** バーの区切り数                       */
//    private int[] data_Div = {};
    /** X軸上段ライン位置上下微調整値        */
    private int lineDetailXTop;

    /** 区切り線間隔の微調整値               */
    private int detailDecade;
	/** 区切り線位置の微調整値               */
    private int detailState;

    /** グラフバーList                       */
    private List list       = null;
    /** グラフバー詳細List                   */
    private List detailList = null;

    /** コンストラクタ */
    public GraphMakerCommonDataSet() {
        // Listの初期化
        list       = new ArrayList(0);
        // グラフバー詳細Listの初期化(行わない)
        detailList = new ArrayList(0);
    }


    /**********************************************************
     * グラフ描画位置の原点からのX座標オフセットのセット
     * @param  int グラフ描画位置の原点からのオフセット
     * @return なし
     **********************************************************/
    public void setGraphOffset_X(int Graph_offset_X) {
        this.Graph_offset_X = Graph_offset_X;
    }

    /**********************************************************
     * グラフ描画位置の原点からのX座標オフセットの取得
     * @return グラフ描画位置の原点からのオフセット
     **********************************************************/
    public int getGraphOffset_X() {
        return this.Graph_offset_X;
    }

	/**********************************************************
	 * グラフ描画位置の原点からのY座標オフセットのセット
	 * @param  int グラフ描画位置の原点からのオフセット
	 * @return なし
	 **********************************************************/
	public void setGraphOffset_Y(int Graph_offset_Y) {
		this.Graph_offset_Y = Graph_offset_Y;
	}

	/**********************************************************
	 * グラフ描画位置の原点からのY座標オフセットの取得
	 * @return グラフ描画位置の原点からのオフセット
	 **********************************************************/
	public int getGraphOffset_Y() {
		return this.Graph_offset_Y;
	}
	
    /**********************************************************
     * グラフの大きさ(高さ)のセット
     *
     * @param  int グラフの大きさ(高さ)
     * @return なし
     **********************************************************/
    public void setGraphHeight(int Graph_Height) {
        this.Graph_Height = Graph_Height;
    }

    /**********************************************************
     * グラフの大きさ(高さ)の取得
     *
     * @return グラフの大きさ(高さ)
     **********************************************************/
    public int getGraphHeight() {
        return this.Graph_Height;
    }

    /**********************************************************
     * バーで100%(MAX値)と考える値のセット
     *
     * @param  int バーで100%(MAX値)と考える値
     * @return なし
     **********************************************************/
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**********************************************************
     * バーで100%(MAX値)と考える値の取得
     *
     * @return バーで100%(MAX値)と考える値
     **********************************************************/
    public int getMaxValue() {
        return this.maxValue;
    }

    /**********************************************************
     * ヘッダータイトル名称のセット
     *
     * @param  String ヘッダータイトル名称
     * @return なし
     **********************************************************/
    public void setHeaderName(String Header_Name) {
        this.Header_Name = Header_Name;
    }

    /**********************************************************
     * ヘッダータイトル名称の取得
     *
     * @return ヘッダータイトル名称
     **********************************************************/
    public String getHeaderName() {
        return this.Header_Name;
    }

    /**********************************************************
     * ヘッダー位置(横軸)のセット
     *
     * @param  int ヘッダー位置(横軸)
     * @return なし
     **********************************************************/
    public void setHeaderWidth(int Header_Width) {
        this.Header_Width = Header_Width;
    }

    /**********************************************************
     * ヘッダー位置(横軸)の取得
     *
     * @return ヘッダー位置(横軸)
     **********************************************************/
    public int getHeaderWidth() {
        return this.Header_Width;
    }

    /**********************************************************
     * ヘッダー位置(縦軸)のセット
     *
     * @param  int ヘッダー位置(縦軸)
     * @return なし
     **********************************************************/
    public void setHeaderHeight(int Header_Height) {
        this.Header_Height = Header_Height;
    }

    /**********************************************************
     * ヘッダー位置(縦軸)の取得
     *
     * @return ヘッダー位置(縦軸)
     **********************************************************/
    public int getHeaderHeight() {
        return this.Header_Height;
    }

    /**********************************************************
     * サブステータス名称のセット
     *
     * @param  String サブステータス名称
     * @return なし
     **********************************************************/
    public void setSubStatusName(String Sub_Status_Name) {
        this.Sub_Status_Name = Sub_Status_Name;
    }

    /**********************************************************
     * サブステータス名称の取得
     *
     * @return サブステータス名称
     **********************************************************/
    public String getSubStatusName() {
        return this.Sub_Status_Name;
    }

    /**********************************************************
     * サブステータス位置(横軸)のセット
     *
     * @param  int サブステータス位置(横軸)
     * @return なし
     **********************************************************/
    public void setSubStatusWidth(int Sub_Status_Width) {
        this.Sub_Status_Width = Sub_Status_Width;
    }

    /**********************************************************
     * サブステータス位置(横軸)の取得
     *
     * @return サブステータス位置(横軸)
     **********************************************************/
    public int getSubStatusWidth() {
        return this.Sub_Status_Width;
    }

    /**********************************************************
     * サブステータス位置(縦軸)のセット
     *
     * @param  int サブステータス位置(縦軸)
     * @return なし
     **********************************************************/
    public void setSubStatusHeight(int Sub_Status_Height) {
        this.Sub_Status_Height = Sub_Status_Height;
    }

    /**********************************************************
     * サブステータス位置(縦軸)の取得
     *
     * @return サブステータス位置(縦軸)
     **********************************************************/
    public int getSubStatusHeight() {
        return this.Sub_Status_Height;
    }

    /**********************************************************
     * X軸単位名称のセット
     *
     * @param  String X軸単位名称
     * @return なし
     **********************************************************/
    public void setXUnitName(String X_Unit_Name) {
        this.X_Unit_Name = X_Unit_Name;
    }

    /**********************************************************
     * X軸単位名称の取得
     *
     * @return X軸単位名称
     **********************************************************/
    public String getXUnitName() {
        return this.X_Unit_Name;
    }

    /**********************************************************
     * X軸単位位置(横軸)のセット
     *
     * @param  int X軸単位位置(横軸)
     * @return なし
     **********************************************************/
    public void setXUnitWidth(int X_Unit_Width) {
        this.X_Unit_Width = X_Unit_Width;
    }

    /**********************************************************
     * X軸単位位置(横軸)の取得
     *
     * @return X軸単位位置(横軸)
     **********************************************************/
    public int getXUnitWidth() {
        return this.X_Unit_Width;
    }

    /**********************************************************
     * X軸単位位置(縦軸)のセット
     *
     * @param  int X軸単位位置(縦軸)
     * @return なし
     **********************************************************/
    public void setXUnitHeight(int X_Unit_Height) {
        this.X_Unit_Height = X_Unit_Height;
    }

    /**********************************************************
     * X軸単位位置(縦軸)の取得
     *
     * @return X軸単位位置(縦軸)
     **********************************************************/
    public int getXUnitHeight() {
        return this.X_Unit_Height;
    }

    /**********************************************************
     * Y軸単位名称のセット
     *
     * @param  String Y軸単位名称
     * @return なし
     **********************************************************/
    public void setYUnitName(String Y_Unit_Name) {
        this.Y_Unit_Name = Y_Unit_Name;
    }

    /**********************************************************
     * Y軸単位名称の取得
     *
     * @return Y軸単位名称
     **********************************************************/
    public String getYUnitName() {
        return this.Y_Unit_Name;
    }

    /**********************************************************
     * Y軸単位位置(横軸)のセット
     *
     * @param  int Y軸単位位置(横軸)
     * @return なし
     **********************************************************/
    public void setYUnitWidth(int Y_Unit_Width) {
        this.Y_Unit_Width = Y_Unit_Width;
    }

    /**********************************************************
     * Y軸単位位置(横軸)の取得
     *
     * @return Y軸単位位置(横軸)
     **********************************************************/
    public int getYUnitWidth() {
        return this.Y_Unit_Width;
    }

    /**********************************************************
     * Y軸単位位置(縦軸)のセット
     *
     * @param  int Y軸単位位置(縦軸)
     * @return なし
     **********************************************************/
    public void setYUnitHeight(int Y_Unit_Height) {
        this.Y_Unit_Height = Y_Unit_Height;
    }

    /**********************************************************
     * Y軸単位位置(縦軸)の取得
     *
     * @return Y軸単位位置(縦軸)
     **********************************************************/
    public int getYUnitHeight() {
        return this.Y_Unit_Height;
    }

    /**********************************************************
     * 縦軸の分割数のセット
     *
     * @param  int 縦軸の分割数
     * @return なし
     **********************************************************/
    public void setGraphDevide(int Graph_Devide) {
        this.Graph_Devide = Graph_Devide;
    }

    /**********************************************************
     * 縦軸の分割数の取得
     *
     * @return 縦軸の分割数
     **********************************************************/
    public int getGraphDevide() {
        return this.Graph_Devide;
    }

    /**********************************************************
     * 縦軸の目盛り間の値のセット
     *
     * @param  int 縦軸の目盛り間の値
     * @return なし
     **********************************************************/
    public void setGraphDevideHeight(int Graph_Devide_Height) {
        this.Graph_Devide_Height = Graph_Devide_Height;
    }

    /**********************************************************
     * 縦軸の目盛り間の値の取得
     *
     * @return 縦軸の目盛り間の値
     **********************************************************/
    public int getGraphDevideHeight() {
        return this.Graph_Devide_Height;
    }

    /**********************************************************
     * 縦軸の1分割分の高さのセット
     *
     * @param  int 縦軸の1分割分の高さ
     * @return なし
     **********************************************************/
    public void setYTickHeight(int Y_Tick_Height) {
        this.Y_Tick_Height = Y_Tick_Height;
    }

    /**********************************************************
     * 縦軸の1分割分の高さの取得
     *
     * @return 縦軸の1分割分の高さ
     **********************************************************/
    public int getYTickHeight() {
        return this.Y_Tick_Height;
    }

    /**********************************************************
     * 横軸の目盛り(数値)の位置の調整値のセット
     *
     * @param  int 横軸の目盛り(数値)の位置の調整値
     * @return なし
     **********************************************************/
    public void setGraphWidthDetail(int Graph_Width_Detail) {
        this.Graph_Width_Detail = Graph_Width_Detail;
    }

    /**********************************************************
     * 横軸の目盛り(数値)の位置の調整値の取得
     *
     * @return 横軸の目盛り(数値)の位置の調整値
     **********************************************************/
    public int getGraphWidthDetail() {
        return this.Graph_Width_Detail;
    }

    /**********************************************************
     * グラフの横幅のセット
     *
     * @param  int グラフの横幅
     * @return なし
     **********************************************************/
    public void setGraphWidth(int Graph_Width) {
        this.Graph_Width = Graph_Width;
    }

    /**********************************************************
     * グラフの横幅の取得
     *
     * @return グラフの横幅
     **********************************************************/
    public int getGraphWidth() {
        return this.Graph_Width;
    }

    /**********************************************************
     * 横軸の分割数のセット
     *
     * @param  int 横軸の分割数
     * @return なし
     **********************************************************/
    public void setXTickWidth(int X_Tick_Width) {
        this.X_Tick_Width= X_Tick_Width;
    }

    /**********************************************************
     * 横軸の分割数の取得
     *
     * @return 横軸の分割数
     **********************************************************/
    public int getXTickWidth() {
        return this.X_Tick_Width;
    }

	/**********************************************************
	 * 横軸の目盛り間の値のセット
	 *
	 * @param  int 横軸の目盛り間の値
	 * @return なし
	 **********************************************************/
	public void setGraphDevideWidth(int Graph_Devide_Width) {
		this.Graph_Devide_Width = Graph_Devide_Width;
	}

	/**********************************************************
	 * 横軸の目盛り間の値の取得
	 *
	 * @return 横軸の目盛り間の値
	 **********************************************************/
	public int getGraphDevideWidth() {
		return this.Graph_Devide_Width;
	}

	/**********************************************************
	 * 横軸の目盛り(サブ)の上下位置調整値のセット
	 *
	 * @return 横軸の目盛り間の値
	 **********************************************************/
	public void setXLabelSubDetail(int XLabelSubDetail) {
		this.XLabelSubDetail = XLabelSubDetail;
	}

	/**********************************************************
	 * 横軸の目盛り(サブ)の上下位置調整値の取得
	 *
	 * @return 横軸の目盛り間の値
	 **********************************************************/
	public int getXLabelSubDetail() {
		return this.XLabelSubDetail;
	}

    /**********************************************************
     * グラフバーの位置調整値のセット
     *
     * @param  int グラフバーの位置調整値
     * @return なし
     **********************************************************/
    public void setGraphBarDetail(int Graph_Bar_Detail) {
        this.Graph_Bar_Detail = Graph_Bar_Detail;
    }

    /**********************************************************
     * グラフバーの位置調整値の取得
     *
     * @return グラフバーの位置調整値
     **********************************************************/
    public int getGraphBarDetail() {
        return this.Graph_Bar_Detail;
    }

    /**********************************************************
     * グラフバーの太さのセット
     *
     * @param  int グラフバーの太さ
     * @return なし
     **********************************************************/
    public void setGraphBarThick(int Graph_Bar_Thick) {
        this.Graph_Bar_Thick = Graph_Bar_Thick;
    }

    /**********************************************************
     * グラフバーの太さの取得
     *
     * @return グラフバーの太さ
     **********************************************************/
    public int getGraphBarThick() {
        return this.Graph_Bar_Thick;
    }

    /**********************************************************
     * ヘッダーフォントのセット
     *
     * @param  Font ヘッダーフォント
     * @return なし
     **********************************************************/
    public void setFontH(Font fontH) {
        this.fontH = fontH;
    }

    /**********************************************************
     * ヘッダーフォントの取得
     *
     * @return ヘッダーフォント
     **********************************************************/
    public Font getFontH() {
        return this.fontH;
    }

    /**********************************************************
     * サブステータスフォントのセット
     *
     * @param  fontS サブステータスフォント
     * @return なし
     **********************************************************/
    public void setFontS(Font fontS) {
        this.fontS = fontS;
    }

    /**********************************************************
     * サブステータスフォントの取得
     *
     * @return サブステータスフォント
     **********************************************************/
    public Font getFontS() {
        return this.fontS;
    }

    /**********************************************************
     * Y軸単位フォントのセット
     *
     * @param  Font Y軸単位フォント
     * @return なし
     **********************************************************/
    public void setFontU_Y(Font fontU_Y) {
        this.fontU_Y = fontU_Y;
    }

    /**********************************************************
     * Y軸単位フォントの取得
     *
     * @return Y軸単位フォント
     **********************************************************/
    public Font getFontU_Y() {
        return this.fontU_Y;
    }

    /**********************************************************
     * X軸単位フォントのセット
     *
     * @param  Font X軸単位フォント
     * @return なし
     **********************************************************/
    public void setFontU_X(Font fontU_X) {
        this.fontU_X = fontU_X;
    }

    /**********************************************************
     * X軸単位フォントの取得
     *
     * @return X軸単位フォント
     **********************************************************/
    public Font getFontU_X() {
        return this.fontU_X;
    }

    /**********************************************************
     * 縦軸フォントのセット
     *
     * @param  Font 縦軸フォント
     * @return なし
     **********************************************************/
    public void setFontY(Font fontY) {
        this.fontY = fontY;
    }

    /**********************************************************
     * 縦軸フォントの取得
     *
     * @return 縦軸フォント
     **********************************************************/
    public Font getFontY() {
        return this.fontY;
    }

    /**********************************************************
     * 横軸フォントのセット
     *
     * @param  Font 横軸フォント
     * @return なし
     **********************************************************/
    public void setFontX(Font fontX) {
        this.fontX = fontX;
    }

    /**********************************************************
     * 横軸フォントの取得
     *
     * @return 横軸フォント
     **********************************************************/
    public Font getFontX() {
        return this.fontX;
    }

    /**********************************************************
     * バーフォントのセット
     *
     * @param  Font バーフォント
     * @return なし
     **********************************************************/
    public void setFontB(Font fontB) {
        this.fontB = fontB;
    }

    /**********************************************************
     * バーフォントの取得
     *
     * @return バーフォント
     **********************************************************/
    public Font getFontB() {
        return this.fontB;
    }

    /**********************************************************
     * バーの値フォントのセット
     *
     * @param  Font バーの値フォント
     * @return なし
     **********************************************************/
    public void setFontB_S(Font fontB_S) {
        this.fontB_S = fontB_S;
    }

    /**********************************************************
     * バーの値フォントの取得
     *
     * @return バーの値フォント
     **********************************************************/
    public Font getFontB_S() {
        return this.fontB_S;
    }

    /**********************************************************
     * ヘッダーフォントカラーのセット
     *
     * @param  Color ヘッダーフォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorH(Color fontColorH) {
        this.fontColorH = fontColorH;
    }

    /**********************************************************
     * ヘッダーフォントカラーの取得
     *
     * @return ヘッダーフォントカラー
     **********************************************************/
    public Color getFontColorH() {
        return this.fontColorH;
    }

    /**********************************************************
     * サブステータスフォントカラーのセット
     *
     * @param  Color サブステータスフォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorS(Color fontColorS) {
        this.fontColorS = fontColorS;
    }

    /**********************************************************
     * サブステータスフォントカラーの取得
     *
     * @return サブステータスフォントカラー
     **********************************************************/
    public Color getFontColorS() {
        return this.fontColorS;
    }

    /**********************************************************
     * Y軸単位フォントカラーのセット
     *
     * @param  Color Y軸単位フォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorU_Y(Color fontColorU_Y) {
        this.fontColorU_Y = fontColorU_Y;
    }

    /**********************************************************
     * Y軸単位フォントカラーの取得
     *
     * @return Y軸単位フォントカラー
     **********************************************************/
    public Color getFontColorU_Y() {
        return this.fontColorU_Y;
    }

    /**********************************************************
     * X軸単位フォントカラーのセット
     *
     * @param  Color X軸単位フォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorU_X(Color fontColorU_X) {
        this.fontColorU_X = fontColorU_X;
    }

    /**********************************************************
     * X軸単位フォントカラーの取得
     *
     * @return X軸単位フォントカラー
     **********************************************************/
    public Color getFontColorU_X() {
        return this.fontColorU_X;
    }

    /**********************************************************
     * 縦軸フォントカラーのセット
     *
     * @param  Color 縦軸フォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorY(Color fontColorY) {
        this.fontColorY = fontColorY;
    }

    /**********************************************************
     * 縦軸フォントカラーの取得
     *
     * @return 縦軸フォントカラー
     **********************************************************/
    public Color getFontColorY() {
        return this.fontColorY;
    }

    /**********************************************************
     * 横軸フォントカラーのセット
     *
     * @param  Color 横軸フォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorX(Color fontColorX) {
        this.fontColorX = fontColorX;
    }

    /**********************************************************
     * 横軸フォントカラーの取得
     *
     * @return 横軸フォントカラー
     **********************************************************/
    public Color getFontColorX() {
        return this.fontColorX;
    }

    /**********************************************************
     * バーフォントカラーのセット
     *
     * @param  Color バーフォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorB(Color fontColorB) {
        this.fontColorB = fontColorB;
    }

    /**********************************************************
     * バーフォントカラーの取得
     *
     * @return バーフォントカラー
     **********************************************************/
    public Color getFontColorB() {
        return this.fontColorB;
    }

    /**********************************************************
     * バーの値フォントカラーのセット
     *
     * @param  Color バーの値フォントカラー
     * @return なし
     **********************************************************/
    public void setFontColorB_S(Color fontColorB_S) {
        this.fontColorB_S = fontColorB_S;
    }

    /**********************************************************
     * バーの値フォントカラーの取得
     *
     * @return バーの値フォントカラー
     **********************************************************/
    public Color getFontColorB_S() {
        return this.fontColorB_S;
    }

    /**********************************************************
     * 縦軸目盛り有無フラグのセット
     *
     * @param  boolean 縦軸目盛り有無フラグ
     * @return なし
     **********************************************************/
    public void setIsScaleY(boolean isScaleY) {
        this.isScaleY = isScaleY;
    }

    /**********************************************************
     * 縦軸目盛り有無フラグの取得
     *
     * @return バーの値フォントカラー
     **********************************************************/
    public boolean getIsScaleY() {
        return this.isScaleY;
    }

    /**********************************************************
     * 縦軸目盛り調整値(増減で左右に遷移)のセット
     *
     * @param  int 縦軸目盛り調整値(増減で左右に遷移)
     * @return なし
     **********************************************************/
    public void setScaleDetailY(int scaleDetailY) {
        this.scaleDetailY = scaleDetailY;
    }

    /**********************************************************
     * 縦軸目盛り調整値(増減で左右に遷移)の取得
     *
     * @return 縦軸目盛り調整値(増減で左右に遷移)
     **********************************************************/
    public int getScaleDetailY() {
        return this.scaleDetailY;
    }

    /**********************************************************
     * Y軸項目最大表示文字数のセット
     *
     * @param  int Y軸項目最大表示文字数
     * @return なし
     **********************************************************/
    public void setMaxScaleLengthY(int maxScaleLengthY) {
        this.maxScaleLengthY = maxScaleLengthY;
    }

    /**********************************************************
     * Y軸項目最大表示文字数の取得
     *
     * @return Y軸項目最大表示文字数
     **********************************************************/
    public int getMaxScaleLengthY() {
        return this.maxScaleLengthY;
    }

    /**********************************************************
     * 左詰右詰めフラグ(true:左、false:右)のセット
     *
     * @param  boolean 左詰右詰めフラグ(true:左、false:右)
     * @return なし
     **********************************************************/
    public void setIsHeadOrTail(boolean isHeadOrTail) {
        this.isHeadOrTail = isHeadOrTail;
    }

    /**********************************************************
     * 左詰右詰めフラグ(true:左、false:右)の取得
     *
     * @return 左詰右詰めフラグ(true:左、false:右)
     **********************************************************/
    public boolean getIsHeadOrTail() {
        return this.isHeadOrTail;
    }

    /**********************************************************
     * グラフバーの値表示/非表示フラグのセット
     *
     * @param  boolean グラフバーの値表示/非表示フラグ
     * @return なし
     **********************************************************/
    public void setIsGraphBarStatus(boolean isGraph_Bar_Status) {
        this.isGraph_Bar_Status = isGraph_Bar_Status;
    }

    /**********************************************************
     * グラフバーの値表示/非表示フラグの取得
     *
     * @return グラフバーの値表示/非表示フラグ
     **********************************************************/
    public boolean getIsGraphBarStatus() {
        return this.isGraph_Bar_Status;
    }

    /**********************************************************
     * 縦軸目もり値のセット<br>
     * index0はダミー値なので空文字を必ずセットしてください
     *
     * @param  String[] 縦軸目もり値
     * @return なし
     **********************************************************/
    public void setDataLabelY(String[] data_label_Y) {
        this.data_label_Y = data_label_Y;
    }

    /**********************************************************
     * 縦軸目もり値の取得
     *
     * @return 縦軸目もり値
     **********************************************************/
    public String[] getDataLabelY() {
        return this.data_label_Y;
    }

    /**********************************************************
     * 横軸目もり値のセット<br>
     * 注)ここでセットされる配列の要素数は setDataDiv()、setDataLabelXSub()<br>
     * の要素数と同じでなくてはなりません。
     *
     * @param  String[] 横軸目もり値
     * @return なし
     **********************************************************/
    public void setDataLabelX(String[] data_label_X) {
        this.data_label_X = data_label_X;
    }

    /**********************************************************
     * 横軸目もり値の取得
     *
     * @return 横軸目もり値
     **********************************************************/
    public String[] getDataLabelX() {
        return this.data_label_X;
    }
    
	/**********************************************************
	 * 横軸目もり値(サブ)のセット<br>
     * 注)ここでセットされる配列の要素数は setDataLabelX()、setDataDiv()<br>
     * の要素数と同じでなくてはなりません。
	 *
	 * @param  String[] 横軸目もり値
	 * @return なし
	 **********************************************************/
	public void setDataLabelXSub(String[] data_label_X_Sub) {
		this.data_label_X_Sub = data_label_X_Sub;
	}

	/**********************************************************
	 * 横軸目もり値(サブ)の取得
	 *
	 * @return 横軸目もり値
	 **********************************************************/
	public String[] getDataLabelXSub() {
		return this.data_label_X_Sub;
	}

    /**********************************************************
     * X軸横線の上段の線調整値
     *
     * @param int X軸横線の上段の線調整値
     **********************************************************/
    public void setLineDetailXTop(int lineDetailXTop) {
        this.lineDetailXTop = lineDetailXTop;
    }

    /**********************************************************
     * X軸横線の上段の線調整値の取得
     *
     * @return X軸横線の上段の線調整値
     **********************************************************/
    public int getLineDetailXTop() {
        return lineDetailXTop;
    }

	/**********************************************************
	 * 区切り線間隔の微調整値のセット<br>
	 * 使用の推奨はしません。
	 *
	 * @param int 区切り線間隔の微調整値
	 **********************************************************/
	public void setDetailDecade(int detailDecade) {
		this.detailDecade = detailDecade;
	}

	/**********************************************************
	 * 区切り線間隔の微調整値の取得<br>
	 * 使用の推奨はしません。
	 * 
	 * @return 区切り線間隔の微調整値
	 **********************************************************/
	public int getDetailDecade() {
		return detailDecade;
	}
	
	/**********************************************************
	 * 区切り線位置の微調整値のセット<br>
	 * 使用の推奨はしません。
	 *
	 * @param int 区切り線間隔の微調整値
	 **********************************************************/
	public void setDetailState(int detailState) {
		this.detailState = detailState;
	}

	/**********************************************************
	 * 区切り線位置の微調整値の取得<br>
	 * 使用の推奨はしません。
	 * 
	 * @return 区切り線間隔の微調整値
	 **********************************************************/
	public int getDetailState() {
		return detailState;
	}

    /**********************************************************
     *
     *
     * @return なし
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
     * @return なし
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
     * リストのサイズを返します
     *
     * @return int
     **********************************************************/
    public int getListSize() {
        return this.list.size();
    }

    /**********************************************************
     * リストのサイズを返します
     *
     * @return int
     **********************************************************/
    public int getDetailListSize() {
        return this.detailList.size();
    }

    /***********************************************************
     * データクリア
     *
     ***********************************************************/
    public void clearList() {
        if (null != list) {
            this.list.clear();
        }
    }

    /***********************************************************
     * データクリア
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
    /*  File Description :   グラフメーカー汎用データセット(バー詳細)インナークラス */
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
     * グラフメーカー汎用データセット(バー詳細)インナークラス
     **********************************************************/
    public static class GraphMakerCommonDetailDataSet {

        /** グラフバー上に表示するテキスト文字列        */
        private String text     = null;

        /** グラフバー上に表示するテキストの色          */
        private Color textColor = null;

        /** グラフバーの色                              */
        private Color barColor  = null;

        /** グラフバーの100％に対する値の割合(バーの値) */
        private int rate       = 0;
		
        /**********************************************************
         * グラフバー上に表示するテキスト文字列のセット
         *
         * @return String グラフバー上に表示するテキスト文字列
         **********************************************************/
        public void setText(String text) {
            this.text = text;
        }

        /**********************************************************
         * グラフバー上に表示するテキスト文字列の取得
         *
         * @return グラフバー上に表示するテキスト文字列
         **********************************************************/
        public String getText() {
            return this.text;
        }

        /**********************************************************
         * グラフバー上に表示するテキストの色のセット
         *
         * @param Color グラフバー上に表示するテキストの色
         **********************************************************/
        public void setTextColor(Color textColor) {
            this.textColor = textColor;
        }

        /**********************************************************
         * グラフバー上に表示するテキストの色の取得
         *
         * @return グラフバー上に表示するテキスト文字列
         **********************************************************/
        public Color getTextColor() {
            return this.textColor;
        }

        /**********************************************************
         * グラフバーの色のセット
         *
         * @param Color グラフバーの色
         **********************************************************/
        public void setBarColor(Color barColor) {
            this.barColor = barColor;
        }

        /**********************************************************
         * グラフバーの色の取得
         *
         * @return グラフバーの色
         **********************************************************/
        public Color getBarColor() {
            return this.barColor;
        }

        /**********************************************************
         * グラフバーの100％に対する値の割合(バーの値)のセット
         *
         * @param int グラフバーの100％に対する値(バーの値)の割合
         **********************************************************/
        public void setRate(int rate) {
            this.rate = rate;
        }

        /**********************************************************
         * グラフバーの100％に対する値の割合(バーの値)の取得
         *
         * @return グラフバーの100％に対する値の割合(バーの値)
         **********************************************************/
        public int getRate() {
            return rate;
        }
    }
}
