package eduVidya;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Locator{
	
	public static WebDriver driver;
	
	public static Properties property;
	
	
	public Properties createProperty() throws FileNotFoundException {
		
		property = new Properties();
		FileOutputStream objFile = new FileOutputStream("C:\\Users\\2318660\\eclipse-workspace\\eduVidya\\src\\test\\resources\\OR.properties");
		
		property.setProperty("baseUrl", "https://www.eduvidya.com/");
		property.setProperty("courseName", "Science");
		property.setProperty("cityName", "Chennai");

		
		try {
			property.store(objFile, null);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return property;
	}
	
	
	public WebDriver createDriver(String name) {
		
		driver = DriverSetup.getDriver(name);
		driver.get(property.getProperty("baseUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	
	public void findCollegeElement() {
		
		driver.findElement(By.linkText("Colleges")).click();
		
	}
	
	public void selectOptions() {
		
		WebElement course = driver.findElement(By.xpath("//select[@id='ddl_Category']"));
		
		Select courseOption = new Select(course);
		
		courseOption.selectByVisibleText(property.getProperty("courseName"));
		
		WebElement city = driver.findElement(By.xpath("//select[@id='ddl_City']"));
		
		Select cityOption = new Select(city);
		
		cityOption.selectByVisibleText(property.getProperty("cityName"));
		
		driver.findElement(By.xpath("//input[@id='btnSearch']")).click();
		
		
		/*TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File des = new File("C:\\Users\\2318660\\eclipse-workspace\\eduVidya\\src\\test\\resources\\colleges.png");

        try {
            FileUtils.copyFile(src, des);
            
        } catch (IOException e) {
            e.printStackTrace();
        }*/
		
		
	}
	
	public List<WebElement> searchResults(){
		
		List<WebElement> result = driver.findElements(By.xpath("//*[@id=\"pnllist\"]/child::div/ul/li"));
		
		return result;
		
	}
	
	public void closeDriver() {
		driver.quit();
	}

}
