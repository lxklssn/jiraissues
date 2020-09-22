package de.lxklssn.jira

import org.gradle.api.Plugin
import org.gradle.api.Project

class JiraIssuesPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('jiraissues', JiraIssuesPluginExtension, project)
        project.tasks.create("getIssues", GetJiraIssuesTask) {

            jiraUsername = extension.jiraUsername
            jiraPassword = extension.jiraPassword
            filePath = extension.filePath
            jiraBaseUrl = extension.jiraBaseUrl
            jql = extension.jql
        }
    }

}
