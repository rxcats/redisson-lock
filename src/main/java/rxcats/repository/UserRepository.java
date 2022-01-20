package rxcats.repository;

import org.springframework.data.repository.Repository;
import rxcats.model.User;

public interface UserRepository extends Repository<User, Long> {
}
