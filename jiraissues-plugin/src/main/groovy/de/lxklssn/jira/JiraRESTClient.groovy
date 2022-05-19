package de.lxklssn.jira

import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import org.apache.http.conn.scheme.Scheme
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.net.ssl.SSLContext

class JiraRESTClient extends RESTClient {

    private static Logger LOG = LoggerFactory.getLogger(JiraRESTClient)

    private static final String HTTPS = "https"
    private static final int HTTPS_PORT = 443
    private static final String TLS = "TLS"

    private static final String DATA = "data"
    private static final String ISSUES = "issues"

    private String url
    private String username
    private String password

    JiraRESTClient(String url, String username, String password) {
        super(url)
        this.url = url
        getClient().getConnectionManager().getSchemeRegistry().register(new Scheme(HTTPS, HTTPS_PORT, createTLSSocketFactory()))

        this.username = username
        this.password = password
    }

    private TLSSocketFactory createTLSSocketFactory() {
        SSLContext sslContext = SSLContext.getInstance(TLS)
        sslContext.init(null, null, null)
        return new TLSSocketFactory(sslContext)
    }

    private def get(String path, def query) {
        try {
            def basicAuthCredentials = "$username:$password".bytes.encodeBase64().toString()
            return get(path: path, contentType: "application/json", query: query, headers: [Authorization: "Basic $basicAuthCredentials"])
        } catch (HttpResponseException e) {
            if (e.response.status == 400) {
                throw new IllegalArgumentException("JIRA query failed, response data=${e.response.data}", e)
            } else {
                throw new IOException("JIRA connection failed, got HTTP status ${e.response.status}, response data=${e.response.data}", e)
            }
        }
    }

    private List search(String jql) {
        return searchPaginated(jql, new ArrayList(), 0, 0)
    }

    private List searchPaginated(String jql, List allIssues, int startAtIssue, int totalResultsSoFar) {
        def query = [:]
        query['jql'] = jql

        query['startAt'] = startAtIssue
        query['maxResults'] = 50

        def issuesResponse = get("search", query)
        def responseData = issuesResponse.getProperties().get(DATA)

        def newIssues = responseData.getAt(ISSUES) as List

        def maxResults = responseData.getAt("maxResults")
        def totalResults = responseData.getAt("total")

        totalResultsSoFar += newIssues.size()
        allIssues.addAll(newIssues)

        if (totalResultsSoFar < totalResults) {
            startAtIssue += maxResults
            searchPaginated(jql, allIssues, startAtIssue, totalResultsSoFar)
        }

        return allIssues
    }

    List getIssues(String jiraVersion, String jql) {
        jql += " AND fixVersion in ('${jiraVersion}')"
        LOG.debug("Querying JIRA server '{}' using JQL '{}'", this.url, jql)
        return search(jql)
    }
}
