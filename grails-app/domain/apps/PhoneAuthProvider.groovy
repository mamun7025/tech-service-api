package apps

class PhoneAuthProvider {

    Long id
    String phoneNumber
    String tokenCode
    Integer duration // in second
    Date timestamp

    static mapping = {
        table 'phone_auth_provider'
        version false
        id generator: 'increment'
        id column: 'ID'
    }

    static constraints = {
        tokenCode nullable: true
        phoneNumber nullable: true
        duration nullable: true
        timestamp nullable: true
    }


}
