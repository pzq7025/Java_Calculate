package calculation;

/**
 * title : Calculation
 * @author : pzq
 * @date : 2018/11/11
 * unicode
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 采取继承的JFrame的方式  创建calculation的界面窗口
 * 采取Grid进行排布  不采取Box进行排布
 */


public class new_pro extends JFrame{
    // 建立对象字符数组  存放输入的表达式
    private ArrayList<String> list;
    /**
     * 快速搭建计算器的界面
     */
    // 建立对象的字符数组 数字
    private final String[] KEYS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "π", "(", ")", "^", "e", "√", "sin", "cos", "tan"};
    // 建立对象的字符数组 计算功能
    private final String[] CAL_SYM = {"-", "+", "*", "/", "="};
    // 建立对象的字符数组  功能键 退格 清零等
    private final String[] CLEAR = {"AC", "Backspace"};


    // 创建出JButton的对象数组后面直接使用Grid进行排布
    private JButton keys[] = new JButton[KEYS.length];
    private JButton cal_sym[] = new JButton[CAL_SYM.length];
    private JButton clear[] = new JButton[CLEAR.length];
    // 存放结果的文本
    public JTextField resultText = new JTextField("0");


    // 通过初始化控制流  就可以控制输入与输出的内容
    // 控制输入，true为重新输入，false为接着输入
    private boolean vbegin = true;
    // true为未输入=，false表示输入=
    private boolean equals_flag = true;
    // true为正确，可以继续输入，false错误，输入锁定
    private boolean isContinueInput = true;

    // 定义最大长度  以及π的值
    final int MAX_LEN = 500;
    final double PI = 3.141592657989624;
    final double ei = 2.71828;


    // 构造函数
    public new_pro(){

        super();
//        // 由于继续了  JFrame的类所以在使用的时候可以直接建立窗口对象
//        // 设置背景颜色
//        this.setBackground(Color.LIGHT_GRAY);
//        this.setTitle("CALCULATION");
//        // 设置大小
//        this.setLocation(500, 400);
//        this.setResizable(false);
//        this.pack();
//        // 构建函数
        init();
    }


    void init(){
        // 界面设计得函数
        initLayout();
        // 对Button组件的响应
        initActionEvent();

    }


    void initLayout(){

        resultText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setEditable(false);
        resultText.setBackground(Color.white);
        list = new ArrayList<String>();


        // 组件对象 具有布局的功能
        JPanel clickPane = new JPanel();
        clickPane.setLayout(new GridLayout(5, 3, 3, 3));
        // 用for循环进行快速的布局
        for (int i = 0; i < KEYS.length; i++) {
            // 实例化对象
            keys[i] = new JButton(KEYS[i]);
            // 将对象添加到组件中间去
            clickPane.add(keys[i]);
            // 设置按钮背景为灰绿色
            keys[i].setForeground(Color.BLACK);
        }
        for (int i = 0; i < CAL_SYM.length; i++) {
            // 实例化对象
            cal_sym[i] = new JButton(CAL_SYM[i]);
            // 将对象添加到组件中间去
            clickPane.add(cal_sym[i]);
            // 设置Button按钮的背景颜色为 绿色
            cal_sym[i].setForeground(Color.BLACK);
        }
        for (int i = 0; i < CLEAR.length; i++) {
            // 实例化对象
            clear[i] = new JButton(CLEAR[i]);
            // 将实例化的对象添加都组件中间去
            clickPane.add(clear[i]);
            // 设置按钮的背景颜色
            clear[i].setForeground(Color.RED);
        }

        JPanel text = new JPanel();
        text.setLayout(new BorderLayout());
        text.add("Center", resultText);


        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(3, 3, 3, 3);



        // 进行布局处理
        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 1;
        panel1.add(cal_sym[0], gbc);//  “/号”

        gbc.gridx = 1;
        gbc.gridy = 0;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 1;
        panel1.add(clear[0], gbc);//  "AC"

        gbc.gridx = 0;
        gbc.gridy = 1;
        //gbc.gridwidth = 1;
        //gbc.gridheight = 1;
        panel1.add(cal_sym[1], gbc);//"*"

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        panel1.add(clear[1], gbc);//“backspace”

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        panel1.add(cal_sym[2], gbc);//“-”

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 33;
        panel1.add(cal_sym[3], gbc);//"+"

        gbc.gridx = 1;
        gbc.gridy = 3;
        //gbc.ipadx = 10;
        //gbc.ipady = 33;
        panel1.add(cal_sym[4], gbc);//"="

        getContentPane().setLayout(new BorderLayout(3, 3));
        getContentPane().add("Center", clickPane);
        getContentPane().add("East", panel1);
        getContentPane().add("North", text);



        // 由于继承了  JFrame的类所以在使用的时候可以直接建立窗口对象
        // 设置背景颜色
        setBackground(Color.LIGHT_GRAY);
        setTitle("CALCULATION");
        // 设置大小
        setLocation(500, 400);
        // 不可修改窗体大小
        setResizable(false);
        pack();
    }


    // 建立监听
    void initActionEvent(){
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this::actionPerformed);
        }

        for (int i = 0; i < CAL_SYM.length; i++) {
            cal_sym[i].addActionListener(this::actionPerformed);
        }

        for (int i = 0; i < CLEAR.length; i++) {
            clear[i].addActionListener(this::actionPerformed);
        }
    }

    // 组件发生操作时调用  按钮发生响应时反馈
    // 组件响应时候的操作
    public void actionPerformed(ActionEvent e){
        String label = e.getActionCommand();
        if(label.equals(CLEAR[1])){
            // 退格响应
            hanleBackspace();
            // 清空处理
        } else if (label.equals(CLEAR[0])){
            list.clear();
            resultText.setText("0");
            // 初始状态为继续输入
            vbegin = true;
            equals_flag = true;
        } else {
            handle(label);
        }
    }


    // 退格函数的书写
    private void hanleBackspace(){
        // 获取输入的内容 存入数组进行操作 获取Text的内容
        String Text = resultText.getText();
        // 将内容存入数组中
        list.add(Text);
        // 获取字符的长度  进行栈处理
        int i = Text.length();
        // 判断是否有值
        if(i > 0 && list.size() > 0){
            Text = Text.substring(0, i-1);
            // 移除最上层的元素  也就是栈顶元素
            list.remove(list.size() -1);
            if(Text.length() == 0){
                // 清楚数组内容
                // 清空栈
                list.clear();
                resultText.setText("0");
                // 恢复到最开始的状态
                // 可以继续输入
                vbegin = true;
                // 未出现等号
                equals_flag = true;
                // 没有值是将不改变文本的内容
            } else {
                resultText.setText(Text);
            }
        }
    }


    // 对输入内容的获取
    private void handle(String key){
        // 获取界面的内容 不包括=
        // π0123456789.()+-*/^
        String con = "π0123456789.()+-*/^";
        // 将获取的内容用一个字符进行接收
        String text = resultText.getText();
        // 当输入的不是等号的时候
        // 等号会做计算  不能输入为false
        if(equals_flag == false){
            list.add(text);
            vbegin = false; // 继续输入
        }
        // 对输入的内容进行送检
        if(!list.isEmpty()){
            TipChecker(list.get(list.size()-1), key);
        } else {
            TipChecker("#", key);
        }
        if(isContinueInput && con.indexOf(key) != -1){
            list.add(key);
        }

        // 对运算式合理性的判断
        String exp = "+-*/^";
        // 对数字的判断
        String num = "π0123456789.()";
        // 对于正确的表达式  就允许出现在屏幕上
        if(isContinueInput && con.indexOf(key) != -1){
            if(equals_flag == false && exp.indexOf(key) != -1){
                vbegin = false;
                equals_flag = true;
                // 打印结果
                printText(key);
            } else if (equals_flag == false
            && num.indexOf(key) != -1){
                // 重新输入
                vbegin = true;
                // 未出现等号
                equals_flag = true;
                // 显示内容
                printText(key);
            } else {
                // 显示内容
                printText(key);
            }
        } else if (isContinueInput && equals_flag && key.equals("=")){
            // 不可继续输入
            isContinueInput = false;
            // 已经输入等号
            equals_flag = false;
            // 重新输入标志设置
            vbegin = true;
            // 表达式解析   对表达式解析运算  代码核心部分
            process(resultText.getText());
            // 清空数组
            list.clear();
        }
        isContinueInput = true;
    }


    /**
     * 检测函数检测输入的合理性  控制表达式的合理性  检测是否能够继续输入
     * 通过对两个字符的相互检测  判断是否能够入栈继续运算  为tipcode指示提供依据
     *数字第一位可以是零  为了规范化 第一位为负数的时候  用括号括起来
     * 运算符  后面接数字  π后面不接任何数字  π只是个数字  后面接运算符
     *
     * @param tipcommand1: 第一次输入的字符
     * @param tipcommand2  第二次输入的字符
     */
    private void TipChecker(String tipcommand1, String tipcommand2){
        String num = "0123456789";
        String oper = "+-*/";
        // tipcode1表示错误类型   tipcode2表示名词性解释类型
        int tipcode1 = 0, tipcode2 = 0;
        // 表示命令类型
        int tiptype1 = 0, tiptype2 = 0;
        // 括号数
        int bracket = 0;
        // +-*/^  这些运算符不能在第一位
        if (tipcommand1.compareTo("#") == 0
                && (tipcommand2.compareTo("/") == 0
                || tipcommand2.compareTo("*") == 0
                || tipcommand2.compareTo("+") == 0
                || tipcommand2.compareTo("-") == 0
                || tipcommand2.compareTo("^") == 0)){
            tipcode1 = -1;
            // 定义存储字符串中最后一位的类型
        } else if (tipcommand1.compareTo("#") != 0){
            if (tipcommand1.compareTo("(") == 0) {
                tiptype1 = 1;
            } else if (tipcommand1.compareTo(")") == 0){
                tiptype1 = 2;
            } else if (tipcommand1.compareTo(".") == 0){
                tiptype1 = 3;
            } else if(num.indexOf(tipcommand1) != -1){
                tiptype1 = 4;
            } else if (oper.indexOf(tipcommand1) != -1){
                tiptype1 = 5;
            } else if ("^".indexOf(tipcommand1) != -1){
                tiptype1 = 6;
            } else if ("π".indexOf(tipcommand1) != -1){
                tiptype1 = 7;
            }

            // 定义欲输输入的按键类型
            if (tipcommand2.compareTo("(") == 0){
                tiptype2 = 1;
            } else if (tipcommand2.compareTo(")") == 0){
                tiptype2 = 2;
            } else if (tipcommand2.compareTo(".") == 0){
                tiptype2 = 3;
            } else if (num.indexOf(tipcommand2) != -1){
                tiptype2 = 4;
            } else if (oper.indexOf(tipcommand2) != -1){
                tiptype2 = 5;
            } else if ("^".indexOf(tipcommand2) != -1){
                tiptype2 = 6;
            } else if ("π".indexOf(tipcommand2) != -1){
                tiptype2 = 7;
            }

            switch (tiptype1){
                case 1:
                    // 左括号后面直接接右括号   +-*/(不算负号)  或者"^"
                    if (tiptype2 == 2
                            || (tiptype2 == 5 && tipcommand2.compareTo("-") != 0)
                            || tiptype2 == 6){
                        tipcode1 = 1;
                    }
                    break;
                case 2:
                    // 右括号后面接左括号 数字 “+-*/^...π”
                    if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4 || tiptype2 == 7){
                        tipcode1 = 2;
                    }
                    break;
                case 3:
                    // “.”后面接左括号，π
                    if (tiptype2 == 1 || tiptype2 == 7){
                        tipcode1 = 3;
                    }
                    // 连续输入两个.
                    if (tiptype2 == 3){
                        tipcode1 = 8;
                    }
                    break;
                case 4:
                    // 数字后面直接接左括号和π
                    if (tiptype2 == 1 || tiptype2 == 7){
                        tipcode1 = 5;
                    }
                    break;
                case 6:
                    // "^"后面直接接右括号   "+-*/ ^π"
                    if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6 || tiptype2 == 7){
                        tipcode1 = 6;
                    }
                    break;
                case 7:
                    // π之后只能跟运算符 不能接括号数字
                    if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4 || tiptype2== 7){
                        tipcode1 = 7;
                    }
                    break;
            }
        }
        // 检测小数点的重复性  tipcode1=0 表明满足前面的规则
        if (tipcode1 == 0 && tipcommand2.compareTo(".") == 0){
            // 小数点个数的计数器
            int tip_point = 0 ;
            for (int i = 0; i < list.size(); i++) {
                // 若之前出现一个小数点  则小数点计数加1
                if (list.get(i).equals(".")){
                    tip_point++;
                }
                // 若出现以下几个运算符之一  小数点计数清零
                if (list.get(i).equals("^") || list.get(i).equals("/")
                        || list.get(i).equals("*") || list.get(i).equals("-")
                        || list.get(i).equals("+") || list.get(i).equals("(")
                        || list.get(i).equals(")")){
                    tip_point = 0;
                }
            }
            tip_point++;
            // 若小数点的计数大于1  说明小数点有重复
            if (tip_point > 1){
                tipcode1 = 8;
            }
        }
        // 检测右括号是否匹配
        if (tipcode1 == 0 && tipcommand2.compareTo(")") == 0){
            // 右括号的计数器  记录右边括号的个数
            int tip_right_brack = 0;
            for (int i = 0; i < list.size(); i++) {
                // 如果多一个左括号 计数器加一
                if (list.get(i).equals("(")){
                    tip_right_brack++;
                }
                // 如果出现一个右括号  则计数减一
                if (list.get(i).equals(")")){
                    tip_right_brack--;
                }
            }
            if (tip_right_brack == 0){
                tipcode1 = 10;
            }
        }

        // 检测输入=的合法性
        if (tipcode1 == 0 && tipcommand2.compareTo("=") == 0){
            // 括号匹配数
            int tip_brack = 0;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).equals("(")){
                    tip_brack++;
                }
                if (list.get(i).equals(")")){
                    tip_brack--;
                }
            }
            // 若大于零  表明左括号不匹配
            if (tip_brack > 0){
                tipcode1 = 9;
                bracket = tip_brack;
            } else if (tip_brack == 0){
                if("+-*/".indexOf(tipcommand1) != -1){
                    tipcode1 = 5;
                }
            }
        }


        if (tipcode1 != 0){
            // 如果不符合规定关闭输入
            isContinueInput = false;
        }
    }


    /**
     * 代码的核心部分表达式的求值
     * 算法的解析  通过合理的算法解析  得到正确的结果
     * 首先是算法的优先级  按照数学逻辑的优先级进行判断
     * 建立两个栈  数组入number栈  运算符入operator栈
     * +-基本优先级为一
     * x÷基本优先级为二
     * 开根、乘方的优先级为三
     * 括号内层的优先级比外层同级运算符优先级高4级
     * 当前运算符优先级高于栈顶元素就压栈
     * 低于栈顶弹出一个预算符与两个元素运算
     * 重复知道运算符大于栈顶
     * 扫描完后对剩下的运算符与数字依次计算
     * @param str1 对传入的表达式进行运算
     *  参考解决表达式:-4+7*(2*(-3+2*3)-1/4))
     */
    public void  process(String str1){
        int weightPlus = 0, weightTemp = 0, topOp = 0, topnum = 0, flag = 1;
        /**
         * flag为1 为正为-1 为负  由于错误导致程序运行失败
         */
        /**
         * weightPlus为同一()下的的基本优先级 weightTemp为临时的记录的优先级的变化
         * topOp为weight[], operator[]的计数器, topNum为number[]的计数器
         * flag表示数的正负, 1表示正, -1表示负
         */
        // 保存operator栈中运算符的优先级, 以topOp为记数变量
        int weight[];
        // 保存数字以topNum记数
        double number[];
        // operator保存运算符, 以topOp为基数变量, ch运算符  ch_c运算符改变符
        char ch, ch_c, operator[];
        // 记录数字 除了运算符以外在运算符中间中都是字符集  即为数字
        String num;
        weight = new int[MAX_LEN];
        number = new double[MAX_LEN];
        operator = new char[MAX_LEN];
        // 字符转化涉及到特殊的字符集  如π e
        String expression = str1.replace("π", String.valueOf(PI));
        String expresson1 = str1.replace("e", String.valueOf(ei));
        // 字符集的分隔  在运算符处分隔字符
        // 可以使用split
        String [] aa = str1.split("/+-/(/)^*");
        StringTokenizer expToken = new StringTokenizer(expression, "+-*/()^");
        int i = 0;
        while (i < expression.length()) {
            // charAt(int index)方法是一个能够用来检索特定索引下的字符的String实例的方法.
            ch = expression.charAt(i);
            // 判断正负数
            if (i == 0) {
                // 开头的字母为负
                if (ch == '-') {
                    flag = -1;
                }
                    // 括号中第一个为负
                } else if (expression.charAt(i - 1) == '(' && ch == '-') {
                    flag = -1;
                }
                // 获取数字 并将正负号转移给数字  E是科学计数
                if (ch <= '9' && ch >= '0' || ch == '.' || ch == 'E') {
                    // 做完一次分隔进行索引下一个数据
                    num = expToken.nextToken();
                    ch_c = ch;
                    // 取得整个数字
                    while (i < expression.length()
                            && (ch_c <= '9' && ch_c >= '0' || ch_c == '.' || ch_c == 'E')) {
                        ch_c = expression.charAt(i++);
                    }
                    // 将游标退回之前的位置 即每个数字末尾的位置
                    if (i >= expression.length()) {
                        i -= 1;
                    } else {
                        i -= 2;
                    }
                    if (num.compareTo(".") == 0) {
                        number[topnum++] = 0;
                        // 将正负号转移给数字
                    } else {
                        number[topnum++] = Double.parseDouble(num) * flag;
                        flag = 1;
                    }
                }
                // 计算级的优先
                if (ch == '(') {
                    weightPlus += 4;
                }
                if (ch == ')') {
                    weightPlus -= 4;
                }
            if (ch == '-' && flag == 1 || ch == '+' || ch == '*' || ch == '/' || ch == '^') {
                // 运算的方法
                switch (ch) {
                    // +-法的优先级最低 为1
                    case '+':
                    case '-':
                        weightTemp = 1 + weightPlus;
                        break;
                    // */的优先级为2
                    case '*':
                    case '/':
                        weightTemp = 2 + weightPlus;
                        break;
                    // 默认的是乘方 开方
                    default:
                        weightTemp = 4 + weightPlus;
                        break;
                }

                // 如果当前的优先级大于栈顶的元素的优先级  直接入栈
                // 说明这个要先算
                if (topOp == 0 || weight[topOp - 1] < weightTemp) {
                    weight[topOp] = weightTemp;
                    operator[topOp] = ch;
                    topOp++;
                    // 否则将堆栈中元素逐个取出 直到当前栈顶运算符的优先级小于运算符的优先级
                } else {
                    while (topOp > 0 && weight[topOp - 1] >= weightTemp) {
                        switch (operator[topOp - 1]) {
                            // 取出数字数组的相应元素进行运算
                            case '+':
                                number[topnum - 2] += number[topnum - 1];
                                System.out.println("1");
                                break;
                            case '-':
                                number[topnum - 2] -= number[topnum - 1];
                                break;
                            case '*':
                                number[topnum - 2] *= number[topnum - 1];
                                break;
                            // 判断除数是否为零
                            case '/':
                                if (number[topnum - 1] == 0) {
                                    JOptionPane.showMessageDialog(null, "除数不能为零", "WARNING", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                                number[topnum - 2] /= number[topnum - 1];
                                break;
                            case '^':
                                number[topnum - 2] = Math.pow(number[topnum - 2], number[topnum - 1]);
                                break;
                        }
                        // 继续取堆栈的下一个元素进行判断
                        topnum--;
                        topOp--;
                    }
                    // 将运算符入堆栈
                    weight[topOp] = weightTemp;
                    operator[topOp] = ch;
                    topOp++;
                }
            }
            i++;
        }

        // 依次取出堆栈的运算符进行运算
        while (topOp > 0){
            // +-*直接将数组的后两位数字取出运算
            switch (operator[topOp - 1]){
                case '+':
                    number[topnum - 2] += number[topnum - 1];
                    break;
                case '-':
                    number[topnum - 2] -= number[topnum - 1];
                    break;
                case '*':
                    number[topnum - 2] *= number[topnum - 1];
                    break;
                case '/':
                    if (number[topnum - 1] == 0){
                        JOptionPane.showMessageDialog(null, "除数不能为零", "WARNING", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    number[topnum - 2] /= number[topnum - 1];
                    break;
                case '^':
                    number[topnum - 2] = Math.pow(number[topnum - 2], number[topnum - 1]);
                    break;
            }
            // 取出下一个堆栈中的元素进行运算
            topnum--;
            topOp--;
        }
        // 如果数字太大给出提示信息
        if(number[0] > 7.3E306){
            JOptionPane.showMessageDialog(null, "数字太大计算能力不够", "WARNING", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 输出最终的结果
        resultText.setText(String.valueOf(FP(number[0])));
    // 这是函数的括号
    }


    // 对输出的小数 进行一个规划  限定其位置
    public double FP(double n){
        DecimalFormat format = new DecimalFormat("0.###############");
        return Double.parseDouble(format.format(n));

    }


    private void printText(String key){
        if(vbegin){
            // 清屏后输出
            resultText.setText(key);
        } else {
            resultText.setText(resultText.getText() + key);
        }
        vbegin = false;
    }

    public static void main(String[] args){
        // 实例化对象  并且将GUI界面设置为可视化
        new_pro np = new new_pro();
        np.setVisible(true);
        np.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}