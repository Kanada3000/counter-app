import 'package:flutter/material.dart';

import '../web/firebase_auth.dart';

class VerifyingPage extends StatelessWidget {
  final String phoneNumber;
  final smsController = TextEditingController();

  VerifyingPage({Key? key, required this.phoneNumber}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            SizedBox(
              width: 300,
              child: TextField(
                controller: smsController,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  hintText: 'Enter the code from SMS',
                ),
              ),
            ),
            SizedBox(
              height: 30,
            ),
            ElevatedButton(
              onPressed: () {
                verifying(context, smsController.text, phoneNumber);
              },
              child: Text('Enter'),
            ),
            
            Text("We sent SMS with code on this phone: "),
            Text(phoneNumber),
          ],
        ),
      ),
    );
  }
}
