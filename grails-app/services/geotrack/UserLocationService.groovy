package geotrack

import grails.gorm.services.Service

@Service(UserLocation)
interface UserLocationService {

    UserLocation get(Serializable id)

    List<UserLocation> list(Map args)

    Long count()

    void delete(Serializable id)

    UserLocation save(UserLocation userLocation)

}