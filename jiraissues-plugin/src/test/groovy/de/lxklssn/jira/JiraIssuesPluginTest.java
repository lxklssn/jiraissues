package de.lxklssn.jira;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JiraIssuesPluginTest {

    private Project createProject() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("de.lxklssn.jiraissues");
        return project;
    }

    @Test
    public void instantiateTask() {
        Project project = createProject();

        assertThat(project.getTasks().getByName("getIssues")).isInstanceOf(GetJiraIssuesTask.class);
    }

}