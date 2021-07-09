package comn

class Organization {

    Long id

    String code
    String name
    String category
    String ownerName
    String contactNumber
    String address
    String binNumber

    static mapping = {
        table 'organization'
        version false
        id generator: 'increment'
        id column: 'ID'
    }

    static constraints = {

    }
}
