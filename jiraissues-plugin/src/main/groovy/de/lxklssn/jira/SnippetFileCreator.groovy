package de.lxklssn.jira

class SnippetFileCreator {

    private static final String UTF8 = 'UTF8'

    private String filePath
    private String fileName

    SnippetFileCreator(String filePath, String fileName) {
        this.filePath = filePath
        this.fileName = fileName
    }

    void createIssueSnippet(String version, IssueChapter issueChapter) {

        def path = filePath + '/' + version
        def folder = new File(path)

        if (!folder.exists()) {
            folder.mkdirs()
        }

        File file = new File(path + "/" + fileName)
        String fileContent = ''

        issueChapter.getIssues().each { issue ->
            fileContent = fileContent.concat(issue).concat('\n')
        }
        file.write(fileContent, UTF8)
    }

}
