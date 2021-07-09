package service

import auth.User

class FavoriteTechnician {

    Long id

    User clientUserId
    User techUserId
    Boolean favorite

    static mapping = {
        table 'favorite_technician'
        version false
        id generator: 'increment'
        id column: 'ID'

        clientUserId column: 'CLIENT_USER_ID'
        techUserId column: 'TECH_USER_ID'
        favorite column: 'FAVORITE'
    }

    static constraints = {
        clientUserId nullable: true,unique: ['techUserId']
        techUserId nullable: true
        favorite nullable: true

    }
}
