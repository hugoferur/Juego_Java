import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {

	private String cadenaConexion;
	private String driver;
	private Connection cn;
	private ResultSet rs;

	//CONSTRUCTOR/ES
	public BaseDatos(String bd){
		cadenaConexion="jdbc:mysql://localhost:3306/"+bd;
		driver="com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public void conectar(){
		try {
			cn=DriverManager.getConnection(cadenaConexion, 
					"root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet obtenerDatos(){
		PreparedStatement sentencia;
		String strSent="select * from juego";
		try {
			sentencia=cn.prepareStatement(strSent, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);

			rs=sentencia.executeQuery();
			sentencia.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	

	public void insertarRecord(String nombre, String tiempo) {
		String sentSql = "INSERT INTO records (nombre, tiempo) VALUES (?, ?)";
		PreparedStatement sentencia;
		try {
			sentencia = cn.prepareStatement(sentSql);
			sentencia.setString(1, nombre);
			sentencia.setString(2, tiempo);

			int cont= sentencia.executeUpdate();  
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	public void cerrarConexion(){
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}