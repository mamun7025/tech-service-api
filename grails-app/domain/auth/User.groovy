package auth

import comn.Organization
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    Organization organization
    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    // added attributes
    String phoneNumber          // as username // maximum length of 15 digits
    String firstName
    String lastName
    String displayName          // marge firstName and lastName

    String email                // [user]@[mysite].com = 64 + 255, but it should be 254
    String city                 // [Dhaka, Chattogram, Sylhet...]
    String fullAddress
    String userType             // Group Checkbox [client, technician-default technician now]
    String expertiseArea        // [Electronics, Electrical, Software, Mechanical.....]
    String expertiseKeywords    // [Laptop, Mobile, TV]
    String gender               // Optional
    Date birthDate              // Optional
    Double targetEarningPerMonth// Optional (25000, 35000, 50000... --- Inspirational )
    // securityCode ------------ 4 digit code sent to mobile and need put this input box
    Double rating
    Integer numOfRatingUser
    Boolean activeOnline = false

    Date registrationDate
    Boolean isApproved
    Date approvalDate

    String imageName
    String profilePicPath

    String deviceType
    String deviceToken

    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser


    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        organization nullable: true
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true, size: 1..64

        phoneNumber nullable: false, blank: false, unique: true, size: 9..15
        firstName nullable: true, size: 1..15
        lastName nullable: true, size: 1..15
        displayName nullable: false, size: 1..30

        email nullable: true, size: 1..254
        city nullable: true, size: 1..50
        fullAddress nullable: true, size: 0..500
        userType nullable: false, size: 1..20
        expertiseArea nullable: true
        expertiseKeywords nullable: true
        gender nullable: true, size: 4..6
        birthDate nullable: true
        targetEarningPerMonth nullable: true
        rating nullable: true
        numOfRatingUser nullable: true
        activeOnline nullable: true

        registrationDate nullable: true
        isApproved nullable: true
        approvalDate nullable: true
        imageName nullable: true
        profilePicPath nullable: true

        deviceType nullable: true
        deviceToken nullable: true

        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }

    static mapping = {
        version false
        table "auth_user"
//	    password column: '`password`'
	    password column: 'password'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }
}
