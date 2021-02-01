package Complements;

//import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author OmarGuevara
 * @version 1.1 primera vercion de metodo para validaciones
 */
public class MetodosValidar {

//     
    /**
     * Para la siguiente vercion se puede hacer unas validaciones usando como
     * parametro la clases y con class
     *
     * @author Darwin Omar Guevara Diaz
     * @version 1.1
     *
     * @param componetes
     * @param estado
     */
    private String mascara = "";

    public static void activarControles(JComponent componetes, boolean estado) {
        Component[] contenedor = componetes.getComponents();
        for (int i = 0; i < contenedor.length; i++) {
            if (contenedor[i] instanceof JTextField
                    || contenedor[i] instanceof JLabel
                    || contenedor[i] instanceof JButton
                    || contenedor[i] instanceof JTable
                    || contenedor[i] instanceof JCheckBox
                    || contenedor[i] instanceof JTextComponent
                    || contenedor[i] instanceof JSpinner
                    || contenedor[i] instanceof JDateChooser
                    || contenedor[i] instanceof JComboBox) {
                contenedor[i].setEnabled(estado);
            }
        }
    }

  

  
    
  
   
    public static void soloNumeros(JTextField txt, int numeroCaracteres) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char car = evt.getKeyChar();
                if (car < '0' || car > '9') { // numero que estan entre  0-9
                    evt.consume();
                }

                if (numeroCaracteres > 0) {
                    if (txt.getText().length() == numeroCaracteres) {
                        evt.consume();
                    }
                }
            }
        });
    }
    
    public static void ValCod(JTextField txt, int numeroCaracteres) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char car = evt.getKeyChar();
                if ((car < '0' || car > '9')&& car != 'x') { // numero que estan entre  0-9
                    evt.consume();
                }

                if (numeroCaracteres > 0) {
                    if (txt.getText().length() == numeroCaracteres) {
                        evt.consume();
                    }
                }
            }
        });
    }


    /**
     * Este metodo solo acepta numero con decimales y al mismo tiempo limita el
     * tamaño de caracteres, este metodo solo presenta un problema de validacion
     * la cual es la siguiente: si introducimos el numero .0123 en ese caso solo
     * en ese caso me saldria un error, dado que no cuenta con un numero antes
     * del punto, pero para esto lo valido ya desde el formulario
     *
     * @param txt este el el objeto (la caja de texto),
     * @param numeroCaracteres este el numeor maximo caracteres que tendra la
     */
    public static void soloNumerosDecimales(JTextField txt, int numeroCaracteres) {

        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                    evt.consume();
                }
                if (c == '.' && txt.getText().contains(".")) {
                    evt.consume();
                }

                if (numeroCaracteres > 0) {
                    if (txt.getText().length() == numeroCaracteres) {
                        evt.consume();
                    }
                }
            }
        });
    }

    /**
     * Este metodo permite ingresar solo letras y numeros, segun el tamaño que
     * se le especifique
     *
     * @param txt este el el objeto (la caja de texto),
     * @param numeroCaracteres este el numeor maximo caracteres que tendra la
     */
    public static void soloLetrasNumeros(JTextField txt, int numeroCaracteres) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < 'a' || c > 'z')
                        && (c < 'A' || c > 'Z')
                        && (c < '0' || c > '9')
                        && c != 'a' //Minúsculas
                        && c != 'e'
                        && c != 'i'
                        && c != 'o'
                        && c != 'u'
                        && c != 'A' //Mayúsculas
                        && c != 'E'
                        && c != 'I'
                        && c != 'O'
                        && c != 'U'
                        && (c != (char) e.VK_SPACE)) {
                    e.consume(); //evita que se escriba el caracter
                }
                if (numeroCaracteres > 0) {
                    if (txt.getText().length() == numeroCaracteres) {
                        e.consume();
                    }
                }
            }
        });
    }

    /**
     * Este metodo permite ingresar solo letras, segun el tamaño que se le
     * especifique
     *
     * @param txt este el el objeto (la caja de texto),
     * @param numeroCaracteres este el numeor maximo caracteres que tendra la
     */
    public static void soloLetras(JTextField txt, int numeroCaracteres) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((c < 'a' || c > 'z')
                        && (c < 'A' || c > 'Z')
                        && c != 'á' //Minúsculas
                        && c != 'é'
                        && c != 'í'
                        && c != 'ó'
                        && c != 'ú'
                        && c != 'Á' //Mayúsculas
                        && c != 'É'
                        && c != 'Í'
                        && c != 'Ó'
                        && c != 'Ú'
                        && (c != (char) e.VK_SPACE)) {
                    e.consume(); //evita que se escriba el caracter
                }
                if (numeroCaracteres > 0) {
                    if (txt.getText().length() == numeroCaracteres) {
                        e.consume();
                    }
                }
            }
        });
    }


   

    public static MaskFormatter validarEntradas(int op) {
        MaskFormatter mascara = null;
        try {
            switch (op) {
                case 1:
//                    ruc
                    mascara = new MaskFormatter("###########");
                    break;
                case 2:
//                    Fechas
                    mascara = new MaskFormatter("##/##/####");
                    break;
                case 3:
//                    Telefono Fijo
                    mascara = new MaskFormatter("(##) ## ## ##");
//                    mascara.setValidCharacters("0123456789");
                    break;
                case 4:
//                    Celular 
                    mascara = new MaskFormatter("### ### ###");
                    break;
                case 5:
//                    Dni
                    mascara = new MaskFormatter("########");
                    break;
            }
        } catch (ParseException erro) {
        }
        return mascara;
    }


    public static void navegar(Container objContenedor) {

        Set<AWTKeyStroke> teclas = new HashSet<AWTKeyStroke>();
        Set<AWTKeyStroke> teclas2 = new HashSet<AWTKeyStroke>();

        teclas.add(AWTKeyStroke.getAWTKeyStroke(
                java.awt.event.KeyEvent.VK_ENTER, 0));

        teclas.add(AWTKeyStroke.getAWTKeyStroke(
                java.awt.event.KeyEvent.VK_TAB, 0));

        teclas.add(AWTKeyStroke.getAWTKeyStroke(
                java.awt.event.KeyEvent.VK_DOWN, 0));

        teclas2.add(AWTKeyStroke.getAWTKeyStroke(
                java.awt.event.KeyEvent.VK_UP, 0));

        // Se pasa el conjunto de teclas al panel principal
        objContenedor.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                teclas);

        objContenedor.setFocusTraversalKeys(
                KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
                teclas2);

    }


    public static BigDecimal redondearNumero(double numero, int numeroDecimales) {
        BigDecimal big = new BigDecimal(numero);
        big = big.setScale(numeroDecimales, RoundingMode.HALF_UP);
        return big;
    }

    public static double redondearNumeros(float numero, int numeroDecimales) {
        int aux = 10;
        try {

            if (numeroDecimales >= 1) {
                switch (numeroDecimales) {
                    case 1:
                        aux = 10;
                        return Math.rint(numero * aux) / aux;
                    case 2:
                        aux = 100;
                        return Math.rint(numero * aux) / aux;
                    case 3:
                        aux = 1000;
                        return Math.rint(numero * aux) / aux;
                    case 4:
                        aux = 10000;
                        return Math.rint(numero * aux) / aux;
                    default:
                        return Math.rint(numero * aux) / aux;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return Math.rint(numero * aux) / aux;
    }

    public static void autoCompletarNumeros(JTextField txtObjeto, int longitud) {
        String formato = "000000000000000000000000000";
        String nroAutoCompletar = formato + txtObjeto.getText();
        nroAutoCompletar = nroAutoCompletar.substring(nroAutoCompletar.length() - longitud);
        txtObjeto.setText(nroAutoCompletar);
    }


}



