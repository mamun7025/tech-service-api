package order

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ServicePaymentServiceSpec extends Specification {

    ServicePaymentService servicePaymentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ServicePayment(...).save(flush: true, failOnError: true)
        //new ServicePayment(...).save(flush: true, failOnError: true)
        //ServicePayment servicePayment = new ServicePayment(...).save(flush: true, failOnError: true)
        //new ServicePayment(...).save(flush: true, failOnError: true)
        //new ServicePayment(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //servicePayment.id
    }

    void "test get"() {
        setupData()

        expect:
        servicePaymentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ServicePayment> servicePaymentList = servicePaymentService.list(max: 2, offset: 2)

        then:
        servicePaymentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        servicePaymentService.count() == 5
    }

    void "test delete"() {
        Long servicePaymentId = setupData()

        expect:
        servicePaymentService.count() == 5

        when:
        servicePaymentService.delete(servicePaymentId)
        sessionFactory.currentSession.flush()

        then:
        servicePaymentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ServicePayment servicePayment = new ServicePayment()
        servicePaymentService.save(servicePayment)

        then:
        servicePayment.id != null
    }
}
