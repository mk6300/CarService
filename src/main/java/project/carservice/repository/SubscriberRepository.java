package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Subscriber;
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    boolean existsBySubsEmail(String subsEmail);

    void deleteBySubsEmail(String email);
}
