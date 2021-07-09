package sales

import grails.gorm.services.Service

@Service(SalesOrder)
interface SalesOrderService {

    SalesOrder get(Serializable id)

    List<SalesOrder> list(Map args)

    Long count()

    void delete(Serializable id)

    SalesOrder save(SalesOrder salesOrder)

}