databaseChangeLog = {

    changeSet(author: "Walton (generated)", id: "1598290769477-1") {
        dropForeignKeyConstraint(baseTableName: "service_orders", constraintName: "FK5ttg6ovkthgnehkb59f0t7udb")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-2") {
        dropForeignKeyConstraint(baseTableName: "service_payment", constraintName: "FK9mk11danw31qpo2yc28mqlp76")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-3") {
        dropForeignKeyConstraint(baseTableName: "service_orders", constraintName: "FKad6v58512nn5imlsiip09cvus")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-4") {
        dropForeignKeyConstraint(baseTableName: "sales_invoice_line", constraintName: "FKe2qct6p2ydr2cr7is1p83lgee")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-5") {
        dropForeignKeyConstraint(baseTableName: "service_items", constraintName: "FKf10beow6weqmqtypb2kg54k8c")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-6") {
        dropForeignKeyConstraint(baseTableName: "service_center", constraintName: "FKhqgeaif1xv5rthwh7w4i4b75k")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-7") {
        dropForeignKeyConstraint(baseTableName: "service_center", constraintName: "FKp1hp9v67pwuo87mneo9pn7xe8")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-8") {
        dropForeignKeyConstraint(baseTableName: "service_items", constraintName: "FKqmis1e8jd7m7jb2gr4dudfpde")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-9") {
        dropForeignKeyConstraint(baseTableName: "sales_invoice", constraintName: "FKtrw035b4j2t701ufeuna4iuk9")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-10") {
        dropTable(tableName: "int_integration_data_source")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-11") {
        dropTable(tableName: "product")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-12") {
        dropTable(tableName: "sales_invoice")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-13") {
        dropTable(tableName: "sales_invoice_line")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-14") {
        dropTable(tableName: "service_payment")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-15") {
        dropColumn(columnName: "SERVICE_ICON_NAME", tableName: "service_items")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-16") {
        dropColumn(columnName: "SERVICE_ICON_PATH", tableName: "service_items")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-17") {
        dropColumn(columnName: "creation_user_id", tableName: "service_center")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-18") {
        dropColumn(columnName: "creation_user_id", tableName: "service_items")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-19") {
        dropColumn(columnName: "creation_user_id", tableName: "service_orders")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-20") {
        dropColumn(columnName: "last_update_user_id", tableName: "service_center")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-21") {
        dropColumn(columnName: "last_update_user_id", tableName: "service_items")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-22") {
        dropColumn(columnName: "last_update_user_id", tableName: "service_orders")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-23") {
        dropNotNullConstraint(columnDataType: "datetime", columnName: "approval_date", tableName: "auth_user_details")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-24") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "expertise_area", tableName: "auth_user_details")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-25") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "expertise_keywords", tableName: "auth_user_details")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-26") {
        dropNotNullConstraint(columnDataType: "boolean", columnName: "is_approved", tableName: "auth_user_details")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-27") {
        dropNotNullConstraint(columnDataType: "datetime", columnName: "registration_date", tableName: "auth_user_details")
    }

    changeSet(author: "Walton (generated)", id: "1598290769477-28") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "user_type", tableName: "auth_user_details")
    }
}
