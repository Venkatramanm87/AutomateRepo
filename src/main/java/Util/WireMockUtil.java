package Util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockUtil {

    private static WireMockServer wireMockServer;

    /**
     * Starts WireMock server on a given port (e.g., 8089)
     */
    public static void startServer(int port) {
        if (wireMockServer == null || !wireMockServer.isRunning()) {
            wireMockServer = new WireMockServer(wireMockConfig().port(port));
            wireMockServer.start();
            WireMock.configureFor("localhost", port);
        }
    }

    /**
     * Stops the WireMock server
     */
    public static void stopServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    /**
     * Clears all stub mappings between tests
     */
    public static void reset() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            WireMock.reset();
        }
    }

    /**
     * Utility method to stub a GET endpoint returning JSON
     */
    public static void stubGet(String url, int statusCode, String responseJsonBody) {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseJsonBody)));
    }

    /**
     * Utility method to stub a POST endpoint returning JSON
     */
    public static void stubPost(String url, int statusCode, String responseJsonBody) {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseJsonBody)));
    }
}