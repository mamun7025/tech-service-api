databaseChangeLog = {

    changeSet(author: "Walton (generated)", id: "1599476571604-1") {
        addColumn(tableName: "service_orders") {
            column(name: "BILL_AMOUNT", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-2") {
        addColumn(tableName: "service_orders") {
            column(name: "BOOK_TIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-3") {
        addColumn(tableName: "service_orders") {
            column(name: "BRAND_NAME", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-4") {
        addColumn(tableName: "service_orders") {
            column(name: "CARD_RCV_AMOUNT", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-5") {
        addColumn(tableName: "service_orders") {
            column(name: "CASH_RCV_AMOUNT", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-6") {
        addColumn(tableName: "offer_promos") {
            column(name: "CREATION_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-7") {
        addColumn(tableName: "offer_promos") {
            column(name: "CREATION_USER", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-8") {
        addColumn(tableName: "service_orders") {
            column(name: "DELIVERY_ADDRESS", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-9") {
        addColumn(tableName: "offer_promos") {
            column(name: "LAST_UPDATE_DATETIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-10") {
        addColumn(tableName: "offer_promos") {
            column(name: "LAST_UPDATE_USER", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-11") {
        addColumn(tableName: "service_orders") {
            column(name: "PRODUCT_SERIAL", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-12") {
        addColumn(tableName: "service_orders") {
            column(name: "REF_INVOICE_NUMBER", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-13") {
        addColumn(tableName: "service_orders") {
            column(name: "SD_AMOUNT", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-14") {
        addColumn(tableName: "service_orders") {
            column(name: "SERVICE_DURATION", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-15") {
        addColumn(tableName: "service_orders") {
            column(name: "SERVICE_END_TIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-16") {
        addColumn(tableName: "service_orders") {
            column(name: "SERVICE_PARTS_SREQUIRED", type: "number(1, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-17") {
        addColumn(tableName: "service_orders") {
            column(name: "SERVICE_PARTS_SREQUIRED_ACKNLG", type: "number(1, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-18") {
        addColumn(tableName: "service_orders") {
            column(name: "SERVICE_START_TIME", type: "timestamp")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-19") {
        addColumn(tableName: "service_orders") {
            column(name: "TOTAL_RCV_AMOUNT", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-20") {
        addColumn(tableName: "service_orders") {
            column(name: "VAT_AMOUNT", type: "double precision")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-21") {
        addColumn(tableName: "service_orders") {
            column(name: "WARRANTY_PRODUCT", type: "number(1, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-22") {
        addColumn(tableName: "shop") {
            column(name: "code", type: "varchar2(255 CHAR)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-23") {
        addColumn(tableName: "shop") {
            column(name: "contact_number", type: "varchar2(255 CHAR)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-24") {
        addColumn(tableName: "phone_auth_provider") {
            column(name: "duration", type: "number(10, 0)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-25") {
        addColumn(tableName: "app_system_counter") {
            column(name: "prefix", type: "varchar2(255 CHAR)") 
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-26") {
        addColumn(tableName: "shop") {
            column(name: "shop_owner_name", type: "varchar2(255 CHAR)")
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-27") {
        addColumn(tableName: "app_system_counter") {
            column(name: "step", type: "number(10, 0)") 
        }
    }

    changeSet(author: "Walton (generated)", id: "1599476571604-28") {
        addColumn(tableName: "app_system_counter") {
            column(name: "suffix", type: "varchar2(255 CHAR)") 
        }
    }

   
}
