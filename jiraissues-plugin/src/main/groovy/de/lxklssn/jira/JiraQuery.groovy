package de.lxklssn.jira

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property

class JiraQuery {

    String name
    Property<String> fileName
    Property<String> jql

    JiraQuery(String name, ObjectFactory objectFactory) {
        this.name = name
        this.fileName = objectFactory.property(String).value("issues.adoc")
        this.jql = objectFactory.property(String)
    }

    Property<String> getFileName() {
        return fileName
    }

    void setFileName(String fileName) {
        this.fileName.set(fileName)
    }

    Property<String> getJql() {
        return jql
    }

    void setJql(String jql) {
        this.jql.set(jql)
    }
}
