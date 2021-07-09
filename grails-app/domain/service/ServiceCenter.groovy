package service

import auth.User

class ServiceCenter {

    Long id

    String code
    String name

    String contactPerson
    String contactPhoneNumber
    String geoLocationAddress
    String addressDetails

    Integer numberOfTechnician

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'service_center'
        version false
        id generator: 'increment'
        id column: 'ID'

        code column: 'CODE'
        name column: 'NAME'

        contactPerson column: 'CONTACT_PERSON'
        contactPhoneNumber column: 'CONTACT_PHONE_NUMBER'
        geoLocationAddress column: 'GEO_LOCATION_ADDRESS'
        addressDetails column: 'ADDRESS_DETAILS'

        numberOfTechnician column: 'NUMBER_OF_TECHNICIAN'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }


    static constraints = {
        code nullable: true
        name nullable: true

        contactPerson nullable: true
        contactPhoneNumber nullable: true
        geoLocationAddress nullable: true
        addressDetails nullable: true

        numberOfTechnician nullable: true

        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }


}
