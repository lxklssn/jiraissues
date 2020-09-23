# jiraissues

Builds on CircleCI: 
[![CircleCI](https://circleci.com/gh/lxklssn/jiraissues/tree/master.svg?style=svg)](https://circleci.com/gh/lxklssn/jiraissues/tree/master)

## Usage

Include this plugin in your project. Visit this 
[plugin's page on plugins.gradle.org](https://plugins.gradle.org/plugin/de.lxklssn.jiraissues) 
for more information. 

```gradle
plugins {
  id "de.lxklssn.jiraissues" version "2.0.0"
}

jiraIssues {
    jiraBaseUrl = "http://www.jirabase.com"
    jiraUsername = "admin"
    jiraPassword = "admin"

    jiraVersions = ["Version 1.2.1", "Version 1.2.2"]
}

jiraQueries {
    changeRequests {
        fileName = "change-requests.adoc"
        jql = "project in ('MY_PROJECT_KEY') and status in (CLOSED) and type = ChangeRequest"
    }
    bugs {
        fileName = "bugs.adoc"
        jql = "project in ('MY_PROJECT_KEY') and status in (CLOSED) and type = Bug"
    }
}
```

Each query configuration will create a task namend `getChangelogFor<QueryName>`.

## Contributing

Feel free to open issues for new ideas or bugs and submit pull requests if you want to add some features.

### Repository structure

This repository contains the plugin and a sandbox project for manual tests when editing the plugin.

#### ./jiraissues-plugin-sandbox

This is a project which uses the plugin. The plugin is included in this build in order to test changes without the need to deploy the plugin first.
Run your manual tests here. You can change the sandbox project to demonstrate new features.

```
> cd jiraissues-plugin-sandbox
> gradlew getIssues
```