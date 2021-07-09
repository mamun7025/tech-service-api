package sales

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SalesOrderServiceSpec extends Specification {

    SalesOrderService salesOrderService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SalesOrder(...).save(flush: true, failOnError: true)
        //new SalesOrder(...).save(flush: true, failOnError: true)
        //SalesOrder salesOrder = new SalesOrder(...).save(flush: true, failOnError: true)
        //new SalesOrder(...).save(flush: true, failOnError: true)
        //new SalesOrder(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //salesOrder.id
    }

    void "test get"() {
        setupData()

        expect:
        salesOrderService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SalesOrder> salesOrderList = salesOrderService.list(max: 2, offset: 2)

        then:
        salesOrderList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        salesOrderService.count() == 5
    }

    void "test delete"() {
        Long salesOrderId = setupData()

        expect:
        salesOrderService.count() == 5

        when:
        salesOrderService.delete(salesOrderId)
        sessionFactory.currentSession.flush()

        then:
        salesOrderService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SalesOrder salesOrder = new SalesOrder()
        salesOrderService.save(salesOrder)

        then:
        salesOrder.id != null
    }
}
