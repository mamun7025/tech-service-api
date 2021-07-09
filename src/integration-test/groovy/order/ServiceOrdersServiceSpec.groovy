package order

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import service.ServiceOrders
import service.ServiceOrdersService
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ServiceOrdersServiceSpec extends Specification {

    ServiceOrdersService serviceOrdersService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ServiceOrders(...).save(flush: true, failOnError: true)
        //new ServiceOrders(...).save(flush: true, failOnError: true)
        //ServiceOrders serviceOrders = new ServiceOrders(...).save(flush: true, failOnError: true)
        //new ServiceOrders(...).save(flush: true, failOnError: true)
        //new ServiceOrders(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //serviceOrders.id
    }

    void "test get"() {
        setupData()

        expect:
        serviceOrdersService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ServiceOrders> serviceOrdersList = serviceOrdersService.list(max: 2, offset: 2)

        then:
        serviceOrdersList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        serviceOrdersService.count() == 5
    }

    void "test delete"() {
        Long serviceOrdersId = setupData()

        expect:
        serviceOrdersService.count() == 5

        when:
        serviceOrdersService.delete(serviceOrdersId)
        sessionFactory.currentSession.flush()

        then:
        serviceOrdersService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ServiceOrders serviceOrders = new ServiceOrders()
        serviceOrdersService.save(serviceOrders)

        then:
        serviceOrders.id != null
    }
}
