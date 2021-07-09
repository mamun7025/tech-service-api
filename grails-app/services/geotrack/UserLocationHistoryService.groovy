package geotrack

import grails.gorm.services.Service

@Service(UserLocationHistory)
interface UserLocationHistoryService {

    UserLocationHistory get(Serializable id)

    List<UserLocationHistory> list(Map args)

    Long count()

    void delete(Serializable id)

    UserLocationHistory save(UserLocationHistory userLocationHistory)

}