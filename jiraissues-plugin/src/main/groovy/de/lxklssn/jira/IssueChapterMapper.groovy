package de.lxklssn.jira

class IssueChapterMapper {

    final String baseUrl;

    IssueChapterMapper(String baseUrl) {
        this.baseUrl = baseUrl
    }

    IssueChapter map(List issues) {
        IssueChapter issueChapter = new IssueChapter();
        List issueEntries = new ArrayList();
        issues.each {
            issueEntries.add(createIssueEntry(it.key, it.fields.summary));
        }
        issueChapter.setIssues(issueEntries);

        return issueChapter;
    }

    String createIssueEntry(String ticketKey, String ticketSummary) {
        String ticketUrl = baseUrl.concat(ticketKey)
        return "* ".concat(ticketUrl).concat("[*").concat(ticketKey).concat("*] - ").concat(ticketSummary)
    }
}
