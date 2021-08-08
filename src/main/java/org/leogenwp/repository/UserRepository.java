package org.leogenwp.repository;
import org.leogenwp.model.User;

public interface UserRepository extends GenericRepository<User,Integer>{
    public User getByLogin(String login);
}
