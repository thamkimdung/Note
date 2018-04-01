package springmvc_example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import springmvc_example.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<User> listAllUser() {
		String sql = "select * from user";
		List<User> list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new UserMapper());
		return list;
	}

	private SqlParameterSource getSqlParameterByModel(User user) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		return mapSqlParameterSource;

	}

	private static final class UserMapper implements org.springframework.jdbc.core.RowMapper<User> {
		public User mapRow(ResultSet rs, int i) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirst_name(rs.getString("first_name"));
			user.setLast_name(rs.getString("last_name"));
			user.setAddress(rs.getString("address"));
			return user;

		}
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user(frst_name,last_name,address) VALUES(:first_name,:last_name,:address)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String sql = "Update user set first_name= : first_name, last_name= :last_name, address=: address where id=: id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));

	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from user where id = : id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(new User(id)));

	}

	public User finUserById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from user where id=:id";
		return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(new User(id)), new UserMapper());
	}

}
