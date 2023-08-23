import 'package:firebase_auth/firebase_auth.dart';

import '../pages/counter_page.dart';
import '../ui_parts/loading_indicator_dialog.dart';
import 'package:flutter/material.dart';

void verifying(context, smsCode, phoneNumber) async {
    FirebaseAuth auth = FirebaseAuth.instance;
    LoadingIndicatorDialog().show(context);
    await auth.verifyPhoneNumber(
      phoneNumber: phoneNumber,
      verificationCompleted: (PhoneAuthCredential credential) async {
        // ANDROID ONLY!

        // Sign the user in (or link) with the auto-generated credential
        await auth.signInWithCredential(credential);
        Navigator.push(context,
                    MaterialPageRoute(builder: (context) => CounterHomePage(phoneNumber: phoneNumber)));
      },
      verificationFailed: (FirebaseAuthException e) {
        LoadingIndicatorDialog().dismiss();
        if (e.code == 'invalid-phone-number') {
          print('The provided phone number is not valid.');
        } else {
          print('Something went wrong!');
        }

        showDialog(
          context: context,
          builder: (BuildContext context) {
            return AlertDialog(
              title: Text("Error"),
              content: Text(e.code),
              actions: [
                ElevatedButton(
                  child: Text("Ok"),
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                )
              ],
            );
            });
      },
      codeSent: (String verificationId, int? resendToken) async {
        // Create a PhoneAuthCredential with the code
        PhoneAuthCredential credential = PhoneAuthProvider.credential(
            verificationId: verificationId, smsCode: smsCode);

        // Sign the user in (or link) with the credential
        await auth.signInWithCredential(credential);
        Navigator.push(context,
                    MaterialPageRoute(builder: (context) => CounterHomePage(phoneNumber: phoneNumber)));
      },
      codeAutoRetrievalTimeout: (String verificationId) {},
    );
    LoadingIndicatorDialog().dismiss();
  }