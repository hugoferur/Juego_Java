import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class JuegoMenu extends Canvas {

	private Principal principal;
	private Timer relojInicial;
	private Coche coche1, coche2, coche3, coche4;
	private Image buffer;
	private Graphics pantVirtual;
	private int pos;
	private Timer reloj;
	private Image fondo;
	private Image fondoaux;
	private int cont=0;
	private int carrilUltCoche;
	private Image semaforo;
	private Image roadtohell;

	public JuegoMenu(Principal principal) {
		pos=0;
		setBounds(0, 0, 840, 650);
		this.principal=principal;
		fondo=Toolkit.getDefaultToolkit().getImage("./images/carretera.png");
		fondoaux=Toolkit.getDefaultToolkit().getImage("./images/carretera.png");
		semaforo=Toolkit.getDefaultToolkit().getImage("./images/semaforo.png");
		roadtohell=Toolkit.getDefaultToolkit().getImage("./images/RoadToHell.png").getScaledInstance(110, 110, Image.SCALE_SMOOTH);

		coche1=new Coche(200, -80, 50, 80);
		coche2=new Coche(330, -80, 50, 80);
		coche3=new Coche(460, -80, 50, 80);
		coche4=new Coche(590, -80, 50, 80);

		registrarEventosInic();
	}

	public int getCarrilUltCoche() {
		return carrilUltCoche;
	}

	public void setCarrilUltCoche(int carrilUltCoche) {
		this.carrilUltCoche = carrilUltCoche;
	}
	public Image getFondo() {
		return fondo;
	}

	public void setFondo(Image fondo) {
		this.fondo = fondo;
	}

	public void registrarEventosInic() {
		reloj=new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pos=pos+5;
			}
		});
		reloj.start();
	}


	public void registrarEventos(){
		relojInicial=new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				requestFocus();
				coche1.moverVert();
				if(coche1.getPosY()==-100){
					carrilUltCoche=coche1.getCarril();

				}
				if(cont>30){
					coche2.moverVert();
					if(coche2.getPosY()==-100){
						carrilUltCoche=coche2.getCarril();
					}
				}
				if(cont>60){
					coche3.moverVert();
					if(coche3.getPosY()==-100){
						carrilUltCoche=coche3.getCarril();
					}
				}
				if(cont>90){
					coche4.moverVert();
					if(coche4.getPosY()==-100){
						carrilUltCoche=coche4.getCarril();
					}
				}
				cont++;
				repaint();	
			}
		});	
		relojInicial.start();
	}


	public void paint(Graphics g) {
		super.paint(g);

		if(pos==0){
			g.drawImage(fondo, 0, pos, 840, 650, this);
		}else {
			if(pos>650){
				pos=0;
			}
			g.drawImage(fondo, 0, pos, 840, 650, this);
			g.drawImage(fondo, 0, pos-650, 840, 650, this);
		}

		coche1.dibujar(g);
		coche2.dibujar(g);
		coche3.dibujar(g);
		coche4.dibujar(g);
		g.drawImage(semaforo, 0 , 50, 400, 400, this);
		g.drawImage(roadtohell, 300, 70, 500, 400, this);
	}
	//}
	@Override
	public void update(Graphics g){
		buffer=createImage(840, 650);
		pantVirtual=buffer.getGraphics();
		paint(pantVirtual);
		g.drawImage(buffer, 0, 0, 840, 650, null);
	}

}