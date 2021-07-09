package service

import auth.User

class ServiceItems {

    Long id // service item id // DB ID AI

    String itemCode // service item code  // Like LPR0007
    String itemDescription
    String itemName

    String iconName
    String iconImgPath

    Double regularPrice = 0.00
    Double minPrice = 0.00

    Integer sequence // view order
    Boolean active = true

    Double comChargePct
    Double techRatingPrice = 0.00

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'service_items'
        version false
        id generator: 'increment'
        id column: 'ID'

        itemCode column: 'ITEM_CODE'
        itemName column: 'ITEM_NAME'
        itemDescription column: 'ITEM_DESCRIPTION'

        iconName column: 'ICON_NAME'
        iconImgPath column: 'ICON_IMG_PATH'

        active column: 'ACTIVE'

        comChargePct column: 'COM_CHARGE_PCT'
        techRatingPrice column: 'TECH_RATING_PRICE'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        itemCode nullable: true
        itemName nullable: false
        itemDescription nullable: false

        iconName nullable: true
        iconImgPath nullable: true

        regularPrice nullable: true
        minPrice nullable: true

        sequence  nullable: true
        active nullable: true
        comChargePct nullable: true
        techRatingPrice nullable: true

        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }


}
