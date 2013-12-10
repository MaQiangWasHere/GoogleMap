/**
 * 
 */
package com.gmap;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.Relative;
import org.sikuli.api.ScreenLocation;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.script.FindFailed;
import org.sikuli.webdriver.ImageElement;
import org.sikuli.webdriver.SikuliFirefoxDriver;
import org.sikuli.webdriver.support.FindByImage;
import org.sikuli.webdriver.support.SikuliPageFactory;

/**
 * @author twer
 * 
 */
public class GoogleMapTestOfTest {

	/**
	 * 
	 */
	public GoogleMapTestOfTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws MalformedURLException
	 * @throws InterruptedException 
	 * @throws FindFailed 
	 */
	public static void main(String[] args) throws MalformedURLException, InterruptedException, FindFailed {
		// TODO Auto-generated method stub
		SikuliFirefoxDriver driver = new SikuliFirefoxDriver();
//		driver.get("https://ditu.google.com/maps?&z=12&hl=ZH-cn&ll=34.38934588891087,108.9400863647461");
		driver.navigate().to("https://ditu.google.com/maps?&z=12&hl=ZH-cn");
		
		driver.manage().window().maximize();
		
		GoogleMapPage page = SikuliPageFactory.initElements(driver,
				GoogleMapPage.class);
		page.searchFor("西安");

		Xian xianMap = SikuliPageFactory.initElements(driver, Xian.class);
		xianMap.yanta.doubleClick();
		xianMap.zoomOut();
		xianMap.zoomOut();
		xianMap.zoomOut();
		
		//drag and drop
		ScreenRegion s = new DesktopScreenRegion();
		ScreenLocation c = s.getCenter();
		Mouse mouse = new DesktopMouse();
		mouse.click(c);
		while(driver.findImageElement(new URL("file:///Users/twer/sikuli_source/weishanhu.png")) == null){
			c = s.getCenter();
			mouse.drag(c);
			mouse.drop(Relative.to(c).left(900).getScreenLocation());
		}
		
		driver.navigate().to("https://ditu.google.com/maps?&z=12&hl=ZH-cn&ll=34.683476,117.270699");
		
		Target imageTarget = new ImageTarget(new URL("file:///Users/twer/sikuli_source/weishanhujingqu2.png"));
		ScreenRegion r = s.wait(imageTarget,5000);
        mouse.click(r.getCenter());
        mouse.click(r.getCenter());
        mouse.click(s.wait(new ImageTarget(new URL("file:///Users/twer/sikuli_source/huoquluxian.png")), 5000).getCenter());
//        driver.findElementByLinkText("获取路线").click();
        driver.findElementByCssSelector("#d_d").sendKeys("西安钟楼");
        driver.findElementByCssSelector("#d_sub").click();
		System.out.println("over");
		driver.quit();
	}

	public static class Xian extends GoogleMapPage {
		@FindByImage(url = "file:///Users/twer/sikuli_source/yantaqu.png")
		public ImageElement yanta;

	}

	public static class GoogleMapPage {

		@FindBy(how = How.ID, using = "gbqfq")
		private WebElement searchInput;

		@FindBy(how = How.CLASS_NAME, using = "css-3d-layer")
		public WebElement map;
		
		@FindBy(how = How.CSS, using = "div[title='缩小']")
		private WebElement zoomOut;
		
		@FindBy(how = How.CSS, using = "div[title='放大']")
		private WebElement zoomIn;

		@FindByImage(url = "https://dl.dropbox.com/u/5104407/plus.png")
		private ImageElement plus;

		public void zoomOut() {
			zoomOut.click();
		}

		public void zoomIn() {
			zoomIn.click();	
		}
		public void searchFor(String text) {
			searchInput.clear();
			searchInput.sendKeys(text);
			searchInput.submit();
		}
	}
}
