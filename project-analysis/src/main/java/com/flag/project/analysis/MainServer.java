package com.flag.project.analysis;

import com.flag.project.analysis.server.AnalysisServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * analysis class server
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 10:37
 */
public class MainServer {

    private static final Logger LOG = LogManager.getLogger(MainServer.class);

    public static void main(String[] args) {
        LOG.info("======== starting main");
        int port = 8087;
        new AnalysisServer(port).bind();
    }

}
