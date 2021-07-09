package service

class TechnicianRate {

    Long id

    ServiceItems serviceItems
    String brand
    Double rating
    Double price


    static mapping = {
        table 'technician_rate'
        version false
        id generator: 'increment'
        id column: 'ID'

        serviceItems column: 'SERVICE_PRODUCT_ID'
        brand column: 'BRAND'
        rating column: 'RATING'
        price column: 'PRICE'
    }

    static constraints = {
        serviceItems nullable: false
        brand nullable: true
        rating nullable: true
        price nullable: true

    }
}
