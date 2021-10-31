import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Screenshot {
	static int index = 1;
	WebDriver driver;
	String name;

	public static void capture(WebDriver driver, String name, String user, boolean first, boolean last) throws InterruptedException
	{ 
		try
		{
			String html;
			if(first == true)
				html="<html><body><center><h2>Test case "+index+"</h2><p><b>"+name+"-"+user+"</b></p></center><div>	<a href='TestReport"+(index+1)+".html'  style='margin-left: 1100px;'>Next</a></div><center><img src='"+index+".png' width='1000' height='500'></center></body></html>";
			else if(last == true)
				html="<html><body><center><h2>Test case "+index+"</h2><p><b>"+name+"-"+user+"</b></p></center><div>	<a href='TestReport"+(index-1)+".html'  style='margin-left: 100px;'>Prev</a></div><center><img src='"+index+".png' width='1000' height='500'></center></body></html>";
			else
				html="<html><body><center><h2>Test case "+index+"</h2><p><b>"+name+"-"+user+"</b></p></center><div>	<a href='TestReport"+(index-1)+".html'  style='margin-left: 100px;'>Prev</a> <a href='TestReport"+(index+1)+".html' style='margin-left: 1000px;'>Next</a></div><center><img src='"+index+".png' width='1000' height='500'></center></body></html>";
			File f = new File("./TestReport/TestReport"+index+".html");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html);
			bw.close();
			TakesScreenshot ts= (TakesScreenshot)driver;
			File s=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(s, new File("./TestReport/"+index+".png"));
			index++;

		}
		catch(Exception e)
		{
			System.out.println(e);
			Thread.sleep(2000);
		}
	}
}