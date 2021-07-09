package user

import grails.gorm.services.Service

@Service(UserFeedbacks)
interface UserFeedbacksService {

    UserFeedbacks get(Serializable id)

    List<UserFeedbacks> list(Map args)

    Long count()

    void delete(Serializable id)

    UserFeedbacks save(UserFeedbacks userFeedbacks)

}