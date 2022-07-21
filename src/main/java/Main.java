import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        /* * [
         * Order(DELIVERY, "EUR", 2000),
         * Order (DELIVERY, "USD", 15),
         * Order (DELIVERY, "RUB", 200),
         * Order (PICKUP, "RUB", 1250),
         * Order (DELIVERY, "USD", 35),
         * Order (PICKUP, "USD", 55),
         * Order (DELIVERY, "RUB", 100)
         * ]*/
        List<OrderService.OrderData> orderDataList = new ArrayList<>();
        orderDataList.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "EUR", 2000L));
        orderDataList.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "USD", 15L));
        orderDataList.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "RUB", 200L));
        orderDataList.add(new OrderService.OrderData(OrderService.Type.PICKUP, "RUB", 1250L));
        orderDataList.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "USD", 35L));
        orderDataList.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "USD", 5L));
        orderDataList.add(new OrderService.OrderData(OrderService.Type.PICKUP, "USD", 55L));
        orderDataList.add(new OrderService.OrderData(OrderService.Type.DELIVERY, "RUB", 100L));

        OrderService service = new OrderService();
        Map<String, Double> collect4 = service.getMaxMinusMinDeliveryMapByCurrency(orderDataList);
        double[] doubles = new double[]{2, 2, 1, 4, 2, 3, 2};
        task(doubles);
    }

    /*Порядок чисел в оригинальном массиве должен быть сохранён.
    Из дубликатов нужно оставлять последний элемент, например, для {2,1,4,2,3}
    правильное решение - {1,4,2,3}, а не {2,1,4,3}

    В случае, если во входном массиве есть элемент меньше 0, то нужно выдавать ошибку.
    Например, для {2,3,-1,5} обработка должна закончиться ошибкой.
    */
    public static double[] task(double[] a) {
        if (Arrays.stream(a).anyMatch(x -> x < 0))
            throw new IllegalArgumentException("в входном массиве есть число <0");
        List<Double> dup = Arrays.stream(a).boxed().collect(Collectors.toList());
        Collections.reverse(dup);
        List<Double> s = dup.stream().distinct().collect(Collectors.toList());
        Collections.reverse(s);
        s.forEach(System.out::print);
        return s.stream().mapToDouble(d -> d).toArray();
    }
}