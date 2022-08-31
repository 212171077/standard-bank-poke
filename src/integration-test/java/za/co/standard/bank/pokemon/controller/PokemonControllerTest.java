package za.co.standard.bank.pokemon.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.standard.bank.pokemon.model.dto.PokemonResources;
import za.co.standard.bank.pokemon.model.dto.PokemonResponse;
import za.co.standard.bank.pokemon.payload.JwtResponse;
import za.co.standard.bank.pokemon.payload.MessageResponse;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokemonControllerTest extends CommonTestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonControllerTest.class);
    private String baseURL;
    private String getPokemonByEndpointURL;
    private String getPokemonByEndpointAndIDorNameUrl;

    private String userUrl;


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        baseURL = "http://localhost:" + port;
        getPokemonByEndpointURL = baseURL + "/api/pokemon/v1/get-pokemon/endpoint?endpoint=";
        getPokemonByEndpointAndIDorNameUrl = baseURL
            + "/api/pokemon/v1/get-pokemon/endpoint/id-or-name?endpoint=#endpoint#&idOrName=#idOrName#";
        userUrl = baseURL + "/api/pokemon/user/v1";
    }

    //Testing api/pokemon/v1/get-resources

    @Test
    public void whenCallingGetResources_ThenReturn200() {

        //Given
        saveRoles();

        ResponseEntity<MessageResponse> responseEntity = this.restTemplate
            .postForEntity(userUrl + "/signup", getAdminSignupRequest(), MessageResponse.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ResponseEntity<JwtResponse> tokenResponse = this.restTemplate
            .postForEntity(userUrl + "/signin", getAdminLoginRequest(), JwtResponse.class);

        assertEquals(HttpStatus.OK, tokenResponse.getStatusCode());

        String token = tokenResponse.getBody().getToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        ResponseEntity<PokemonResources> response = this.restTemplate.exchange(
            baseURL.concat("/api/pokemon/v1/get-resources"),
            HttpMethod.GET,
            new HttpEntity<>(httpHeaders),
            new ParameterizedTypeReference<>() {
            });

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    /**
     * Testing api/pokemon/v1/get-pokemon/{endpoint} service Expecting a successful response for all
     * the valid endpoints
     */

    @Test
    public void whenCallingGetPokemonBy_Endpoint_ThenReturn200() {
        List<String> successList = new ArrayList<>();
        List<String> failedList = new ArrayList<>();
        CommonTestUtil.ENDPOINT_LIST.forEach(endpoint -> {
            String url = getPokemonByEndpointURL + endpoint;
            ResponseEntity<PokemonResources> response = this.restTemplate.exchange(url,
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                PokemonResources.class);

            if (HttpStatus.OK.equals(response.getStatusCode())) {
                successList.add(endpoint);
            } else {
                LOGGER.error("Unable to call: {}", endpoint);
                failedList.add(endpoint);
            }
        });

        assertEquals(CommonTestUtil.ENDPOINT_LIST.size(), successList.size());
        assertEquals(0, failedList.size());
    }

    /**
     * Testing /api/pokemon/v1/get-pokemon/{endpoint}/{id-or-name} service Expecting a successful
     * response for all the valid endpoints
     */
    @Test
    public void whenCallingGetPokemonBy_EndpointAndIDOrName_ThenReturn200() {
        List<String> successList = new ArrayList<>();
        List<String> failedList = new ArrayList<>();
        CommonTestUtil.ENDPOINT_LIST.forEach(endpoint -> {
            String url = getPokemonByEndpointAndIDorNameUrl.replace("#endpoint#", endpoint);
            url = url.replace("#idOrName#", "1");
            LOGGER.info("URL: {}", url);
            ResponseEntity<PokemonResponse> response = this.restTemplate.exchange(url,
                HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
                PokemonResponse.class);

            if (HttpStatus.OK.equals(response.getStatusCode())) {
                successList.add(endpoint);
            } else {
                LOGGER.error("Unable to call: {}", endpoint);
                failedList.add(endpoint);
            }
        });

        assertEquals(CommonTestUtil.ENDPOINT_LIST.size(), successList.size());
        assertEquals(0, failedList.size());
    }


    @Test
    public void whenCallingGetPokemonBy_InvalidEndpoint_ThenReturn400() {
        String url = getPokemonByEndpointURL + "invalid-endpoint";
        ResponseEntity<PokemonResources> response = this.restTemplate.exchange(url,
            HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
            PokemonResources.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}
