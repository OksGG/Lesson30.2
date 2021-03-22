import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class TaskTest {
    private WebDriver webDriver;
    WebElement element;

    @Before
    public void driver() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://savkk.github.io/selenium-practice/");
        Thread.sleep(2000);
    }


    public void click() {
        element = webDriver.findElement(By.xpath("//*[@id=\"back\"]/a"));
        element.click();
    }
    public void clickReturn() {
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/button[3]"));
        element.click();
    }


    public void fifthTask() throws InterruptedException {
        element = webDriver.findElement(By.xpath("//*[@id=\"iframe\"]"));
        element.click();
        Thread.sleep(2000);
        webDriver.switchTo().frame("code-frame");
        String pass = webDriver.findElement(By.xpath("//label[@id=\"code\"]")).getText();
        String[] parts = pass.split(" ");
        String part= parts[parts.length-1];
        System.out.println(part);
        webDriver.switchTo().defaultContent();
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/input[1]"));
        element.sendKeys(part);
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/input[2]"));
        element.click();
        Thread.sleep(2000);
        String firstTask = webDriver.findElement(By.xpath("//*[@id=\"back\"]/a")).getText();
        Assert.assertTrue(firstTask.contains("Great! Return to menu"));
        click();
    }


    public void sixthTask() throws InterruptedException {
        element = webDriver.findElement(By.xpath("//*[@id=\"alerts\"]"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/button[1]"));
        element.click();
        Alert alert = webDriver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        String[] parts = alertText.split(" ");
        String part= parts[parts.length-1];
        System.out.println(part);
        Thread.sleep(2000);
        webDriver.switchTo().defaultContent();
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/button[2]"));
        element.click();
        alert.sendKeys(part);
        alert.accept();
        Thread.sleep(2000);
        String sixthTask = webDriver.findElement(By.xpath("//*[@id=\"content\"]/label")).getText();
        Assert.assertTrue(sixthTask.contains("Great!"));
         sixthTask = webDriver.findElement(By.xpath("//*[@id=\"content\"]/button[3]")).getText();
        Assert.assertTrue(sixthTask.contains("RETURN TO MENU"));
        clickReturn();
         alert = webDriver.switchTo().alert();
         alert.accept();
    }

    public void seventhTask() throws InterruptedException {
        element = webDriver.findElement(By.xpath("//*[@id=\"table\"]"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[4]/td[1]/input"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[5]/td[1]/input"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/input"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[1]/input"));
        element.sendKeys("Test");
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[2]/input"));
        element.sendKeys("Petr");
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[3]/input"));
        element.sendKeys("Russia");
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input"));
        element.click();
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[1]/input"));
        element.sendKeys("Testit");
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[2]/input"));
        element.sendKeys("Ann");
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[3]/input"));
        element.sendKeys("Russia");
        element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input"));
        element.click();
        WebElement table =      webDriver.findElement(By.xpath("//*[@id=\"customers\"]"));
                 List<WebElement> rowsList = table.findElements(By.tagName("tr"));

        List<WebElement> columnsList = null;

        for (WebElement row : rowsList) {
            System.out.println();
            columnsList = row.findElements(By.tagName("td"));

            for (WebElement column : columnsList) {
                System.out.print(column.getText() + ", ");
            }
        }
        String seventh = webDriver.findElement(By.xpath("//*[@id=\"back\"]/a")).getText();
        Assert.assertTrue(seventh.contains("Great! Return to menu"));
        click();
    }



    @Test
    public void test1() throws InterruptedException {
       fifthTask();
    }

    @Test
    public void test2() throws InterruptedException {
        sixthTask();
    }

    @Test
   public void test3() throws InterruptedException {
       seventhTask();

    }

    @After
    public void close() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
