package com.zoomly.mes.panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.zoomly.mes.FtpHelper;
import com.zoomly.mes.Start;
import com.zoomly.mes.Utility;

public class PanelBoxFrame extends JFrame {

	private static final long serialVersionUID = -3085221246728176768L;
	
	private JPanel workPanel;
	
	private JTextField boxIdTF;
	
	private JTextField panelIdTF;
	
	private JTextField hehui_ProdIdTF;
	
	private JTextField maxCountTF;
	
	private JTextField curCountTF;
	
	private JComboBox<String> prodCombo;
	
	private int curCount = 0;
	
	public PanelBoxFrame(){
		super(Utility.MES_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		workPanel = new JPanel();
		getContentPane().add(workPanel, BorderLayout.CENTER);
		init();
	}
	
	protected void init(){
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[]{1.0};
		gbl.rowWeights = new double[]{0.0, 1.0};
		workPanel.setLayout(gbl);
		
		JLabel label = new JLabel(Utility.getText("Local_Directory"));
		JTextField rootDirTF = new JTextField(Utility.ROOT_DIR);
		rootDirTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		rootDirTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		label.setEnabled(false);
		rootDirTF.setEditable(false);
		JPanel labelP = new JPanel();
		labelP.add(label);
		labelP.add(rootDirTF);
		
		GridBagConstraints gbc_labelP = new GridBagConstraints();
		gbc_labelP.anchor = GridBagConstraints.NORTHWEST;
		gbc_labelP.gridx = 0;
		gbc_labelP.gridy = 0;
		gbc_labelP.insets = new Insets(10, 10, 10, 10);
		workPanel.add(labelP, gbc_labelP);
		
		JPanel mainPanel = createMainPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.anchor = GridBagConstraints.CENTER;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		gbc_mainPanel.insets = new Insets(10, 10, 10, 10);
		workPanel.add(mainPanel, gbc_mainPanel);
		
	}
	
	private JPanel createMainPanel(){
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[]{0.0, 0.0};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0};
		JPanel panel = new JPanel();
		panel.setLayout(gbl);
		
		JLabel boxL = new JLabel(Utility.getText("Inner_Box_ID"));
		GridBagConstraints gbc_boxL = new GridBagConstraints();
		gbc_boxL.anchor = GridBagConstraints.CENTER;
		gbc_boxL.gridx = 0;
		gbc_boxL.gridy = 1;
		gbc_boxL.insets = new Insets(10, 10, 10, 0);
		panel.add(boxL, gbc_boxL);
		
		boxIdTF = new JTextField();
		boxIdTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		boxIdTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		GridBagConstraints gbc_boxTF = new GridBagConstraints();
		gbc_boxTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_boxTF.gridx = 1;
		gbc_boxTF.gridy = 1;
		gbc_boxTF.insets = new Insets(10, 10, 10, 10);
		panel.add(boxIdTF, gbc_boxTF);
		
//		maxCountTF = new JTextField();
//		maxCountTF.setEditable(false);
//		maxCountTF.setMinimumSize(Utility.DIMENSION_LEN_40);
//		maxCountTF.setPreferredSize(Utility.DIMENSION_LEN_40);
//		curCountTF = new JTextField();
//		curCountTF.setEditable(false);
//		curCountTF.setMinimumSize(Utility.DIMENSION_LEN_40);
//		curCountTF.setPreferredSize(Utility.DIMENSION_LEN_40);
//		
//		JLabel boxC = new JLabel(Utility.getText("Curr_Box_Cap"));
//		GridBagConstraints curr_boxL = new GridBagConstraints();
//		curr_boxL.anchor = GridBagConstraints.NORTHWEST;
//		curr_boxL.gridx = 2;
//		curr_boxL.gridy = 1;
//		curr_boxL.insets = new Insets(10, 0, 10, 0);
//		panel.add(boxC, curr_boxL);
//		
//		GridBagConstraints gbc_curCountTF = new GridBagConstraints();
//		gbc_curCountTF.anchor = GridBagConstraints.NORTHWEST;
//		gbc_curCountTF.gridx = 3;
//		gbc_curCountTF.gridy = 1;
//		gbc_curCountTF.insets = new Insets(10, 0, 10, 10);
//		panel.add(curCountTF, gbc_curCountTF);
//		
//		JLabel labl = new JLabel("/");
//		Font font = new Font("Arial", Font.BOLD, 16);
//		labl.setFont(font);
//		GridBagConstraints gbc_labl = new GridBagConstraints();
//		gbc_labl.anchor = GridBagConstraints.NORTHWEST;
//		gbc_labl.gridx = 4;
//		gbc_labl.gridy = 1;
//		gbc_labl.insets = new Insets(10, 0, 10, 0);
//		panel.add(labl, gbc_labl);
//		
//		JLabel boxA = new JLabel(Utility.getText("Actu_Box_Cap"));
//		GridBagConstraints act_boxL = new GridBagConstraints();
//		act_boxL.anchor = GridBagConstraints.NORTHWEST;
//		act_boxL.gridx = 5;
//		act_boxL.gridy = 1;
//		act_boxL.insets = new Insets(10, 10, 10, 0);
//		panel.add(boxA, act_boxL);
//        
//		
//		GridBagConstraints gbc_maxCountTF = new GridBagConstraints();
//		gbc_maxCountTF.anchor = GridBagConstraints.NORTHWEST;
//		gbc_maxCountTF.gridx = 6;
//		gbc_maxCountTF.gridy = 1;
//		gbc_maxCountTF.insets = new Insets(10, 0, 10, 10);
//		panel.add(maxCountTF, gbc_maxCountTF);
		
		JPanel countPanel = createCountPanel();
		GridBagConstraints gbc_countPanel = new GridBagConstraints();
		gbc_countPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_countPanel.gridx = 2;
		gbc_countPanel.gridy = 1;
		gbc_countPanel.insets = new Insets(0, 10, 0, 10);
		panel.add(countPanel, gbc_countPanel);
		
		JLabel panelL = new JLabel(Utility.getText("Panel_ID"));
		GridBagConstraints gbc_panelL = new GridBagConstraints();
		gbc_panelL.anchor = GridBagConstraints.CENTER;
		gbc_panelL.gridx = 0;
		gbc_panelL.gridy = 2;
		gbc_panelL.insets = new Insets(10, 10, 10, 0);
		panel.add(panelL, gbc_panelL);
		
		panelIdTF = new JTextField();
		panelIdTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		panelIdTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		GridBagConstraints gbc_panelTF = new GridBagConstraints();
		gbc_panelTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_panelTF.gridx = 1;
		gbc_panelTF.gridy = 2;
		gbc_panelTF.insets = new Insets(10, 10, 10, 10);
		panel.add(panelIdTF, gbc_panelTF);
		
		JLabel prodL = new JLabel(Utility.getText("Prodl_ID"));
		GridBagConstraints gbc_prodL = new GridBagConstraints();
		gbc_prodL.anchor = GridBagConstraints.CENTER;
		gbc_prodL.gridx = 0;
		gbc_prodL.gridy = 0;
		gbc_prodL.insets = new Insets(10, 10, 10, 0);
		panel.add(prodL, gbc_prodL);
		
		prodCombo = new JComboBox<String>();
		prodCombo.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		prodCombo.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		GridBagConstraints gbc_prodTF = new GridBagConstraints();
		gbc_prodTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_prodTF.gridx = 1;
		gbc_prodTF.gridy = 0;
		gbc_prodTF.insets = new Insets(10, 10, 10, 10);
		panel.add(prodCombo, gbc_prodTF);
		addListTypeItem();
		
//		JLabel heHuiProdL = new JLabel(Utility.getText("hehui_Prodl_ID"));
//		GridBagConstraints gbc_hehui_prodL = new GridBagConstraints();
//		gbc_hehui_prodL.anchor = GridBagConstraints.NORTHWEST;
//		gbc_hehui_prodL.gridx = 2;
//		gbc_hehui_prodL.gridy = 0;
//		gbc_hehui_prodL.insets = new Insets(10, 10, 10, 10);
//		panel.add(heHuiProdL, gbc_hehui_prodL);
//		
//		hehui_ProdIdTF = new JTextField();
//		hehui_ProdIdTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
//		hehui_ProdIdTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
//		GridBagConstraints gbc_ProdTF = new GridBagConstraints();
//		gbc_ProdTF.anchor = GridBagConstraints.NORTHWEST;
//		gbc_ProdTF.gridx = 3;
//		gbc_ProdTF.gridy = 0;
//		gbc_ProdTF.insets = new Insets(10, 10, 10, 10);
//		panel.add(hehui_ProdIdTF, gbc_ProdTF);
//		hehui_ProdIdTF.setEditable(false);
		
		JPanel hehuiProdPanel = createHehuiProdPanel();
		GridBagConstraints gbc_hehuiProdPanel = new GridBagConstraints();
		gbc_hehuiProdPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_hehuiProdPanel.gridx = 2;
		gbc_hehuiProdPanel.gridy = 0;
		gbc_hehuiProdPanel.insets = new Insets(0, 0, 0, 10);
		panel.add(hehuiProdPanel, gbc_hehuiProdPanel);
		
		prodCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String prodId = (String)prodCombo.getSelectedItem();
				if(!Utility.isEmpty(prodId)){
					maxCountTF.setEditable(false);
					maxCountTF.setText(Start.getProdList().getProperty(prodId).split(",")[0]);
					hehui_ProdIdTF.setText(Start.getProdList().getProperty(prodId).split(",")[2]);
					
					maxCountTF.requestFocus();
					
				} else{
					hehui_ProdIdTF.setText("");
					maxCountTF.setText("");
					maxCountTF.setEditable(true);
				}
			}
		});
		
		boxIdTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Utility.isEmpty(boxIdTF.getText()) ){
//					maxCountTF.setText("");
//					maxCountTF.requestFocus();
					panelIdTF.requestFocus();
					curCount = 0;
					curCountTF.setText(String.valueOf(curCount));
				}
			}
		});
		
		panelIdTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String panelId = panelIdTF.getText();
				String boxId = boxIdTF.getText();
				if(!Utility.isEmpty(panelId) && checkInfo()){
					if(judgePRODID()){
						boolean result = Utility.createPanelFile(panelId.trim(), boxIdTF.getText());
						if(result){
							curCount++;
							curCountTF.setText(String.valueOf(curCount));
							//upload file using new thread
							upload(panelId, boxId);
							JOptionPane.showMessageDialog(null, Utility.getText("Panel_[{0}]_Success", panelId), Utility.MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, Utility.getText("Panel_[{0}]_Failure", panelId), Utility.MES_TITLE, JOptionPane.ERROR_MESSAGE);
						}
						panelIdTF.setText("");
					}
				}
			}
		});
		
		return panel;
	}
	
	private JPanel createHehuiProdPanel(){
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[]{0.0, 0.0};
		gbl.rowWeights = new double[]{0.0};
		JPanel panel = new JPanel();
		panel.setLayout(gbl);
		
		JLabel heHuiProdL = new JLabel(Utility.getText("hehui_Prodl_ID"));
		GridBagConstraints gbc_hehui_prodL = new GridBagConstraints();
		gbc_hehui_prodL.anchor = GridBagConstraints.CENTER;
		gbc_hehui_prodL.gridx = 0;
		gbc_hehui_prodL.gridy = 0;
		gbc_hehui_prodL.insets = new Insets(10, 10, 10, 10);
		panel.add(heHuiProdL, gbc_hehui_prodL);
		
		hehui_ProdIdTF = new JTextField();
		hehui_ProdIdTF.setMinimumSize(Utility.DEFAULT_COMPONENT_D);
		hehui_ProdIdTF.setPreferredSize(Utility.DEFAULT_COMPONENT_D);
		GridBagConstraints gbc_ProdTF = new GridBagConstraints();
		gbc_ProdTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_ProdTF.gridx = 1;
		gbc_ProdTF.gridy = 0;
		gbc_ProdTF.insets = new Insets(10, 0, 10, 10);
		panel.add(hehui_ProdIdTF, gbc_ProdTF);
		hehui_ProdIdTF.setEditable(false);
		
		
		return panel;
	}
	
	private JPanel createCountPanel(){
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl.rowWeights = new double[]{0.0};
		JPanel panel = new JPanel();
		panel.setLayout(gbl);
		
		maxCountTF = new JTextField();
		maxCountTF.setEditable(false);
		maxCountTF.setMinimumSize(Utility.DIMENSION_LEN_40);
		maxCountTF.setPreferredSize(Utility.DIMENSION_LEN_40);
		curCountTF = new JTextField();
		curCountTF.setEditable(false);
		curCountTF.setMinimumSize(Utility.DIMENSION_LEN_40);
		curCountTF.setPreferredSize(Utility.DIMENSION_LEN_40);
		
		JLabel boxC = new JLabel(Utility.getText("Curr_Box_Cap"));
		GridBagConstraints curr_boxL = new GridBagConstraints();
		curr_boxL.anchor = GridBagConstraints.CENTER;
		curr_boxL.gridx = 0;
		curr_boxL.gridy = 0;
		curr_boxL.insets = new Insets(10, 0, 10, 0);
		panel.add(boxC, curr_boxL);
		
		GridBagConstraints gbc_curCountTF = new GridBagConstraints();
		gbc_curCountTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_curCountTF.gridx = 1;
		gbc_curCountTF.gridy = 0;
		gbc_curCountTF.insets = new Insets(10, 0, 10, 10);
		panel.add(curCountTF, gbc_curCountTF);
		
		JLabel labl = new JLabel("/");
		Font font = new Font("Arial", Font.BOLD, 16);
		labl.setFont(font);
		GridBagConstraints gbc_labl = new GridBagConstraints();
		gbc_labl.anchor = GridBagConstraints.NORTHWEST;
		gbc_labl.gridx = 2;
		gbc_labl.gridy = 0;
		gbc_labl.insets = new Insets(10, 0, 10, 0);
		panel.add(labl, gbc_labl);
		
		JLabel boxA = new JLabel(Utility.getText("Actu_Box_Cap"));
		GridBagConstraints act_boxL = new GridBagConstraints();
		act_boxL.anchor = GridBagConstraints.CENTER;
		act_boxL.gridx = 3;
		act_boxL.gridy = 0;
		act_boxL.insets = new Insets(10, 10, 10, 0);
		panel.add(boxA, act_boxL);
        
		
		GridBagConstraints gbc_maxCountTF = new GridBagConstraints();
		gbc_maxCountTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_maxCountTF.gridx = 4;
		gbc_maxCountTF.gridy = 0;
		gbc_maxCountTF.insets = new Insets(10, 0, 10, 10);
		panel.add(maxCountTF, gbc_maxCountTF);
		
		return panel;
	}
	
	private void addListTypeItem(){
		prodCombo.addItem("");
		Properties properties = Start.getProdList();
		for(Entry<Object, Object> entry : properties.entrySet()){
			prodCombo.addItem(entry.getKey().toString());
		}		
	}
	
	private boolean judgePRODID(){
		if(Start.getMesConfig().isFtpEnable()){
			String prodId = "";
			String panelId = panelIdTF.getText();
			//本地抓取料號
			prodId = Utility.getProdIdByPanelId(panelId);
			
			if(Utility.isEmpty(prodId)){
				JOptionPane.showMessageDialog(null, Utility.getText("Product_is_null", prodId), Utility.MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
				return false;
//			}else if(!prodCombo.getSelectedItem().equals(prodId)){
			}else if(!hehui_ProdIdTF.getText().equals(prodId)){
				JOptionPane.showMessageDialog(null, Utility.getText("Product_{0}_is_not_match", prodId), Utility.MES_TITLE, JOptionPane.INFORMATION_MESSAGE);
				return false;
			}	
			System.out.println("prodId" + prodId);
		}
		return true;
	}
	
	private boolean checkInfo(){
		String panelId = panelIdTF.getText();
		if(!Utility.isEmpty(panelId)){
			if(!panelId.startsWith("A") || panelId.trim().length() != 13){
				JOptionPane.showMessageDialog(null, Utility.getText("Invalid_Panel_ID"), Utility.MES_TITLE, JOptionPane.WARNING_MESSAGE);
				panelIdTF.requestFocus();
				return false;
			}
		}
		
		if(Utility.isEmpty(boxIdTF.getText())){
			JOptionPane.showMessageDialog(null, Utility.getText("Please_Input_Inner_Box"), Utility.MES_TITLE, JOptionPane.WARNING_MESSAGE);
			boxIdTF.requestFocus();
			return false;
		}
		
		if(Utility.isEmpty(maxCountTF.getText())){
			JOptionPane.showMessageDialog(null, Utility.getText("Please_Input_Inner_Box_Capacity"), Utility.MES_TITLE, JOptionPane.WARNING_MESSAGE);
			maxCountTF.requestFocus();
			return false;
		}
		int maxCount = Integer.valueOf(maxCountTF.getText());
		if(curCount >= maxCount){
			JOptionPane.showMessageDialog(null, Utility.getText("Inner_Box_is_Full_Filled,Please_Reinput_Inner_Box"), Utility.MES_TITLE, JOptionPane.WARNING_MESSAGE);
			boxIdTF.setText("");
			boxIdTF.requestFocus();
			return false;
		}
	
		if(Utility.isDuplicatePanel(panelId, boxIdTF.getText())){
			JOptionPane.showMessageDialog(null, Utility.getText("Duplicate_Panel_ID"), Utility.MES_TITLE, JOptionPane.WARNING_MESSAGE);
			panelIdTF.setText("");
			panelIdTF.requestFocus();
			return false;
		}
		
		return true;
	}

	private void upload(final String panelId, final String boxId){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if(Start.getMesConfig().isFtpEnable()){
					FtpHelper ftpHelper = new FtpHelper();
					ftpHelper.connect();
					String localFile = Utility.getPanelFilePath(panelId);
					String remoteFile = Utility.getRemotePanelFilePath(panelId);
					ftpHelper.upload(localFile, remoteFile);
					localFile = Utility.getInnerBoxTablePath(boxId);
					remoteFile = Utility.getRemoteInnerBoxTablePath(boxId);
					ftpHelper.upload(localFile, remoteFile);
					ftpHelper.disConnect();
				}
			}
		});
	}
	

}
