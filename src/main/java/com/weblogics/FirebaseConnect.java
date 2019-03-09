/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.weblogics;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * 
 * @author Abhishek Abhinav
 */
public class FirebaseConnect {
    public static void getConnection() throws FileNotFoundException, IOException {
        //----------------Firebase Connection-------------------//
        URL url = new URL("https://device-pics.firebaseapp.com/$confidential$/serviceAccountKey.json");
        InputStream serviceAccount
                = url.openStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://device-search-review-system.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        
        //-----------------------------------------------------//
    }
}
