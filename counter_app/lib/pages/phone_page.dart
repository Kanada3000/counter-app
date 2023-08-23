import 'package:flutter/material.dart';

import 'verifying_page.dart';

class PhonePage extends StatelessWidget {
  final phoneController = TextEditingController();

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
                controller: phoneController,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  hintText: 'Enter your phone',
                ),
              ),
            ),
            SizedBox(
              height: 30,
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => VerifyingPage(phoneNumber:phoneController.text)));
              },
              child: Text('Enter'),
            ),
            SizedBox(
              height: 50,
            ),
            Text('We will send you a verifying code :)'),
          ],
        ),
      ),
    );
  }
}
