databaseChangeLog = {

    changeSet(author: "Walton (generated)", id: "1597991913001-1") {
        createTable(tableName: "sys_integration_data_src") {
            column(name: "ID", type: "NUMBER(19, 0)") {
                constraints(nullable: "false")
            }

            column(name: "EXP_ENVIRONMENT", type: "VARCHAR2(255 CHAR)")

            column(name: "SRC_ENVIRONMENT", type: "VARCHAR2(255 CHAR)")

            column(name: "DATA_SOURCE_MAP_QUERY_STRING", type: "LONG")

            column(name: "INTEGRATION_REFERENCE_ID", type: "VARCHAR2(255 CHAR)")

            column(name: "LAST_UPDATE_USER", type: "VARCHAR2(255 CHAR)")

            column(name: "HARDCODE_PARAMETERS", type: "VARCHAR2(255 CHAR)")

            column(name: "DRIVER", type: "VARCHAR2(255 CHAR)")

            column(name: "INTEGRATION_NAME", type: "VARCHAR2(255 CHAR)")

            column(name: "PASSWORD", type: "VARCHAR2(255 CHAR)")

            column(name: "USER", type: "VARCHAR2(255 CHAR)")

            column(name: "CREATION_DATETIME", type: "TIMESTAMP")

            column(name: "DB_CLIENT", type: "VARCHAR2(255 CHAR)")

            column(name: "HOST_URL", type: "VARCHAR2(255 CHAR)")

            column(name: "CREATION_USER", type: "VARCHAR2(255 CHAR)")

            column(name: "LAST_UPDATE_DATETIME", type: "TIMESTAMP")
        }
    }

    changeSet(author: "Walton (generated)", id: "1597991913001-2") {
        createIndex(indexName: "IX_sys_integration_data_srcPK", tableName: "sys_integration_data_src", unique: "true") {
            column(name: "ID")
        }

        addPrimaryKey(columnNames: "ID", constraintName: "sys_integration_data_srcPK", forIndexName: "IX_sys_integration_data_srcPK", tableName: "sys_integration_data_src")
    }

    changeSet(author: "Walton (generated)", id: "1597991913001-3") {
        dropTable(tableName: "SYS_INTEGRATION_DATA_SOURCE")
    }

}
