package bryan.sockets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class Servidor extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private ServerSocket servidor;
	private Socket socketEscuha;
	private JLabel lblServer;
	private JTextArea textArea;
	private BufferedReader bufferFromClient;

	public static void main(String[] args) {
		Servidor server = new Servidor();

	}

	public Servidor() {
		initComponents();
	}

	private void initComponents() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(800, 100, 199, 291);
		frame.getContentPane().setLayout(null);

		lblServer = new JLabel("Servidor");
		lblServer.setBounds(57, 21, 55, 14);

		textArea = new JTextArea();
		textArea.setBounds(10, 45, 163, 173);
		textArea.setEditable(true);

		frame.getContentPane().add(lblServer);
		frame.getContentPane().add(textArea);

		frame.setVisible(true);
		Thread hiloEscuchaCliente = new Thread(this);
		hiloEscuchaCliente.start();
	}

	public void run() {
	

		try {
			servidor = new ServerSocket(6666);

			while (true) {
				System.out.println("Esperando..");				
				
				// socket para el cliente queesta a la escucha
				socketEscuha = servidor.accept();
				
				System.out.println("cliente en linea");
				// obtener flujo entrante desde el cliente
				bufferFromClient = new BufferedReader(new InputStreamReader(
						socketEscuha.getInputStream(), "UTF-8"));

				String mensaje = bufferFromClient.readLine();
				
				System.out.println("Mensaje que llego: " + mensaje);
				// establecer mensaje al servidor
				if (textArea == null) {
					textArea = new JTextArea();
					textArea.append(mensaje + "\n");
				} else {
					textArea.append(mensaje + "\n");
				
				}
				socketEscuha.close();
				bufferFromClient.close();

			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}
}
