package base;

import org.openqa.selenium.*;

public class BasePage {
    public static WebDriver driver;

    public void setDriver(WebDriver driver){
        BasePage.driver=driver;
    }

    public void navigateToUrl(String url){
        driver.get(url);
    }

    //findElement
    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected void set(By locator ,String text){
        find(locator).clear();
        find(locator).sendKeys(text);
    }
    protected void SendEnterKey(By locator){
        find(locator).sendKeys(Keys.ENTER);
    }


    protected  void click(By locator){
        try {
            find(locator).click();

        } catch (ElementClickInterceptedException e) {
            System.out.println("⚠️ Click intercepted. Trying JavaScript click for: " + locator);
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            System.out.println("❌ Could not click element: " + locator);
            ex.printStackTrace();
        }
    }
    }


