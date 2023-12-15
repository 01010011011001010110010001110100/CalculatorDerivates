package view;


//GUI
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//Jep
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;



public class Calculator extends javax.swing.JFrame {

    //Variables
    //Ejemplos que tendra el combobox
    String[][] examples = new String[][]{
        {"",""},
        {"x", "2*x + 5"},
        {"g","2 + 4*g^2 + x - 2"},
        {"x","sen(x) * cos(x)"},
        {"p","2(p * p^2)"},
        {"h","cot(h) + 2*u^10"}
    };
    char variable = ' ';

    

    ////////////////////////////////////////CONSTRUCTOR
    public Calculator() {
        initComponents();
        sldDerivate.setMaximum(0);
        sldDerivate.setValue(0);
        taFunction.requestFocus();
        
        //Agregar las acciones de los botones
        for (Component com : pnlButtons.getComponents()) {
            if (com instanceof JButton) {
                JButton btn = (JButton) com;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        taFunction.insert(btn.getText(), taFunction.getCaretPosition());
                        taFunction.requestFocus();
                    }
                });
            }
        }
        
        /////////////////////////////////////////////////////////////////ACCIONES DE LOS BOTONES SOBRESCRIBIEDO METODOS
        //COMBOBOX EXAMPLES
        cmbbExamples.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Rellenar campos
                try{
                    variable = examples[cmbbExamples.getSelectedIndex()][0].charAt(0);
                    tfVariable.setText("("+variable+")");
                    taFunction.setText(examples[cmbbExamples.getSelectedIndex()][1]);
                    btnDerive.requestFocus();
                } catch (Exception e1){CleanAll();}
            }
        });
        
        //BOTON DERIVAR
        btnDerive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfDerivate.setText(Derivate(taFunction.getText()));
                sldDerivate.setMaximum(tfDerivate.getText().length());
                sldDerivate.setValue(tfDerivate.getCaretPosition());
            }
        });
        
        //CAJA DE TEXTO VARIABLE
        tfVariable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                variable = e.getKeyChar();
                if (Character.isAlphabetic(e.getKeyChar())) {
                    tfVariable.setText("(" + variable + ")");
                } else {
                    JOptionPane.showMessageDialog(Calculator.this, "<html><p text-align:center>No se pueden "
                            + "introducir<br>caracteres especiales ni numeros<br>como variable"
                            + "</p><html>","Warning",JOptionPane.WARNING_MESSAGE);
                    tfVariable.setText("( )");
                }
            }
        });
        
        //SLIDER
        sldDerivate.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                tfDerivate.setCaretPosition(sldDerivate.getValue());
            }
        });
        
        //LIMPIAR TODO
        btnClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               CleanAll();
            }
        });
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        btnDerive = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfVariable = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlButtons = new javax.swing.JPanel();
        btnPlus = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        btnMulti = new javax.swing.JButton();
        btnDiv = new javax.swing.JButton();
        btnPow = new javax.swing.JButton();
        btnKeyClose = new javax.swing.JButton();
        btnParentesisOpen = new javax.swing.JButton();
        btnParentesisClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnKeyOpen1 = new javax.swing.JButton();
        cmbbExamples = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taFunction = new javax.swing.JTextArea();
        tfDerivate = new javax.swing.JTextField();
        sldDerivate = new javax.swing.JSlider();
        btnClean = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDerive.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnDerive.setText("! DERIVE ¡");
        pnlPrincipal.add(btnDerive, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 300, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Derivate");
        pnlPrincipal.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, -1, -1));

        tfVariable.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tfVariable.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfVariable.setText("( )");
        tfVariable.setPreferredSize(new java.awt.Dimension(64, 31));
        pnlPrincipal.add(tfVariable, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 50, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Calculator of derivates");
        pnlPrincipal.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("=");
        pnlPrincipal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Funcion");
        pnlPrincipal.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));
        pnlPrincipal.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 790, -1));

        pnlButtons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPlus.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPlus.setText("+");
        pnlButtons.add(btnPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 30, 30));

        btnMinus.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnMinus.setText("-");
        pnlButtons.add(btnMinus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 30, 30));

        btnMulti.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnMulti.setText("*");
        pnlButtons.add(btnMulti, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 30, 30));

        btnDiv.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnDiv.setText("/");
        pnlButtons.add(btnDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 30, 30));

        btnPow.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnPow.setText("^");
        pnlButtons.add(btnPow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 30, 30));

        btnKeyClose.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnKeyClose.setText("]");
        pnlButtons.add(btnKeyClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 30, 30));

        btnParentesisOpen.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        btnParentesisOpen.setText(")");
        pnlButtons.add(btnParentesisOpen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 30, 30));

        btnParentesisClose.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        btnParentesisClose.setText("(");
        pnlButtons.add(btnParentesisClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 30, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("symbols");
        pnlButtons.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnKeyOpen1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        btnKeyOpen1.setText("[");
        pnlButtons.add(btnKeyOpen1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 30, 30));

        pnlPrincipal.add(pnlButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 130, 170));

        cmbbExamples.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cmbbExamples.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "examples", "1", "2", "3", "4", "5" }));
        pnlPrincipal.add(cmbbExamples, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 160, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("f");
        pnlPrincipal.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        taFunction.setColumns(20);
        taFunction.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        taFunction.setRows(1);
        jScrollPane1.setViewportView(taFunction);

        pnlPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 250, 50));

        tfDerivate.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfDerivate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDerivate.setFocusable(false);
        pnlPrincipal.add(tfDerivate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 380, 150));
        pnlPrincipal.add(sldDerivate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, 380, -1));

        btnClean.setBackground(new java.awt.Color(79, 13, 13));
        btnClean.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnClean.setText("x");
        btnClean.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlPrincipal.add(btnClean, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 40, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //////////////////////////////////////////////////////////////////////FUNCIONESS
    
    ///Esta funcion se encarga de derivar las funciones que le pasemos
    // mediante una libreria llamada Jep
    // Esta podra derivar cosas sencillas como seno, suma, potencia, etc
    private String Derivate(String function){
        
        String derivate = " ";
        try {
            DJep d = new DJep();
            d.addStandardFunctions();
            d.addStandardConstants();
            d.addComplex();
            d.setAllowUndeclared(true);
            d.setAllowAssignment(true);
            d.setImplicitMul(true);
            d.addStandardDiffRules();
        
            Node n = d.parse(function);
            Node diff = d.differentiate(n, variable+"");
            Node sim = d.simplify(diff);
            derivate = d.toString(sim);
        } catch (ParseException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "<html><p style=text-align:center>Hubo un error en algun "
                    + "campo,<br> ingrese los valores de forma correcta</p><html>", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return derivate;
    }
    
    private void CleanAll(){
        variable = ' ';
        taFunction.setText("");
        tfDerivate.setText("");
        tfVariable.setText("( )");
        cmbbExamples.setSelectedIndex(0);
        sldDerivate.setMaximum(0);
        sldDerivate.setValue(0);
    }
    
    ///////////////////////////////////////////////////////////////////// MAIN
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
            UIManager.put("TextComponent.arc", 15);
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClean;
    private javax.swing.JButton btnDerive;
    private javax.swing.JButton btnDiv;
    private javax.swing.JButton btnKeyClose;
    private javax.swing.JButton btnKeyOpen1;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnMulti;
    private javax.swing.JButton btnParentesisClose;
    private javax.swing.JButton btnParentesisOpen;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnPow;
    private javax.swing.JComboBox<String> cmbbExamples;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JSlider sldDerivate;
    private javax.swing.JTextArea taFunction;
    private javax.swing.JTextField tfDerivate;
    private javax.swing.JTextField tfVariable;
    // End of variables declaration//GEN-END:variables


}
