package seleniumJava.Handler;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTabHandler {

	public static void main(String[] args) throws Exception 
	{
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
//Multiple Window handlers
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
		
//Open links in new tab
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
}


