package apps

import grails.gorm.services.Service

@Service(PhoneAuthProvider)
interface PhoneAuthProviderService {

    PhoneAuthProvider get(Serializable id)

    List<PhoneAuthProvider> list(Map args)

    Long count()

    void delete(Serializable id)

    PhoneAuthProvider save(PhoneAuthProvider phoneAuthProvider)

}