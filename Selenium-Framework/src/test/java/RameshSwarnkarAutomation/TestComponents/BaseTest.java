package RameshSwarnkarAutomation.TestComponents;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import RameshSwarnkarAutomation.ObjectClasses.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop = new Properties();
	public LandingPage landingPage;
	public ExtentReports extent;

	public WebDriver initializeDriver() throws IOException {

		String filePath = "/Users/rameshswarnkar/Automation2024/eclipse-workspace/SeleniumFrameworkDesign/src/main/java/RameshSwarnkarAutomation/Resources/GlobalData.properties";
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);

		// java ternary operator
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browserName = prop.getProperty("browser");

		/*
		 * switch (browserName) { case "chrome": driver = new ChromeDriver(); break;
		 * case "firefox": driver = new FirefoxDriver(); case "edge": driver = new
		 * EdgeDriver(); break; }
		 */
		if (browserName.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();
			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000L));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src,
				new File(System.getProperty("user.dir") + "//test-output//Screenshots//" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//test-output//Screenshots//" + testCaseName + ".png";
	}

	public ExtentReports ExtentReport() {

		String file = System.getProperty("user.dir") + "//Reports//TestReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(file);
		reporter.config().setReportName("Ramesh Swarnkar Automation Framework");
		reporter.config().setDocumentTitle("Ramesh Swarnkar Test Report");

		extent = new ExtentReports();
		extent.attachReporter(reporter);

		extent.setSystemInfo("Tester Name", "Ramesh Swarnkar");
		return extent;
	}

	/*
	 * public void getJsonDataToMap(String filePath) throws IOException {
	 * 
	 * // first we have to read the json file to string using FileUtils from java //
	 * then we have to covert the string file to HashMap using Jackson databind //
	 * utility
	 * 
	 * // reading json file to string - this will covert our json file to string
	 * format String jsonContent = FileUtils.readFileToString(new File(filePath),
	 * StandardCharsets.UTF_8);
	 * 
	 * // now we need to convert this string file to HashMaps // for that firstly
	 * add Jackson databind dependencies in pom.xml
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * 
	 * List<HashMap<String, String>> data = mapper.readValue(jsonContent, new
	 * TypeReference<List<HashMap<String, String>>>() { });
	 * 
	 * }
	 */

}