package com.mca.extraction;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.txt.read.Txt_Read;
import com.txt.write.writeData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CinFinal1
{
	public static void main(String[] args) throws IOException
	{
		String inFilename = "D:\\CIN_Extraction\\src\\com\\testdata\\misc_mca.txt";
		String opFileName = "C:\\Users\\leelaram.j\\Downloads\\MCA_misc1.txt";
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	    writeData.write2Txt(opFileName,"Split_ID","Input_Name","MCA_Name","CIN","Address","Status");
	    try
	    {
	    	String[] lines = Txt_Read.txtRead(inFilename);
	        for (String line : lines) 
	        {
	        	String[] arrOfStr = line.split(",");
	        	String splitId = arrOfStr[0];
	    		String inName =arrOfStr[1];
	    		String gsearch = inName+" zaubacorp";
	    		driver.get("https://www.google.com");
	    		String titleCheck = driver.getTitle();
	    		Boolean b = titleCheck.equals("Google");
	    		if (!b)
	    		{
	    			Thread.sleep(900000);
	    			driver.get("https://www.google.com");
				}
	    		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(gsearch);
	            driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
	            driver.findElement(By.xpath("//div[@class='srg']/div[1]/div/div/div[1]/a")).click();
	            int counter = driver.findElements(By.xpath("//table[@class='table table-striped']/thead/tr/td[2]")).size();
	            if (counter==0)
	            {
	            	continue;
	            }
	            String CIN = driver.findElement(By.xpath("//table[@class='table table-striped']/thead/tr/td[2]")).getText();
	            String mcaName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[2]")).getText();
	            String status = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[2]/td[2]")).getText();
	            String address = driver.findElement(By.xpath("//div[@class='col-lg-6 col-md-6 col-sm-12 col-xs-12']/p[4]")).getText();
	            writeData.write2Txt(opFileName,splitId,inName,mcaName,CIN,address,status);
	        }
	    }
	    catch (ElementNotFoundException e)
        {
        	e.printStackTrace();
        }
	    catch (NoSuchElementException ignored)
	    {
	        //return false;
	    }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
    	catch (InterruptedException e)
    	{
			e.printStackTrace();
		}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
	    driver.close();    
	}

}
