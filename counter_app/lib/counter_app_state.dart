import 'package:flutter/material.dart';
import 'web/rest_counter.dart';

class CounterAppState extends ValueNotifier {
  CounterAppState() : super(0) {
    //Get the value from response.
    getCounter().then((x) => value = x);
  }

  void increment() {
    value++;
    notifyListeners();
  }
}