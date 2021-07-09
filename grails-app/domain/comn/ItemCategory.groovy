package comn

class ItemCategory {
    Long id

    Organization organization
    String name
    String keyword
    String description
    Boolean isActive

    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'item_category'
        version false
        id generator: 'increment'
        id column: 'ID'

        organization column: 'ORGANIZATION_ID'
        name column: 'NAME'
        keyword column: 'KEYWORD'
        description column: 'DESCRIPTION'
        isActive column: 'IS_ACTIVE'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        organization nullable: true
        name nullable: true
        keyword nullable: true
        description nullable: true
        isActive nullable: true

        // System log fields
        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }
}
