package Panzer;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Button {

		private int x;
		private int y;
		private int width;
		private int height;
		
		private boolean enabled;
		private boolean pressed;
		
		private String text;
		private final Font font = new Font("Verdana", Font.PLAIN, 14);
		private ActionListener listener;
		
		
		public Button(ActionListener listener, String text, int x, int y, int width, int height) {
			
			this.listener=listener;
			this.text=text;
			this.x=x;
			this.y=y;
			this.width=width;
			this.height=height;
			enabled=true;
		}
		
		public void render(Graphics2D g) {
			if(pressed) {
				g.setColor(Color.BLUE);
			} else {
				g.setColor(Color.YELLOW);
			}
			
			if(enabled) {
				g.fillRect(x,  y, width, height);
				g.setFont(font);
				g.setColor(Color.BLACK);
				int stringWidth = g.getFontMetrics().stringWidth(text);
				g.drawString(text, x + width / 2 - stringWidth / 2, y + height / 2);
			}
		}
		
		private boolean isPressed(int x, int y) {
			return x >= this.x && x <= this.x + width
					&& y >= this.y && y <= this.y +height;
		}
		
		public void mousePressed(MouseEvent e) {
			if (isPressed(e.getX(), e.getY())) {
				pressed = true;
			}
		}
		
		public void mouseReleased(MouseEvent e) {
			if(pressed && enabled) {
				listener.ActionPerformed(new ActionEvent(this));
				pressed = false;
			}
		}
}
