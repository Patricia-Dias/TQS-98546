package io.cucumber.skeleton.salary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SalarySteps {
    SalaryManager manager;
 
	@Given("the salary management system is initialized with the following data")
	public void the_salary_management_system_is_initialized_with_the_following_data(final DataTable employees) throws Throwable {
		List<Employee> emp = new ArrayList<>();
		
		List<Map<String,String>> rows  = employees.asMaps();
		for (Map<String, String> row : rows){
			emp.add(new Employee(
				Integer.parseInt(row.get("id")),
				row.get("user"),
				Float.parseFloat(row.get("salary"))));
		}
		manager = new SalaryManager(emp);
	}
 
	@When("the boss increases the salary for the employee with id {string} by {int}%")
	public void the_boss_increases_the_salary_for_the_employee_with_id_by(final String str_id, final int increaseInPercent) throws Throwable {
		Integer id = Integer.parseInt(str_id);
		manager.increaseSalary(id, increaseInPercent);
	}
 
	@Then("the payroll for the employee with id {string} should display a salary of {float}")
	public void the_payroll_for_the_employee_with_id_should_display_a_salary_of(final String str_id, final float salary) throws Throwable {
		Integer id = Integer.parseInt(str_id);
		Employee nominee = manager.getPayroll(id);
		assertEquals(nominee.getSalary(), salary);
	}

}
