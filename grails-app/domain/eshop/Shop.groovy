package eshop

class Shop {

    Long id

    String code
    String shopName
    String shopCategory
    String shopOwnerName
    String contactNumber

    static mapping = {
        table 'shop'
        version false
        id generator: 'increment'
        id column: 'ID'
    }

    static constraints = {
        shopName nullable: true
        shopCategory nullable: true
        shopOwnerName nullable: true
    }


}
