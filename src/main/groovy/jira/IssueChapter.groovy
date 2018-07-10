package jira

class IssueChapter {

    private List<String> issues = new ArrayList<>();

    void setIssues(List<String> issues) {
        this.issues = issues
    }

    List<String> getIssues() {
        return issues
    }
}
