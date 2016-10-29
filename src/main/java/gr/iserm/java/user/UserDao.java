package gr.iserm.java.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    final RowMapper<User> userRowMapper = (rs, rowNum) -> User.Builder.create()
            .setUuid(rs.getString(1))
            .setName(rs.getString(2))
            .setEmail(rs.getString(3))
            .build();

    private static SqlParameterSource userParameters(User user) {
        return new MapSqlParameterSource(user.toMap());
    }

    public List<User> findAll() {
        return jdbcTemplate.query("select uuid,name,email from users", userRowMapper);
    }

    public Optional<User> findByName(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        try {
            return Optional.of(jdbcTemplate.queryForObject("select uuid,name,email from users where name=:name", params, userRowMapper));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean insert(User user) {
        int update = jdbcTemplate.update("insert into users (uuid,name,email) values (:uuid,:name,:email)", userParameters(user));
        return update > 0;
    }
}
