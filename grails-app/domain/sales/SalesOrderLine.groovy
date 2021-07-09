package sales

import comn.Items

class SalesOrderLine {

    Long id

    SalesOrder salesOrder
    Integer lineNumber

    /*
    ItemType
    ItemCode --- ProductCode
    ItemLot
    ItemSerial --- ItemElement --- prodSlNo
    */
    Items item
    String itemCode
    String itemDescription
    String itemSerial

    Double quantity
    Double unitPrice
    Double amount // subTotal
    Double discount

    Double cogsUnitPrice

    // VAT attributes-------------------------------------
    Double sdPct            // == Supplementary Duty
    Double sdAmount         // == Supplementary Duty
    Double vatPct           // == Value Added Tax
    Double vatAmount        // == Value Added Tax
    Double totalTaxAmount
    //-------------------------------------------------------
    Double netTotalAmount

    String remarks

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'sales_order_line'
        version false
        id generator: 'increment'
        id column: 'ID'

        salesOrder column: 'SALES_ORDER_ID'
        lineNumber column: 'LINE_NUMBER'

        item column: 'ITEM_ID'
        itemCode column: 'ITEM_CODE'
        itemDescription column: 'ITEM_DESCRIPTION'
        itemSerial column: 'ITEM_SERIAL'

        quantity column: 'QUANTITY'
        unitPrice column: 'UNIT_PRICE'
        amount column: 'AMOUNT'
        discount column: 'DISCOUNT'

        cogsUnitPrice column: 'COGS_UNIT_PRICE'

        // VAT attributes-------------------------------------
        sdPct column: 'SD_PCT'
        sdAmount column: 'SD_AMOUNT'
        vatPct column: 'VAT_PCT'
        vatAmount column: 'VAT_AMOUNT'
        totalTaxAmount column: 'TOTAL_TAX_AMOUNT'
        //-------------------------------------------------------
        netTotalAmount column: 'NET_TOTAL_AMOUNT'

        remarks column: 'REMARKS'

        // System log fields
        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }


    static constraints = {
        salesOrder nullable: false
        lineNumber nullable: true

        item nullable: true
        itemCode nullable: true
        itemDescription nullable: true
        itemSerial nullable: true

        quantity nullable: true
        unitPrice nullable: true
        amount nullable: true
        discount nullable:true
        cogsUnitPrice nullable: true

        // VAT attributes-------------------------------------
        sdPct nullable: true
        sdAmount nullable: true
        vatPct nullable: true
        vatAmount nullable: true
        totalTaxAmount nullable: true
        //-------------------------------------------------------
        netTotalAmount nullable: true

        remarks nullable: true

        // System log fields
        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true

    }



}
