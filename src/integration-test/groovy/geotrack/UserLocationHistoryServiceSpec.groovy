package geotrack

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserLocationHistoryServiceSpec extends Specification {

    UserLocationHistoryService userLocationHistoryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserLocationHistory(...).save(flush: true, failOnError: true)
        //new UserLocationHistory(...).save(flush: true, failOnError: true)
        //UserLocationHistory userLocationHistory = new UserLocationHistory(...).save(flush: true, failOnError: true)
        //new UserLocationHistory(...).save(flush: true, failOnError: true)
        //new UserLocationHistory(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userLocationHistory.id
    }

    void "test get"() {
        setupData()

        expect:
        userLocationHistoryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserLocationHistory> userLocationHistoryList = userLocationHistoryService.list(max: 2, offset: 2)

        then:
        userLocationHistoryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userLocationHistoryService.count() == 5
    }

    void "test delete"() {
        Long userLocationHistoryId = setupData()

        expect:
        userLocationHistoryService.count() == 5

        when:
        userLocationHistoryService.delete(userLocationHistoryId)
        sessionFactory.currentSession.flush()

        then:
        userLocationHistoryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserLocationHistory userLocationHistory = new UserLocationHistory()
        userLocationHistoryService.save(userLocationHistory)

        then:
        userLocationHistory.id != null
    }
}
