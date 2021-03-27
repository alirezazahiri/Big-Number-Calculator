package com.main_dir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;


public class IntegralController extends MainController {

    protected Main main;


    @FXML
    Button back;

    @FXML
    TextField coef, power,
                lowerbound, upperbound;

    @FXML
    TextArea integral_field;

    String show = "";

    public void setMain(Main main) {
        this.main = main;
    }

    public String latest_result , processor = "0";

    public void handleBack(ActionEvent event){
        show = "";
        latest_result = "";
        processor = "";
        main.mainWindow();
    }

    @FXML
    TextArea result_area ;
    public void handleCalculate(){
        result_area.setText(processor);
    }

    public void handleAdd(){
        String x_lower = lowerbound.getText();
        String x_upper = upperbound.getText();
        String coefficient = coef.getText();
        String pow = power.getText();

        System.out.println("(" + coefficient + ")X^(" + pow + ")");

        show += " (" + coefficient + ")X^(" + pow + ") +";
        integral_field.setText(remove_last_char(show));

        String new_pow = sum_neg_or_pos(power.getText(), "1");
        String new_coef = div_neg_or_pos(coefficient, new_pow);

        System.out.println("new coef : " + new_coef);
        System.out.println("new pow : " + new_pow);

        System.out.println("(" + new_coef + ")X^(" + new_pow + ")");

        String upperbound_result = calc_upper(new_coef, new_pow, x_upper);
        String lowerbound_result = calc_lower(new_coef, new_pow, x_lower);

        latest_result = sub_neg_or_pos(upperbound_result, lowerbound_result);

        add_to_others(latest_result);

        coef.setText(null);
        power.setText(null);

    }

    private void add_to_others(String result) {
        processor = sum_neg_or_pos(processor, result);
        System.out.println("result till now: " + processor);
    }

    private String calc_lower(String coef, String pow, String x) {
        if (lowerbound.getText().isEmpty()){
            System.out.println("Error! Fill the LowerBound Field!");
            return "0";
        }
        return mul_neg_or_pos(coef, pow(x, pow));
    }

    private String calc_upper(String coef, String pow, String x) {
        if (upperbound.getText().isEmpty()){
            System.out.println("Error! Fill the UpperBound Field!");
            return "0";
        }
        return mul_neg_or_pos(coef, pow_neg_or_pos(x, pow));
    }
}
