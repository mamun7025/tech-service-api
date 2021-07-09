package comn

import sales.SalesOrder
import service.ServiceOrders

class Payment {

    Long id

    Organization organization
    ServiceOrders serviceOrders
    String serviceOrderCode

    SalesOrder salesOrder

    String paymentUser
    String paymentMethod
    Double paymentAmount

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser


    static mapping = {
        table 'payments'
        version false
        id generator: 'increment'
        id column: 'ID'

        organization column: 'ORGANIZATION_ID'
        serviceOrders column: 'SERVICE_ORDERS_ID'
        serviceOrderCode column: 'SERVICE_ORDERS_CODE'

        salesOrder column: 'SALES_ORDER_ID'

        paymentUser column: 'PAYMENT_USER'
        paymentMethod column: 'PAYMENT_METHOD'
        paymentAmount column: 'PAYMENT_AMOUNT'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        organization nullable: true
        serviceOrders nullable: true
        serviceOrderCode nullable: true

        salesOrder nullable: true

        paymentUser nullable: true
        paymentMethod nullable: true
        paymentAmount nullable: true

        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }

}
