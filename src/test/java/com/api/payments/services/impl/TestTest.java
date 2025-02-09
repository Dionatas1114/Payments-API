package com.api.payments.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestTest {

    public static List<Order> getPedidos(){
        return Arrays.asList(
                new Order(1, 20.00, Order.OrderStatus.CANCELLED),
                new Order(2, 25.00, Order.OrderStatus.PENDING),
                new Order(3, 35.00, Order.OrderStatus.SHIPPED),
                new Order(4, 85.00, Order.OrderStatus.PENDING),
                new Order(5, 75.00, Order.OrderStatus.DELIVERED),
                new Order(6, 25.00, Order.OrderStatus.DELIVERED),
                new Order(7, 15.00, Order.OrderStatus.CANCELLED),
                new Order(8, 95.00, Order.OrderStatus.SHIPPED)

        );
    }

    public List<Order> filterByValue(List<Order> list, double minValue){
        return list.stream().filter(item -> item.totalAmount > minValue).collect(Collectors.toList());
    }

    public Map<Order.OrderStatus, List<Order>> filterByStatus(List<Order> list){
        return list.stream().collect(Collectors.groupingBy(Order::getStatus));
    }

    private double filterByTotal(List<Order> list, Order.OrderStatus status) {
        return list.stream()
                .filter(item -> status.equals(item.getStatus()))
                .mapToDouble(item -> item.totalAmount).sum();
    }

    @Test
    @DisplayName("test1")
    public void testGetPedidos() {
        // 1
        List<Order> list = getPedidos();
        List<Order> list2 = filterByValue(list, 25.00);

        List<Order> orders = Arrays.asList(
                new Order(3, 35.00, Order.OrderStatus.SHIPPED),
                new Order(4, 85.00, Order.OrderStatus.PENDING),
                new Order(5, 75.00, Order.OrderStatus.DELIVERED),
                new Order(8, 95.00, Order.OrderStatus.SHIPPED)
        );

        assertEquals(list2.size(), orders.size());
        assertEquals(list2.get(0).id, orders.get(0).id);
        assertEquals(list2.get(1).id, orders.get(1).id);
        assertEquals(list2.get(2).id, orders.get(2).id);
        assertEquals(list2.get(3).id, orders.get(3).id);
    }

    @Test
    @DisplayName("test2")
    public void testGetPedidosByStatus() {
        // 2
        List<Order> list = getPedidos();

        Map<Order.OrderStatus, List<Order>> list2 = filterByStatus(list);

        assertEquals(2, list2.get(Order.OrderStatus.SHIPPED).size());
        assertEquals(2, list2.get(Order.OrderStatus.PENDING).size());
        assertEquals(2, list2.get(Order.OrderStatus.CANCELLED).size());
        assertEquals(2, list2.get(Order.OrderStatus.DELIVERED).size());
    }
    
    
    @Test
    @DisplayName("test3")
    public void testGetPedidosByTotal() {
        // 3
        List<Order> list = getPedidos();

        Order.OrderStatus delivered = Order.OrderStatus.DELIVERED;
        double total = filterByTotal(list, delivered);

        List<Order> orders = Arrays.asList(
                new Order(3, 35.00, Order.OrderStatus.SHIPPED),
                new Order(4, 85.00, Order.OrderStatus.PENDING),
                new Order(5, 75.00, Order.OrderStatus.DELIVERED),
                new Order(8, 95.00, Order.OrderStatus.SHIPPED)

        );

        assertEquals(100.00, total);
    }

    @Test
    @DisplayName("test4")
    public void test4() {
        String a = "12321";
        String space = " ";
        String repeat = space.repeat(10);
        String result = "test: " + repeat + "1";

        String expect = "test:           1";

        assertEquals(expect, result);
    }

    @Test
    @DisplayName("test5")
    public void test5() {
        // Verified if reversed is equal.
        String a = "12321";
        String reversed = new StringBuilder(a).reverse().toString();

        assertEquals(a, reversed);
    }

    @Test
    @DisplayName("test6")
    public void test6() {
        // Write a Java program to calculate the average of a list of integers using streams.
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
//        Collections.addAll(list, 1, 2, 3, 4, 5);
        int total = 0;
        for (Integer integer : list) {
            total = total + integer;
        }
        int average = total / list.size();
        assertEquals(21, total);
        assertEquals(3, average);
    }

    @Test
    @DisplayName("test7")
    public void test7() {
//        Class student = Student.class;
//        Method[] methods = student.getDeclaredMethods();
//
//        ArrayList<String> methodList = new ArrayList<>();
//        for(Method method : methods){
//            methodList.add(method.getName());
//        }
//        Collections.sort(methodList);
//        for(String name: methodList){
//            System.out.println(name);
//        }

//        assertEquals(a, reversed);
    }

    @Test
    @DisplayName("test8")
    public void test8() {
        String initialString = "12321";
        int stringConvertedToInt = Integer.parseInt(initialString); // Convert String to Int.

        int initialInteger = 12321;
        String intConvertedToString = String.valueOf(initialInteger); // Convert Int to String.

        assertEquals(initialInteger, stringConvertedToInt);
        assertEquals(initialString, intConvertedToString);
    }

    public boolean isOdd(int number) {
        return number % 2 != 0;
    }

    @Test
    @DisplayName("test9")
    public void test9() {
        int initialInteger = 12321;

        boolean odd = isOdd(initialInteger); // Verified if isOdd.

        assertEquals(true, odd);
    }
}
