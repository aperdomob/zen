package model.financial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */

@NamedQueries({
	@org.hibernate.annotations.NamedQuery(
	name = "findGetAllUser",
	query = "from Usuarios o "
			+ " where o.userName = :userName"
			+ " and o.password = :password"
			+ " and o.names = :names"
			+ " and o.lastName = :lastName"
         
	)
})

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "findGetAllUserNative",
	query = "select * from Usuarios where user_name = :userName and password = :password and names = :names and last_name = :lastName",
        resultClass = Usuarios.class 
	)
})
@Entity
@Table(name = "usuarios", catalog = "financial")
public class Usuarios extends NameQryUsers implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private String names;
	private String lastName;

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** full constructor */
	public Usuarios(String userName, String password, String names,
			String lastName) {
		this.userName = userName;
		this.password = password;
		this.names = names;
		this.lastName = lastName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "names", nullable = false, length = 150)
	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	@Column(name = "last_name", nullable = false, length = 150)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}