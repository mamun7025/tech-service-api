package user

import auth.User
import comn.Organization

class UserFeedbacks {

    Long id

    Organization organization
    User feedbackUser
    User toUser

    Integer appRating
    String userComment

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'user_feedbacks'
        version false
        id generator: 'increment'
        id column: 'ID'

        organization column: 'ORGANIZATION_ID'
        feedbackUser column: 'FEEDBACK_USER_ID'
        toUser column: 'TO_USER_ID'
        appRating column: 'APP_RATING'
        userComment column: 'USER_COMMENT'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        organization nullable: true
        feedbackUser nullable: true
        toUser nullable: true

        appRating nullable: true
        userComment nullable: true

        // System log fields
        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }


}
