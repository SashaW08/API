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

public class API_Project {
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        // To print in JSON format.
        API_Project readingIsWhat = new API_Project();
        readingIsWhat.showEventDemo();
        try {
            readingIsWhat.addImage(); //calling this before????
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JFrame mainFrame;
    private JPanel searchpanel1;
    private JPanel biggerpanel;
    private JPanel infopanel;
    private JPanel infopanel1;
    private JPanel picturepanel;
    private JLabel imageLabel;
    private JScrollPane scroll;
    private JTextArea tb;
    int WIDTH = 550;
    int HEIGHT = 700;
    int number = 0;
    private Font font = new Font("Times New Roman", Font.BOLD, 15);
    private Font font1 = new Font("Times New Roman", Font.BOLD, 18);
    private Color lightpurple = new Color (215, 197, 245);
    private Color lightgreen = new Color (193,225,193);
    private String urltags = "";

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
        biggerpanel.setLayout(new GridBagLayout());
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
        JLabel label1 = new JLabel ("Cat info:", JLabel.CENTER);

        button1.setActionCommand("Next");
        button1.addActionListener(new ButtonClickListener());
        button2.setActionCommand("Previous");
        button2.addActionListener(new ButtonClickListener());

        button1.setFont(font);
        button2.setFont(font);
        label1.setFont(font);

        button1.setBackground(lightpurple);
        button2.setBackground(lightpurple);
        label1.setOpaque(true);
        label1.setBackground(lightgreen);

        mainFrame.add(searchpanel1, BorderLayout.NORTH);
        searchpanel1.add(button2);
        searchpanel1.add(button1);
        mainFrame.add(biggerpanel);

        /**figure out what to do about the stuff below and how to make it work**/

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        biggerpanel.add(infopanel1, c);
        c.gridheight=2;
        biggerpanel.add(picturepanel, c);

        /**end**/

        infopanel1.add(infopanel, BorderLayout.CENTER);
        infopanel1.add(label1, BorderLayout.NORTH);
        infopanel.add(scroll);

        mainFrame.setVisible(true);
    }

    public API_Project(){

        prepareGUI();

        try {
            pull();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addImage() throws IOException {
        try {

            String path = "https://cataas.com/cat/"+urltags;
            int bobby = path.lastIndexOf(",");
            String newpath = path.substring(0,bobby);

            URL url = new URL(newpath);
            BufferedImage ErrorImage = ImageIO.read(new File("Error.jpg"));
            BufferedImage inputImageBuff = ImageIO.read(url.openStream());

            double errorimagewidth = ErrorImage.getWidth();
            double errorimageheight = ErrorImage.getHeight();
            double inputimagebuffwidth = inputImageBuff.getWidth();
            double inputimagebuffheight = inputImageBuff.getHeight();

            double errorratio = (errorimagewidth/errorimageheight);
            double inputimageratio = (inputimagebuffwidth/inputimagebuffheight);

            int newheighterrorimage = (int) Math.round((errorimagewidth/errorimagewidth)*310);
            int newheightinputimagebuff = (int) Math.round((inputimagebuffheight/inputimagebuffheight)*310);
            int newwidtherrorimage = (int) Math.round((newheighterrorimage)*(errorratio));
            int newinputimagewidth = (int) Math.round((newheightinputimagebuff)*(inputimageratio));

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
                tb.append("Tag " +(k+1)+": "+tags1+"\n");

                urltags= urltags+tags1+",";
            }

            System.out.println(urltags);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

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