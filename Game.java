package HorseSea;
import javax.swing.JOptionPane;
import java.util.concurrent.Semaphore;

public class Game {
	/* Lớp cha của tất cả đối tượng trong game */

	static final int BLUE = 1;
	static final int RED = 4;
	static final int YELLOW = 2;
	static final int GREEN = 3;
	static final int DISTANCE = 45;// Khoảng cách giữa 2 nốt cá ngựa // Can sua

	/* Các cờ đánh dấu các giai đoạn trong game */
	static boolean diePhaseFlag = true; // Giai đoạn tung xúc xắc
	static boolean horsePhaseFlag = false; // Giai đoạn đi quân
	static Semaphore diePhaseSema = new Semaphore(0);
	static Semaphore horsePhaseSema = new Semaphore(0);

	public void showError(String error) {
		JOptionPane.showMessageDialog(null, error);
	}

	public void sleep(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}