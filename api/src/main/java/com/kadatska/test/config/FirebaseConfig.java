package com.kadatska.test.config;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseConfig {
    @PostConstruct
    void firebaseAuth() throws IOException {
        // FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = FirebaseOptions.builder()
                // .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            // $env:GOOGLE_APPLICATION_CREDENTIALS="path\to\service-account-file.json"
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();

        FirebaseApp.initializeApp(options);
    }
}
