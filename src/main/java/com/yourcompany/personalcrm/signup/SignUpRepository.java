package com.yourcompany.personalcrm.signup;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.yourcompany.personalcrm.login.User;

public interface SignUpRepository
{
    @SqlQuery("""
        SELECT
            id,
            username,
            email,
            hash_of_secret as password_hash
        FROM app_users 
        WHERE
            username ILIKE :username
            OR
            email ILIKE :email
        """)
    @RegisterConstructorMapper(User.class)
    List<User> findByUsernameOrEmail(@Bind String username, @Bind String email);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO app_users (username, email, hash_of_secret) VALUES (:username, :email, :passwordHash)")
    String insertUser(@Bind String username, @Bind String email, @Bind String passwordHash);
}
