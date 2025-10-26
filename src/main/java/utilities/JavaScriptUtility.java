package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static base.BasePage.driver;

public class JavaScriptUtility extends utilities{

    public static void scrollToElementsJS(By locator){
        WebElement element =driver .findElement(locator);
        String jsScript="arguments[0].scrollIntoView();";
        ((JavascriptExecutor)driver).executeScript(jsScript,element);

    }

    public static void clickJs(By locator){
        WebElement element=driver.findElement(locator);
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",element);
    }
}
