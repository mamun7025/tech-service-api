package sales

import grails.gorm.services.Service

@Service(SalesOrderLine)
interface SalesOrderLineService {

    SalesOrderLine get(Serializable id)

    List<SalesOrderLine> list(Map args)

    Long count()

    void delete(Serializable id)

    SalesOrderLine save(SalesOrderLine salesOrderLine)

}