package com.mca.extraction;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.txt.read.Txt_Read;
import com.txt.write.writeData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CinFinal1
{
	public static void main(String[] args) throws IOException
	{
		String inFilename = "C:\\Users\\leelaram.j\\Downloads\\mcaScrapIP1.txt";
		String opFileName = "C:\\Users\\leelaram.j\\Downloads\\mcaScrapOP11.txt";
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,10);
		//Increase the value of 5 in the next line to 10 if script is failing frequently
	    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
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
	    		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(gsearch);
	            driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
	            int capchtaCheck = driver.findElements(By.id("captcha-form")).size();
	            if(capchtaCheck==1)
	            {
	            	Thread.sleep(90000);
	            	driver.get("https://www.google.com");
		    		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(gsearch);
		            driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
	            	
	            }
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id=''search'/div/div/div[1]/div[1]/a")));
	            int a = driver.findElements(By.xpath("//div[@id=''search'/div/div/div[1]/div[1]/a")).size();
	            if(a==0)
	            {
	            	continue;
	            }
	            driver.findElement(By.xpath("//div[@id=''search'/div/div/div[1]/div[1]/a")).click();
	            int counter1 = driver.findElements(By.xpath("//table[@class='table table-striped']/thead/tr/td[2]")).size();
	            int counter2 = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[2]")).size();
	            int counter3 = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr[2]/td[2]")).size();
	            int counter4 = driver.findElements(By.xpath("//div[@class='col-lg-6 col-md-6 col-sm-12 col-xs-12']/p[4]")).size();
	            int counter = counter1+counter2+counter3+counter4;
	            if (counter<4)
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
	    catch (IOException e)
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
