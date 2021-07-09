package marketing

import auth.User
import comn.Organization

class OfferPromos {

    Organization organization
    User promoUser
    String title
    String type
    Double discountAmount
    String promoCode
    Boolean isPromoCode
    Date expiryDate
    String conditions
    Integer leftPromo
    Boolean isExpired

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'offer_promos'
        version false
        id generator: 'increment'
        id column: 'ID'

        organization column: 'ORGANIZATION_ID'
        promoUser column: 'PROMO_USER_ID'
        title column: 'TITLE'
        type column: 'TYPE'
        discountAmount column: 'DISCOUNT_AMOUNT'
        promoCode column: 'PROMO_CODE'
        isPromoCode column: 'IS_PROMO_CODE'
        expiryDate column: 'EXPIRY_DATE'
        conditions column: 'CONDITIONS'
        leftPromo column: 'LEFT_PROMO'
        isExpired column: 'IS_EXPIRED'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }


    static constraints = {
        organization nullable: true
        promoUser nullable: true
        title nullable: true
        type nullable: true
        discountAmount nullable: true
        promoCode nullable: true
        isPromoCode nullable: true
        expiryDate nullable: true
        conditions nullable: true
        leftPromo nullable: true
        isExpired nullable: true
        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }


}
