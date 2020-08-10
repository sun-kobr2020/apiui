import com.google.common.io.Files;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.security.SecureRandom;

public class SingUpPage {
    public static  boolean Text_Margin;
    public static boolean Text_Margin_feedback;
    public static ChromeDriver driver;
    static boolean Name;
    public static boolean LastName = false;
    public static boolean Email = false;
    public static boolean Pass = false;
    public static boolean Button = false;
    public static boolean textPostReg = false;
    public static boolean ButtonPostReg = false;



    public static String ColorText2 (String a) {
        switch (a) {
            case "":
                a = "NotColor";
                break;
            case "rgba(85, 89, 92, 1)":
                a = "DarkSulfur";
                break;
            case "rgba(129, 138, 145, 1)":
                a = "Dark";
                break;
            case "rgba(255, 87, 34, 1)":
                a = "Red";
                break;
            default : a="Other_color";
        }
        return a;
    }

    static boolean WaitTextMargin(String b) throws InterruptedException {
        for (int second = 0; ; second++) {
            //Thread.sleep(150);
            if (second >= 3 || b.equals(null)|| b.equals("")) return false;
            try {
                if (driver.findElementByXPath(b).isDisplayed()){
                    break;
                }
                if (driver.findElementsByXPath(b).size()<=0){
                    return false;
                }
            } catch (Exception e) {break;}
        }
        return true;
    }

    ///////////////////////////////функция генерации набора англ. букы//////////////////////////
    //__________________________________________________________________________________________________//
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generate(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
    //__________________________________________________________________________________________________//
    public static boolean assertElementNotPresentBase(By element) throws IllegalArgumentException {
        java.util.List<WebElement> sizeElement = driver.findElements(element);
        return sizeElement.size() != 0;
    }

    public static void takeSnapShot(ChromeDriver driver, String fileWithPath) throws Exception{
        TakesScreenshot scrShot = driver;
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        Files.copy(SrcFile,DestFile);
    }
}
