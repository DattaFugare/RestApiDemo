package Payload;

public class payLoad {

    public static  String  createObjectPlayload()
    {
        return "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";
    }
    public static  String  updateOjectPayload()
    {
        return "{\n" +
                "   \"name\": \"Apple MacBook Pro 18\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2020,\n" +
                "      \"price\": 2049.99,\n" +
                "      \"CPU model\": \"Intel Core i10\",\n" +
                "      \"Hard disk size\": \"2 TB\",\n" +
                "      \"color\": \"black\"\n" +
                "   }\n" +
                "}";
    }
    public static  String  PartialupdateOjectPayload()
    {
        return "{\n" +
                "   \"name\": \"Apple MacBook Pro 20 edition (Updated Name)\"\n" +
                "}";
    }
}
