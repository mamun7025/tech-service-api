package geotrack

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserLocationServiceSpec extends Specification {

    UserLocationService userLocationService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserLocation(...).save(flush: true, failOnError: true)
        //new UserLocation(...).save(flush: true, failOnError: true)
        //UserLocation userLocation = new UserLocation(...).save(flush: true, failOnError: true)
        //new UserLocation(...).save(flush: true, failOnError: true)
        //new UserLocation(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userLocation.id
    }

    void "test get"() {
        setupData()

        expect:
        userLocationService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserLocation> userLocationList = userLocationService.list(max: 2, offset: 2)

        then:
        userLocationList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userLocationService.count() == 5
    }

    void "test delete"() {
        Long userLocationId = setupData()

        expect:
        userLocationService.count() == 5

        when:
        userLocationService.delete(userLocationId)
        sessionFactory.currentSession.flush()

        then:
        userLocationService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserLocation userLocation = new UserLocation()
        userLocationService.save(userLocation)

        then:
        userLocation.id != null
    }
}
