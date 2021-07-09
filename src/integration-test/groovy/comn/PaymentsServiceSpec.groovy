package comn

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PaymentsServiceSpec extends Specification {

    PaymentsService paymentsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Payment(...).save(flush: true, failOnError: true)
        //new Payment(...).save(flush: true, failOnError: true)
        //Payment payments = new Payment(...).save(flush: true, failOnError: true)
        //new Payment(...).save(flush: true, failOnError: true)
        //new Payment(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //payments.id
    }

    void "test get"() {
        setupData()

        expect:
        paymentsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Payment> paymentsList = paymentsService.list(max: 2, offset: 2)

        then:
        paymentsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        paymentsService.count() == 5
    }

    void "test delete"() {
        Long paymentsId = setupData()

        expect:
        paymentsService.count() == 5

        when:
        paymentsService.delete(paymentsId)
        sessionFactory.currentSession.flush()

        then:
        paymentsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Payment payments = new Payment()
        paymentsService.save(payments)

        then:
        payments.id != null
    }
}
