#include <iostream>
#include <stack>
#include <cmath>
#include <cctype>
#include "CALCprocess.h"
using namespace std;




bool isoperator(char ch) {
    return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
}

double operation(char ch, double x, double y) {
    switch (ch) {
        case '+': return x + y;
        case '-': return x - y;
        case '*': return x * y;
        case '/': return x / y;
        case '^': return pow(x, y);
        default: cout << "Error!!" << endl; return 0;
    }
}

int precedence(char ch) {
    if (ch == '^') return 3;
    if (ch == '*' || ch == '/') return 2;
    if (ch == '+' || ch == '-') return 1;
    return 0;
}

bool parenthesis_checking(string exp) {
    stack<char> s;
    for (char c : exp) {
        if (c == '(') s.push(c);
        else if (c == ')') {
            if (s.empty()) return false;
            s.pop();
        }
    }
    return s.empty();
}

string infix_to_postfix(string exp) {
    string result;
    stack<char> s;
    bool last_was_operator = true; // Tracks if last character was an operator (to detect unary minus)
    
    for (size_t i = 0; i < exp.length(); ++i) {
        char c = exp[i];
        
        if (isdigit(c)) {
            // Handle multi-digit numbers
            while (i < exp.length() && (isdigit(exp[i]) || exp[i] == '.')) {
                result += exp[i++];
            }
            result += ' ';  // Separate numbers with space in postfix
            i--; // adjust position since we moved ahead
            last_was_operator = false;
        } else if (c == '(') {
            s.push(c);
            last_was_operator = true;
        } else if (c == ')') {
            while (!s.empty() && s.top() != '(') {
                result += s.top();
                s.pop();
            }
            s.pop(); // remove '('
            last_was_operator = false;
        } else if (isoperator(c)) {
            if (c == '-' && last_was_operator) { // Handle unary minus
                result += '0'; // add 0 to handle cases like (-3) as (0-3)
                result += ' ';
            }
            while (!s.empty() && precedence(s.top()) >= precedence(c)) {
                result += s.top();
                s.pop();
            }
            s.push(c);
            last_was_operator = true;
        }
    }
    
    while (!s.empty()) {
        result += s.top();
        s.pop();
    }
    return result;
}

double expression_evaluation(string exp) {
    stack<double> s;
    string token;
    
    for (size_t i = 0; i < exp.length(); ++i) {
        if (isdigit(exp[i]) || (exp[i] == '-' && isdigit(exp[i + 1]))) {
            // Parse the full number including negative sign if present
            token.clear();
            while (i < exp.length() && (isdigit(exp[i]) || exp[i] == '.' || exp[i] == '-')) {
                token += exp[i++];
            }
            i--; // adjust position
            s.push(stod(token));
        } else if (isoperator(exp[i])) {
            double val1 = s.top(); s.pop();
            double val2 = s.top(); s.pop();
            s.push(operation(exp[i], val2, val1));
        }
    }
    return s.top();
}

string Algebraic_process(string exp) {
    if (parenthesis_checking(exp)) {
        string process = infix_to_postfix(exp);
        return to_string(expression_evaluation(process));
    } else {
        return " " ;
    }
}



extern "C" JNIEXPORT jstring JNICALL Java_CALCprocess_
  (JNIEnv *env, jobject obj, jstring message) {
    // Convert Java string to C++ string
    const char *nativeMessage = env->GetStringUTFChars(message, nullptr);
    std::string response = Algebraic_process(std::string(nativeMessage));
    
    // Release memory
    env->ReleaseStringUTFChars(message, nativeMessage);

    // Return a new Java string
    return env->NewStringUTF(response.c_str());
}

int main() {
    string exp;
    cout << "Enter your expression: ";
    cin >> exp;
    Algebraic_process(exp);
    return 0;
}