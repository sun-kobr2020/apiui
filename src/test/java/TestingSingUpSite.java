import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class TestingSingUpSite {

    @BeforeMethod
    public void appSetup() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        SingUpPage.driver = new ChromeDriver();
        SingUpPage.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        SingUpPage.driver.get("https://rocit.ru/registration");
    }

    @Test(priority = 1)
    public void checkingPresenceMargin() throws InterruptedException {
        SingUpPage.Name = SingUpPage.WaitTextMargin("//*[@id=\"registration_firstName\"]");
        String NameColor = SingUpPage.driver.findElementByXPath("//*[@id=\"registration_firstName\"]").getCssValue("color");
        System.out.println("oldcolor =" + NameColor);
        String NewNameColor = SingUpPage.ColorText2(NameColor);
        System.out.println("newcolor =" + NewNameColor);
        SingUpPage.LastName = SingUpPage.WaitTextMargin("//*[@id=\"registration_lastName\"]");
        SingUpPage.Email = SingUpPage.WaitTextMargin("//*[@id=\"registration_email\"]");
        SingUpPage.Pass = SingUpPage.WaitTextMargin("//*[@id=\"registration_password\"]");
        SingUpPage.Button = SingUpPage.WaitTextMargin("//button[contains(text(),'Зарегистрироваться')]");
        Assert.assertTrue(SingUpPage.Name);
        Assert.assertTrue(SingUpPage.LastName);
        Assert.assertTrue(SingUpPage.Email);
        Assert.assertTrue(SingUpPage.Pass);
        Assert.assertTrue(SingUpPage.Button);

    }

    @Test(priority = 2)
    public void checkingPresenceText() {
        String FIOColor;
        for (int i = 1; i < 5; i++) {
            SingUpPage.Text_Margin = SingUpPage.driver.findElement(By.cssSelector(".form-group:nth-child(" + i + ") > .form-control-label")).isDisplayed();
            FIOColor = SingUpPage.driver.findElement(By.cssSelector(".form-group:nth-child(" + i + ") > .form-control-label")).getCssValue("color");
            FIOColor = SingUpPage.ColorText2(FIOColor);
            Assert.assertTrue(SingUpPage.Text_Margin);
            Assert.assertEquals(FIOColor, "Dark");
            //System.out.println(i + " Text_Margin =" + SingUpPage.Text_Margin);
            //System.out.println(i + " IterraciyaFIOColor =" + FIOColor);

        }

    }

    @Test(priority = 3)
    public void checkingErrorText() {

        SingUpPage.driver.findElementByXPath("//button[contains(text(),'Зарегистрироваться')]").click();

        String ErrorTextColor;
        for (int i = 1; i < 5; i++) {
            SingUpPage.Text_Margin = !(SingUpPage.driver.findElement(By.cssSelector(".form-group:nth-child(" + i + ") > .form-control-label")).isDisplayed());
            SingUpPage.Text_Margin_feedback = SingUpPage.assertElementNotPresentBase(By.cssSelector(".form-group:nth-child("+i+") > .form-control-feedback"));
            ErrorTextColor = SingUpPage.driver.findElement(By.cssSelector(".form-group:nth-child(" + i + ") > .form-control-label")).getCssValue("color");
            ErrorTextColor = SingUpPage.ColorText2(ErrorTextColor);
            Assert.assertFalse(SingUpPage.Text_Margin);
            Assert.assertEquals(ErrorTextColor, "Red");
            /*System.out.println(i + " Text_Margin =" + SingUpPage.Text_Margin);
            System.out.println(i + " Text_Margin_feedback =" + SingUpPage.Text_Margin_feedback);
            System.out.println(i + " ErrorTextColor =" + ErrorTextColor);*/

        }
    }
    @Test (priority = 4)
    public void registration () throws Exception {

        SingUpPage.driver.findElementByXPath("//a[contains(text(),'Регистрация')]").click();
        SingUpPage.WaitTextMargin("//*[@id=\"registration_firstName\"]");
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_firstName\"]").sendKeys(RsN);
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_lastName\"]").sendKeys(RsLN);
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_email\"]").sendKeys(myEmailAddress);
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_password\"]").sendKeys(RandomString_PassUser);
        SingUpPage.takeSnapShot(SingUpPage.driver, "C://scr//test.png") ;
        SingUpPage.driver.findElementByXPath("//button[contains(text(),'Зарегистрироваться')]").click();
        List<WebElement> email = SingUpPage.driver.findElementsByXPath("//div[contains(text(),'Это значение уже используется.')]");
        System.out.println("email === "+email);
        Assert.assertEquals(email.size(), 0);
        System.out.println("Имя ="+RsN);
        System.out.println("Фамилие ="+RsLN);
        System.out.println("myEmailAddress ="+myEmailAddress);
        System.out.println("RandomString_PassUser ="+RandomString_PassUser);
        SingUpPage.textPostReg = SingUpPage.WaitTextMargin("//h1[contains(.,'РОЦИТ — ваш помощник в интернете')]");
        SingUpPage.ButtonPostReg = SingUpPage.WaitTextMargin("//button[contains(text(),"+FirstLetter+")]");
        SingUpPage.driver.findElementByXPath("//button[contains(text(),"+FirstLetter+")]").click();
        //System.out.println("textPostReg "+SingUpPage.textPostReg);
        //System.out.println("ButtonPostReg "+SingUpPage.ButtonPostReg);
        Assert.assertTrue(SingUpPage.textPostReg);
        Assert.assertTrue(SingUpPage.ButtonPostReg); //a[contains(text(),'Выйти')]
        SingUpPage.driver.findElementByXPath("//a[contains(text(),'Выйти')]").click(); //a[contains(text(),'Регистрация')]
        SingUpPage.textPostReg = SingUpPage.WaitTextMargin("//a[contains(text(),'Регистрация')]");
        SingUpPage.Text_Margin = SingUpPage.driver.findElement(new By.ByXPath("//a[contains(text(),'Регистрация')]")).isDisplayed();

    }
    @Test (priority = 5)
    public void authorization () throws Exception {
        SingUpPage.driver.findElementByXPath("//a[contains(text(),'Регистрация')]").click();
        SingUpPage.WaitTextMargin("//*[@id=\"registration_firstName\"]");
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_firstName\"]").sendKeys(RsN);
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_lastName\"]").sendKeys(RsLN);
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_email\"]").sendKeys(myEmailAddress);
        SingUpPage.driver.findElementByXPath("//*[@id=\"registration_password\"]").sendKeys(RandomString_PassUser);
        SingUpPage.takeSnapShot(SingUpPage.driver, "C://scr//test.png");
        SingUpPage.driver.findElementByXPath("//button[contains(text(),'Зарегистрироваться')]").click();
        List<WebElement> email = SingUpPage.driver.findElementsByXPath("//div[contains(text(),'Это значение уже используется.')]");
        Assert.assertEquals(email.size(), 0);
        System.out.println("Имя ="+RsN);
        System.out.println("Фамилие ="+RsLN);
        System.out.println("myEmailAddress ="+myEmailAddress);
        System.out.println("RandomString_PassUser ="+RandomString_PassUser);
        SingUpPage.textPostReg = SingUpPage.WaitTextMargin("//h1[contains(.,'РОЦИТ — ваш помощник в интернете')]");
        SingUpPage.ButtonPostReg = SingUpPage.WaitTextMargin("//button[contains(text(),"+FirstLetter+")]");
        //SingUpPage.driver.findElementByXPath("//button[contains(text(),"+FirstLetter+")]").click();
        //System.out.println("textPostReg "+SingUpPage.textPostReg);
        //System.out.println("ButtonPostReg "+SingUpPage.ButtonPostReg);
        Assert.assertTrue(SingUpPage.textPostReg);
        Assert.assertTrue(SingUpPage.ButtonPostReg); //a[contains(text(),'Выйти')] Это значение уже используется.
    }

        @AfterMethod()
        public void close () {
            SingUpPage.driver.manage().deleteAllCookies();
            SingUpPage.driver.close();
            SingUpPage.driver.quit();
        }

    //генерация числа от 5 до 10
    int a = (int) (5 + (Math.random() * (10 - 5)));

    //построение ФИО и Email
    public String NameUser = SingUpPage.generate(a);
    String LastUser = SingUpPage.generate(a);
    String passUser = SingUpPage.generate(a);
    String mailUser = SingUpPage.generate(a);
    String time = String.valueOf(System.nanoTime());
    String RandomStringNameUser = NameUser.toLowerCase();
    String FirstLetter = RandomStringNameUser.substring(0, 1);
    String RandomStringLastNameUser = LastUser.toLowerCase();
    String myEmailAddress = mailUser + time + "@gmail.com";
    String RandomString_PassUser = passUser+time;
    String RsN = RandomStringNameUser.substring(0, 1).toUpperCase()+RandomStringNameUser.substring(1);
    String RsLN = RandomStringLastNameUser.substring(0, 1).toUpperCase()+RandomStringLastNameUser.substring(1);

}
