package jira

class JiraQueryBuilder {

    private String[] projects
    private String[] issueKeys
    private String[] issueTypes
    private String[] excludedIssueTypes
    private String[] resolution
    private JiraStatus[] status
    private String[] labels
    private String fixVersion
    private String orderBy

    JiraQueryBuilder(String... projects) {
        assert projects
        this.projects = projects
        withIssueTypes("standardIssueTypes()", "Sub-task")
        withOrderBy("key")
    }

    JiraQueryBuilder withIssueTypes(String... issueTypes) {
        this.issueTypes = issueTypes
        return this
    }

    JiraQueryBuilder withExcludedIssueTypes(String... excludedIssueTypes) {
        this.excludedIssueTypes = excludedIssueTypes
        return this
    }

    JiraQueryBuilder withResolution(String... resolution) {
        this.resolution = resolution
        return this
    }

    JiraQueryBuilder withStatus(JiraStatus... status) {
        this.status = status
        return this
    }

    JiraQueryBuilder withLabels(String... labels) {
        this.labels = labels
        return this
    }

    JiraQueryBuilder withFixVersion(String fixVersion) {
        this.fixVersion = fixVersion
        return this
    }

    JiraQueryBuilder withOrderBy(String orderBy) {
        this.orderBy = orderBy
        return this
    }

    String build() {
        String query = "project in (${projects.join(',')})"

        if (issueKeys) {
            query += " AND issuekey in (${issueKeys.join(',')})"
        }

        if (issueTypes) {
            query += " AND issuetype in (${issueTypes.join(',')})"
        }

        if (excludedIssueTypes) {
            query += " AND issuetype not in (${excludedIssueTypes.join(',')})"
        }

        if (status) {
            query += " AND status in (${status*.getId().join(',')})"
        }

        if (resolution) {
            query += " AND resolution in (${resolution.join(',')})"
        }

        if (labels) {
            query += " AND labels in (${labels.join(',')})"
        }

        if (fixVersion) {
            query += " AND fixVersion = \"${fixVersion}\""
        }

        if (orderBy) {
            query += " order by ${orderBy}"
        }

        return query
    }
}

