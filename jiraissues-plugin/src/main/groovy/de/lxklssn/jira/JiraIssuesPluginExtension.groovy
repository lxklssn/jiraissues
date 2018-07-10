package de.lxklssn.jira

import org.gradle.api.Project
import org.gradle.api.provider.Property

class JiraIssuesPluginExtension {

    final Property<String> jiraProjects = project.objects.property(String);
    final Property<String> jiraVersions = project.objects.property(String);
    final Property<String> jiraUsername = project.objects.property(String);
    final Property<String> jiraPassword = project.objects.property(String);
    final Property<String> filePath = project.objects.property(String);
    final Property<String> jiraBaseUrl = project.objects.property(String);

    JiraIssuesPluginExtension(Project project) {
        jiraUsername = project.objects.property(String)
        jiraPassword = project.objects.property(String)
        filePath = project.objects.property(String)
        jiraBaseUrl = project.objects.property(String)
        jiraVersions = project.objects.property(String)
        jiraProjects = project.objects.property(String)
    }
}
