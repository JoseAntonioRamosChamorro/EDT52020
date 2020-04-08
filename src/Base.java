import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Base extends Frame implements WindowListener {

	private static final long serialVersionUID = 1L;
	MenuBar MB = new MenuBar();

	Menu mEmpl = new Menu("Empleados");
	MenuItem mEmplAlta = new MenuItem("Alta");
	MenuItem mEmplEliminar = new MenuItem("Eliminar");
	MenuItem mEmplConsulta = new MenuItem("Consulta");
	MenuItem mEmplModificar= new MenuItem("Modificar");

	Dialog dlgA = new Dialog(this);
	Dialog dlgE = new Dialog(this);
	Dialog dlgC = new Dialog(this);
	Dialog dlgM = new Dialog(this);
	//cerrar
	Button cerrarAlta = new Button("Cerrar");
	Button cerrarEliminar = new Button("Cerrar");
	Button cerrarConsulta = new Button("Cerrar");
	Button cerrarModificar = new Button("Cerrar");


	//nuevo
	Panel pnnuevo = new Panel();
	Button nuevoAceptar = new Button("Insertar");
	TextField nuevoExEmpl = new TextField(10);
	TextField nuevoFechaNacimiento = new TextField(10);
	TextField nuevoIngreso = new TextField(10);
	TextField nuevoSalario = new TextField(10);
	TextField nuevoComision = new TextField(10);
	TextField nuevoHijos = new TextField(10);
	TextField nuevoNombreEmpl = new TextField(10);
	//texto nuevo
	Label extensionEmpl = new Label("Extension Empleado");
	Label fechanacimiEmpl = new Label("Fecha Nacimiento");
	Label fechaingresoEmpl = new Label("Fecha Ingreso Empleado");
	Label SalarioEmpl = new Label("Salario Empleado");
	Label ComisionEmpl = new Label("Comision Empleado");
	Label FechaNacimin = new Label("Hijos Empleado");
	Label nuevoNomEpl = new Label("Nombre Empleado");

	//consulta
	Panel pnconsulta = new Panel();
	TextArea consulta = new TextArea(5,20);


	//eliminar
	Panel pnEliminar = new Panel();
	Label lblCliente = new Label("Cliente a Eliminar:");
	Choice choEli = new Choice();
	Button ElibtnAceptar = new Button("Aceptar");
	Dialog seguro;
	Button btndlgSi = new Button("Si");
	Button btndlgNo = new Button("No");
	//Modificar
	Choice chMod = new Choice();
	Panel pnModi = new Panel();
	TextField ModiExEmpl = new TextField(10);
	TextField ModiFechaNacimiento = new TextField(10);
	TextField ModiIngreso = new TextField(10);
	TextField ModiSalario = new TextField(10);
	TextField ModiComision = new TextField(10);
	TextField ModiHijos = new TextField(10);
	TextField ModiNombreEmpl = new TextField(10);
	Button ModAcep = new Button("Modificar");
	//texto modificacion
	Label extensionEmplm = new Label("Extension Empleado");
	Label fechanacimiEmplm = new Label("Fecha Nacimiento");
	Label fechaingresoEmplm = new Label("Fecha Ingreso Empleado");
	Label SalarioEmplm = new Label("Salario Empleado");
	Label ComisionEmplm = new Label("Comision Empleado");
	Label FechaNaciminm = new Label("Hijos Empleado");
	Label nuevoNomEplm = new Label("Nombre Empleado");

	public Base() {
		// Conectar BD
		Connection con = conectar();
		setTitle("Empresa");
		addWindowListener(this);
		dlgA.addWindowListener(this);
		dlgE.addWindowListener(this);
		dlgC.addWindowListener(this);
		dlgM.addWindowListener(this);
		setLayout(new FlowLayout());
		mEmpl.add(mEmplAlta);
		mEmpl.add(mEmplEliminar);
		mEmpl.add(mEmplConsulta);
		mEmpl.add(mEmplModificar);	
		MB.add(mEmpl);
		setMenuBar(MB);
		//ADD
		//addNuevo
		pnnuevo.add(extensionEmpl);
		pnnuevo.add(nuevoExEmpl);
		pnnuevo.add(fechanacimiEmpl);
		pnnuevo.add(nuevoFechaNacimiento);
		pnnuevo.add(fechaingresoEmpl);
		pnnuevo.add(nuevoIngreso);
		pnnuevo.add(SalarioEmpl);
		pnnuevo.add(nuevoSalario);
		pnnuevo.add(FechaNacimin);
		pnnuevo.add(nuevoComision);
		pnnuevo.add(ComisionEmpl);
		pnnuevo.add(nuevoHijos);
		pnnuevo.add(nuevoNomEpl);
		pnnuevo.add(nuevoNombreEmpl);
		pnnuevo.add(nuevoAceptar); 
		pnnuevo.add(cerrarAlta);

		//addConsulta
		pnconsulta.add(consulta);

		pnconsulta.add(cerrarConsulta);
		//addEliminar
		pnEliminar.add(lblCliente);
		pnEliminar.add(choEli);
		pnEliminar.add(ElibtnAceptar);
		pnEliminar.add(cerrarEliminar);
		//rellenar choose
		String sqlSelect = "SELECT * FROM empleados";
		try {
			// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSelect);
			while (rs.next()) 
			{
				choEli.add(rs.getInt("idEmpleado")+
						"-"+rs.getString("nombreEmpleado"));

			}

		} catch (SQLException ex) {
			System.out.println("ERROR:al consultar");
			ex.printStackTrace();
		}
		// Cerrar la conexión
		desconectar(con);
		//addModificar
		pnModi.add(chMod);
		pnModi.add(extensionEmplm);
		pnModi.add(ModiExEmpl);
		pnModi.add(fechanacimiEmplm);
		pnModi.add(ModiFechaNacimiento);	
		pnModi.add(fechaingresoEmplm);
		pnModi.add(ModiIngreso);
		pnModi.add(SalarioEmplm);
		pnModi.add(ModiSalario);
		pnModi.add(ComisionEmplm);
		pnModi.add(ModiComision);
		pnModi.add(FechaNaciminm);
		pnModi.add(ModiHijos);
		pnModi.add(nuevoNomEplm);
		pnModi.add(ModiNombreEmpl);
		pnModi.add(ModAcep);
		pnModi.add(cerrarModificar);

		//ADD end

		setLocation(550, 300); 
		setSize(200,200);
		dlgA.setResizable(false);
		dlgE.setResizable(false);
		dlgC.setResizable(false);
		dlgM.setResizable(true);
		setResizable(false);

		//Menus
		mEmplAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgA.setTitle("Alta");
				dlgA.setSize(150,470);
				dlgA.add(pnnuevo);
				dlgA.setVisible(true);
				dlgA.setLocation(550, 300); 

			}
		});

		mEmplEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgE.setTitle("Baja");
				dlgE.setSize(200,150);
				dlgE.add(pnEliminar);
				dlgE.setVisible(true);
				dlgE.setLocation(550, 300); 
				//rellenar choose
				String sqlSelect = "SELECT * FROM empleados";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						choEli.add(rs.getInt("idEmpleado")+
								"-"+rs.getString("nombreEmpleado"));

					}
					rs.close();
					stmt.close();
				} catch (SQLException ex) {
					System.out.println("ERROR:al consultar");
					ex.printStackTrace();
				}
				// Cerrar la conexión

			}
		});

		mEmplConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgC.setTitle("Consulta");
				dlgC.setSize(200,300);
				dlgC.add(pnconsulta);
				dlgC.setVisible(true);
				dlgC.setLocation(550, 300); 
				Connection con = conectar();
				rellenarTextArea(con, consulta);
				desconectar(con);
			}

			private void rellenarTextArea(Connection con, TextArea consulta) {
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
		});

		mEmplModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgM.setTitle("Modificar");
				dlgM.setSize(150,500);
				dlgM.add(pnModi);
				dlgM.setVisible(true);
				dlgM.setLocation(550, 300); 
				Connection con = conectar();
				// Rellenar el Choice
				String sqlSelect = "SELECT * FROM empleados";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						chMod.add(rs.getInt("idEmpleado")+
								"-"+rs.getString("nombreEmpleado"));
					}
					//terminar de montar
					chMod.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent arg0) {}
					});
					rs.close();
					stmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				ModAcep.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String[] Emple=chMod.getSelectedItem().split("-");
						int respuesta = modificar(con, Integer.parseInt(Emple[0]),ModiExEmpl.getText(), ModiFechaNacimiento.getText(),
								ModiIngreso.getText(), ModiSalario.getText(), ModiComision.getText(), ModiHijos.getText(), 
								ModiNombreEmpl.getText());
						// Mostramos resultado
						if(respuesta == 0)
						{
							System.out.println("Pan Modificado");
						}
						else
						{
							System.out.println("Error en la modificación");
						}
						// Actualizar el Choice
						chMod.removeAll();
						chMod.add("Seleccionar uno...");
						String sqlSelect = "SELECT * FROM Pan";
						try {
							// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(sqlSelect);
							while (rs.next()) 
							{
								chMod.add(rs.getInt("idEmpleado")+
										"-"+rs.getString("nombreEmpleado"));

							}

							rs.close();
							stmt.close();
						} catch (SQLException ex) {
							System.out.println("ERROR:al consultar");
							ex.printStackTrace();
						}

					}

					private int modificar(Connection con, int idEM, String ModiExEmpl, String ModiFechaNacimiento, String ModiIngreso,
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
				});

				desconectar(con);


			}
		});
		//Boton cerrar
		cerrarAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgA.setVisible(false);

			}
		});
		cerrarEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgE.setVisible(false);

			}
		});
		cerrarConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgC.setVisible(false);


			}
		});
		cerrarModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dlgM.setVisible(false);


			}
		});
		//Conexiones
		//Nuevo
		nuevoAceptar.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {


				Connection con = conectar();
				int respuesta = insertar(con,nuevoExEmpl.getText(), nuevoFechaNacimiento.getText(),
						nuevoIngreso.getText(), nuevoSalario.getText(), nuevoComision.getText(),nuevoHijos.getText(),
						nuevoNombreEmpl.getText());
				desconectar(con);
			}
		});
		//Eliminar

		ElibtnAceptar.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				// Conectar a BD
				Connection con = conectar(); 
				// Borrar
				String[] EliEmp=choEli.getSelectedItem().split("-");
				int respuesta = borrar(con, Integer.parseInt(EliEmp[0]));

				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						choEli.add(rs.getInt("idEmpleado")+
								"-"+rs.getString("nombreEmpleado"));
					}
					rs.close();
					stmt.close();
				} catch (SQLException ex) {
					System.out.println("ERROR:al consultar");
					ex.printStackTrace();
				}																																																																			
			}
		});
		setVisible(true);
		//fin
	}



	private Connection conectar() {
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

	public static void main(String[] args) {
		new Base();

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
