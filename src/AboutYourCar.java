//Quynh Nguyen - Automation Assessment 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;



public class AboutYourCar {
	
	public static void SelectComboBox (WebDriver driver,String ComboBoxText, String ComboBoxItem)
	{
	
		if (!ComboBoxText.isEmpty())
		{
		driver.findElement(By.xpath("//span[contains(.,'"+ComboBoxText+"')]")).click();
		}
		
		if (!ComboBoxItem.isEmpty())
		{
		WebDriverWait wait = new WebDriverWait(driver,30);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(.,'"+ComboBoxItem+"')]")));
       
        
        driver.findElement(By.xpath("//li[contains(.,'"+ComboBoxItem+"')]")).click();
		}
	}
	
	public static void verifyTextPresent(WebDriver driver,String value,String xpath)
	{
	
        try {
			Thread.sleep(3000);
		  } catch (InterruptedException e) {

			e.printStackTrace();
		  }
	 
	 System.out.println("Text from page: "+driver.findElement(By.xpath(xpath)).getText());
	   
      if (driver.findElement(By.xpath(xpath)).getText().contains(value))
      {
             System.out.println("Verification Passed - The correct text is displayed on the page.");
      }
     else
      {
             System.out.println("Verification Failed - An incorrect text is displayed on the page.");
      }
	}
	
	public static void NavigatePage(WebDriver driver, String appUrl,String expectedTitle) {
		
		// launch the chrome browser and open the application url
        
       driver.get(appUrl);
       driver.manage().window().maximize();	              
       
//compare the expected title of the page with the actual title of the page and print the result
      
       String actualTitle = driver.getTitle();
       System.out.println("Title of the page: "+actualTitle);
       
       if (expectedTitle.equals(actualTitle))
       {
              System.out.println("Verification Passed - The correct title is displayed on the web page.");
       }
      else
       {
              System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
       }
	}
	
	public static void TestCase1_InputValidData() {
		
		System.out.println("Test case 1: Input valid data on About Your Car page");
		
		//launch the page
		  System.setProperty("webdriver.chrome.driver","/Users/hongquynh/Applications/chromedriver");
         WebDriver driver = new ChromeDriver();
    
         String appUrl = "https://car.iselect.com.au/car/compare-car-insurance/gatewayStore";
         
         NavigatePage(driver,appUrl,"Car Insurance");

        
// select the options

        
        SelectComboBox(driver,"Make","Hyundai");
        SelectComboBox(driver,"","Elantra");
        SelectComboBox(driver,"","2020");
        SelectComboBox(driver,"","AD.2 MY20 Go Sedan 4dr Man 6sp 2.0i");
        SelectComboBox(driver,"","Blue");
        SelectComboBox(driver,"Third Party Property, Fire and Theft","");
        SelectComboBox(driver,"Please select","I have bought a new car");
        SelectComboBox(driver,"Please select","Alarm");
        SelectComboBox(driver,"Yes","");
        SelectComboBox(driver,"Select factory options, if any","Standard Paint");
        driver.findElement(By.xpath("//div[6]/div/button")).click();
        SelectComboBox(driver,"Select...","Airbag");
        driver.findElement(By.cssSelector(".bc > .ey > .b7 > .gr > .gq:nth-child(2) > .db")).click();
        SelectComboBox(driver,"","Bluetooth");
        SelectComboBox(driver,"Please select","$200");
        SelectComboBox(driver,"Please select","$300");
        driver.findElement(By.xpath("//div[10]/div[2]/div/div/button/span")).click();
        
        WebElement Element = driver.findElement(By.xpath("//button[contains(.,'Hail')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);	              
        Element.click();
        
        SelectComboBox(driver,"Please select","No, cover has lapsed");
        driver.findElement(By.xpath("//label/span")).click();
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
      	
       
        verifyTextPresent(driver,"Usage & Driver","//h1");

        
// close the web browser
        driver.close();
        System.out.println("Test case 1 is executed successfully");
	}
	
	public static void TestCase2_InputInvalidData() {
		
		System.out.println("Test case 2: Not input any data except selecting the checkbox of Term & Condition then click Continue on About Your Car page");
		
		//launch the page
		  System.setProperty("webdriver.chrome.driver","/Users/hongquynh/Applications/chromedriver");
         WebDriver driver = new ChromeDriver();
    
         String appUrl = "https://car.iselect.com.au/car/compare-car-insurance/gatewayStore";
         NavigatePage(driver,appUrl,"Car Insurance");

        
// select the options

       
        
        WebElement Element = driver.findElement(By.xpath("//button[contains(.,'Continue')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);	              
        
        driver.findElement(By.xpath("//label/span")).click();
        driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
      	
       
        verifyTextPresent(driver,"Please select an option","//div[2]/div[2]/div/span");

        
// close the web browser
        
       driver.close();
        System.out.println("Test case 2 is executed successfully");
	}
	
	public static void main(String[] args) {
		
        //run the test cases
		
		TestCase1_InputValidData();
		TestCase2_InputInvalidData(); 
		
		// terminate the program
		
		 System.exit(0);
		       }
	
}
