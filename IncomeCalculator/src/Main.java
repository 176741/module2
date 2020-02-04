import java.util.Scanner;

public class Main
{
    private static int minIncome = 200000;//минимальный возможный доход
    private static int maxIncome = 900000;//максимальный возможный доход

    private static int officeRentCharge = 140000;//аренда помещения
    private static int telephonyCharge = 12000;//плата за телефонию
    private static int internetAccessCharge = 7200;//плата за интернет

    private static int assistantSalary = 45000;//зарплата ассистента
    private static int financeManagerSalary = 90000;//зарплата финансового менеджера

    private static double mainTaxPercent = 0.24;//основной налог (24%)
    private static double managerPercent = 0.15;//вознаграждение менеджеру (15%)

    private static double minInvestmentsAmount = 100000;//минимальный размер инвестиций

    public static void main(String[] args)
    {//Полученная формула выглядит так 100000=(0.85I-294200)-(0.204I-70608), при таком уравнении I=500916.409, что и подтверждает программа
        // при значении прибыли в 500916 - компания НЕ МОЖЕТ инвестировать, при значении 500917 компания МОЖЕТ инвестировать
        while(true)
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");
            int income = (new Scanner(System.in)).nextInt();

            if(!checkIncomeRange(income)) {//проверка проверки на "дурака"
                continue;
            }

            double managerSalary = income * managerPercent;//расчет зарплаты менеджера (процент от прибыли)
            double pureIncome = income - managerSalary -
                calculateFixedCharges();//расчет чистой прибыли = прибыль - зарплата менеджера - постоянные затраты
            double taxAmount = mainTaxPercent * pureIncome;//расчет налога на прибыль (24 % от чистой прибыли)
            double pureIncomeAfterTax = pureIncome - taxAmount;//расчет прибыли после налоговой очистки

            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount;//проверка возможности инвестиций, больше ли или равна ли наичистейшая прибыль 100000
//вывод сообщений и результатов
            System.out.println("Зарплата менеджера: " + managerSalary);
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));//проверка положительного результата расчета налога и его вывода 
                                                 //(в противном случае будет показан 0)
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет"));//вывод главного результата всей программы
            if(pureIncome < 0) {//проверка чистой прибыли для вывода неутешительного предупреждения
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
        }
    }

    private static boolean checkIncomeRange(int income)//проверка на "дурака"
    {
        if(income < minIncome)
        {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if(income > maxIncome)
        {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges()//расчет постоянных расходов
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
