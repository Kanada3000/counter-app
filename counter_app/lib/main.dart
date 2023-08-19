import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(CounterApp());
}

class CounterApp extends StatelessWidget {
  const CounterApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => CounterAppState(),
      child: MaterialApp(
        title: 'Counter App',
        theme: ThemeData(
          useMaterial3: true,
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.green),
        ),
        home: CounterHomePage(),
      ),
    );
  }
}

Future<int> getCounter() async {
  final response = await http
      .get(Uri.parse('http://localhost:8080/get-counter'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return jsonDecode(response.body)['number'];
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load counter');
  }
}

void increaseCounter() async {
  final response = await http
      .post(Uri.parse('http://localhost:8080/increase-counter'));

  if (response.statusCode != 200) {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to update counter');
  }
}

class CounterAppState extends ValueNotifier {
  CounterAppState() : super(0){
    //Get the value from response.
    getCounter().then((x) => value = x);
  }

  void increment(){
    value ++;
    notifyListeners();
  }
}

class CounterHomePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    var appState = Provider.of<CounterAppState>(context);
    var counter = appState.value;

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Click to add 1 to counter:'),
            SizedBox(height: 30,),
            BigCard(counter: counter),
            SizedBox(height: 30,),
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

class BigCard extends StatelessWidget {
  const BigCard({
    super.key,
    required this.counter,
  });

  final int counter;

  @override
  Widget build(BuildContext context) {
    var theme = Theme.of(context);
    var style = theme.textTheme.displayMedium!.copyWith(
      color: theme.colorScheme.onPrimary,
    );

    return Card(
      color: theme.colorScheme.primary,
      child: Padding(
        padding: const EdgeInsets.all(20),
        child: Text(counter.toString(), style: style),
      ),
    );
  }
}