package com.tec.data.weather.view.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;

import com.tec.data.weather.controller.SwingFrameManager;
import com.tec.data.weather.controller.TableRenderer;
import com.tec.data.weather.model.NowWeatherModel;

@SuppressWarnings("serial")
public final class SwingFrame extends JFrame {

    private static final Font CITY_TEXTFEALD = new Font("Comic Sans MS", Font.BOLD, 13);
    private static final Font SEARCH_BUTTON = new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 14);
    private static final Font LENGUAGE_COMBO_BOX = new Font("Microsoft JhengHei", Font.BOLD, 13);
    private static final Font GENERAL_WEATHER_TEXTAREA = new Font("Microsoft JhengHei", Font.BOLD, 17);
    private static final Font NOW_WEATHER_TEMPERATURE = new Font("Comic Sans MS", Font.BOLD, 35);
    private static final Font NOW_WEATHER_FEELSLIKE_LABEL = new Font("Comic Sans MS", Font.BOLD, 20);
    private static final Font NOW_WEATHER_FEELSLIKE = new Font("Comic Sans MS", Font.BOLD, 25);
    private static final Font NOW_WEATHER_OTHER = new Font("Comic Sans MS", Font.BOLD, 18);
    private static final Font WEATHER_BUTTON_FONT = new Font("Microsoft JhengHei", Font.PLAIN, 14);
    private static final Color WHITE_COLOR = new Color(255, 255, 204);
    private static final Font BUTTON_FONT = new Font("Microsoft JhengHei", Font.PLAIN, 13);
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(238, 238, 238);
    private static final Color CHOOSE_BACKGROUND_COLOR = new Color(108, 182, 255);
    private static final Color BACKGROUND_COLOR = new Color(181, 218, 234);

    private JPanel contentPane = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel searchPanel = new JPanel();
    private JPanel lenguagePanel = new JPanel();
    private JPanel statusPanel = new JPanel();
    private JPanel centralBorderPanel = new JPanel();
    private JPanel centralBorderNorthPanel = new JPanel();
    private JPanel buttonWeatherPanel = new JPanel();
    private JPanel generalWeatherPanel = new JPanel();
    private FlowLayout searchPanelFlowL = (FlowLayout) searchPanel.getLayout();
    private FlowLayout lenguagePanelFlowL = (FlowLayout) lenguagePanel.getLayout();
    private FlowLayout statusPanelFlowL = (FlowLayout) statusPanel.getLayout();
    private JComboBox<String> lenguageComboBox = new JComboBox<>();
    private JButton searchButton = new JButton("Search");
    private JButton oneDayButton = new JButton("Weather for one day");
    private JButton nextDayButton = new JButton("Weather for nex day");
    private JButton treeDaysButton = new JButton("Weather for tree days");
    private JButton sixDaysButton = new JButton("Weather for six days");
    private final JButton anyPeriodButton = new JButton("Weather for any period");
    private JLabel lenguageLabel = new JLabel("Language");
    private JTextField cityNameField = new JTextField();
    private JTextArea generalWTextArea = new JTextArea();
    private JTable table = new JTable() {
        @Override
        public TableCellRenderer getCellRenderer(int row, int column) {
            return TableRenderer.getInstance();
        }
    };
    private JScrollPane scrollPane = new JScrollPane(table);
    private ActionListener actionListener;
    private Map<String, JButton> buttonMap;
    private String previosButtonSelected;
    private JProgressBar progressBar = new JProgressBar();

    private JPanel nowWeatherPanel = new JPanel();
    private JLabel iconLabel = new JLabel("");
    private JLabel temperatureLabel = new JLabel("");
    private JLabel feelslikeLabel = new JLabel("");
    private JPanel panel = new JPanel();
    private JLabel feelslikeTextLabel = new JLabel("Feelslike");
    private JTextArea otherTextArea = new JTextArea();

    private SwingFrameManager manager = new SwingFrameManager(table);

    public SwingFrame() {
        initEvent();
        initComponents();
        initShowTable();
        initShowWeatherButton();
        resizedJFrame();
        setTitle("Weather API v.1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void resizedJFrame() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int tempHeight = (int) (dimension.getHeight() - SwingFrameManager.DEFAULT_HEIGHT_FRAME);
        int tempWigth = (int) (dimension.getWidth() - SwingFrameManager.DEFAULT_WIDTH_FRAME);
        if (tempHeight > 0 && tempWigth > 0) {
            setBounds(tempWigth / 2, tempHeight / 2, SwingFrameManager.DEFAULT_WIDTH_FRAME,
                    SwingFrameManager.DEFAULT_HEIGHT_FRAME);
        } else
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(640, 480));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                manager.resizeTable(SwingFrameManager.DEFAULT_WIDTH_FRAME <= getWidth(), getWidth());
            }
        });
    }

    private void initComponents() {
        setContentPane(contentPane);
        contentPane.setBackground(BACKGROUND_COLOR);
        contentPane.setLayout(new BorderLayout(0, 0));

        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout(0, 0));
        topPanel.setBackground(BACKGROUND_COLOR);
        searchPanelFlowL.setVgap(2);

        searchPanelFlowL.setAlignOnBaseline(true);
        searchPanelFlowL.setAlignment(FlowLayout.LEFT);
        searchPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(searchPanel, BorderLayout.WEST);

        cityNameField.setFont(CITY_TEXTFEALD);
        cityNameField.setHorizontalAlignment(SwingConstants.CENTER);
        cityNameField.setText(SwingFrameManager.ENTER_NAME_OF_CITY);
        searchPanel.add(cityNameField);
        cityNameField.setColumns(18);

        ImageIcon icon = new ImageIcon("classes/Zoom.png");
        searchButton.setFont(SEARCH_BUTTON);
        if (icon.getIconWidth() == -1) {
            icon = new ImageIcon("src/main/resources/Zoom.png");
        }
        searchButton.setIcon(icon);
        searchPanel.add(searchButton);

        lenguagePanelFlowL.setVgap(2);
        lenguagePanelFlowL.setAlignment(FlowLayout.RIGHT);
        topPanel.add(lenguagePanel, BorderLayout.EAST);
        lenguagePanel.setBackground(BACKGROUND_COLOR);

        lenguageLabel.setFont(BUTTON_FONT);
        lenguagePanel.add(lenguageLabel);

        lenguageComboBox.setFont(LENGUAGE_COMBO_BOX);
        lenguageComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "en", "ua" }));
        lenguagePanel.add(lenguageComboBox);

        statusPanelFlowL.setAlignment(FlowLayout.RIGHT);
        contentPane.add(statusPanel, BorderLayout.SOUTH);

        statusPanel.add(progressBar);
        statusPanel.setBackground(BACKGROUND_COLOR);
        progressBar.setVisible(false);

        contentPane.add(centralBorderPanel, BorderLayout.CENTER);
        centralBorderPanel.setLayout(new BorderLayout(0, 0));
        centralBorderPanel.setBackground(BACKGROUND_COLOR);
        centralBorderNorthPanel.setBackground(BACKGROUND_COLOR);

        centralBorderPanel.add(centralBorderNorthPanel, BorderLayout.NORTH);
        centralBorderNorthPanel.setLayout(new BorderLayout(0, 0));

        centralBorderNorthPanel.add(buttonWeatherPanel, BorderLayout.SOUTH);
        buttonWeatherPanel.setBackground(BACKGROUND_COLOR);

        centralBorderNorthPanel.add(generalWeatherPanel, BorderLayout.CENTER);
        generalWeatherPanel.setLayout(new GridLayout(0, 1, 0, 0));
        generalWTextArea.setEditable(false);
        generalWTextArea.setRows(2);
        generalWTextArea.setFont(GENERAL_WEATHER_TEXTAREA);
        generalWTextArea.setLineWrap(true);
        generalWTextArea.setBackground(BACKGROUND_COLOR);
        generalWeatherPanel.add(generalWTextArea);
        nowWeatherPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

        centralBorderNorthPanel.add(nowWeatherPanel, BorderLayout.NORTH);
        nowWeatherPanel.setBackground(CHOOSE_BACKGROUND_COLOR);
        nowWeatherPanel.setLayout(new BoxLayout(nowWeatherPanel, BoxLayout.LINE_AXIS));
        temperatureLabel.setForeground(WHITE_COLOR);
        temperatureLabel.setHorizontalAlignment(SwingConstants.LEFT);
        temperatureLabel.setFont(NOW_WEATHER_TEMPERATURE);
        nowWeatherPanel.add(temperatureLabel);
        panel.setBackground(CHOOSE_BACKGROUND_COLOR);

        nowWeatherPanel.add(panel);
        feelslikeTextLabel.setForeground(WHITE_COLOR);
        feelslikeTextLabel.setFont(NOW_WEATHER_FEELSLIKE_LABEL);
        feelslikeTextLabel.setVisible(false);
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        panel.add(feelslikeTextLabel);
        feelslikeLabel.setForeground(WHITE_COLOR);
        panel.add(feelslikeLabel);
        feelslikeLabel.setFont(NOW_WEATHER_FEELSLIKE);
        nowWeatherPanel.add(iconLabel);
        otherTextArea.setEditable(false);
        nowWeatherPanel.add(otherTextArea);
        otherTextArea.setEditable(false);
        otherTextArea.setFont(NOW_WEATHER_OTHER);
        otherTextArea.setBackground(CHOOSE_BACKGROUND_COLOR);
        otherTextArea.setForeground(WHITE_COLOR);
    }

    private void initShowTable() {
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setColumnSelectionAllowed(true);
        table.setModel(SwingFrameManager.DEFAULT_TABLE_MODEL);
        table.setFont(WEATHER_BUTTON_FONT);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        manager.resizeTable(SwingFrameManager.DEFAULT_WIDTH_FRAME <= getWidth(), getWidth());
        centralBorderPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void initShowWeatherButton() {
        buttonWeatherPanel.setLayout(new GridLayout(1, 0, 0, 0));
        oneDayButton.setFont(WEATHER_BUTTON_FONT);
        oneDayButton.setBackground(CHOOSE_BACKGROUND_COLOR);
        oneDayButton.setActionCommand(SwingFrameManager.ONE_DAY);
        previosButtonSelected = oneDayButton.getActionCommand();
        oneDayButton.addActionListener(actionListener);
        buttonWeatherPanel.add(oneDayButton);

        nextDayButton.setFont(WEATHER_BUTTON_FONT);
        nextDayButton.setActionCommand(SwingFrameManager.NEXT_DAY);
        nextDayButton.addActionListener(actionListener);
        buttonWeatherPanel.add(nextDayButton);

        treeDaysButton.setFont(WEATHER_BUTTON_FONT);
        treeDaysButton.setActionCommand(SwingFrameManager.TREE_DAY);
        treeDaysButton.addActionListener(actionListener);
        buttonWeatherPanel.add(treeDaysButton);

        sixDaysButton.setFont(WEATHER_BUTTON_FONT);
        sixDaysButton.setActionCommand(SwingFrameManager.SIX_DAY);
        sixDaysButton.addActionListener(actionListener);
        buttonWeatherPanel.add(sixDaysButton);
        
        anyPeriodButton.setFont(WEATHER_BUTTON_FONT);
        anyPeriodButton.setActionCommand(SwingFrameManager.ANY_PERIOD);
        anyPeriodButton.addActionListener(actionListener);
        buttonWeatherPanel.add(anyPeriodButton);

        buttonMap = new HashMap<String, JButton>() {
            {
                put(oneDayButton.getActionCommand(), oneDayButton);
                put(nextDayButton.getActionCommand(), nextDayButton);
                put(treeDaysButton.getActionCommand(), treeDaysButton);
                put(sixDaysButton.getActionCommand(), sixDaysButton);
                put(anyPeriodButton.getActionCommand(), anyPeriodButton);
            }
        };
    }

    private void initEvent() {
        cityNameField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (cityNameField.getText().contains(SwingFrameManager.ENTER_NAME_OF_CITY))
                    cityNameField.setText("");
            }
        });

        cityNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    cityEntered();
                }
            }
        });

        actionListener = e -> {
            buttonMap.get(previosButtonSelected).setBackground(DEFAULT_BACKGROUND_COLOR);
            buttonMap.get(e.getActionCommand()).setBackground(CHOOSE_BACKGROUND_COLOR);
            previosButtonSelected = e.getActionCommand();
            manager.changeTableModel(previosButtonSelected);

        };

        searchButton.addActionListener(e -> {
            cityEntered();
        });
    }

    private void cityEntered() {
        NowWeatherModel model = new NowWeatherModel(temperatureLabel, feelslikeLabel, feelslikeTextLabel, iconLabel,
                otherTextArea);
        manager.searchButtonPressed(cityNameField, model, generalWTextArea, previosButtonSelected);
    }

}
