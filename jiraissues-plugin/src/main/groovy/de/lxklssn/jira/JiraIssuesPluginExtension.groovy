package de.lxklssn.jira

import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

class JiraIssuesPluginExtension {

    Property<String> jql;
    ListProperty<String> jiraVersions;
    Property<String> jiraUsername;
    Property<String> jiraPassword;
    Property<String> filePath;
    Property<String> jiraBaseUrl;
    Property<String> fileName;

    JiraIssuesPluginExtension(Project project) {
        jiraUsername = project.objects.property(String)
        jiraPassword = project.objects.property(String)
        filePath = project.objects.property(String)
        jiraBaseUrl = project.objects.property(String)
        jiraVersions = project.objects.listProperty(String)
        jql = project.objects.property(String)
        fileName = project.objects.property(String)
    }
}
