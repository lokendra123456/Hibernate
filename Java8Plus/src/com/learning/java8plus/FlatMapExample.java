package com.learning.java8plus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class FlatMapExample {

	public static void main(String[] args) {
		List<Department> departments = new ArrayList<>();
		Department department = new Department();
		department.setName("HR");
		List<SubDepartMent> subDepartments = buildObject();
		department.setSunDepartments(subDepartments);
		departments.add(department);
		Employee employee = new Employee();
		employee.setDepartment(departments);

		Optional.ofNullable(employee).map(Employee::getDepartment).map(Collection::stream).orElseGet(Stream::empty)
				.map(Department::getSunDepartments).map(Collection::stream)
				.forEach(subDepartment -> System.out.println(subDepartment));

		Optional.of(employee).map(Employee::getDepartment).map(Collection::stream).orElseGet(Stream::empty)
				.map(Department::getSunDepartments).flatMap(Collection::stream).filter(Objects::nonNull)
				.forEach(subDepartment -> System.out.println(subDepartment.getId()));
	}

	private static List<SubDepartMent> buildObject() {
		List<SubDepartMent> subDepartments = new ArrayList<>();
		subDepartments.add(null);
		SubDepartMent subDepartMent = new SubDepartMent();
		subDepartMent.setId(1);
		subDepartments.add(subDepartMent);
		return subDepartments;
	}
}
