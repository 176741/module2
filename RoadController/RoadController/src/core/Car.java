package core;

public class Car
{
    public String number;
    public int height;
    public double weight;
    public boolean hasVehicle;
    public boolean isSpecial;

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        String trailer = hasVehicle ? "ТС с прицепом" : "";/*Дополнительно добавил в вывод данных параметр имеется ли
        прицеп для более удобного мониторинга результатов*/
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг" + "\n\t" + trailer;
    }
}