package bryan.sockets2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.io.DataInputStream;

public class Servidor extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private ServerSocket servidor;
	private Socket socketEscuha;
	private JLabel lblServer;
	private JTextArea textArea;
	private DataInputStream dtInput;
	private BufferedReader bufferFromClient;

	public static void main(String[] args) {
		Servidor server = new Servidor();

	}

	public Servidor() {
		initComponents();
		// this.run();
	}

	private void initComponents() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 199, 291);
		frame.getContentPane().setLayout(null);

		lblServer = new JLabel("Servidor");
		lblServer.setBounds(57, 21, 55, 14);

		JTextArea txtrTest = new JTextArea();
		txtrTest.setBounds(10, 45, 163, 173);
		txtrTest.setEditable(true);

		frame.getContentPane().add(lblServer);
		frame.getContentPane().add(txtrTest);

		frame.setVisible(true);
		Thread hiloEscuchaCliente = new Thread(this);
		hiloEscuchaCliente.start();
	}

	public void run() {

		try {

			while (true) {
				System.out.println("Esperando..");
				servidor = new ServerSocket(6666);
				socketEscuha = servidor.accept();// socket para el cliente que
													// esta a la escucha
				System.out.println("cliente en linea");
				// obtener flujo entrante desde el cliente
				bufferFromClient = new BufferedReader(new InputStreamReader(
						socketEscuha.getInputStream()));

				System.out.println("Mensaje que llego: "
						+ bufferFromClient.readLine());

				// establecer mensaje al servidor
				if (textArea == null) {
					textArea = new JTextArea();
					textArea.setText(bufferFromClient.readLine() + "\n");
				} else {
					textArea.setText(bufferFromClient.readLine() + "\n");
				}

				socketEscuha.close();
				bufferFromClient.close();

			}
		} catch (IOException e) {
			e.getMessage();
		}

	}
}
