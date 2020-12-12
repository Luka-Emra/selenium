import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CommandsTest
{
    @Test
    public void automationTest()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/context_menu");
        driver.manage().window().maximize();

        WebElement element= driver.findElement(By.id("hot-spot"));
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement element_1=driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        element_1.click();

        try{                               // without this time delay, .isEnabled() would return false
            Thread.sleep(5000);       // as the element needs some time to become enabled
        }
        catch(InterruptedException ie)
        {
            System.out.println("InterruptedException");
        }

        System.out.println("isEnabled: " + element_1.isEnabled());

        WebElement text=driver.findElement(By.xpath("//*[@id=\"message\"]"));

        System.out.println("isDisplayed: " + text.isDisplayed());

        driver.navigate().to("http://uitestpractice.com/Students/Actions");
        WebElement table_1 =driver.findElement(By.name("one"));
        WebElement table_2 =driver.findElement(By.name("two"));

        Actions action_1 = new Actions(driver);
        action_1.clickAndHold(table_1).build().perform();
        action_1.moveToElement(table_1).release();
        System.out.println("Background color has changed: " + (!table_1.getCssValue("background-color").equals(table_2.getCssValue("background-color"))));

        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        WebElement element_A=driver.findElement(By.xpath("//*[@id=\"column-a\"]"));
        WebElement element_B=driver.findElement(By.xpath("//*[@id=\"column-b\"]"));
        Point point_A = element_A.getLocation();
        Point point_B = element_B.getLocation();
        Dimension dimension_A= element_A.getSize();
        System.out.println("B div is located to the right of the A div: " + (point_A.x + dimension_A.width < point_B.x));
    }
}
