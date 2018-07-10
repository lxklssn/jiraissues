package jira

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskAction

class GetJiraIssuesTask extends DefaultTask {

    private static final String DATA = "data"
    private static final String ISSUES = "issues"

    final Property<String> jiraUsername = project.objects.property(String);
    final Property<String> jiraPassword = project.objects.property(String);
    final Property<String> filePath = project.objects.property(String);
    final Property<String> jiraBaseUrl = project.objects.property(String);
    final Property<String[]> jiraVersions = project.objects.property(String[]);
    final Property<String[]> projects = project.objects.property(String[]);

    @TaskAction
    public void get() {
        SnippetFileCreator snippetFileCreator = new SnippetFileCreator(filePath.get());
        IssueChapterMapper issueChapterMapper = new IssueChapterMapper(jiraBaseUrl.get() + "/browse");
        JiraRESTClient jiraRESTClient = new JiraRESTClient(jiraBaseUrl.get() + "/rest/api/2/" , jiraUsername.get(), jiraPassword.get(), projects.get())
        jiraVersions.get().each { jiraVersion ->
            def issuesResponse = jiraRESTClient.getIssues(jiraVersion)
            List issues = issuesResponse.properties.get(DATA).getAt(ISSUES) as List;
            snippetFileCreator.createIssueSnippet(jiraVersion, issueChapterMapper.map(issues));
        }
    }

}

