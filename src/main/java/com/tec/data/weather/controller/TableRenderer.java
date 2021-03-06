package com.tec.data.weather.controller;

import java.awt.Color;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.tec.data.weather.model.weather.Weather;

@SuppressWarnings("serial")
public class TableRenderer extends JLabel implements TableCellRenderer {
	
	private static Logger LOG = Logger.getLogger(TableRenderer.class.getName());

    public static final Color FG_BLACK_COLOR = new Color(0, 0, 0);
    public static final Color FG_COLD_COLOR = new Color(35, 35, 237);
    public static final Color FG_HOT_COLOR = new Color(255, 85, 85);
    private static final TableRenderer INSTANCE = new TableRenderer();
    private Weather weather;

    private TableRenderer() {
    }
    
    public static final TableRenderer getInstance() {
        return INSTANCE;
    }
    
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        if (weather != null) {
            if (row == 3 && column != 0) {
                try {
                    URL url = new URL("http:" + value);
                    ImageIcon icon = new ImageIcon(url);
                    return new JLabel(icon, CENTER);
                } catch (MalformedURLException e) {
                	LOG.log(Level.INFO, "Can't load icon {0}", e.getMessage());
                }
            } else if ((row == 5 || row == 6 || (row >= 12 && row <= 14)) && column != 0) {
                if (((double) value) > 0.0) {
                    setHorizontalAlignment(CENTER);
                    setForeground(FG_HOT_COLOR);
                    setText("" + value);
                } else if (((double) value) < 0.0) {
                    setHorizontalAlignment(CENTER);
                    setForeground(FG_COLD_COLOR);
                    setText("" + value);
                }
            } else if (column == 0) {
                setHorizontalAlignment(RIGHT);
                setForeground(FG_BLACK_COLOR);
                setText("" + value);
            } else if ((row == 4 || row == 15 || row == 16) && (value.equals(0) || value.equals(0.0))) {
                setHorizontalAlignment(CENTER);
                setForeground(FG_BLACK_COLOR);
                setText("-");
            } else if (column != 0 && row == 7) {
                if (!((int) value >= 750 && (int) value <= 760)) {
                    setHorizontalAlignment(CENTER);
                    setForeground(FG_HOT_COLOR);
                    setText("" + value);
                } else {
                    setHorizontalAlignment(CENTER);
                    setForeground(FG_BLACK_COLOR);
                    setText("" + value);
                }
            }else {
                setHorizontalAlignment(CENTER);
                setForeground(FG_BLACK_COLOR);
                setText("" + value);
            }
        } else {
            setHorizontalAlignment(RIGHT);
            setForeground(FG_BLACK_COLOR);
            setText("" + value);
        }
        return this;
    }

}
