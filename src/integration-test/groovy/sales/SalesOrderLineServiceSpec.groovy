package sales

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SalesOrderLineServiceSpec extends Specification {

    SalesOrderLineService salesOrderLineService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SalesOrderLine(...).save(flush: true, failOnError: true)
        //new SalesOrderLine(...).save(flush: true, failOnError: true)
        //SalesOrderLine salesOrderLine = new SalesOrderLine(...).save(flush: true, failOnError: true)
        //new SalesOrderLine(...).save(flush: true, failOnError: true)
        //new SalesOrderLine(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //salesOrderLine.id
    }

    void "test get"() {
        setupData()

        expect:
        salesOrderLineService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SalesOrderLine> salesOrderLineList = salesOrderLineService.list(max: 2, offset: 2)

        then:
        salesOrderLineList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        salesOrderLineService.count() == 5
    }

    void "test delete"() {
        Long salesOrderLineId = setupData()

        expect:
        salesOrderLineService.count() == 5

        when:
        salesOrderLineService.delete(salesOrderLineId)
        sessionFactory.currentSession.flush()

        then:
        salesOrderLineService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SalesOrderLine salesOrderLine = new SalesOrderLine()
        salesOrderLineService.save(salesOrderLine)

        then:
        salesOrderLine.id != null
    }
}
