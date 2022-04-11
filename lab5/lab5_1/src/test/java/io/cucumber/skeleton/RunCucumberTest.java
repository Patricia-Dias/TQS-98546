package io.cucumber.skeleton;

import org.junit.jupiter.api.TestFactory;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("io/cucumber/skeleton")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.cucumber.skeleton")
public class RunCucumberTest {
    // @TestFactory
    // public Stream<DynamicTest> runCukes(Stream<DynamicTest> scenarios) {
    //     List<DynamicTest> tests = scenarios.collect(Collectors.toList());
    //     return tests.stream();
    // }
}
