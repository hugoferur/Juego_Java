import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Coche extends Forma {

	private Image img;
	private int dirH;
	private int dirV;
	private int velocidad;
	private ArrayList<Image> cochesEnemigos;
	private int i;
	private int posicion[];
	private int carril;
	private JuegoCarretera juegocarretera;
	private Principal principal;


	public int getCarril() {
		return carril;
	}

	public void setCarril(int carril) {
		this.carril = carril;
	}

	public int getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


	public Coche(){
		super();

		dirH=0;
		dirV=1;		
		velocidad=10;
		posicion=new int[4];
		posicion[0]=200;
		posicion[1]=330;
		posicion[2]=460;
		posicion[3]=590;
	}


	public Coche(int posX, int posY, int ancho, int alto){
		super(posX, posY, ancho, alto);

		Random r;
		r=new Random();
		cochesEnemigos = new ArrayList<>();
		for(i=0;i<6;i++){
			cochesEnemigos.add(Toolkit.getDefaultToolkit().getImage("./images/coche"+(i+1)+".png"));
		}
		img=cochesEnemigos.get(r.nextInt(5));
		dirH=0;
		dirV=1;
		velocidad=10;
		posicion=new int[4];
		posicion[0]=200;
		posicion[1]=330;
		posicion[2]=460;
		posicion[3]=590;
	}


	public Rectangle getRectangle(){
		Rectangle rect;
		rect = new Rectangle();
		rect.setBounds(getPosX(), getPosY(), getAncho(), getAlto());
		return rect;
	}

	public void dibujar(Graphics g){

		g.drawImage(img, getPosX(), getPosY(), getAncho(), getAlto(), null);
	}

	public void moverVert(){
		Random r;
		r=new Random();

		carril = r.nextInt(4);
		int posicionX= 0;
		int carrilUltCoche=0;

		setPosY(getPosY()+velocidad*dirV);

		if(getPosY()>700){
			setPosY(-100);
			if(getPosX()==200){
				carrilUltCoche=0;
			}else if(getPosX()==330){
				carrilUltCoche=1;
			}else if(getPosX()==460){
				carrilUltCoche=2;
			}else if(getPosX()==590){
				carrilUltCoche=3;
			}

			while(carrilUltCoche == carril){
				carril = r.nextInt(4);
			}

			posicionX=posicion[carril];

			if(getPosY()<700){		
				setPosX(posicionX);

			}

		}
	}
}