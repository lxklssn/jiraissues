package de.lxklssn.jira

import org.apache.http.conn.ssl.SSLSocketFactory
import org.apache.http.params.HttpParams

import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException

class TLSSocketFactory extends SSLSocketFactory {

    private javax.net.ssl.SSLSocketFactory internalSSLSocketFactory

    public TLSSocketFactory(SSLContext context) throws KeyManagementException, NoSuchAlgorithmException {
        super(context)
        internalSSLSocketFactory = context.getSocketFactory()
    }

    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return enableTLSOnSocket(internalSSLSocketFactory.createSocket(s, host, port, autoClose))
    }

    @Override
    public Socket createSocket(HttpParams httpParams) throws IOException, UnknownHostException {
        return enableTLSOnSocket(internalSSLSocketFactory.createSocket())
    }

    private Socket enableTLSOnSocket(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            String[] protocols = ["TLSv1.2", "TLSv1.1"]
            ((SSLSocket) socket).setEnabledProtocols(protocols)
        }
        return socket
    }
}
