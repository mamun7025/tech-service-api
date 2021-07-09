package service

import grails.gorm.services.Service
import service.ServiceOrders

@Service(ServiceOrders)
interface ServiceOrdersService {

    ServiceOrders get(Serializable id)

    List<ServiceOrders> list(Map args)

    Long count()

    void delete(Serializable id)

    ServiceOrders save(ServiceOrders serviceOrders)

}