import java.util.*;
import java.util.concurrent.TimeUnit;

import base.BaseUtil;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;


public class ProgrammeRequirement extends BaseUtil {
    private BaseUtil base;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private web.User user;
    private WebDriverWait wait;

    public ProgrammeRequirement(BaseUtil base){
        this.base = base;
    }

	@Given("user logged in as a Software Engineering Student")
	public void user_logged_in_as_a_Software_Engineering_Student() {
		this.wait = new WebDriverWait(this.base.driver, 20);
        this.base.driver.get("http://localhost:8181/programmeRequirement.html");
        WebElement currentUser = base.driver.findElement(By.id("users"));
        Select users = new Select(currentUser);
        // undergrade student
        users.selectByVisibleText("aaa");
        if(this.base.scenario.getName().equals("student want to know the programme requirement of his programme")){
            this.base.setScreenShot("ProgrammeRequirement1.png");
        }else if(this.base.scenario.getName().equals("student want to know if his programme requirement of his programme has been met")) {
            this.base.setScreenShot("ProgrammeRequirement2.png");
        }
	}

	@Given("he is in his {string} year study")
	public void he_is_in_his_year_study(String string) {
		this.user = new web.User(123, "Student", "Will");
		System.out.println("he is in his "+string+" year study");
	}

	@When("he clicks enrol")
	public void he_clicks_enrol() {
		System.out.println("he clicks enrol");
	}

	@When("he clicks my programme requirement tab")
	public void he_clicks_my_programme_requirement_tab() {
		System.out.println("he clicks my programme requirement tab");
	}

	@Then("he should see {string}, {string}, {string} as compulsory")
	public void he_should_see_as_compulsory(String string, String string2, String string3) {
		List<String> compulsoryList = Stream.of(string, string2, string3).collect(Collectors.toList());
        List<WebElement> text= base.driver.findElements(By.id("compulsoryCourses"));
        List<String> compulsoryCoursesOnPage = new ArrayList<>();
        for (String item: text.get(0).getText().split("\n")) {
            compulsoryCoursesOnPage.add(item);
        }

        assertTrue(compulsoryCoursesOnPage.equals(compulsoryList));
	}

	@Then("he should see {string}, {string}, {string} as elective")
	public void he_should_see_as_elective(String string, String string2, String string3) {
		List<String> electiveList = Stream.of(string, string2, string3).collect(Collectors.toList());
        List<WebElement> text= base.driver.findElements(By.id("electiveCourses"));
        List<String> electiveCoursesOnPage = new ArrayList<>();
        for (String item: text.get(0).getText().split("\n")) {
            electiveCoursesOnPage.add(item);
        }
        assertTrue(electiveCoursesOnPage.equals(electiveList));
	}

	@Given("his is in his 3rd year of study")
	public void his_is_in_his_3rd_year_of_study() {
		System.out.println("his is in his 3rd year of study");
	}

	@When("he is enrolled in {string}, {string}, {string}")
	public void he_is_enrolled_in(String string, String string2, String string3) {
		System.out.println("he is enrolled in "+string+", "+string2+", "+string3);
	}

	@Then("he should be told that he has {string} for the 3rd year")
	public void he_should_be_told_that_he_has_for_the_3rd_year(String string) {
		List<WebElement> text= base.driver.findElements(By.id("if-met"));
        String str = text.get(0).getText();
        assertTrue(str.equals(string));
	}


}
