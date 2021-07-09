package service

import grails.gorm.services.Service
import service.ServiceItems

@Service(ServiceItems)
interface ServiceItemsService {

    ServiceItems get(Serializable id)

    List<ServiceItems> list(Map args)

    Long count()

    void delete(Serializable id)

    ServiceItems save(ServiceItems serviceItems)

}