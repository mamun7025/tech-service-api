package service

class ProdServicePriceRateList {
    String serviceProduct
    Integer startRange
    Integer endRange
    Double servicePrice

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'prod_service_price_rate_list'
        version false
        id generator: 'increment'
        id column: 'ID'

        serviceProduct column: 'SERVICE_PRODUCT'
        startRange column: 'START_RANGE'
        endRange column: 'END_RANGE'
        servicePrice column: 'SERVICE_PRICE'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        serviceProduct nullable: true
        startRange nullable: true
        endRange nullable: true
        servicePrice nullable: true

        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }
}
