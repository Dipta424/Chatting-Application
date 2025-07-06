import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client implements ActionListener {
    JTextField text;
    static JPanel p2;
    static DataOutputStream dout;
    static DataInputStream din;
    static Box vertical = Box.createVerticalBox();
    static JFrame f=new JFrame();

    Client() {
        f.setLayout(null); //ignoring default layouts
        JPanel p1 = new JPanel(); // new panel above the frame

        p1.setBackground(new Color(7, 94, 84)); // background color of the panel
        p1.setBounds(0, 0, 450, 70); // default layout null so need to set custom layout
        p1.setLayout(null); // for further action in the panel

        f.add(p1);  //adding panel to the frame


        //adding image to the panel
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));

        //resizing image
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);

        // jlabel only takes imageicon as argument
        ImageIcon i3 = new ImageIcon(i2);

        JLabel back = new JLabel(i3);

        //setting bounds for image
        back.setBounds(5, 20, 25, 25);

        //adding label image to the panel
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        //adding image to the panel
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/rohan.png"));

        //resizing image
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);

        // jlabel only takes imageicon as argument
        ImageIcon i6 = new ImageIcon(i5);

        JLabel profile = new JLabel(i6);

        //setting bounds for image
        profile.setBounds(40, 10, 50, 50);

        //adding label image to the panel
        p1.add(profile);


        //adding image to the panel
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));

        //resizing image
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

        // jlabel only takes imageicon as argument
        ImageIcon i9 = new ImageIcon(i8);

        JLabel video = new JLabel(i9);

        //setting bounds for image
        video.setBounds(300, 20, 30, 30);

        //adding label image to the panel
        p1.add(video);


        //adding image to the panel
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));

        //resizing image
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

        // jlabel only takes imageicon as argument
        ImageIcon i12 = new ImageIcon(i11);

        JLabel phone = new JLabel(i12);

        //setting bounds for image
        phone.setBounds(360, 20, 30, 30);

        //adding label image to the panel
        p1.add(phone);

        //name label adding
        JLabel name = new JLabel("Rohan");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        //status label adding
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 14);
        status.setForeground(Color.white);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        //adding message panel
        p2 = new JPanel();
        p2.setBounds(5, 75, 440, 570);
        f.add(p2);

        //adding message field
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        f.add(text);

        //adding send button beside the text field

        JButton button = new JButton("Send");
        button.setBounds(320, 655, 123, 40);
        //button.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        button.setForeground(Color.white);
        button.setBackground(new Color(7, 94, 84));
        button.addActionListener(this);

        f.add(button);


        //adding image to the panel
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));

        //resizing image
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);

        // jlabel only takes imageicon as argument
        ImageIcon i15 = new ImageIcon(i14);

        JLabel dot = new JLabel(i15);

        //setting bounds for image
        dot.setBounds(420, 20, 10, 25);

        //adding label image to the panel
        p1.add(dot);


        f.setSize(450, 700); // frame size
        f.setLocation(800, 50);   //frame location on screen
        f.setUndecorated(true);     //removing box panel above the title panel

        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String out = text.getText();
            JPanel p = formateLabel(out);

            p2.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());  // creating new panel with border layout

            right.add(p, BorderLayout.LINE_END);

            vertical.add(right); // for multiple message the vertical allignment
            vertical.add(Box.createVerticalStrut(15)); // vertical distance

            p2.add(vertical, BorderLayout.PAGE_START); // adding vertical box to the panel p2 at the start of the page
            dout.writeUTF(out); //sending message to the server

            text.setText(" ");  // this will empty the message box after sending the message

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception ae)
        {
            ae.printStackTrace();
        }


    }

    public static JPanel formateLabel(String out) {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style = \"width: 150px\">" + out + "</p></html>");

        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);

        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);


        return panel;
    }

    public static void main(String[] args)
    {
        new Client();
        try{
            Socket skt2 = new Socket("127.0.0.1", 6001); // creating socket
            din=new DataInputStream(skt2.getInputStream()); // input messages
            dout=new DataOutputStream(skt2.getOutputStream());// output messages

            while(true)
            {
                p2.setLayout(new BorderLayout());
                String msg= din.readUTF();
                JPanel panel=formateLabel(msg);

                JPanel left=new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                p2.add(vertical, BorderLayout.PAGE_START);
                f.validate();
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
