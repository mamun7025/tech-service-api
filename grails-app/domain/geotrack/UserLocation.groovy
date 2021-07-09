package geotrack

import auth.User

class UserLocation {

    Long id

    User user

    Double latitude
    Double longitude
    Double altitude
    Double accuracy
    Double altitudeAccuracy
    Double heading
    Double speed

    long timestamp

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'user_location'
        // table 'gtk_user_location'
        version false
        id generator: 'increment'
        id column: 'ID'

        user column: 'USER_ID'
        latitude column: 'LATITUDE'
        longitude column: 'LONGITUDE'
        altitude column: 'ALTITUDE'
        accuracy column: 'ACCURACY'
        altitudeAccuracy column: 'ALTITUDE_ACCURACY'
        heading column: 'HEADING'
        speed column: 'SPEED'
        timestamp column: 'TIMESTAMP'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        user nullable: false, unique: true, blank: false

        latitude(nullable:true)
        longitude(nullable:true)
        altitude(nullable:true)
        accuracy(nullable:true)
        altitudeAccuracy(nullable:true)
        heading(nullable:true)
        speed(nullable:true)

        timestamp nullable: true

        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }


    // Entity Log Management -------------------------------------------------------------------------------------------
    def beforeInsert() {
        UserLocationHistory.withNewSession {
            def logEntityInst = new UserLocationHistory(this.properties)
            logEntityInst.setRefId(this.id)
            if(!logEntityInst.save(flush: true, failOnError: true)) throw new Exception(logEntityInst.errors.allErrors.join(' \n'))
        }
    }
    def beforeUpdate() {
        UserLocationHistory.withNewSession {
            def logEntityInst = new UserLocationHistory(this.properties)
            logEntityInst.setRefId(this.id)
            if(!logEntityInst.save(flush: true, failOnError: true)) throw new Exception(logEntityInst.errors.allErrors.join(' \n'))
        }
    }
    def beforeDelete() {
        UserLocationHistory.withNewSession {
            def logEntityInst = new UserLocationHistory(this.properties)
            logEntityInst.setRefId(this.id)
            if(!logEntityInst.save(flush: true, failOnError: true)) throw new Exception(logEntityInst.errors.allErrors.join(' \n'))
        }
    }




}
