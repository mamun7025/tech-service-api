package order

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import service.ServiceCenter
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ServiceCenterServiceSpec extends Specification {

    ServiceCenterService serviceCenterService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ServiceCenter(...).save(flush: true, failOnError: true)
        //new ServiceCenter(...).save(flush: true, failOnError: true)
        //ServiceCenter serviceCenter = new ServiceCenter(...).save(flush: true, failOnError: true)
        //new ServiceCenter(...).save(flush: true, failOnError: true)
        //new ServiceCenter(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //serviceCenter.id
    }

    void "test get"() {
        setupData()

        expect:
        serviceCenterService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ServiceCenter> serviceCenterList = serviceCenterService.list(max: 2, offset: 2)

        then:
        serviceCenterList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        serviceCenterService.count() == 5
    }

    void "test delete"() {
        Long serviceCenterId = setupData()

        expect:
        serviceCenterService.count() == 5

        when:
        serviceCenterService.delete(serviceCenterId)
        sessionFactory.currentSession.flush()

        then:
        serviceCenterService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ServiceCenter serviceCenter = new ServiceCenter()
        serviceCenterService.save(serviceCenter)

        then:
        serviceCenter.id != null
    }
}
