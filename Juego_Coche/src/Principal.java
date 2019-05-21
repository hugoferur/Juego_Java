import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JuegoCarretera juegocarretera;
	private JButton btnEmpezar;
	private JButton btnInstrucciones;
	private JButton btnSalir;
	private JuegoMenu juegomenu;
	private Image rojoApagado;
	private Image rojoEncendido;
	private Image amarilloApagado;
	private Image amarilloEncendido;
	private Image verdeApagado;
	private Image verdeEncendido;
	private Image titulo;
	private int min=0, sec=-1;
	private JLabel lblCronometro;
	private Timer cronometro;
	private Timer musicaespera;
	private PantallaMuerte pantallamuerte;
	private Coche coche;
	private int vel=10;
	private int temp=10;
	private Sonido musicafondomenu, musicafondomuerte, chocar, gameover;
	private int tiempo=0;
	private boolean music;
	private PantallaRecords pantallarecords;
	private CocheJugador cochejugador;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JLabel getLblCronometro() {
		return lblCronometro;
	}

	public void setLblCronometro(JLabel lblCronometro) {
		this.lblCronometro = lblCronometro;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}


	public Principal() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(890, 679);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		rojoApagado=Toolkit.getDefaultToolkit().getImage("./images/rojo apagado.png").getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		rojoEncendido=Toolkit.getDefaultToolkit().getImage("./images/rojo encendido.png").getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		amarilloApagado=Toolkit.getDefaultToolkit().getImage("./images/amarillo apagado.png").getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		amarilloEncendido=Toolkit.getDefaultToolkit().getImage("./images/amarillo encendido.png").getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		verdeApagado=Toolkit.getDefaultToolkit().getImage("./images/verde apagado.png").getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		verdeEncendido=Toolkit.getDefaultToolkit().getImage("./images/verde encendido.png").getScaledInstance(110, 110, Image.SCALE_SMOOTH);

		musicafondomuerte=new Sonido("musicafondo.wav");

		btnSalir = new JButton("");
		btnSalir.setBounds(145, 90, 110, 110);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setBorder(null);
		btnSalir.setOpaque(true);
		btnSalir.setFocusPainted(false);
		btnSalir.setIcon(new ImageIcon(rojoApagado));
		btnSalir.setRolloverIcon(new ImageIcon(rojoEncendido));
		contentPane.add(btnSalir);


		btnInstrucciones = new JButton("");
		btnInstrucciones.setBounds(145, 210, 110, 110);
		btnInstrucciones.setBackground(Color.DARK_GRAY);
		btnInstrucciones.setContentAreaFilled(false);
		btnInstrucciones.setBorder(null);
		btnInstrucciones.setOpaque(true);
		btnInstrucciones.setFocusPainted(false);
		btnInstrucciones.setIcon(new ImageIcon(amarilloApagado));
		btnInstrucciones.setRolloverIcon(new ImageIcon(amarilloEncendido));
		contentPane.add(btnInstrucciones);


		btnEmpezar = new JButton("");
		btnEmpezar.setBounds(145, 330, 110, 110);
		btnEmpezar.setBackground(Color.DARK_GRAY);
		btnEmpezar.setContentAreaFilled(false);
		btnEmpezar.setBorder(null);
		btnEmpezar.setOpaque(true);
		btnEmpezar.setFocusPainted(false);
		btnEmpezar.setIcon(new ImageIcon(verdeApagado));
		btnEmpezar.setRolloverIcon(new ImageIcon(verdeEncendido));
		contentPane.add(btnEmpezar);

		lblCronometro = new JLabel("00:00");
		lblCronometro.setBounds(795, 32, 36, 14);
		contentPane.add(lblCronometro);
		lblCronometro.setVisible(false);

		juegocarretera=new JuegoCarretera(this);
		juegocarretera.setBounds(0, 0, 840, 661);
		contentPane.add(juegocarretera);
		juegocarretera.setVisible(false);

		juegomenu=new JuegoMenu(this);
		juegomenu.setBounds(0, 0, 840, 661);
		contentPane.add(juegomenu);

		pantallamuerte=new PantallaMuerte(this);
		pantallamuerte.setBounds(0, 0, 840, 661);
		contentPane.add(pantallamuerte);
		pantallamuerte.setVisible(false);

		juegomenu.registrarEventos();
		RegistrarEventos();
		//juegocarretera.registrarEventosInic();
		juegocarretera.moverCocheJugador();
		musicafondomenu=new Sonido("musicafondo.wav");
		musicafondomenu.playLoop();

		pantallarecords=new PantallaRecords(this);
		pantallarecords.setBounds(0, 0, 840, 661);
		contentPane.add(pantallarecords);
		pantallarecords.setVisible(false);
		
		
	}


	private void RegistrarEventos() {

		btnEmpezar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comenzar();

			}
		});
		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		btnInstrucciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	protected void mostrarRecords() {
		pantallamuerte.setVisible(false);
		juegomenu.setVisible(false);
		juegocarretera.setVisible(false);
		pantallarecords.setVisible(true);
		btnEmpezar.setVisible(false);
		btnInstrucciones.setVisible(false);
		btnSalir.setVisible(false);
	}

	public void finalJuego(){

		boolean fin;

		fin=juegocarretera.isFin();
		if(fin==true){
			music=true;
			fin=false;
			chocar=new Sonido("ChoqueCoche.wav");
			chocar.play();
			juegocarretera.setVisible(false);
			cronometro.stop();
			juegocarretera.restart();
			pantallamuerte.setVisible(true);		
			btnEmpezar.setVisible(true);
			btnInstrucciones.setVisible(true);
			btnSalir.setVisible(true);
			min=0;
			sec=0;
			temp=10;
			vel=10;
			juegocarretera.getCoche1().setVelocidad(vel);
			juegocarretera.getCoche2().setVelocidad(vel);
			juegocarretera.getCoche3().setVelocidad(vel);
			juegocarretera.getCoche4().setVelocidad(vel);
			juegocarretera.getCocheJugador().setPosY(530);
			gameover=new Sonido("gameOver.wav");
			gameover.play();
			musicaespera=new Timer(1000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(music==true){
						tiempo=tiempo+1;
						if(tiempo==2){
							musicafondomuerte=new Sonido("musicafondo.wav");
							musicafondomuerte.playLoop();
						}
					}
				}
			});
			musicaespera.start();
		}
	}

	public void comenzar(){
		music=false;
		gameover=new Sonido("gameOver.wav");
		gameover.detener();
		min=0;
		sec=0;
		vel=10;
		temp=10;
		tiempo=0;
		juegocarretera.registrarEventos();
		lblCronometro.setBounds(841, 30, 46, 14);
		lblCronometro.setText("00:00");
		juegomenu.setVisible(false);	
		btnEmpezar.setVisible(false);
		btnInstrucciones.setVisible(false);
		btnSalir.setVisible(false);
		juegocarretera.setVisible(true);
		tiempo();
		juegocarretera.registrarEventosInic();
		lblCronometro.setVisible(true);
		musicafondomenu.detener();
		musicafondomuerte.detener();
	}

	public void cerrar(){
		System.exit(0);
	}

	public void tiempo(){
		cronometro=new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sec=sec+1;

				if(sec == temp){
					vel=vel+5;
					juegocarretera.getCoche1().setVelocidad(vel);
					juegocarretera.getCoche2().setVelocidad(vel);
					juegocarretera.getCoche3().setVelocidad(vel);
					juegocarretera.getCoche4().setVelocidad(vel);
					temp=temp+10;
					if(temp >60){
						temp=0;
					}
				}

				if(sec==60){
					min=min+1;
					sec=0;
				}

				if( min < 10 && sec < 10 ) {
					lblCronometro.setText("0"+ min + ":0" + sec );
				}
				else if( min <10 && sec > 9){
					lblCronometro.setText("0"+ min + ":" + sec ); 
				}	
			}
		});
		cronometro.start();
	}	
}