package eduVidya;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Locator locator = new Locator();
		try {
			locator.createProperty();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Enter the browser(chrome/edge) you want to test on:");
		String browserName = sc.nextLine(); 
		locator.createDriver(browserName);
		
		locator.findCollegeElement();
		
		locator.selectOptions();
		
		List<WebElement> result = locator.searchResults();
		
		if(result.size()>0) {
			System.out.println("Colleges Displayed");
		}
		else {
			System.out.println("No college is available");
		}
		
		locator.closeDriver();
		sc.close();
		
	}

}

