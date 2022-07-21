import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

/*
 * Посчитайте для каждой валюты разницу между самым большим и самым маленьким заказом типа DELIVERY.
 * Верните результат вычисления для каждой валюты.
 * Валюты в результате должны находиться в порядке возрастания этой разницы. */
class OrderService {
    enum Type {DELIVERY, PICKUP}

    static class OrderData {
        final Type type;
        final String currency;
        final Long amount;

        OrderData(@NotNull Type type,
                  @NotNull String currency,
                  @NotNull Long amount) {
            this.type = type;
            this.currency = currency;
            this.amount = amount;
        }

        String getCurrency() {
            return currency;
        }

        Long getAmount() {
            return amount;
        }

        Type getType() {
            return type;
        }
    }

    /**
     * Возвращает map вида [валюта (в порядке возрастания разницы) – разница между самым большим и маленьким заказом типа DELIVERY для валюты].
     * Если по какой-то валюте только один заказ, то он является и самым большим и самым маленьким и разница равна 0.
     * Пример входных данных:
     * [
     * Order(DELIVERY, "EUR", 2000),
     * Order (DELIVERY, "USD", 15),
     * Order (DELIVERY, "RUB", 200),
     * Order (PICKUP, "RUB", 1250),
     * Order (DELIVERY, "USD", 35),
     * Order (PICKUP, "USD", 55),
     * Order (DELIVERY, "RUB", 100)
     * ]
     * <p>
     * Ожидаемый результат:
     * ["EUR" -> 0.0, "USD" -> 20.0, "RUB" -> 100.0]
     */
    Map<String, Double> getMaxMinusMinDeliveryMapByCurrency(List<OrderData> orderDataList) {
        Map<String, Double> different = orderDataList.stream()
                .filter(x -> x.getType().equals(Type.DELIVERY))
                .collect(Collectors.groupingBy(OrderData::getCurrency, summarizingDouble(OrderData::getAmount)))
                .entrySet().stream()
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue().getMax()-e.getValue().getMin()));

        //different.entrySet().forEach(e -> System.out.print(" "+ e.getKey() + " -> " + " "+e.getValue()));

        return different;
    }
}

