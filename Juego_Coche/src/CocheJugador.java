import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class CocheJugador extends Forma{

	private Image img;
	private int dirH;
	private int dirV;
	private int velocidad;
	private ArrayList<Image> cochesEnemigos;

	public CocheJugador() {
		super();
		img=Toolkit.getDefaultToolkit().getImage("./images/cocheRojo.png");
		dirH=0;
		dirV=1;
		velocidad=10;
	}

	public CocheJugador(int posX, int posY, int ancho, int alto){
		super(posX, posY, ancho, alto);
		img=Toolkit.getDefaultToolkit().getImage("./images/cocheRojo.png");
		dirH=0;
		dirV=1;
		velocidad=10;
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

	public void mover(int tecla){
		if(tecla==KeyEvent.VK_UP || tecla==KeyEvent.VK_W){
			setPosY(getPosY()-5);
			if(getPosY()<300){
				setPosY(300);
			}
		}
		if(tecla==KeyEvent.VK_DOWN || tecla==KeyEvent.VK_S){
			setPosY(getPosY()+5);
			if(getPosY()>565){
				setPosY(565);
			}
		}
		if(tecla==KeyEvent.VK_LEFT || tecla==KeyEvent.VK_A){
			setPosX(getPosX()-130);
			if(getPosX()<200){
				setPosX(200);
			}
		}
		if(tecla==KeyEvent.VK_RIGHT || tecla==KeyEvent.VK_D){
			setPosX(getPosX()+130);
			if(getPosX()>590){
				setPosX(590);
			}
		}	
	}
}