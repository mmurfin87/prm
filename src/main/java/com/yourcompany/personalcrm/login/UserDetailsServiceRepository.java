package com.yourcompany.personalcrm.login;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.NonNull;

public interface UserDetailsServiceRepository extends UserDetailsService
{
    @RegisterConstructorMapper(User.class)
    @SqlQuery("SELECT id, username, email, hash_of_secret as password_hash FROM app_users WHERE username = :username")
    User findByUsername(@Bind String username);

    @RegisterConstructorMapper(User.class)
    @SqlQuery("SELECT u.id, u.username, u.email, u.hash_of_secret as password_hash FROM app_users u INNER JOIN user_tokens at ON u.id = at.user_id WHERE at.token = :token")
    User findUserByToken(@Bind String token);

    @SqlUpdate("INSERT INTO app_users (username, password) VALUES (:username, :password)")
    void saveUser(@Bind String username, @Bind String password);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO user_tokens (user_id, token, token_type, expiration) SELECT id, :token, :tokenType, :expiration FROM app_users WHERE username = :username")
    String saveToken(@Bind String username, @Bind String token, @Bind String tokenType, @Bind Instant expiration);

    @RegisterConstructorMapper(UserToken.class)
    @SqlQuery("SELECT id, user_id, token, token_type, expiration FROM user_tokens WHERE token = :token AND expiration > CURRENT_TIMESTAMP")
    UserToken findValidTokenByToken(@Bind String token);

    @SqlUpdate("DELETE FROM user_tokens WHERE token = :token")
    void deleteToken(@Bind String token);

    @SqlUpdate("DELETE FROM user_tokens WHERE user_id = :userId AND token_type = :tokenType")
    void deleteByUserIdAndTokenType(@Bind("userId") Long userId, @Bind("tokenType") String tokenType);

    default String createSessionToken(String username)
    {
        return createToken(this, username, "SESSION");
    }

    default String createBearerToken(String username)
    {
        return createToken(this, username, "BEARER");
    }

    private static String createToken(@NonNull final UserDetailsServiceRepository repo, @NonNull final String username, @NonNull final String tokenType)
    {
        final String token = UUID.randomUUID().toString();
        return repo.saveToken(username, token, tokenType, Instant.now().plus(1, ChronoUnit.DAYS));
    }

    @Override
    default User loadUserByUsername(String username) throws UsernameNotFoundException
    {
        final User user = findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return user;
    }

    
}