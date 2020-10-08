package de.lxklssn.jira

import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class GetJiraIssuesTask extends DefaultTask {

    @Input
    final Property<String> jql = project.objects.property(String)
    @Input
    final ListProperty<String> fixVersions = project.objects.listProperty(String)
    @Input
    final Property<String> jiraUsername = project.objects.property(String)
    @Input
    final Property<String> jiraPassword = project.objects.property(String)
    @Input
    final Property<String> filePath = project.objects.property(String)
    @Input
    final Property<String> fileName = project.objects.property(String)
    @Input
    final Property<String> jiraBaseUrl = project.objects.property(String)

    @TaskAction
    void get() {
        SnippetFileCreator snippetFileCreator = new SnippetFileCreator(filePath.get(), fileName.get())
        IssueChapterMapper issueChapterMapper = new IssueChapterMapper(jiraBaseUrl.get() + "/browse/")
        JiraRESTClient jiraRESTClient = new JiraRESTClient(jiraBaseUrl.get() + "/rest/api/2/", jiraUsername.get(), jiraPassword.get())

        fixVersions.get().each { fixVersion ->
            List issues = jiraRESTClient.getIssues(fixVersion, jql.get())
            snippetFileCreator.createIssueSnippet(fixVersion, issueChapterMapper.map(issues))
        }
    }

}

