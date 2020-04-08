import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {
	void rellenarTextArea(Connection con, TextArea consulta) {
		String sqlSelect = "SELECT * FROM empleados";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				if(consulta.getText().length()==0)
				{
					consulta.setText(rs.getInt("idEmpleado")+
							"-"+rs.getString("nombreEmpleado"));
				}
				else
				{
					consulta.setText(consulta.getText() + "\n" +
							rs.getInt("idEmpleado")+
							"-"+rs.getString("nombreEmpleado"));
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("ERROR:al consultar");
			ex.printStackTrace();
		}

	}
	int modificar(Connection con, int idEM, String ModiExEmpl, String ModiFechaNacimiento, String ModiIngreso,
			String ModiSalario, String ModiComision, String ModiHijos, String ModiNombreEmpl) {
		int respuesta = 0;
		String sql = "update empleado set extensionEmpleado='"+ModiExEmpl+"',fechaNacimientoEmpleado='"+ModiFechaNacimiento+"'"
				+ ",fechaIngresoEmpleado='"+ModiIngreso+"' ,salarioEmpleado='"+ModiSalario+"',"
				+ "comisionEmpleado='"+ModiComision+"' ,hijosEmpleado='"+ModiHijos+"' ,"
				+ "nombreEmpleado='"+ModiNombreEmpl+"' where idPan='"+idEM+"'";
		System.out.println(sql);
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			sta.executeUpdate(sql);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			System.out.println("ERROR:al hacer un Update");
			ex.printStackTrace();
			respuesta = 1;
		}
		return respuesta;

	}
	Connection conectar() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/empresa?autoReconnect=true&useSSL=false";
		String user = "root2";
		String password = "12345678A";
		Connection con = null;
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD empresa
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("Conectado a la base de datos");
			}
		} catch (SQLException ex) {
			System.out.println("ERROR:La dirección no es válida o el usuario y clave");
			ex.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1-" + cnfe.getMessage());
		}
		return con;
	}
	public void desconectar(Connection con)
	{
		try
		{
			con.close();
		}
		catch(Exception e) {}
	}
	//nUEVO
	public int insertar(Connection con, String nuevoExEmpl, String nuevoFechaNacimiento, String nuevoIngreso, String nuevoSalario,
			String nuevoComision,String nuevoHijos, String nuevoNombreEmpl) {
		int respuesta = 0;
		try 
		{
			java.sql.Statement sta = con.createStatement();
			String cadenaSQL = "INSERT INTO empleados (extensionEmpleado,fechaNacimientoEmpleado,fechaIngresoEmpleado,salarioEmpleado,"
					+ "comisionEmpleado,hijosEmpleado,nombreEmpleado) "
					+ "VALUES ('" + nuevoExEmpl + "','"+ nuevoFechaNacimiento +"','"+ nuevoIngreso +"','"+ nuevoSalario +"','"+ nuevoComision +"'"
					+ ",'"+ nuevoHijos +"','"+ nuevoNombreEmpl +"')";
			System.out.println(cadenaSQL);
			sta.executeUpdate(cadenaSQL);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			System.out.println("ERROR:al hacer un Insert");
			ex.printStackTrace();
			respuesta = 1;
		}
		return respuesta;

	}
	public int borrar(Connection con, int idEmpleado) {
		int respuesta = 0;
		String sql = "DELETE FROM empleados WHERE idEmpleado = " + idEmpleado;
		System.out.println(sql);
		try 
		{
			// Creamos un STATEMENT para una consulta SQL INSERT.
			Statement sta = con.createStatement();
			sta.executeUpdate(sql);
			sta.close();
		} 
		catch (SQLException ex) 
		{
			System.out.println("ERROR:al hacer un Delete");
			ex.printStackTrace();
			respuesta = 1;
		}
		return respuesta;
	}
}
