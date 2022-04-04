package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "pretty", "json:target/cucumber-report/report.json"},
        features = "src/test/resources/features",
        glue = "tests.steps",
        tags = {"@Run"}
)
public class CucumberRunner {
    // string for master branch # start
    // string for debug_code branch # start
    // string for debug_code branch # 2
    // string for debug_code branch # 4 Modify_3
    // test for test 22
    // continue testing new branch _
    // continue testing new branch april
    // continue testing new branch may
    // continue testing new branch june
    // for compare with master branch
    // continue testing bla bla bla (in master branch)
    // continue testing bla2 bla2 bla2 (in master branch 2)
    // continue all
    // selected 1
}
