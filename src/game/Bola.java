package game;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.
public class Bola extends DesenhoAnimado {
	
	private int loopStatus = 0;
	private Thread t = new Thread(this);

	public Bola() {}
	
	public Bola(int x, int y, String path) {
		super(x, y, path);
		t.start();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true) {
			switch(loopStatus) {
				case 0:
					this.setX(this.getX() + 10);
					break;
				case 1:
					this.setY(this.getY() + 10);
					break;
				case 2:
					this.setX(this.getX() - 10);
					break;
				case 3:
					this.setY(this.getY() - 10);
					break;
			}
			
			try {
				t.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			loopStatus++;
			if(loopStatus == 4) loopStatus = 0;
		}
	}

}