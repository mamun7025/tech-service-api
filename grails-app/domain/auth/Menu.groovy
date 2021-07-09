package auth

class Menu {

    String title
    String description
    String urlPath
    String menuClass
    String menuType = "MAIN_MENU"
    Menu parentMenu
    Boolean isExternal
    Boolean isOpenNewTab
    Boolean isActive
    Integer sortOrder
    String createdBy
    Date createdDate
    String modifiedBy
    Date modifiedDate

    static mapping = {
        table 'auth_menus'
        id column: 'id', params: [sequence: 'auth_menus_seq'] // only for oracle db
        version false
        parentMenu column: 'parent_id'

    }

    static constraints = {
        title blank: false, nullable: false
        description nullable: true, size: 1..250
        urlPath nullable: false, blank: false
        menuClass nullable: true, size: 1..100
        menuType nullable: true, size: 1..100
        parentMenu nullable: true
        isExternal nullable: true
        isOpenNewTab nullable: true
        isActive nullable: false
        sortOrder nullable: true
        createdDate nullable: false
        createdBy nullable: false
        modifiedDate nullable: true
        modifiedBy nullable: true
    }

    String toString() {
        return title
    }


}
