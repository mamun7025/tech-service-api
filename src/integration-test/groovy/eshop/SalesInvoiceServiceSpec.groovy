package eshop

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import sales.SalesOrder
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SalesInvoiceServiceSpec extends Specification {

    SalesInvoiceService salesInvoiceService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SalesOrder(...).save(flush: true, failOnError: true)
        //new SalesOrder(...).save(flush: true, failOnError: true)
        //SalesOrder salesInvoice = new SalesOrder(...).save(flush: true, failOnError: true)
        //new SalesOrder(...).save(flush: true, failOnError: true)
        //new SalesOrder(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //salesInvoice.id
    }

    void "test get"() {
        setupData()

        expect:
        salesInvoiceService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SalesOrder> salesInvoiceList = salesInvoiceService.list(max: 2, offset: 2)

        then:
        salesInvoiceList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        salesInvoiceService.count() == 5
    }

    void "test delete"() {
        Long salesInvoiceId = setupData()

        expect:
        salesInvoiceService.count() == 5

        when:
        salesInvoiceService.delete(salesInvoiceId)
        sessionFactory.currentSession.flush()

        then:
        salesInvoiceService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SalesOrder salesInvoice = new SalesOrder()
        salesInvoiceService.save(salesInvoice)

        then:
        salesInvoice.id != null
    }
}
