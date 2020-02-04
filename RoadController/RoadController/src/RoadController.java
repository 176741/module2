/*в данном коде были совместимы задания 1,2 и 5
* 1 - посмотрел код, почти запутался, понял, что автор допустил некоторые ошибки проектирования.
* Также понял, что алгоритм высчета стоимости скорее ориентируется на лунные сутки, чем на сгенерированные
* данные;
* 2 - постарался максимально описать уже исправленный код;
* 5 - исправлен и упрощен алгоритм высчета стоимости проезда ТС (про расчет по высоте никто ничего не сказал же...);
* Также в методе Car добавлен вывод параметра имеет ли ТС прицеп для более объективной оценки работы алгоритма расчета;
*/
import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController
{
    //здесь идет объявление констант для алгоритма высчета стоимости проезда
    private static double passengerCarMaxWeight = 3500.0; // kg
    private static int passengerCarMaxHeight = 2000; // mm (больше не используется в расчетах)
    private static int controllerMaxHeight = 3500; // mm (изменено с 4000 до 3500)

    private static int passengerCarPrice = 100; // RUB
    private static int cargoCarPrice = 250; // RUB
    private static int vehicleAdditionalPrice = 200; // RUB

    public static void main(String[] args)
    {
        System.out.println("Сколько автомобилей сгенерировать?");
//это алгоритм генерации количества авто
        Scanner scanner = new Scanner(System.in);//число заполняется пользователем
        int carsCount = scanner.nextInt();//число присваивается переменной

        for(int i = 0; i < carsCount; i++)//выполняется цикл согласно числу переменной
        {
            Car car = Camera.getNextCar();//генерация данных ТС методом Camera в переменные метода Car
            System.out.println(car);//Вывод данных метода Car

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car);
            if(price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car)
    {
        int carHeight = car.height;//переменная берется из метода Car
        int price = 0;//изначально цена проезда 0
        if (carHeight > controllerMaxHeight)//проверка на возможность проезда ТС как такового
        {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;//возвращение отрицательного результата для остановки процесса генерации данных
        }
        else /*if (carHeight > passengerCarMaxHeight) данная строчка перестала быть нужной, т.к. в задании сказано,
        что необходимо считать цену только по весу ТС и наличию прицепа*/
        {
            double weight = car.weight;//объявляем переменную и берем ее из метода Car
            //Грузовой автомобиль (сначала проверяем вес авто)
            if (weight > passengerCarMaxWeight) {
                price = cargoCarPrice;//присвоение переменной price цены за грузовое ТС
            }
            //Легковой автомобиль (в противном случае - это легковушка, логично)
            else{
                price = passengerCarPrice;//присвоение переменной price цены за легковое ТС
            }
            /*Теперь проверяем, есть ли у ТС прицеп, ведь легковые ТС тоже могут быть
            с прицепами и за них тоже необходимо взимать плату*/
                if (car.hasVehicle) {
                price = price + vehicleAdditionalPrice;//к ранее присвоенной стоимости добавляется стоимость за прицеп
            }
       }
        return price;//возвращается результат для отображения
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}