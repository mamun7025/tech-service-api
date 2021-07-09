package order

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import service.ServiceItemsService
import service.ServiceItems
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ServiceItemsServiceSpec extends Specification {

    ServiceItemsService serviceItemsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ServiceItems(...).save(flush: true, failOnError: true)
        //new ServiceItems(...).save(flush: true, failOnError: true)
        //ServiceItems serviceItems = new ServiceItems(...).save(flush: true, failOnError: true)
        //new ServiceItems(...).save(flush: true, failOnError: true)
        //new ServiceItems(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //serviceItems.id
    }

    void "test get"() {
        setupData()

        expect:
        serviceItemsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ServiceItems> serviceItemsList = serviceItemsService.list(max: 2, offset: 2)

        then:
        serviceItemsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        serviceItemsService.count() == 5
    }

    void "test delete"() {
        Long serviceItemsId = setupData()

        expect:
        serviceItemsService.count() == 5

        when:
        serviceItemsService.delete(serviceItemsId)
        sessionFactory.currentSession.flush()

        then:
        serviceItemsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ServiceItems serviceItems = new ServiceItems()
        serviceItemsService.save(serviceItems)

        then:
        serviceItems.id != null
    }
}
