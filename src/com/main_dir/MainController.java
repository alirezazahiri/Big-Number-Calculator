package com.main_dir;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class MainController {

    protected Main main ;
    protected Stage primaryStage ;

    public void setMain(Main main, Stage primaryStage) {
        this.main = main ;
        this.primaryStage = primaryStage ;
    }
    @FXML
    public Button zero, one, two, three, four, five, six, seven, eight,
            nine, del, ce, c , multiply, divide, sum,
            sub,equal, integral, change;
    @FXML
    public TextArea display;

    @FXML
    public Label operator;

    public String[] number_btns = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public String first_num = "", second_num = "";
    public String text = "";


    private String result;
    private boolean operator_pressed = false;

    @FXML
    public void handleClicked(ActionEvent event){

        boolean del_pressed = false;
        boolean graph_pressed = false;
        Button btn = (Button) event.getSource();
        String id = btn.getId();

        if (!is_number_btn(id)){

            if ("del".equals(btn.getId())) {
                text = remove_last_char(display.getText());
                display.setText(text);
                System.out.println("display : " + display.getText());
                System.out.println("text : " + text);
                operator.setText("⌫");
                del_pressed = true;
            }
            if (first_num.equals("") && !del_pressed){
                first_num = text;
                text = "";
            }else if(second_num.equals("") && !del_pressed) {
                second_num = text;
                text = "";
            }

            if ("c".equals(btn.getId()))
            {
                if (first_num.equals("") && second_num.equals("")){
                    text = "";
                    display.setText(text);
                }
                else if(second_num.equals("")){
                    first_num = "";
                    text = "";
                    display.setText("");
                }
                operator.setText("C");
            }
            if ("ce".equals(btn.getId()))
            {
                text = "";
                first_num = "";
                second_num = "";
                display.setText(text);
                operator.setText("CE");
            }
            if ("multiply".equals(btn.getId()))
            {
                operator.setText("x");
                operator.setId("multiply");
                text = "";
                display.setText("");
                operator_pressed = true;
            }
            if ("divide".equals(btn.getId()))
            {
                operator.setText("÷");
                operator.setId("divide");
                text = "";
                display.setText("");
                operator_pressed = true;
            }
            if ("sum".equals(btn.getId()))
            {
                operator.setText("+");
                operator.setId("sum");
                text = "";
                display.setText("");
                operator_pressed = true;
            }
            if ("sub".equals(btn.getId()))
            {
                operator.setText("-");
                operator.setId("sub");
                text = "";
                display.setText("");
                operator_pressed = true;
            }

            if ("change".equals(btn.getId())){
                if (!first_num.equals("") && second_num.equals("")){
                    if (!(first_num.charAt(0)=='-')){
                        first_num = reverse_str(first_num);
                        first_num += "-";
                        first_num = reverse_str(first_num);
                    }else {
                        first_num = remove_first_char(first_num);
                    }
                    display.setText(first_num);
                }else if(operator_pressed){
                    if (!(second_num.charAt(0)=='-')){
                        second_num = reverse_str(second_num);
                        second_num += "-";
                        second_num = reverse_str(second_num);
                    }else {
                        second_num = remove_first_char(second_num);
                    }
                    display.setText(second_num);
                }

                System.out.println("First num : " + first_num);
                System.out.println("Second num : " + second_num);
            }


            if ("equal".equals(btn.getId())) {
                operator_pressed = false;
                try {
                    System.out.println("First num : " + first_num);
                    System.out.println("Second num : " + second_num);
                    if (operator.getId().equals("sum") || operator.getId().equals("sub") ||
                            operator.getId().equals("divide") || operator.getId().equals("multiply") && !(first_num.equals("") || second_num.equals(""))) {
                        equal_operator(operator.getId());
                    } else {
                        System.out.println("Error!");
                    }
                }catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }

                first_num = result;
                second_num = "";
            }


            if("integral".equals(btn.getId())){
                main.integralWindow();
            }

            if("lineGraph".equals(btn.getId())){
                main.graphWindow();
            }

        }else {
            text += btn.getText();
            display.setText(text);
            System.out.println(text);
        }

        System.out.println(id + " PRESSED");

    }

    // DETERMINE IF NUMBERS ARE NEGATIVE OR POSITIVE
    public String sum_neg_or_pos(String s1, String s2){
        if (s1.charAt(0) == '-' && s2.charAt(0) == '-'){
            s1 = remove_first_char(s1);
            s2 = remove_first_char(s2);

            return '-' + sum(s1, s2);
        }
        else if(s1.charAt(0) == '-'){
            s1 = remove_first_char(s1);

            return sub(s2, s1);
        }
        else if(s2.charAt(0) == '-'){
            s2 = remove_first_char(s2);
            System.out.println("removed minus (CHECK) " + s2);
            return sub(s1, s2);
        }
        return sum(s1, s2) ;
    }

    public String sub_neg_or_pos(String s1, String s2){

        if (s1.charAt(0) == '-' && s2.charAt(0) == '-'){
            s2 = remove_first_char(s2);
            s1 = remove_first_char(s1);
            return sub(s2, s1);
        }
        else if(s1.charAt(0) == '-'){
            s1 = remove_first_char(s1);
            return "-" + sum(s1, s2);
        }
        else if(s2.charAt(0) == '-'){
            s2 = remove_first_char(s2);
            return sum(s1, s2);
        }
        return sub(s1, s2);
    }

    public String mul_neg_or_pos(String s1, String s2){

        if (s1.charAt(0) == '-' && s2.charAt(0) == '-'){
            s1 = remove_first_char(s1);
            s2 = remove_first_char(s2);

            return multiply(s1, s2);
        }
        else if(s1.charAt(0) == '-'){
            s1 = remove_first_char(s1);

            if (s2.equals("0")){
                return "0";
            }

            return '-' + multiply(s1, s2);
        }
        else if(s2.charAt(0) == '-'){
            s2 = remove_first_char(s2);

            if (s1.equals("0")){
                return "0";
            }

            return '-' + multiply(s1, s2);
        }
        return multiply(s1, s2);
    }

    public String div_neg_or_pos(String s1, String s2){

        if (s1.charAt(0) == '-' && s2.charAt(0) == '-'){
            s1 = remove_first_char(s1);
            s2 = remove_first_char(s2);

            return divide(s1, s2);
        }
        else if(s1.charAt(0) == '-'){
            s1 = remove_first_char(s1);

            return '-' + divide(s1, s2);
        }
        else if(s2.charAt(0) == '-'){
            s2 = remove_first_char(s2);

            return '-' + divide(s1, s2);
        }
        if (s2.equals("0")){
            return "inf";
        }

        return divide(s1, s2);
    }


    private void equal_operator(String operation) {
        switch (operation){
            case "multiply":
                result = mul_neg_or_pos(first_num, second_num);
                display.setText(result);
                break;
            case "divide":
                result = div_neg_or_pos(first_num, second_num);
                String remainder = sub_neg_or_pos(mul_neg_or_pos(div_neg_or_pos(first_num, second_num), second_num), first_num);
                if(remainder.charAt(0) == '-'){
                    remainder = reverse_str(remainder);
                    remainder = remove_last_char(remainder);
                    remainder = reverse_str(remainder);
                }
                display.setText(String.format("Result : %s\nRemainder : %s", result, remainder));
                break;
            case "sum":
                result = sum_neg_or_pos(first_num, second_num);
                display.setText(result);
                break;
            case "sub":
                result = sub_neg_or_pos(first_num, second_num);
                display.setText(result);
                break;
        }
    }

    protected String remove_last_char(String str){

        StringBuilder new_str = new StringBuilder();

        for (int i = 0 ; i < str.length()-1 ; i++){
            new_str.append(str.charAt(i));
        }

        return new_str.toString();
    }

    protected String remove_first_char(String str){

        StringBuilder new_str = new StringBuilder();

        for (int i = 1 ; i < str.length() ; i++){
            new_str.append(str.charAt(i));
        }

        return new_str.toString();
    }

    private boolean is_number_btn(String id) {

        for(String in : number_btns){
            if (id.equals(in)){
                return true;
            }
        }
        return false;
    }


    /*   Calculations   */
    boolean is_smaller(String s1, String s2){

        if(s1.length() < s2.length())
            return true;

        if(s2.length() < s1.length())
            return false;

        for(int i = 0 ; i < s1.length() ; i++){

            if(s1.charAt(i) > s2.charAt(i))
                return false;

            else if(s2.charAt(i) > s1.charAt(i))
                return true;
        }

        return false;
    }

    protected String shift(String s){

        int carry = 0;
        StringBuilder res = new StringBuilder();

        for(int i=0;i<s.length();i++){
            int t=(s.charAt(i) - '0') + carry;
            carry=(t%2)*10;
            res.append((char) ((t / 2) + '0'));
        }
        while(res.charAt(0) == '0' && res.length() > 1) {
            res = new StringBuilder(res.substring(1));
        }
        return res.toString();
    }

    protected String reverse_str(String str){

        StringBuilder rev = new StringBuilder();
        for (int i = str.length() - 1 ; i >= 0  ; i--){
            rev.append(str.charAt(i));
        }

        return rev.toString();
    }

    // MAIN FUNCTIONS

    protected String sum(String s1, String s2) {

        if (s1.length() > s2.length())
        {
            String temp = s1;
            s1 = s2 ;
            s2 = temp ;
        }

        s1 = reverse_str(s1) ;
        s2 = reverse_str(s2) ;

        StringBuilder str = new StringBuilder();
        int carry = 0;

        for (int i=0; i<s1.length(); i++)
        {
            int sum = ((s1.charAt(i) - '0')+(s2.charAt(i) - '0')+carry);
            str.append((char) (sum % 10 + '0'));

            carry = sum/10;
        }

        for (int i=s1.length(); i<s2.length(); i++)
        {
            int sum = ((s2.charAt(i) - '0') + carry);
            str.append((char) (sum % 10 + '0'));
            carry = sum/10;
        }

        if (carry > 0)
        {
            str.append((char) (carry + '0'));
        }

        str = new StringBuilder(reverse_str(str.toString()));

        return str.toString();
    }

    protected String find_diff(String s1, String s2){
        StringBuilder ans= new StringBuilder();
        if(is_smaller(s1,s2)){
            String temp = s1;
            s1 = s2 ;
            s2 = temp ;
        }

        s1 = reverse_str(s1);
        s2 = reverse_str(s2);

        int carry=0;

        for(int i=0;i<s2.length();i++){
            int sum=((s1.charAt(i)-'0') - (s2.charAt(i)-'0')-carry);

            if(sum<0){
                sum+=10;
                carry=1;
            }else
                carry=0;
            ans.append((char) (sum + '0'));

        }

        for(int i=s2.length();i<s1.length();i++){
            int sum=((int)(s1.charAt(i)-'0')-carry);
            if(sum<0) {
                sum += 10;
                carry = 1;
            }else
                carry=0;
            ans.append((char) (sum + '0'));
        }

        while (ans.charAt(ans.length()-1)=='0'&&ans.length()>1)
            ans = new StringBuilder(ans.substring(0, ans.length() - 1));

        ans = new StringBuilder(reverse_str(ans.toString()));
        int counter = 0 ;
        for(int i = 0; i < ans.length(); i++){
            if(ans.charAt(i) != '0')
                break;

            if (ans.charAt(0) == '0'){
                counter++;
            }
            else if(ans.charAt(i) == '0'){
                counter++;
            }
        }

        StringBuilder new_str = new StringBuilder();

        for (int i = counter ; i < ans.length() ; i++){
            new_str.append(ans.charAt(i));
        }

        return ans.toString();
    }

    protected String sub(String s1, String s2){

        if(is_smaller(s1, s2)){
            return "-" + find_diff(s1, s2);
        }

        return find_diff(s1, s2);
    }


    protected String multiply(String s1, String s2)
    {
        String res = "0";
        while (!(s1.equals("0"))) {
            if ((s1.charAt(s1.length() - 1) - '0') % 2 == 1) {
                res = sum(res, s2);
            }
            s2 = sum(s2, s2);
            s1 = shift(s1);
        }
        while (res.charAt(0) == '0' && res.length() > 1)
            res = res.substring(1);

        return res;
    }

    protected String divide(String s1, String s2){

        if(is_smaller(s1,s2))
            return "0";

        int length1 = s1.length() - s2.length(); // 1
        int length2 = s1.length() - s2.length() + 1; // 2

        StringBuilder lowerBound = new StringBuilder("1"); // 1

        for (int i = 0; i < length1 - 1; i++) { // 10
            lowerBound.append("0"); // 5678 , 234
            // 9999 , 100
        }

        StringBuilder upperBound = new StringBuilder();

        for (int i = 0; i < length2 ; i++) { // 99
            upperBound.append("9");
        }
        // up = 99, low = 10

        String ans = "";

        while (is_smaller("1", sub(upperBound.toString(), lowerBound.toString()))) { // (up - low) > 1
            ans = shift(sum(lowerBound.toString(), upperBound.toString())); // ans --> (up + low) / 2

            if (is_smaller(multiply(ans, s2), s1) || multiply(ans, s2).equals(s1)) { // if ans * s2 <= s1 --> mid * low_number <= s1
                // s1 / s2 = ?
                lowerBound = new StringBuilder(ans); // low = ans
            } else {
                upperBound = new StringBuilder(ans); // up = ans
            }
        }
        return lowerBound.toString();
    }


    /*  Integral-Related Methods  */

    protected String pow(String s1,String s2){
        String res="1";
        while (!(s2.equals("0"))){
            if((s2.charAt(s2.length()-1) - '0') % 2 == 1){
                res = multiply(res, s1);
            }
            s1 = multiply(s1, s1);
            s2 = shift(s2);
        }
        return res;
    }

    protected String pow_neg_or_pos(String s1, String s2){

        if (s1.charAt(0) == '-' && s2.charAt(0) == '-'){
            s1 = remove_first_char(s1);
            s2 = remove_first_char(s2);

            return "0";
        }
        else if(s1.charAt(0) == '-'){
            s1 = remove_first_char(s1);
            if ((s2.charAt(s2.length()-1) - '0') % 2 == 0){
                return pow(s1, s2);
            }
            return '-' + pow(s1, s2) ;
        }
        else if(s2.charAt(0) == '-'){
            s2 = remove_first_char(s2);

            if (s2.charAt(0) == '1' && s2.length() == 1 && s1.equals("1"))
                return "1";
            return "0";
        }
        return pow(s1, s2);
    }

    /*    handle Keyboard options     */

    KeyCode[] numKeys = {KeyCode.NUMPAD1, KeyCode.NUMPAD2, KeyCode.NUMPAD3,
            KeyCode.NUMPAD4, KeyCode.NUMPAD5, KeyCode.NUMPAD6,
            KeyCode.NUMPAD7, KeyCode.NUMPAD8, KeyCode.NUMPAD9,
            KeyCode.NUMPAD0};

    private boolean isNum(KeyEvent event){
        for(KeyCode in: numKeys){
            if (event.getCode() == in){
                return true;
            }
        }
        return false;
    }

    public void handleKeyPressed(KeyEvent evt) {

        if (isNum(evt)){
            text += evt.getText();
            display.setText(text);
            System.out.println(text);
        }
    }
}
