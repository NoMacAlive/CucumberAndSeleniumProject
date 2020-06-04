package stepDefinitions;
import base.BaseUtil;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;

import static org.junit.Assert.*;

public class CourseManagementSystem {
    private BaseUtil base;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private web.User user;
    private WebDriverWait wait;
    private String courseCode;


    public CourseManagementSystem(BaseUtil base){
        this.base = base;
    }


    
    @Given("user logged in as Course Coordinator")
    public void user_logged_in_as_Course_Coordinator() {
        // Write code here that turns the phrase above into concrete actions
        this.wait = new WebDriverWait(this.base.driver, 20);
        this.base.driver.get("http://localhost:8181/courseManage.html");
        WebElement currentUser = base.driver.findElement(By.id("users"));
        Select users = new Select(currentUser);
        // undergrade student
        users.selectByVisibleText("Prof. xxx");
        if(this.base.scenario.getName().equals("Course Coordinator post new course to the system")){
            this.base.setScreenShot("CourseManagement1.png");
        }else if(this.base.scenario.getName().equals("student want to know if his programme requirement of his programme has been met")) {
            this.base.setScreenShot("ProgrammeRequirement2.png");
        }
    }

    @When("enters the course details in correct format name:{string},code:{string}")
    public void enters_the_course_details_in_correct_format_name_code(String string, String string2) {
        WebElement code = this.base.driver.findElement(By.id("courseCode"));
        WebElement name = this.base.driver.findElement(By.id("courseName"));
        this.courseCode = string2;
        code.sendKeys(string2);
        name.sendKeys(string);
    }

    @When("he clicks {string} in the course management page")
    public void he_clicks_in_the_course_management_page(String string) {
        WebElement button = this.base.driver.findElement(By.id("addCourse"));
        button.click();
    }


    @Then("the new course should be displayed in the system")
    public void the_new_course_should_be_displayed_in_the_system() {
        WebElement courseList = this.base.driver.findElement(By.id("courseList"));
        List<WebElement> list = courseList.findElements(By.xpath(".//h6[@class='my-0']"));
        String courseCode = list.get(list.size()-1).getText();
        if(!this.courseCode.equals(courseCode)){
            fail();
        }
    }
}