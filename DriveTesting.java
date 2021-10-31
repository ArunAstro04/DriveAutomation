import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.AWTException;
import org.xml.sax.SAXException;
import org.openqa.selenium.InvalidElementStateException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.openqa.selenium.By;
import java.io.File;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class DriveTesting extends Screenshot{

	static WebDriver driver;
	static WebDriverWait wait;
	static String browserDriver, key, path, value, url, xpath, functionality, user="";
    static int count = 3;
    static boolean firstTime= true;
    static Robot robot;

    public static void main(String[] args) throws AWTException, ParserConfigurationException, InterruptedException, SAXException, IOException {
        deletePastReports();
        loginAndShare();
        editDocument_User1();
        editDocument_User2();
    }

    public static void deletePastReports() throws AWTException, ParserConfigurationException, InterruptedException, SAXException, IOException {
        File dir = new File("E:/Selenium/Drive File Sharing Tests/TestReport/");
        File[] listFiles = dir.listFiles();
        for(File file : listFiles)
            file.delete();
        System.out.println("==========================================");
        System.out.println("Files in TestReport Directory deleted");
        System.out.println("==========================================");
    }

	public static void initialize() throws AWTException, ParserConfigurationException, InterruptedException, SAXException, IOException
	{
		browserDriver = "ie-driver";
		getKeyAndValue();
		System.setProperty(key, path);
		if(browserDriver.equals("chrome-driver")){
            driver = new ChromeDriver();
        }
        else if(browserDriver.equals("gecko-driver")){
            driver = new FirefoxDriver();
        }
        else if(browserDriver.equals("edge-driver")){
            driver = new EdgeDriver();
        }
        else if(browserDriver.equals("ie-driver")){
            driver = new InternetExplorerDriver();
        }
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        robot = new Robot();
		System.out.println("==========================================");
        System.out.println("Driver Initialized Successfully");
        System.out.println("==========================================");
        if(firstTime == true){
            Screenshot.capture(driver,"PASS - Driver Initialized","No user", true, false);
            firstTime = false;
        }
	}

	public static void loginAndShare() throws AWTException, ParserConfigurationException, InterruptedException, SAXException, IOException
	{
        user = "User 1";
        initialize();
		driver.get(url);
        File file = new File("E:\\Selenium\\Drive File Sharing Tests\\inputs.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nList = (NodeList) doc.getElementsByTagName("input");
        for (int temp = 0; temp < 21; temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                xpath = eElement.getElementsByTagName("xpath").item(0).getTextContent();
                functionality = eElement.getElementsByTagName("function").item(0).getTextContent();
                value = eElement.getElementsByTagName("value").item(0).getTextContent();
                Thread.sleep(1000);
                performOperation();
            }
        }
        Thread.sleep(3000);
	}

	public static void editDocument_User1() throws AWTException, ParserConfigurationException, InterruptedException, SAXException, IOException
	{
		user = "User 2";
        driver.quit();
		initialize();
		driver.get(url);
        File file = new File("E:\\Selenium\\Drive File Sharing Tests\\inputs.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nList = (NodeList) doc.getElementsByTagName("input");
        for (int temp = 21; temp < 33; temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                xpath = eElement.getElementsByTagName("xpath").item(0).getTextContent();
                functionality = eElement.getElementsByTagName("function").item(0).getTextContent();
                value = eElement.getElementsByTagName("value").item(0).getTextContent();
                Thread.sleep(1000);
                performOperation();
            }
        }
        Thread.sleep(3000);
	}

	public static void editDocument_User2() throws AWTException, ParserConfigurationException, InterruptedException, SAXException, IOException
	{
        user = "User 3";
		driver.quit();
		initialize();
		driver.get(url);
        File file = new File("E:\\Selenium\\Drive File Sharing Tests\\inputs.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nList = (NodeList) doc.getElementsByTagName("input");
        for (int temp = 33; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                xpath = eElement.getElementsByTagName("xpath").item(0).getTextContent();
                functionality = eElement.getElementsByTagName("function").item(0).getTextContent();
                value = eElement.getElementsByTagName("value").item(0).getTextContent();
                Thread.sleep(1000);
                performOperation();
            }
        }   
        Thread.sleep(3000);
        driver.quit();
        System.exit(0);
	}
	public static void performOperation() throws AWTException, ParserConfigurationException, InterruptedException  , SAXException, IOException
    {
    	try{
    	if(functionality.equals("sendKeys")){
                driver.findElement(By.xpath(xpath)).sendKeys(value);
                System.out.println("Test case : Pass");
                Screenshot.capture(driver,"PASS", user, false, false);
                count = 3;
            }
            if(functionality.equals("clear")){
                driver.findElement(By.xpath(xpath)).clear();
            }
            if(functionality.equals("click")){
                if(browserDriver.equals("ie-driver")){
                    if(value.equals("first")){
                        robot.mouseMove(750, 135);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                    else if(value.equals("second")){
                        robot.mouseMove(375, 135);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                    else if(value.equals("third")){
                        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[contains(text(),'Cancel')]")));
                        System.out.println("Test case : Pass");
                        Screenshot.capture(driver,"PASS", user, false, false);
                        count = 3;
                    }
                    else if(value.equals("fourth")){
                        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[contains(text(),'Done')]")));
                        System.out.println("Test case : Pass");
                        Screenshot.capture(driver,"PASS", user, false, false);
                        count = 3;
                    }
                    else {
                        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
                        System.out.println("Test case : Pass");
                        Screenshot.capture(driver,"PASS", user, false, false);
                        count = 3;
                    }
                }
                else {
                    driver.findElement(By.xpath(xpath)).click();
                    System.out.println("Test case : Pass");
                    Screenshot.capture(driver,"PASS", user, false, false);
                    count = 3;
                }
            }
            if(functionality.equals("doubleClick")){
            	Thread.sleep(3000);
                if(browserDriver.equals("ie-driver")){
                    if(xpath.contains("document")){
                        robot.mouseMove(300, 300);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                    else{
                        robot.mouseMove(400, 235);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    }
                }
                else{
                	Actions action = new Actions(driver);
    				action.doubleClick(driver.findElement(By.xpath(xpath))).perform();
                    System.out.println("Test case : Pass");
                    Screenshot.capture(driver,"PASS", user, false, false);
                    count = 3;
    				Thread.sleep(3000);
                }
            }
            if(functionality.equals("sleep")){
            	Thread.sleep(5000);
            }
            if(functionality.equals("tab")){
                driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
            }
            if(functionality.equals("switchTo"))
            {
            	if(value.equals("defaultContent")){
            		driver.switchTo().defaultContent();
                }
            	else if(value.equals("tab")){
            		for(String winHandler: driver.getWindowHandles())
            			driver.switchTo().window(winHandler);
                    if(browserDriver.equals("ie-driver")){
                        Thread.sleep(1000);
                        driver.manage().window().maximize();
                        Thread.sleep(2000);
                    }
            	}
            	else{
	            	Thread.sleep(1000);
	            	driver.switchTo().frame(driver.findElement(By.cssSelector(xpath)));
                    Screenshot.capture(driver,"PASS", user, false, false);
                    System.out.println("Test case : Pass");
                    count = 3;
	            }
            }
            functionality = "";
            
        }
        catch(NoSuchElementException e){
        	functionality = "";
        	Screenshot.capture(driver,"FAIL", user, false, false);
            System.out.println("Test case : FAIL");
            count--;
            if(count <= 0){
                System.out.println("SSS");
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            }
        }
        catch(NoSuchWindowException e){
            functionality = "";
            Screenshot.capture(driver,"FAIL", user, false, false);
            System.out.println("Test case : FAIL");
            count--;
            if(count <= 0){
                System.out.println("SSS");
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            }
        }
        catch(Exception e){
            functionality = "";
            Screenshot.capture(driver,"FAIL", user, false, false);
            System.out.println("Test case : FAIL");
            count--;
            if(count <= 0)
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }

	public static void getKeyAndValue() throws AWTException, ParserConfigurationException, InterruptedException, SAXException, IOException
    {
        File file = new File("E:\\Selenium\\Drive File Sharing Tests\\inputs.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nList = (NodeList) doc.getElementsByTagName(browserDriver);
        Node nNode = nList.item(0);
        Element eElement = (Element) nNode;
        key = eElement.getElementsByTagName("name").item(0).getTextContent();
        path = eElement.getElementsByTagName("path").item(0).getTextContent();
        nList = (NodeList) doc.getElementsByTagName("login-page");
        nNode = nList.item(0);
        eElement = (Element) nNode;
        url = eElement.getElementsByTagName("url").item(0).getTextContent();
    }
}