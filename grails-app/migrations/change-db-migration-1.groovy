databaseChangeLog = {

    changeSet(author: "Walton (generated)", id: "1589747984902-1") {
        addColumn(tableName: "user_location") {
            column(name: "ACCURACY", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-2") {
        addColumn(tableName: "user_location_history") {
            column(name: "ACCURACY", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-3") {
        addColumn(tableName: "service_items") {
            column(name: "ACTIVE", type: "number(1, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-4") {
        addColumn(tableName: "user_location") {
            column(name: "ALTITUDE", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-5") {
        addColumn(tableName: "user_location_history") {
            column(name: "ALTITUDE", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-6") {
        addColumn(tableName: "user_location") {
            column(name: "ALTITUDE_ACCURACY", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-7") {
        addColumn(tableName: "user_location_history") {
            column(name: "ALTITUDE_ACCURACY", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-8") {
        addColumn(tableName: "user_feedbacks") {
            column(name: "APP_RATING", type: "number(10, 0)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-9") {
        addColumn(tableName: "user_feedbacks") {
            column(name: "COMMENT", type: "varchar2(255 CHAR)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-10") {
        addColumn(tableName: "service_items") {
            column(name: "CREATION_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-11") {
        addColumn(tableName: "service_payment") {
            column(name: "CREATION_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-12") {
        addColumn(tableName: "user_feedbacks") {
            column(name: "CREATION_DATETIME", type: "timestamp") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-13") {
        addColumn(tableName: "user_location") {
            column(name: "CREATION_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-14") {
        addColumn(tableName: "user_location_history") {
            column(name: "CREATION_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-15") {
        addColumn(tableName: "user_location") {
            column(name: "CREATION_USER", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-16") {
        addColumn(tableName: "user_location_history") {
            column(name: "CREATION_USER", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-17") {
        addColumn(tableName: "user_feedbacks") {
            column(name: "CUSTOMER_USER_ID", type: "number(19, 0)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-18") {
        addColumn(tableName: "user_location") {
            column(name: "HEADING", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-19") {
        addColumn(tableName: "user_location_history") {
            column(name: "HEADING", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-20") {
        addColumn(tableName: "service_items") {
            column(name: "ITEM_CODE", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-21") {
        addColumn(tableName: "service_items") {
            column(name: "ITEM_DESCRIPTION", type: "varchar2(255 CHAR)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-22") {
        addColumn(tableName: "service_items") {
            column(name: "ITEM_NAME", type: "varchar2(255 CHAR)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-23") {
        addColumn(tableName: "service_items") {
            column(name: "LAST_UPDATE_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-24") {
        addColumn(tableName: "service_payment") {
            column(name: "LAST_UPDATE_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-25") {
        addColumn(tableName: "user_feedbacks") {
            column(name: "LAST_UPDATE_DATETIME", type: "timestamp") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-26") {
        addColumn(tableName: "user_location") {
            column(name: "LAST_UPDATE_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-27") {
        addColumn(tableName: "user_location_history") {
            column(name: "LAST_UPDATE_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-28") {
        addColumn(tableName: "user_location") {
            column(name: "LAST_UPDATE_USER", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-29") {
        addColumn(tableName: "user_location_history") {
            column(name: "LAST_UPDATE_USER", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-30") {
        addColumn(tableName: "user_location") {
            column(name: "LATITUDE", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-31") {
        addColumn(tableName: "user_location_history") {
            column(name: "LATITUDE", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-32") {
        addColumn(tableName: "user_location") {
            column(name: "LONGITUDE", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-33") {
        addColumn(tableName: "user_location_history") {
            column(name: "LONGITUDE", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-34") {
        addColumn(tableName: "service_payment") {
            column(name: "PAYMENT_AMOUNT", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-35") {
        addColumn(tableName: "service_payment") {
            column(name: "PAYMENT_METHOD", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-36") {
        addColumn(tableName: "service_items") {
            column(name: "SERVICE_ICON_NAME", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-37") {
        addColumn(tableName: "service_items") {
            column(name: "SERVICE_ICON_PATH", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-38") {
        addColumn(tableName: "user_feedbacks") {
            column(name: "SERVICE_MAN_USER_ID", type: "number(19, 0)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-39") {
        addColumn(tableName: "service_payment") {
            column(name: "SERVICE_ORDERS_CODE", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-40") {
        addColumn(tableName: "service_payment") {
            column(name: "SERVICE_ORDERS_ID", type: "number(19, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-41") {
        addColumn(tableName: "user_location") {
            column(name: "SPEED", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-42") {
        addColumn(tableName: "user_location_history") {
            column(name: "SPEED", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-43") {
        addColumn(tableName: "user_location") {
            column(name: "TIMESTAMP", type: "number(19, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-44") {
        addColumn(tableName: "user_location_history") {
            column(name: "TIMESTAMP", type: "number(19, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-45") {
        addColumn(tableName: "user_location") {
            column(name: "USER_ID", type: "number(19, 0)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-46") {
        addColumn(tableName: "user_location_history") {
            column(name: "USER_ID", type: "number(19, 0)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-47") {
        addUniqueConstraint(columnNames: "USER_ID", constraintName: "UC_USER_LOCATIONUSER_ID_COL", tableName: "user_location")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-48") {
        addForeignKeyConstraint(baseColumnNames: "SERVICE_ORDERS_ID", baseTableName: "service_payment", constraintName: "FK9mk11danw31qpo2yc28mqlp76", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "service_orders")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-49") {
        addForeignKeyConstraint(baseColumnNames: "SERVICE_MAN_USER_ID", baseTableName: "user_feedbacks", constraintName: "FKa1ufo9ca7xrn0o0mym8pt4c0s", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "auth_user")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-50") {
        addForeignKeyConstraint(baseColumnNames: "CUSTOMER_USER_ID", baseTableName: "user_feedbacks", constraintName: "FKeilorcp3097wjk9ci93cpdana", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "auth_user")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-51") {
        addForeignKeyConstraint(baseColumnNames: "USER_ID", baseTableName: "user_location_history", constraintName: "FKj4ivp469wjjfbe0tbs8aoikow", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "auth_user")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-52") {
        addForeignKeyConstraint(baseColumnNames: "USER_ID", baseTableName: "user_location", constraintName: "FKj4kbkryi6s1r3o1s7bleruk1w", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "auth_user")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-53") {
        dropColumn(columnName: "CREATION_DATE_TIME", tableName: "SERVICE_ITEMS")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-54") {
        dropColumn(columnName: "CREATION_DATE_TIME", tableName: "SERVICE_PAYMENT")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-55") {
        dropColumn(columnName: "CREATION_DATE_TIME", tableName: "USER_FEEDBACKS")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-56") {
        dropColumn(columnName: "LAST_UPDATE_DATE_TIME", tableName: "SERVICE_ITEMS")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-57") {
        dropColumn(columnName: "LAST_UPDATE_DATE_TIME", tableName: "SERVICE_PAYMENT")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-58") {
        dropColumn(columnName: "LAST_UPDATE_DATE_TIME", tableName: "USER_FEEDBACKS")
    }


    changeSet(author: "Walton (generated)", id: "1589747984902-60") {
        dropNotNullConstraint(columnDataType: "varchar(255 CHAR)", columnName: "CREATION_USER", tableName: "service_items")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-61") {
        dropNotNullConstraint(columnDataType: "varchar(255 CHAR)", columnName: "CREATION_USER", tableName: "service_payment")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-62") {
        dropNotNullConstraint(columnDataType: "varchar(255 CHAR)", columnName: "LAST_UPDATE_USER", tableName: "service_items")
    }

    changeSet(author: "Walton (generated)", id: "1589747984902-63") {
        dropNotNullConstraint(columnDataType: "varchar(255 CHAR)", columnName: "LAST_UPDATE_USER", tableName: "service_payment")
    }
}
