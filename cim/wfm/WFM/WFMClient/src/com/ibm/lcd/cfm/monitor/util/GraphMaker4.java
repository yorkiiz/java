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
	/** 横軸目もり値(index 0はダミー)        */
	private String[] data_label_X;
	/** 横軸目もり値サブ(index 0はダミー)    */
	private String[] data_label_X_Sub;

	/** グ??フ描画位置の原点からのX座標オフセット */
	private int Graph_offset_X;
	/** グ??フ描画位置の原点からのY座標オフセット */
	private int Graph_offset_Y;
	/** グ??フの大きさ(??さ)                 */
	private int Graph_Height;
	/** バーで100%(MAX値)と考える値          */
	private int maxValue;

	/** ヘッダータイト??名称                 */
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

	/** 縦軸の分????                         */
	private int Graph_Devide;
	/** 縦軸の目盛り間の値                   */
	//private int Graph_Devide_Height;
	/** 縦軸の1分??分の??さ                  */
	private int Y_Tick_Height;
	
	/** グ??フの横??                         */
	private int Graph_Width;
	/** 横軸の分????                         */
	//private int X_Tick_Width;
	/** 横軸の目盛り間の値                   */
	//private int Graph_Devide_Width;
	/** 横軸の目盛り(サブ)の上下位置調整値   */
	private int XLabelSubDetail;
	/** 横軸の目盛り(??値)の位置の調整値     */
	private int Graph_Width_Detail; //-7

	/** グ??フバーの位置調整値               */
	private int Graph_Bar_Detail;
	/** グ??フバーの太さ                     */
	private int Graph_Bar_Thick;

	/** ヘッダーフォ??ト                     */
	private Font fontH;
	/** サブステータスフォ??ト               */
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
	/** X軸上段??イ??位置上下微調整値        */
	private int lineDetailXTop;

	/** 縦区切り線の間隔(??)の微調整値       */
	private int detailDecade;
	/** 縦区切り線の間隔(位置)の微調整値       */
	private int detailState;

	/** グ??フ??ーカー汎用データセット       */
	private GraphMakerCommonDataSet data;

	/**************************************************************************
	 * コ??スト??クタ<br>
	 * グ??フの各パーツの??期設定を行います。<br>
	 *
	 * @param dataSet グ??フ??ーカー汎用データセット
	 *************************************************************************/
	public GraphMaker4(GraphMakerCommonDataSet dataSet) {

		// デフォ??ト値のセット
		setDefault();

		this.data_label_Y        = dataSet.getDataLabelY();	// 縦軸目もり値(index 0はダミー)
		this.data_label_X        = dataSet.getDataLabelX();	// 横軸目もり値(index 0はダミー)
		this.data_label_X_Sub    = dataSet.getDataLabelXSub();	// 横軸目もり値サブ(index 0はダミー)
		
		this.Graph_offset_X      = dataSet.getGraphOffset_X();			// グ??フ描画位置の原点からのX座標オフセット
		this.Graph_offset_Y      = dataSet.getGraphOffset_Y();			// グ??フ描画位置の原点からのY座標オフセット
		this.Graph_Height        = dataSet.getGraphHeight();			// グ??フの大きさ(??さ)
		this.maxValue            = dataSet.getMaxValue();				// バーで100%(MAX値)と考える値
		this.Graph_Devide        = dataSet.getDataLabelX().length - 1;	// 縦軸の分????
		this.Graph_Width         = dataSet.getGraphWidth();			// グ??フの横??
		this.Graph_Width_Detail  = dataSet.getGraphWidthDetail();		// 横軸の目盛り(??値)の位置の調整値
		this.Graph_Bar_Detail    = dataSet.getGraphBarDetail();		// グ??フバーの位置調整値
		this.Graph_Bar_Thick     = dataSet.getGraphBarThick();			// グ??フバーの太さ
		this.Header_Name         = dataSet.getHeaderName();			// ヘッダー名称
		this.Header_Width        = dataSet.getHeaderWidth();			// ヘッダー位置(横軸)
		this.Header_Height       = dataSet.getHeaderHeight();			// ヘッダー位置(縦軸)
		this.Sub_Status_Name     = dataSet.getSubStatusName();			// サブステータス
		this.Sub_Status_Width    = dataSet.getSubStatusWidth();		// サブステータス表示位置(横軸)
		this.Sub_Status_Height   = dataSet.getSubStatusHeight();		// サブステータス表示位置(縦軸)
		this.X_Unit_Name         = dataSet.getXUnitName();				// X軸単位名称
		this.X_Unit_Width        = dataSet.getXUnitWidth();			// X軸単位位置(横軸)
		this.X_Unit_Height       = dataSet.getXUnitHeight();			// X軸単位位置(縦軸)
		this.Y_Unit_Name         = dataSet.getYUnitName();				// Y軸単位名称
		this.Y_Unit_Width        = dataSet.getYUnitWidth();			// Y軸単位位置(横軸)
		this.Y_Unit_Height       = dataSet.getYUnitHeight();			// Y軸単位位置(縦軸)
		this.fontH               = dataSet.getFontH();					// ヘッダーフォ??ト
		this.fontS               = dataSet.getFontS();					// サブステータスフォ??ト
		this.fontU_Y             = dataSet.getFontU_Y();				// Y軸単位フォ??ト
		this.fontU_X             = dataSet.getFontU_X();				// X軸単位フォ??ト
		this.fontY               = dataSet.getFontY();					// 縦軸フォ??ト
		this.fontX               = dataSet.getFontX();					// 横軸フォ??ト
		this.fontB               = dataSet.getFontB();					// バーフォ??ト
		this.fontB_S             = dataSet.getFontB_S();				// バーの値フォ??ト
		this.fontColorH          = dataSet.getFontColorH();			// ヘッダーフォ??トカ??ー
		this.fontColorS          = dataSet.getFontColorS();			// サブステータスフォ??トカ??ー
		this.fontColorU_Y        = dataSet.getFontColorU_Y();			// Y軸単位フォ??トカ??ー
		this.fontColorU_X        = dataSet.getFontColorU_X();			// X軸単位フォ??トカ??ー
		this.fontColorY          = dataSet.getFontColorY();			// 縦軸フォ??トカ??ー
		this.fontColorX          = dataSet.getFontColorX();			// 横軸フォ??トカ??ー
		this.fontColorB          = dataSet.getFontColorB();			// バーフォ??トカ??ー
		this.fontColorB_S        = dataSet.getFontColorB_S();			// バーの値フォ??トカ??ー
		this.isScaleY            = dataSet.getIsScaleY();				// 横軸目盛り有無フ??グ
		this.scaleDetailY        = dataSet.getScaleDetailY();			// 縦軸目盛り調整値(??減で左右に遷移)
		this.maxScaleLengthY     = dataSet.getMaxScaleLengthY();		// Y軸??目最大表示文????
		this.isHeadOrTail        = dataSet.getIsHeadOrTail();			// 左詰右詰めフ??グ(true:左、false:右)
		this.isGraph_Bar_Status  = dataSet.getIsGraphBarStatus();		// グ??フバーの値表示/非表示フ??グ(true:表示、false:非表示)
		this.lineDetailXTop      = dataSet.getLineDetailXTop();		// X軸上段??イ??位置上下微調整値
		this.XLabelSubDetail     = dataSet.getXLabelSubDetail();		// 横軸の目盛り(サブ)の上下位置調整値
		
		this.detailDecade        = dataSet.getDetailDecade();		// 区切り線間隔の微調整値
		this.detailState         = dataSet.getDetailState();		// 区切り線位置の微調整値
		
		//this.Graph_Devide_Height = maxValue / Graph_Devide;		// 縦軸の目盛り間の値(未使用)
		//this.Graph_Devide_Width  = maxValue / Graph_Devide;		// 横軸の目盛り間の値(未使用)
		
		this.Y_Tick_Height       = Graph_Height / data_label_Y.length;				// 縦軸の1分??分の??さ
		//this.X_Tick_Width        = (Graph_Width / Graph_Devide) - Graph_offset_X;	// 横軸の分????
		
		this.data = dataSet;

	}

	private void setDefault() {
		
		this.data_label_Y        = null;
		this.data_label_X        = null;	/* 横軸目もり値(index 0はダミー)        */
		this.data_label_X_Sub    = null;	/* 横軸目もり値サブ(index 0はダミー)    */
		
		this.Graph_offset_X      = 1;		/* グ??フ描画位置の原点からのXオフセット*/
		this.Graph_offset_Y      = 1;		/* グ??フ描画位置の原点からのYオフセット*/
		this.Graph_Height        = 1;		/* グ??フの大きさ(??さ)                 */
		this.maxValue            = 10000;	/* バーで100%(MAX値)と考える値          */
		
		this.Header_Name         = "";		/* ヘッダータイト??名称                 */
		this.Header_Width        = 1;		/* ヘッダー位置(横軸)                   */
		this.Header_Height       = 1;		/* ヘッダー位置(縦軸)                   */
		
		this.Sub_Status_Name     = "";		/* サブステータス名称                   */
		this.Sub_Status_Width    = 1;		/* サブステータス位置(横軸)             */
		this.Sub_Status_Height   = 1;		/* サブステータス位置(縦軸)             */
		
		this.X_Unit_Name         = "";		/* X軸単位名称                          */
		this.X_Unit_Width        = 1;		/* X軸単位位置(横軸)                    */
		this.X_Unit_Height       = 1;		/* X軸単位位置(縦軸)                    */
		
		this.Y_Unit_Name         = "";		/* Y軸単位名称                          */
		this.Y_Unit_Width        = 1;		/* Y軸単位位置(横軸)                    */
		this.Y_Unit_Height       = 1;		/* Y軸単位位置(縦軸)                    */
		
		this.Graph_Devide        = 1;		/* 縦軸の分????                         */
		//this.Graph_Devide_Height = 1;		/* 縦軸の目盛り間の値                   */
		this.Y_Tick_Height       = 1;		/* 縦軸の1分??分の??さ                  */
		
		this.Graph_Width         = 1;		/* バーの横??                           */
		//this.X_Tick_Width        = 1;		/* 横軸の分????                         */
		this.Graph_Width_Detail  = 1;		/* 横軸の目盛り(??値)の位置の調整値     */
		this.Graph_Bar_Detail    = 1;		/* グ??フバーの位置調整値               */
		this.Graph_Bar_Thick     = 1;		/* グ??フバーの太さ                     */
		
		this.fontH               = null;	/* ヘッダーフォ??ト                     */
		this.fontS               = null;	/* サブステータスフォ??ト               */
		this.fontU_Y             = null;	/* Y軸単位フォ??ト                      */
		this.fontU_X             = null;	/* X軸単位フォ??ト                      */
		this.fontY               = null;	/* 縦軸フォ??ト                         */
		this.fontX               = null;	/* 横軸フォ??ト                         */
		this.fontB               = null;	/* バーフォ??ト                         */
		this.fontB_S             = null;	/* バーの値フォ??ト                     */
		
		this.fontColorH          = null;	/* ヘッダーフォ??トカ??ー               */
		this.fontColorS          = null;	/* サブステータスフォ??トカ??ー         */
		this.fontColorU_Y        = null;	/* Y軸単位フォ??トカ??ー                */
		this.fontColorU_X        = null;	/* X軸単位フォ??トカ??ー                */
		this.fontColorY          = null;	/* 縦軸フォ??トカ??ー                   */
		this.fontColorX          = null;	/* 横軸フォ??トカ??ー                   */
		this.fontColorB          = null;	/* バーフォ??トカ??ー                   */
		this.fontColorB_S        = null;	/* バーの値フォ??トカ??ー               */
		
		this.isScaleY            = false;	/* 横軸目盛り有無フ??グ                 */
		this.scaleDetailY        = 1;		/* 縦軸目盛り調整値(??減で左右に遷移)   */
		this.maxScaleLengthY     = 1;		/* Y軸??目最大表示文????                */
		this.isHeadOrTail        = false;	/* 左詰右詰めフ??グ(true:左、false:右)  */
		this.isGraph_Bar_Status  = false;	/* グ??フバーの値表示/非表示フ??グ      */
		
		this.lineDetailXTop      = 1;		/* X軸上段??イ??位置上下微調整値        */
		this.XLabelSubDetail     = 1;		/* 横軸の目盛り(サブ)の上下位置調整値   */
		
		this.detailDecade        = 0;		/* 区切り線間隔の微調整値               */
		this.detailState         = 0;		/* 区切り線位置の微調整値               */
	}

	public void paint(Graphics g) {
		defaultCheck(fontH, fontS, fontU_Y, fontU_X, fontY, fontX, fontB,
					fontColorH, fontColorS, fontColorU_Y, fontColorU_X, fontColorY, fontColorX, fontColorB,
					data_label_Y, data_label_X, data_label_X_Sub);

		makeHeader(g);

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

	}

	/**********************************************************
	 * グ??フヘッダー??分(グ??フタイト??)を描写します
	 *
	 * @param g グ??フィクス
	 **********************************************************/
	private void makeHeader(Graphics g) {

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
	private void makeSubStatus(Graphics g) {

		// フォ??トのスタイ??設定
		setStyle(g, fontS, fontColorS);

		// ヘッダの文言と描写位置の設定
		g.drawString(this.Sub_Status_Name, this.Sub_Status_Width, this.Sub_Status_Height);
	}

	/**********************************************************
	 * X軸単位名称を描写します
	 *
	 * @param g グ??フィクス
	 **********************************************************/
	private void makeUnitX(Graphics g) {

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
	private void makeUnitY(Graphics g) {

		// フォ??トのスタイ??設定
		setStyle(g, fontU_Y, fontColorU_Y);

		// ヘッダの文言と描写位置の設定
		g.drawString(this.Y_Unit_Name, this.Y_Unit_Width, this.Y_Unit_Height);

	}

	/**********************************************************
	 * グ??フ縦軸??分を描写します
	 *
	 * @param g グ??フィクス
	 **********************************************************/
	private void makeHeight(Graphics g) {
		/** 縦軸目盛りの値の長さチェック値 */
		//int chkWidth_Length        = 0;
		/** 縦軸目盛りの描写位置 */
		int State_Width            = 0;

		// フォ??トのスタイ??設定
		setStyle(g, fontY, fontColorY);

		// 縦軸を描写します(左側罫線)
		g.drawLine(	Graph_offset_X - 1,
					Graph_offset_Y - 1,
					Graph_offset_X - 1,
					Graph_offset_Y + Graph_Height);

		// 縦軸を描写します(右側罫線)
		g.drawLine(	Graph_offset_X + Graph_Width -1,
					Graph_offset_Y,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + Graph_Height);

		// 縦軸に??ベ??を入れます
		for (int i = 0; i < data_label_Y.length; i++) {
			String checkedtext = "";

			// 基本State_Width(縦軸??目の位置(X軸方向に左右移動させます))
			State_Width = scaleDetailY /*114*/;

			// 半角英??文??、半角カタカナを全角文??に変換
			// String chkT = ConvertUtil.hanKkana2ZenKkana(ConvertUtil.toFullANS(data_label_Y[i]));

			// Y軸表示??目文??詰め位置判定
			if (isHeadOrTail) {
				// checkedtext = StringUtil.addtoN(chkT, maxScaleLengthY);
				checkedtext = StringUtil.addtoN(data_label_Y[i], maxScaleLengthY);	// Y軸??目を左詰めにする
			} else {
				// checkedtext = StringUtil.addtoN2(chkT, maxScaleLengthY);
				checkedtext = StringUtil.addtoN2(data_label_Y[i], maxScaleLengthY);	// Y軸??目を右詰めにする
			}

			// 縦軸目盛りを描写
			g.drawString(	checkedtext, 
							Graph_offset_X - State_Width - 25,
							Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + 5);	// 縦軸の目盛り(??値)の位置(横)

			setStyle(g, fontY, fontColorY);
			if (isScaleY) {
				// グ??ッド線を描画します
				if (i > 0) {
					// Y軸目盛りグ??ッド線を描画します
					g.drawLine( Graph_offset_X,
								Graph_offset_Y + Graph_Height - (i * Y_Tick_Height),
								Graph_offset_X - 5,
								Graph_offset_Y + Graph_Height - (i * Y_Tick_Height));
				}
			}
		}
		
	}

	/**********************************************************
	 * グ??フ横軸??分を描写します
	 *
	 * @param g グ??フィクス
	 **********************************************************/
	private void makeWidth(Graphics g) {

		// フォ??トのスタイ??設定
		setStyle(g, fontX, fontColorX);

		// 区切り線間隔の微調整値
		final int moreDetailDecade = (Graph_Width / Graph_Devide) + detailDecade;

		// 目盛りの区切りとなる線を描画します
		for (int i = 1; i < Graph_Devide ; i++) {
			//int bar_width = Graph_Height;

			// 区切り線の配色
			if (i != Graph_Devide) {
				// ??ベ??ダー色
				setStyle(g, fontX, new Color(220, 220, 220));
			} else {
				setStyle(g, fontX, fontColorX);
			}

			// グ??ッド線を描画します(X軸目盛りごとに描写)
			g.drawLine( Graph_offset_X + (moreDetailDecade * i) + detailState /*(X_Tick_Width * i) + (Graph_offset * i)*/,
						Graph_offset_Y,
						Graph_offset_X + (moreDetailDecade * i) + detailState /*(X_Tick_Width * i) + (Graph_offset * i)*/,
						Graph_offset_Y + Graph_Height);

		}

		// フォ??トを元に戻す
		setStyle(g, fontX, fontColorX);

		// 横軸を描写します(上側罫線) // 微調整??が必要なので??意！
		g.drawLine( Graph_offset_X,
					Graph_offset_Y + lineDetailXTop,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + lineDetailXTop);
//		g.drawLine( Graph_offset,
//				   (Graph_offset + Graph_Height - (data_label_Y.length * Y_Tick_Height)) -6 + lineDetailXTop,
//					Graph_offset + Graph_Width -1,
//				   (Graph_offset + Graph_Height - (data_label_Y.length * Y_Tick_Height)) -6 + lineDetailXTop);
				   
System.out.println("Graph_Height:"+ Graph_Height +" Y_length:"+ data_label_Y.length +" Y_Tick_Height:"+ Y_Tick_Height +" lineDetailXTop:"+ lineDetailXTop);

		// 横軸を描写します(下側罫線)
		g.drawLine( Graph_offset_X,
					Graph_offset_Y + Graph_Height,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + Graph_Height);

		// 横軸に??ベ??を入れます
		for (int i = 0; i <= Graph_Devide; i++) {
			g.drawString(data_label_X[i],
						Graph_offset_X + /*(X_Tick_Width * i) + (Graph_offset * i)*/(moreDetailDecade * i) + Graph_Width_Detail,    // 横軸の目盛り(??値)の位置(横)
						Graph_offset_Y + Graph_Height + 18);                                               // 横軸の目盛り(??値)の位置(縦)

			// 横軸ステータス(サブ)
			if(data_label_X.length == data_label_X_Sub.length && data_label_X_Sub.length > 0 && data_label_X_Sub != null){
				g.drawString(data_label_X_Sub[i],
							Graph_offset_X + /*(X_Tick_Width * i) + (Graph_offset * i)*/(moreDetailDecade * i) + Graph_Width_Detail,// 横軸の目盛りサブ(??値)の位置(横)
							Graph_offset_Y + Graph_Height + 18 + XLabelSubDetail);                         // 横軸の目盛りサブ(??値)の位置(縦)
			}

		}
	}

//	/**********************************************************
//	 * グ??フバー??分を描写します
//	 *
//	 * @param g グ??フィクス
//	 **********************************************************/
//	private void makeBar_org(Graphics g) {
//		/** ??ソッド名称 */
//		final String METHOD_NAME = "makeBar";
//
//		// デバッグ??グ出力
//		LogUtil.out(true, CLASS_NAME, METHOD_NAME, "START");
//
//		// フォ??トのスタイ??設定
//		setStyle(g, fontB, fontColorB);
//
//		// 退避用int
//		int next = 0;
//
//		// 詳細データ全てを格納した??ストの取得
//		ArrayList array1 = (ArrayList) data.getList();
//
//		// パ????ータの個??分だけ??ープ
//		for (int i = 0; i < array1.size(); i++) {
//			// i == 0の場??はバーは絶対に??かないこと
//			if (i > 0) {
//				// グ??フデータの取得
//				GraphMakerCommonDataSet data1 =	(GraphMakerCommonDataSet) array1.get(i);
//				
//				// 1本づつのバーの詳細情報を格納したArrayList
//				ArrayList array2 = (ArrayList) data1.getDetailList();
//
//				
//				int bar_width_bk = 0;	// 1つのバーの全体の長さ
//				int bar_width    = 0;	// 1つのバーの??の一??目のバーの長さ
//				int checkFrom;			// データを??っている配列の取得開始イ??デックス
//				int checkTo;			// データを??っている配列の取得終了イ??デックス
//				
//				
//				if (i == 1)	{checkFrom = 1 + next;}	// ??期スタート開始イ??デックスは1
//				else			{checkFrom = next;}		// ??期以外の開始イ??デックス
//				
//				
//				// 開始イ??デックスから分????(終了イ??デックス)までの調整
//				checkTo = checkFrom + data_div[i] + 1;
//				next = checkTo;
//				
//				// グ??フのバーの??き??み
//				for (int k = checkFrom; k < checkTo; k++) {
//
//					// グ??フ詳細データの取得
//					GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet data2 =
//						(GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet) array2.get(k);
//
//					// 1分??のバーの長さ
//					bar_width = data2.getRate() * Graph_Width / maxValue;
//					// グ??フバーの色の設定
//					g.setColor(data2.getBarColor());
//					// グ??フバーを描画
//					g.fillRect(Graph_offset + bar_width_bk,                                         // X軸の始点
//							   Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail,// Y軸の始点
//							   bar_width,                                                           // X軸の終点
//							   Graph_Bar_Thick);                                                    // Y軸の太さ(バーの太さ)
//
//					// 1本のバー内に??種類のバーを??くための値設定
//					bar_width_bk = bar_width_bk + bar_width;
//
//					/****************************
//					 * グ??フバーの値を描写します
//					 ****************************/
//					if(isGraph_Bar_Status){
//						// フォ??トのスタイ??設定
//						setStyle(g, fontB_S, fontColorB_S);
//
//						// バーの????が小さい場??、バー外に??目データを描写します
//						if(data2.getRate() <= 5 && data2.getRate() > 0){
//
//							g.setColor(Color.DARK_GRAY);
//							// 指線
//							g.drawLine((Graph_offset + bar_width_bk - (bar_width / 2)) ,
//										Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail,
//									   (Graph_offset + bar_width_bk - (bar_width / 2)),
//										Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 3);
//
//							// グ??フバーの値を表示する文??列の色
//							g.setColor(data2.getTextColor());
//							// グ??フバーの値を描写します
//							g.drawString(data2.getText(),                                                            // ??ベ??(目盛り)はi * Graph_Devide_Heightごとに表示
//										 (Graph_offset + bar_width_bk - (bar_width / 2)) - 5,                        // 縦軸の目盛り(??値)の位置(横)。??心より10左より文????き出し
//										  Graph_offset + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 5); // 縦軸の目盛り(??値)の位置(縦)
//						} else {
//
//							// グ??フバーの値を表示する文??列の色
//							g.setColor(data2.getTextColor());
//							// グ??フバーの値を描写します
//							g.drawString(data2.getText(),                                   // ??ベ??(目盛り)はi * Graph_Devide_Heightごとに表示
//									(Graph_offset + bar_width_bk - (bar_width / 2)) - 10,   // 縦軸の目盛り(??値)の位置(横)。??心より10左より文????き出し
//									Graph_offset + Graph_Height - (i * Y_Tick_Height) + 5); // 縦軸の目盛り(??値)の位置(縦)
//						}
//
//						// フォ??トのスタイ??設定(元に戻す)
//						setStyle(g, fontB, fontColorB);
//					}
//				}
//			}
//		}
//
//		// 重なったときの優先度が??いので??き直す
//		// フォ??トのスタイ??設定
//		setStyle(g, fontY, fontColorY);
//
//		// 縦軸を描写します(右側罫線)
//		g.drawLine(
//			Graph_offset + Graph_Width -1,
//			Graph_offset,
//			Graph_offset + Graph_Width -1,
//			Graph_offset + Graph_Height);
//
//		// デバッグ??グ出力
//		LogUtil.out(true, CLASS_NAME, METHOD_NAME, "END");
//	}

	/**********************************************************
	 * グ??フバー??分を描写します
	 *
	 * @param g グ??フィクス
	 **********************************************************/
	private void makeBar(Graphics g) {
		setStyle(g, fontB, fontColorB);

		ArrayList array1 = (ArrayList) data.getList();
		
		for (int i = 0; i < array1.size(); i++) {
			if (i > 0) {				
				ArrayList detaillist = (ArrayList) array1.get(i);
				
				int bar_width_bk = 0;	// 1つのバーの全体の長さ
				int bar_width    = 0;	// 1つのバーの??の一??目のバーの長さ
				
				int finger_flg = 0;	// 指線フ??グ(0:指線なし 1:指線上 2:指線下)
				int fingerdetail = 0;	// 指線下の場??の調整値
				int fingertext = 0;	// 指線下の場??の調整値
				
				for (int k = 0; k < detaillist.size(); k++) {
					
					// グ??フ詳細データの取得
					GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet data2 =
						(GraphMakerCommonDataSet.GraphMakerCommonDetailDataSet) detaillist.get(k);
					
					// 1分??のバーの長さ
					if(k == detaillist.size() - 1){
						bar_width = Graph_Width - bar_width_bk;
					}else{
						bar_width = data2.getRate() * Graph_Width / maxValue;
					}
					
					// bar_widthがゼ??の場??は、コ????トを消す
					if(bar_width == 0){ data2.setText(""); }
					
					// グ??フバーの色の設定
					g.setColor(data2.getBarColor());
					// グ??フバーを描画
					g.fillRect(Graph_offset_X + bar_width_bk,                                         // X軸の始点
							   Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail,// Y軸の始点
							   bar_width,                                                             // X軸の終点
							   Graph_Bar_Thick);                                                      // Y軸の太さ(バーの太さ)

					// 1本のバー内に??種類のバーを??くための値設定
					bar_width_bk = bar_width_bk + bar_width;

					/****************************
					 * グ??フバーの値を描写します
					 ****************************/
					if(isGraph_Bar_Status){
						// フォ??トのスタイ??設定
						setStyle(g, fontB_S, fontColorB_S);

						// バーの????が小さい場??、バー外に??目データを描写します
						if(data2.getRate() <= (maxValue*0.05) && data2.getRate() > 0){
							
							// 指線の調整
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
							// 指線
							g.drawLine((Graph_offset_X + bar_width_bk - (bar_width / 2)) ,
										Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail + fingerdetail,
									   (Graph_offset_X + bar_width_bk - (bar_width / 2)),
										Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 3 + fingerdetail);

							// グ??フバーの値を表示する文??列の色
							g.setColor(data2.getTextColor());
							// グ??フバーの値を描写します
							g.drawString(data2.getText(),                                                              // ??ベ??(目盛り)はi * Graph_Devide_Heightごとに表示
										 (Graph_offset_X + bar_width_bk - (bar_width / 2)) - 5,                        // 縦軸の目盛り(??値)の位置(横)。??心より10左より文????き出し
										  Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + Graph_Bar_Detail - 5 + fingertext); // 縦軸の目盛り(??値)の位置(縦)
						} else {
							// 指線フ??グ??期化
							finger_flg = 0;
							fingerdetail = 0;
							
							// グ??フバーの値を表示する文??列の色
							g.setColor(data2.getTextColor());
							// グ??フバーの値を描写します
							g.drawString(data2.getText(),                                      // ??ベ??(目盛り)はi * Graph_Devide_Heightごとに表示
									(Graph_offset_X + bar_width_bk - (bar_width / 2)) - 10,    // 縦軸の目盛り(??値)の位置(横)。??心より10左より文????き出し
									 Graph_offset_Y + Graph_Height - (i * Y_Tick_Height) + 5); // 縦軸の目盛り(??値)の位置(縦)
						}

						// フォ??トのスタイ??設定(元に戻す)
						setStyle(g, fontB, fontColorB);
					}
				}
			}
		}

		// 重なったときの優先度が??いので??き直す
		// フォ??トのスタイ??設定
		setStyle(g, fontY, fontColorY);

		// 縦軸を描写します(右側罫線)
		g.drawLine( Graph_offset_X + Graph_Width -1,
					Graph_offset_Y,
					Graph_offset_X + Graph_Width -1,
					Graph_offset_Y + Graph_Height);

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
	 * @param data_label_Y  縦軸目もり値
	 * @param data_label_X  横軸目盛り値
	 * @param data_div      バーの区切り??群配列
	 **********************************************************/
	private void defaultCheck(Font fontH, Font fontS, Font fontU_Y, Font fontU_X, Font fontY, Font fontX, Font fontB,
								Color fontColorH, Color fontColorS, Color fontColorU_Y, Color fontColorU_X, Color fontColorY, Color fontColorX, Color fontColorB,
								String[] data_label_Y, String[] data_label_X, String[] data_label_X_Sub) {

		// フォ??トnull対策  デフォ??トフォ??トをセット
		if (fontH == null)		{ this.fontH   = new Font("Courier", Font.PLAIN, 10); }
		if (fontS == null)		{ this.fontS   = new Font("Courier", Font.PLAIN, 10); }
		if (fontU_Y == null)	{ this.fontU_Y = new Font("Courier", Font.PLAIN, 10); }
		if (fontU_X == null)	{ this.fontU_X = new Font("Courier", Font.PLAIN, 10); }
		if (fontY == null)		{ this.fontY   = new Font("Courier", Font.PLAIN, 10); }
		if (fontX == null)		{ this.fontX   = new Font("Courier", Font.PLAIN, 10); }
		if (fontB == null)		{ this.fontB   = new Font("Courier", Font.PLAIN, 10); }
		if (fontB_S == null)	{ this.fontB_S = new Font("Courier", Font.PLAIN, 10); }

		// フォ??トカ??ーnull対策  デフォ??トフォ??トカ??ー(??)をセット
		if (fontColorH == null)	{this.fontColorH = Color.BLACK;}
		if (fontColorS == null)	{this.fontColorS = Color.BLACK;}
		if (fontColorU_Y == null)	{this.fontColorU_Y = Color.BLACK;}
		if (fontColorU_X == null)	{this.fontColorU_X = Color.BLACK;}
		if (fontColorY == null)	{this.fontColorY = Color.BLACK;}
		if (fontColorX == null)	{this.fontColorX = Color.BLACK;}
		if (fontColorB == null)	{this.fontColorB = Color.BLACK;}
		if (fontColorB_S == null)	{this.fontColorB_S = Color.BLACK;}

		// 縦軸目もり値チェック
		if (data_label_Y.length <= 0 || data_label_Y == null) {
			data_label_Y = new String[1];
			data_label_Y[0] = "";
		}
		// 横軸目もり値チェック
		if (data_label_X.length <= 0 || data_label_X == null) {
			data_label_X = new String[1];
			data_label_X[0] = "";
		}
		// バーの区切り??値チェック
		if (data_label_X_Sub.length <= 0 || data_label_X_Sub == null) {
			data_label_X_Sub = new String[1];
			data_label_X_Sub[0] = "";
		}

	}

	/**********************************************************
	 * グ??フバーのフォ??ト設定を行います
	 *
	 * @param g         グ??フィクス
	 * @param font      フォ??ト
	 * @param fontColor フォ??トカ??ー
	 **********************************************************/
	private void setStyle(Graphics g, Font font, Color fontColor) {
		
		g.setColor(fontColor);	// フォ??トの色設定
		g.setFont(font);		// フォ??トの設定
	}
}
