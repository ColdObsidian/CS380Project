package com.example.brewbuddycs380;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Find create account button
        Button createAccountButton = (Button) findViewById(R.id.createAccount);

        // Set onClickListener for create account button
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set username, password, and confirm password EditText fields
                EditText usernameEditText = (EditText) findViewById(R.id.username);
                EditText passwordEditText = (EditText) findViewById(R.id.password);
                EditText confirmPasswordEditText = (EditText) findViewById(R.id.confirmPassword);

                // Get text from username, password, and confirm password EditText fields
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Check if any fields are empty
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    // Display toast message asking user to fill in all fields
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
<<<<<<< Updated upstream
                    return;
                }

                if (!password.equals(confirmPassword)) {
=======
                } else if (password.equals(confirmPassword)) { // Check if password and confirm password match

                        // Create instance of UserService and attempt to create account
                    //Toast.makeText(getApplicationContext(), "doing", Toast.LENGTH_SHORT).show();

                        //workaround to get around the networkOnMainThreadException
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        //endworkaround
                        GreetClient.test();
                        //Database.connect();
                        boolean success = false;//Database.createUser(username, password);
                        Toast.makeText(getApplicationContext(), "doing"+success, Toast.LENGTH_SHORT).show();
                        if (success) {
                            Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Account creation failed", Toast.LENGTH_SHORT).show();
                        }


                } else {
>>>>>>> Stashed changes
                    // Display toast message indicating passwords do not match
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    boolean success = UserService.createAccount(username, password);
                    if (success) {
                        Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                        usernameEditText.getText().clear();
                        passwordEditText.getText().clear();
                        confirmPasswordEditText.getText().clear();
                    } else {
                        Toast.makeText(getApplicationContext(), "Account creation failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (AccountTakenException e) {
                    Toast.makeText(getApplicationContext(), "Account username taken", Toast.LENGTH_SHORT).show();
                    return;
                } catch (UserServiceException e) {
                    Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
