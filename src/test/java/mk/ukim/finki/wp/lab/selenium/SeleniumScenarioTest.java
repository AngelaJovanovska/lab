package mk.ukim.finki.wp.lab.selenium;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
//    private HtmlUnitDriver driver;
//
//
//    @BeforeEach
//    private void setup() {
//        this.driver = new HtmlUnitDriver(true);
//
//    }
    private static Teacher t1;
    private static Course c1;
    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

//    private static User regularUser;
    private static Role adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private HtmlUnitDriver driver;

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup()  {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

//    private void initData()  {
//        if (!dataInitialized) {
////            t1 =teacherService.create("asa","asas");
//            c1 = courseService.save((long)1D,"nov","nekoj",(long)1D);
////            adminUser = userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
////            courseService.save((long) 2D,"name" ,"desc", (long)1D);
////            dataInitialized = true;
//
//        }
//    }

//    @Test
//    public void testScenario() throws Exception {
//        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9989/courses");
//        driver.get(url);
//        WebElement addButton = driver.findElement(By.className("add"));
//        WebElement editButton = driver.findElement(By.className("edit"));
//        WebElement deleteButton = driver.findElement(By.xpath("delete"));
//        assert !addButton.isDisplayed();
//        assert !editButton.isDisplayed();
//        assert !deleteButton.isDisplayed();
//
//
//        driver.get("http://localhost:8000/login");
//        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("user");
//        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
//        driver.findElement(By.xpath("//button[text()='Log in']")).click();
//
//
//        addButton = driver.findElement(By.xpath("//button[text()='Add']"));
//        editButton = driver.findElement(By.xpath("//button[text()='Edit']"));
//        deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
//        assert addButton.isDisplayed();
//        assert editButton.isDisplayed();
//        assert deleteButton.isDisplayed();
//
//        driver.quit();
//        List<WebElement> elements = driver.findElements(By.className("del"));
//        Assert.assertEquals(elements.size(), 0);
//        url = System.getProperty("geb.build.baseUrl", "http://localhost:999/login");
//        driver.get(url);
//        driver.findElement(By.id("username")).sendKeys("admin");
//        driver.findElement(By.id("password")).sendKeys("admin");
//        driver.findElement(By.className("btn")).click();
//        elements = driver.findElements(By.className("del"));
//        Assert.assertNotEquals(elements.size(), 0);

//    }

    private void initData()  {
        if (!dataInitialized) {
//            t1 =teacherService.create("asa","asas");
//            c1 = courseService.save((long)1D,"nov","nekoj",(long)1D);
//            courseService.save((long)1D,"nov","nekoj",(long)1D);
//            adminUser = l.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
//            dataInitialized = true;
//            courseService.create( (long)0,"name" ,"desc", (long)1);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9989/courses");
        driver.get(url);
        List<WebElement> elements = driver.findElements(By.className("del"));
        Assert.assertEquals(elements.size(), 0);
        url = System.getProperty("geb.build.baseUrl", "http://localhost:9989/login");
        driver.get(url);
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.className("btn")).click();
        elements = driver.findElements(By.className("del"));
        Assert.assertNotEquals(elements.size(), 0);

    }


}
