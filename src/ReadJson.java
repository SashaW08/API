import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;

// Program for print data in JSON format.
public class ReadJson {
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", 1704310046);
        file.put("Tuition Fees", 65400);

        // To print in JSON format.
        System.out.print(file.get("Tuition Fees"));
        ReadJson readingIsWhat = new ReadJson();

        readingIsWhat.showEventDemo();
    }

    private JFrame mainFrame;
    private JPanel searchpanel1;
    private JPanel biggerpanel;
    private JPanel infopanel;
    private JPanel infopanel1;
    private JScrollPane scroll;
    private JTextArea tb;
    int WIDTH = 300;
    int HEIGHT = 300;
    int number = 0;

    private void prepareGUI() {
        mainFrame = new JFrame("Avatar the Last Airbender");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        infopanel1 = new JPanel();
        infopanel1.setLayout(new BorderLayout());
        biggerpanel = new JPanel();
        biggerpanel.setLayout(new GridLayout(1,1));
        infopanel = new JPanel();
        infopanel.setLayout(new GridLayout(1,1));
        tb = new JTextArea();
        scroll = new JScrollPane(tb);
        searchpanel1 = new JPanel();
        searchpanel1.setLayout(new GridLayout(1,3));

        mainFrame.setVisible(true);
    }

    public void showEventDemo(){

        JButton button1 = new JButton("Next");
        JButton button2 = new JButton("Previous");
        JLabel label1 = new JLabel ("Character info:", JLabel.CENTER);

        button1.setActionCommand("Next");
        button1.addActionListener(new ButtonClickListener());
        button2.setActionCommand("Previous");
        button2.addActionListener(new ButtonClickListener());
        mainFrame.add(searchpanel1, BorderLayout.NORTH);
        searchpanel1.add(button2);
        searchpanel1.add(button1);
        mainFrame.add(biggerpanel);
        biggerpanel.add(infopanel1);
        infopanel1.add(infopanel, BorderLayout.CENTER);
        infopanel1.add(label1, BorderLayout.NORTH);
        infopanel.add(scroll);

        mainFrame.setVisible(true);
    }

    public ReadJson() {

        prepareGUI();

        try {
            pull();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {

            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters");
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
            int p = jsonmainarray.size();

                JSONObject object = (JSONObject) jsonmainarray.get(number);

                tb.append("Name: "+object.get("name")+"\n");
                tb.append("ID: "+object.get("_id")+"\n");
            tb.append("Gender: "+object.get("gender")+"\n");
            tb.append("Profession: "+object.get("profession")+"\n");
            tb.append("Hair color: "+object.get("hair")+"\n");
            tb.append("Weapon: "+object.get("weapon")+"\n");
            tb.append("Photo link: "+object.get("photoUrl")+"\n");
            tb.append("Affiliation: "+object.get("affiliation")+"\n");

                org.json.simple.JSONArray enemies = (org.json.simple.JSONArray) object.get("enemies");
                int d = enemies.size();
                for (int k = 0; k < d; k++) {
                    String enemies1 = (String) enemies.get(k);
                    tb.append("Enemy: "+enemies1+"\n");
                }

                org.json.simple.JSONArray allies = (org.json.simple.JSONArray) object.get("allies");
                int j = allies.size();
                for (int h = 0; h < j; h++) {
                    String allies1 = (String) allies.get(h);
                    tb.append("Ally: "+allies1+"\n");
                }

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

                if(number==20){
                    tb.setText("No more characters this way! Hit 'Previous' button");
                }

                System.out.println(number);

                try {
                    pull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }

            if(command.equals("Previous")){
                tb.setText("");

                number=number-1;

                if(number==-1){
                    tb.setText("No more characters this way! Hit 'Next' button");
                }

                System.out.println(number);

                try {
                    pull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }
    }

}