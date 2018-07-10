package de.lxklssn.jira

import org.gradle.api.Project
import org.gradle.api.provider.Property

class JiraIssuesPluginExtension {

    Property<String> jiraProjects;
    Property<String> jiraVersions;
    Property<String> jiraUsername;
    Property<String> jiraPassword;
    Property<String> filePath;
    Property<String> jiraBaseUrl;

    JiraIssuesPluginExtension(Project project) {
        jiraUsername = project.objects.property(String)
        jiraPassword = project.objects.property(String)
        filePath = project.objects.property(String)
        jiraBaseUrl = project.objects.property(String)
        jiraVersions = project.objects.property(String)
        jiraProjects = project.objects.property(String)
    }
}
