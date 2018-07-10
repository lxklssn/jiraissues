package jira

class SnippetFileCreator {

    private static final String UTF8 = 'UTF8'

    private static final String ISSUES_FILE_NAME = "/issues.adoc"

    private String filePath

    SnippetFileCreator(String filePath) {
        this.filePath = filePath
    }

    void createIssueSnippet(String version, IssueChapter issueChapter) {

        def path = filePath + '/' + version
        def folder = new File(path)

        if (!folder.exists()) {
            folder.mkdirs()
        }

        File file = new File(path + ISSUES_FILE_NAME)
        String fileContent = ''

        issueChapter.getIssues().each { issue ->
            fileContent = fileContent.concat(issue).concat('\n')
        }
        file.write(fileContent, UTF8)
    }

}
