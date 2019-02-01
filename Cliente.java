package bryan.sockets2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Cliente extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JLabel lblCliente;
	private JTextArea txtArea;
	private JButton btnEnvioCliente;
	private Socket skCliente;
	private DataOutputStream flujoSalida;
	private String mensaje;

	public Cliente() {
		initComponents();

	}

	private void initComponents() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 205, 306);
		frame.setLayout(null);

		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(73, 11, 46, 14);

		txtArea = new JTextArea();
		txtArea.setBounds(10, 36, 169, 166);

		btnEnvioCliente = new JButton("Enviar");
		btnEnvioCliente.setBounds(53, 213, 89, 23);
		btnEnvioCliente.addActionListener(this);

		frame.add(lblCliente);
		frame.add(txtArea);
		frame.add(btnEnvioCliente);

		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnEnvioCliente) {
			envioPaqueteServer();
		}

	}

	private void envioPaqueteServer() {

		try {
			System.out.println("**************");

			skCliente = new Socket("localhost", 6666);
			mensaje = txtArea.getText().trim();
			// flujo de salida por el puente donde sera enviado el mensaje
			flujoSalida = new DataOutputStream(skCliente.getOutputStream());
			flujoSalida.writeUTF(mensaje);
			flujoSalida.close();
			txtArea.setText("");

		} catch (UnknownHostException e) {
			e.getMessage();
		} catch (IOException e) {

			e.getMessage();
		}finally{
			System.out.println("->>>>>>>>>>>>>>>>>>>>>");
		}

	}

	public static void main(String[] args) {
		Cliente cli = new Cliente();
	}
	
	/*
	 *https://www.programarya.com/Cursos-Avanzados/Java/Sockets 
	 */

}
