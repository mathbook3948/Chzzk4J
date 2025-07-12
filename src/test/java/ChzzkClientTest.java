import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.ChzzkClient;
import com.github.mathbook3948.client.api.model.category.SearchCategoriesRequest;
import com.github.mathbook3948.client.api.model.channel.GetChannelInfoRequest;
import com.github.mathbook3948.client.api.model.live.GetLiveListRequest;

public class ChzzkClientTest {
    public static void main(String[] args) {
        String clientId = System.getenv("CHZZK_CLIENT_ID");
        String clientSecret = System.getenv("CHZZK_CLIENT_SECRET");

        System.out.println("Client ID: " + clientId);
        System.out.println("Client Secret: " + clientSecret);

        ObjectMapper objectMapper = new ObjectMapper();

        ChzzkClient client = ChzzkClient.build(objectMapper, clientId, clientSecret);

        try {

            GetLiveListRequest liveListReq = new GetLiveListRequest(null, null);

            System.out.println("Live List==============================================");
            System.out.println(objectMapper.writeValueAsString(client.getLiveList(liveListReq)));

            SearchCategoriesRequest categorySearchReq = new SearchCategoriesRequest(null, "게임");

            System.out.println("Category Search==============================================");
            System.out.println(objectMapper.writeValueAsString(client.searchCategories(categorySearchReq)));

            GetChannelInfoRequest channelInfoReq = new GetChannelInfoRequest(new String[]{"45e71a76e949e16a34764deb962f9d9f", "affa78deac0b23d2046b8ed4856c1e62"});

            System.out.println("Channel Info==============================================");
            System.out.println(objectMapper.writeValueAsString(client.getChannelInfo(channelInfoReq)));

            System.out.println("Create Client Session==============================================");
            System.out.println(objectMapper.writeValueAsString(client.createClientSession()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.getHttpClient().stop();
                client.getWebSocketClient().stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}