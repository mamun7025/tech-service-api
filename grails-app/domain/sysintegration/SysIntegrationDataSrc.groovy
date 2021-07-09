package sysintegration

class SysIntegrationDataSrc {

    Long id

    String integrationName // Global Unique // EBS DO Pull like that
    String integrationReferenceId
    String srcEnvironment // source system / company
    String expEnvironment

    String hostUrl      // "dbc:oracle:thin:@192.168.100.190:1531:test"
    String dbUser       // "APPS" // user is reserved for oracle
    String dbPassword   // "APPS"
    String driver       // "oracle.jdbc.OracleDriver"

    String dbClient     // "ORACLE" // MYSQL, ORACLE IBMDB2

    String dataSourceMapQueryString // with mapping fields using 'AS'
    String hardcodeParameters

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser


    static mapping = {
        table 'sys_integration_data_src'
        version false
        id generator: 'increment'
        id column: 'ID'

        srcEnvironment column: 'SRC_ENVIRONMENT'
        expEnvironment column: 'EXP_ENVIRONMENT'

        hostUrl column: 'HOST_URL'
        dbUser column: 'DB_USER'
        dbPassword column: 'DB_PASSWORD'
        driver column: 'DRIVER'
        dbClient column: 'DB_CLIENT'

        integrationName column: 'INTEGRATION_NAME'
        integrationReferenceId column: 'INTEGRATION_REFERENCE_ID'

        dataSourceMapQueryString column: 'DATA_SOURCE_MAP_QUERY_STRING'
        hardcodeParameters column: 'HARDCODE_PARAMETERS'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {

        srcEnvironment nullable: true
        expEnvironment nullable: true

        hostUrl nullable: true
        dbUser nullable: true
        dbPassword nullable: true
        driver nullable: true
        dbClient nullable: true

        integrationName nullable: true
        integrationReferenceId nullable: true

        dataSourceMapQueryString size: 0..5000, nullable: true
        hardcodeParameters nullable: true

        creationDateTime(nullable: true)
        creationUser(nullable: true)
        lastUpdateDateTime(nullable: true)
        lastUpdateUser(nullable: true)
    }


    String toString() {
        return "${id}"
    }


}
