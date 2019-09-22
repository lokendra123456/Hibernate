package com.learning.java8plus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertUpperCase {
	public static void main(String[] args) {

		List<String> names = Arrays.asList("lokey", "karan", "lalita");

		List<String> nameWithUpperCar = names.stream().map(String::toUpperCase).collect(Collectors.toList());

		System.out.println(nameWithUpperCar);
	}
}
