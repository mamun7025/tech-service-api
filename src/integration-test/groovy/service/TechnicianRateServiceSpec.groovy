package service

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TechnicianRateServiceSpec extends Specification {

    TechnicianRateService technicianRateService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TechnicianRate(...).save(flush: true, failOnError: true)
        //new TechnicianRate(...).save(flush: true, failOnError: true)
        //TechnicianRate technicianRate = new TechnicianRate(...).save(flush: true, failOnError: true)
        //new TechnicianRate(...).save(flush: true, failOnError: true)
        //new TechnicianRate(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //technicianRate.id
    }

    void "test get"() {
        setupData()

        expect:
        technicianRateService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TechnicianRate> technicianRateList = technicianRateService.list(max: 2, offset: 2)

        then:
        technicianRateList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        technicianRateService.count() == 5
    }

    void "test delete"() {
        Long technicianRateId = setupData()

        expect:
        technicianRateService.count() == 5

        when:
        technicianRateService.delete(technicianRateId)
        sessionFactory.currentSession.flush()

        then:
        technicianRateService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TechnicianRate technicianRate = new TechnicianRate()
        technicianRateService.save(technicianRate)

        then:
        technicianRate.id != null
    }
}
