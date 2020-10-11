package com.ibm.lcd.cfm.monitor.status;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ScrollingTextPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = -2295029347334610662L;

	private String m_message = "";
	private int m_interval = 1;
	public boolean expired = false;

	// public String message;
	// public Font messageF;
	public int messageX;
	public int messageY;
	public int messageW;


    //marquee message
    boolean isPicture;  //marquee message is picture or text?
    Image picture;
    //String text;
    Font font;
    Color color;
    //marquee dimensions
    int w;
    int h;
    //offscreen marquee image
    Image im = null;
    int imWidth = 0;
    int imHeight = 0;
    //Insets insets = new Insets(0, 0, 0, 0);
    //position of image relative to canvas viewport
    int viewX = 0;
    int viewY = 0;
    //mouse X coord while draging
    int mouseX;
    //thread used as a timer for scrolling image
    Thread timerThread = null;
    boolean tempStop = false;  //class stops thread
    boolean stopped = true;    //user stops thread
    //scrolling parameters
    boolean toLeft = true;
    int advance = 1;
    //int interval = 100;
    //coords of rectangle to be erased
    int dirtyX = 0;
    int dirtyY = 0;
    int dirtyW = 0;
    int dirtyH = 0;
    //component readiness to create image
    boolean ready = false;
    //draw component borders or not
    //boolean borders = false;

	Dimension preferredD = new Dimension(100, 30);

	public ScrollingTextPanel(int interval) {
		super(true);

		this.setBackground(new Color(0, 0, 120));

		
		m_interval = interval;
		//setMsg(m_message);
        
        //default marquee message attributes
        isPicture = false;
        picture = null;
        font = new Font("Helvetica", Font.BOLD, 12);
        color = Color.yellow;

        this.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() !=2) return;
				if( stopped) 
					move();
				else
					hold();
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
			}
        });
        
        
	}

	public void setFontColor(Color c){
		color = c;
	}
	
	public void setSpeed(int speed){
		m_interval = speed;
	}
	
	public Dimension getPreferredSize() {
		return preferredD;
	}



	// standard overriden methods
	public void paint(Graphics g) {
		super.paint(g);
		
		g.clearRect(dirtyX, dirtyY, dirtyW-1, dirtyH);
		resetDirtyRect();
		if (im != null) {
			g.drawImage(im, viewX, viewY, this);
		}
	}

	public void update(Graphics g) {
		paint(g); // this, with dirtyRect, gives smooth movement
	}

	// marquee message methods
	public void setMsg(String text) {
		if (timerThread != null) {
			tempStop = true;
			startTimerThread(false);
		}
		dirtyRect(viewX, viewY, imWidth, imHeight);
		isPicture = false;
		this.picture = null;
		m_message = text;
		makeImage(m_message);
		repaint();
		if (tempStop) {
			tempStop = false;
			startTimerThread(true);
		}
	}

	// scrolling parameter methods
	public void goLeft(boolean toLeft) {
		if (timerThread != null) {
			tempStop = true;
			startTimerThread(false);
		}
		this.toLeft = toLeft;
		if (tempStop) {
			tempStop = false;
			startTimerThread(true);
		}
	}

	// image positioning methods (relative to canvas)
	public void initPos() {
        this.w = this.getWidth();
       
        this.h = this.getHeight();
        
		initX();
		initY();
	}

	public void initX() {
		if (timerThread != null) {
			tempStop = true;
			startTimerThread(false);
		}
		dirtyRect(viewX, viewY, imWidth, imHeight);
		viewX = toLeft ? w - 1 : -(imWidth - 1); // to the edge
		repaint();
		if (tempStop) {
			tempStop = false;
			startTimerThread(true);
		}
	}

	public void initY() {
		if (timerThread != null) {
			tempStop = true;
			startTimerThread(false);
		}
		dirtyRect(viewX, viewY, imWidth, imHeight);
		viewY = (int) Math.round((h - imHeight) / 2); // center
		repaint();
		if (tempStop) {
			tempStop = false;
			startTimerThread(true);
		}
	}

//	// scrolling movement methods
	public void move() {
		startTimerThread(true);
		stopped = false;
	}

	public void hold() {
		startTimerThread(false);
		stopped = true;
	}
	
	// thread methods
	public void run() {

		while (timerThread == Thread.currentThread()) {
//	        this.w = this.getWidth();
//	        this.h = this.getHeight();
//	        System.out.println(this.w+" - "+this.h);
			//System.out.println(System.currentTimeMillis());
			if (toLeft) {
				dirtyRect(viewX + imWidth - advance, viewY, advance, imHeight);
				viewX -= advance;
				if (viewX < -(imWidth - 1))
					viewX = w - 1;
			} else {
				dirtyRect(viewX, viewY, advance, imHeight);
				viewX += advance;
				if (viewX > w - 1)
					viewX = -(imWidth - 1);
			}
			try {
				SwingUtilities.invokeAndWait(new Runnable(){
					public void run() {
						repaint();
					}
				});
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}

			try {
				Thread.sleep(m_interval*10L);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	void startTimerThread(boolean start) {
		if (start) {
			timerThread = new Thread(this);
			timerThread.start();
		} else {
			timerThread = null;
		}
	}

	// create image with marquee message
	void makeImage(String text) {
		if (!ready){
			return;
		}
		FontMetrics fm = getFontMetrics(font);
		imWidth = fm.stringWidth(text);
		imHeight = fm.getHeight();
		im = createImage(imWidth, imHeight);

		Graphics imGraph = im.getGraphics();
		imGraph.setColor(this.getBackground());
		imGraph.fillRect(0, 0, imWidth+2, imHeight);
		imGraph.setColor(color);
		imGraph.setFont(font);
		int fontDescent = fm.getDescent();
		imGraph.drawString(text, 0, imHeight - fontDescent);
//		if (borders) {
//			imGraph.drawRect(0, 0, imWidth - 1, imHeight - 1);
//		}
		imGraph.dispose();
	}

	// sets coords for dirty rectangle
	void dirtyRect(int x, int y, int w, int h) {
		// if first call after resetDirtyRect
		if (dirtyW == 0 || dirtyH == 0) {
			dirtyX = x;
			dirtyY = y;
			dirtyW = w;
			dirtyH = h;
			//System.out.println("return dirtyRect");
			return;
		}
		// else calculate the bounding rect
		int minX = x < dirtyX ? x : dirtyX;
		int maxX = x + w > dirtyX + dirtyW ? x + w : dirtyX + dirtyW;
		int minY = y < dirtyY ? y : dirtyY;
		int maxY = y + h > dirtyY + dirtyH ? y + h : dirtyY + dirtyH;
		dirtyX = minX;
		dirtyY = minY;
		dirtyW = maxX - minX;
		dirtyH = maxY - minY;
	}

	void resetDirtyRect() {
		dirtyW = 0;
		dirtyH = 0;
	}

	// overriden method, called by system
	public void addNotify() {
		super.addNotify();
		ready = true; // green light to create image
	}
}
