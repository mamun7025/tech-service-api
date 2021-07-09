package service

import grails.gorm.services.Service

@Service(TechnicianRate)
interface TechnicianRateService {

    TechnicianRate get(Serializable id)

    List<TechnicianRate> list(Map args)

    Long count()

    void delete(Serializable id)

    TechnicianRate save(TechnicianRate technicianRate)

}