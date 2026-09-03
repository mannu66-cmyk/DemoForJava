package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

interface PaymentStrategy {
 String getType();
 void process(double amount);
}
@Component
class UpiPaymentStrategy implements PaymentStrategy {@Override
public String getType() {
 return "UPI";
}
 @Override
 public void process(double amount) {
  // UPI logic
 }
}
@Component
class CardPaymentStrategy implements PaymentStrategy {
 @Override
 public String getType() {
  return "CARD";
 }
 @Override
 public void process(double amount) {
  // Card logic
 }
}
@Service
public class PaymentService {
 private final Map<String, PaymentStrategy> strategies;
 public PaymentService(
         List<PaymentStrategy> strategyList) {
  this.strategies = strategyList.stream()
          .collect(Collectors.toMap(
                  PaymentStrategy::getType,
                  x->x
          ));
  for (Map.Entry<String, PaymentStrategy> p:this.strategies.entrySet()){
   System.out.println(p.getKey()+" "+p.getValue());
  }
 }
 public void process(String type, double amount) {
  PaymentStrategy strategy = strategies.get(type);
  if (strategy == null) {
   throw new IllegalArgumentException(
           "Unsupported payment type: " + type);
  }
  strategy.process(amount);
 }
}


