import 'package:firebase_ui_auth/firebase_ui_auth.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'counter_app_state.dart';
import 'pages/phone_page.dart';
import 'package:firebase_core/firebase_core.dart';
import 'web/firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  FirebaseUIAuth.configureProviders([
    PhoneAuthProvider(),
  ]);

  runApp(ChangeNotifierProvider(
    create: (context) => CounterAppState(),
    child: MaterialApp(
      title: 'Counter App',
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.green),
      ),
      home: PhonePage(),
    ),
  ));
}
