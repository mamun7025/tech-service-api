package apps

import auth.User
import comn.Organization

class Notification {

    Long id

    Organization organization
    String title
    String message

    User user
    String userType
    String userName
    Integer status = 0
    Boolean isPromotional
    String orderCode
    Date timestamp

    static mapping = {
        table 'app_notification'
        version false
//        id generator: 'increment'
//        id column: 'ID'
    }

    static constraints = {
        organization nullable: true
        title nullable: true
        message nullable: true
        user nullable: true
        userType nullable: true
        userName nullable: true
        orderCode nullable: true
        status nullable: true
        isPromotional nullable: true
        timestamp nullable: true
    }


}
