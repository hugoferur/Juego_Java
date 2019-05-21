public class Forma {

	private int alto, ancho;
	private int posX, posY;

	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Forma(){
		this.posX=0;
		this.posY=0;
		this.ancho=10;
		this.alto=10;
	}
	public Forma(int posX, int posY, int ancho, int alto){
		this.posX=posX;
		this.posY=posY;
		this.ancho=ancho;
		this.alto=alto;
	}

}