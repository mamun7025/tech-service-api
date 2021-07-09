package user

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserFeedbacksServiceSpec extends Specification {

    UserFeedbacksService userFeedbacksService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserFeedbacks(...).save(flush: true, failOnError: true)
        //new UserFeedbacks(...).save(flush: true, failOnError: true)
        //UserFeedbacks userFeedbacks = new UserFeedbacks(...).save(flush: true, failOnError: true)
        //new UserFeedbacks(...).save(flush: true, failOnError: true)
        //new UserFeedbacks(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userFeedbacks.id
    }

    void "test get"() {
        setupData()

        expect:
        userFeedbacksService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserFeedbacks> userFeedbacksList = userFeedbacksService.list(max: 2, offset: 2)

        then:
        userFeedbacksList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userFeedbacksService.count() == 5
    }

    void "test delete"() {
        Long userFeedbacksId = setupData()

        expect:
        userFeedbacksService.count() == 5

        when:
        userFeedbacksService.delete(userFeedbacksId)
        sessionFactory.currentSession.flush()

        then:
        userFeedbacksService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserFeedbacks userFeedbacks = new UserFeedbacks()
        userFeedbacksService.save(userFeedbacks)

        then:
        userFeedbacks.id != null
    }
}
