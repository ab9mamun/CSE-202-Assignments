package serverpkg;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Exam {
    ServerMain main;

    private boolean flag;

    private  String name;
    private  int startTime;
    private  int duration;
    private  int warningTime;
    private  int backupInterval;
    private  String validIDs;
    private  String allowableApps;
    private String answerPath;
    private String questionPath;
    private  String rules;

    private ArrayList<Examinee> examinees;


    public Exam(ServerMain main, String name, int startTime, int duration, int warningTime,
                int backupInterval, String validIDs, String allowableApps,
                String answerPath, String questionPath, String rules) {

        examinees = new ArrayList<>();

        this.main = main;

        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        this.warningTime = warningTime;
        this.backupInterval = backupInterval;
        this.validIDs = validIDs;
        this.allowableApps = allowableApps;
        this.answerPath = answerPath;
        this.questionPath = questionPath;
        this.rules = rules;
    }

    public String registerRequest(int stdID, Socket socket){
        String ip = socket.getInetAddress().getHostAddress();
        for(Examinee x:examinees){
                String i = x.getIp();
                int id = x.getStdID();

            if(id==stdID && i.equals(ip)) {
                return "REJECTED:You have already registered.\n";
            }
            if(i.equals(ip)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                    break;
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                return "REJECTED:Registration denied.";
            }
            if(id==stdID){
               Platform.runLater(()->{
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setTitle("Confirmation Dialog");
                   alert.setHeaderText("Look, a Confirmation Dialog");
                   alert.setContentText("Are you ok with this?");

                   Optional<ButtonType> result = alert.showAndWait();
                   if (result.get() == ButtonType.OK){
                       // ... user chose OK
                       flag = true;

                   } else {
                       // ... user chose CANCEL or closed the dialog
                       flag = false;
                   }
               });
                if(flag) break;
                return "REJECTED:Registration denied.";

            }


        }
        register(stdID,socket);
        return "ACCEPTED:"+name+"|"+startTime+"|"+currentTime()+"|"+duration+"|"+backupInterval+"|"+rules+"|"+allowableApps;
    }
    private void register(int stdid, Socket socket){
        examinees.add(new Examinee(stdid, socket));

    }




    public String currentTime(){
        return "asfdlkj";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWarningTime() {
        return warningTime;
    }

    public void setWarningTime(int warningTime) {
        this.warningTime = warningTime;
    }

    public int getBackupInterval() {
        return backupInterval;
    }

    public void setBackupInterval(int backupInterval) {
        this.backupInterval = backupInterval;
    }

    public String getValidIDs() {
        return validIDs;
    }

    public void setValidIDs(String validIDs) {
        this.validIDs = validIDs;
    }

    public String getAllowableApps() {
        return allowableApps;
    }

    public void setAllowableApps(String allowableApps) {
        this.allowableApps = allowableApps;
    }

    public String getAnswerPath() {
        return answerPath;
    }

    public void setAnswerPath(String answerPath) {
        this.answerPath = answerPath;
    }

    public String getQuestionPath() {
        return questionPath;
    }

    public void setQuestionPath(String questionPath) {
        this.questionPath = questionPath;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
