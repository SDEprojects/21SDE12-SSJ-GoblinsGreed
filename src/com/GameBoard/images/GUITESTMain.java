package com.GameBoard.res;



import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class GUITESTMain extends JFrame{

    static Clip clip;
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel; // for creating the area to write on the screen
    JLabel titleNameLabel; // for creating the Title Art
    Font titleFont = new Font("Copperplate Gothic Bold", Font.BOLD, 65);
    Font normalFont = new Font("Times New Roman", Font.BOLD, 30);
    JButton startButton;
    static JButton musicButton;


    public static void main(String[] args) {



        new GUITESTMain();

    }


    public GUITESTMain() {

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        con = window.getContentPane();

        titleNamePanel = new JPanel(); // Creating title art
        titleNamePanel.setBounds(100, 100, 600, 150); // Creating space for title text
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Goblin's Greed");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("Start Game");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);

        musicButton = new JButton("Music on/off ");
        musicButton.addActionListener(new AL());





        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        startButtonPanel.add(musicButton);


        con.add(titleNamePanel);
        con.add(startButtonPanel);

        window.setVisible(true);

    }

    public static class AL implements ActionListener {
        public final void actionPerformed(ActionEvent e){

            if (musicButton.isSelected()){
                stopMusic();
            }else {
                music();
            }

        }
    }



    public static void music(){
        try {
            File song = new File("./resources/sounds/intro.mid");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.flush();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

    public static void stopMusic() {
        if (clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0);
        }
    }


}
