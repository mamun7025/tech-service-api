databaseChangeLog = {

    changeSet(author: "Walton (generated)", id: "1597990998903-1") {
        createTable(tableName: "sys_integration_data_source") {
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

    changeSet(author: "Walton (generated)", id: "1597990998903-2") {
        createIndex(indexName: "IX_sys_integration_data_sourcePK", tableName: "sys_integration_data_source", unique: "true") {
            column(name: "ID")
        }

        addPrimaryKey(columnNames: "ID", constraintName: "sys_integration_data_sourcePK", forIndexName: "IX_sys_integration_data_sourcePK", tableName: "sys_integration_data_source")
    }

}
