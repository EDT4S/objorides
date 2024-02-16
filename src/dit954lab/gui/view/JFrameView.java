package dit954lab.gui.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 **/

public class JFrameView extends JFrame implements View{
    private static final int X = 860;
    private static final int Y = 800;

    protected DrawPanel drawPanel;

    protected JPanel controlPanel = new JPanel();

    protected JPanel gasPanel = new JPanel();
    protected JSpinner gasSpinner = new JSpinner();
    protected int gasAmount = 0;
    protected JLabel gasLabel = new JLabel("Amount of gas");

    protected JButton gasButton = new JButton("Gas");
    protected JButton brakeButton = new JButton("Brake");
    protected JButton turboOnButton = new JButton("Saab Turbo on");
    protected JButton turboOffButton = new JButton("Saab Turbo off");
    protected JButton liftBedButton = new JButton("Scania Lift Bed");
    protected JButton lowerBedButton = new JButton("Lower Lift Bed");

    protected JButton startButton = new JButton("Start all cars");
    protected JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public JFrameView(String framename, Listener listener, ViewModel model){
        this.drawPanel = new DrawPanel(X, Y-240,model);
        initComponents(framename, listener);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title, Listener listener) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        gasButton.addActionListener(e -> listener.onGas(gasAmount));
        brakeButton.addActionListener(e -> listener.onBrake(gasAmount));
        turboOnButton.addActionListener(e -> listener.onTurboOn());
        turboOffButton.addActionListener(e -> listener.onTurboOff());
        liftBedButton.addActionListener(e -> listener.onLiftBed());
        lowerBedButton.addActionListener(e -> listener.onLowerBed());
        startButton.addActionListener(e -> listener.onStart());
        stopButton.addActionListener(e -> listener.onStop());

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void repaint(){
        // repaint() calls the paintComponent method of the panel
        this.drawPanel.repaint();
    }

    @Override
    public int getWidth(){
        return X;
    }
}