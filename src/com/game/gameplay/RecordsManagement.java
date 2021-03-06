package com.game.gameplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import com.game.window.Menu;

public abstract class RecordsManagement 
{
	private static final String PATH = "resources/files/";
	
	public static void saveRecord(int difficulty, int gameplay, int currentScore) throws IOException
	{
		Writer wr = null;
		
		if(difficulty == 0 && gameplay == 0 && currentScore > Menu.getCurrentNormalEasyRecord())
		{
			Menu.setCurrentNormalEasyRecord(currentScore); 
			wr = new FileWriter(PATH + "currentNormalEasyRecord.txt");	 
		}
		else if(difficulty == 1 && gameplay == 0 && currentScore > Menu.getCurrentNormalMediumRecord())
		{
			Menu.setCurrentNormalMediumRecord(currentScore); 
			wr = new FileWriter(PATH + "currentNormalMediumRecord.txt");	
		}
		else if(difficulty == 2 && gameplay == 0 && currentScore > Menu.getCurrentNormalHardRecord())
		{
			Menu.setCurrentNormalHardRecord(currentScore); 
			wr = new FileWriter(PATH + "currentNormalHardRecord.txt"); 	
		}
		else if(difficulty == 0 && gameplay == 1 && currentScore > Menu.getCurrentTimeEasyRecord())
		{
			Menu.setCurrentTimeEasyRecord(currentScore); 
			wr = new FileWriter(PATH + "currentTimeEasyRecord.txt"); 	
		}
		else if(difficulty == 1 && gameplay == 1 && currentScore > Menu.getCurrentTimeMediumRecord())
		{
			Menu.setCurrentTimeMediumRecord(currentScore); 
			wr = new FileWriter(PATH + "currentTimeMediumRecord.txt");	
		}
		else if(difficulty == 2 && gameplay == 1 && currentScore > Menu.getCurrentTimeHardRecord())
		{
			Menu.setCurrentTimeHardRecord(currentScore); 
			wr = new FileWriter(PATH + "currentTimeHardRecord.txt"); 	
		}
		
		if(wr != null)
		{
			wr.write(currentScore + "");
			wr.close();
		}
	}
	
	public static void savePlays() throws IOException
	{
		Menu.setPlaysNumber(Menu.getPlaysNumber() + 1);
		
		Writer wr = new FileWriter(PATH + "playsNumber.txt");
		wr.write(Menu.getPlaysNumber() + "");
		wr.close();
	}
	
	public static void setData() throws FileNotFoundException
	{	
		File file = new File(PATH + "playsNumber.txt");
		Scanner input = new Scanner(file);
		Menu.setPlaysNumber(Integer.parseInt(input.next()) - 1); 
		input.close();
		
		file = new File(PATH + "currentNormalEasyRecord.txt");
		input = new Scanner(file);
		Menu.setCurrentNormalEasyRecord(Integer.parseInt(input.next()));  
		input.close();
		
		file = new File(PATH + "currentNormalMediumRecord.txt");
		input = new Scanner(file);
		Menu.setCurrentNormalMediumRecord(Integer.parseInt(input.next()));  
		input.close();
		
		file = new File(PATH + "currentNormalHardRecord.txt");
		input = new Scanner(file);
		Menu.setCurrentNormalHardRecord(Integer.parseInt(input.next()));  
		input.close();
		
		file = new File(PATH + "currentTimeEasyRecord.txt");
		input = new Scanner(file);
		Menu.setCurrentTimeEasyRecord(Integer.parseInt(input.next()));  
		input.close();
		
		file = new File(PATH + "currentTimeMediumRecord.txt");		
		input = new Scanner(file);
		Menu.setCurrentTimeMediumRecord(Integer.parseInt(input.next()));  
		input.close();
		
		file = new File(PATH + "currentTimeHardRecord.txt");
		input = new Scanner(file);
		Menu.setCurrentTimeHardRecord(Integer.parseInt(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement1.txt");
		input = new Scanner(file);
		Menu.setAchievement1(Boolean.valueOf(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement2.txt");
		input = new Scanner(file);
		Menu.setAchievement2(Boolean.valueOf(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement3.txt");
		input = new Scanner(file);
		Menu.setAchievement3(Boolean.valueOf(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement4.txt");
		input = new Scanner(file);
		Menu.setAchievement4(Boolean.valueOf(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement5.txt");
		input = new Scanner(file);
		Menu.setAchievement5(Boolean.valueOf(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement6.txt");
		input = new Scanner(file);
		Menu.setAchievement6(Boolean.valueOf(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement7.txt");
		input = new Scanner(file);
		Menu.setAchievement7(Boolean.valueOf(input.next()));  
		input.close();
		
		file = new File(PATH + "achievement8.txt");
		input = new Scanner(file);
		Menu.setAchievement8(Boolean.valueOf(input.next()));  
		input.close();
	}
	
	public static void saveAchievements(int difficulty, int gameplay, int currentScore, 
										int playsNumber, int time) throws IOException
	{
		Writer wr = null;
		
		if(difficulty == 0 && gameplay == 0 && currentScore >= 5000)
		{
			Menu.setAchievement1(true); 
			wr = new FileWriter(PATH + "achievement1.txt");
		}
		else if(difficulty == 1 && gameplay == 0 && currentScore >= 5000)
		{
			Menu.setAchievement2(true); 
			wr = new FileWriter(PATH + "achievement2.txt");			
		}
		else if(difficulty == 2 && gameplay == 0 && currentScore >= 5000)
		{
			Menu.setAchievement3(true); 
			wr = new FileWriter(PATH + "achievement3.txt");
		}
		else if(difficulty == 0 && gameplay == 1 && time == 0)
		{
			Menu.setAchievement4(true); 
			wr = new FileWriter(PATH + "achievement4.txt");		
		}
		else if(difficulty == 1 && gameplay == 1 && time == 0)
		{
			Menu.setAchievement5(true); 
			wr = new FileWriter(PATH + "achievement5.txt");		
		}
		else if(difficulty == 2 && gameplay == 1 && time == 0)
		{
			Menu.setAchievement6(true); 
			wr = new FileWriter(PATH + "achievement6.txt");
		}
		
		if(playsNumber == 2000)
		{
			Menu.setAchievement7(true); 
			wr = new FileWriter(PATH + "achievement7.txt");	
		}
		if(playsNumber == 5000)
		{
			Menu.setAchievement8(true); 
			wr = new FileWriter(PATH + "achievement8.txt");	
		}
		if(wr != null)
		{
			wr.write("true");
			wr.close();
		}	
	}
}
