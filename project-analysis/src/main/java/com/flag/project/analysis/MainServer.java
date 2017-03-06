package com.flag.project.analysis;

import com.flag.project.analysis.server.AnalysisServer;

/**
 * analysis class server
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 10:37
 */
public class MainServer {
    public static void main(String[] args) {
        int port = 8087;
        new AnalysisServer(port).bind();
    }
}
