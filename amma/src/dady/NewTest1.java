package dady;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NewTest1 
{
	WebDriver driver;
  @Test(priority=1)
  public void launch() 
  {
	  System.setProperty("webdriver.chrome.driver","F:\\kumar\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("https://www.facebook.com/");
  }
  @Test(priority=2)
  public void validate() throws Exception
  {
	  String x=driver.getTitle();
	  if(x.contains("Facebook"))
	  {
		  System.out.println("title test was passed");
	  }
	  else 
	  {
		  System.out.println("test was failed");
		  File f=(File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  File d=new File("ss.png");
		  FileUtils.copyFile(f,d);
		  Reporter.log("ss.png");
	  }
	  }
  @Test(dependsOnMethods= {"validate"})
  @Parameters({"ud","pd"})
  public void login(String x, String y) 
  {
  
	 
		  driver.manage().timeouts().implicitlyWait(23,TimeUnit.SECONDS);
		  driver.findElement(By.name("email")).sendKeys(x);
		  driver.findElement(By.name("pass")).sendKeys(y);
		  driver.findElement(By.xpath("//*[@aria-label='Log In']")).click();
  }
	  @Test(priority=4)
	  public void login1() 
	  {
		  driver.close();
  }
}
