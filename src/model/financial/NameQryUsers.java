package model.financial;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;


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
public class NameQryUsers {
	public enum FieldsUser{ Name, Password, Names, LastName};
}
