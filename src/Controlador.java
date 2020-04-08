import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controlador implements WindowListener, ActionListener {

	Vista CVista = null;
	Modelo CMode = null;
	

	public Controlador(Vista cVista, Modelo cMode) {
		this.CVista = cVista;
		this.CMode = cMode;
		cVista.addWindowListener(this);
		//Menus
		cVista.mEmplAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgA.setTitle("Alta");
				cVista.dlgA.setSize(150,470);
				cVista.dlgA.add(cVista.pnnuevo);
				cVista.dlgA.setVisible(true);
				cVista.dlgA.setLocation(550, 300); 

			}
		});
		cVista.mEmplEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgE.setTitle("Baja");
				cVista.dlgE.setSize(200,150);
				cVista.dlgE.add(cVista.pnEliminar);
				cVista.dlgE.setVisible(true);
				cVista.dlgE.setLocation(550, 300); 
				//rellenar choose
				String sqlSelect = "SELECT * FROM empleados";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Connection con = cMode.conectar();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						cVista.choEli.add(rs.getInt("idEmpleado")+
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

		cVista.mEmplConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgC.setTitle("Consulta");
				cVista.dlgC.setSize(200,300);
				cVista.dlgC.add(cVista.pnconsulta);
				cVista.dlgC.setVisible(true);
				cVista.dlgC.setLocation(550, 300); 
				Connection con = cMode.conectar();
				cMode.rellenarTextArea(con,cVista.consulta);
				cMode.desconectar(con);
			}

		
		});

		cVista.mEmplModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgM.setTitle("Modificar");
				cVista.dlgM.setSize(150,500);
				cVista.dlgM.add(cVista.pnModi);
				cVista.dlgM.setVisible(true);
				cVista.	dlgM.setLocation(550, 300); 
				Connection con = cMode.conectar();
				// Rellenar el Choice
				String sqlSelect = "SELECT * FROM empleados";
				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						cVista.chMod.add(rs.getInt("idEmpleado")+
								"-"+rs.getString("nombreEmpleado"));
					}
					//terminar de montar
					cVista.chMod.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent arg0) {}
					});
					rs.close();
					stmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				cVista.ModAcep.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String[] Emple=cVista.chMod.getSelectedItem().split("-");
						int respuesta = cMode.modificar(con, Integer.parseInt(Emple[0]),cVista.ModiExEmpl.getText(), cVista.ModiFechaNacimiento.getText(),
								cVista.ModiIngreso.getText(), cVista.ModiSalario.getText(),cVista.ModiComision.getText(), cVista.ModiHijos.getText(), 
								cVista.ModiNombreEmpl.getText());
						// Mostramos resultado
						if(respuesta == 0)
						{
							System.out.println("Empleado Modificado");
						}
						else
						{
							System.out.println("Error en la modificación");
						}
						// Actualizar el Choice
						cVista.chMod.removeAll();
						cVista.chMod.add("Seleccionar uno...");
						String sqlSelect = "SELECT * FROM Pan";
						try {
							// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(sqlSelect);
							while (rs.next()) 
							{
								cVista.chMod.add(rs.getInt("idEmpleado")+
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

				cMode.desconectar(con);


			}
		});
		//Boton cerrar
		cVista.cerrarAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgA.setVisible(false);

			}
		});
		cVista.cerrarEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgE.setVisible(false);

			}
		});
		cVista.cerrarConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgC.setVisible(false);


			}
		});
		cVista.cerrarModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cVista.dlgM.setVisible(false);


			}
		});
		//Conexiones
		//Nuevo
		cVista.nuevoAceptar.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {


				Connection con = cMode.conectar();
				int respuesta = cMode.insertar(con,cVista.nuevoExEmpl.getText(), cVista.nuevoFechaNacimiento.getText(),
						cVista.nuevoIngreso.getText(), cVista.nuevoSalario.getText(), cVista.nuevoComision.getText(),cVista.nuevoHijos.getText(),
						cVista.nuevoNombreEmpl.getText());
				cMode.desconectar(con);
			}
		});
		//Eliminar

		cVista.ElibtnAceptar.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				// Conectar a BD
				Connection con = cMode.conectar(); 
				// Borrar
				String[] EliEmp=cVista.choEli.getSelectedItem().split("-");
				int respuesta = cMode.borrar(con, Integer.parseInt(EliEmp[0]));

				try {
					// CREAR UN STATEMENT PARA UNA CONSULTA SELECT
					String sqlSelect = "SELECT * FROM empleados";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sqlSelect);
					while (rs.next()) 
					{
						cVista.choEli.add(rs.getInt("idEmpleado")+
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
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
