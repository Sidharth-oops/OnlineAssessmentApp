package com.example.quizc;

import com.google.gson.annotations.SerializedName;

public class Questions {
    public String qId;
    public String  getqId(){
        return  qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }
    String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public class Answers{
         public String answer_a;

        public String getAnswer_a() {
            return answer_a;
        }

        public void setAnswer_a(String answer_a) {
            this.answer_a = answer_a;

        }

        public String answer_b;

        public String getAnswer_b() {
            return answer_b;
        }

        public void setAnswer_b(String answer_b) {
            this.answer_b = answer_b;
        }
        public String answer_c;

        public String getAnswer_c() {
            return answer_c;
        }

        public void setAnswer_c(String answer_c) {
            this.answer_c = answer_c;
        }
        public String answer_d;

        public String getAnswer_d() {
            return answer_d;
        }

        public void setAnswer_d(String answer_d) {
            this.answer_d = answer_d;
        }
        public String answer_e;

        public String getAnswer_e() {
            return answer_e;
        }

        public void setAnswer_e(String answer_e) {
            this.answer_e = answer_e;
        }
        public  String answer_f;

        public String getAnswer_f() {
            return answer_f;
        }

        public void setAnswer_f(String answer_f) {
            this.answer_f = answer_f;
        }
    }
    Answers answers;

    public class CorrectAnswers {
        @SerializedName("answer_a_correct")
        String ansAC;
        @SerializedName("answer_b_correct")
        String ansBC;
        @SerializedName("answer_c_correct")
        String ansCC;
        @SerializedName("answer_d_correct")
        String ansDC;
        @SerializedName("answer_e_correct")
        String ansEC;
        @SerializedName("answer_f_correct")
        String ansFC;

        public String getAnsAC() {
            return ansAC;
        }

        public void setAnsAC(String ansAC) {
            this.ansAC = ansAC;
        }

        public String getAnsBC() {
            return ansBC;
        }

        public void setAnsBC(String ansBC) {
            this.ansBC = ansBC;
        }

        public String getAnsCC() {
            return ansCC;
        }

        public void setAnsCC(String ansCC) {
            this.ansCC = ansCC;
        }

        public String getAnsDC() {
            return ansDC;
        }

        public void setAnsDC(String ansDC) {
            this.ansDC = ansDC;
        }

        public String getAnsEC() {
            return ansEC;
        }

        public void setAnsEC(String ansEC) {
            this.ansEC = ansEC;
        }

        public String getAnsFC() {
            return ansFC;
        }

        public void setAnsFC(String ansFC) {
            this.ansFC = ansFC;
        }
    }
    CorrectAnswers correct_answers;


}
