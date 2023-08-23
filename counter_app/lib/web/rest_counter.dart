import 'dart:convert';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:http/http.dart' as http;

Future<String> get token async => (await FirebaseAuth.instance.currentUser!.getIdToken())!;
Future<Map<String, String>> headers() async => {
      "Authorization": "Bearer ${(await token)}",
    };

Future<int> getCounter() async {
  final response = await http.get(Uri.parse('http://localhost:8080/counter'), headers: await headers());

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
  final response = await http.post(Uri.parse('http://localhost:8080/counter'), headers: await headers());

  if (response.statusCode != 200) {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to update counter');
  }
}