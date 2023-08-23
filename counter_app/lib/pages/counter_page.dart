import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../ui_parts/big_card.dart';
import '../counter_app_state.dart';
import '../web/rest_counter.dart';

class CounterHomePage extends StatelessWidget {
  final String phoneNumber;

  CounterHomePage({Key? key, required this.phoneNumber}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var appState = Provider.of<CounterAppState>(context);
    var counter = appState.value;

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('You are now sign-in with this phone:'),
            Text(phoneNumber),
            SizedBox(
              height: 15,
            ),
            Text('Click to add 1 to the counter:'),
            SizedBox(
              height: 30,
            ),
            BigCard(counter: counter),
            SizedBox(
              height: 30,
            ),
            ElevatedButton(
              onPressed: () {
                appState.increment();
                increaseCounter();
              },
              child: Text('Click'),
            ),
          ],
        ),
      ),
    );
  }
}


