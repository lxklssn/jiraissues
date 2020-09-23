package de.lxklssn.jira


import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

class JiraIssuesPluginExtension {

    Property<String> filePath

    Property<String> jiraBaseUrl
    Property<String> jiraUsername
    Property<String> jiraPassword

    ListProperty<String> fixVersions

    JiraIssuesPluginExtension(Project project) {
        filePath = project.objects.property(String).value("build/jira-issues")

        jiraBaseUrl = project.objects.property(String)
        jiraUsername = project.objects.property(String)
        jiraPassword = project.objects.property(String)

        fixVersions = project.objects.listProperty(String)
    }
}
