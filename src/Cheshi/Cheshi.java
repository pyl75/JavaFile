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
 * 我的计算器。Cheshi 继承于 JFrame，是计算器的界面  
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
        super(title);       // 调用父类构造方法   
        setupFrame();       // 调整窗体属性   
        setupControls();    // 创建控件   
    }   
    
    private void setupControls() {   
        setupDisplayPanel();    // 创建文本面板   
        setupButtonsPanel();    // 创建按钮面板   
    }   
    
    // 创建按钮面板并添加按钮   
    private void setupButtonsPanel() {   
        JPanel panel = new JPanel();   
        panel.setBorder(border);   
        panel.setLayout(new GridLayout(4, 5, 3, 3));   
    
        createButtons(panel, new String[]{   
                "7", "8", "9", "+", "C",   
                "4", "5", "6", "-", "CE",   
                "1", "2", "3", "*", "",  // 空字符串表示这个位置没有按钮   
                "0", ".", "=", "/", ""  
        });   
    
        this.add(panel, BorderLayout.CENTER);   
    }   
    
    /**  
     * 在指定的面板上创建按钮  
     *  
     * @param panel  要创建按钮的面板  
     * @param labels 按钮文字  
     */  
    private void createButtons(JPanel panel, String[] labels) {   
        for (String label : labels) {   
            // 如果 label 为空，则表示创建一个空面板。否则创建一个按钮。   
            if (label.equals("")) {   
                panel.add(new JPanel());   
    
            } else {   
                JButton b = new JButton(label);   
                b.addActionListener(listener);  // 为按钮添加侦听器   
                panel.add(b);   
            }   
        }   
    }   
    
    // 设置显示面板，用一个文本框来作为计算器的显示部分。   
    private void setupDisplayPanel() {   
        JPanel panel = new JPanel();   
        panel.setLayout(new BorderLayout());   
        panel.setBorder(border);   
    
        setupTextbox();   
        panel.add(textbox, BorderLayout.CENTER);   
        this.add(panel, BorderLayout.NORTH);   
    }   
    
    // 调整文本框   
    private void setupTextbox() {   
        textbox.setHorizontalAlignment(JTextField.RIGHT);   // 文本右对齐   
        textbox.setEditable(false);                         // 文本框只读   
        textbox.setBackground(Color.white);                 // 文本框背景色为白色   
    }   
    
    // 调整窗体   
    private void setupFrame() {   
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // 当窗体关闭时程序结束   
        this.setLocation(100, 50);      // 设置窗体显示在桌面上的位置   
        this.setSize(300, 200);         // 设置窗体大小   
        this.setResizable(false);       // 窗体大小固定   
    }   
    
    // 程序入口   
    public static void main(String[] args) throws Exception {   
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());   
        Cheshi frame = new Cheshi("我的计算器");   
        frame.setVisible(true);          // 在桌面上显示窗体   
    }   
}   
    
/**  
 * 计算器核心逻辑。这个逻辑只能处理 1~2 个数的运算。  
 */  
class CalculatorCore {   
    
    private String displayText = "0";   // 要显示的文本   
    
    boolean reset = true;   
    
    private BigDecimal number1, number2;   
    
    private String operator;   
    
    private HashMap<String, Operator> operators = new HashMap<String, Operator>();   
    
    private HashMap<String, Processor> processors = new HashMap<String, Processor>();   
    
    CalculatorCore() {   
        setupOperators();   
        setupProcessors();   
    }   
    
    // 为每种命令添加处理方式   
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
    
    // 为每种 operator 添加处理方式   
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
    
    // 根据命令处理。这里的命令实际上就是按钮文本。   
    public String process(String command) {   
        for (String pattern : processors.keySet()) {   
            if (command.matches(pattern)) {   
                processors.get(pattern).calculate(command);   
                break;   
            }   
        }   
    
        return displayText;   
    }   
    
    // 当按下 CE 时   
    private void clearErrorClicked() {   
        if (operator == null) {   
            number1 = null;   
        } else {   
            number2 = null;   
        }   
        displayText = "0";   
        reset = true;   
    }   
    
    // 当按下 C 时，将计算器置为初始状态。   
    private void clearClicked() {   
        number1 = null;   
        number2 = null;   
        operator = null;   
        displayText = "0";   
        reset = true;   
    }   
    
    // 当按下 = 时   
    private void equalsClicked() {   
        calculateResult();   
        number1 = null;   
        number2 = null;   
        operator = null;   
        reset = true;   
    }   
    
    // 计算结果   
    private void calculateResult() {   
        number2 = new BigDecimal(displayText);   
        Operator oper = operators.get(operator);   
        if (oper != null) {   
            BigDecimal result = oper.process(number1, number2);   
            displayText = result.toString();   
        }   
    }   
    
    // 当按下 +-*/ 时（这里也可以扩展成其他中间操作符）   
    private void operatorClicked(String command) {   
        if (operator != null) {   
            calculateResult();   
        }   
    
        number1 = new BigDecimal(displayText);   
        operator = command;   
    
        reset = true;   
    }   
    
    // 当按下 . 时   
    private void dotClicked() {   
        if (displayText.indexOf(".") == -1) {   
            displayText += ".";   
        } else if (reset) {   
            displayText = "0.";   
        }   
        reset = false;   
    }   
    
    // 当按下 0-9 时   
    private void numberClicked(String command) {   
        if (reset) {   
            displayText = command;   
        } else {   
            displayText += command;   
        }   
        reset = false;   
    }   
    
    // 运算符处理接口   
    interface Operator {   
    
        BigDecimal process(BigDecimal number1, BigDecimal number2);   
    }   
    
    // 按钮处理接口   
    interface Processor {   
    
        void calculate(String command);   
    }   
    
}
