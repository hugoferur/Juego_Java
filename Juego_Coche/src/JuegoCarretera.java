import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class JuegoCarretera extends Canvas {



	private Principal principal;
	private Timer relojInicial;
	private Coche coche1, coche2, coche3, coche4;
	private CocheJugador cocheJugador;
	private boolean fin=false;
	private Image buffer;
	private Graphics pantVirtual;
	private int pos;
	private Timer reloj;
	private boolean juegoIniciado;
	private Image fondo;
	private Image fondoaux;
	private int cont=0;
	private int carrilUltCoche=-1;
	private int carrilUltCocheaux=-1;
	private int contPuntos=0;




	public JuegoCarretera(Principal principal) {
		pos=0;
		juegoIniciado=false;
		setBounds(0, 0, 840, 650);
		this.principal=principal;
		fondo=Toolkit.getDefaultToolkit().getImage("./images/carretera.png");
		fondoaux=Toolkit.getDefaultToolkit().getImage("./images/carretera.png");

		coche1=new Coche(200, -80, 50, 80);
		coche2=new Coche(330, -80, 50, 80);
		coche3=new Coche(460, -80, 50, 80);
		coche4=new Coche(590, -80, 50, 80);

		cocheJugador=new CocheJugador(330, 530,50 ,80);

	}

	public CocheJugador getCocheJugador() {
		return cocheJugador;
	}

	public void setCocheJugador(CocheJugador cocheJugador) {
		this.cocheJugador = cocheJugador;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Coche getCoche1() {
		return coche1;
	}

	public void setCoche1(Coche coche1) {
		this.coche1 = coche1;
	}

	public Coche getCoche2() {
		return coche2;
	}

	public void setCoche2(Coche coche2) {
		this.coche2 = coche2;
	}

	public Coche getCoche3() {
		return coche3;
	}

	public void setCoche3(Coche coche3) {
		this.coche3 = coche3;
	}

	public Coche getCoche4() {
		return coche4;
	}

	public void setCoche4(Coche coche4) {
		this.coche4 = coche4;
	}

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public int getCarrilUltCoche() {
		return carrilUltCoche;
	}

	public void setCarrilUltCoche(int carrilUltCoche) {
		this.carrilUltCoche = carrilUltCoche;
	}

	public boolean isJuegoIniciado() {
		return juegoIniciado;
	}

	public void setJuegoIniciado(boolean juegoIniciado) {
		this.juegoIniciado = juegoIniciado;
	}

	public Image getFondo() {
		return fondo;
	}

	public void setFondo(Image fondo) {
		this.fondo = fondo;
	}

	public void moverCocheJugador(){
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				cocheJugador.mover(e.getKeyCode());
			}
		});
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
				Rectangle rect1, rect2, rect3, rect4, rect5;
				requestFocus();
				moverCoches();

				contPuntos=contPuntos+50;
				rect1=coche1.getRectangle();
				rect2=cocheJugador.getRectangle();
				rect3=coche2.getRectangle();
				rect4=coche3.getRectangle();
				rect5=coche4.getRectangle();

				if(rect2.intersects(rect1) || rect2.intersects(rect3) || rect2.intersects(rect4) || rect2.intersects(rect5)){
					relojInicial.stop();
					reloj.stop();
					fin=true;
					principal.finalJuego();
				}
				cont++;
				repaint();	
			}
		});	
		relojInicial.start();
	}

	public void moverCoches(){
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
				carrilUltCoche=coche2.getCarril();
			}
		}

		//-------------------------------INTENTO DE QUE NO SE REPITAN LOS CARRILES EN DOS COCHES SEGUIDOS--------------------------------------------------------
		/*coche1.moverVert();
		carrilUltCocheaux=coche2.getCarril();
		while(carrilUltCoche==carrilUltCocheaux){
			coche1.moverVert();
		}
		carrilUltCoche=carrilUltCocheaux;
		if(coche1.getPosY()==-100){
			carrilUltCoche=coche1.getCarril();
		}

		if(cont>30){
			coche2.moverVert();
			carrilUltCocheaux=coche2.getCarril();
			while(carrilUltCoche==carrilUltCocheaux){
				coche2.moverVert();
			}
			carrilUltCoche=carrilUltCocheaux;
			if(coche2.getPosY()==-100){
				carrilUltCoche=coche2.getCarril();
			}
		}
		if(cont>60){
			coche3.moverVert();
			carrilUltCocheaux=coche3.getCarril();
			while(carrilUltCoche==carrilUltCocheaux){
				coche3.moverVert();
			}
			carrilUltCoche=carrilUltCocheaux;

			if(coche3.getPosY()==-100){
				carrilUltCoche=coche3.getCarril();
			}
		}
		if(cont>90){
			coche4.moverVert();
			carrilUltCocheaux=coche4.getCarril();
			while(carrilUltCoche==carrilUltCocheaux){
				coche4.moverVert();
			}
			carrilUltCoche=carrilUltCocheaux;

			if(coche4.getPosY()==-100){
				carrilUltCoche=coche4.getCarril();
			}
		}*/
		//---------------------------------------------------------------------------------------------------------------------------------------
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

		cocheJugador.dibujar(g);
		coche1.dibujar(g);
		coche2.dibujar(g);
		coche3.dibujar(g);
		coche4.dibujar(g);
	}

	@Override
	public void update(Graphics g){
		buffer=createImage(840, 650);
		pantVirtual=buffer.getGraphics();
		paint(pantVirtual);
		g.drawImage(buffer, 0, 0, 840, 650, null);
	}

	public void restart(){
		relojInicial.stop();
		reloj.stop();
		cont=0;
		coche1.setPosY(-80);
		coche2.setPosY(-80);
		coche3.setPosY(-80);
		coche4.setPosY(-80);
		cocheJugador.setPosX(330);
	}
}