package jira

enum JiraStatus {
    OPEN("1"), IN_PROGRESS("3"), DEV_TEST("10000"), AUTHOR_TEST("10001"),
    CLOSED("6"), READY_FOR_DEPLOYMENT("10002");

    JiraStatus(String id) { this.id = id }

    private final String id

    String getId() {
        return id
    }
}