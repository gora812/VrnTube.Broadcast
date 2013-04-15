package ru.yuriyshevtsov.vrntube.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Camera {

    public static Map<String, Camera> CAMERAS_MAP = new HashMap<String, Camera>();
    public static final List<Camera> CAMERAS = Arrays.asList(
            new Camera("dataart", "Вид из офиса компании DataArt на Плехановскую"),
            new Camera("holzunova", "Пересечение Хользунова и Шишкова"),

            new Camera("begov", "Пересечение Беговой и М.Проспекта"),
            new Camera("arena", "ТЦ Арена"),

            new Camera("relex", "Перекресток Кирова - 20 лет Октября (офис РЕЛЭКС, Дом быта)"),
            new Camera("klinich", "Пересечение 45 стрелковой дивизии и Московского Проспекта"),

            new Camera("zastava", "Площадь \"Застава\" перекресток"),
            new Camera("pamslav2", "Кольцо Пам.Славы, Хользунова - Моск проспект"),

            new Camera("pamslav3", "Памятник Славы"),
            new Camera("solnechnaya", "Солнечная"),

            new Camera("kolcov", "Улица Кольцовская"),
            new Camera("kosm60", "Кольцо на улице Космонавтов (\"Самолет\")"),

            new Camera("lenina", "Перекресток Ленина и Урицкого"),
            new Camera("ostuzheva", "Перекресток ул. Остужева и Переверткина (маг. \"Линия\")"),

            new Camera("viaduk2", "мост Виадук - пл.Застава"),
            new Camera("brusilova", "Брусилова - Ленинский проспект"),

            new Camera("proletka", "Кинотеатр Пролетарий"),
            new Camera("bpobedy", "Кольцо на Бульваре Победы"),

            new Camera("prtruda", "проспект Труда - Московский проспект"),
            new Camera("dexi", "Перекресток Среднемосковская - Никитинская (магазин DEXI)"),

            new Camera("uvzhd", "Перекресток перед зданием ЮВЖД"),
            new Camera("pamslavy", "Московский проспект - Памятник Славы")
            );

    public final String id;
    public final String address;
    public final String url;

    public Camera(String id, String address) {
        this.id = id;
        this.address = address;
        this.url = "rtmp://video.bvf.ru/live/" + id;

        CAMERAS_MAP.put(id, this);
    }
}
