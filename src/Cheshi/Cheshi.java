package Cheshi;



import javax.swing.*;   
import javax.swing.border.Border;   
import java.awt.*;   
import java.awt.event.ActionListener;   
import java.awt.event.ActionEvent;   
import java.math.BigDecimal;   
import java.math.RoundingMode;   
import java.util.HashMap;   
    
/**  
 * �ҵļ�������Cheshi �̳��� JFrame���Ǽ������Ľ���  
c*/  
public class Cheshi extends JFrame {   
    
    private Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);   
    
    private JTextField textbox = new JTextField("0");   
    
    private CalculatorCore core = new CalculatorCore();   
    
    private ActionListener listener = new ActionListener() {   
    
        public void actionPerformed(ActionEvent e) {   
            JButton b = (JButton) e.getSource();   
            String label = b.getText();   
            String result = core.process(label);   
            textbox.setText(result);   
        }   
    };   
    
    public Cheshi(String title) throws HeadlessException {   
        super(title);       // ���ø��๹�췽��   
        setupFrame();       // ������������   
        setupControls();    // �����ؼ�   
    }   
    
    private void setupControls() {   
        setupDisplayPanel();    // �����ı����   
        setupButtonsPanel();    // ������ť���   
    }   
    
    // ������ť��岢��Ӱ�ť   
    private void setupButtonsPanel() {   
        JPanel panel = new JPanel();   
        panel.setBorder(border);   
        panel.setLayout(new GridLayout(4, 5, 3, 3));   
    
        createButtons(panel, new String[]{   
                "7", "8", "9", "+", "C",   
                "4", "5", "6", "-", "CE",   
                "1", "2", "3", "*", "",  // ���ַ�����ʾ���λ��û�а�ť   
                "0", ".", "=", "/", ""  
        });   
    
        this.add(panel, BorderLayout.CENTER);   
    }   
    
    /**  
     * ��ָ��������ϴ�����ť  
     *  
     * @param panel  Ҫ������ť�����  
     * @param labels ��ť����  
     */  
    private void createButtons(JPanel panel, String[] labels) {   
        for (String label : labels) {   
            // ��� label Ϊ�գ����ʾ����һ������塣���򴴽�һ����ť��   
            if (label.equals("")) {   
                panel.add(new JPanel());   
    
            } else {   
                JButton b = new JButton(label);   
                b.addActionListener(listener);  // Ϊ��ť���������   
                panel.add(b);   
            }   
        }   
    }   
    
    // ������ʾ��壬��һ���ı�������Ϊ����������ʾ���֡�   
    private void setupDisplayPanel() {   
        JPanel panel = new JPanel();   
        panel.setLayout(new BorderLayout());   
        panel.setBorder(border);   
    
        setupTextbox();   
        panel.add(textbox, BorderLayout.CENTER);   
        this.add(panel, BorderLayout.NORTH);   
    }   
    
    // �����ı���   
    private void setupTextbox() {   
        textbox.setHorizontalAlignment(JTextField.RIGHT);   // �ı��Ҷ���   
        textbox.setEditable(false);                         // �ı���ֻ��   
        textbox.setBackground(Color.white);                 // �ı��򱳾�ɫΪ��ɫ   
    }   
    
    // ��������   
    private void setupFrame() {   
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // ������ر�ʱ�������   
        this.setLocation(100, 50);      // ���ô�����ʾ�������ϵ�λ��   
        this.setSize(300, 200);         // ���ô����С   
        this.setResizable(false);       // �����С�̶�   
    }   
    
    // �������   
    public static void main(String[] args) throws Exception {   
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());   
        Cheshi frame = new Cheshi("�ҵļ�����");   
        frame.setVisible(true);          // ����������ʾ����   
    }   
}   
    
/**  
 * �����������߼�������߼�ֻ�ܴ��� 1~2 ���������㡣  
 */  
class CalculatorCore {   
    
    private String displayText = "0";   // Ҫ��ʾ���ı�   
    
    boolean reset = true;   
    
    private BigDecimal number1, number2;   
    
    private String operator;   
    
    private HashMap<String, Operator> operators = new HashMap<String, Operator>();   
    
    private HashMap<String, Processor> processors = new HashMap<String, Processor>();   
    
    CalculatorCore() {   
        setupOperators();   
        setupProcessors();   
    }   
    
    // Ϊÿ��������Ӵ���ʽ   
    private void setupProcessors() {   
        processors.put("[0-9]", new Processor() {   
            public void calculate(String command) {   
                numberClicked(command);   
            }   
        });   
        processors.put("\\.", new Processor() {   
            public void calculate(String command) {   
                dotClicked();   
            }   
        });   
        processors.put("=", new Processor() {   
            public void calculate(String command) {   
                equalsClicked();   
            }   
        });   
        processors.put("[+\\-*/]", new Processor() {   
            public void calculate(String command) {   
                operatorClicked(command);   
            }   
        });   
        processors.put("C", new Processor() {   
            public void calculate(String command) {   
                clearClicked();   
            }   
        });   
        processors.put("CE", new Processor() {   
            public void calculate(String command) {   
                clearErrorClicked();   
            }   
        });   
    }   
    
    // Ϊÿ�� operator ��Ӵ���ʽ   
    private void setupOperators() {   
        operators.put("+", new Operator() {   
            public BigDecimal process(BigDecimal number1, BigDecimal number2) {   
                return number1.add(number2);   
            }   
        });   
        operators.put("-", new Operator() {   
            public BigDecimal process(BigDecimal number1, BigDecimal number2) {   
                return number1.subtract(number2);   
            }   
        });   
        operators.put("*", new Operator() {   
            public BigDecimal process(BigDecimal number1, BigDecimal number2) {   
                return number1.multiply(number2);   
            }   
        });   
        operators.put("/", new Operator() {   
            public BigDecimal process(BigDecimal number1, BigDecimal number2) {   
                return number1.divide(number2, 30, RoundingMode.HALF_UP);   
            }   
        });   
    }   
    
    // ������������������ʵ���Ͼ��ǰ�ť�ı���   
    public String process(String command) {   
        for (String pattern : processors.keySet()) {   
            if (command.matches(pattern)) {   
                processors.get(pattern).calculate(command);   
                break;   
            }   
        }   
    
        return displayText;   
    }   
    
    // ������ CE ʱ   
    private void clearErrorClicked() {   
        if (operator == null) {   
            number1 = null;   
        } else {   
            number2 = null;   
        }   
        displayText = "0";   
        reset = true;   
    }   
    
    // ������ C ʱ������������Ϊ��ʼ״̬��   
    private void clearClicked() {   
        number1 = null;   
        number2 = null;   
        operator = null;   
        displayText = "0";   
        reset = true;   
    }   
    
    // ������ = ʱ   
    private void equalsClicked() {   
        calculateResult();   
        number1 = null;   
        number2 = null;   
        operator = null;   
        reset = true;   
    }   
    
    // ������   
    private void calculateResult() {   
        number2 = new BigDecimal(displayText);   
        Operator oper = operators.get(operator);   
        if (oper != null) {   
            BigDecimal result = oper.process(number1, number2);   
            displayText = result.toString();   
        }   
    }   
    
    // ������ +-*/ ʱ������Ҳ������չ�������м��������   
    private void operatorClicked(String command) {   
        if (operator != null) {   
            calculateResult();   
        }   
    
        number1 = new BigDecimal(displayText);   
        operator = command;   
    
        reset = true;   
    }   
    
    // ������ . ʱ   
    private void dotClicked() {   
        if (displayText.indexOf(".") == -1) {   
            displayText += ".";   
        } else if (reset) {   
            displayText = "0.";   
        }   
        reset = false;   
    }   
    
    // ������ 0-9 ʱ   
    private void numberClicked(String command) {   
        if (reset) {   
            displayText = command;   
        } else {   
            displayText += command;   
        }   
        reset = false;   
    }   
    
    // ���������ӿ�   
    interface Operator {   
    
        BigDecimal process(BigDecimal number1, BigDecimal number2);   
    }   
    
    // ��ť����ӿ�   
    interface Processor {   
    
        void calculate(String command);   
    }   
    
}
