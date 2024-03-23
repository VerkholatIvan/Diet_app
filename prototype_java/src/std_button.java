import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class std_button extends JButton implements MouseListener {

    protected Color default_color;
    protected Color active_color;

    public std_button(){}


    public std_button(String name,Color color1,Color color2,int width,int height,int font_size){

        super(name);
        this.default_color=color1;
        this.active_color=color2;
        this.setBackground(color1);
        this.setFont(new Font("Avenir",Font.PLAIN,font_size));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        this.setPreferredSize(new Dimension(width,height));
        addMouseListener(this);


    }

    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==this){
            this.setBackground(this.active_color);
            this.repaint();
            //System.out.println(this.getBackground());//test
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==this){
            this.setBackground(this.default_color);
            this.repaint();
            //System.out.println(this.getBackground());//test
        }
    }


}