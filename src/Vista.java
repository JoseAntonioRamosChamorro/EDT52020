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

public class Vista extends Frame{
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
	
	public Vista() {
		// Conectar BD
				
				setTitle("Empresa");
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
				setVisible(true);
	}
}

