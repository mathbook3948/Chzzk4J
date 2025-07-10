import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.ChzzkClient;
import com.github.mathbook3948.api.model.GetLiveListRequest;

public class ChzzkClientTest {
    public static void main(String[] args) {
        String clientId = System.getenv("CHZZK_CLIENT_ID");
        String clientSecret = System.getenv("CHZZK_CLIENT_SECRET");

        System.out.println("Client ID: " + clientId);
        System.out.println("Client Secret: " + clientSecret);

        ObjectMapper objectMapper = new ObjectMapper();

        ChzzkClient client = ChzzkClient.build(objectMapper, clientId, clientSecret);

        GetLiveListRequest req = new GetLiveListRequest(null, null);

        try {
            System.out.println(objectMapper.writeValueAsString(client.getLiveList(req)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}