
package GUI;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;


public class Panel_funct extends JPanel{

    String path = "D:\\JavaProject\\QLNS\\src\\images\\";

    ImageIcon img;
    JLabel lb_img;
    JButton btn;
    public Panel_funct(String img_path, String lab){
        btn = new JButton(lab);
        img = new ImageIcon(path+img_path);
        lb_img = new JLabel(img);
        lb_img.setMaximumSize(new Dimension(Integer.MAX_VALUE, lb_img.getPreferredSize().height));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn.getPreferredSize().height));

       
        this.add(lb_img);
        this.add(btn);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new DimensionUIResource(200, 400));     
    }
    public JButton get_btn(){
        return btn;
    }
            
}
