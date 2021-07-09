package service

import comn.Items

class ReqServiceCost {

    Long id

    ServiceOrders serviceOrders
    String costCategory // tadaCost/ partsCost/ othersCost/ serviceCost
    Items items
    String itemCode
    String itemDescription
    Double quantity
    Double costPrice
    String uom
    Integer status

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'req_service_cost'
        version false
        id generator: 'increment'
        id column: 'ID'

        serviceOrders column: 'SERVICE_ORDER_ID'
        costCategory column: 'COST_CATEGORY'
        items column: 'ITEM_ID'
        itemCode column: 'TEM_CODE'
        itemDescription column: 'ITEM_DESCRIPTION'
        quantity column: 'QUANTITY'
        costPrice column: 'COST_PRICE'
        uom column: 'UOM_CODE'
        status column: 'STATUS'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        serviceOrders nullable: false
        costCategory nullable: true
        items nullable: true
        itemCode nullable: true
        itemDescription nullable: true
        quantity nullable: true
        costPrice nullable: true
        uom nullable: true
        status nullable: true

        // System log fields
        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true

    }
}
