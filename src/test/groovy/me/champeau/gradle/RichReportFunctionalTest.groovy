package me.champeau.gradle

import org.gradle.testkit.runner.TaskOutcome
import spock.lang.Unroll

class RichReportFunctionalTest extends BaseFunctionalTest {
    String testProject = 'rich-report'

    @Unroll("can generate rich report with #type rules")
    def "can generate rich report with rules"() {
        when:
        def result = run "japicmp${type.capitalize()}"

        then:
        result.task(":japicmp${type.capitalize()}").outcome == TaskOutcome.SUCCESS
        def report = getReport('rich', 'html').text
        report =~ '<a class=\'navbar-brand\' href=\'#\'>Binary compatibility report</a>'
        report =~ 'A test of rich report'
        report =~ 'This class is deprecated'

        where:
        type << ['generic', 'status', 'change']
    }

}
