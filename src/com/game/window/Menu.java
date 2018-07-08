package com.game.window;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.game.gameplay.RecordsManagement;
import com.game.gamestate.GameStateManager;

public class Menu extends JPanel implements ActionListener, KeyListener
{
	private static final long serialVersionUID = -284968577068930683L;

	public static final int WIDTH = 370;
	public static final int HEIGHT = 280;
	public static final int SCALE = 2;
	
	private static Map<String, Integer> records = new HashMap<String, Integer>();
	private static int playsNumber;
	private static boolean[] achievements = new boolean[8];
	
	public static boolean if_music = false;
	private static Clip clip;
	public Timer timer;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	public Menu()
	{
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
		try 
		{
			RecordsManagement.setData();			
		} 
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		init();
	}
	
	private void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
		
		timer = new Timer(4, this);
		timer.start();
		
		File file = new File("resources/music/music.wav");
		if(file.exists())
		{
			if_music = true;
			playSound(file.getPath());
		}
	}

	public void keyPressed(KeyEvent key) 
	{
		gsm.keyPressed(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key) 
	{
		gsm.keyReleased(key.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	private void update()
	{
		gsm.update();
	}
	
	private void draw()
	{
		super.paintComponent(g);
		gsm.draw(g);
	}
	
	private void drawToScreen()
	{
		getGraphics().drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
	}
	
	public static int getCurrentNormalEasyRecord() 
	{
		return records.get("currentNormalEasyRecord");
	}

	public static void setCurrentNormalEasyRecord(int cner) 
	{
		records.put("currentNormalEasyRecord", cner);
	}

	public static int getCurrentNormalMediumRecord() 
	{	
		return records.get("currentNormalMediumRecord");
	}

	public static void setCurrentNormalMediumRecord(int cnmr) 
	{
		records.put("currentNormalMediumRecord", cnmr);
	}

	public static int getCurrentNormalHardRecord() 
	{
		return records.get("currentNormalHardRecord");
	}

	public static void setCurrentNormalHardRecord(int cnhr) 
	{
		records.put("currentNormalHardRecord", cnhr);
	}

	public static int getCurrentTimeEasyRecord() 
	{
		return records.get("currentTimeEasyRecord");
	}

	public static void setCurrentTimeEasyRecord(int cter) 
	{
		records.put("currentTimeEasyRecord", cter);
	}

	public static int getCurrentTimeMediumRecord() 
	{
		return records.get("currentTimeMediumRecord");
	}

	public static void setCurrentTimeMediumRecord(int ctmr) 
	{
		records.put("currentTimeMediumRecord", ctmr);
	}

	public static int getCurrentTimeHardRecord() 
	{
		return records.get("currentTimeHardRecord");
	}

	public static void setCurrentTimeHardRecord(int cthr) 
	{
		records.put("currentTimeHardRecord", cthr);
	}

	public static int getPlaysNumber()
	{
		return playsNumber;
	}
	
	public static void setPlaysNumber(int pn)
	{
		playsNumber = pn;
	}
	
	public static boolean getAchievement1() 
	{
		return achievements[0];
	}

	public static void setAchievement1(boolean a1) 
	{
		achievements[0] = a1;
	}

	public static boolean getAchievement2() 
	{
		return achievements[1];
	}

	public static void setAchievement2(boolean a2) 
	{
		achievements[1] = a2;
	}

	public static boolean getAchievement3() 
	{
		return achievements[2];
	}

	public static void setAchievement3(boolean a3) 
	{
		achievements[2] = a3;
	}

	public static boolean getAchievement4() 
	{
		return achievements[3];
	}

	public static void setAchievement4(boolean a4) 
	{
		achievements[3] = a4;
	}

	public static boolean getAchievement5() 
	{
		return achievements[4];
	}

	public static void setAchievement5(boolean a5) 
	{
		achievements[4] = a5;
	}

	public static boolean getAchievement6() 
	{
		return achievements[5];
	}

	public static void setAchievement6(boolean a6) 
	{
		achievements[5] = a6;
	}

	public static boolean getAchievement7() 
	{
		return achievements[6];
	}

	public static void setAchievement7(boolean a7) 
	{
		achievements[6] = a7;
	}

	public static boolean getAchievement8() 
	{
		return achievements[7];
	}

	public static void setAchievement8(boolean a8) 
	{
		achievements[7] = a8;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		draw();
		update();
		drawToScreen();
		
		if(if_music && gsm.getCurrentState() == 0 && !clip.isActive()) 
			playSound("resources/music/music.wav");
	}
	
	public static void stopMusic()
	{
		clip.stop();
	}
	
	public void playSound(String filename)
	{
		try 
		{
		    File file = new File(filename);
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;

		    stream = AudioSystem.getAudioInputStream(file);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		    
		}
		catch(Exception e) 
		{
		    e.printStackTrace();
		}
	}
}
