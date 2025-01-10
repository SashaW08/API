import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;

public class API_Project {
    public static void main(String args[]) {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        // To print in JSON format.
        API_Project readingIsWhat = new API_Project();

    }

    private JFrame mainFrame;
    private JFrame firstmainframe;
    private JFrame foxmainframe;
    private JPanel searchpanel1;
    private JPanel biggerpanel;
    private JPanel infopanel;
    private JPanel infopanel1;
    private JPanel picturepanel;
    private JLabel imageLabel;
    private JPanel twobuttonpanel;
    private JPanel choosepanel;
    private JPanel foxmainpanel;
    private JPanel foxpicturepanel;
    private JLabel foximagelabel;
    private JPanel foxbiggerpanel;
    private JScrollPane scroll;
    private JTextArea tb;
    int WIDTH = 600;
    int HEIGHT = 700;
    int number = 0;
    private Font font = new Font("Times New Roman", Font.BOLD, 15);
    private Font font1 = new Font("Times New Roman", Font.BOLD, 18);
    private Color lightpurple = new Color (215, 197, 245);
    private Color lightgreen = new Color (193,225,193);
    private String urltags = "";
    private String foxlink ="";

    /** why does the code not work--what is wrong with the foxpull methodddddd**/

    public API_Project(){
        firstgui();
        firstshoweventdemo();
    }

    private void firstgui(){

        firstmainframe = new JFrame("Options");
        firstmainframe.setSize(WIDTH, HEIGHT);
        firstmainframe.setLayout(new BorderLayout());
        firstmainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        twobuttonpanel = new JPanel ();
        twobuttonpanel.setLayout(new GridLayout(2,1));
        choosepanel = new JPanel ();
        choosepanel.setLayout(new BorderLayout());

        firstmainframe.setVisible(true);
    }

    public void firstshoweventdemo(){

        JButton catbutton = new JButton("Cats");
        JButton foxbutton = new JButton("Foxes");
        JLabel chooselabel = new JLabel("Choose an animal:", JLabel.CENTER);

        catbutton.setActionCommand("Cats");
        catbutton.addActionListener(new ButtonClickListener());
        foxbutton.setActionCommand("Foxes");
        foxbutton.addActionListener(new ButtonClickListener());

        catbutton.setFont(font);
        foxbutton.setFont(font);
        chooselabel.setFont(font);
        catbutton.setBackground(lightpurple);
        foxbutton.setBackground(lightpurple);
        chooselabel.setOpaque(true);
        chooselabel.setBackground(lightgreen);

        firstmainframe.add(choosepanel);
        choosepanel.add(chooselabel, BorderLayout.NORTH);
        choosepanel.add(twobuttonpanel, BorderLayout.CENTER);
        twobuttonpanel.add(catbutton);
        twobuttonpanel.add(foxbutton);

        firstmainframe.setVisible(true);

    }

    private void foxgui(){

        foxmainframe = new JFrame("Foxes");
        foxmainframe.setSize(WIDTH, HEIGHT);
        foxmainframe.setLayout(new BorderLayout());
        foxmainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        foxmainpanel = new JPanel ();
        foxmainpanel.setLayout(new BorderLayout());
        foxpicturepanel = new JPanel ();
        foxpicturepanel.setLayout(new BorderLayout());
        foxbiggerpanel = new JPanel();
        foxbiggerpanel.setLayout(new BorderLayout());

        foxmainframe.setVisible(true);

    }

    public void foxshoweventdemo(){

        JButton foxnextbutton = new JButton("Next fox");
        JButton foxbackbutton = new JButton("Back to animal options");
        JLabel foxlabel = new JLabel("Fox image:", JLabel.CENTER);

        foxnextbutton.setActionCommand("Next fox");
        foxnextbutton.addActionListener(new ButtonClickListener());
        foxbackbutton.setActionCommand("Back to animal options");
        foxbackbutton.addActionListener(new ButtonClickListener());

        foxnextbutton.setFont(font);
        foxbackbutton.setFont(font);
        foxlabel.setFont(font);
        foxnextbutton.setBackground(lightpurple);
        foxbackbutton.setBackground(lightpurple);
        foxlabel.setOpaque(true);
        foxlabel.setBackground(lightgreen);

        foxmainframe.add(foxbiggerpanel);
        foxbiggerpanel.add(foxmainpanel, BorderLayout.CENTER);
        foxbiggerpanel.add(foxbackbutton, BorderLayout.SOUTH);
        foxmainpanel.add(foxlabel, BorderLayout.NORTH);
        foxmainpanel.add(foxnextbutton, BorderLayout.SOUTH);
        foxmainpanel.add(foxpicturepanel, BorderLayout.CENTER);

        foxmainframe.setVisible(true);
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Cats!");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        picturepanel  = new JPanel();
        picturepanel.setLayout(new GridLayout(1,1));
        infopanel1 = new JPanel();
        infopanel1.setLayout(new BorderLayout());
        biggerpanel = new JPanel();
        biggerpanel.setLayout(new BorderLayout());
        infopanel = new JPanel();
        infopanel.setLayout(new GridLayout(1,1));
        tb = new JTextArea();
        tb.setFont(font1);
        scroll = new JScrollPane(tb);
        searchpanel1 = new JPanel();
        searchpanel1.setLayout(new GridLayout(1,1));

        mainFrame.setVisible(true);
    }

    public void showEventDemo(){

        JButton button1 = new JButton("Next");
        JButton button2 = new JButton("Previous");
        JButton button3 = new JButton("Back to animal options");
        JLabel label1 = new JLabel ("Cat info:", JLabel.CENTER);

        button1.setActionCommand("Next");
        button1.addActionListener(new ButtonClickListener());
        button2.setActionCommand("Previous");
        button2.addActionListener(new ButtonClickListener());
        button3.setActionCommand("Back to animal options");
        button3.addActionListener(new ButtonClickListener());

        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        label1.setFont(font);

        button1.setBackground(lightpurple);
        button2.setBackground(lightpurple);
        button3.setBackground(lightpurple);
        label1.setOpaque(true);
        label1.setBackground(lightgreen);

        mainFrame.add(searchpanel1, BorderLayout.NORTH);
        searchpanel1.add(button2);
        searchpanel1.add(button1);
        mainFrame.add(biggerpanel);
        biggerpanel.add(infopanel1, BorderLayout.NORTH);
        biggerpanel.add(picturepanel);
        infopanel1.add(infopanel, BorderLayout.CENTER);
        infopanel1.add(label1, BorderLayout.NORTH);
        biggerpanel.add(button3, BorderLayout.SOUTH);
        infopanel.add(scroll);

        mainFrame.setVisible(true);
    }

    private void addImage() throws IOException {
        try {

            String path = "https://cataas.com/cat/"+urltags;
            int bobby = path.lastIndexOf(",");
            String newpath = path.substring(0,bobby);
            System.out.println(path);
            URL url = new URL(newpath);
            BufferedImage ErrorImage = ImageIO.read(new File("Error.jpg"));
            BufferedImage inputImageBuff = ImageIO.read(url.openStream());

            double errorimagewidth = ErrorImage.getWidth();
            double errorimageheight = ErrorImage.getHeight();
            double inputimagebuffwidth = inputImageBuff.getWidth();
            double inputimagebuffheight = inputImageBuff.getHeight();

            double errorratio = (errorimagewidth/errorimageheight);
            double inputimageratio = (inputimagebuffwidth/inputimagebuffheight);

            int newheighterrorimage = 504;
            int newheightinputimagebuff = 504;
            int newwidtherrorimage = (int) Math.round((newheighterrorimage)*(errorratio));
            int newinputimagewidth = (int) Math.round((newheightinputimagebuff)*(inputimageratio));
            System.out.println("height: "+ newheightinputimagebuff);
            System.out.println("width: "+ newinputimagewidth);

            ImageIcon inputImage;
            if (inputImageBuff != null) {
                inputImage = new ImageIcon(inputImageBuff.getScaledInstance(newinputimagewidth, newheightinputimagebuff, Image.SCALE_SMOOTH));

                if (inputImage != null) {
                    imageLabel = new JLabel(inputImage);
                } else {
                    imageLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(newwidtherrorimage,newheighterrorimage, Image.SCALE_SMOOTH)));

                }
                picturepanel.removeAll();
                picturepanel.repaint();

                picturepanel.add(imageLabel);
                biggerpanel.add(picturepanel);

            }
            else{
                imageLabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(600, 300, Image.SCALE_SMOOTH)));
            }

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("sadness");
            BufferedImage ErrorImage = ImageIO.read(new File("Error.jpg"));
            JLabel imageLabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));

            picturepanel.removeAll();
            picturepanel.repaint();
            picturepanel.add(imageLabel);
            biggerpanel.add(picturepanel);
        }

        System.out.println("image label height: "+picturepanel.getHeight());

        urltags = "";

        mainFrame.setVisible(true);
    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {

            URL url = new URL("https://cataas.com/api/cats?tags=cute");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();

        try {

            org.json.simple.JSONArray jsonmainarray = (org.json.simple.JSONArray) parser.parse(totlaJson);
            JSONObject object = (JSONObject) jsonmainarray.get(number);

            tb.append("ID: "+object.get("_id")+"\n");
            tb.append("Size: "+object.get("size")+"\n");

            org.json.simple.JSONArray tags = (org.json.simple.JSONArray) object.get("tags");
            int d = tags.size();
            for (int k = 0; k < d; k++) {
                String tags1 = (String) tags.get(k);
                if(k==0) {
                    tb.append("Tags: " + tags1);
                }else{
                    tb.append(", "+tags1);
                }
                urltags= urltags+tags1+",";
            }
            System.out.println(urltags);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void foxaddimage() throws IOException {

        try {

            //HOW I FIXED THE 403 ERROR
            //https://stackoverflow.com/questions/41069457/403-error-when-trying-to-access-an-image-url

            URL url = new URL(foxlink);

            URLConnection openConnection = url.openConnection();
            openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            openConnection.connect();

            BufferedImage img = null;

            InputStream in = new BufferedInputStream(openConnection.getInputStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            img = ImageIO.read(new ByteArrayInputStream(response));

            System.out.println(foxlink);

            BufferedImage ErrorImage = ImageIO.read(new File("Error.jpg"));
            System.out.println(url);
            BufferedImage inputImageBuff = img;


            double foxratio = inputImageBuff.getHeight()/inputImageBuff.getWidth();

            System.out.println(inputImageBuff.getHeight());
            System.out.println(inputImageBuff.getWidth());

            System.out.println(foxratio);
            int b = (int)(570*foxratio);



            ImageIcon inputImage;
            if (inputImageBuff != null) {
                inputImage = new ImageIcon(inputImageBuff.getScaledInstance(b, 570, Image.SCALE_SMOOTH));

                if (inputImage != null) {
                    foximagelabel = new JLabel(inputImage);
                } else {
                    foximagelabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

                }
                foxpicturepanel.removeAll();
                foxpicturepanel.repaint();

                foxpicturepanel.add(foximagelabel);
                foxmainpanel.add(foxpicturepanel);

            }
            else{
                foximagelabel =new JLabel(new ImageIcon(ErrorImage.getScaledInstance(600, 300, Image.SCALE_SMOOTH)));
            }

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("sadness");
            BufferedImage ErrorImage = ImageIO.read(new File("Error.jpg"));
            JLabel foximagelabel = new JLabel(new ImageIcon(ErrorImage.getScaledInstance(800, 589, Image.SCALE_SMOOTH)));

            foxpicturepanel.removeAll();
            foxpicturepanel.repaint();
            foxpicturepanel.add(foximagelabel);
            foxmainpanel.add(foxpicturepanel);
        }

        foxmainframe.setVisible(true);

    }

    public void foxpull() throws ParseException {

        String output = "abc";
        String foxtotlaJson = "";

        try {

            URL url = new URL("https://randomfox.ca/floof/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0"); // Add User-Agent
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                foxtotlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();

        try {

            org.json.simple.JSONObject foxjsonobject = (org.json.simple.JSONObject) parser.parse(foxtotlaJson);

            foxlink = (String) foxjsonobject.get("image");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.equals("Back to animal options")){
                firstgui();
                firstshoweventdemo();
            }

            if(command.equals("Cats")){
                prepareGUI();
                showEventDemo();

                try {
                    pull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    addImage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

            if(command.equals("Foxes")){
                foxgui();
                foxshoweventdemo();

                try {
                    foxpull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    foxaddimage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(command.equals("Next fox")){
                try {
                    foxpull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    foxaddimage();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if (command.equals("Next")) {

                tb.setText("");

                number = number+1;

                if(number>=10){
                    tb.setText("No more cats this way! Hit 'Previous' button");
                }

                System.out.println(number);

                if(number!=10) {

                    try {
                        pull();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        addImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            if(command.equals("Previous")){

                tb.setText("");
                number=number-1;

                if(number<=-1){
                    tb.setText("No more cats this way! Hit 'Next' button");
                }

                System.out.println(number);

                if(number!=-1) {

                    try {
                        pull();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        addImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
}