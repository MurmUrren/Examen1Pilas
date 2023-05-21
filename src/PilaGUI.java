import javax.swing.*;
import java.awt.*;


public class PilaGUI extends JFrame {
    private Pila pila;
    private JTextField textFieldElemento;
    private JTextArea textAreaPila;

    public PilaGUI(int capacidad) {
        pila = new Pila(capacidad);

        setTitle("Pila GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel labelElemento = new JLabel("Elemento:");
        textFieldElemento = new JTextField(10);
        JButton buttonPush = new JButton("Push");
        JButton buttonPop = new JButton("Pop");
        JButton buttonTop = new JButton("Top");


        panelSuperior.add(labelElemento);
        panelSuperior.add(textFieldElemento);
        panelSuperior.add(buttonPush);
        panelSuperior.add(buttonPop);
        panelSuperior.add(buttonTop);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());
        textAreaPila = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaPila);

        panelInferior.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelInferior, BorderLayout.CENTER);

        add(panelPrincipal);

        buttonPush.addActionListener(e -> {
            try {
                if (pila.isFull()) {
                    JOptionPane.showMessageDialog(PilaGUI.this, "La pila está llena",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int elemento = Integer.parseInt(textFieldElemento.getText());
                    pila.push(elemento);
                    actualizarPila();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PilaGUI.this, "Ingresa un número válido",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(PilaGUI.this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            textFieldElemento.setText("");
        });

        buttonPop.addActionListener(e -> {
            try {
                int elemento = pila.pop();
                JOptionPane.showMessageDialog(PilaGUI.this, "Elemento removido: " + elemento,
                        "Pop", JOptionPane.INFORMATION_MESSAGE);
                actualizarPila();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(PilaGUI.this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonTop.addActionListener(e -> {
            try {
                int elemento = pila.top();
                JOptionPane.showMessageDialog(PilaGUI.this, "Elemento superior: " + elemento,
                        "Top", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(PilaGUI.this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void actualizarPila() {
        textAreaPila.setText("");
        Pila pilaTemporal = new Pila(pila.size());

        while (!pila.isEmpty()) {
            int elemento = pila.pop();
            textAreaPila.append(elemento + "\n");
            pilaTemporal.push(elemento);
        }

        while (!pilaTemporal.isEmpty()) {
            int elemento = pilaTemporal.pop();
            pila.push(elemento);
        }
    }

    public static void main(String[] args) {
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Ingrese el tamaño de la pila:", "Tamaño de la Pila", JOptionPane.QUESTION_MESSAGE));
        SwingUtilities.invokeLater(() -> new PilaGUI(capacidad).setVisible(true));
    }
}

