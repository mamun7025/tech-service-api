package eshop

import grails.gorm.services.Service
import sales.SalesOrder

@Service(SalesOrder)
interface SalesInvoiceService {

    SalesOrder get(Serializable id)

    List<SalesOrder> list(Map args)

    Long count()

    void delete(Serializable id)

    SalesOrder save(SalesOrder salesInvoice)

}