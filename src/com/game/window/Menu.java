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

	//dimensions
	public static final int WIDTH = 370;
	public static final int HEIGHT = 280;
	public static final int SCALE = 2;
	
	private static int currentNormalEasyRecord;
	private static int currentNormalMediumRecord;
	private static int currentNormalHardRecord;
	private static int currentTimeEasyRecord;
	private static int currentTimeMediumRecord;
	private static int currentTimeHardRecord;
	private static int playsNumber;

	private static boolean achievement1;
	private static boolean achievement2;
	private static boolean achievement3;
	private static boolean achievement4;
	private static boolean achievement5;
	private static boolean achievement6;
	private static boolean achievement7;
	private static boolean achievement8;
	
	public Timer timer;
	static Clip clip;
	public static boolean if_music = false;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//game state manager
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
			
		} catch (FileNotFoundException e) 
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
	public void keyTyped(KeyEvent arg0) 
	{
		
	}
	
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
		return currentNormalEasyRecord;
	}

	public static void setCurrentNormalEasyRecord(int currentNormalEasyRecord) 
	{
		Menu.currentNormalEasyRecord = currentNormalEasyRecord;
	}

	public static int getCurrentNormalMediumRecord() 
	{
		return currentNormalMediumRecord;
	}

	public static void setCurrentNormalMediumRecord(int currentNormalMediumRecord) 
	{
		Menu.currentNormalMediumRecord = currentNormalMediumRecord;
	}

	public static int getCurrentNormalHardRecord() 
	{
		return currentNormalHardRecord;
	}

	public static void setCurrentNormalHardRecord(int currentNormalHardRecord) 
	{
		Menu.currentNormalHardRecord = currentNormalHardRecord;
	}

	public static int getCurrentTimeEasyRecord() 
	{
		return currentTimeEasyRecord;
	}

	public static void setCurrentTimeEasyRecord(int currentTimeEasyRecord) 
	{
		Menu.currentTimeEasyRecord = currentTimeEasyRecord;
	}

	public static int getCurrentTimeMediumRecord() 
	{
		return currentTimeMediumRecord;
	}

	public static void setCurrentTimeMediumRecord(int currentTimeMediumRecord) 
	{
		Menu.currentTimeMediumRecord = currentTimeMediumRecord;
	}

	public static int getCurrentTimeHardRecord() 
	{
		return currentTimeHardRecord;
	}

	public static void setCurrentTimeHardRecord(int currentTimeHardRecord) 
	{
		Menu.currentTimeHardRecord = currentTimeHardRecord;
	}

	public static int getPlaysNumber()
	{
		return playsNumber;
	}
	
	public static void setPlaysNumber(int playsNumber)
	{
		Menu.playsNumber = playsNumber;
	}
	
	public static boolean getAchievement1() 
	{
		return achievement1;
	}

	public static void setAchievement1(boolean achievement1) 
	{
		Menu.achievement1 = achievement1;
	}

	public static boolean getAchievement2() 
	{
		return achievement2;
	}

	public static void setAchievement2(boolean achievement2) 
	{
		Menu.achievement2 = achievement2;
	}

	public static boolean getAchievement3() 
	{
		return achievement3;
	}

	public static void setAchievement3(boolean achievement3) 
	{
		Menu.achievement3 = achievement3;
	}

	public static boolean getAchievement4() 
	{
		return achievement4;
	}

	public static void setAchievement4(boolean achievement4) 
	{
		Menu.achievement4 = achievement4;
	}

	public static boolean getAchievement5() 
	{
		return achievement5;
	}

	public static void setAchievement5(boolean achievement5) 
	{
		Menu.achievement5 = achievement5;
	}

	public static boolean getAchievement6() 
	{
		return achievement6;
	}

	public static void setAchievement6(boolean achievement6) 
	{
		Menu.achievement6 = achievement6;
	}

	public static boolean getAchievement7() 
	{
		return achievement7;
	}

	public static void setAchievement7(boolean achievement7) 
	{
		Menu.achievement7 = achievement7;
	}

	public static boolean getAchievement8() 
	{
		return achievement8;
	}

	public static void setAchievement8(boolean achievement8) 
	{
		Menu.achievement8 = achievement8;
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
		    
		}catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}

}