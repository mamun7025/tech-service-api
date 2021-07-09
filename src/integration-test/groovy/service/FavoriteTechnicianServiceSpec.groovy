package service

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FavoriteTechnicianServiceSpec extends Specification {

    FavoriteTechnicianService favoriteTechnicianService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FavoriteTechnician(...).save(flush: true, failOnError: true)
        //new FavoriteTechnician(...).save(flush: true, failOnError: true)
        //FavoriteTechnician favoriteTechnician = new FavoriteTechnician(...).save(flush: true, failOnError: true)
        //new FavoriteTechnician(...).save(flush: true, failOnError: true)
        //new FavoriteTechnician(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //favoriteTechnician.id
    }

    void "test get"() {
        setupData()

        expect:
        favoriteTechnicianService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FavoriteTechnician> favoriteTechnicianList = favoriteTechnicianService.list(max: 2, offset: 2)

        then:
        favoriteTechnicianList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        favoriteTechnicianService.count() == 5
    }

    void "test delete"() {
        Long favoriteTechnicianId = setupData()

        expect:
        favoriteTechnicianService.count() == 5

        when:
        favoriteTechnicianService.delete(favoriteTechnicianId)
        sessionFactory.currentSession.flush()

        then:
        favoriteTechnicianService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FavoriteTechnician favoriteTechnician = new FavoriteTechnician()
        favoriteTechnicianService.save(favoriteTechnician)

        then:
        favoriteTechnician.id != null
    }
}
