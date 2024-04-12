package com.obarra.pocjdk8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringTest {

    private static final String AUDIT_LOG_PATH = "%s/%s/log";

    private static final String AUDIT_LOG_FILENAME = AUDIT_LOG_PATH + "/audit.log";

    /**
     * The %%d is escaped so that results in a '%d' to define the rollover specifier (daily) and the timezone specifier
     * (UTC). The '.gz' suffix automatically enables compression on rolled logs. See the logback docs on
     * <a href="https://logback.qos.ch/manual/appenders.html#TimeBasedRollingPolicy">TimeBasedRollingPolicy</a>
     */
    private static final String AUDIT_ARCHIVE_LOG_FILENAME = AUDIT_LOG_PATH + "/audit-%%d{yyyy-MM-dd, UTC}.log.gz";

    @Test
    void stringFormat() {
        String auditLogBasePath = "./sonatype-work/clm-cluster";
        String discriminatingValue = "tt1";
        String result = String.format(AUDIT_ARCHIVE_LOG_FILENAME, auditLogBasePath, discriminatingValue);

        Assertions.assertEquals("./sonatype-work/clm-cluster/tt1/log/audit-%d{yyyy-MM-dd, UTC}.log.gz", result);
    }
}