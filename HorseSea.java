package HorseSea;
import java.awt.event.*;
import javax.swing.*;
public class HorseSea extends Game {
	/* Cá ngựa */
	private int color;
	private int id, rank = Destination.NO_RANK;
	private int position;// Vị trí cá ngựa trong mảng.
	private JLabel label = new JLabel();
	static final int FINISH_POSITION = 56;

	HorseSea(int color, int id) {
		this.color = color;
		this.id = id;

		switch (color) {
		case BLUE:
			position = 0;
			break;
		case RED:
			position = 42;
			break;
		case YELLOW:
			position = 14;
			break;
		case GREEN:
			position = 28;
			break;
		default:
			System.err.println("Không có người chơi màu " + color);
		}
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public int getId() {
		return id;
	}

	public int getColor() {
		return color;
	}

	public void toFinish() {
		position = FINISH_POSITION;
	}

	public boolean move(GameMap map, int steps) {
		if (map.setMap(color, id, position, steps)) {
			if (position != FINISH_POSITION) {
				position = (position + steps) % GameMap.NUMBER_NODE;
			}

			return true;
		}

		return false;
	}

	public boolean changeRank(Destination destination, int steps) {
		if (destination.setDestination(rank, steps, this)) {
			return true;
		}

		return false;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setIcon(final Icon icon[]) {
		label.setIcon(icon[color]);
	}

	public void addMouseListener(GameMap map, int steps) {
		if (label != null) {
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (horsePhaseFlag) {
						if (position == FINISH_POSITION) {
							if(changeRank(map.getPlayer()[color].destination, steps)){
								horsePhaseFlag = false;
								horsePhaseSema.release();
							}
						} else if (move(map, steps)) {
							horsePhaseFlag = false;
							horsePhaseSema.release();
						}
					}
				}
			});
		}
	}

	public void removeMouseListener() {
		if (label != null) {
			MouseListener list[] = label.getMouseListeners();
			for (int i = 0; i < list.length; i++) {
				label.removeMouseListener(list[i]);
			}
		}
	}
}