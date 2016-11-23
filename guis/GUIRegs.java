package guis;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import application.*;
import objects.User;
import objects.UserPermissions;


public class GUIRegs extends JFrame
 {
     public GUIRegs()
     {
         setLayout(new BorderLayout());

         //file you want to play
         URL mediaURL = "https://youtu.be/ylfgXDy8bgo";  //Whatever
         //create the media player with the media url
         Player mediaPlayer = Manager.createRealizedPlayer(mediaURL);
         //get components for video and playback controls
         Component video = mediaPlayer.getVisualComponent();
         Component controls = mediaPlayer.getControlPanelComponent();
         add(video,BorderLayout.CENTER);
         add(controls,BorderLayout.SOUTH);
     }
 }