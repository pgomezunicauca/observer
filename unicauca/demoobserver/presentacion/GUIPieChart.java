
package com.unicauca.demoobserver.presentacion;

import com.unicauca.demoobserver.dominio.servicios.VotosServicio;
import com.unicauca.demoobserver.infra.Observer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;

public class GUIPieChart extends JPanel implements Observer {
    private HashMap<String, Integer> data;

    public GUIPieChart(HashMap<String, Integer> data) {
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPieChart(g);
    }

    private void drawPieChart(Graphics g) {
        if (data == null || data.isEmpty()) {
            return;
        }

        double total = 0;
        for (Integer value : data.values()) {
            total += value;
        }

        int width = getWidth();
        int height = getHeight();
        //Limpiar
        g.setColor(Color.WHITE);
         g.fillRect(0, 0, width, height);
         
        int x = width / 4;
        int y = height / 4;
        int diameter = Math.min(width, height) / 2;

        double startAngle = 0;
        for (String key : data.keySet()) {
            double value = data.get(key);
            int arcAngle = (int) Math.round(value / total * 360);
            g.setColor(getRandomColor());
            g.fillArc(x, y, diameter, diameter, (int) startAngle, arcAngle);
            double midAngle = Math.toRadians(startAngle + arcAngle / 2);
            int textX = x + (int) (Math.cos(midAngle) * diameter / 2) + diameter / 2;
            int textY = y + (int) (Math.sin(midAngle) * diameter / 2) + diameter / 2;
            
            g.setColor(Color.BLACK);
            g.drawString(key + " (" + String.format("%.1f", value) + "%)", textX, textY);
            
            startAngle += arcAngle;
        }
    }

    private Color getRandomColor() {
        return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }
    
    @Override
    public void update(Object object) {
        VotosServicio sujeto = (VotosServicio) object;
        data = sujeto.procesarVotos();
        this.drawPieChart(this.getGraphics());
    }
}
