package sales

import auth.User
import comn.Organization

class SalesOrder {

    Long id

    Organization organization
    String orderCode
    Date orderDate
    User orderUser

    // Others fields
    String customerName
    String contactNumber

    String paymentMethod
    String shippingAddress

    // quantity
    Double totalQuantity

    // Money
    Double subTotalAmount // SubTotal
    Double discountAmount
    Double netTotalAmount   // NetTotal

    // VAT
    Double totalSdAmount = 0.0
    Double totalVatAmount = 0.0
    Double totalCogsAmount = 0.0
    Double totalTaxAmount = 0.0

    // Grand
    Double amountAdjustment = 0.0
    Double shippingCharges = 0.0
    Double grandTotalAmount = 0.0

    // Collect
    Double cashRcvAmount
    Double cardRcvAmount
    Double dueAmount
    Double totalRcvAmount // collected amount - with bank + cash + ... others

    Boolean isConfirmed
    Boolean isDelivered


    String financialYear
    String remarks


    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static hasMany = [salesOrderLine: SalesOrderLine]

    static mapping = {
        table 'sales_order'
        version false
        id generator: 'increment'
        id column: 'ID'

        organization column: 'ORGANIZATION_ID'
        orderCode column: 'ORDER_CODE'
        orderDate column: 'ORDER_DATE'
        orderUser column: 'ORDER_USER_ID'

        // Others fields
        customerName column: 'CUSTOMER_NAME'
        contactNumber column: 'CONTACT_NUMBER'

        paymentMethod column: 'PAYMENT_METHOD'
        shippingAddress column: 'SHIPPING_ADDRESS'

        // quantity
        totalQuantity column: 'TOTAL_QUANTITY'

        // Money
        subTotalAmount column: 'SUB_TOTAL_AMOUNT'
        discountAmount column: 'DISCOUNT_AMOUNT'
        netTotalAmount column: 'NET_TOTAL_AMOUNT'

        // VAT
        totalSdAmount column: 'TOTAL_SD_AMOUNT'
        totalVatAmount column: 'TOTAL_VAT_AMOUNT'
        totalCogsAmount column: 'TOTAL_COGS_AMOUNT'
        totalTaxAmount column: 'TOTAL_TAX_AMOUNT'

        // Grand
        amountAdjustment column: 'AMOUNT_ADJUSTMENT'
        shippingCharges column: 'SHIPPING_CHARGES'
        grandTotalAmount column: 'GRAND_TOTAL_AMOUNT'

        // Collect
        cashRcvAmount column: 'CASH_RCV_AMOUNT'
        cardRcvAmount column: 'CARD_RCV_AMOUNT'
        dueAmount column: 'DUE_AMOUNT'
        totalRcvAmount column: 'TOTAL_RCV_AMOUNT'

        isConfirmed column: 'IS_CONFIRMED'
        isDelivered column: 'IS_DELIVERED'


        financialYear column: 'FINANCIAL_YEAR'
        remarks column: 'REMARKS'


        // System log fields
        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        organization nullable: true
        orderCode nullable: true
        orderDate nullable: true
        orderUser nullable: false

        // Others fields
        customerName nullable: true
        contactNumber nullable: true

        paymentMethod nullable: true
        shippingAddress nullable: true

        // quantity
        totalQuantity nullable: true

        // Money
        subTotalAmount nullable: true // SubTotal
        discountAmount nullable: true
        netTotalAmount nullable: true  // NetTotal

        // VAT
        totalSdAmount nullable: true
        totalVatAmount nullable: true
        totalCogsAmount nullable: true
        totalTaxAmount nullable: true

        // Grand
        amountAdjustment nullable: true
        shippingCharges nullable: true
        grandTotalAmount nullable: true

        // Collect
        cashRcvAmount nullable: true
        cardRcvAmount nullable: true
        dueAmount nullable: true
        totalRcvAmount nullable: true

        isConfirmed nullable: true
        isDelivered nullable: true


        financialYear nullable: true
        remarks nullable: true


        // System log fields
        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true

    }



}
