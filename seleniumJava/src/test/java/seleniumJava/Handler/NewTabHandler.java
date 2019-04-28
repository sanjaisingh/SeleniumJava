package seleniumJava.Handler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTabHandler {

	public static WebDriver driver;
	
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//getWinHandle();
		//getTabHandle();
		getAlertHandle();
	}

//Multiple Window handlers
	public static void getWinHandle() throws Exception
	{
		driver.get("https://www.naukri.com");
		String parantWindow = driver.getWindowHandle();		
		for(String childWindows : driver.getWindowHandles())
		{
			if(!parantWindow.equalsIgnoreCase(childWindows))
			{
				driver.switchTo().window(childWindows);
				System.out.println(driver.getTitle() +" : "+ childWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parantWindow);
		System.out.println(driver.getTitle() +" : "+ parantWindow);
		Thread.sleep(5000);
		driver.close();
	}	
	
//Open links in new tab
	public static void getTabHandle() throws Exception
	{
		driver.get("https://www.gmail.com");
		List<WebElement> list = driver.findElements(By.tagName("a"));
		System.out.println(list.size());
		String parentTab = driver.getWindowHandle();
		for(WebElement ele : list)
		{
			String openLinkInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
			System.out.println(ele.getText());
			ele.sendKeys(openLinkInNewTab);
			Thread.sleep(2000);			
		}
//Switch tabs and close all tabs		
		for(String childTab : driver.getWindowHandles())
		{
			if(!parentTab.equalsIgnoreCase(childTab))
			{
				driver.switchTo().window(childTab);
				System.out.println(driver.getTitle());
				driver.close();
			}
		}
		driver.switchTo().window(parentTab);
		Thread.sleep(5000);
		driver.close();
	}
	
//Alert handling
	public static void getAlertHandle() throws Exception
	{
		driver.get("https://www.toolsqa.com/handling-alerts-using-selenium-webdriver/");
	//Simple Alert	
		driver.findElement(By.xpath("//*[@id='content']/p[4]/button")).click();
		Alert sAlert = driver.switchTo().alert();
		System.out.println("Simple Alert Title: " + sAlert.getText());
		sAlert.accept();
	//Confirmation Alert
		driver.findElement(By.xpath("//*[@id='content']/p[8]/button")).click();
		Alert cAlert = driver.switchTo().alert();
		System.out.println("Confirmation Alert Title:" + cAlert.getText());
		cAlert.accept();
	//Prompt Alert
		driver.findElement(By.xpath("//*[@id='content']/p[11]/button")).click();
		Alert pAlert = driver.switchTo().alert();
		System.out.println("Prompt Alert Title:" + pAlert.getText());
		pAlert.sendKeys("Testing selenium alert");
		pAlert.accept();
		Thread.sleep(5000);
		driver.close();
	}
}