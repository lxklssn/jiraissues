package de.lxklssn.jira

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

class JiraIssuesPluginTest {

    private Project createProject() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: "de.lxklssn.jiraissues"

        project.jiraIssues {
            jiraBaseUrl = "http://www.jirabase.com"
            jiraUsername = "admin"
            jiraPassword = "admin"

            jiraVersions = ["Version 1.2.1", "Version 1.2.2"]
        }

        project.jiraQueries {
            changeRequests {
                fileName = "change-requests.adoc"
                jql = "project in ('MY_PROJECT_KEY') and status in (CLOSED) and type = ChangeRequest"
            }
            bugs {
                fileName = "bugs.adoc"
                jql = "project in ('MY_PROJECT_KEY') and status in (CLOSED) and type = Bug"
            }
        }

        return project
    }

    @Test
    void instantiateTask() {
        Project project = createProject()

        assertThat(project.getTasks().getByName("getChangelogForBugs")).isInstanceOf(GetJiraIssuesTask.class)
        assertThat(project.getTasks().getByName("getChangelogForChangeRequests")).isInstanceOf(GetJiraIssuesTask.class)
    }

}