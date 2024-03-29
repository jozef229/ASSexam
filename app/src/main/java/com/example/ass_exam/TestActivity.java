package com.example.ass_exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.behametrics.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {


    private TextView countLabel;
    private ImageView questionImage;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private int a = 0;
    private int b = 0;
    private int c = 0;

    private String rightAnswer;
    private String answer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"name image","right answer",""}
            {"image_encapsulated_implementation", "Encapsulated Implementation", "Encapsulated Implementation is Component Partitioning"},
            {"image_whole_part", "Whole Part", "Whole Part is Component Partitioning"},
            {"image_composite", "Composite", "Composite is Component Partitioning"},
            {"image_master_slave", "Master-Slave", "Master-Slave is Component Partitioning"},
            {"image_half_object_protocol", "Half-Object + Protocol", "Half-Object + Protocol is Component Partitioning"},
            {"image_replicated_component_group", "Replicated Component Group", "Replicated Component Group is Component Partitioning"},
            {"image_page_controller", "Page Controller", "Page Controller is Application Control"},
            {"image_front_controller", "Front Controller", "Front Controller is Application Control"},
            {"image_application_controller", "Application Controller", "Application Controller is Application Control"},
            {"image_command_processor", "Command Processor", "Command Processor is Application Control"},
            {"image_template_view", "Template View", "Template View is Application Control"},
            {"image_transform_view", "Transform View", "Transform View is Application Control"},
            {"image_firewall_proxy", "Firewall Proxy", "Firewall Proxy is Application Control"},
            {"image_authorization", "Authorization", "Authorization is Application Control"},
            {"image_observer", "Observer", "Observer is Object Interaction"},
            {"image_mediator", "Mediator", "Mediator is Object Interaction"},
            {"image_command", "Command", "Command is Object Interaction"},
            {"image_memento", "Memento", "Memento is Object Interaction"},
            {"image_data_transfer_object", "Data Transfer Object", "Data Transfer Object is Object Interaction"},
            {"image_message", "Message", "Message is Object Interaction"},
            {"image_bridge", "Bridge", "Bridge is Adaptation & Extension"},
            {"image_object_adapter", "Object Adapter", "Object Adapter is Adaptation & Extension"},
            {"image_chain_of_responsibility", "Chain of Responsibility", "Chain of Responsibility is Adaptation & Extension"},
            {"image_interpreter", "Interpreter", "Interpreter is Adaptation & Extension"},
            {"image_interceptor", "Interceptor", "Interceptor is Adaptation & Extension"},
            {"image_decorator", "Decorator", "Decorator is Adaptation & Extension"},
            {"image_visitor", "Visitor", "Visitor is Adaptation & Extension"},
            {"image_template_method", "Template Method", "Template Method is Adaptation & Extension"},
            {"image_strategy", "Strategy", "Strategy is Adaptation & Extension"},
            {"image_wrapper_facade", "Wrapper Facade", "Wrapper Facade is Adaptation & Extension"},
            {"image_null_object", "Null Object", "Null Object is Adaptation & Extension"},
            {"image_execute_around_object", "Execute-Around Object", "Execute-Around Object is Adaptation & Extension"},
            {"image_messaging", "Messaging", "Messaging is Distribution Infrastructure"},
            {"image_message_translator", "Message Translator", "Message Translator is Distribution Infrastructure"},
            {"image_message_endpoint", "Message Endpoint", "Message Endpoint is Distribution Infrastructure"},
            {"image_message_channel", "Message Channel", "Message Channel is Distribution Infrastructure"},
            {"image_message_router", "Message Router", "Message Router is Distribution Infrastructure"},
            {"image_publisher_subscriber", "Publisher-Subscriber", "Publisher-Subscriber is Distribution Infrastructure"},
            {"image_broker", "Broker", "Broker is Distribution Infrastructure"},
            {"image_client_proxy", "Client Proxy", "Client Proxy is Distribution Infrastructure"},
            {"image_requestor", "Requestor", "Requestor is Distribution Infrastructure"},
            {"image_invoker", "Invoker", "Invoker is Distribution Infrastructure"},
            {"image_client_request_handler", "Client Request Handler", "Client Request Handler is Distribution Infrastructure"},
            {"image_server_request_handler", "Server Request Handler", "Server Request Handler is Distribution Infrastructure"},
            {"image_reactor", "Reactor", "Reactor is Event Demultiplexing & Dispatching"},
            {"image_proactor", "Proactor", "Proactor is Event Demultiplexing & Dispatching"},
            {"image_acceptor_connector", "Acceptor-Connector", "Acceptor-Connector is Event Demultiplexing & Dispatching"},
            {"image_asynchronous_completion_token", "Asynchronous Completion Token", "Asynchronous Completion Token is Event Demultiplexing & Dispatching"},
            {"image_explicit_interface", "Explicit Interface", "Explicit Interface is Interface Partitioning"},
            {"image_extension_interface", "Extension Interface", "Extension Interface is Interface Partitioning"},
            {"image_introspective_interface", "Introspective Interface", "Introspective Interface is Interface Partitioning"},
            {"image_dynamic_invocation_interface", "Dynamic Invocation Interface", "Dynamic Invocation Interface is Interface Partitioning"},
            {"image_proxy", "Proxy", "Proxy is Interface Partitioning"},
            {"image_business_delegate", "Business Delegate", "Business Delegate is Interface Partitioning"},
            {"image_facade", "Facade", "Facade is Interface Partitioning"},
            {"image_combined_method", "Combined Method", "Combined Method is Interface Partitioning"},
            {"image_iterator", "Iterator", "Iterator is Interface Partitioning"},
            {"image_enumeration_method", "Enumeration Method", "Enumeration Method is Interface Partitioning"},
            {"image_batch_method", "Batch Method", "Batch Method is Interface Partitioning"},
            {"image_abstract_factory", "Abstract Factory", "Abstract Factory is Resource Management"},
            {"image_builder", "Builder", "Builder is Resource Management"},
            {"image_factory_method", "Factory Method", "Factory Method is Resource Management"},
            {"image_objects_for_states", "Objects for States", "Objects for States is Modal Behaviour"},
            {"image_mvc", "MVC", "MVC"},
            {"image_microkernel", "Microkernel", "Microkernel"},
            {"image_reflection", "Reflection", "Reflection"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        Logger.start(this);
        Logger.startNewSession();
        Logger.log("Test",
                "Start",
                getIntent().getStringExtra("PERSON_GENDER"),
                getIntent().getStringExtra("PERSON_HAND_TYPE"),
                getIntent().getStringExtra("PERSON_HAND"),
                getIntent().getStringExtra("PERSON_EMAIL"),
                getIntent().getStringExtra("PERSON_NAME"),
                getIntent().getStringExtra("PERSON_SURNAME"));

        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        a = -1;
        b = -1;
        c = -1;

        for(int i = 0; i < quizData.length; i++){
            ArrayList<String> tmpArray = new ArrayList<>();
            generationNumber(i);

            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[a][1]);
            tmpArray.add(quizData[b][1]);
            tmpArray.add(quizData[c][1]);

            quizArray.add(tmpArray);
        }

        showNextQuiz();


        answerBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkButtonAction(answerBtn1);
            }
        });
        answerBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkButtonAction(answerBtn2);
            }
        });
        answerBtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkButtonAction(answerBtn3);
            }
        });
        answerBtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                checkButtonAction(answerBtn4);
            }
        });
    }


    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void checkButtonAction(Button answerBtn) {
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(rightAnswer)){
            alertTitle = "Correct !!!";
            rightAnswerCount++;
            Logger.log("Test",
                    "Correct",
                    getIntent().getStringExtra("PERSON_GENDER"),
                    getIntent().getStringExtra("PERSON_HAND_TYPE"),
                    getIntent().getStringExtra("PERSON_HAND"),
                    getIntent().getStringExtra("PERSON_EMAIL"),
                    getIntent().getStringExtra("PERSON_NAME"),
                    getIntent().getStringExtra("PERSON_SURNAME"));
        }
        else{
            alertTitle = "Wrong !!!";
            Logger.log("Test",
                    "Wrong",
                    getIntent().getStringExtra("PERSON_GENDER"),
                    getIntent().getStringExtra("PERSON_HAND_TYPE"),
                    getIntent().getStringExtra("PERSON_HAND"),
                    getIntent().getStringExtra("PERSON_EMAIL"),
                    getIntent().getStringExtra("PERSON_NAME"),
                    getIntent().getStringExtra("PERSON_SURNAME"));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: " + answer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Logger.log("Test",
                        "OK",
                        getIntent().getStringExtra("PERSON_GENDER"),
                        getIntent().getStringExtra("PERSON_HAND_TYPE"),
                        getIntent().getStringExtra("PERSON_HAND"),
                        getIntent().getStringExtra("PERSON_EMAIL"),
                        getIntent().getStringExtra("PERSON_NAME"),
                        getIntent().getStringExtra("PERSON_SURNAME"));
                if(quizArray.size() < 1){
                    showResult();
                }
                else{
                    quizCount++;
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    public void showResult(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(rightAnswerCount + " / " + quizData.length);
        builder.setPositiveButton("Close and send data for experiment ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishLogger();
                SendEmail();
                onBackPressed();
                finish();
            }
        });
        builder.show();
    }

    public void finishLogger(){
        Logger.stop(this);
    }

    public void showNextQuiz(){
        countLabel.setText("Question " + quizCount + " / " + quizData.length + "    (Score: " + rightAnswerCount + ")" );
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());
        ArrayList<String> quiz = quizArray.get(randomNum);
        System.out.println(quiz.get(0));
        questionImage.setImageResource(getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        rightAnswer = quiz.get(2);
        answer = quiz.get(1);
        quiz.remove(0);
        quiz.remove(0);
        Collections.shuffle(quiz);

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        quizArray.remove(randomNum);
    }

    public void generationNumber(int i){
        Random random = new Random();
        int randomNum = 0;

        this.a = -1;
        this.b = -1;
        this.c = -1;
        int exitValue = 0;

        while(exitValue == 0){
//            System.out.println("aaaaaaaa///////////aaaaaaaaa "+ this.a + " " + this.b + " " + this.c + " " + i);
            randomNum = random.nextInt(quizData.length);
            if(randomNum == i) continue;
            if(this.a == -1){
                this.a = randomNum;
            }
            else if(this.b == -1){
                if(randomNum != this.a) this.b = randomNum;
            }
            else if(this.c == -1){
                if(randomNum != this.a && randomNum != this.b) this.c = randomNum;
            }
            if(this.a != -1 && this.b != -1 && this.c != -1) {
                exitValue = 1;
            }
        }
    }

    public static void copy(File src, File dst) throws IOException {
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        }
    }

    private void SendEmail(){

        Context con = getApplicationContext();
        File touch2 = new File(con.getFilesDir(),"/touch.csv");
        File custom2 = new File(con.getFilesDir(),"/custom.csv");
        File orientation2 = new File(con.getFilesDir(),"/orientation.csv");
        File sensor_gravity2 = new File(con.getFilesDir(),"/sensor_gravity.csv");
        File sensor_accelerometer2 = new File(con.getFilesDir(),"/sensor_accelerometer.csv");
        File sensor_game_rotation_vector2 = new File(con.getFilesDir(),"/sensor_game_rotation_vector.csv");
        File sensor_linear_acceleration2 = new File(con.getFilesDir(),"/sensor_linear_acceleration.csv");
        File sensor_magnetic_field2 = new File(con.getFilesDir(),"/sensor_magnetic_field.csv");

        touch2.delete();
        custom2.delete();
        orientation2.delete();
        sensor_accelerometer2.delete();
        sensor_game_rotation_vector2.delete();
        sensor_linear_acceleration2.delete();
        sensor_magnetic_field2.delete();
        sensor_gravity2.delete();

        File touch = new File(con.getFilesDir(),"/logs/touch.csv");
        File custom = new File(con.getFilesDir(),"/logs/custom.csv");
        File orientation = new File(con.getFilesDir(),"/logs/orientation.csv");
        File sensor_gravity = new File(con.getFilesDir(),"/logs/sensor_gravity.csv");
        File sensor_accelerometer = new File(con.getFilesDir(),"/logs/sensor_accelerometer.csv");
        File sensor_game_rotation_vector = new File(con.getFilesDir(),"/logs/sensor_game_rotation_vector.csv");
        File sensor_linear_acceleration = new File(con.getFilesDir(),"/logs/sensor_linear_acceleration.csv");
        File sensor_magnetic_field = new File(con.getFilesDir(),"/logs/sensor_magnetic_field.csv");

        try { copy(touch,touch2); }catch (IOException e) {}
        try { copy(custom,custom2); }catch (IOException e) {}
        try { copy(orientation,orientation2); }catch (IOException e) {}
        try { copy(sensor_accelerometer,sensor_accelerometer2); }catch (IOException e) {}
        try { copy(sensor_game_rotation_vector,sensor_game_rotation_vector2); }catch (IOException e) {}
        try { copy(sensor_gravity,sensor_gravity2); }catch (IOException e) {}
        try { copy(sensor_magnetic_field,sensor_magnetic_field2); }catch (IOException e) {}
        try { copy(sensor_linear_acceleration,sensor_linear_acceleration2); }catch (IOException e) {}

        touch.delete();
        custom.delete();
        orientation.delete();
        sensor_accelerometer.delete();
        sensor_game_rotation_vector.delete();
        sensor_linear_acceleration.delete();
        sensor_magnetic_field.delete();
        sensor_gravity.delete();
//
//        try { touch.createNewFile(); }catch (IOException e) {}
//        try { custom.createNewFile(); }catch (IOException e) {}
//        try { orientation.createNewFile(); }catch (IOException e) {}
//        try { sensor_accelerometer.createNewFile(); }catch (IOException e) {}
//        try { sensor_game_rotation_vector.createNewFile(); }catch (IOException e) {}
//        try { sensor_linear_acceleration.createNewFile(); }catch (IOException e) {}
//        try { sensor_magnetic_field.createNewFile(); }catch (IOException e) {}
//        try { sensor_gravity.createNewFile(); }catch (IOException e) {}


        Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { "assexam2019@gmail.com" });
        i.putExtra(Intent.EXTRA_SUBJECT,"AssExamApp - sensor data");
        i.putExtra(Intent.EXTRA_TEXT, "Sensor data :D");

        ArrayList<Uri> uris = new ArrayList<Uri>();

//        Uri uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", touch);
//        i.putExtra(Intent.EXTRA_STREAM, uri);

        Uri uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", touch2);
        uris.add(uri);
        uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", custom2);
        uris.add(uri);
        uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", orientation2);
        uris.add(uri);
        uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", sensor_accelerometer2);
        uris.add(uri);
        uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", sensor_game_rotation_vector2);
        uris.add(uri);
        uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", sensor_linear_acceleration2);
        uris.add(uri);
        uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", sensor_magnetic_field2);
        uris.add(uri);
        uri = FileProvider.getUriForFile(con, con.getPackageName() + ".files", sensor_gravity2);
        uris.add(uri);

        i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        i.setType("message/rfc822");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        con.startActivity(i);
    }
}
