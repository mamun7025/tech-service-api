package apps

class SystemCounter {

    Long id

    String code
    String prefix
    String suffix
    Integer step
    Integer nextNumber

    static mapping = {
        table 'app_system_counter'
        version false
//        id generator: 'increment'
//        id column: 'ID'
    }

    static constraints = {
        code nullable: true
        prefix nullable: true
        suffix nullable: true
        step nullable: true
        nextNumber nullable: true
    }


}
