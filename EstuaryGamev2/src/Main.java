
public class Main {
	
	public static void main(String[] args) {
		Controller controller = new Controller(new Model(), new foodPanel());
		(new Thread(controller)).start();
	}

}
