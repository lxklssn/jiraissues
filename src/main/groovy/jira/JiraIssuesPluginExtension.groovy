package jira

import org.gradle.api.Project
import org.gradle.api.provider.Property

class JiraIssuesPluginExtension {

    final Property<String> jiraUsername;
    final Property<String> jiraPassword;
    final Property<String> filePath;
    final Property<String[]> jiraVersions;
    final Property<String[]> projects;
    final Property<String> jiraBaseUrl;

    JiraIssuesPluginExtension(Project project) {
        jiraUsername = project.objects.property(String)
        jiraPassword = project.objects.property(String)
        filePath = project.objects.property(String)
        jiraBaseUrl = project.objects.property(String)
        jiraVersions = project.objects.property(String[])
        projects = project.objects.property(String[])
    }
}
