package de.lxklssn.jira

import org.gradle.api.*

class JiraIssuesPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('jiraIssues', JiraIssuesPluginExtension, project)

        NamedDomainObjectContainer<JiraQuery> queries = project.container(JiraQuery, new NamedDomainObjectFactory<JiraQuery>() {
            JiraQuery create(String name) {
                return new JiraQuery(name, project.getObjects());
            }
        })

        project.extensions.add('jiraQueries', queries)

        queries.all(new Action<JiraQuery>() {

            @Override
            void execute(JiraQuery query) {

                def taskName = "getChangelogFor" + query.name.capitalize()

                project.task(taskName, type: GetJiraIssuesTask) {
                    description = "Fetch change log for ${query.name} query"
                    group = 'documentation'

                    getJiraBaseUrl().set(extension.jiraBaseUrl)
                    getJiraUsername().set(extension.jiraUsername)
                    getJiraPassword().set(extension.jiraPassword)

                    getJiraVersions().set(extension.jiraVersions)
                    getFilePath().set(extension.filePath)

                    getFileName().set(query.fileName)
                    getJql().set(query.jql)
                }
            }
        })
    }

}
