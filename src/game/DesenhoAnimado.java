package game;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERA�O REALIZADA DEVE OBEDECER A HIERARQUIA
//ELA DEVE SER UTILIZADA NA HERAN�A DE DESENHOS QUE POSSAM SER MOVIMENTADOS (ANIMADOS) DE FORMA AUTOM�TICA
public abstract class DesenhoAnimado extends Desenho implements Runnable {

	public DesenhoAnimado() {}
	
	public DesenhoAnimado(int x, int y, String path) {
		super(x, y, path);
	}

}